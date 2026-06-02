package guarana.gtk.codegen

import better.files.*
import guarana.codegen.*
import io.github.classgraph.{ClassGraph, ClassInfo}
import scala.jdk.CollectionConverters.*
import scala.meta.*

object run extends Windows, Containers {
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
    .addOps(
      Seq(
        "def getChildren(): Iterator[org.gnome.gtk.Widget] = Iterator.unfold(v.getFirstChild()) {",
        "  case null => None",
        "  case w => Some(w -> w.getNextSibling())",
        "}"
      )
    )

  lazy val AllWidgets = WidgetNode :: AllWindows ::: AllContainers ::: classIndex.scanResult
    .getSubclasses("org.gnome.gtk.Widget")
    .iterator()
    .asScala
    .filter(!_.isInnerClass())
    .filterNot(c => (AllWindows ::: AllContainers).exists(_.name == c.getSimpleName()))
    .map(ci => genNodeDescr(ci, ci.getSimpleName(), Some(WidgetNode)))
    .toList

  def genNodeDescr(ci: ClassInfo, name: String, parent: Option[NodeDescr]): NodeDescr = {
    println(s"Parsing ${ci.getName()}")
    val widgetInfo = classIndex.getWidgetInfo(ci)

    val widgetProperties = (
      if (ci != widgetClassInfo) widgetInfo.properties -- classIndex.getWidgetInfo(ci.getSuperclass()).properties
      else widgetInfo.properties
    ).toList.sortBy(_.nameInPascalCase)

    val uninitParams = widgetInfo.constructorParams.map(p => Parameter(p.nameInCamelCase, mapTypeToNodes(p.tpe), ""))

    NodeDescr(
      "guarana.gtk",
      name = name,
      underlying = ci.getName(),
      upperBounds = parent.toSeq,
      isAbstract = ci.isAbstract(),
      companionObjectExtends = Some("VarsMap"),
      uninitExtraParams = uninitParams,
      creator = Seq(s"new ${ci.getName()}(${widgetInfo.constructorParams
          .map { p =>
            val paramType = mapTypeToNodes(p.tpe)
            val isNullable = paramType.endsWith(" | Null")
            if (paramType.startsWith("guarana.gtk")) {
              if (isNullable) s"${p.nameInCamelCase}.?(_.unwrap)"
              else s"${p.nameInCamelCase}.unwrap"
            } else p.nameInCamelCase
          }
          .mkString(", ")})"),
      props = widgetProperties.map(p =>
        val varType = mapTypeToNodes(p.tpe)
        ExternalProp(
          name = p.nameInCamelCase,
          tpe = varType,
          getter = genGetter(p, varType),
          setter = genSetter(p, varType),
          externalName = Some(p.name),
          deprecated = p.deprecated
        )
      ),
      opsExtra = Seq(
        s"export unwrap.{${widgetInfo.signals.toList.sortBy(_.name).map(v => s"on${v.nameInPascalCase}").mkString("\n      ", ",\n      ", "\n    ")}}"
      ),
      initExtra = Seq("connectVarsListener(v)")
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
    for (node <- AllWidgets) {
      val f = File(s"src/main/scala/guarana/gtk/${node.name}.scala")
      f.writeText(
        s"""
        |package guarana
        |package gtk

        |import guarana.util.*

        |${genScalaSource(node, toolkitType = Some("Toolkit"))}
        """.stripMargin
      )
      println(s"$f written")
      // println(genScalaSource(node, toolkitType = Some("Toolkit")))
    }
  }
}
