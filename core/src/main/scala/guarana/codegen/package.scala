package guarana

import util.{Opt, UnsetParam, ifSet}

/** This package contains classes useful to model automatically generated wrappers for toolkits
  * Since there's no way in scala to abstract over method parameters, and the builders guaraná provides
  * are based on factory methods, we need a way to generate the node mappings trying to avoid manual coding
  * mistakes.
  */
package object codegen

package codegen {

  sealed trait Property {
    def name: String
    def tpe: String
    def visibility: Option[String]
    def overrideTpeInStaticPos: Option[String]
    def tpeInStaticPos = (overrideTpeInStaticPos getOrElse tpe) match {
      case "_" => "Any"
      case other => other
    }
    def readOnly: Boolean
  }
  case class ExternalProp(
    name: String,
    tpe: String,
    getter: String,
    setter: String,
    visibility: Option[String] = None,
    overrideTpeInStaticPos: Option[String] = None,
    eagerEvaluation: Boolean = true,
    readOnly: Boolean = false,
  ) extends Property
  object ExternalProp {
    def getter(name: String, tpe: String) = tpe match {
      case "Boolean" => s"_.is${name.capitalize}"
      case "Byte" | "Short" | "Int" | "Char" | "Long" | "Float" | "Double" => s"_.get${name.capitalize}"
      case s if s.endsWith("| Null") => s"_.get${name.capitalize}"
      case _ => s"_.get${name.capitalize}.nn"
    }
    def setter(name: String, tpe: String) = s"_.set${name.capitalize}(_)"
    def apply(name: String, tpe: String): ExternalProp = ExternalProp(name, tpe, getter(name, tpe), setter(name, tpe))
  }
  case class VarProp(
    name: String,
    tpe: String,
    initValue: String,
    visibility: Option[String] = None,
    overrideTpeInStaticPos: Option[String] = None,
    eagerEvaluation: Boolean = false
  ) extends Property {
    def readOnly = false
  }

  case class EmitterDescr(name: String, tpe: String, initializer: Seq[String])
  case class Parameter(name: String, tpe: String, passAs: String, erased: Boolean = false)

  case class NodeDescr(
    val name: String,
    val underlying: String,
    val creator: Seq[String],
    val tpeParams: Seq[String] = Seq.empty,
    val upperBounds: Seq[NodeDescr | String] = Seq.empty,
    val lowerBounds: Seq[NodeDescr | String] = Seq.empty,
    val props: Seq[Property] = Seq.empty,
    val opsExtra: Seq[String] = Seq.empty,
    val emitters: Seq[EmitterDescr] = Seq.empty,
    val initExtra: Seq[String] = Seq.empty,
    val uninitExtraParams: Seq[Parameter] = Seq.empty,
    val uninitExtra: Seq[String] = Seq.empty,
    val wrapExtra: Seq[String] = Seq.empty,
    val isAbstract: Boolean = false,
    val companionObjectExtras: Seq[String] = Seq.empty,
  ) {
    override def toString = name
  }

  def genScalaSource(n: NodeDescr, objectExtends: Opt[String] = UnsetParam, toolkitIsGlobal: Boolean = true): String = {

    val tpeParamsDecls = if (n.tpeParams.nonEmpty) n.tpeParams.mkString("[", ", ", "]") else ""
    val tpeParams = tpeParamsDecls.replaceAll("[+-]", "").nn

    val emptyTpeParams = tpeParams.replaceAll(raw"\w|_ [><]: \w+", "Any")

    def propDecl(p: Property): String = p.visibility.map(s => s + " ").getOrElse("") + (p match {
      case p@ExternalProp(name, tpe, getter, setter, _, _, eagerEval, true) => 
        s"""val ${name.capitalize}: ExternalObsVal.Aux[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}] = ExternalObsVal[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}]("$name", $getter)"""
      case p@ExternalProp(name, tpe, getter, setter, _, _, eagerEval, _) => 
        s"""val ${name.capitalize}: ExternalVar.Aux[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}] = ExternalVar[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}]("$name", $getter, $setter, $eagerEval)"""
      case p@VarProp(name, tpe, initValue, _, _, eval) => 
        s"""val ${name.capitalize}: Var[${p.tpeInStaticPos}] = Var[${p.tpeInStaticPos}]("$name", $initValue, $eval)"""
    })

    val seenVars = collection.mutable.Set.empty[String]
    val allMutVars: Vector[(NodeDescr, Property)] = Iterator.unfold(Seq(n)) {
      case Seq() => None
      case parents => 
        val allParentVars = parents.flatMap(p => p.props.filterNot(prop => seenVars(prop.name) || prop.visibility.exists(_ `startsWith` "private")).map(p -> _))
        seenVars ++= allParentVars.map(_._2.name)
        Some(allParentVars -> parents.flatMap(_.upperBounds.collect { case n: NodeDescr => n }))
    }.flatten.toVector.filter(!_._2.readOnly).sortBy(_._2.name)

    val initializers = 
      if (!n.isAbstract) {
        s"""def uninitialized$tpeParams(${n.uninitExtraParams.filterNot(_.erased).map(t => s"${t.name}: ${t.tpe}").mkString(", ")}): ${n.name}$tpeParams = {
          |  val res = ${n.creator.mkString("\n  ")}
          |  ${n.uninitExtra.mkString("\n    ")}
          |  res.asInstanceOf[${n.name}$tpeParams]
          |}
          |
          |def apply$tpeParams(
          |  ${if (n.uninitExtraParams.nonEmpty) n.uninitExtraParams.filterNot(_.erased).map(t => s"${t.name}: ${t.tpe}").mkString(", ") + "," else ""}
          |  ${allMutVars.map(v => s"${v._2.name}: Opt[Binding[${v._2.tpe}]] = UnsetParam").mkString(",\n  ")}
          |): ${if !toolkitIsGlobal then "Toolkit ?=> " else ""}VarContextAction[${n.name}$tpeParams] = {
          |  val res = uninitialized$tpeParams(${n.uninitExtraParams.filterNot(_.erased).map(_.name).mkString(", ")})
          |  ${n.name}.init(res)
          |  ${allMutVars.map(v => s"ifSet(${v._2.name}, ${v._1.name}.ops.${v._2.name}(res) := _)").mkString("\n  ")}
          |  res
          |}
          |
        """.stripMargin.trim.nn.split("\n").asInstanceOf[Array[String]].toSeq
      } else Seq.empty 

    val sortedProps = n.props.sortBy(_.name)
    val nonPrivateSortedProps = sortedProps.filterNot(_.visibility.exists(_ `startsWith` "private"))
    val sortedEmitters = n.emitters.sortBy(_.name)

    val upperBounds = if (n.upperBounds.nonEmpty) n.upperBounds.mkString("<: ", " & ", "") else ""
    val lowerBounds = if (n.lowerBounds.nonEmpty) n.lowerBounds.mkString(">: ", " & ", "") else ""
    val extendsType = ifSet(objectExtends, s => s" extends $s").getOrElse("")
    s"""opaque type ${n.name}$tpeParamsDecls $upperBounds $lowerBounds = ${(n.underlying +: n.upperBounds).mkString(" & ")}
      |object ${n.name}$extendsType {
      |  ${sortedProps.map(propDecl).mkString("\n  ")}
      |
      |  ${sortedEmitters.map(e => s"val ${e.name.capitalize} = Emitter[${e.tpe}]()").mkString("\n  ")}
      |
      |  given ops: Ops.type = Ops
      |  object Ops {
      |    extension $tpeParams(v: ${n.name}$tpeParams) {
      |      ${nonPrivateSortedProps.map(p =>
                 val varTpe = if p.readOnly then "ObsVal" else "Var"
                 s"def ${p.name}: $varTpe.Aux[${p.tpe}, v.type] = ${n.name}.${p.name.capitalize}.asInstanceOf[$varTpe.Aux[${p.tpe}, v.type]]"
               ).mkString("\n      ")}

      |      ${sortedEmitters.map(e => s"def ${e.name}: Emitter.Aux[${e.tpe}, v.type] = ${n.name}.${e.name.capitalize}.forInstance(v)").mkString("\n      ")}

      |      ${n.opsExtra.mkString("\n      ")}
      |      def unwrap: ${n.underlying} = v
      |    }
      |  }
      |
      |  def wrap$tpeParams(v: ${n.underlying}): ${n.name}$tpeParams = 
      |    val res = v.asInstanceOf[${n.name}$tpeParams]
      |    ${n.wrapExtra.mkString("\n    ")}
      |    res
      |
      |  def init$tpeParams(v: ${n.name}$tpeParams): ${if !toolkitIsGlobal then "Toolkit ?=> " else ""}Unit = ${if !toolkitIsGlobal then "(tk: Toolkit) ?=> " else ""}{
      |    ${n.upperBounds.headOption.map(p => s"$p.init(v)").getOrElse("")}
      |    ${n.initExtra.mkString("\n    ")}
      |    ${sortedEmitters.flatMap(_.initializer).mkString("\n    ")}
      |  }
      |  ${initializers.mkString("\n  ")}
      |  ${n.companionObjectExtras.mkString("\n  ")}
      |}
    """.stripMargin.trim.nn
      .replace(" | UncheckedNull", "").nn
  }
}