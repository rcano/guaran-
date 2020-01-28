package guarana.swing
package codegen

import better.files._
import language.implicitConversions

/** Since there's no way in scala to abstract over method parameters, and the builders guaraná provides
  * are based on factory methods, we need a way to generate the node mappings trying to avoid manual coding
  * mistakes. This class is used to encode the properties per type as well as inheritance, so that the code
  * for each node type can be generated.
  */

sealed trait Property {
  def name: String
  def tpe: String
  def visibility: Option[String]
}
case class SwingProp(name: String, tpe: String, getter: String, setter: String, visibility: Option[String] = None) extends Property
object SwingProp {
  def getter(name: String, tpe: String) = tpe match {
    case "Boolean" => s"_.is${name.capitalize}"
    case "Byte" | "Short" | "Int" | "Char" | "Long" | "Float" | "Double" => s"_.get${name.capitalize}"
    case s if s.endsWith("| Null") => s"_.get${name.capitalize}"
    case _ => s"_.get${name.capitalize}.nn"
  }
  def setter(name: String, tpe: String) = s"_.set${name.capitalize}(_)"
  def apply(name: String, tpe: String): SwingProp = SwingProp(name, tpe, getter(name, tpe), setter(name, tpe))
}
case class VarProp(name: String, tpe: String, initValue: String, visibility: Option[String] = None) extends Property

case class EmitterDescr(name: String, tpe: String, initializer: Seq[String])
type Parameter = (String, String)

abstract class NodeDescr(
  val name: String,
  val underlying: String,
  val parents: Seq[NodeDescr] = Seq.empty,
  val props: Seq[Property] = Seq.empty,
  val opsExtra: Seq[String] = Seq.empty,
  val emitters: Seq[EmitterDescr] = Seq.empty,
  val initExtra: Seq[String] = Seq.empty,
  val uninitExtraParams: Seq[Parameter] = Seq.empty,
  val uninitExtra: Seq[String] = Seq.empty,
  val isAbstract: Boolean = false,
  val addsPropertySwingListener: Boolean = true,
)

case object Node extends NodeDescr(
  "Node",
  "java.awt.Container",
  props = Seq(
    SwingProp("background", "java.awt.Color | Null"),
    SwingProp("bounds", "java.awt.Rectangle"),
    SwingProp("componentOrientation", "java.awt.ComponentOrientation"),
    SwingProp("cursor", "java.awt.Cursor | Null"),
    SwingProp("enabled", "Boolean"),
    SwingProp("focusable", "Boolean"),
    SwingProp("font", "java.awt.Font | Null"),
    SwingProp("foreground", "java.awt.Color | Null"),
    SwingProp("maximumSize", "java.awt.Dimension | Null"),
    SwingProp("minimumSize", "java.awt.Dimension | Null"),
    SwingProp("prefSize", "java.awt.Dimension | Null", "_.getPreferredSize", "_.setPreferredSize(_)"),
    SwingProp("visible", "Boolean"),
    VarProp("mouseLocation", "(Int, Int)", "(0, 0)"),
  ),
  opsExtra = """
    |def alignmentX = v.getAlignmentX
    |def alignmentY = v.getAlignmentY
    |def insets = v.getInsets
    |def location = v.getLocation
    |def size = v.getSize
    |def location(x: Int, y: Int) = v.setLocation(x, y)
    |def size(x: Int, y: Int) = v.setSize(x, y)
    |def children: Seq[Node] = (0 until v.getComponentCount).map(i => v.getComponent(i).asInstanceOf[Container])
    """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toIndexedSeq,
  initExtra = """
  |v addMouseMotionListener new java.awt.event.MouseMotionListener {
  |  def mouseDragged(evt: java.awt.event.MouseEvent | Null) = ()
  |  def mouseMoved(evt: java.awt.event.MouseEvent | Null) = sc.update {
  |    val nnEvt = evt.nn
  |    Node.MouseLocation.forInstance(v) := (nnEvt.getX, nnEvt.getY)
  |  }
  |}
  """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toIndexedSeq
)

