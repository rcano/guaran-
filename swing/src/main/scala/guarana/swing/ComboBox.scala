//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, TextComponent => _, TextField => _, _}
import java.awt.event._
import javax.swing._
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type ComboBox[+E] <: Component = javax.swing.JComboBox[_ <: E] & Component
object ComboBox extends VarsMap {
  val UI: SwingVar.Aux[ComboBox[Any], javax.swing.plaf.ComboBoxUI] = SwingVar[ComboBox[Any], javax.swing.plaf.ComboBoxUI]("UI", _.getUI.nn, _.setUI(_))
  val Action: SwingVar.Aux[ComboBox[Any], javax.swing.Action | Null] = SwingVar[ComboBox[Any], javax.swing.Action | Null]("action", _.getAction, _.setAction(_))
  val ActionCommand: SwingVar.Aux[ComboBox[Any], java.lang.String | Null] = SwingVar[ComboBox[Any], java.lang.String | Null]("actionCommand", _.getActionCommand, _.setActionCommand(_))
  val Editable: SwingVar.Aux[ComboBox[Any], Boolean] = SwingVar[ComboBox[Any], Boolean]("editable", _.isEditable, _.setEditable(_))
  val Editor: SwingVar.Aux[ComboBox[Any], javax.swing.ComboBoxEditor | Null] = SwingVar[ComboBox[Any], javax.swing.ComboBoxEditor | Null]("editor", _.getEditor, _.setEditor(_))
  val Items: SwingVar.Aux[ComboBox[Any], Seq[Any]] = SwingVar[ComboBox[Any], Seq[Any]]("items", {c => val m = c.getModel.nn; (0 until m.getSize).map(m.getElementAt(_).asInstanceOf)}, (c, i) => c.setModel(DefaultComboBoxModel(i.toArray.asInstanceOf[Array[AnyRef | UncheckedNull]]).asInstanceOf))
  val KeySelectionManager: SwingVar.Aux[ComboBox[Any], javax.swing.JComboBox.KeySelectionManager | Null] = SwingVar[ComboBox[Any], javax.swing.JComboBox.KeySelectionManager | Null]("keySelectionManager", _.getKeySelectionManager, _.setKeySelectionManager(_))
  val LightWeightPopupEnabled: SwingVar.Aux[ComboBox[Any], Boolean] = SwingVar[ComboBox[Any], Boolean]("lightWeightPopupEnabled", _.isLightWeightPopupEnabled, _.setLightWeightPopupEnabled(_))
  val MaximumRowCount: SwingVar.Aux[ComboBox[Any], Int] = SwingVar[ComboBox[Any], Int]("maximumRowCount", _.getMaximumRowCount, _.setMaximumRowCount(_))
  val Model: SwingVar.Aux[ComboBox[Any], javax.swing.ComboBoxModel[?]] = SwingVar[ComboBox[Any], javax.swing.ComboBoxModel[?]]("model", _.getModel.nn, (c, m) => c.setModel(m.asInstanceOf))
  val PopupVisible: SwingVar.Aux[ComboBox[Any], Boolean] = SwingVar[ComboBox[Any], Boolean]("popupVisible", _.isPopupVisible, _.setPopupVisible(_))
  val PrototypeDisplayValue: SwingVar.Aux[ComboBox[Any], Any] = SwingVar[ComboBox[Any], Any]("prototypeDisplayValue", _.getPrototypeDisplayValue.asInstanceOf, (c, v) => c.setPrototypeDisplayValue(v.asInstanceOf))
  val Renderer: SwingVar.Aux[ComboBox[Any], javax.swing.ListCellRenderer[?]] = SwingVar[ComboBox[Any], javax.swing.ListCellRenderer[?]]("renderer", _.getRenderer.nn, (c, r) => c.setRenderer(r.asInstanceOf))
  val SelectedIndex: SwingVar.Aux[ComboBox[Any], Int] = SwingVar[ComboBox[Any], Int]("selectedIndex", _.getSelectedIndex, _.setSelectedIndex(_))
  val SelectedItem: SwingVar.Aux[ComboBox[Any], Option[?]] = SwingVar[ComboBox[Any], Option[?]]("selectedItem", {c => val v = c.getSelectedItem; if (v != null) Some(v) else None}, _.setSelectedItem(_))

  

  extension ops on [E](v: ComboBox[E]) {
    def UI: Var.Aux[javax.swing.plaf.ComboBoxUI, v.type] = ComboBox.UI.asInstanceOf[Var.Aux[javax.swing.plaf.ComboBoxUI, v.type]]
    def action: Var.Aux[javax.swing.Action | Null, v.type] = ComboBox.Action.asInstanceOf[Var.Aux[javax.swing.Action | Null, v.type]]
    def actionCommand: Var.Aux[java.lang.String | Null, v.type] = ComboBox.ActionCommand.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def editable: Var.Aux[Boolean, v.type] = ComboBox.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def editor: Var.Aux[javax.swing.ComboBoxEditor | Null, v.type] = ComboBox.Editor.asInstanceOf[Var.Aux[javax.swing.ComboBoxEditor | Null, v.type]]
    def items: Var.Aux[Seq[E], v.type] = ComboBox.Items.asInstanceOf[Var.Aux[Seq[E], v.type]]
    def keySelectionManager: Var.Aux[javax.swing.JComboBox.KeySelectionManager | Null, v.type] = ComboBox.KeySelectionManager.asInstanceOf[Var.Aux[javax.swing.JComboBox.KeySelectionManager | Null, v.type]]
    def lightWeightPopupEnabled: Var.Aux[Boolean, v.type] = ComboBox.LightWeightPopupEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maximumRowCount: Var.Aux[Int, v.type] = ComboBox.MaximumRowCount.asInstanceOf[Var.Aux[Int, v.type]]
    def model: Var.Aux[javax.swing.ComboBoxModel[E], v.type] = ComboBox.Model.asInstanceOf[Var.Aux[javax.swing.ComboBoxModel[E], v.type]]
    def popupVisible: Var.Aux[Boolean, v.type] = ComboBox.PopupVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def prototypeDisplayValue: Var.Aux[E, v.type] = ComboBox.PrototypeDisplayValue.asInstanceOf[Var.Aux[E, v.type]]
    def renderer: Var.Aux[javax.swing.ListCellRenderer[_ >: E], v.type] = ComboBox.Renderer.asInstanceOf[Var.Aux[javax.swing.ListCellRenderer[_ >: E], v.type]]
    def selectedIndex: Var.Aux[Int, v.type] = ComboBox.SelectedIndex.asInstanceOf[Var.Aux[Int, v.type]]
    def selectedItem: Var.Aux[Option[E], v.type] = ComboBox.SelectedItem.asInstanceOf[Var.Aux[Option[E], v.type]]

    

