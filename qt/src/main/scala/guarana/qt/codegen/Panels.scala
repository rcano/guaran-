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
  lazy val hboxNode = genNodeDescsrFromMetaObject(QWidget.staticMetaObject.nn, "HBox", Some(widgetNode))
    .addProperty(VarProp("nodes", "Seq[Widget | LayoutItem]", "Seq.empty", eagerEvaluation = true))
    .addUninitExtra(
      Seq(
        "QHBoxLayout(res)"
      )
    )
    .addInitExtra("val layout = v.layout().asInstanceOf[QHBoxLayout]" +: replaceAllNodesInit)
  lazy val vboxNode = genNodeDescsrFromMetaObject(QWidget.staticMetaObject.nn, "VBox", Some(widgetNode))
    .addProperty(VarProp("nodes", "Seq[Widget | LayoutItem]", "Seq.empty", eagerEvaluation = true))
    .addUninitExtra(
      Seq(
        "QVBoxLayout(res)"
      )
    )
    .addInitExtra("val layout = v.layout().asInstanceOf[QVBoxLayout]" +: replaceAllNodesInit)

  lazy val tabbedPaneNode = genNodeDescsrFromMetaObject(QTabWidget.staticMetaObject.nn, "TabbedPane", Some(widgetNode))
    .addProperty(VarProp("tabs", "Seq[Tab]", "Seq.empty", eagerEvaluation = true))
    .addInitExtra(
      Seq(
        "Toolkit.update {",
        "  v.varUpdates := EventIterator.foreach {",
        "    case v.tabs(oldv, newv) =>",
        "      v.clear()",
        "      newv.foreach(tab => v.addTab(tab.widget.unwrap, tab.icon.orNull, tab.label))",
        "    case _ =>",
        "  }",
        "}",
        "Toolkit.connectVar(CurrentIndex.forInstance(v), v.currentChanged.unn)"
      )
    )

  lazy val panels = Seq(
    hboxNode,
    vboxNode,
    tabbedPaneNode
  )
}
