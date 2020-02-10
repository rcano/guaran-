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
  def overrideTpeInStaticPos: Option[String]
  def tpeInStaticPos = (overrideTpeInStaticPos getOrElse tpe) match {
    case "_" => "Any"
    case other => other
  }
}
case class SwingProp(name: String, tpe: String, getter: String, setter: String, visibility: Option[String] = None, overrideTpeInStaticPos: Option[String] = None) extends Property
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
case class VarProp(name: String, tpe: String, initValue: String, visibility: Option[String] = None, overrideTpeInStaticPos: Option[String] = None, eagerEvaluation: Boolean = false) extends Property

case class EmitterDescr(name: String, tpe: String, initializer: Seq[String])
case class Parameter(name: String, tpe: String, passAs: String)

abstract class NodeDescr(
  val name: String,
  val underlying: String,
  val tpeParams: Seq[String] = Seq.empty,
  val parents: Seq[NodeDescr] = Seq.empty,
  val props: Seq[Property] = Seq.empty,
  val fieldsExtra: Seq[String] = Seq.empty,
  val opsExtra: Seq[String] = Seq.empty,
  val emitters: Seq[EmitterDescr] = Seq.empty,
  val initExtra: Seq[String] = Seq.empty,
  val uninitExtraParams: Seq[Parameter] = Seq.empty,
  val uninitExtra: Seq[String] = Seq.empty,
  val isAbstract: Boolean = false,
  val addsPropertySwingListener: Boolean = true,
  val companionObjectExtras: Seq[String] = Seq.empty,
)

