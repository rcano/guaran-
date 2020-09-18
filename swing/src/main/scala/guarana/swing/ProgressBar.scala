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

opaque type ProgressBar <: Component = javax.swing.JProgressBar & Component
object ProgressBar extends VarsMap {
  val UI: SwingVar.Aux[ProgressBar, javax.swing.plaf.ProgressBarUI] = SwingVar[ProgressBar, javax.swing.plaf.ProgressBarUI]("UI", _.getUI.nn, _.setUI(_))
  val BorderPainted: SwingVar.Aux[ProgressBar, Boolean] = SwingVar[ProgressBar, Boolean]("borderPainted", _.isBorderPainted, _.setBorderPainted(_))
  val Indeterminate: SwingVar.Aux[ProgressBar, Boolean] = SwingVar[ProgressBar, Boolean]("indeterminate", _.isIndeterminate, _.setIndeterminate(_))
  val Max: SwingVar.Aux[ProgressBar, Int] = SwingVar[ProgressBar, Int]("max", _.getMaximum, _.setMaximum(_))
  val Min: SwingVar.Aux[ProgressBar, Int] = SwingVar[ProgressBar, Int]("min", _.getMinimum, _.setMinimum(_))
  val Model: SwingVar.Aux[ProgressBar, javax.swing.BoundedRangeModel | Null] = SwingVar[ProgressBar, javax.swing.BoundedRangeModel | Null]("model", _.getModel, _.setModel(_))
  val Orientation: SwingVar.Aux[ProgressBar, Int] = SwingVar[ProgressBar, Int]("orientation", _.getOrientation, _.setOrientation(_))
  val String: SwingVar.Aux[ProgressBar, java.lang.String | Null] = SwingVar[ProgressBar, java.lang.String | Null]("string", _.getString, _.setString(_))
  val StringPainted: SwingVar.Aux[ProgressBar, Boolean] = SwingVar[ProgressBar, Boolean]("stringPainted", _.isStringPainted, _.setStringPainted(_))
  val Value: SwingVar.Aux[ProgressBar, Int] = SwingVar[ProgressBar, Int]("value", _.getValue, _.setValue(_))

  

  extension ops on (v: ProgressBar) {
    def UI: Var.Aux[javax.swing.plaf.ProgressBarUI, v.type] = ProgressBar.UI.asInstanceOf[Var.Aux[javax.swing.plaf.ProgressBarUI, v.type]]
    def borderPainted: Var.Aux[Boolean, v.type] = ProgressBar.BorderPainted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def indeterminate: Var.Aux[Boolean, v.type] = ProgressBar.Indeterminate.asInstanceOf[Var.Aux[Boolean, v.type]]
    def max: Var.Aux[Int, v.type] = ProgressBar.Max.asInstanceOf[Var.Aux[Int, v.type]]
    def min: Var.Aux[Int, v.type] = ProgressBar.Min.asInstanceOf[Var.Aux[Int, v.type]]
    def model: Var.Aux[javax.swing.BoundedRangeModel | Null, v.type] = ProgressBar.Model.asInstanceOf[Var.Aux[javax.swing.BoundedRangeModel | Null, v.type]]
    def orientation: Var.Aux[Int, v.type] = ProgressBar.Orientation.asInstanceOf[Var.Aux[Int, v.type]]
    def string: Var.Aux[java.lang.String | Null, v.type] = ProgressBar.String.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def stringPainted: Var.Aux[Boolean, v.type] = ProgressBar.StringPainted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def value: Var.Aux[Int, v.type] = ProgressBar.Value.asInstanceOf[Var.Aux[Int, v.type]]

    

    def changeListeners = v.getChangeListeners
    def percentComplete = v.getPercentComplete
    def unwrap: javax.swing.JProgressBar = v
  }

  def wrap(v: javax.swing.JProgressBar) = v.asInstanceOf[ProgressBar]

  def init(v: ProgressBar): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    val l: ChangeListener = (e: ChangeEvent | UncheckedNull) => summon[Scenegraph].update(summon[VarContext].swingPropertyUpdated(ops.extension_value(v), v.getValue))
    v.addChangeListener(l)
    
  }
  def uninitialized(): ProgressBar = {
    val res = javax.swing.JProgressBar().asInstanceOf[ProgressBar]
    
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.ProgressBarUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    borderPainted: Opt[Binding[Boolean]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    indeterminate: Opt[Binding[Boolean]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    max: Opt[Binding[Int]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    min: Opt[Binding[Int]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    model: Opt[Binding[javax.swing.BoundedRangeModel | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    orientation: Opt[Binding[Int]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    string: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    stringPainted: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    value: Opt[Binding[Int]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[ProgressBar] = {
    val res = uninitialized()
    ProgressBar.init(res)
    ifSet(UI, ProgressBar.ops.extension_UI(res) := _)
    ifSet(actionMap, Component.ops.extension_actionMap(res) := _)
    ifSet(alignmentX, Component.ops.extension_alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.extension_alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.extension_autoscrolls(res) := _)
    ifSet(background, Node.ops.extension_background(res) := _)
    ifSet(border, Component.ops.extension_border(res) := _)
    ifSet(borderPainted, ProgressBar.ops.extension_borderPainted(res) := _)
    ifSet(bounds, Node.ops.extension_bounds(res) := _)
    ifSet(componentOrientation, Node.ops.extension_componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.extension_componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.extension_cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.extension_debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.extension_doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.extension_enabled(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(indeterminate, ProgressBar.ops.extension_indeterminate(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.extension_inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.extension_inputVerifier(res) := _)
    ifSet(max, ProgressBar.ops.extension_max(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(min, ProgressBar.ops.extension_min(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(model, ProgressBar.ops.extension_model(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(name, Node.ops.extension_name(res) := _)
    ifSet(opaque, Component.ops.extension_opaque(res) := _)
    ifSet(orientation, ProgressBar.ops.extension_orientation(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.extension_requestFocusEnabled(res) := _)
    ifSet(string, ProgressBar.ops.extension_string(res) := _)
    ifSet(stringPainted, ProgressBar.ops.extension_stringPainted(res) := _)
    ifSet(toolTipText, Component.ops.extension_toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.extension_transferHandler(res) := _)
    ifSet(value, ProgressBar.ops.extension_value(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.extension_verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  
}