    def actionListeners: Array[java.awt.event.ActionListener] = v.getActionListeners.asInstanceOf
    def itemCount: Int = v.getItemCount
    def itemListeners: Array[java.awt.event.ItemListener] = v.getItemListeners.asInstanceOf
    def popupMenuListeners: Array[javax.swing.event.PopupMenuListener] = v.getPopupMenuListeners.asInstanceOf
    def selectedObjects: Seq[E] = v.getSelectedObjects.nn.toIndexedSeq.asInstanceOf
    def unwrap: javax.swing.JComboBox[_ <: E] = v
  }

  def wrap[E](v: javax.swing.JComboBox[_ <: E]) = v.asInstanceOf[ComboBox[E]]

  def init[E](v: ComboBox[E]): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    val il: ItemListener = evt => sc.update {
      val vc = summon[VarContext]
      vc.swingPropertyUpdated(ops.extension_selectedIndex(v), v.getSelectedIndex)
      val si = v.getSelectedItem.asInstanceOf[E | Null]
      vc.swingPropertyUpdated(ops.extension_selectedItem(v), if (si == null) None else Some(si))
    }
    v.addItemListener(il)
    
  }
  def uninitialized[E](): ComboBox[E] = {
    val res = javax.swing.JComboBox[E]().asInstanceOf[ComboBox[E]]
    
    res
  }
  
  def apply[E](
    
    UI: Opt[Binding[javax.swing.plaf.ComboBoxUI]] = UnsetParam,
    action: Opt[Binding[javax.swing.Action | Null]] = UnsetParam,
    actionCommand: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    editor: Opt[Binding[javax.swing.ComboBoxEditor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    items: Opt[Binding[Seq[E]]] = UnsetParam,
    keySelectionManager: Opt[Binding[javax.swing.JComboBox.KeySelectionManager | Null]] = UnsetParam,
    lightWeightPopupEnabled: Opt[Binding[Boolean]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    maximumRowCount: Opt[Binding[Int]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    model: Opt[Binding[javax.swing.ComboBoxModel[E]]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    popupVisible: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    prototypeDisplayValue: Opt[Binding[E]] = UnsetParam,
    renderer: Opt[Binding[javax.swing.ListCellRenderer[_ >: E]]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    selectedIndex: Opt[Binding[Int]] = UnsetParam,
    selectedItem: Opt[Binding[Option[E]]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[ComboBox[E]] = {
    val res = uninitialized[E]()
    ComboBox.init(res)
    ifSet(UI, ComboBox.ops.extension_UI(res) := _)
    ifSet(action, ComboBox.ops.extension_action(res) := _)
    ifSet(actionCommand, ComboBox.ops.extension_actionCommand(res) := _)
    ifSet(actionMap, Component.ops.extension_actionMap(res) := _)
    ifSet(alignmentX, Component.ops.extension_alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.extension_alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.extension_autoscrolls(res) := _)
    ifSet(background, Node.ops.extension_background(res) := _)
    ifSet(border, Component.ops.extension_border(res) := _)
    ifSet(bounds, Node.ops.extension_bounds(res) := _)
    ifSet(componentOrientation, Node.ops.extension_componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.extension_componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.extension_cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.extension_debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.extension_doubleBuffered(res) := _)
    ifSet(editable, ComboBox.ops.extension_editable(res) := _)
    ifSet(editor, ComboBox.ops.extension_editor(res) := _)
    ifSet(enabled, Node.ops.extension_enabled(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.extension_inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.extension_inputVerifier(res) := _)
    ifSet(items, ComboBox.ops.extension_items(res) := _)
    ifSet(keySelectionManager, ComboBox.ops.extension_keySelectionManager(res) := _)
    ifSet(lightWeightPopupEnabled, ComboBox.ops.extension_lightWeightPopupEnabled(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(maximumRowCount, ComboBox.ops.extension_maximumRowCount(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(model, ComboBox.ops.extension_model(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(opaque, Component.ops.extension_opaque(res) := _)
    ifSet(popupVisible, ComboBox.ops.extension_popupVisible(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(prototypeDisplayValue, ComboBox.ops.extension_prototypeDisplayValue(res) := _)
    ifSet(renderer, ComboBox.ops.extension_renderer(res) := _)
    ifSet(requestFocusEnabled, Component.ops.extension_requestFocusEnabled(res) := _)
    ifSet(selectedIndex, ComboBox.ops.extension_selectedIndex(res) := _)
    ifSet(selectedItem, ComboBox.ops.extension_selectedItem(res) := _)
    ifSet(toolTipText, Component.ops.extension_toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.extension_transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.extension_verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  
}