case object Node extends NodeDescr(
  "Node",
  "java.awt.Container",
  props = Seq(
    SwingProp("background", "java.awt.Color | Null"),
    SwingProp("bounds", "Bounds"),
    SwingProp("componentOrientation", "java.awt.ComponentOrientation"),
    SwingProp("cursor", "java.awt.Cursor | Null"),
    SwingProp("enabled", "Boolean"),
    SwingProp("focusable", "Boolean"),
    VarProp("focusedMut", "Boolean", "false", Some("private")),
    SwingProp("font", "java.awt.Font | Null"),
    SwingProp("foreground", "java.awt.Color | Null"),
    SwingProp("maxSize", "(Double, Double) | Null",
      "{n => val d = n.getMaximumSize; if (d != null) (d.getWidth, d.getHeight) else null}",
      "{(n, d) => n.setMaximumSize(if (d == null) null else java.awt.Dimension(d._1.toInt, d._2.toInt))}"),
    SwingProp("minSize", "(Double, Double) | Null",
      "{n => val d = n.getMinimumSize; if (d != null) (d.getWidth, d.getHeight) else null}",
      "{(n, d) => n.setMinimumSize(if (d == null) null else java.awt.Dimension(d._1.toInt, d._2.toInt))}"),
    SwingProp("prefSize", "(Double, Double) | Null",
      "{n => val d = n.getPreferredSize; if (d != null) (d.getWidth, d.getHeight) else null}",
      "{(n, d) => n.setPreferredSize(if (d == null) null else java.awt.Dimension(d._1.toInt, d._2.toInt))}"),
    SwingProp("visible", "Boolean"),
    VarProp("mouseLocationMut", "(Int, Int)", "(0, 0)", Some("private")),
  ),
  fieldsExtra = Seq("val MouseLocation = mouseLocationMut.asObsValIn()"),
  opsExtra = """
    |def focused = Node.FocusedMut.asObsValIn(v)
    |def mouseLocation = Node.MouseLocationMut.asObsValIn(v)
    |def alignmentX = v.getAlignmentX
    |def alignmentY = v.getAlignmentY
    |def insets = v.getInsets
    |def location = v.getLocation
    |def size = v.getSize
    |def location(x: Int, y: Int) = v.setLocation(x, y)
    |def requestFocus() = v.requestFocus()
    |def requestFocusInWindow() = v.requestFocusInWindow()
    |def size(x: Int, y: Int) = v.setSize(x, y)
    |def children: Seq[Node] = (0 until v.getComponentCount).map(i => v.getComponent(i).asInstanceOf[Container])
    """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toIndexedSeq,
  emitters = Seq(EmitterDescr("focusEvents", "(FocusEvent, Boolean)", Nil)),
  initExtra = """
  |v addMouseMotionListener new java.awt.event.MouseMotionListener {
  |  def mouseDragged(evt: java.awt.event.MouseEvent | Null) = ()
  |  def mouseMoved(evt: java.awt.event.MouseEvent | Null) = sc.update {
  |    val nnEvt = evt.nn
  |    Node.MouseLocationMut.forInstance(v) := (nnEvt.getX, nnEvt.getY)
  |  }
  |}
  |v addFocusListener new FocusListener {
  |  def focusGained(evt: FocusEvent | UncheckedNull) = sc.update {
  |    Node.FocusedMut.forInstance(v) := true 
  |    summon[Emitter.Context].emit(v.focusEvents, (evt.nn -> true))
  |  }
  |  def focusLost(evt: FocusEvent | UncheckedNull) = sc.update {
  |    Node.FocusedMut.forInstance(v) := false
  |    summon[Emitter.Context].emit(v.focusEvents, (evt.nn -> false))
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

case object WindowBase extends NodeDescr(
  "WindowBase",
  "java.awt.Window",
  parents = Seq(Node),
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
    SwingProp("root", "Node", "c => Node(c.getComponent(0).asInstanceOf[Container])", "(w, n) => w.add(n.unwrap, 0)"),
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
    "def toFront() = v.toFront()",
    "def toBack() = v.toBack()",
    "def toolkit = v.getToolkit",
    "def validateRoot = v.isValidateRoot",
    "def warningString = v.getWarningString",
    "def windowFocusListeners = v.getWindowFocusListeners",
    "def windowListeners = v.getWindowListeners",
    "def windowStateListeners = v.getWindowStateListeners"
  ),
  uninitExtraParams = Seq(Parameter("parent", "WindowBase | Null = null", "parent"), Parameter("gc", "GraphicsConfiguration | Null = null", "gc")),
)

case object Window extends NodeDescr(
  "Window",
  "javax.swing.JWindow",
  parents = Seq(WindowBase),
  props = Seq(
    SwingProp("contentPane", "java.awt.Container", "_.getContentPane().nn", "_.setContentPane(_)"),
    SwingProp("glassPane", "java.awt.Component | Null"),
    SwingProp("layeredPane", "javax.swing.JLayeredPane | Null"),
    SwingProp("transferHandler", "javax.swing.TransferHandler | Null"),
  ),
  opsExtra = Seq(
    "def rootPane: JRootPane = v.getRootPane.nn"
  ),
  uninitExtraParams = Seq(Parameter("gc", "GraphicsConfiguration | Null = null", "gc")),
)

case object Frame extends NodeDescr(
  "Frame",
  "javax.swing.JFrame",
  parents = Seq(WindowBase),
  props = Seq(
    SwingProp("extendedState", "Int"),
    SwingProp("iconImage", "java.awt.Image | Null"),
    SwingProp("maximizedBounds", "Bounds | Null"),
    SwingProp("resizable", "Boolean"),
    SwingProp("state", "Int"),
    SwingProp("title", "java.lang.String | Null"),
    SwingProp("undecorated", "Boolean"),
    SwingProp("menuBar", "javax.swing.JMenuBar | Null", "_.getJMenuBar", "_.setJMenuBar(_)"),
    SwingProp("contentPane", "java.awt.Container", "_.getContentPane().nn", "_.setContentPane(_)"),
    SwingProp("defaultCloseOperation", "Int"),
    SwingProp("glassPane", "java.awt.Component | Null"),
    SwingProp("layeredPane", "javax.swing.JLayeredPane | Null"),
    SwingProp("transferHandler", "javax.swing.TransferHandler | Null"),
  ),
  opsExtra = Seq(
    "def rootPane: JRootPane = v.getRootPane.nn"
  ),
  uninitExtraParams = Seq(Parameter("gc", "GraphicsConfiguration | Null = null", "gc")),
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
      "(p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) }"),
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
      "(p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.NORTH) }"),
    SwingProp("bottom", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.SOUTH).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.SOUTH) }"),
    SwingProp("left", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.WEST).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.WEST) }"),
    SwingProp("right", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.EAST).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.EAST) }"),
    SwingProp("center", "Node | Null",
      "c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER).asInstanceOf[Node | Null]",
      "(p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.CENTER) }"),
    SwingProp("hgap", "Double",
      "c => c.getLayout.asInstanceOf[BorderLayout].getHgap",
      "(p, g) => p.getLayout.asInstanceOf[BorderLayout].setHgap(g.toInt)"),
    SwingProp("vgap", "Double",
      "c => c.getLayout.asInstanceOf[BorderLayout].getVgap",
      "(p, g) => p.getLayout.asInstanceOf[BorderLayout].setVgap(g.toInt)"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(BorderLayout())"),
)

case object GridPane extends NodeDescr(
  "GridPane",
  "javax.swing.JPanel",
  parents = Seq(Pane),
  props = Seq(
    VarProp("rows", "Seq[Seq[Node]]", "Seq.empty"),
    VarProp("hgap", "Double", "0.0"),
    VarProp("vgap", "Double", "0.0"),
    SwingProp("layoutVar", "Unit", "_ => ()", "(_, _) => ()"),
    SwingProp("autoCreateContainerGaps", "Boolean",
      "_.getLayout.asInstanceOf[GroupLayout].getAutoCreateContainerGaps()",
      "_.getLayout.asInstanceOf[GroupLayout].setAutoCreateContainerGaps(_)"),
  ),
  uninitExtra = Seq("res.asInstanceOf[JPanel].setLayout(GroupLayout(res))"),
  initExtra = """
      |sc.update(LayoutVar.forInstance(v) := Binding.dyn {
      |val rows = v.rows()
      |val hgap = v.hgap().toInt
      |val vgap = v.vgap().toInt

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
      |    .addComponent(node.unwrap)

      |  vSeqGroups
      |    .getOrElseUpdate(rowIdx, layout.createBaselineGroup(true, false).nn.tap { g => 
      |      vgroup.addGroup(g)
      |      if (vgap > 0 && rowIdx < rowSize - 1) vgroup.addGap(vgap)
      |    })
      |    .addComponent(node.unwrap)
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
      "(p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) }"),
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
      "(p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) }"),
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
    SwingProp("iconTextGap", "Double", "_.getIconTextGap", "(l, g) => l.setIconTextGap(g.toInt)"),
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
    SwingProp("armed", "Boolean", "_.getModel.nn.isArmed", "_.getModel.nn.setArmed(_)"),
    SwingProp("borderPainted", "Boolean"),
    SwingProp("contentAreaFilled", "Boolean"),
    SwingProp("disabledIcon", "javax.swing.Icon | Null"),
    SwingProp("disabledSelectedIcon", "javax.swing.Icon | Null"),
    SwingProp("displayedMnemonicIndex", "Int"),
    SwingProp("enabled", "Boolean", "_.getModel.nn.isEnabled", "_.getModel.nn.setEnabled(_)"),
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
    SwingProp("pressed", "Boolean", "_.getModel.nn.isPressed", "_.getModel.nn.setPressed(_)"),
    SwingProp("pressedIcon", "javax.swing.Icon | Null"),
    SwingProp("rollover", "Boolean", "_.getModel.nn.isRollover", "_.getModel.nn.setRollover(_)"),
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
      """val al: java.awt.event.ActionListener = evt => sc.update(summon[Emitter.Context].emit(v.actionEvents, evt.nn))
        |v.addActionListener(al)
        |val m = v.getModel.nn
        |var wasArmed = m.isArmed
        |var wasEnabled = m.isEnabled
        |var wasPressed = m.isPressed
        |var wasRollover = m.isRollover
        |var wasSelected = v.isSelected
        |val cl: javax.swing.event.ChangeListener = evt => sc.update {
        |  val ctx = summon[VarContext]
        |  val m = v.getModel.nn
        |  if (m.isArmed != wasArmed)
        |    ctx.swingPropertyUpdated(ops.armed(v), m.isArmed)
        |  wasArmed = m.isArmed
        |  if (m.isEnabled != wasEnabled)
        |    ctx.swingPropertyUpdated(ops.enabled(v), m.isEnabled)
        |  wasEnabled = m.isEnabled
        |  if (m.isPressed != wasPressed)
        |    ctx.swingPropertyUpdated(ops.pressed(v), m.isPressed)
        |  wasPressed = m.isPressed
        |  if (m.isRollover != wasRollover)
        |    ctx.swingPropertyUpdated(ops.rollover(v), m.isRollover)
        |  wasRollover = m.isRollover
        |  if (v.isSelected != wasSelected)
        |    ctx.swingPropertyUpdated(ops.selected(v), v.isSelected)
        |  wasSelected = v.isSelected
        |}
        |v.addChangeListener(cl)
        """.trim.nn.stripMargin.split("\n").asInstanceOf[Array[String]].toIndexedSeq),
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
  opsExtra = Seq(
    "def changeListeners = v.getChangeListeners",
  ),
  initExtra = 
    "val l: ChangeListener = (e: ChangeEvent | UncheckedNull) => summon[Scenegraph].update(summon[VarContext].swingPropertyUpdated(ops.value(v), v.getValue))" ::
    "v.addChangeListener(l)" ::
    Nil
)

