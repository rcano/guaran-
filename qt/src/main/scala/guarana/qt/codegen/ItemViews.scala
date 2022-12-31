package guarana.qt.codegen

import guarana.*
import guarana.codegen.*
import io.qt.gui.*
import io.qt.widgets.*

trait ItemViews { self: run.type =>
  lazy val abstractItemViewNode =
    genNodeDescsrFromMetaObject(QAbstractItemView.staticMetaObject.unn, "AbstractItemView", Some(scrollAreaBaseNode))
      .addEmitter(
        EmitterDescr(
          "activated",
          "io.qt.core.QModelIndex",
          Seq(
            "v.activated.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.activated(v), index))))"
          )
        )
      )
      .addEmitter(
        EmitterDescr(
          "clicked",
          "io.qt.core.QModelIndex",
          Seq(
            "v.clicked.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.clicked(v), index))))"
          )
        )
      )
      .addEmitter(
        EmitterDescr(
          "doubleClicked",
          "io.qt.core.QModelIndex",
          Seq(
            "v.doubleClicked.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.doubleClicked(v), index))))"
          )
        )
      )
      .addEmitter(
        EmitterDescr(
          "entered",
          "io.qt.core.QModelIndex",
          Seq(
            "v.entered.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.entered(v), index))))"
          )
        )
      )
      .addEmitter(
        EmitterDescr(
          "iconSizeChanged",
          "io.qt.core.QSize",
          Seq(
            "v.iconSizeChanged.unn.connect(slot[io.qt.core.QSize](s => Toolkit.update(summon[Emitter.Context].emit(Ops.iconSizeChanged(v), s))))"
          )
        )
      )
      .addEmitter(
        EmitterDescr(
          "pressed",
          "io.qt.core.QModelIndex",
          Seq(
            "v.pressed.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.pressed(v), index))))"
          )
        )
      )
      .addEmitter(
        EmitterDescr(
          "viewportEntered",
          "Unit",
          Seq("v.viewportEntered.unn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.viewportEntered(v), ()))))")
        )
      )

  lazy val tableViewNode = genNodeDescsrFromMetaObject(QTableView.staticMetaObject.unn, "TableView", Some(abstractItemViewNode))

  lazy val listViewNode = genNodeDescsrFromMetaObject(QListView.staticMetaObject.unn, "ListView", Some(abstractItemViewNode))

  lazy val treeViewNode = genNodeDescsrFromMetaObject(QTreeView.staticMetaObject.unn, "TreeView", Some(abstractItemViewNode))

  lazy val headerViewNode = genNodeDescsrFromMetaObject(QHeaderView.staticMetaObject.unn, "HeaderView", Some(abstractItemViewNode))
    .addUninitParam(
      Seq(Parameter("orientation", "io.qt.core.Qt.Orientation", "---"), Parameter("parent", "Widget | Null = null", "---"))
    )
    .copy(creator = Seq("new io.qt.widgets.QHeaderView(orientation, parent.?(_.unwrap))"))

  lazy val columnViewNode = genNodeDescsrFromMetaObject(QColumnView.staticMetaObject.unn, "ColumnView", Some(abstractItemViewNode))
}
