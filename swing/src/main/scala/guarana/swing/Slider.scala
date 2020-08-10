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

opaque type Slider <: Component = javax.swing.JSlider & Component
object Slider extends VarsMap {
  val UI: SwingVar.Aux[Slider, javax.swing.plaf.SliderUI] = SwingVar[Slider, javax.swing.plaf.SliderUI]("UI", _.getUI.nn, _.setUI(_))
  val Extent: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("extent", _.getExtent, _.setExtent(_))
  val Inverted: SwingVar.Aux[Slider, Boolean] = SwingVar[Slider, Boolean]("inverted", _.getInverted, _.setInverted(_))
  val LabelTable: SwingVar.Aux[Slider, java.util.Dictionary[_, _] | Null] = SwingVar[Slider, java.util.Dictionary[_, _] | Null]("labelTable", _.getLabelTable, _.setLabelTable(_))
  val MajorTickSpacing: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("majorTickSpacing", _.getMajorTickSpacing, _.setMajorTickSpacing(_))
  val Max: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("max", _.getMaximum, _.setMaximum(_))
  val Min: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("min", _.getMinimum, _.setMinimum(_))
  val MinorTickSpacing: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("minorTickSpacing", _.getMinorTickSpacing, _.setMinorTickSpacing(_))
  val Model: SwingVar.Aux[Slider, javax.swing.BoundedRangeModel | Null] = SwingVar[Slider, javax.swing.BoundedRangeModel | Null]("model", _.getModel, _.setModel(_))
  val Orientation: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("orientation", _.getOrientation, _.setOrientation(_))
  val PaintLabels: SwingVar.Aux[Slider, Boolean] = SwingVar[Slider, Boolean]("paintLabels", _.getPaintLabels, _.setPaintLabels(_))
  val PaintTicks: SwingVar.Aux[Slider, Boolean] = SwingVar[Slider, Boolean]("paintTicks", _.getPaintTicks, _.setPaintTicks(_))
  val PaintTrack: SwingVar.Aux[Slider, Boolean] = SwingVar[Slider, Boolean]("paintTrack", _.getPaintTrack, _.setPaintTrack(_))
  val SnapToTicks: SwingVar.Aux[Slider, Boolean] = SwingVar[Slider, Boolean]("snapToTicks", _.getSnapToTicks, _.setSnapToTicks(_))
  val Value: SwingVar.Aux[Slider, Int] = SwingVar[Slider, Int]("value", _.getValue, _.setValue(_))
  val ValueIsAdjusting: SwingVar.Aux[Slider, Boolean] = SwingVar[Slider, Boolean]("valueIsAdjusting", _.getValueIsAdjusting, _.setValueIsAdjusting(_))

  