////////////////////////////////////////////////////////////////////////////
// progress bar
////////////////////////////////////////////////////////////////////////////

case object ProgressBar extends NodeDescr(
  "ProgressBar",
  "javax.swing.JProgressBar",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.ProgressBarUI"),
    SwingProp("borderPainted", "Boolean"),
    SwingProp("indeterminate", "Boolean"),
    SwingProp("max", "Int", "_.getMaximum", "_.setMaximum(_)"),
    SwingProp("min", "Int", "_.getMinimum", "_.setMinimum(_)"),
    SwingProp("model", "javax.swing.BoundedRangeModel | Null"),
    SwingProp("orientation", "Int"),
    SwingProp("string", "java.lang.String | Null"),
    SwingProp("stringPainted", "Boolean"),
    SwingProp("value", "Int"),
  ),
  opsExtra = Seq(
    "def changeListeners = v.getChangeListeners",
    "def percentComplete = v.getPercentComplete"
  ),
  initExtra = 
    "val l: ChangeListener = (e: ChangeEvent | UncheckedNull) => summon[Scenegraph].update(summon[VarContext].swingPropertyUpdated(ops.value(v), v.getValue))" ::
    "v.addChangeListener(l)" ::
    Nil
)


////////////////////////////////////////////////////////////////////////////
// list
////////////////////////////////////////////////////////////////////////////


