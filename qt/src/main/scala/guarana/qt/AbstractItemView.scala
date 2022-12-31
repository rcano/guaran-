
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type AbstractItemView <: ScrollAreaBase  = io.qt.widgets.QAbstractItemView & ScrollAreaBase
object AbstractItemView {
  private val AbstractItemViewInitialized: Var[Boolean] = Var[Boolean]("AbstractItemViewInitialized", false, false)
  val AlternatingRowColors: ExternalVar.Aux[AbstractItemView, Boolean] = ExternalVar[AbstractItemView, Boolean]("alternatingRowColors", _.alternatingRowColors(), _.setAlternatingRowColors(_), true)
  val AutoScroll: ExternalVar.Aux[AbstractItemView, Boolean] = ExternalVar[AbstractItemView, Boolean]("autoScroll", _.hasAutoScroll(), _.setAutoScroll(_), true)
  val AutoScrollMargin: ExternalVar.Aux[AbstractItemView, Int] = ExternalVar[AbstractItemView, Int]("autoScrollMargin", _.autoScrollMargin(), _.setAutoScrollMargin(_), true)
  val DefaultDropAction: ExternalVar.Aux[AbstractItemView, io.qt.core.Qt.DropAction] = ExternalVar[AbstractItemView, io.qt.core.Qt.DropAction]("defaultDropAction", _.defaultDropAction().unn, _.setDefaultDropAction(_), true)
  val DragDropMode: ExternalVar.Aux[AbstractItemView, io.qt.widgets.QAbstractItemView.DragDropMode] = ExternalVar[AbstractItemView, io.qt.widgets.QAbstractItemView.DragDropMode]("dragDropMode", _.dragDropMode().unn, _.setDragDropMode(_), true)
  val DragDropOverwriteMode: ExternalVar.Aux[AbstractItemView, Boolean] = ExternalVar[AbstractItemView, Boolean]("dragDropOverwriteMode", _.dragDropOverwriteMode(), _.setDragDropOverwriteMode(_), true)
  val DragEnabled: ExternalVar.Aux[AbstractItemView, Boolean] = ExternalVar[AbstractItemView, Boolean]("dragEnabled", _.dragEnabled(), _.setDragEnabled(_), true)
  val EditTriggers: ExternalVar.Aux[AbstractItemView, io.qt.widgets.QAbstractItemView.EditTriggers | Null] = ExternalVar[AbstractItemView, io.qt.widgets.QAbstractItemView.EditTriggers | Null]("editTriggers", _.editTriggers(), _.setEditTriggers(_), true)
  val HorizontalScrollMode: ExternalVar.Aux[AbstractItemView, io.qt.widgets.QAbstractItemView.ScrollMode] = ExternalVar[AbstractItemView, io.qt.widgets.QAbstractItemView.ScrollMode]("horizontalScrollMode", _.horizontalScrollMode().unn, _.setHorizontalScrollMode(_), true)
  val IconSize: ExternalVar.Aux[AbstractItemView, io.qt.core.QSize | Null] = ExternalVar[AbstractItemView, io.qt.core.QSize | Null]("iconSize", _.iconSize(), _.setIconSize(_), true)
  val SelectionBehavior: ExternalVar.Aux[AbstractItemView, io.qt.widgets.QAbstractItemView.SelectionBehavior] = ExternalVar[AbstractItemView, io.qt.widgets.QAbstractItemView.SelectionBehavior]("selectionBehavior", _.selectionBehavior().unn, _.setSelectionBehavior(_), true)
  val SelectionMode: ExternalVar.Aux[AbstractItemView, io.qt.widgets.QAbstractItemView.SelectionMode] = ExternalVar[AbstractItemView, io.qt.widgets.QAbstractItemView.SelectionMode]("selectionMode", _.selectionMode().unn, _.setSelectionMode(_), true)
  val ShowDropIndicator: ExternalVar.Aux[AbstractItemView, Boolean] = ExternalVar[AbstractItemView, Boolean]("showDropIndicator", _.showDropIndicator(), _.setDropIndicatorShown(_), true)
  val TabKeyNavigation: ExternalVar.Aux[AbstractItemView, Boolean] = ExternalVar[AbstractItemView, Boolean]("tabKeyNavigation", _.tabKeyNavigation(), _.setTabKeyNavigation(_), true)
  val TextElideMode: ExternalVar.Aux[AbstractItemView, io.qt.core.Qt.TextElideMode] = ExternalVar[AbstractItemView, io.qt.core.Qt.TextElideMode]("textElideMode", _.textElideMode().unn, _.setTextElideMode(_), true)
  val VerticalScrollMode: ExternalVar.Aux[AbstractItemView, io.qt.widgets.QAbstractItemView.ScrollMode] = ExternalVar[AbstractItemView, io.qt.widgets.QAbstractItemView.ScrollMode]("verticalScrollMode", _.verticalScrollMode().unn, _.setVerticalScrollMode(_), true)

  val Activated = Emitter[io.qt.core.QModelIndex]()
  val Clicked = Emitter[io.qt.core.QModelIndex]()
  val DoubleClicked = Emitter[io.qt.core.QModelIndex]()
  val Entered = Emitter[io.qt.core.QModelIndex]()
  val IconSizeChanged = Emitter[io.qt.core.QSize]()
  val Pressed = Emitter[io.qt.core.QModelIndex]()
  val ViewportEntered = Emitter[Unit]()

