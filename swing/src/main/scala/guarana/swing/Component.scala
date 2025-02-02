//AUTOGENERATED FILE, DO NOT MODIFY

package guarana
package swing

import language.implicitConversions
import java.awt.{Component => _, Menu => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, *}
import java.awt.event.*
import javax.swing.{Action => _, *}
import javax.swing.event.*
import guarana.util.*
import guarana.swing.util.*
import scala.jdk.CollectionConverters.*
import scala.util.chaining.*

opaque type Component <: Node  = javax.swing.JComponent & Node
object Component extends VarsMap {
  val ActionMap: SwingVar.Aux[Component, javax.swing.ActionMap] = SwingVar[Component, javax.swing.ActionMap]("actionMap", _.getActionMap.nn, _.setActionMap(_))
  val AlignmentX: SwingVar.Aux[Component, Float] = SwingVar[Component, Float]("alignmentX", _.getAlignmentX, _.setAlignmentX(_))
  val AlignmentY: SwingVar.Aux[Component, Float] = SwingVar[Component, Float]("alignmentY", _.getAlignmentY, _.setAlignmentY(_))
  val Autoscrolls: SwingVar.Aux[Component, Boolean] = SwingVar[Component, Boolean]("autoscrolls", _.getAutoscrolls, _.setAutoscrolls(_))
  val Border: SwingVar.Aux[Component, javax.swing.border.Border | Null] = SwingVar[Component, javax.swing.border.Border | Null]("border", _.getBorder, _.setBorder(_))
  val ComponentPopupMenu: SwingVar.Aux[Component, PopupMenu | Null] = SwingVar[Component, PopupMenu | Null]("componentPopupMenu", _.getComponentPopupMenu.asInstanceOf, (c, p) => c.setComponentPopupMenu(p.asInstanceOf))
  val DebugGraphicsOptions: SwingVar.Aux[Component, Int] = SwingVar[Component, Int]("debugGraphicsOptions", _.getDebugGraphicsOptions, _.setDebugGraphicsOptions(_))
  val DoubleBuffered: SwingVar.Aux[Component, Boolean] = SwingVar[Component, Boolean]("doubleBuffered", _.isDoubleBuffered, _.setDoubleBuffered(_))
  val InheritsPopupMenu: SwingVar.Aux[Component, Boolean] = SwingVar[Component, Boolean]("inheritsPopupMenu", _.getInheritsPopupMenu, _.setInheritsPopupMenu(_))
  val InputVerifier: SwingVar.Aux[Component, javax.swing.InputVerifier | Null] = SwingVar[Component, javax.swing.InputVerifier | Null]("inputVerifier", _.getInputVerifier, _.setInputVerifier(_))
  val Opaque: SwingVar.Aux[Component, Boolean] = SwingVar[Component, Boolean]("opaque", _.isOpaque, _.setOpaque(_))
  val RequestFocusEnabled: SwingVar.Aux[Component, Boolean] = SwingVar[Component, Boolean]("requestFocusEnabled", _.isRequestFocusEnabled, _.setRequestFocusEnabled(_))
  val ToolTipText: SwingVar.Aux[Component, String | Null] = SwingVar[Component, String | Null]("toolTipText", _.getToolTipText, _.setToolTipText(_))
  val TransferHandler: SwingVar.Aux[Component, javax.swing.TransferHandler | Null] = SwingVar[Component, javax.swing.TransferHandler | Null]("transferHandler", _.getTransferHandler, _.setTransferHandler(_))
  val VerifyInputWhenFocusTarget: SwingVar.Aux[Component, Boolean] = SwingVar[Component, Boolean]("verifyInputWhenFocusTarget", _.getVerifyInputWhenFocusTarget, _.setVerifyInputWhenFocusTarget(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Component) {
      def actionMap: Var.Aux[javax.swing.ActionMap, v.type] = Component.ActionMap.asInstanceOf[Var.Aux[javax.swing.ActionMap, v.type]]
      def alignmentX: Var.Aux[Float, v.type] = Component.AlignmentX.asInstanceOf[Var.Aux[Float, v.type]]
      def alignmentY: Var.Aux[Float, v.type] = Component.AlignmentY.asInstanceOf[Var.Aux[Float, v.type]]
      def autoscrolls: Var.Aux[Boolean, v.type] = Component.Autoscrolls.asInstanceOf[Var.Aux[Boolean, v.type]]
      def border: Var.Aux[javax.swing.border.Border | Null, v.type] = Component.Border.asInstanceOf[Var.Aux[javax.swing.border.Border | Null, v.type]]
      def componentPopupMenu: Var.Aux[PopupMenu | Null, v.type] = Component.ComponentPopupMenu.asInstanceOf[Var.Aux[PopupMenu | Null, v.type]]
      def debugGraphicsOptions: Var.Aux[Int, v.type] = Component.DebugGraphicsOptions.asInstanceOf[Var.Aux[Int, v.type]]
      def doubleBuffered: Var.Aux[Boolean, v.type] = Component.DoubleBuffered.asInstanceOf[Var.Aux[Boolean, v.type]]
      def inheritsPopupMenu: Var.Aux[Boolean, v.type] = Component.InheritsPopupMenu.asInstanceOf[Var.Aux[Boolean, v.type]]
      def inputVerifier: Var.Aux[javax.swing.InputVerifier | Null, v.type] = Component.InputVerifier.asInstanceOf[Var.Aux[javax.swing.InputVerifier | Null, v.type]]
      def opaque: Var.Aux[Boolean, v.type] = Component.Opaque.asInstanceOf[Var.Aux[Boolean, v.type]]
      def requestFocusEnabled: Var.Aux[Boolean, v.type] = Component.RequestFocusEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def toolTipText: Var.Aux[String | Null, v.type] = Component.ToolTipText.asInstanceOf[Var.Aux[String | Null, v.type]]
      def transferHandler: Var.Aux[javax.swing.TransferHandler | Null, v.type] = Component.TransferHandler.asInstanceOf[Var.Aux[javax.swing.TransferHandler | Null, v.type]]
      def verifyInputWhenFocusTarget: Var.Aux[Boolean, v.type] = Component.VerifyInputWhenFocusTarget.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def UI = v.getUI
      def UIClassID = v.getUIClassID
      def ancestorListeners = v.getAncestorListeners
      def inputMap = v.getInputMap
      def optimizedDrawingEnabled = v.isOptimizedDrawingEnabled
      def paintingForPrint = v.isPaintingForPrint
      def paintingTile = v.isPaintingTile
      def registeredKeyStrokes = v.getRegisteredKeyStrokes
      def rootPane = v.getRootPane
      def topLevelAncestor = v.getTopLevelAncestor
      def vetoableChangeListeners = v.getVetoableChangeListeners
      def visibleRect = v.getVisibleRect
      def uiPrefSize: (Double, Double) | Null = ops.UI(v).?(_.getPreferredSize(v)).?(d => (d.getWidth, d.getHeight))
      def insets = v.getInsets
      def children: Seq[Node] = (0 until v.getComponentCount).map(i => v.getComponent(i).asInstanceOf[Node])
      def unwrap: javax.swing.JComponent = v
    }
  }

  def wrap(v: javax.swing.JComponent) = v.asInstanceOf[Component]

  def init(v: Component): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Node.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    v.putClientProperty(InitializedKey, true)
    
  }
  
  def isInitialized(v: Component): Boolean = v.getClientProperty(InitializedKey) != null
  val InitializedKey = "guarana-initialized-key"
}