package guarana.gtk.codegen

import better.files.*
import guarana.codegen.*
import io.github.classgraph.{ClassGraph, ClassInfo}
import scala.meta.*

object run {
  lazy val classIndex = ClassIndex(
    ClassGraph()
      .enableClassInfo()
      .enableMethodInfo()
      .enableAnnotationInfo()
      .acceptPackages("org.gnome")
      .scan()
  )

  private lazy val widgetClassInfo = classIndex.scanResult.getClassInfo("org.gnome.gtk.Widget")

  lazy val WidgetNode = genNodeDescr(widgetClassInfo, "Widget", None)
  lazy val TextNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.Text"), "Text", Some(WidgetNode))

  def genNodeDescr(ci: ClassInfo, name: String, parent: Option[NodeDescr]): NodeDescr = {
    val widgetInfo = classIndex.getWidgetInfo(ci)

    val widgetProperties = (
      if (ci != widgetClassInfo) widgetInfo.properties -- classIndex.getWidgetInfo(ci.getSuperclass()).properties
      else widgetInfo.properties
    ).toList.sortBy(_.nameInPascalCase)

    NodeDescr(
      name = name,
      underlying = ci.getName(),
      upperBounds = parent.toSeq,
      creator = Seq(s"new ${ci.getName()}(${widgetInfo.constructorParams
          .map(p =>
            val paramType = mapTypeToNodes(p.tpe)
            if (paramType.startsWith("guarana.gtk")) s"${p.nameInCamelCase}.unwrap"
            else p.nameInCamelCase
          )
          .mkString(", ")})"),
      props = widgetProperties.map(p =>
        val varType = mapTypeToNodes(p.tpe)
        ExternalProp(
          name = p.nameInCamelCase,
          tpe = varType,
          getter = genGetter(p, varType),
          setter = genSetter(p, varType),
          externalName = Some(p.name),
        )
      ),
      opsExtra = Seq(
        s"export unwrap.{${widgetInfo.signals.toList.sortBy(_.name).map(v => s"on${v.nameInPascalCase}").mkString("\n      ", ",\n      ", "\n    ")}}"
      )
    )
  }

  /** Looks for raw gtk types that should be mapped to our guarana ones */
  def mapTypeToNodes(tpe: ReflectedType): String = tpe.value.syntax.replace("org.gnome.gtk.Widget", "guarana.gtk.Widget")

  def genGetter(prop: GtkPropertyInfo, tpe: String): String = {
    val isWrappedType = tpe.startsWith("guarana.gtk")
    val isNullable = tpe.endsWith(" | Null")
    val wrapperType = tpe.stripSuffix(" | Null")
    val getter = prop.actualGetterName.get
    if (isWrappedType) {
      if (isNullable) s"_.${getter}().?($wrapperType.wrap)"
      else s"n => $wrapperType.wrap(n.${getter}())"
    } else s"_.${getter}()"
  }

  def genSetter(prop: GtkPropertyInfo, tpe: String): String = {
    val isWrappedType = tpe.startsWith("guarana.gtk")
    val isNullable = tpe.endsWith(" | Null")
    val setter = s"set${prop.nameInPascalCase}"
    if (isWrappedType) {
      if (isNullable) s"(n, v) => n.${setter}(v.?(_.unwrap))"
      else s"(n, v) => n.${setter}(v.unwrap)"
    } else s"_.${setter}(_)"
  }

  def main(args: Array[String]): Unit = {
    for (node <- Seq(WidgetNode, TextNode)) {
      val f = File(s"src/main/scala/guarana/gtk/${node.name}.scala")
      f.writeText(
        s"""
        |package guarana
        |package gtk
        
        |import guarana.util.*

        |${genScalaSource(node)}
        """.stripMargin
      )
      println(s"$f written")
    }
  }
}
