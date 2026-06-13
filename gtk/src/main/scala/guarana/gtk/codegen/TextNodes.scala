package guarana.gtk.codegen

import guarana.codegen.*

trait TextNodes { self: run.type =>
  lazy val TextView = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.TextView"), "TextView", Some(WidgetNode))
    .copy(companionObjectExtends = Some("VarsMap, internal.TextViewSupport"))

  lazy val AllTextNodes = TextView :: Nil
}