case object ListView extends NodeDescr(
  "ListView",
  "javax.swing.JList[_ <: E]",
  tpeParams = Seq("+E"),
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.ListUI"),
    SwingProp("cellRenderer", "javax.swing.ListCellRenderer[_ >: E]", "_.getCellRenderer.nn", "(l, c) => l.setCellRenderer(c.asInstanceOf)", overrideTpeInStaticPos = Some("javax.swing.ListCellRenderer[_]")),
    SwingProp("dragEnabled", "Boolean", "_.getDragEnabled", "_.setDragEnabled(_)"),
    SwingProp("dropMode", "javax.swing.DropMode | Null"),
    SwingProp("fixedCellHeight", "Int"),
    SwingProp("fixedCellWidth", "Int"),
    SwingProp("layoutOrientation", "Int"),
    SwingProp("model", "javax.swing.ListModel[E]", "_.getModel.nn" , "(l, m) => l.setModel(m.asInstanceOf)", overrideTpeInStaticPos = Some("javax.swing.ListModel[_]")),
    SwingProp("prototypeCellValue", "E | Null", "_.getPrototypeCellValue", "(l, p) => l.setPrototypeCellValue(p.asInstanceOf)", overrideTpeInStaticPos = Some("_")),
    SwingProp("selectedIndex", "Int"),
    SwingProp("selectedIndices", "Array[Int]"),
    SwingProp("selectionBackground", "java.awt.Color | Null"),
    SwingProp("selectionForeground", "java.awt.Color | Null"),
    SwingProp("selectionMode", "Int"),
    SwingProp("selectionModel", "javax.swing.ListSelectionModel", "_.getSelectionModel.nn", "_.setSelectionModel(_)"),
    SwingProp("valueIsAdjusting", "Boolean", "_.getValueIsAdjusting", "_.setValueIsAdjusting(_)"),
    SwingProp("visibleRowCount", "Int"),
  ),
  opsExtra = Seq(
    "def anchorSelectionIndex: Int = v.getAnchorSelectionIndex",
    "def dropLocation: javax.swing.JList.DropLocation | Null = v.getDropLocation",
    "def firstVisibleIndex: Int = v.getFirstVisibleIndex",
    "def lastVisibleIndex: Int = v.getLastVisibleIndex",
    "def leadSelectionIndex: Int = v.getLeadSelectionIndex",
    "def listSelectionListeners: Array[javax.swing.event.ListSelectionListener] = v.getListSelectionListeners.asInstanceOf[Array[javax.swing.event.ListSelectionListener]]",
    "def maxSelectionIndex: Int = v.getMaxSelectionIndex",
    "def minSelectionIndex: Int = v.getMinSelectionIndex",
    "def preferredScrollableViewportSize: java.awt.Dimension | Null = v.getPreferredScrollableViewportSize",
    "def scrollableTracksViewportHeight: Boolean = v.getScrollableTracksViewportHeight",
    "def scrollableTracksViewportWidth: Boolean = v.getScrollableTracksViewportWidth",
    "def selectedValue: Option[E] = { val r = v.getSelectedValue; if (r == null) None else Some(r) }",
    "def selectedValues: Seq[E] = v.getSelectedValuesList.nn.asScala.asInstanceOf",
    "def selectionEmpty: Boolean = v.isSelectionEmpty"
  ),
  initExtra = """
    |val lsl: ListSelectionListener = (evt) => sc.update{
    |  val vc = summon[VarContext]
    |  vc.swingPropertyUpdated(ops.selectedIndex(v), v.getSelectedIndex)
    |  vc.swingPropertyUpdated(ops.selectedIndices(v), v.getSelectedIndices.nn)
    |  vc.swingPropertyUpdated(ops.selectedIndices(v), v.getSelectedIndices.nn)
    |}
    |v.addListSelectionListener(lsl)
    """.stripMargin.split("\n").asInstanceOf[Array[String]].toIndexedSeq,
  companionObjectExtras = Seq(
  )
)

////////////////////////////////////////////////////////////////////////////
// table
////////////////////////////////////////////////////////////////////////////

case object TableView extends NodeDescr(
  "TableView",
  "javax.swing.JTable",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.TableUI"),
    SwingProp("autoCreateColumnsFromModel", "Boolean", "_.getAutoCreateColumnsFromModel", "_.setAutoCreateColumnsFromModel(_)"),
    SwingProp("autoCreateRowSorter", "Boolean", "_.getAutoCreateRowSorter", "_.setAutoCreateRowSorter(_)"),
    SwingProp("autoResizeMode", "Int"),
    SwingProp("cellEditor", "javax.swing.table.TableCellEditor"),
    SwingProp("cellSelectionEnabled", "Boolean", "_.getCellSelectionEnabled", "_.setCellSelectionEnabled(_)"),
    SwingProp("columnModel", "javax.swing.table.TableColumnModel"),
    SwingProp("columnSelectionAllowed", "Boolean", "_.getColumnSelectionAllowed", "_.setColumnSelectionAllowed(_)"),
    SwingProp("dragEnabled", "Boolean", "_.getDragEnabled", "_.setDragEnabled(_)"),
    SwingProp("dropMode", "javax.swing.DropMode | Null"),
    SwingProp("editingColumn", "Int"),
    SwingProp("editingRow", "Int"),
    SwingProp("fillsViewportHeight", "Boolean", "_.getFillsViewportHeight", "_.setFillsViewportHeight(_)"),
    SwingProp("gridColor", "java.awt.Color | Null"),
    SwingProp("intercellSpacing", "(Double, Double) | Null",
      "{n => val d = n.getIntercellSpacing; if (d != null) (d.getWidth, d.getHeight) else null}",
      "{(n, d) => n.setIntercellSpacing(if (d == null) null else java.awt.Dimension(d._1.toInt, d._2.toInt))}"),
    SwingProp("model", "javax.swing.table.TableModel"),
    SwingProp("preferredScrollableViewportSize", "(Double, Double) | Null",
      "{n => val d = n.getPreferredScrollableViewportSize; if (d != null) (d.getWidth, d.getHeight) else null}",
      "{(n, d) => n.setPreferredScrollableViewportSize(if (d == null) null else java.awt.Dimension(d._1.toInt, d._2.toInt))}"),
    SwingProp("rowHeight", "Int"),
    SwingProp("rowMargin", "Int"),
    SwingProp("rowSelectionAllowed", "Boolean", "_.getRowSelectionAllowed", "_.setRowSelectionAllowed(_)"),
    SwingProp("rowSorter", "javax.swing.RowSorter[_ <: javax.swing.table.TableModel] | Null"),
    SwingProp("selectionBackground", "java.awt.Color | Null"),
    SwingProp("selectionForeground", "java.awt.Color | Null"),
    SwingProp("selectionModel", "javax.swing.ListSelectionModel"),
    SwingProp("showHorizontalLines", "Boolean", "_.getShowHorizontalLines", "_.setShowHorizontalLines(_)"),
    SwingProp("showVerticalLines", "Boolean", "_.getShowVerticalLines", "_.setShowVerticalLines(_)"),
    SwingProp("surrendersFocusOnKeystroke", "Boolean", "_.getSurrendersFocusOnKeystroke", "_.setSurrendersFocusOnKeystroke(_)"),
    SwingProp("tableHeader", "javax.swing.table.JTableHeader | Null"),
    SwingProp("updateSelectionOnSort", "Boolean", "_.getUpdateSelectionOnSort", "_.setUpdateSelectionOnSort(_)"),
  ),
  opsExtra = Seq(
    "def columnCount: Int = v.getColumnCount",
    "def dropLocation: javax.swing.JTable.DropLocation | Null = v.getDropLocation",
    "def editing: Boolean = v.isEditing",
    "def editorComponent: java.awt.Component | Null = v.getEditorComponent",
    "def rowCount: Int = v.getRowCount",
    "def scrollableTracksViewportHeight: Boolean = v.getScrollableTracksViewportHeight",
    "def scrollableTracksViewportWidth: Boolean = v.getScrollableTracksViewportWidth",
    "def selectedColumn: Int = v.getSelectedColumn",
    "def selectedColumnCount: Int = v.getSelectedColumnCount",
    "def selectedColumns: Array[Int] = v.getSelectedColumns.nn",
    "def selectedRow: Int = v.getSelectedRow",
    "def selectedRowCount: Int = v.getSelectedRowCount",
    "def selectedRows: Array[Int] = v.getSelectedRows.nn"
  ),
)

