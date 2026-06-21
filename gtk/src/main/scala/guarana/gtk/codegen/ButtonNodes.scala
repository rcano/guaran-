package guarana.gtk.codegen

import guarana.codegen.*
import scala.util.chaining.*

trait ButtonNodes { self: run.type =>

  lazy val ButtonNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.Button"), "Button", Some(WidgetNode))
    .addProperty(ExternalProp("label", "String | Null"))
    .pipe(n => n.copy(uninitExtraParams = n.uninitExtraParams.filterNot(_.name == "label"), creator = n.creator.filterNot(_.contains("ifSet(label"))))

  lazy val ToggleButton = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.ToggleButton"), "ToggleButton", Some(ButtonNode)).removeLabelParam
  lazy val CheckButton = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.CheckButton"), "CheckButton", Some(WidgetNode)).removeLabelParam
  lazy val LinkButton = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.LinkButton"), "LinkButton", Some(ButtonNode)).removeLabelParam
  lazy val LockButton = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.LockButton"), "LockButton", Some(ButtonNode)).removeLabelParam
  lazy val StyleSchemeChooserButton = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtksourceview.StyleSchemeChooserButton"), "StyleSchemeChooserButton", Some(ButtonNode)).removeLabelParam

  lazy val AllButtonNodes = ButtonNode :: CheckButton :: ToggleButton :: LinkButton :: LockButton :: Nil

  extension (n: NodeDescr) private def removeLabelParam: NodeDescr =
    n.copy(
      uninitExtraParams = n.uninitExtraParams.filterNot(_.name == "label"),
      creator = n.creator.filterNot(_.contains("ifSet(label, "))
    )
}
