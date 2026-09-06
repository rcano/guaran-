package guarana.gtk.codegen

import scala.jdk.CollectionConverters.*

trait Windows { self: run.type =>

  lazy val WindowNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.Window"), "Window", Some(WidgetNode))

  lazy val PopoverNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.Popover"), "Popover", Some(WidgetNode))
  lazy val PopoverMenuNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.PopoverMenu"), "PopoverMenu", Some(PopoverNode))


  lazy val AllWindows = WindowNode :: PopoverNode :: PopoverMenuNode :: classIndex.scanResult
    .getSubclasses("org.gnome.gtk.Window")
    .iterator()
    .asScala
    .filter(!_.isInnerClass())
    .map(ci => genNodeDescr(ci, ci.getSimpleName(), Some(WindowNode)))
    .toList

}