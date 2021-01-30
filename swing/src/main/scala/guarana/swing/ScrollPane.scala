//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, _}
import java.awt.event._
import javax.swing.{Action => _, _}
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type ScrollPane <: Component  = javax.swing.JScrollPane & Component
object ScrollPane extends VarsMap {
  val UI: SwingVar.Aux[ScrollPane, javax.swing.plaf.ScrollPaneUI] = SwingVar[ScrollPane, javax.swing.plaf.ScrollPaneUI]("UI", _.getUI.nn, _.setUI(_))
  val BotLeftCorner: SwingVar.Aux[ScrollPane, Node | Null] = SwingVar[ScrollPane, Node | Null]("botLeftCorner", _.getCorner(ScrollPaneConstants.LOWER_LEFT_CORNER).asInstanceOf[Node | Null], (s, n) => s.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, if (n != null) n.unwrap else null))
  val BotRightCorner: SwingVar.Aux[ScrollPane, Node | Null] = SwingVar[ScrollPane, Node | Null]("botRightCorner", _.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER).asInstanceOf[Node | Null], (s, n) => s.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, if (n != null) n.unwrap else null))
  val ColumnHeader: SwingVar.Aux[ScrollPane, javax.swing.JViewport | Null] = SwingVar[ScrollPane, javax.swing.JViewport | Null]("columnHeader", _.getColumnHeader, _.setColumnHeader(_))
  val Content: SwingVar.Aux[ScrollPane, Node | Null] = SwingVar[ScrollPane, Node | Null]("content", _.getViewport.nn.getView.asInstanceOf[Node | Null], (s, n) => s.setViewportView(if (n != null) n.unwrap else null))
  val HorizontalScrollBar: SwingVar.Aux[ScrollPane, javax.swing.JScrollBar] = SwingVar[ScrollPane, javax.swing.JScrollBar]("horizontalScrollBar", _.getHorizontalScrollBar.nn, _.setHorizontalScrollBar(_))
  val HorizontalScrollBarPolicy: SwingVar.Aux[ScrollPane, Int] = SwingVar[ScrollPane, Int]("horizontalScrollBarPolicy", _.getHorizontalScrollBarPolicy, _.setHorizontalScrollBarPolicy(_))
  val RowHeader: SwingVar.Aux[ScrollPane, javax.swing.JViewport | Null] = SwingVar[ScrollPane, javax.swing.JViewport | Null]("rowHeader", _.getRowHeader, _.setRowHeader(_))
  val TopLeftCorner: SwingVar.Aux[ScrollPane, Node | Null] = SwingVar[ScrollPane, Node | Null]("topLeftCorner", _.getCorner(ScrollPaneConstants.UPPER_LEFT_CORNER).asInstanceOf[Node | Null], (s, n) => s.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, if (n != null) n.unwrap else null))
  val TopRightCorner: SwingVar.Aux[ScrollPane, Node | Null] = SwingVar[ScrollPane, Node | Null]("topRightCorner", _.getCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER).asInstanceOf[Node | Null], (s, n) => s.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, if (n != null) n.unwrap else null))
  val VerticalScrollBar: SwingVar.Aux[ScrollPane, javax.swing.JScrollBar] = SwingVar[ScrollPane, javax.swing.JScrollBar]("verticalScrollBar", _.getVerticalScrollBar.nn, _.setVerticalScrollBar(_))
  val VerticalScrollBarPolicy: SwingVar.Aux[ScrollPane, Int] = SwingVar[ScrollPane, Int]("verticalScrollBarPolicy", _.getVerticalScrollBarPolicy, _.setVerticalScrollBarPolicy(_))
  val Viewport: SwingVar.Aux[ScrollPane, javax.swing.JViewport] = SwingVar[ScrollPane, javax.swing.JViewport]("viewport", _.getViewport.nn, _.setViewport(_))
  val ViewportBorder: SwingVar.Aux[ScrollPane, javax.swing.border.Border | Null] = SwingVar[ScrollPane, javax.swing.border.Border | Null]("viewportBorder", _.getViewportBorder, _.setViewportBorder(_))
  val WheelScrollingEnabled: SwingVar.Aux[ScrollPane, Boolean] = SwingVar[ScrollPane, Boolean]("wheelScrollingEnabled", _.isWheelScrollingEnabled, _.setWheelScrollingEnabled(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ScrollPane) {
      def UI: Var.Aux[javax.swing.plaf.ScrollPaneUI, v.type] = ScrollPane.UI.asInstanceOf[Var.Aux[javax.swing.plaf.ScrollPaneUI, v.type]]
      def botLeftCorner: Var.Aux[Node | Null, v.type] = ScrollPane.BotLeftCorner.asInstanceOf[Var.Aux[Node | Null, v.type]]
      def botRightCorner: Var.Aux[Node | Null, v.type] = ScrollPane.BotRightCorner.asInstanceOf[Var.Aux[Node | Null, v.type]]
      def columnHeader: Var.Aux[javax.swing.JViewport | Null, v.type] = ScrollPane.ColumnHeader.asInstanceOf[Var.Aux[javax.swing.JViewport | Null, v.type]]
      def content: Var.Aux[Node | Null, v.type] = ScrollPane.Content.asInstanceOf[Var.Aux[Node | Null, v.type]]
      def horizontalScrollBar: Var.Aux[javax.swing.JScrollBar, v.type] = ScrollPane.HorizontalScrollBar.asInstanceOf[Var.Aux[javax.swing.JScrollBar, v.type]]
      def horizontalScrollBarPolicy: Var.Aux[Int, v.type] = ScrollPane.HorizontalScrollBarPolicy.asInstanceOf[Var.Aux[Int, v.type]]
      def rowHeader: Var.Aux[javax.swing.JViewport | Null, v.type] = ScrollPane.RowHeader.asInstanceOf[Var.Aux[javax.swing.JViewport | Null, v.type]]
      def topLeftCorner: Var.Aux[Node | Null, v.type] = ScrollPane.TopLeftCorner.asInstanceOf[Var.Aux[Node | Null, v.type]]
      def topRightCorner: Var.Aux[Node | Null, v.type] = ScrollPane.TopRightCorner.asInstanceOf[Var.Aux[Node | Null, v.type]]
      def verticalScrollBar: Var.Aux[javax.swing.JScrollBar, v.type] = ScrollPane.VerticalScrollBar.asInstanceOf[Var.Aux[javax.swing.JScrollBar, v.type]]
      def verticalScrollBarPolicy: Var.Aux[Int, v.type] = ScrollPane.VerticalScrollBarPolicy.asInstanceOf[Var.Aux[Int, v.type]]
      def viewport: Var.Aux[javax.swing.JViewport, v.type] = ScrollPane.Viewport.asInstanceOf[Var.Aux[javax.swing.JViewport, v.type]]
      def viewportBorder: Var.Aux[javax.swing.border.Border | Null, v.type] = ScrollPane.ViewportBorder.asInstanceOf[Var.Aux[javax.swing.border.Border | Null, v.type]]
      def wheelScrollingEnabled: Var.Aux[Boolean, v.type] = ScrollPane.WheelScrollingEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def viewportBorderBounds: java.awt.Rectangle = v.getViewportBorderBounds.nn
      def unwrap: javax.swing.JScrollPane = v
    }
  }

  def wrap(v: javax.swing.JScrollPane) = v.asInstanceOf[ScrollPane]

  def init(v: ScrollPane): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): ScrollPane = {
    val res = javax.swing.JScrollPane().asInstanceOf[ScrollPane]
    
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.ScrollPaneUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    botLeftCorner: Opt[Binding[Node | Null]] = UnsetParam,
    botRightCorner: Opt[Binding[Node | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    columnHeader: Opt[Binding[javax.swing.JViewport | Null]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
    content: Opt[Binding[Node | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    horizontalScrollBar: Opt[Binding[javax.swing.JScrollBar]] = UnsetParam,
    horizontalScrollBarPolicy: Opt[Binding[Int]] = UnsetParam,
    hoveredMut: Opt[Binding[Boolean]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rowHeader: Opt[Binding[javax.swing.JViewport | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    topLeftCorner: Opt[Binding[Node | Null]] = UnsetParam,
    topRightCorner: Opt[Binding[Node | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalScrollBar: Opt[Binding[javax.swing.JScrollBar]] = UnsetParam,
    verticalScrollBarPolicy: Opt[Binding[Int]] = UnsetParam,
    viewport: Opt[Binding[javax.swing.JViewport]] = UnsetParam,
    viewportBorder: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    wheelScrollingEnabled: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[ScrollPane] = {
    val res = uninitialized()
    ScrollPane.init(res)
    ifSet(UI, ScrollPane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(botLeftCorner, ScrollPane.ops.botLeftCorner(res) := _)
    ifSet(botRightCorner, ScrollPane.ops.botRightCorner(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(columnHeader, ScrollPane.ops.columnHeader(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(content, ScrollPane.ops.content(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(horizontalScrollBar, ScrollPane.ops.horizontalScrollBar(res) := _)
    ifSet(horizontalScrollBarPolicy, ScrollPane.ops.horizontalScrollBarPolicy(res) := _)
    ifSet(hoveredMut, Node.ops.hoveredMut(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(mouseDragMut, Node.ops.mouseDragMut(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rowHeader, ScrollPane.ops.rowHeader(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(topLeftCorner, ScrollPane.ops.topLeftCorner(res) := _)
    ifSet(topRightCorner, ScrollPane.ops.topRightCorner(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalScrollBar, ScrollPane.ops.verticalScrollBar(res) := _)
    ifSet(verticalScrollBarPolicy, ScrollPane.ops.verticalScrollBarPolicy(res) := _)
    ifSet(viewport, ScrollPane.ops.viewport(res) := _)
    ifSet(viewportBorder, ScrollPane.ops.viewportBorder(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    ifSet(wheelScrollingEnabled, ScrollPane.ops.wheelScrollingEnabled(res) := _)
    res
  }
  
}