case object Component extends NodeDescr(
  "Component",
  "javax.swing.JComponent",
  parents = Seq(Node),
  props = Seq(
    SwingProp("actionMap", "javax.swing.ActionMap"),
    SwingProp("alignmentX", "Float"),
    SwingProp("alignmentY", "Float"),
    SwingProp("autoscrolls", "Boolean", "_.getAutoscrolls", "_.setAutoscrolls(_)"),
    SwingProp("border", "javax.swing.border.Border | Null"),
    SwingProp("componentPopupMenu", "javax.swing.JPopupMenu | Null"),
    SwingProp("debugGraphicsOptions", "Int"),
    SwingProp("doubleBuffered", "Boolean"),
    SwingProp("inheritsPopupMenu", "Boolean", "_.getInheritsPopupMenu", "_.setInheritsPopupMenu(_)"),
    SwingProp("inputVerifier", "javax.swing.InputVerifier | Null"),
    SwingProp("opaque", "Boolean"),
    SwingProp("requestFocusEnabled", "Boolean"),
    SwingProp("toolTipText", "String | Null"),
    SwingProp("transferHandler", "javax.swing.TransferHandler | Null"),
    SwingProp("verifyInputWhenFocusTarget", "Boolean", "_.getVerifyInputWhenFocusTarget", "_.setVerifyInputWhenFocusTarget(_)"),
  ),
  opsExtra = Seq(
    "def UI = v.getUI",
    "def UIClassID = v.getUIClassID",
    "def ancestorListeners = v.getAncestorListeners",
    "def inputMap = v.getInputMap",
    "def optimizedDrawingEnabled = v.isOptimizedDrawingEnabled",
    "def paintingForPrint = v.isPaintingForPrint",
    "def paintingTile = v.isPaintingTile",
    "def registeredKeyStrokes = v.getRegisteredKeyStrokes",
    "def rootPane = v.getRootPane",
    "def topLevelAncestor = v.getTopLevelAncestor",
    "def vetoableChangeListeners = v.getVetoableChangeListeners",
    "def visibleRect = v.getVisibleRect"
  ),
  isAbstract = true,
)

case object Window extends NodeDescr(
  "Window",
  "java.awt.Window",
  parents = Seq(Component),
  props = Seq(
    SwingProp("alwaysOnTop", "Boolean"),
    SwingProp("autoRequestFocus", "Boolean"),
    SwingProp("focusCycleRoot", "Boolean"),
    SwingProp("focusableWindowState", "Boolean", "_.getFocusableWindowState", "_.setFocusableWindowState(_)"),
    SwingProp("iconImages", "java.util.List[_ <: java.awt.Image] | Null"),
    SwingProp("locationByPlatform", "Boolean"),
    SwingProp("modalExclusionType", "java.awt.Dialog.ModalExclusionType"),
    SwingProp("opacity", "Float"),
    SwingProp("shape", "java.awt.Shape | Null"),
    SwingProp("tpe", "java.awt.Window.Type", "_.getType.nn", "_.setType(_)"),
    SwingProp("root", "Node", "c => Node(c.getComponent(0).asInstanceOf[Container])", "(w, n) => w.add(n, 0)"),
  ),
  opsExtra = Seq(
    "def accessibleContext = v.getAccessibleContext",
    "def active = v.isActive",
    "def alwaysOnTopSupported = v.isAlwaysOnTopSupported",
    "def bufferStrategy = v.getBufferStrategy",
    "def focusCycleRootAncestor = v.getFocusCycleRootAncestor",
    "def focusOwner = v.getFocusOwner",
    "def focusableWindow = v.isFocusableWindow",
    "def focused = v.isFocused",
    "def inputContext = v.getInputContext",
    "def locale = v.getLocale",
    "def mostRecentFocusOwner = v.getMostRecentFocusOwner",
    "def opaque = v.isOpaque",
    "def ownedWindows = v.getOwnedWindows",
    "def owner = v.getOwner",
    "def pack() = v.pack()",
    "def showing = v.isShowing",
    "def toolkit = v.getToolkit",
    "def validateRoot = v.isValidateRoot",
    "def warningString = v.getWarningString",
    "def windowFocusListeners = v.getWindowFocusListeners",
    "def windowListeners = v.getWindowListeners",
    "def windowStateListeners = v.getWindowStateListeners"
  ),
  uninitExtraParams = Seq("parent" -> "java.awt.Window | Null = null", "gc" -> "GraphicsConfiguration | Null = null"),
)

