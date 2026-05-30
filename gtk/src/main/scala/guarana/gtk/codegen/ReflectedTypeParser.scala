package guarana.gtk.codegen

import io.github.classgraph.{ArrayTypeSignature, BaseTypeSignature, ClassRefTypeSignature, TypeSignature, TypeVariableSignature}
import scala.jdk.CollectionConverters.*
import scala.meta.*
import scala.util.chaining.*

object ReflectedTypeParser {
    /** Special fixes to types that get parsed wrong from the library */
  val TypeFixes = Map(
    "java.util.Set[org.gnome.gtk.InputHints | Null] | Null" -> t"java.util.Set[org.gnome.gtk.InputHints] | Null",
    "java.util.Set[org.gnome.gtk.PrintCapabilities | Null] | Null" -> t"java.util.Set[org.gnome.gtk.PrintCapabilities] | Null",
    "java.util.Set[org.gnome.gtk.PopoverMenuFlags | Null] | Null" -> t"java.util.Set[org.gnome.gtk.PopoverMenuFlags] | Null",
    "org.gnome.gtk.SelectionModel | Null" -> t"org.gnome.gtk.SelectionModel[?] | Null",
    "java.util.Set[org.gnome.gdk.GLAPI | Null] | Null" -> t"java.util.Set[org.gnome.gdk.GLAPI] | Null",
    "org.gnome.gio.ListModel | Null" -> t"org.gnome.gio.ListModel[?] | Null",
  )

  private val typesCache = collection.mutable.HashMap.empty[TypeSignature, Type]
  def toType(descr: TypeSignature): Type = {
    typesCache.getOrElseUpdate(
      descr, {
        descr
          .match {
            case rawDt: BaseTypeSignature => t"${Type.Name(rawDt.getTypeStr().capitalize)}"
            case arrT: ArrayTypeSignature =>
              t"Array[${toType(arrT.getNestedType())}]"
            case classT: ClassRefTypeSignature =>
              val tparams = classT.getTypeArguments().asScala.map(a => toType(a.getTypeSignature())).toList
              var res = classT.getFullyQualifiedClassName().parse[Type].get
              if (tparams.nonEmpty) res = t"$res[..$tparams]"

              if (
                descr.getTypeAnnotationInfo() != null && descr
                  .getTypeAnnotationInfo()
                  .asScala
                  .exists(a => a.getName == "org.jspecify.annotations.Nullable")
              )
                res = t"$res | Null"

              res
            case tvar: TypeVariableSignature => t"_ <: ${toType(tvar.resolve().getClassBound())}"
            case _ =>
              scribe.error(s"Unsupported property type $descr")
              Type.Wildcard(Type.Bounds.empty)
          }
          .pipe(res => TypeFixes.getOrElse(res.syntax, res))
      }
    )
  }
}
