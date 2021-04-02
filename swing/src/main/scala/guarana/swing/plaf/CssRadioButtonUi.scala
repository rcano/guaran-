package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{AbstractButton, JComponent, UIManager}
import javax.swing.plaf.basic.BasicGraphicsUtils
import javax.swing.plaf.metal.MetalRadioButtonUI
import scala.annotation.static
import scala.util.chaining._

class CssRadioButtonUi() extends MetalRadioButtonUI, CssSwingControlUi {
  override def installDefaults(b: AbstractButton): Unit =
    super.installDefaults(b)
    scenegraph.stylist.installDefaults(b)

  override def uninstallDefaults(b: AbstractButton): Unit =
    super.uninstallDefaults(b)
    scenegraph.stylist.uninstallDefaults(b)

  override def paint(g: Graphics, c: JComponent): Unit = {
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

  override protected def paintText(g: Graphics, c: JComponent, textRect: Bounds, text: String): Unit =
    CssButtonUi.paintButtonText(g, c, textRect, text)
}
object CssRadioButtonUi extends CssRadioButtonUi {
  @static def createUI(c: JComponent): CssRadioButtonUi = this
}