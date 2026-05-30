package guarana
package gtk.codegen
import scala.meta.*
import scala.meta.contrib.*

object Overrides {
  private case class WidgetOverrides(
    properties: Map[String, Property],
    extraProperties: Seq[Property]
  )

  case class Property(
    declaration: Decl.Val,
    accessor: Decl.Def,
    asParam: Term.Param,
  )

  private val anyWidgetOverrides: WidgetOverrides = WidgetOverrides(
    properties = Map(),
    extraProperties = Seq()
  )
  private val widgetOverrides: Map[String, WidgetOverrides] = Map(

  )

  def getPropertyOverride(widgetName: String, prop: GtkPropertyInfo): Option[Property] =
    widgetOverrides.get(widgetName).flatMap(_.properties.get(prop.name)).orElse(anyWidgetOverrides.properties.get(prop.name))

  def getExtraProperties(widgetName: String): Seq[Property] =
    anyWidgetOverrides.extraProperties ++ widgetOverrides.get(widgetName).toSeq.flatMap(_.extraProperties)
}
