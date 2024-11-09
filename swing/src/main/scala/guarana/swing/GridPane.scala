//AUTOGENERATED FILE, DO NOT MODIFY

package guarana
package swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, *}
import java.awt.event.*
import javax.swing.{Action => _, *}
import javax.swing.event.*
import guarana.util.*
import guarana.swing.util.*
import scala.jdk.CollectionConverters.*
import scala.util.chaining.*

opaque type GridPane <: Pane  = javax.swing.JPanel & Pane
object GridPane extends VarsMap {
  val AutoCreateContainerGaps: SwingVar.Aux[GridPane, Boolean] = SwingVar[GridPane, Boolean]("autoCreateContainerGaps", _.getLayout.asInstanceOf[GroupLayout].getAutoCreateContainerGaps(), _.getLayout.asInstanceOf[GroupLayout].setAutoCreateContainerGaps(_))
  val Hgap: Var[Double] = Var[Double]("hgap", 0.0, false)
  private val LayoutVar: SwingVar.Aux[GridPane, Unit] = SwingVar[GridPane, Unit]("layoutVar", _ => (), (_, _) => ())
  val Rows: Var[Seq[Seq[Node]]] = Var[Seq[Seq[Node]]]("rows", Seq.empty, false)
  val Vgap: Var[Double] = Var[Double]("vgap", 0.0, false)
  final val LayoutDefaultSize = GroupLayout.DEFAULT_SIZE
  final val LayoutPreferredSize = GroupLayout.PREFERRED_SIZE
  val LayoutMin: Var[LayoutDefaultSize.type | LayoutPreferredSize.type | Int] = Var.autoName(LayoutDefaultSize)
  val LayoutPref: Var[LayoutDefaultSize.type | LayoutPreferredSize.type | Int] = Var.autoName(LayoutDefaultSize)
  val LayoutMax: Var[LayoutDefaultSize.type | LayoutPreferredSize.type | Int] = Var.autoName(LayoutDefaultSize)
  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: GridPane) {
      def autoCreateContainerGaps: Var.Aux[Boolean, v.type] = GridPane.AutoCreateContainerGaps.asInstanceOf[Var.Aux[Boolean, v.type]]
      def hgap: Var.Aux[Double, v.type] = GridPane.Hgap.asInstanceOf[Var.Aux[Double, v.type]]
      def rows: Var.Aux[Seq[Seq[Node]], v.type] = GridPane.Rows.asInstanceOf[Var.Aux[Seq[Seq[Node]], v.type]]
      def vgap: Var.Aux[Double, v.type] = GridPane.Vgap.asInstanceOf[Var.Aux[Double, v.type]]

      

      
      def unwrap: javax.swing.JPanel = v
    }
  }

  def wrap(v: javax.swing.JPanel) = v.asInstanceOf[GridPane]

  def init(v: GridPane): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Pane.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    sc.update(LayoutVar.forInstance(v) := Binding.dyn {
    val rows = v.rows()
    val hgap = v.hgap().toInt
    val vgap = v.vgap().toInt
    
    val layout = GroupLayout(v)
    v.setLayout(layout)

    val hgroup = layout.createSequentialGroup().nn
    val vgroup = layout.createSequentialGroup().nn
    
    val hSeqGroups = collection.mutable.Map.empty[Int, GroupLayout#ParallelGroup]
    val vSeqGroups = collection.mutable.Map.empty[Int, GroupLayout#ParallelGroup]
    
    val rowSize = rows.size
    
    for {
      (row, rowIdx) <- rows.zipWithIndex
      colSize = row.length
      (node, colIdx) <- row.zipWithIndex
    } {
      val nodeLayoutMin = LayoutMin.forInstance(node)
      val nodeLayoutPref = LayoutPref.forInstance(node)
      val nodeLayoutMax = LayoutMax.forInstance(node)

      hSeqGroups
        .getOrElseUpdate(colIdx, layout.createParallelGroup().nn.tap { g => 
          hgroup.addGroup(g)
          if (hgap > 0 && colIdx < colSize - 1) hgroup.addGap(hgap)
        })
        .addComponent(node.unwrap, nodeLayoutMin(), nodeLayoutPref(), nodeLayoutMax())
    
      vSeqGroups
        .getOrElseUpdate(rowIdx, layout.createBaselineGroup(true, true).nn.tap { g => 
          vgroup.addGroup(g)
          if (vgap > 0 && rowIdx < rowSize - 1) vgroup.addGap(vgap)
        })
        .addComponent(node.unwrap, nodeLayoutMin(), nodeLayoutPref(), nodeLayoutMax())
    }
    
    layout.setHorizontalGroup(hgroup)
    layout.setVerticalGroup(vgroup)
    })
    
  }
  def uninitialized(): GridPane = {
    val res = javax.swing.JPanel().asInstanceOf[GridPane]
    res.asInstanceOf[JPanel].setLayout(GroupLayout(res))
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.PanelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoCreateContainerGaps: Opt[Binding[Boolean]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    hgap: Opt[Binding[Double]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rows: Opt[Binding[Seq[Seq[Node]]]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    vgap: Opt[Binding[Double]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[GridPane] = {
    val res = uninitialized()
    GridPane.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoCreateContainerGaps, GridPane.ops.autoCreateContainerGaps(res) := _)
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
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hgap, GridPane.ops.hgap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rows, GridPane.ops.rows(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(vgap, GridPane.ops.vgap(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}