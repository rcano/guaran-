package guarana.gtk

import better.files.*
import io.github.classgraph.{ArrayTypeSignature, BaseTypeSignature, ClassGraph, ClassInfo, ClassRefTypeSignature, MethodInfo, TypeSignature, TypeVariableSignature}
import org.gnome.gtk.Widget
import scala.jdk.CollectionConverters.*
import scala.meta.*
import scala.meta.dialects.Scala38
import scala.util.Using

object Main {
  val topLevel = classOf[Widget]

  transparent trait Named {
    def nameParts: Seq[String]
    val name = nameParts.mkString("-")
    val nameInCamelCase: String = nameParts.map(_.capitalize).mkString("")
    val nameInPascalCase: String = nameParts.head + nameParts.tail.map(_.capitalize).mkString("")
  }
  case class PropertyInfo(nameParts: Seq[String], computedType: Type) extends Named {
  }

  case class SignalInfo(nameParts: Seq[String]) extends Named {}

  def main(args: Array[String]): Unit = Using(ClassGraph().enableClassInfo().enableMethodInfo().acceptPackages("org.gnome").scan()) {
    scan =>

      val sourcesDirectory = File("src/main/scala/guarana/gtk")

      val widgetClassInfo = scan.getClassInfo(classOf[Widget].getName())
      val allWidgets = widgetClassInfo +: scan.getSubclasses(classOf[Widget]).filter(!_.isInnerClass()).asScala.toSeq

      println(s"all widgets: ${allWidgets.map(_.getSimpleName())}")

      for (widgetInfo <- allWidgets) {
        val widgetName = widgetInfo.getSimpleName()
        val builderInfo = widgetInfo.getInnerClasses().filter(_.getSimpleName() == "Builder").getFirst()
        val propsAndSignals = builderInfo.getDeclaredMethodInfo().asScala.flatMap { methodInfo =>
          if (methodInfo.getName().startsWith("set")) Some(computeProperty(methodInfo))
          else if (methodInfo.getName().startsWith("on")) Some(computeSignal(methodInfo))
          else None
        }.toList

        val parentClass = widgetInfo.getSuperclass()
        val targetFile = sourcesDirectory / s"$widgetName.scala"
        
        val srcCode = q"""
          package guarana {
            package gtk {
              ${
                if (widgetInfo != widgetClassInfo) q"opaque type ${Type.Name(widgetName)} <: ${Type.Name(parentClass.getSimpleName)} = ${widgetInfo.getName().parse[Type].get} & ${Type.Name(parentClass.getSimpleName)}"
                else q"opaque type Widget >: org.gnome.gtk.Widget = org.gnome.gtk.Widget"
              }
              object ${Term.Name(widgetName)} {
                ..${
                  propsAndSignals collect {
                    case pi: PropertyInfo if !isConstructorOnly(pi, widgetInfo) =>
                      q"""val ${Pat.Var(Term.Name(pi.nameInCamelCase))}: ExternalVar.Aux[${Type.Name(widgetName)}, ${pi.computedType}] =
                        ExternalVar[${Type.Name(widgetName)}, ${pi.computedType}](${pi.name}, _.${Term.Name(s"get${pi.nameInCamelCase}")}(), _.${Term.Name(s"set${pi.nameInCamelCase}")}(_), true)"""
                  }
                }

                ()

                extension (v: ${Type.Name(widgetName)}) {
                  def unwrap: ${widgetInfo.getName().parse[Type].get}  = v

                  ..${
                    val exports = propsAndSignals collect { case si: SignalInfo => s"unwrap.on${si.nameInCamelCase}".parse[Importer].get }
                    if (exports.isEmpty) Nil else List(Export(exports))
                  }
                }
              }
            }
          }
        """

        // targetFile.writeText(srcCode.syntax)
      }
  }.get

  def toType(descr: TypeSignature): Type = descr match {
    case rawDt: BaseTypeSignature => t"${Type.Name(rawDt.getTypeStr().capitalize)}"
    case arrT: ArrayTypeSignature =>
      t"Array[${toType(arrT.getNestedType())}]"
    case classT: ClassRefTypeSignature =>
      val tparams = classT.getTypeArguments().asScala.map(a => toType(a.getTypeSignature())).toList
      val tpeName = classT.getFullyQualifiedClassName().parse[Type].get
      if (tparams.isEmpty) t"$tpeName | Null"
      else t"$tpeName[..$tparams] | Null"
    case tvar: TypeVariableSignature => t"_ <: ${toType(tvar.resolve().getClassBound())}"
    case _ => 
      scribe.error(s"Unsupported property type $descr")
      Type.Wildcard(Type.Bounds.empty)
  }

  def isConstructorOnly(pi: PropertyInfo, widgetInfo: ClassInfo): Boolean = {
    val getters = (widgetInfo.getDeclaredMethodInfo(s"get${pi.nameInCamelCase}").asScala
      ++ widgetInfo.getDeclaredMethodInfo(s"is${pi.nameInCamelCase}").asScala)
      .filter(m => toType(m.getTypeSignatureOrTypeDescriptor().getResultType()) == pi.computedType)

    val setters = widgetInfo.getDeclaredMethodInfo(s"set${pi.nameInCamelCase}")
      .filter(m => toType(m.getParameterInfo()(0).getTypeSignatureOrTypeDescriptor()) == pi.computedType)

    setters.isEmpty || getters.isEmpty
  }

  def computeNameParts(s: String): Seq[String] = {
    val sb = StringBuilder()
    val res = scala.collection.mutable.ListBuffer.empty[String]
    for (c <- s) {
      if (c.isUpper && sb.nonEmpty) {
        res += sb.result()
        sb.clear()
      }
      sb += c.toLower
    }
    if (sb.nonEmpty) res += sb.result()
    res.toList
  }

  def computeProperty(methodInfo: MethodInfo): PropertyInfo = {
    val parts = computeNameParts(methodInfo.getName().stripPrefix("set"))
    PropertyInfo(parts, toType(methodInfo.getParameterInfo()(0).getTypeSignatureOrTypeDescriptor()))
  }

  def computeSignal(methodInfo: MethodInfo): SignalInfo = {
    val parts = computeNameParts(methodInfo.getName().stripPrefix("on"))
    SignalInfo(parts)
  }
}