  extension ops on (v: Slider) {
    def UI: Var.Aux[javax.swing.plaf.SliderUI, v.type] = Slider.UI.asInstanceOf[Var.Aux[javax.swing.plaf.SliderUI, v.type]]
    def extent: Var.Aux[Int, v.type] = Slider.Extent.asInstanceOf[Var.Aux[Int, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = Slider.Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def labelTable: Var.Aux[java.util.Dictionary[_, _] | Null, v.type] = Slider.LabelTable.asInstanceOf[Var.Aux[java.util.Dictionary[_, _] | Null, v.type]]
    def majorTickSpacing: Var.Aux[Int, v.type] = Slider.MajorTickSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def max: Var.Aux[Int, v.type] = Slider.Max.asInstanceOf[Var.Aux[Int, v.type]]
    def min: Var.Aux[Int, v.type] = Slider.Min.asInstanceOf[Var.Aux[Int, v.type]]
    def minorTickSpacing: Var.Aux[Int, v.type] = Slider.MinorTickSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def model: Var.Aux[javax.swing.BoundedRangeModel | Null, v.type] = Slider.Model.asInstanceOf[Var.Aux[javax.swing.BoundedRangeModel | Null, v.type]]
    def orientation: Var.Aux[Int, v.type] = Slider.Orientation.asInstanceOf[Var.Aux[Int, v.type]]
    def paintLabels: Var.Aux[Boolean, v.type] = Slider.PaintLabels.asInstanceOf[Var.Aux[Boolean, v.type]]
    def paintTicks: Var.Aux[Boolean, v.type] = Slider.PaintTicks.asInstanceOf[Var.Aux[Boolean, v.type]]
    def paintTrack: Var.Aux[Boolean, v.type] = Slider.PaintTrack.asInstanceOf[Var.Aux[Boolean, v.type]]
    def snapToTicks: Var.Aux[Boolean, v.type] = Slider.SnapToTicks.asInstanceOf[Var.Aux[Boolean, v.type]]
    def value: Var.Aux[Int, v.type] = Slider.Value.asInstanceOf[Var.Aux[Int, v.type]]
    def valueIsAdjusting: Var.Aux[Boolean, v.type] = Slider.ValueIsAdjusting.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    def changeListeners = v.getChangeListeners
    def unwrap: javax.swing.JSlider = v
  }

  def wrap(v: javax.swing.JSlider) = v.asInstanceOf[Slider]

  def init(v: Slider): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    val l: ChangeListener = (e: ChangeEvent | UncheckedNull) => summon[Scenegraph].update(summon[VarContext].swingPropertyUpdated(ops.extension_value(v), v.getValue))
    v.addChangeListener(l)
    
  }
  def uninitialized(): Slider = {
    val res = javax.swing.JSlider().asInstanceOf[Slider]
    
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.SliderUI]] = UnsetParam,
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
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    extent: Opt[Binding[Int]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    inverted: Opt[Binding[Boolean]] = UnsetParam,
    labelTable: Opt[Binding[java.util.Dictionary[_, _] | Null]] = UnsetParam,
    majorTickSpacing: Opt[Binding[Int]] = UnsetParam,
    max: Opt[Binding[Int]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    min: Opt[Binding[Int]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minorTickSpacing: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[javax.swing.BoundedRangeModel | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    orientation: Opt[Binding[Int]] = UnsetParam,
    paintLabels: Opt[Binding[Boolean]] = UnsetParam,
    paintTicks: Opt[Binding[Boolean]] = UnsetParam,
    paintTrack: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    snapToTicks: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    value: Opt[Binding[Int]] = UnsetParam,
    valueIsAdjusting: Opt[Binding[Boolean]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Slider] = {
    val res = uninitialized()
    Slider.init(res)
    ifSet(UI, Slider.ops.extension_UI(res) := _)
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
    ifSet(enabled, Node.ops.extension_enabled(res) := _)
    ifSet(extent, Slider.ops.extension_extent(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.extension_inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.extension_inputVerifier(res) := _)
    ifSet(inverted, Slider.ops.extension_inverted(res) := _)
    ifSet(labelTable, Slider.ops.extension_labelTable(res) := _)
    ifSet(majorTickSpacing, Slider.ops.extension_majorTickSpacing(res) := _)
    ifSet(max, Slider.ops.extension_max(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(min, Slider.ops.extension_min(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(minorTickSpacing, Slider.ops.extension_minorTickSpacing(res) := _)
    ifSet(model, Slider.ops.extension_model(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(opaque, Component.ops.extension_opaque(res) := _)
    ifSet(orientation, Slider.ops.extension_orientation(res) := _)
    ifSet(paintLabels, Slider.ops.extension_paintLabels(res) := _)
    ifSet(paintTicks, Slider.ops.extension_paintTicks(res) := _)
    ifSet(paintTrack, Slider.ops.extension_paintTrack(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.extension_requestFocusEnabled(res) := _)
    ifSet(snapToTicks, Slider.ops.extension_snapToTicks(res) := _)
    ifSet(toolTipText, Component.ops.extension_toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.extension_transferHandler(res) := _)
    ifSet(value, Slider.ops.extension_value(res) := _)
    ifSet(valueIsAdjusting, Slider.ops.extension_valueIsAdjusting(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.extension_verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  
}