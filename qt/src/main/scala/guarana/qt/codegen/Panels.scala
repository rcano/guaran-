package guarana.qt.codegen

import guarana.codegen.*
import io.qt.gui.*
import io.qt.widgets.*

trait Panels { self: run.type =>
  
  private lazy val replaceAllNodesInit = Seq(
    "Toolkit.update {",
    "  v.varUpdates := EventIterator.foreach {",
    "    case v.nodes(oldv, newv) =>",
    "      var oldItem: QLayoutItem | Null = null",
    "      while {oldItem = layout.takeAt(0); oldItem != null} do oldItem.dispose()",
    "      newv.foreach {",
    "        case w: QWidget => layout.addWidget(w)",
    "        case LayoutItem.Space(s) => layout.addSpacing(s().toInt)",
    "        case LayoutItem.Stretch(s) => layout.addStretch(s().toInt)",
    "      }",
    "    case _ =>",
    "  }",
    "}",
  )
  lazy val hboxNode = genNodeDescsrFromMetaObject(QWidget.staticMetaObject.nn, "Hbox", Some(widgetNode))
    .addProperty(VarProp("nodes", "Seq[Widget | LayoutItem]", "Seq.empty", eagerEvaluation = true))
    .addUninitExtra(Seq(
      "QHBoxLayout(res)"
    ))
    .addInitExtra("val layout = v.layout().asInstanceOf[QHBoxLayout]" +: replaceAllNodesInit)
  lazy val vboxNode = genNodeDescsrFromMetaObject(QWidget.staticMetaObject.nn, "Vbox", Some(widgetNode))
    .addProperty(VarProp("nodes", "Seq[Widget | LayoutItem]", "Seq.empty", eagerEvaluation = true))
    .addUninitExtra(Seq(
      "QVBoxLayout(res)"
    ))
    .addInitExtra("val layout = v.layout().asInstanceOf[QVBoxLayout]" +: replaceAllNodesInit)


  lazy val panels = Seq(
    hboxNode,
    vboxNode,
  )
}
