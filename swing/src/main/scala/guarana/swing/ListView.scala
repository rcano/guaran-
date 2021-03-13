//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, *}
import java.awt.event.*
import javax.swing.{Action => _, *}
import javax.swing.event.*
import guarana.swing.util.*
import scala.jdk.CollectionConverters.*
import scala.util.chaining.*

opaque type ListView[+E] <: Component  = javax.swing.JList[_ <: E] & Component
object ListView extends VarsMap {
  val UI: SwingVar.Aux[ListView[Any], javax.swing.plaf.ListUI] = SwingVar[ListView[Any], javax.swing.plaf.ListUI]("UI", _.getUI.nn, _.setUI(_))
  val CellRenderer: SwingVar.Aux[ListView[Any], javax.swing.ListCellRenderer[_]] = SwingVar[ListView[Any], javax.swing.ListCellRenderer[_]]("cellRenderer", _.getCellRenderer.nn, (l, c) => l.setCellRenderer(c.asInstanceOf))
  val DragEnabled: SwingVar.Aux[ListView[Any], Boolean] = SwingVar[ListView[Any], Boolean]("dragEnabled", _.getDragEnabled, _.setDragEnabled(_))
  val DropMode: SwingVar.Aux[ListView[Any], javax.swing.DropMode | Null] = SwingVar[ListView[Any], javax.swing.DropMode | Null]("dropMode", _.getDropMode, _.setDropMode(_))
  val FixedCellHeight: SwingVar.Aux[ListView[Any], Int] = SwingVar[ListView[Any], Int]("fixedCellHeight", _.getFixedCellHeight, _.setFixedCellHeight(_))
  val FixedCellWidth: SwingVar.Aux[ListView[Any], Int] = SwingVar[ListView[Any], Int]("fixedCellWidth", _.getFixedCellWidth, _.setFixedCellWidth(_))
  val LayoutOrientation: SwingVar.Aux[ListView[Any], Int] = SwingVar[ListView[Any], Int]("layoutOrientation", _.getLayoutOrientation, _.setLayoutOrientation(_))
  val Model: SwingVar.Aux[ListView[Any], javax.swing.ListModel[_]] = SwingVar[ListView[Any], javax.swing.ListModel[_]]("model", _.getModel.nn, (l, m) => l.setModel(m.asInstanceOf))
  val PrototypeCellValue: SwingVar.Aux[ListView[Any], Any] = SwingVar[ListView[Any], Any]("prototypeCellValue", _.getPrototypeCellValue, (l, p) => l.setPrototypeCellValue(p.asInstanceOf))
  val SelectedIndex: SwingVar.Aux[ListView[Any], Int] = SwingVar[ListView[Any], Int]("selectedIndex", _.getSelectedIndex, _.setSelectedIndex(_))
  val SelectedIndices: SwingVar.Aux[ListView[Any], Array[Int]] = SwingVar[ListView[Any], Array[Int]]("selectedIndices", _.getSelectedIndices.nn, _.setSelectedIndices(_))
  val SelectionBackground: SwingVar.Aux[ListView[Any], java.awt.Color | Null] = SwingVar[ListView[Any], java.awt.Color | Null]("selectionBackground", _.getSelectionBackground, _.setSelectionBackground(_))
  val SelectionForeground: SwingVar.Aux[ListView[Any], java.awt.Color | Null] = SwingVar[ListView[Any], java.awt.Color | Null]("selectionForeground", _.getSelectionForeground, _.setSelectionForeground(_))
  val SelectionMode: SwingVar.Aux[ListView[Any], Int] = SwingVar[ListView[Any], Int]("selectionMode", _.getSelectionMode, _.setSelectionMode(_))
  val SelectionModel: SwingVar.Aux[ListView[Any], javax.swing.ListSelectionModel] = SwingVar[ListView[Any], javax.swing.ListSelectionModel]("selectionModel", _.getSelectionModel.nn, _.setSelectionModel(_))
  val ValueIsAdjusting: SwingVar.Aux[ListView[Any], Boolean] = SwingVar[ListView[Any], Boolean]("valueIsAdjusting", _.getValueIsAdjusting, _.setValueIsAdjusting(_))
  val VisibleRowCount: SwingVar.Aux[ListView[Any], Int] = SwingVar[ListView[Any], Int]("visibleRowCount", _.getVisibleRowCount, _.setVisibleRowCount(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension [E](v: ListView[E]) {
      def UI: Var.Aux[javax.swing.plaf.ListUI, v.type] = ListView.UI.asInstanceOf[Var.Aux[javax.swing.plaf.ListUI, v.type]]
      def cellRenderer: Var.Aux[javax.swing.ListCellRenderer[_ >: E], v.type] = ListView.CellRenderer.asInstanceOf[Var.Aux[javax.swing.ListCellRenderer[_ >: E], v.type]]
      def dragEnabled: Var.Aux[Boolean, v.type] = ListView.DragEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def dropMode: Var.Aux[javax.swing.DropMode | Null, v.type] = ListView.DropMode.asInstanceOf[Var.Aux[javax.swing.DropMode | Null, v.type]]
      def fixedCellHeight: Var.Aux[Int, v.type] = ListView.FixedCellHeight.asInstanceOf[Var.Aux[Int, v.type]]
      def fixedCellWidth: Var.Aux[Int, v.type] = ListView.FixedCellWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def layoutOrientation: Var.Aux[Int, v.type] = ListView.LayoutOrientation.asInstanceOf[Var.Aux[Int, v.type]]
      def model: Var.Aux[javax.swing.ListModel[E], v.type] = ListView.Model.asInstanceOf[Var.Aux[javax.swing.ListModel[E], v.type]]
      def prototypeCellValue: Var.Aux[E | Null, v.type] = ListView.PrototypeCellValue.asInstanceOf[Var.Aux[E | Null, v.type]]
      def selectedIndex: Var.Aux[Int, v.type] = ListView.SelectedIndex.asInstanceOf[Var.Aux[Int, v.type]]
      def selectedIndices: Var.Aux[Array[Int], v.type] = ListView.SelectedIndices.asInstanceOf[Var.Aux[Array[Int], v.type]]
      def selectionBackground: Var.Aux[java.awt.Color | Null, v.type] = ListView.SelectionBackground.asInstanceOf[Var.Aux[java.awt.Color | Null, v.type]]
      def selectionForeground: Var.Aux[java.awt.Color | Null, v.type] = ListView.SelectionForeground.asInstanceOf[Var.Aux[java.awt.Color | Null, v.type]]
      def selectionMode: Var.Aux[Int, v.type] = ListView.SelectionMode.asInstanceOf[Var.Aux[Int, v.type]]
      def selectionModel: Var.Aux[javax.swing.ListSelectionModel, v.type] = ListView.SelectionModel.asInstanceOf[Var.Aux[javax.swing.ListSelectionModel, v.type]]
      def valueIsAdjusting: Var.Aux[Boolean, v.type] = ListView.ValueIsAdjusting.asInstanceOf[Var.Aux[Boolean, v.type]]
      def visibleRowCount: Var.Aux[Int, v.type] = ListView.VisibleRowCount.asInstanceOf[Var.Aux[Int, v.type]]

      

      def anchorSelectionIndex: Int = v.getAnchorSelectionIndex
      def dropLocation: javax.swing.JList.DropLocation | Null = v.getDropLocation
      def firstVisibleIndex: Int = v.getFirstVisibleIndex
      def lastVisibleIndex: Int = v.getLastVisibleIndex
      def leadSelectionIndex: Int = v.getLeadSelectionIndex
      def listSelectionListeners: Array[javax.swing.event.ListSelectionListener] = v.getListSelectionListeners.asInstanceOf[Array[javax.swing.event.ListSelectionListener]]
      def maxSelectionIndex: Int = v.getMaxSelectionIndex
      def minSelectionIndex: Int = v.getMinSelectionIndex
      def preferredScrollableViewportSize: java.awt.Dimension | Null = v.getPreferredScrollableViewportSize
      def scrollableTracksViewportHeight: Boolean = v.getScrollableTracksViewportHeight
      def scrollableTracksViewportWidth: Boolean = v.getScrollableTracksViewportWidth
      def selectedValue: Option[E] = { val r = v.getSelectedValue; if (r == null) None else Some(r) }
      def selectedValues: Seq[E] = v.getSelectedValuesList.nn.asScala.asInstanceOf
      def selectionEmpty: Boolean = v.isSelectionEmpty
      def unwrap: javax.swing.JList[_ <: E] = v
    }
  }

  def wrap[E](v: javax.swing.JList[_ <: E]) = v.asInstanceOf[ListView[E]]

  def init[E](v: ListView[E]): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    val lsl: ListSelectionListener = (evt) => sc.update{
      val vc = summon[VarContext]
      vc.externalPropertyUpdated(ops.selectedIndex(v), v.getSelectedIndex)
      vc.externalPropertyUpdated(ops.selectedIndices(v), v.getSelectedIndices.nn)
      vc.externalPropertyUpdated(ops.selectedIndices(v), v.getSelectedIndices.nn)
    }
    v.addListSelectionListener(lsl)
        
    
  }
  def uninitialized[E](): ListView[E] = {
    val res = javax.swing.JList[E]().asInstanceOf[ListView[E]]
    
    res
  }
  
