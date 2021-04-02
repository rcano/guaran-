package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{JComponent, LookAndFeel, UIManager}
import javax.swing.plaf.basic.BasicTextFieldUI
import scala.annotation.static
import scala.util.chaining.*

class CssTextFieldUi extends BasicTextFieldUI, CssTextComponentCommons {

  private var uninstalled = false
  override protected def installListeners(): Unit =
    val textFieldNode = Node.wrap(getComponent)
    scenegraph.update {
      val placeholderVar = style.CssProperties.PlaceholderText.forInstance(textFieldNode)
      textFieldNode.varUpdates := EventIterator.takeWhile(_ => !uninstalled).foreach {
        case placeholderVar(_, _) | textFieldNode.hovered(_, _) => getComponent.repaint()
        case _ =>
      }
    }

  override protected def uninstallListeners(): Unit = 
    uninstalled = true

  override protected def paintSafely(g: Graphics): Unit = {
    super.paintSafely(g)
    paintPlaceholder(g)
  }

  override protected def paintBackground(g: Graphics): Unit = {
    val textFieldNode = TextComponent.wrap(getComponent)
    if textFieldNode.unwrap.isOpaque then
      val bckgr = style.CssProperties.Background.forInstance(textFieldNode) pipe scenegraph.stateReader.apply
      if bckgr == style.CssProperties.EmptyBackground then super.paintBackground(g)
      else withinRegion(textFieldNode.unwrap)(RegionPainter.paintRegion(bckgr, g.upgrade, _, _, _, _))
  }

  // logic copied from FlatLaf
  private def paintPlaceholder(g: Graphics): Unit = {
    val textFieldNode = TextComponent.wrap(getComponent)
    
    val placeholderProvider = Node.wrap(getComponent.getParent match
      case c: javax.swing.JComboBox[?] => c
      case _ => getComponent
    )

    val placeholderText = style.CssProperties.PlaceholderText.forInstance(placeholderProvider).pipe(scenegraph.stateReader(_))
    val placeholderPaint = style.CssProperties.PlaceholderTextPaint.forInstance(placeholderProvider).pipe(scenegraph.stateReader(_))
    if placeholderText == null || placeholderPaint == null then return

    val g2 = g.upgrade
    g2.setPaint(placeholderPaint)

    withinRegion(textFieldNode.unwrap) { (x, y, w, h) => 
      val fm = textFieldNode.unwrap.getFontMetrics(textFieldNode.unwrap.getFont)
      val ty = fm.getAscent + (h - fm.getHeight()) / 2f
      javax.swing.plaf.basic.BasicGraphicsUtils
        .drawStringUnderlineCharAt(textFieldNode.unwrap, g2, placeholderText, -1, x.toFloat, ty)
    }
  }
}
object CssTextFieldUi {
  @static def createUI(c: JComponent) = CssTextFieldUi()
}