case object Frame extends NodeDescr(
  "Frame",
  "java.awt.Frame",
  parents = Seq(Window),
  props = Seq(
    SwingProp("extendedState", "Int"),
    SwingProp("iconImage", "java.awt.Image | Null"),
    SwingProp("maximizedBounds", "java.awt.Rectangle | Null"),
    SwingProp("menuBar", "java.awt.MenuBar | Null"),
    SwingProp("resizable", "Boolean"),
    SwingProp("state", "Int"),
    SwingProp("title", "java.lang.String | Null"),
    SwingProp("undecorated", "Boolean"),
  ),
  opsExtra = Seq(
    "def cursorType = v.getCursorType"
  ),
  uninitExtraParams = Seq("gc" -> "GraphicsConfiguration | Null = null"),
)

////////////////////////////////////////////////////////////////////////////
// Panels
////////////////////////////////////////////////////////////////////////////

case object Pane extends NodeDescr(
  "Pane",
  "javax.swing.JPanel",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.PanelUI"),
  ),
)

case object AbsolutePositioningPane extends NodeDescr(
  "AbsolutePositioningPane",
  "javax.swing.JPanel",
  parents = Seq(Pane),
  props = Seq(
    SwingProp("nodes", "Seq[Node]",
      "c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node])",
      "(p, children) => { p.removeAll(); children foreach (n => p.add(n)) }"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(null)"),
)

case object BorderPane extends NodeDescr(
  "BorderPane",
  "javax.swing.JPanel",
  parents = Seq(Pane),
  props = Seq(
    SwingProp("top", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.NORTH).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n, BorderLayout.NORTH) }"),
    SwingProp("bottom", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.SOUTH).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n, BorderLayout.SOUTH) }"),
    SwingProp("left", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.WEST).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n, BorderLayout.WEST) }"),
    SwingProp("right", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.EAST).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n, BorderLayout.EAST) }"),
    SwingProp("center", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n, BorderLayout.CENTER) }"),
    SwingProp("hgap", "Int",
      "c => c.getLayout.asInstanceOf[BorderLayout].getHgap",
      "(p, g) => p.getLayout.asInstanceOf[BorderLayout].setHgap(g)"),
    SwingProp("vgap", "Int",
      "c => c.getLayout.asInstanceOf[BorderLayout].getVgap",
      "(p, g) => p.getLayout.asInstanceOf[BorderLayout].setVgap(g)"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(BorderLayout())"),
)