////////////////////////////////////////////////////////////////////////////
// scroll pane
////////////////////////////////////////////////////////////////////////////

case object ScrollPane extends NodeDescr(
  "ScrollPane",
  "javax.swing.JScrollPane",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.ScrollPaneUI"),
    SwingProp("columnHeader", "javax.swing.JViewport | Null"),
    SwingProp("content", "Node | Null", "_.getViewport.nn.getView.asInstanceOf[Node | Null]", "(s, n) => s.setViewportView(if (n != null) n.unwrap else null)"),
    SwingProp("horizontalScrollBar", "javax.swing.JScrollBar"),
    SwingProp("horizontalScrollBarPolicy", "Int"),
    SwingProp("rowHeader", "javax.swing.JViewport | Null"),
    SwingProp("verticalScrollBar", "javax.swing.JScrollBar"),
    SwingProp("verticalScrollBarPolicy", "Int"),
    SwingProp("viewport", "javax.swing.JViewport"),
    SwingProp("viewportBorder", "javax.swing.border.Border | Null"),
    SwingProp("wheelScrollingEnabled", "Boolean"),

    SwingProp("topLeftCorner", "Node | Null", "_.getCorner(ScrollPaneConstants.UPPER_LEFT_CORNER).asInstanceOf[Node | Null]", "(s, n) => s.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, if (n != null) n.unwrap else null)"),
    SwingProp("topRightCorner", "Node | Null", "_.getCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER).asInstanceOf[Node | Null]", "(s, n) => s.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, if (n != null) n.unwrap else null)"),
    SwingProp("botLeftCorner", "Node | Null", "_.getCorner(ScrollPaneConstants.LOWER_LEFT_CORNER).asInstanceOf[Node | Null]", "(s, n) => s.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, if (n != null) n.unwrap else null)"),
    SwingProp("botRightCorner", "Node | Null", "_.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER).asInstanceOf[Node | Null]", "(s, n) => s.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, if (n != null) n.unwrap else null)"),
  ),
  opsExtra = Seq(
    "def viewportBorderBounds: java.awt.Rectangle = v.getViewportBorderBounds.nn"
  )
)

case object ScrollBar extends NodeDescr(
  "ScrollBar",
  "javax.swing.JScrollBar",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.ScrollBarUI"),
    SwingProp("blockIncrement", "Int"),
    SwingProp("maximum", "Int"),
    SwingProp("minimum", "Int"),
    SwingProp("model", "javax.swing.BoundedRangeModel"),
    SwingProp("orientation", "Int"),
    SwingProp("unitIncrement", "Int"),
    SwingProp("value", "Int"),
    SwingProp("valueIsAdjusting", "Boolean", "_.getValueIsAdjusting", "_.setValueIsAdjusting(_)"),
    SwingProp("visibleAmount", "Int"),
  ),
  opsExtra = Seq(
    "def adjustmentListeners: Array[java.awt.event.AdjustmentListener] = v.getAdjustmentListeners.asInstanceOf"
  )
)

////////////////////////////////////////////////////////////////////////////
// split pane
////////////////////////////////////////////////////////////////////////////

case object SplitPane extends NodeDescr(
  "SplitPane",
  "javax.swing.JSplitPane",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.SplitPaneUI"),
    SwingProp("continuousLayout", "Boolean"),
    SwingProp("dividerLocation", "Int"),
    SwingProp("dividerSize", "Int"),
    SwingProp("horizontal", "Boolean", "_.getOrientation == JSplitPane.HORIZONTAL_SPLIT", "(s, h) => s.setOrientation(if (h) JSplitPane.HORIZONTAL_SPLIT else JSplitPane.VERTICAL_SPLIT)"),
    SwingProp("lastDividerLocation", "Int"),
    SwingProp("componentA", "Component | Null", "_.getLeftComponent.asInstanceOf", "(p, c) => p.setLeftComponent(c.?(_.unwrap))"),
    SwingProp("componentB", "Component | Null", "_.getRightComponent.asInstanceOf", "(p, c) => p.setRightComponent(c.?(_.unwrap))"),
    SwingProp("oneTouchExpandable", "Boolean"),
    SwingProp("resizeWeight", "Double"),
),
  opsExtra = Seq(
    "def maximumDividerLocation: Int = v.getMaximumDividerLocation",
    "def minimumDividerLocation: Int = v.getMinimumDividerLocation"
  )
)

