package guarana.swing

import guarana.swing.util._
import java.awt.{Component => _, _}
import javax.swing.JButton

opaque type Slider <: Component = javax.swing.JSlider & Component
object Slider extends VarsMap {
  val UI = SwingVar[Slider, javax.swing.plaf.SliderUI]("UI", _.getUI.nn, _.setUI(_))
  val Extent = SwingVar[Slider, Int]("extent", _.getExtent, _.setExtent(_))
  val Inverted = SwingVar[Slider, Boolean]("inverted", _.getInverted, _.setInverted(_))
  val LabelTable = SwingVar[Slider, java.util.Dictionary[_, _] | Null]("labelTable", _.getLabelTable, _.setLabelTable(_))
  val MajorTickSpacing = SwingVar[Slider, Int]("majorTickSpacing", _.getMajorTickSpacing, _.setMajorTickSpacing(_))
  val Max = SwingVar[Slider, Int]("max", _.getMaximum, _.setMaximum(_))
  val Min = SwingVar[Slider, Int]("min", _.getMinimum, _.setMinimum(_))
  val MinorTickSpacing = SwingVar[Slider, Int]("minorTickSpacing", _.getMinorTickSpacing, _.setMinorTickSpacing(_))
  val Model = SwingVar[Slider, javax.swing.BoundedRangeModel | Null]("model", _.getModel, _.setModel(_))
  val Orientation = SwingVar[Slider, Int]("orientation", _.getOrientation, _.setOrientation(_))
  val PaintLabels = SwingVar[Slider, Boolean]("paintLabels", _.getPaintLabels, _.setPaintLabels(_))
  val PaintTicks = SwingVar[Slider, Boolean]("paintTicks", _.getPaintTicks, _.setPaintTicks(_))
  val PaintTrack = SwingVar[Slider, Boolean]("paintTrack", _.getPaintTrack, _.setPaintTrack(_))
  val SnapToTicks = SwingVar[Slider, Boolean]("snapToTicks", _.getSnapToTicks, _.setSnapToTicks(_))
  val Value = SwingVar[Slider, Int]("value", _.getValue, _.setValue(_))
  val ValueIsAdjusting = SwingVar[Slider, Boolean]("valueIsAdjusting", _.getValueIsAdjusting, _.setValueIsAdjusting(_))

  

  given ops: (v: Slider) extended with {
    def UI = Slider.UI.forInstance(v)
    def extent = Slider.Extent.forInstance(v)
    def inverted = Slider.Inverted.forInstance(v)
    def labelTable = Slider.LabelTable.forInstance(v)
    def majorTickSpacing = Slider.MajorTickSpacing.forInstance(v)
    def max = Slider.Max.forInstance(v)
    def min = Slider.Min.forInstance(v)
    def minorTickSpacing = Slider.MinorTickSpacing.forInstance(v)
    def model = Slider.Model.forInstance(v)
    def orientation = Slider.Orientation.forInstance(v)
    def paintLabels = Slider.PaintLabels.forInstance(v)
    def paintTicks = Slider.PaintTicks.forInstance(v)
    def paintTrack = Slider.PaintTrack.forInstance(v)
    def snapToTicks = Slider.SnapToTicks.forInstance(v)
    def value = Slider.Value.forInstance(v)
    def valueIsAdjusting = Slider.ValueIsAdjusting.forInstance(v)

    

    def changeListeners = v.getChangeListeners
    def unwrap: javax.swing.JSlider = v
  }

  def apply(v: javax.swing.JSlider) = v.asInstanceOf[Slider]

  def init(v: Slider): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    min: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minorTickSpacing: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[javax.swing.BoundedRangeModel | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    orientation: Opt[Binding[Int]] = UnsetParam,
    paintLabels: Opt[Binding[Boolean]] = UnsetParam,
    paintTicks: Opt[Binding[Boolean]] = UnsetParam,
    paintTrack: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    snapToTicks: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    value: Opt[Binding[Int]] = UnsetParam,
    valueIsAdjusting: Opt[Binding[Boolean]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Slider] = {
    val res = uninitialized()
    Slider.init(res)
    ifSet(UI, Slider.ops.UI(res) := _)
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
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(extent, Slider.ops.extent(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(inverted, Slider.ops.inverted(res) := _)
    ifSet(labelTable, Slider.ops.labelTable(res) := _)
    ifSet(majorTickSpacing, Slider.ops.majorTickSpacing(res) := _)
    ifSet(max, Slider.ops.max(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(min, Slider.ops.min(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(minorTickSpacing, Slider.ops.minorTickSpacing(res) := _)
    ifSet(model, Slider.ops.model(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(orientation, Slider.ops.orientation(res) := _)
    ifSet(paintLabels, Slider.ops.paintLabels(res) := _)
    ifSet(paintTicks, Slider.ops.paintTicks(res) := _)
    ifSet(paintTrack, Slider.ops.paintTrack(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(snapToTicks, Slider.ops.snapToTicks(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(value, Slider.ops.value(res) := _)
    ifSet(valueIsAdjusting, Slider.ops.valueIsAdjusting(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}