case object GridPane extends NodeDescr(
  "GridPane",
  "javax.swing.JPanel",
  parents = Seq(Pane),
  props = Seq(
    VarProp("rows", "Seq[Seq[Node]]", "Seq.empty"),
    VarProp("hgap", "Int", "0"),
    VarProp("vgap", "Int", "0"),
    SwingProp("layoutVar", "Unit", "_ => ()", "(_, _) => ()"),
    SwingProp("autoCreateContainerGaps", "Boolean",
      "_.getLayout.asInstanceOf[GroupLayout].getAutoCreateContainerGaps()",
      "_.getLayout.asInstanceOf[GroupLayout].setAutoCreateContainerGaps(_)"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(GroupLayout(res))"),
  initExtra = """
      |sc.update(LayoutVar.forInstance(v) := Binding.dyn {
      |val rows = v.rows()
      |val hgap = v.hgap()
      |val vgap = v.vgap()

      |val layout = v.getLayout.asInstanceOf[GroupLayout]
      |val hgroup = layout.createSequentialGroup().nn
      |val vgroup = layout.createSequentialGroup().nn

      |val hSeqGroups = collection.mutable.Map.empty[Int, GroupLayout#ParallelGroup]
      |val vSeqGroups = collection.mutable.Map.empty[Int, GroupLayout#ParallelGroup]

      |val rowSize = rows.size

      |for {
      |  (row, rowIdx) <- rows.zipWithIndex
      |  colSize = row.length
      |  (node, colIdx) <- row.zipWithIndex
      |} {
      |  hSeqGroups
      |    .getOrElseUpdate(colIdx, layout.createParallelGroup().nn.tap { g => 
      |      hgroup.addGroup(g)
      |      if (hgap > 0 && colIdx < colSize - 1) hgroup.addGap(hgap)
      |    })
      |    .addComponent(node)

      |  vSeqGroups
      |    .getOrElseUpdate(rowIdx, layout.createBaselineGroup(true, false).nn.tap { g => 
      |      vgroup.addGroup(g)
      |      if (vgap > 0 && rowIdx < rowSize - 1) vgroup.addGap(vgap)
      |    })
      |    .addComponent(node)
      |}

      |layout.setHorizontalGroup(hgroup)
      |layout.setVerticalGroup(vgroup)
      |})
  """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toIndexedSeq
)

case object Hbox extends NodeDescr(
  "Hbox",
  "javax.swing.JPanel",
  parents = Seq(Pane),
  props = Seq(
    SwingProp("nodes", "Seq[Node]",
      "c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node])",
      "(p, children) => { p.removeAll(); children foreach (n => p.add(n)) }"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(BoxLayout(res, BoxLayout.X_AXIS))"),
)
case object Vbox extends NodeDescr(
  "Vbox",
  "javax.swing.JPanel",
  parents = Seq(Pane),
  props = Seq(
    SwingProp("nodes", "Seq[Node]",
      "c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node])",
      "(p, children) => { p.removeAll(); children foreach (n => p.add(n)) }"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(BoxLayout(res, BoxLayout.Y_AXIS))"),
)

////////////////////////////////////////////////////////////////////////////
// Text Components
////////////////////////////////////////////////////////////////////////////


case object TextComponent extends NodeDescr(
  "TextComponent",
  "javax.swing.text.JTextComponent",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.TextUI"),
    SwingProp("caret", "javax.swing.text.Caret"),
    SwingProp("caretColor", "java.awt.Color | Null"),
    SwingProp("disabledTextColor", "java.awt.Color | Null"),
    SwingProp("document", "javax.swing.text.Document"),
    SwingProp("dragEnabled", "Boolean", "_.getDragEnabled", "_.setDragEnabled(_)"),
    SwingProp("dropMode", "javax.swing.DropMode | Null"),
    SwingProp("editable", "Boolean"),
    SwingProp("focusAccelerator", "Char"),
    SwingProp("highlighter", "javax.swing.text.Highlighter | Null"),
    SwingProp("keymap", "javax.swing.text.Keymap | Null"),
    SwingProp("margin", "java.awt.Insets | Null"),
    SwingProp("navigationFilter", "javax.swing.text.NavigationFilter | Null"),
    SwingProp("selectedTextColor", "java.awt.Color | Null"),
    SwingProp("selectionColor", "java.awt.Color | Null"),
  ),
  opsExtra = Seq(
    "def actions = v.getActions",
    "def caretListeners = v.getCaretListeners",
    "def dropLocation = v.getDropLocation",
    "def preferredScrollableViewportSize = v.getPreferredScrollableViewportSize",
    "def scrollableTracksViewportHeight = v.getScrollableTracksViewportHeight",
    "def scrollableTracksViewportWidth = v.getScrollableTracksViewportWidth",
    "def selectedText = v.getSelectedText",
    "def text = v.getText",
    "def text_=(s: String) = v.setText(s)"
  ),
  isAbstract = true,
  initExtra = """
   |v.getDocument.addDocumentListener(new DocumentListener {
   |  def changedUpdate(evt: DocumentEvent | Null) = notifyChange()
   |  def insertUpdate(evt: DocumentEvent | Null) = notifyChange()
   |  def removeUpdate(evt: DocumentEvent | Null) = notifyChange()
   |  def notifyChange() = summon[Scenegraph].update(summon[VarContext].swingPropertyUpdated(ops.document(v), v.getDocument.nn))
   |})
  """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toIndexedSeq
)
case object TextArea extends NodeDescr(
  "TextArea",
  "javax.swing.JTextArea",
  parents = Seq(TextComponent),
  props = Seq(
    SwingProp("columns", "Int"),
    SwingProp("lineWrap", "Boolean", "_.getLineWrap", "_.setLineWrap(_)"),
    SwingProp("rows", "Int"),
    SwingProp("tabSize", "Int"),
    SwingProp("wrapStyleWord", "Boolean", "_.getWrapStyleWord", "_.setWrapStyleWord(_)"),
  ),
  opsExtra = Seq(
    "def lineCount = v.getLineCount"
  ),
)
case object TextField extends NodeDescr(
  "TextField",
  "javax.swing.JTextField",
  parents = Seq(TextComponent),
  props = Seq(
    SwingProp("action", "javax.swing.Action | Null"),
    SwingProp("columns", "Int"),
    SwingProp("horizontalAlignment", "Int"),
    SwingProp("scrollOffset", "Int")
  ),
  opsExtra = Seq(
    "def horizontalVisibility = v.getHorizontalVisibility"
  ),
)
case object PasswordField extends NodeDescr(
  "PasswordField",
  "javax.swing.JPasswordField",
  parents = Seq(TextField),
  props = Seq(
    SwingProp("echoChar", "Char"),
  ),
  opsExtra = Seq(
    "def password = v.getPassword"
  ),
)
case object Label extends NodeDescr(
  "Label",
  "javax.swing.JLabel",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.LabelUI"),
    SwingProp("disabledIcon", "javax.swing.Icon | Null"),
    SwingProp("displayedMnemonic", "Int"),
    SwingProp("displayedMnemonicIndex", "Int"),
    SwingProp("horizontalAlignment", "Int"),
    SwingProp("horizontalTextPosition", "Int"),
    SwingProp("icon", "javax.swing.Icon | Null"),
    SwingProp("iconTextGap", "Int"),
    SwingProp("labelFor", "java.awt.Component | Null"),
    SwingProp("text", "java.lang.String | Null"),
    SwingProp("verticalAlignment", "Int"),
    SwingProp("verticalTextPosition", "Int")
  ),
  opsExtra = Seq(
  ),
)

////////////////////////////////////////////////////////////////////////////
// Buttons
////////////////////////////////////////////////////////////////////////////

case object ButtonBase extends NodeDescr(
  "ButtonBase",
  "javax.swing.AbstractButton",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.ButtonUI"),
    SwingProp("action", "javax.swing.Action | Null"),
    SwingProp("actionCommand", "java.lang.String | Null"),
    SwingProp("borderPainted", "Boolean"),
    SwingProp("contentAreaFilled", "Boolean"),
    SwingProp("disabledIcon", "javax.swing.Icon | Null"),
    SwingProp("disabledSelectedIcon", "javax.swing.Icon | Null"),
    SwingProp("displayedMnemonicIndex", "Int"),
    SwingProp("focusPainted", "Boolean"),
    SwingProp("hideActionText", "Boolean", "_.getHideActionText", "_.setHideActionText(_)"),
    SwingProp("horizontalAlignment", "Int"),
    SwingProp("horizontalTextPosition", "Int"),
    SwingProp("icon", "javax.swing.Icon | Null"),
    SwingProp("iconTextGap", "Int"),
    SwingProp("label", "java.lang.String | Null"),
    SwingProp("margin", "java.awt.Insets | Null"),
    SwingProp("mnemonic", "Int"),
    SwingProp("model", "javax.swing.ButtonModel | Null"),
    SwingProp("multiClickThreshhold", "Long"),
    SwingProp("pressedIcon", "javax.swing.Icon | Null"),
    SwingProp("rolloverEnabled", "Boolean"),
    SwingProp("rolloverIcon", "javax.swing.Icon | Null"),
    SwingProp("rolloverSelectedIcon", "javax.swing.Icon | Null"),
    SwingProp("selected", "Boolean"),
    SwingProp("selectedIcon", "javax.swing.Icon | Null"),
    SwingProp("text", "java.lang.String | Null"),
    SwingProp("verticalAlignment", "Int"),
    SwingProp("verticalTextPosition", "Int"),
  ),
  emitters = Seq(
    EmitterDescr("actionEvents", "java.awt.event.ActionEvent",
      "val al: java.awt.event.ActionListener = evt => sc.update(summon[Emitter.Context].emit(v.actionEvents, evt.nn))" ::
      "v.addActionListener(al)" ::
      Nil),
  ),
  opsExtra = Seq(
    "def actionListeners = v.getActionListeners",
    "def changeListeners = v.getChangeListeners",
    "def itemListeners = v.getItemListeners",
    "def selectedObjects = v.getSelectedObjects"
  ),
  isAbstract = true
)

case object Button extends NodeDescr(
  "Button",
  "javax.swing.JButton",
  parents = Seq(ButtonBase),
  props = Seq(
    SwingProp("defaultCapable", "Boolean"),
  ),
  opsExtra = Seq(
   "def defaultButton = v.isDefaultButton"
  ),
)

case object ToggleButton extends NodeDescr(
  "ToggleButton",
  "javax.swing.JToggleButton",
  parents = Seq(ButtonBase),
)

case object CheckBox extends NodeDescr(
  "CheckBox",
  "javax.swing.JCheckBox",
  parents = Seq(ToggleButton),
  props = Seq(
    SwingProp("borderPaintedFlat", "Boolean")
  ),
)

case object RadioButton extends NodeDescr(
  "RadioButton",
  "javax.swing.JRadioButton",
  parents = Seq(ToggleButton),
)

////////////////////////////////////////////////////////////////////////////
// slider
////////////////////////////////////////////////////////////////////////////

case object Slider extends NodeDescr(
  "Slider",
  "javax.swing.JSlider",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.SliderUI"),
    SwingProp("extent", "Int"),
    SwingProp("inverted", "Boolean", "_.getInverted", "_.setInverted(_)"),
    SwingProp("labelTable", "java.util.Dictionary[_, _] | Null"),
    SwingProp("majorTickSpacing", "Int"),
    SwingProp("min", "Int", "_.getMinimum", "_.setMinimum(_)"),
    SwingProp("max", "Int", "_.getMaximum", "_.setMaximum(_)"),
    SwingProp("minorTickSpacing", "Int"),
    SwingProp("model", "javax.swing.BoundedRangeModel | Null"),
    SwingProp("orientation", "Int"),
    SwingProp("paintLabels", "Boolean", "_.getPaintLabels", "_.setPaintLabels(_)"),
    SwingProp("paintTicks", "Boolean", "_.getPaintTicks", "_.setPaintTicks(_)"),
    SwingProp("paintTrack", "Boolean", "_.getPaintTrack", "_.setPaintTrack(_)"),
    SwingProp("snapToTicks", "Boolean", "_.getSnapToTicks", "_.setSnapToTicks(_)"),
    SwingProp("value", "Int"),
    SwingProp("valueIsAdjusting", "Boolean", "_.getValueIsAdjusting", "_.setValueIsAdjusting(_)"),
  ),
  emitters = Seq(
  ),
  opsExtra = Seq(
    "def changeListeners = v.getChangeListeners",
  ),
)

////////////////////////////////////////////////////////////////////////////
// main function
////////////////////////////////////////////////////////////////////////////
def genCode(n: NodeDescr): String = {
  def propDecl(p: Property) = p.visibility.map(s => s + " ").getOrElse("") + (p match {
    case SwingProp(name, tpe, getter, setter, _) => s"""val ${name.capitalize} = SwingVar[${n.name}, $tpe]("$name", $getter, $setter)"""
    case VarProp(name, tpe, initValue, _) => s"""val ${name.capitalize} = Var[$tpe]("$name", $initValue)"""
  })

  val allVars: Vector[(NodeDescr, Property)] = Iterator.unfold(Seq(n)) {
    case Seq() => None
    case parents => Some(parents.flatMap(p => p.props.map(p -> _)) -> parents.flatMap(_.parents))
  }.flatten.toVector.sortBy(_._2.name)

  val initializers = if (!n.isAbstract) 
      s"""def uninitialized(${n.uninitExtraParams.map(t => s"${t._1}: ${t._2}").mkString(", ")}): ${n.name} = {
         |  val res = ${n.underlying}(${n.uninitExtraParams.map(_._1).mkString(", ")}).asInstanceOf[${n.name}]
         |  ${n.uninitExtra.mkString("\n    ")}
         |  res
         |}
         |
         |def apply(
         |  ${if (n.uninitExtraParams.nonEmpty) n.uninitExtraParams.map(t => s"${t._1}: ${t._2}").mkString(", ") + "," else ""}
         |  ${allVars.map(v => s"${v._2.name}: Opt[Binding[${v._2.tpe}]] = UnsetParam").mkString(",\n  ")}
         |): (given Scenegraph) => VarContextAction[${n.name}] = {
         |  val res = uninitialized()
         |  ${n.name}.init(res)
         |  ${allVars.map(v => s"ifSet(${v._2.name}, ${v._1.name}.ops.${v._2.name}(res) := _)").mkString("\n  ")}
         |  res
         |}
         |
      """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toSeq
    else Seq.empty 

  val sortedProps = n.props.sortBy(_.name)
  val sortedEmitters = n.emitters.sortBy(_.name)

  s"""opaque type ${n.name} ${if (n.parents.nonEmpty) n.parents.mkString("<: ", " & ", "") else ""} = ${(n.underlying +: n.parents).mkString(" & ")}
     |object ${n.name} extends VarsMap {
     |  ${sortedProps.map(propDecl).mkString("\n  ")}
     |
     |  ${sortedEmitters.map(e => s"val ${e.name.capitalize} = Emitter[${e.tpe}]()").mkString("\n  ")}
     |
     |  given ops: (v: ${n.name}) extended with {
     |    ${sortedProps.map(p => s"def ${p.name} = ${n.name}.${p.name.capitalize}.forInstance(v)").mkString("\n    ")}

     |    ${sortedEmitters.map(e => s"def ${e.name} = ${n.name}.${e.name.capitalize}.forInstance(v)").mkString("\n    ")}

     |    ${n.opsExtra.mkString("\n    ")}
     |    def unwrap: ${n.underlying} = v
     |  }
     |
     |  def apply(v: ${n.underlying}) = v.asInstanceOf[${n.name}]
     |
     |  def init(v: ${n.name}): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
     |    ${n.parents.headOption.map(p => s"$p.init(v)").getOrElse("")}
     |    ${if (n.addsPropertySwingListener) "v.addPropertyChangeListener(varsPropertyListener(v))" else ""}
     |    ${n.initExtra.mkString("\n    ")}
     |    ${sortedEmitters.flatMap(_.initializer).mkString("\n    ")}
     |  }
     |  ${initializers.mkString("\n  ")}
     |}
  """.stripMargin.trim.nn
}

@main def run(): Unit = {
  val dest = File("src/main/scala/guarana/swing/impl/allNodes.scala").clear()
  val preamble = """
    //AUTOGENERATED FILE, DO NOT MODIFY

    |package guarana.swing
    |package impl
    |import language.implicitConversions
    |import java.awt.{Component => _, TextComponent => _, _}
    |import java.awt.event._
    |import javax.swing._
    |import javax.swing.event._
    |import guarana.swing.util._
    |import scala.util.chaining._
    """.stripMargin.trim.nn
  dest.append(preamble).append("\n\n")

  for (node <- Seq(
    Node,
    Component,
    Window,
    Frame,
    Pane,
    AbsolutePositioningPane,
    BorderPane,
    GridPane,
    Hbox,
    Vbox,
    TextComponent,
    TextArea,
    TextField,
    PasswordField,
    Label,
    ButtonBase,
    Button,
    ToggleButton,
    CheckBox,
    RadioButton,
    Slider
  )) dest.append(genCode(node)).append("\n\n")
}