  def apply[E](
    
    UI: Opt[Binding[javax.swing.plaf.ListUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    cellRenderer: Opt[Binding[javax.swing.ListCellRenderer[_ >: E]]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    dropMode: Opt[Binding[javax.swing.DropMode | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    fixedCellHeight: Opt[Binding[Int]] = UnsetParam,
    fixedCellWidth: Opt[Binding[Int]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    layoutOrientation: Opt[Binding[Int]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    model: Opt[Binding[javax.swing.ListModel[E]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    prototypeCellValue: Opt[Binding[E | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    selectedIndex: Opt[Binding[Int]] = UnsetParam,
    selectedIndices: Opt[Binding[Array[Int]]] = UnsetParam,
    selectionBackground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionForeground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionMode: Opt[Binding[Int]] = UnsetParam,
    selectionModel: Opt[Binding[javax.swing.ListSelectionModel]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    valueIsAdjusting: Opt[Binding[Boolean]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    visibleRowCount: Opt[Binding[Int]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[ListView[E]] = {
    val res = uninitialized[E]()
    ListView.init(res)
    ifSet(UI, ListView.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(cellRenderer, ListView.ops.cellRenderer(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(dragEnabled, ListView.ops.dragEnabled(res) := _)
    ifSet(dropMode, ListView.ops.dropMode(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(fixedCellHeight, ListView.ops.fixedCellHeight(res) := _)
    ifSet(fixedCellWidth, ListView.ops.fixedCellWidth(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(layoutOrientation, ListView.ops.layoutOrientation(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(model, ListView.ops.model(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(prototypeCellValue, ListView.ops.prototypeCellValue(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(selectedIndex, ListView.ops.selectedIndex(res) := _)
    ifSet(selectedIndices, ListView.ops.selectedIndices(res) := _)
    ifSet(selectionBackground, ListView.ops.selectionBackground(res) := _)
    ifSet(selectionForeground, ListView.ops.selectionForeground(res) := _)
    ifSet(selectionMode, ListView.ops.selectionMode(res) := _)
    ifSet(selectionModel, ListView.ops.selectionModel(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(valueIsAdjusting, ListView.ops.valueIsAdjusting(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    ifSet(visibleRowCount, ListView.ops.visibleRowCount(res) := _)
    res
  }
  
}