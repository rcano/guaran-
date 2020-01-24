package guarana.swing
package codegen

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

abstract class NodeDescr(
  val name: String,
  val underlying: String,
  val parents: Seq[NodeDescr] = Seq.empty,
  val props: Seq[Property] = Seq.empty,
  val opsExtra: Seq[String] = Seq.empty,
  val initExtra: Seq[String] = Seq.empty,
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
    SwingProp("autoscrolls", "Boolean"),
    SwingProp("border", "javax.swing.border.Border | Null"),
    SwingProp("componentPopupMenu", "javax.swing.JPopupMenu | Null"),
    SwingProp("debugGraphicsOptions", "Int"),
    SwingProp("doubleBuffered", "Boolean"),
    SwingProp("inheritsPopupMenu", "Boolean"),
    SwingProp("inputVerifier", "javax.swing.InputVerifier | Null"),
    SwingProp("opaque", "Boolean"),
    SwingProp("requestFocusEnabled", "Boolean"),
    SwingProp("toolTipText", "String | Null"),
    SwingProp("transferHandler", "javax.swing.TransferHandler | Null"),
    SwingProp("verifyInputWhenFocusTarget", "Boolean"),
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
    SwingProp("dragEnabled", "Boolean"),
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
    SwingProp("lineWrap", "Boolean"),
    SwingProp("rows", "Int"),
    SwingProp("tabSize", "Int"),
    SwingProp("wrapStyleWord", "Boolean"),
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

////////////////////////////////////////////////////////////////////////////
// Buttons
////////////////////////////////////////////////////////////////////////////


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
      s"""def uninitialized(): ${n.name} = {
         |  val res = ${n.underlying}().asInstanceOf[${n.name}]
         |  ${n.uninitExtra.mkString("\n    ")}
         |  res
         |}
         |
         |def apply(
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

  s"""opaque type ${n.name} ${if (n.parents.nonEmpty) n.parents.mkString("<: ", " & ", "") else ""} = ${(n.underlying +: n.parents).mkString(" & ")}
     |object ${n.name} extends VarsMap {
     |  ${sortedProps.map(propDecl).mkString("\n  ")}
     |
     |  given ops: (v: ${n.name}) extended with {
     |    ${sortedProps.map(p => s"def ${p.name} = ${n.name}.${p.name.capitalize}.forInstance(v)").mkString("\n    ")}
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
     |  }
     |  ${initializers.mkString("\n  ")}
     |}
  """.stripMargin.trim.nn
}

@main def run(): Unit = {
  println(genCode(Vbox))
}