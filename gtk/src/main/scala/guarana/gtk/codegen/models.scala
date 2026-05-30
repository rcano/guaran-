package guarana.gtk.codegen

import scala.meta.contrib.*
import scala.meta.prettyprinters.XtensionSyntax

case class WidgetInfo(properties: Set[GtkPropertyInfo], constructorParams: Seq[GtkPropertyInfo], signals: Set[GtkSignalInfo])

case class ReflectedType(value: scala.meta.Type) {
  override def equals(that: Any): Boolean = that match {
    case that: ReflectedType => value.isEqual(that.value)
    case _ => false
  }

  override def toString(): String = value.syntax
}

transparent trait Named {
  def nameParts: Seq[String]
  val name = nameParts.mkString("-")
  val nameInPascalCase: String = nameParts.map(_.capitalize).mkString("")
  val nameInCamelCase: String = nameParts.head + nameParts.tail.map(_.capitalize).mkString("")
}
case class GtkPropertyInfo(nameParts: Seq[String], tpe: ReflectedType, deprecated: Boolean)(val actualGetterName: Option[String]) extends Named {
  // val computedType = toType(tpe)
}

case class GtkSignalInfo(nameParts: Seq[String], deprecated: Boolean) extends Named {}
