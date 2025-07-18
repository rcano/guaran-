//AUTOGENERATED FILE, DO NOT MODIFY

package guarana
package swing

import language.implicitConversions
import java.awt.{Component as _, Menu as _, MenuBar as _, MenuItem as _, TextComponent as _, TextField as _, PopupMenu as _}
import javax.swing.Action as _
import javax.swing.event.*
import guarana.util.*

opaque type TreeView[+E] <: Component  = javax.swing.JTree & Component
object TreeView extends VarsMap {
  val UI: SwingVar.Aux[TreeView[Any], javax.swing.plaf.TreeUI | Null] = SwingVar[TreeView[Any], javax.swing.plaf.TreeUI | Null]("UI", _.getUI, _.setUI(_))
  val AnchorSelectionPath: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreePath | Null] = SwingVar[TreeView[Any], javax.swing.tree.TreePath | Null]("anchorSelectionPath", _.getAnchorSelectionPath, _.setAnchorSelectionPath(_))
  val CellEditor: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreeCellEditor | Null] = SwingVar[TreeView[Any], javax.swing.tree.TreeCellEditor | Null]("cellEditor", _.getCellEditor, _.setCellEditor(_))
  val CellRenderer: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreeCellRenderer] = SwingVar[TreeView[Any], javax.swing.tree.TreeCellRenderer]("cellRenderer", _.getCellRenderer.nn, _.setCellRenderer(_))
  val DragEnabled: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("dragEnabled", _.getDragEnabled, _.setDragEnabled(_))
  val DropMode: SwingVar.Aux[TreeView[Any], javax.swing.DropMode | Null] = SwingVar[TreeView[Any], javax.swing.DropMode | Null]("dropMode", _.getDropMode, _.setDropMode(_))
  val Editable: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("editable", _.isEditable, _.setEditable(_))
  val ExpandsSelectedPaths: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("expandsSelectedPaths", _.getExpandsSelectedPaths, _.setExpandsSelectedPaths(_))
  val InvokesStopCellEditing: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("invokesStopCellEditing", _.getInvokesStopCellEditing, _.setInvokesStopCellEditing(_))
  val LargeModel: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("largeModel", _.isLargeModel, _.setLargeModel(_))
  val LeadSelectionPath: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreePath | Null] = SwingVar[TreeView[Any], javax.swing.tree.TreePath | Null]("leadSelectionPath", _.getLeadSelectionPath, _.setLeadSelectionPath(_))
  val Model: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreeModel] = SwingVar[TreeView[Any], javax.swing.tree.TreeModel]("model", _.getModel.nn, _.setModel(_))
  val RootVisible: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("rootVisible", _.isRootVisible, _.setRootVisible(_))
  val RowHeight: SwingVar.Aux[TreeView[Any], Int] = SwingVar[TreeView[Any], Int]("rowHeight", _.getRowHeight, _.setRowHeight(_))
  val ScrollsOnExpand: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("scrollsOnExpand", _.getScrollsOnExpand, _.setScrollsOnExpand(_))
  val SelectionModel: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreeSelectionModel] = SwingVar[TreeView[Any], javax.swing.tree.TreeSelectionModel]("selectionModel", _.getSelectionModel.nn, _.setSelectionModel(_))
  val SelectionPath: SwingVar.Aux[TreeView[Any], javax.swing.tree.TreePath | Null] = SwingVar[TreeView[Any], javax.swing.tree.TreePath | Null]("selectionPath", _.getSelectionPath, _.setSelectionPath(_))
  val SelectionPaths: SwingVar.Aux[TreeView[Any], Array[javax.swing.tree.TreePath | Null]] = SwingVar[TreeView[Any], Array[javax.swing.tree.TreePath | Null]]("selectionPaths", _.getSelectionPaths.nn, _.setSelectionPaths(_))
  val SelectionRows: SwingVar.Aux[TreeView[Any], Array[Int]] = SwingVar[TreeView[Any], Array[Int]]("selectionRows", _.getSelectionRows.nn, _.setSelectionRows(_))
  val ShowsRootHandles: SwingVar.Aux[TreeView[Any], Boolean] = SwingVar[TreeView[Any], Boolean]("showsRootHandles", _.getShowsRootHandles, _.setShowsRootHandles(_))
  val ToggleClickCount: SwingVar.Aux[TreeView[Any], Int] = SwingVar[TreeView[Any], Int]("toggleClickCount", _.getToggleClickCount, _.setToggleClickCount(_))
  val VisibleRowCount: SwingVar.Aux[TreeView[Any], Int] = SwingVar[TreeView[Any], Int]("visibleRowCount", _.getVisibleRowCount, _.setVisibleRowCount(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension [E](v: TreeView[E]) {
      def UI: Var.Aux[javax.swing.plaf.TreeUI | Null, v.type] = TreeView.UI.asInstanceOf[Var.Aux[javax.swing.plaf.TreeUI | Null, v.type]]
      def anchorSelectionPath: Var.Aux[javax.swing.tree.TreePath | Null, v.type] = TreeView.AnchorSelectionPath.asInstanceOf[Var.Aux[javax.swing.tree.TreePath | Null, v.type]]
      def cellEditor: Var.Aux[javax.swing.tree.TreeCellEditor | Null, v.type] = TreeView.CellEditor.asInstanceOf[Var.Aux[javax.swing.tree.TreeCellEditor | Null, v.type]]
      def cellRenderer: Var.Aux[javax.swing.tree.TreeCellRenderer, v.type] = TreeView.CellRenderer.asInstanceOf[Var.Aux[javax.swing.tree.TreeCellRenderer, v.type]]
      def dragEnabled: Var.Aux[Boolean, v.type] = TreeView.DragEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def dropMode: Var.Aux[javax.swing.DropMode | Null, v.type] = TreeView.DropMode.asInstanceOf[Var.Aux[javax.swing.DropMode | Null, v.type]]
      def editable: Var.Aux[Boolean, v.type] = TreeView.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def expandsSelectedPaths: Var.Aux[Boolean, v.type] = TreeView.ExpandsSelectedPaths.asInstanceOf[Var.Aux[Boolean, v.type]]
      def invokesStopCellEditing: Var.Aux[Boolean, v.type] = TreeView.InvokesStopCellEditing.asInstanceOf[Var.Aux[Boolean, v.type]]
      def largeModel: Var.Aux[Boolean, v.type] = TreeView.LargeModel.asInstanceOf[Var.Aux[Boolean, v.type]]
      def leadSelectionPath: Var.Aux[javax.swing.tree.TreePath | Null, v.type] = TreeView.LeadSelectionPath.asInstanceOf[Var.Aux[javax.swing.tree.TreePath | Null, v.type]]
      def model: Var.Aux[javax.swing.tree.TreeModel, v.type] = TreeView.Model.asInstanceOf[Var.Aux[javax.swing.tree.TreeModel, v.type]]
      def rootVisible: Var.Aux[Boolean, v.type] = TreeView.RootVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
      def rowHeight: Var.Aux[Int, v.type] = TreeView.RowHeight.asInstanceOf[Var.Aux[Int, v.type]]
      def scrollsOnExpand: Var.Aux[Boolean, v.type] = TreeView.ScrollsOnExpand.asInstanceOf[Var.Aux[Boolean, v.type]]
      def selectionModel: Var.Aux[javax.swing.tree.TreeSelectionModel, v.type] = TreeView.SelectionModel.asInstanceOf[Var.Aux[javax.swing.tree.TreeSelectionModel, v.type]]
      def selectionPath: Var.Aux[javax.swing.tree.TreePath | Null, v.type] = TreeView.SelectionPath.asInstanceOf[Var.Aux[javax.swing.tree.TreePath | Null, v.type]]
      def selectionPaths: Var.Aux[Array[javax.swing.tree.TreePath | Null], v.type] = TreeView.SelectionPaths.asInstanceOf[Var.Aux[Array[javax.swing.tree.TreePath | Null], v.type]]
      def selectionRows: Var.Aux[Array[Int], v.type] = TreeView.SelectionRows.asInstanceOf[Var.Aux[Array[Int], v.type]]
      def showsRootHandles: Var.Aux[Boolean, v.type] = TreeView.ShowsRootHandles.asInstanceOf[Var.Aux[Boolean, v.type]]
      def toggleClickCount: Var.Aux[Int, v.type] = TreeView.ToggleClickCount.asInstanceOf[Var.Aux[Int, v.type]]
      def visibleRowCount: Var.Aux[Int, v.type] = TreeView.VisibleRowCount.asInstanceOf[Var.Aux[Int, v.type]]

      

      def dropLocation: javax.swing.JTree.DropLocation | Null = v.getDropLocation
      def editing: Boolean = v.isEditing
      def editingPath: javax.swing.tree.TreePath | Null = v.getEditingPath
      def fixedRowHeight: Boolean = v.isFixedRowHeight
      def lastSelectedPathComponent: java.lang.Object | Null = v.getLastSelectedPathComponent
      def leadSelectionRow: Int = v.getLeadSelectionRow
      def maxSelectionRow: Int = v.getMaxSelectionRow
      def minSelectionRow: Int = v.getMinSelectionRow
      def preferredScrollableViewportSize: java.awt.Dimension | Null = v.getPreferredScrollableViewportSize
      def rowCount: Int = v.getRowCount
      def scrollableTracksViewportHeight: Boolean = v.getScrollableTracksViewportHeight
      def scrollableTracksViewportWidth: Boolean = v.getScrollableTracksViewportWidth
      def selectionCount: Int = v.getSelectionCount
      def selectionEmpty: Boolean = v.isSelectionEmpty
      def treeExpansionListeners: Array[javax.swing.event.TreeExpansionListener] = v.getTreeExpansionListeners.nnn
      def treeSelectionListeners: Array[javax.swing.event.TreeSelectionListener] = v.getTreeSelectionListeners.nnn
      def treeWillExpandListeners: Array[javax.swing.event.TreeWillExpandListener] = v.getTreeWillExpandListeners.nnn
      def unwrap: javax.swing.JTree = v
    }
  }

  def wrap[E](v: javax.swing.JTree) = v.asInstanceOf[TreeView[E]]

  def init[E](v: TreeView[E]): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    val tsl: TreeSelectionListener = evt => sc.update {
      val vc = summon[VarContext]
      vc.externalPropertyUpdated(ops.leadSelectionPath(v), v.getLeadSelectionPath.toOption)
      vc.externalPropertyUpdated(ops.selectionPath(v), v.getSelectionPath.toOption)
      vc.externalPropertyUpdated(ops.selectionPaths(v), v.getSelectionPaths.toOption)
      vc.externalPropertyUpdated(ops.selectionRows(v), v.getSelectionRows.toOption)
    }
    v.addTreeSelectionListener(tsl)
    
  }
  def uninitialized[E](): TreeView[E] = {
    val res = javax.swing.JTree().asInstanceOf[TreeView[E]]
    
    res
  }
  
  def apply[E](
    
    UI: Opt[Binding[javax.swing.plaf.TreeUI | Null]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    anchorSelectionPath: Opt[Binding[javax.swing.tree.TreePath | Null]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    cellEditor: Opt[Binding[javax.swing.tree.TreeCellEditor | Null]] = UnsetParam,
    cellRenderer: Opt[Binding[javax.swing.tree.TreeCellRenderer]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    dropMode: Opt[Binding[javax.swing.DropMode | Null]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    expandsSelectedPaths: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    invokesStopCellEditing: Opt[Binding[Boolean]] = UnsetParam,
    largeModel: Opt[Binding[Boolean]] = UnsetParam,
    leadSelectionPath: Opt[Binding[javax.swing.tree.TreePath | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    model: Opt[Binding[javax.swing.tree.TreeModel]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rootVisible: Opt[Binding[Boolean]] = UnsetParam,
    rowHeight: Opt[Binding[Int]] = UnsetParam,
    scrollsOnExpand: Opt[Binding[Boolean]] = UnsetParam,
    selectionModel: Opt[Binding[javax.swing.tree.TreeSelectionModel]] = UnsetParam,
    selectionPath: Opt[Binding[javax.swing.tree.TreePath | Null]] = UnsetParam,
    selectionPaths: Opt[Binding[Array[javax.swing.tree.TreePath | Null]]] = UnsetParam,
    selectionRows: Opt[Binding[Array[Int]]] = UnsetParam,
    showsRootHandles: Opt[Binding[Boolean]] = UnsetParam,
    toggleClickCount: Opt[Binding[Int]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    visibleRowCount: Opt[Binding[Int]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[TreeView[E]] = {
    val res = uninitialized[E]()
    TreeView.init(res)
    ifSet(UI, TreeView.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(anchorSelectionPath, TreeView.ops.anchorSelectionPath(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(cellEditor, TreeView.ops.cellEditor(res) := _)
    ifSet(cellRenderer, TreeView.ops.cellRenderer(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(dragEnabled, TreeView.ops.dragEnabled(res) := _)
    ifSet(dropMode, TreeView.ops.dropMode(res) := _)
    ifSet(editable, TreeView.ops.editable(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(expandsSelectedPaths, TreeView.ops.expandsSelectedPaths(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(invokesStopCellEditing, TreeView.ops.invokesStopCellEditing(res) := _)
    ifSet(largeModel, TreeView.ops.largeModel(res) := _)
    ifSet(leadSelectionPath, TreeView.ops.leadSelectionPath(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(model, TreeView.ops.model(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rootVisible, TreeView.ops.rootVisible(res) := _)
    ifSet(rowHeight, TreeView.ops.rowHeight(res) := _)
    ifSet(scrollsOnExpand, TreeView.ops.scrollsOnExpand(res) := _)
    ifSet(selectionModel, TreeView.ops.selectionModel(res) := _)
    ifSet(selectionPath, TreeView.ops.selectionPath(res) := _)
    ifSet(selectionPaths, TreeView.ops.selectionPaths(res) := _)
    ifSet(selectionRows, TreeView.ops.selectionRows(res) := _)
    ifSet(showsRootHandles, TreeView.ops.showsRootHandles(res) := _)
    ifSet(toggleClickCount, TreeView.ops.toggleClickCount(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    ifSet(visibleRowCount, TreeView.ops.visibleRowCount(res) := _)
    res
  }
  
}