////////////////////////////////////////////////////////////////////////////
// tabbed pane
////////////////////////////////////////////////////////////////////////////

case object TabbedPane extends NodeDescr(
  "TabbedPane",
  "javax.swing.JTabbedPane",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.TabbedPaneUI"),
    SwingProp("model", "javax.swing.SingleSelectionModel"),
    SwingProp("selectedComponent", "Option[Node]", "_.getSelectedComponent.toOption.asInstanceOf", "(p, n) => p.setSelectedComponent(n.fold(null)(_.unwrap))"),
    SwingProp("selectedIndex", "Int"),
    SwingProp("tabLayoutPolicy", "Int"),
    SwingProp("tabPlacement", "Int"),
    VarProp("tabs", "ObsBuffer[Tab]", "ObsBuffer()", eagerEvaluation = true)
  ),
  opsExtra = Seq(
    "def changeListeners: Array[javax.swing.event.ChangeListener] = v.getChangeListeners.asInstanceOf",
    "def tabCount: Int = v.getTabCount",
    "def tabRunCount: Int = v.getTabRunCount"
  ),
  initExtra = """
    |//helper methods to add tabs and react to their changes
    |def addTab(t: Tab): Unit = sc.update {
    |  v.addTab(t.title(), t.icon(), t.content().unwrap, t.tip())
    |  v.setEnabledAt(v.getTabCount - 1, t.enabled())
    |  v.setTabComponentAt(v.getTabCount - 1, t.tabNode().?(_.unwrap))
    |}
    |def removeTab(at: Int): Unit = v.removeTabAt(at)
    |def insertTab(t: Tab, at: Int) = sc.update { 
    |  v.insertTab(t.title(), t.icon(), t.content().unwrap, t.tip(), at)
    |  v.setEnabledAt(at, t.enabled())
    |  v.setTabComponentAt(at, t.tabNode().?(_.unwrap))
    |}
    |def replaceTab(oldTab: Tab, withTab: Tab) = sc.update {
    |  val at = v.indexOfTabComponent(oldTab.content().?(_.unwrap))
    |  v.setTitleAt(at, withTab.title())
    |  v.setIconAt(at, withTab.icon())
    |  v.setComponentAt(at, withTab.content().unwrap)
    |  v.setToolTipTextAt(at, withTab.tip())
    |  v.setEnabledAt(at, withTab.enabled())
    |  v.setTabComponentAt(at, withTab.tabNode().?(_.unwrap))
    |}

    |val bufferListener: PartialFunction[ObsBuffer.Event[Tab], Unit] = {
    |  case ObsBuffer.Event.Added(elems) => elems foreach addTab
    |  case ObsBuffer.Event.Inserted(elems, at) => elems.zipWithIndex foreach ((e, i) => insertTab(e, at + i))
    |  case ObsBuffer.Event.Removed(elems, at) => elems foreach (_ => removeTab(at))
    |  case ObsBuffer.Event.Replaced(oldElem, newElem) => replaceTab(oldElem, newElem)
    |  case ObsBuffer.Event.Cleared => v.removeAll()
    |}

    |object LocatedTab {
    |  def unapply(t: Any)(given VarContext): Option[(Tab, Int)] = t match {
    |    case t: Tab =>  v.tabs().indexOf(t) match {
    |      case -1 => None
    |      case i => Some(t -> i)
    |    }
    |    case _ => None
    |  }
    |}

    |sc.update {
    |  sc.varUpdates := EventIterator.foreach {
    |    case v.tabs(old, newv) =>
    |      old.foreach(_.observers -= bufferListener)
    |      newv.observers += bufferListener

    |      //replace all tabs
    |      v.removeAll()
    |      newv foreach addTab

    |    //react to updates to the vars of the Tabs
    |    case Tab.Title.generic(LocatedTab(t, at), oldv, newv) => v.setTitleAt(at, newv)
    |    case Tab.Icon.generic(LocatedTab(t, at), oldv, newv) => v.setIconAt(at, newv)
    |    case Tab.Content.generic(LocatedTab(t, at), oldv, newv) => v.setComponentAt(at, newv.unwrap)
    |    case Tab.Tip.generic(LocatedTab(t, at), oldv, newv) => v.setToolTipTextAt(at, newv)
    |    case Tab.Enabled.generic(LocatedTab(t, at), oldv, newv) => v.setEnabledAt(at, newv)
    |    case Tab.TabNode.generic(LocatedTab(t, at), oldv, newv) => v.setTabComponentAt(at, newv.?(_.unwrap))

    |    case _ => 
    |  }

    |  val cl: ChangeListener = evt => sc.update{
    |    val vc = summon[VarContext]
    |    vc.swingPropertyUpdated(v.selectedComponent, v.getSelectedComponent.toOption.asInstanceOf)
    |    vc.swingPropertyUpdated(v.selectedIndex, v.getSelectedIndex)
    |  }
    |  v.addChangeListener(cl)
    |}
    """.trim.nn.stripMargin.split("\n").asInstanceOf[Array[String]].toIndexedSeq,
)

////////////////////////////////////////////////////////////////////////////
// combo box
////////////////////////////////////////////////////////////////////////////

