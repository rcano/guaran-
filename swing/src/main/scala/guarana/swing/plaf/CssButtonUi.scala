package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{AbstractButton, JComponent, UIManager}
import javax.swing.plaf.metal.MetalButtonUI
import scala.annotation.static

class CssButtonUi extends MetalButtonUI, CssUi {

  override def update(g: Graphics, c: JComponent) = {
    val b = c.asInstanceOf[AbstractButton]
    if (c.isOpaque()) {
      g.setColor(c.getBackground())
      g.fillRect(0, 0, c.getWidth(), c.getHeight())
    }
    
    if (b.isContentAreaFilled) {
      val background = style.CssProperties.Background.forInstance(b)
      withinRegion(c.nn)((x, y, w, h) => 
        plaf.RegionPainter.paintRegion(scenegraph.stateReader(background), g.upgrade.withAliasing, x, y, w, h)
      )
    }
    super.paint(g, c)
  }

  override def paintButtonPressed(g: Graphics, b: AbstractButton) = {

  }
}
object CssButtonUi extends CssButtonUi {
  @static def createUI(c: JComponent): CssButtonUi = this
}