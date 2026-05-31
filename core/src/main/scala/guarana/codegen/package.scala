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
    def deprecated: Boolean
  }
  case class ExternalProp(
    name: String,
    tpe: String,
    getter: String,
    setter: String,
    externalName: Option[String] = None,
    visibility: Option[String] = None,
    overrideTpeInStaticPos: Option[String] = None,
    eagerEvaluation: Boolean = true,
    readOnly: Boolean = false,
    deprecated: Boolean = false,
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
    eagerEvaluation: Boolean = false,
    deprecated: Boolean = false,
  ) extends Property {
    def readOnly = false
  }

  case class EmitterDescr(name: String, tpe: String, initializer: Seq[String])
  case class Parameter(name: String, tpe: String, passAs: String, erased: Boolean = false)

  case class NodeDescr(
    `package`: String,
    name: String,
    underlying: String,
    creator: Seq[String],
    tpeParams: Seq[String] = Seq.empty,
    upperBounds: Seq[NodeDescr | String] = Seq.empty,
    lowerBounds: Seq[NodeDescr | String] = Seq.empty,
    props: Seq[Property] = Seq.empty,
    opsExtra: Seq[String] = Seq.empty,
    emitters: Seq[EmitterDescr] = Seq.empty,
    initExtra: Seq[String] = Seq.empty,
    uninitExtraParams: Seq[Parameter] = Seq.empty,
    uninitExtra: Seq[String] = Seq.empty,
    wrapExtra: Seq[String] = Seq.empty,
    isAbstract: Boolean = false,
    companionObjectExtras: Seq[String] = Seq.empty,
    companionObjectExtends: Option[String] = None,
    deprecated: Boolean = false,
  ) {
    override def toString = s"${`package`}.$name"
  }

  def genScalaSource(n: NodeDescr, objectExtends: Opt[String] = UnsetParam, toolkitType: Option[String] = None): String = {

    val tpeParamsDecls = if (n.tpeParams.nonEmpty) n.tpeParams.mkString("[", ", ", "]") else ""
    val tpeParams = tpeParamsDecls.replaceAll("[+-]", "").nn

    val emptyTpeParams = tpeParams.replaceAll(raw"\w|_ [><]: \w+", "Any")

    def propDecl(p: Property): String = p.visibility.map(s => s + " ").getOrElse("") + (p match {
      case p@ExternalProp(name = name, getter = getter, externalName = externalName, readOnly = true) => 
        s"""${if (p.deprecated) "@deprecated(\"\", \"\") " else ""}val ${name.capitalize}: ExternalObsVal.Aux[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}] = ExternalObsVal[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}]("${externalName.getOrElse(name)}", $getter)"""
      case p@ExternalProp(name = name, getter = getter, setter = setter, externalName = externalName, eagerEvaluation = eagerEval) => 
        s"""${if (p.deprecated) "@deprecated(\"\", \"\") " else ""}val ${name.capitalize}: ExternalVar.Aux[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}] = ExternalVar[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}]("${externalName.getOrElse(name)}", $getter, $setter, $eagerEval)"""
      case p@VarProp(name = name, tpe = tpe, initValue = initValue, eagerEvaluation = eval) => 
        s"""${if (p.deprecated) "@deprecated(\"\", \"\") " else ""}val ${name.capitalize}: Var[${p.tpeInStaticPos}] = Var[${p.tpeInStaticPos}]("$name", $initValue, $eval)"""
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
          |): ${toolkitType.fold("")(n => s"$n ?=> ")}VarContextAction[${n.name}$tpeParams] = {
          |  val res = uninitialized$tpeParams(${n.uninitExtraParams.filterNot(_.erased).map(_.name).mkString(", ")})
          |  $n.init(res)
          |  ${allMutVars.map(v => s"ifSet(${v._2.name}, res.${v._2.name} := _)").mkString("\n  ")}
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
    val extendsType = n.companionObjectExtends.orElse(ifSet(objectExtends, identity)).fold("")(v => s" extends $v")
    s"""${if (n.deprecated) "@deprecated(\"\", \"\") " else ""}opaque type ${n.name}$tpeParamsDecls $upperBounds $lowerBounds = ${(n.underlying +: n.upperBounds).mkString(" & ")}
      |${if (n.deprecated) "@deprecated(\"\", \"\") " else ""}object ${n.name}$extendsType {
      |  ${sortedProps.map(propDecl).mkString("\n  ")}
      |
      |  ${sortedEmitters.map(e => s"val ${e.name.capitalize} = Emitter[${e.tpe}]()").mkString("\n  ")}
      |
      |  extension $tpeParams(v: ${n.name}$tpeParams) {
      |    def unwrap: ${n.underlying} = v
      |
      |    ${nonPrivateSortedProps.map(p =>
              val varTpe = if p.readOnly then "ObsVal" else "Var"
              s"${if (p.deprecated) "@deprecated(\"\", \"\") " else ""}def ${p.name}: $varTpe.Aux[${p.tpe}, v.type] = $n.${p.name.capitalize}.asInstanceOf[$varTpe.Aux[${p.tpe}, v.type]]"
            ).mkString("\n    ")}

      |    ${sortedEmitters.map(e => s"def ${e.name}: Emitter.Aux[${e.tpe}, v.type] = $n.${e.name.capitalize}.forInstance(v)").mkString("\n      ")}

      |    ${n.opsExtra.mkString("\n      ")}
      |  }
      |
      |  def wrap$tpeParams(v: ${n.underlying}): ${n.name}$tpeParams = 
      |    val res = v.asInstanceOf[${n.name}$tpeParams]
      |    ${n.wrapExtra.mkString("\n    ")}
      |    res
      |
      |  def init$tpeParams(v: ${n.name}$tpeParams): ${toolkitType.fold("")(n => s"$n ?=> ")}Unit = ${ toolkitType.fold("")(n => s"(tk: $n) ?=> ")}{
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