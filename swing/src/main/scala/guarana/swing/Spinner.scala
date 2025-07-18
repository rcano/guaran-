//AUTOGENERATED FILE, DO NOT MODIFY

package guarana
package swing

import language.implicitConversions
import java.awt.{Component as _, Menu as _, MenuBar as _, MenuItem as _, TextComponent as _, TextField as _, PopupMenu as _}
import javax.swing.Action as _
import javax.swing.event.*
import guarana.util.*

opaque type Spinner[+E] <: Component  = javax.swing.JSpinner & Component
object Spinner extends VarsMap {
  val UI: SwingVar.Aux[Spinner[Any], javax.swing.plaf.SpinnerUI] = SwingVar[Spinner[Any], javax.swing.plaf.SpinnerUI]("UI", _.getUI.nn, _.setUI(_))
  val Editor: SwingVar.Aux[Spinner[Any], javax.swing.JComponent | Null] = SwingVar[Spinner[Any], javax.swing.JComponent | Null]("editor", _.getEditor, _.setEditor(_))
  val Model: SwingVar.Aux[Spinner[Any], javax.swing.SpinnerModel] = SwingVar[Spinner[Any], javax.swing.SpinnerModel]("model", _.getModel.nn, _.setModel(_))
  val Value: SwingVar.Aux[Spinner[Any], Any] = SwingVar[Spinner[Any], Any]("value", _.getValue, _.setValue(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension [E](v: Spinner[E]) {
      def UI: Var.Aux[javax.swing.plaf.SpinnerUI, v.type] = Spinner.UI.asInstanceOf[Var.Aux[javax.swing.plaf.SpinnerUI, v.type]]
      def editor: Var.Aux[javax.swing.JComponent | Null, v.type] = Spinner.Editor.asInstanceOf[Var.Aux[javax.swing.JComponent | Null, v.type]]
      def model: Var.Aux[javax.swing.SpinnerModel, v.type] = Spinner.Model.asInstanceOf[Var.Aux[javax.swing.SpinnerModel, v.type]]
      def value: Var.Aux[E, v.type] = Spinner.Value.asInstanceOf[Var.Aux[E, v.type]]

      

      
      def unwrap: javax.swing.JSpinner = v
    }
  }

  def wrap[E](v: javax.swing.JSpinner) = v.asInstanceOf[Spinner[E]]

  def init[E](v: Spinner[E]): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    val cl: ChangeListener = evt => sc.update(summon[VarContext].externalPropertyUpdated(ops.value(v), Some(v.getValue.asInstanceOf[E])))
    v.addChangeListener(cl)
    
  }
  def uninitialized[E](): Spinner[E] = {
    val res = javax.swing.JSpinner().asInstanceOf[Spinner[E]]
    
    res
  }
  
  def apply[E](
    
    UI: Opt[Binding[javax.swing.plaf.SpinnerUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    editor: Opt[Binding[javax.swing.JComponent | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    model: Opt[Binding[javax.swing.SpinnerModel]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    value: Opt[Binding[E]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Spinner[E]] = {
    val res = uninitialized[E]()
    Spinner.init(res)
    ifSet(UI, Spinner.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(editor, Spinner.ops.editor(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(model, Spinner.ops.model(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(value, Spinner.ops.value(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}