case object Combobox extends NodeDescr(
  "ComboBox",
  "javax.swing.JComboBox[_ <: E]",
  tpeParams = Seq("+E"),
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.ComboBoxUI"),
    SwingProp("action", "javax.swing.Action | Null"),
    SwingProp("actionCommand", "java.lang.String | Null"),
    SwingProp("editable", "Boolean"),
    SwingProp("editor", "javax.swing.ComboBoxEditor | Null"),
    SwingProp("keySelectionManager", "javax.swing.JComboBox.KeySelectionManager | Null"),
    SwingProp("lightWeightPopupEnabled", "Boolean"),
    SwingProp("maximumRowCount", "Int"),
    SwingProp("model", "javax.swing.ComboBoxModel[E]", "_.getModel.nn", "(c, m) => c.setModel(m.asInstanceOf)", overrideTpeInStaticPos = Some("javax.swing.ComboBoxModel[?]")),
    SwingProp("popupVisible", "Boolean"),
    SwingProp("prototypeDisplayValue", "E", "_.getPrototypeDisplayValue.asInstanceOf", "(c, v) => c.setPrototypeDisplayValue(v.asInstanceOf)", overrideTpeInStaticPos = Some("Any")),
    SwingProp("renderer", "javax.swing.ListCellRenderer[_ >: E]", "_.getRenderer.nn", "(c, r) => c.setRenderer(r.asInstanceOf)", overrideTpeInStaticPos = Some("javax.swing.ListCellRenderer[?]")),
    SwingProp("selectedIndex", "Int"),
    SwingProp("selectedItem", "Option[E]", "{c => val v = c.getSelectedItem; if (v != null) Some(v) else None}", "_.setSelectedItem(_)", overrideTpeInStaticPos = Some("Option[?]")),
    SwingProp("items", "Seq[E]", "{c => val m = c.getModel.nn; (0 until m.getSize).map(m.getElementAt(_).asInstanceOf)}",
      "(c, i) => c.setModel(DefaultComboBoxModel(i.toArray.asInstanceOf[Array[AnyRef | UncheckedNull]]).asInstanceOf)", overrideTpeInStaticPos = Some("Seq[Any]")),
  ),
  opsExtra = Seq(
    "def actionListeners: Array[java.awt.event.ActionListener] = v.getActionListeners.asInstanceOf",
    "def itemCount: Int = v.getItemCount",
    "def itemListeners: Array[java.awt.event.ItemListener] = v.getItemListeners.asInstanceOf",
    "def popupMenuListeners: Array[javax.swing.event.PopupMenuListener] = v.getPopupMenuListeners.asInstanceOf",
    "def selectedObjects: Seq[E] = v.getSelectedObjects.nn.toIndexedSeq.asInstanceOf"
  ),
  initExtra = """
    |val il: ItemListener = evt => sc.update {
    |  val vc = summon[VarContext]
    |  vc.swingPropertyUpdated(ops.selectedIndex(v), v.getSelectedIndex)
    |  val si = v.getSelectedItem.asInstanceOf[E | Null]
    |  vc.swingPropertyUpdated(ops.selectedItem(v), if (si == null) None else Some(si))
    |}
    |v.addItemListener(il)
  """.trim.nn.stripMargin.split("\n").asInstanceOf[Array[String]].toIndexedSeq
)

////////////////////////////////////////////////////////////////////////////
// separator
////////////////////////////////////////////////////////////////////////////

case object Separator extends NodeDescr(
  "Separator",
  "javax.swing.JSeparator",
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.SeparatorUI"),
    SwingProp("horizontal", "Boolean", "_.getOrientation == SwingConstants.HORIZONTAL", "(s, h) => s.setOrientation(if (h) SwingConstants.HORIZONTAL else SwingConstants.VERTICAL)")
  )
)

////////////////////////////////////////////////////////////////////////////
// spinner
////////////////////////////////////////////////////////////////////////////

case object Spinner extends NodeDescr(
  "Spinner",
  "javax.swing.JSpinner",
  tpeParams = Seq("+E"),
  parents = Seq(Component),
  props = Seq(
    SwingProp("UI", "javax.swing.plaf.SpinnerUI"),
    SwingProp("editor", "javax.swing.JComponent | Null"),
    SwingProp("model", "javax.swing.SpinnerModel"),
    SwingProp("value", "E", "_.getValue", "_.setValue(_)", overrideTpeInStaticPos = Some("Any")),
  ),
  initExtra = """
    |val cl: ChangeListener = evt => sc.update(summon[VarContext].swingPropertyUpdated(ops.value(v), v.getValue.asInstanceOf))
    |v.addChangeListener(cl)
    """.trim.nn.stripMargin.split("\n").asInstanceOf[Array[String]].toIndexedSeq
)

