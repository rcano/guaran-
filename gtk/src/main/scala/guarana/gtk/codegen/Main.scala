package guarana.gtk.codegen

import better.files.*
import io.github.classgraph.{ArrayTypeSignature, BaseTypeSignature, ClassGraph, ClassInfo, ClassRefTypeSignature, MethodInfo, TypeSignature, TypeVariableSignature}
import org.gnome.gtk.Widget
import scala.jdk.CollectionConverters.*
import scala.meta.*
import scala.meta.dialects.Scala38
import scala.meta.contrib.equality.*
import scala.util.Using
import scala.util.chaining.*

object Main {
  val topLevel = classOf[Widget]

  def main(args: Array[String]): Unit = Using(ClassGraph()
    .enableClassInfo()
    .enableMethodInfo()
    .enableAnnotationInfo()
    .acceptPackages("org.gnome").scan()) {
    scan =>

      val sourcesDirectory = File("src/main/scala/guarana/gtk")

      val widgetClassInfo = scan.getClassInfo(classOf[Widget].getName())
      val allWidgets = widgetClassInfo +: scan.getSubclasses(classOf[Widget]).filter(!_.isInnerClass()).asScala.toSeq

      println(s"all widgets: ${allWidgets.map(_.getSimpleName())}")

      val classIndex = ClassIndex(scan)

      for (widgetInfo <- allWidgets) {
        val widgetName = widgetInfo.getSimpleName()
        val computedInfo = classIndex.getWidgetInfo(widgetInfo)

        val parentClass = widgetInfo.getSuperclass()
        val targetFile = sourcesDirectory / s"$widgetName.scala"

        val fqnActualType = widgetInfo.getName().parse[Type].get

        val widgetProperties = (
          if (widgetInfo != widgetClassInfo) computedInfo.properties -- classIndex.getWidgetInfo(parentClass).properties
          else computedInfo.properties
        ).toList.sortBy(_.nameInPascalCase)

        val widgetSignals = (
          if (widgetInfo != widgetClassInfo) computedInfo.signals -- classIndex.getWidgetInfo(parentClass).signals
          else computedInfo.signals
        ).toList.sortBy(_.nameInPascalCase)
        
        val srcCode = q"""
          package guarana {
            package gtk {
              ${
                if (widgetInfo != widgetClassInfo) q"opaque type ${Type.Name(widgetName)} <: ${Type.Name(parentClass.getSimpleName)} = ${fqnActualType} & ${Type.Name(parentClass.getSimpleName)}"
                else q"opaque type Widget >: org.gnome.gtk.Widget = org.gnome.gtk.Widget"
              }
              object ${Term.Name(widgetName)} {
                ..${
                  widgetProperties collect {
                    case pi: GtkPropertyInfo =>
                      val propType = toType(pi.tpe)
                      val res = q"""val ${Pat.Var(Term.Name(pi.nameInPascalCase))}: ExternalVar.Aux[${Type.Name(widgetName)}, ${propType}] =
                        ExternalVar[${Type.Name(widgetName)}, ${propType}](${pi.name}, _.${Term.Name(s"get${pi.nameInPascalCase}")}(), _.${Term.Name(s"set${pi.nameInPascalCase}")}(_), true)"""
                      if (pi.deprecated) res.copy(mods = mod"""@deprecated("", "")""" :: Nil)
                      else res
                  }
                }

                ()

                extension (v: ${Type.Name(widgetName)}) {
                  def unwrap: ${fqnActualType}  = v

                  ..${
                    val exports = widgetSignals collect { case si: GtkSignalInfo => s"unwrap.on${si.nameInPascalCase}".parse[Importer].get }
                    if (exports.isEmpty) Nil else List(Export(exports))
                  }
                }

                def init(v: ${Type.Name(widgetName)}): Unit = {
                  ${
                    if (widgetInfo != widgetClassInfo) q"${Term.Name(parentClass.getSimpleName)}.init(v)"
                    else q"() // no parent class to init"
                  }

                  // whatever initialization logic here
                }

                ${
                  if (!widgetInfo.isAbstract()) {
                    q"""
                    def uninitialized(): ${Type.Name(widgetName)} = {
                      val res = new ${fqnActualType}()
                      res.asInstanceOf[${Type.Name(widgetName)}]  
                    }
                    """
                  } else {
                    q"()"
                  }
                }
              }
            }
          }
        """

        targetFile.writeText(srcCode.syntax)
      }
  }.get

  /** Special fixes to types that get parsed wrong from the library */
  val TypeFixes = Map(
    "java.util.Set[org.gnome.gtk.InputHints | Null] | Null" -> t"java.util.Set[org.gnome.gtk.InputHints] | Null",
    "java.util.Set[org.gnome.gtk.PrintCapabilities | Null] | Null" -> t"java.util.Set[org.gnome.gtk.PrintCapabilities] | Null",
    "java.util.Set[org.gnome.gtk.PopoverMenuFlags | Null] | Null" -> t"java.util.Set[org.gnome.gtk.PopoverMenuFlags] | Null",
    "org.gnome.gtk.SelectionModel | Null" -> t"org.gnome.gtk.SelectionModel[?] | Null",
    "java.util.Set[org.gnome.gdk.GLAPI | Null] | Null" -> t"java.util.Set[org.gnome.gdk.GLAPI] | Null",
    "org.gnome.gio.ListModel | Null" -> t"org.gnome.gio.ListModel[?] | Null",
  )

  def toType(descr: TypeSignature): Type = descr.match {
    case rawDt: BaseTypeSignature => t"${Type.Name(rawDt.getTypeStr().capitalize)}"
    case arrT: ArrayTypeSignature =>
      t"Array[${toType(arrT.getNestedType())}]"
    case classT: ClassRefTypeSignature =>
      val tparams = classT.getTypeArguments().asScala.map(a => toType(a.getTypeSignature())).toList
      var res = classT.getFullyQualifiedClassName().parse[Type].get
      res = if (tparams.isEmpty) t"$res" else t"$res[..$tparams]"

      if (descr.getTypeAnnotationInfo() != null && descr.getTypeAnnotationInfo().asScala.exists(a => a.getName == "org.jspecify.annotations.Nullable"))
        res = t"$res | Null"

      res
    case tvar: TypeVariableSignature => t"_ <: ${toType(tvar.resolve().getClassBound())}"
    case _ => 
      scribe.error(s"Unsupported property type $descr")
      Type.Wildcard(Type.Bounds.empty)
  }.pipe(res => TypeFixes.getOrElse(res.syntax, res))

}