  given ops: Ops.type = Ops
  object Ops {
    extension (v: AbstractItemView) {
      def alternatingRowColors: Var.Aux[Boolean, v.type] = AbstractItemView.AlternatingRowColors.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoScroll: Var.Aux[Boolean, v.type] = AbstractItemView.AutoScroll.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoScrollMargin: Var.Aux[Int, v.type] = AbstractItemView.AutoScrollMargin.asInstanceOf[Var.Aux[Int, v.type]]
      def defaultDropAction: Var.Aux[io.qt.core.Qt.DropAction, v.type] = AbstractItemView.DefaultDropAction.asInstanceOf[Var.Aux[io.qt.core.Qt.DropAction, v.type]]
      def dragDropMode: Var.Aux[io.qt.widgets.QAbstractItemView.DragDropMode, v.type] = AbstractItemView.DragDropMode.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractItemView.DragDropMode, v.type]]
      def dragDropOverwriteMode: Var.Aux[Boolean, v.type] = AbstractItemView.DragDropOverwriteMode.asInstanceOf[Var.Aux[Boolean, v.type]]
      def dragEnabled: Var.Aux[Boolean, v.type] = AbstractItemView.DragEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def editTriggers: Var.Aux[io.qt.widgets.QAbstractItemView.EditTriggers | Null, v.type] = AbstractItemView.EditTriggers.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractItemView.EditTriggers | Null, v.type]]
      def horizontalScrollMode: Var.Aux[io.qt.widgets.QAbstractItemView.ScrollMode, v.type] = AbstractItemView.HorizontalScrollMode.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractItemView.ScrollMode, v.type]]
      def iconSize: Var.Aux[io.qt.core.QSize | Null, v.type] = AbstractItemView.IconSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def selectionBehavior: Var.Aux[io.qt.widgets.QAbstractItemView.SelectionBehavior, v.type] = AbstractItemView.SelectionBehavior.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractItemView.SelectionBehavior, v.type]]
      def selectionMode: Var.Aux[io.qt.widgets.QAbstractItemView.SelectionMode, v.type] = AbstractItemView.SelectionMode.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractItemView.SelectionMode, v.type]]
      def showDropIndicator: Var.Aux[Boolean, v.type] = AbstractItemView.ShowDropIndicator.asInstanceOf[Var.Aux[Boolean, v.type]]
      def tabKeyNavigation: Var.Aux[Boolean, v.type] = AbstractItemView.TabKeyNavigation.asInstanceOf[Var.Aux[Boolean, v.type]]
      def textElideMode: Var.Aux[io.qt.core.Qt.TextElideMode, v.type] = AbstractItemView.TextElideMode.asInstanceOf[Var.Aux[io.qt.core.Qt.TextElideMode, v.type]]
      def verticalScrollMode: Var.Aux[io.qt.widgets.QAbstractItemView.ScrollMode, v.type] = AbstractItemView.VerticalScrollMode.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractItemView.ScrollMode, v.type]]

      def activated: Emitter.Aux[io.qt.core.QModelIndex, v.type] = AbstractItemView.Activated.forInstance(v)
      def clicked: Emitter.Aux[io.qt.core.QModelIndex, v.type] = AbstractItemView.Clicked.forInstance(v)
      def doubleClicked: Emitter.Aux[io.qt.core.QModelIndex, v.type] = AbstractItemView.DoubleClicked.forInstance(v)
      def entered: Emitter.Aux[io.qt.core.QModelIndex, v.type] = AbstractItemView.Entered.forInstance(v)
      def iconSizeChanged: Emitter.Aux[io.qt.core.QSize, v.type] = AbstractItemView.IconSizeChanged.forInstance(v)
      def pressed: Emitter.Aux[io.qt.core.QModelIndex, v.type] = AbstractItemView.Pressed.forInstance(v)
      def viewportEntered: Emitter.Aux[Unit, v.type] = AbstractItemView.ViewportEntered.forInstance(v)

      def reset() = v.reset()
      def setRootIndex(arg0: io.qt.core.QModelIndex | Null) = v.setRootIndex(arg0)
      def doItemsLayout() = v.doItemsLayout()
      def selectAll() = v.selectAll()
      def edit(arg0: io.qt.core.QModelIndex | Null) = v.edit(arg0)
      def clearSelection() = v.clearSelection()
      def setCurrentIndex(arg0: io.qt.core.QModelIndex | Null) = v.setCurrentIndex(arg0)
      def scrollToTop() = v.scrollToTop()
      def scrollToBottom() = v.scrollToBottom()
      def update(arg0: io.qt.core.QModelIndex | Null) = v.update(arg0)
      def unwrap: io.qt.widgets.QAbstractItemView = v
    }
  }

  def wrap(v: io.qt.widgets.QAbstractItemView): AbstractItemView = 
    val res = v.asInstanceOf[AbstractItemView]
    if !Toolkit.stateReader(AbstractItemViewInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: AbstractItemView): Unit = {
    ScrollAreaBase.init(v)
    Toolkit.connectVar(IconSize.forInstance[v.type], v.iconSizeChanged.unn)
    Toolkit.update(AbstractItemViewInitialized.forInstance[v.type] := true)
    v.activated.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.activated(v), index))))
    v.clicked.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.clicked(v), index))))
    v.doubleClicked.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.doubleClicked(v), index))))
    v.entered.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.entered(v), index))))
    v.iconSizeChanged.unn.connect(slot[io.qt.core.QSize](s => Toolkit.update(summon[Emitter.Context].emit(Ops.iconSizeChanged(v), s))))
    v.pressed.unn.connect(slot[io.qt.core.QModelIndex](index => Toolkit.update(summon[Emitter.Context].emit(Ops.pressed(v), index))))
    v.viewportEntered.unn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.viewportEntered(v), ()))))
  }
  
  
}
        