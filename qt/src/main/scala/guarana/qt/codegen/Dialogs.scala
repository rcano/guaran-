package guarana.qt.codegen

import guarana.*
import guarana.codegen.*
import io.qt.widgets.*

trait Dialogs { self: run.type =>
  
  lazy val dialogNode = genNodeDescsrFromMetaObject(QDialog.staticMetaObject.unn, "DialogBase", Some(widgetNode))
    .addUninitParam(Seq(Parameter("parent", "Widget | Null = null", "---"), Parameter("windowFlags", "Qt.WindowFlags | Null = null", "---")))
    .copy(creator = Seq("new io.qt.widgets.QDialog(parent.?(_.unwrap), windowFlags)"))

  lazy val colorDialogNode = genNodeDescsrFromMetaObject(QColorDialog.staticMetaObject.unn, "ColorDialog", Some(dialogNode))

  lazy val fileDialogNode = genNodeDescsrFromMetaObject(QFileDialog.staticMetaObject.unn, "FileDialog", Some(dialogNode))
    .addUninitParam(Seq(Parameter("parent", "Widget | Null = null", "---"), Parameter("windowFlags", "Qt.WindowFlags | Null = null", "---")))
    .copy(creator = Seq("new io.qt.widgets.QFileDialog(parent.?(_.unwrap), windowFlags)"))

  lazy val fontDialogNode = genNodeDescsrFromMetaObject(QFontDialog.staticMetaObject.unn, "FontDialog", Some(dialogNode))
  

  lazy val dialogs = Seq(dialogNode, colorDialogNode, fileDialogNode, fontDialogNode)
}
