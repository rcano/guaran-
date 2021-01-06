package guarana.swing
package plaf

import language.implicitConversions
import java.awt.{Dimension, Rectangle}
import javax.swing.{AbstractButton, JComponent, JButton, UIManager}
import javax.swing.plaf.metal.MetalScrollBarUI
import scala.annotation.static
import scala.util.chaining._

class CssScrollBarUi extends MetalScrollBarUI, CssUi {
  override def paint(g: Graphics, c: JComponent) = super.paint(g.upgrade.withAliasing, c)

  override protected def createIncreaseButton(orientation: Int) = createInvisibleButton()
  override protected def createDecreaseButton(orientation: Int) = createInvisibleButton()
  protected def createInvisibleButton() = JButton().tap { b =>
    val dim = Dimension(0, 0)
    b.setPreferredSize(dim)
    b.setMaximumSize(dim)
    b.setMinimumSize(dim)
    b.setVisible(false)
    
  }

  override protected def paintTrack(g: Graphics, c: JComponent, trackBounds: Rectangle) = {
    val sb = scrollbar //must make it a val
    val background = style.CssProperties.Background.forInstance(sb)
    RegionPainter.paintRegion(scenegraph.stateReader(background), g.asInstanceOf, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height)
  }

  override protected def paintThumb(g: Graphics, c: JComponent, thumbBounds: Rectangle) = {
    val sb = scrollbar //must make it a val
    val background = style.CssProperties.ScrollbarThumbBackground.forInstance(sb)
    RegionPainter.paintRegion(scenegraph.stateReader(background), g.upgrade, thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height)
    val border = style.CssProperties.ScrollbarThumbBorder.forInstance(sb) pipe (scenegraph.stateReader(_))
    CssBorder.paintBorder(border, CssBorder.getBorderInsets(border), g.nn, thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height)
  }
}

object CssScrollBarUi {
  @static def createUI(c: JComponent) = CssScrollBarUi() //sadly we have to instantiate a new one, because of how BasicScrollBarUI is defined...
}