////////////////////////////////////////////////////////////////////////////
// main function
////////////////////////////////////////////////////////////////////////////
def genCode(n: NodeDescr): String = {

  val tpeParamsDecls = if (n.tpeParams.nonEmpty) n.tpeParams.mkString("[", ", ", "]") else ""
  val tpeParams = tpeParamsDecls.replaceAll("[+-]", "").nn

  val emptyTpeParams = tpeParams.replaceAll(raw"\w|_ [><]: \w+", "Any")

  def propDecl(p: Property) = p.visibility.map(s => s + " ").getOrElse("") + (p match {
    case p@SwingProp(name, tpe, getter, setter, _, _) => 
      s"""val ${name.capitalize}: SwingVar.Aux[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}] = SwingVar[${n.name}$emptyTpeParams, ${p.tpeInStaticPos}]("$name", $getter, $setter)"""
    case p@VarProp(name, tpe, initValue, _, _, eval) => 
      s"""val ${name.capitalize}: Var[${p.tpeInStaticPos}] = Var[${p.tpeInStaticPos}]("$name", $initValue, $eval)"""
  })

  val seenVars = collection.mutable.Set.empty[String]
  val allVars: Vector[(NodeDescr, Property)] = Iterator.unfold(Seq(n)) {
    case Seq() => None
    case parents => 
      val allParentVars = parents.flatMap(p => p.props.filterNot(prop => seenVars(prop.name)).map(p -> _))
      seenVars ++= allParentVars.map(_._2.name)
      Some(allParentVars -> parents.flatMap(_.parents))
  }.flatten.toVector.sortBy(_._2.name)

  val initializers = if (!n.isAbstract) 
      s"""def uninitialized$tpeParams(${n.uninitExtraParams.map(t => s"${t.name}: ${t.tpe}").mkString(", ")}): ${n.name}$tpeParams = {
         |  val res = ${n.underlying.replaceAll(raw"_ <: ", "")}(${n.uninitExtraParams.map(_.passAs).mkString(", ")}).asInstanceOf[${n.name}$tpeParams]
         |  ${n.uninitExtra.mkString("\n    ")}
         |  res
         |}
         |
         |def apply$tpeParams(
         |  ${if (n.uninitExtraParams.nonEmpty) n.uninitExtraParams.map(t => s"${t._1}: ${t._2}").mkString(", ") + "," else ""}
         |  ${allVars.map(v => s"${v._2.name}: Opt[Binding[${v._2.tpe}]] = UnsetParam").mkString(",\n  ")}
         |): (given Scenegraph) => VarContextAction[${n.name}$tpeParams] = {
         |  val res = uninitialized$tpeParams()
         |  ${n.name}.init(res)
         |  ${allVars.map(v => s"ifSet(${v._2.name}, ${v._1.name}.ops.${v._2.name}(res) := _)").mkString("\n  ")}
         |  res
         |}
         |
      """.stripMargin.trim.split("\n").asInstanceOf[Array[String]].toSeq
    else Seq.empty 

  val sortedProps = n.props.sortBy(_.name)
  val sortedEmitters = n.emitters.sortBy(_.name)

  s"""opaque type ${n.name}$tpeParamsDecls ${if (n.parents.nonEmpty) n.parents.mkString("<: ", " & ", "") else ""} = ${(n.underlying +: n.parents).mkString(" & ")}
     |object ${n.name} extends VarsMap {
     |  ${sortedProps.map(propDecl).mkString("\n  ")}
     |
     |  ${sortedEmitters.map(e => s"val ${e.name.capitalize} = Emitter[${e.tpe}]()").mkString("\n  ")}
     |
     |  extension ops on $tpeParams(v: ${n.name}$tpeParams) {
     |    ${sortedProps.map(p => s"def ${p.name}: Var.Aux[${p.tpe}, v.type] = ${n.name}.${p.name.capitalize}.asInstanceOf[Var.Aux[${p.tpe}, v.type]]").mkString("\n    ")}

     |    ${sortedEmitters.map(e => s"def ${e.name}: Emitter.Aux[${e.tpe}, v.type] = ${n.name}.${e.name.capitalize}.forInstance(v)").mkString("\n    ")}

     |    ${n.opsExtra.mkString("\n    ")}
     |    def unwrap: ${n.underlying} = v
     |  }
     |
     |  def apply$tpeParams(v: ${n.underlying}) = v.asInstanceOf[${n.name}$tpeParams]
     |
     |  def init$tpeParams(v: ${n.name}$tpeParams): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
     |    ${n.parents.headOption.map(p => s"$p.init(v)").getOrElse("")}
     |    ${if (sortedEmitters.nonEmpty) {
              val registeredEmitters = sortedEmitters.map(e => s"  ctx.register(v.${e.name})")
              (("sc.update {" +: "  val ctx = summon[Emitter.Context]" +: registeredEmitters) :+ "}").mkString("\n    ")
            } else ""
          }
     |    ${if (n.addsPropertySwingListener) "v.addPropertyChangeListener(varsPropertyListener(v))" else ""}
     |    ${n.initExtra.mkString("\n    ")}
     |    ${sortedEmitters.flatMap(_.initializer).mkString("\n    ")}
     |  }
     |  ${initializers.mkString("\n  ")}
     |  ${n.companionObjectExtras.mkString("\n  ")}
     |}
  """.stripMargin.trim.nn
}

@main def run(): Unit = {
  val dir = File("src/main/scala/guarana/swing/")
  val preamble = """
    //AUTOGENERATED FILE, DO NOT MODIFY

    |package guarana.swing

    |import language.implicitConversions
    |import java.awt.{Component => _, TextComponent => _, TextField => _, _}
    |import java.awt.event._
    |import javax.swing._
    |import javax.swing.event._
    |import guarana.swing.util._
    |import scala.jdk.CollectionConverters._
    |import scala.util.chaining._
    """.stripMargin.trim.nn
  // dest.append(preamble).append("\n\n")

  for (node <- Seq(
    Node,
    Component,
    WindowBase,
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
    Slider,
    ProgressBar,
    ListView,
    TableView,
    ScrollPane,
    ScrollBar,
    SplitPane,
    Combobox,
    Separator,
    Spinner,
    TabbedPane,
  )) {
    val dest = dir / s"${node.name}.scala"
    dest.clear().append(preamble).append("\n\n")
    dest.append(genCode(node))
  }
}