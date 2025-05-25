package guarana
package swing
package plaf

import language.implicitConversions
import javax.swing.{AbstractButton, JComponent}
import javax.swing.plaf.basic.BasicGraphicsUtils
import javax.swing.plaf.metal.MetalButtonUI
import scala.annotation.static

class CssButtonUi extends MetalButtonUI, CssSwingControlUi {

  override def installDefaults(b: AbstractButton): Unit =
    super.installDefaults(b)
    scenegraph.stylist.installDefaults(b)

  override def uninstallDefaults(b: AbstractButton): Unit =
    super.uninstallDefaults(b)
    scenegraph.stylist.uninstallDefaults(b)

  override def update(g: Graphics, c: JComponent) = {
    updateSupportingEffects(g, c, (g, c) =>
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
    )
  }

  override protected def paintButtonPressed(g: Graphics, b: AbstractButton) = {

  }

  override protected def paintText(g: Graphics, c: JComponent, textRect: Bounds, text: String): Unit =
    CssButtonUi.paintButtonText(g, c, textRect, text)
}
object CssButtonUi extends CssButtonUi {
  @static def createUI(c: JComponent): CssButtonUi = this

  /** Copied over from MetalButtonUI, just so that we can use styled property for foreground */
  def paintButtonText(g: Graphics, c: JComponent, textRect: Bounds, text: String): Unit = {
    val b = c.asInstanceOf[javax.swing.AbstractButton]
    val model = b.getModel().unn
    val fm = c.getFontMetrics(g.getFont()).unn
    val mnemIndex = b.getDisplayedMnemonicIndex()

    /* Draw the Text */
    if(model.isEnabled()) {
      /*** paint the text normally */
      g.setColor(getUiProperty(Node.Foreground, b, _.getForeground()))
    }
    else {
      /*** paint the text disabled ***/
      g.setColor(getDisabledTextColor())
    }
    
    BasicGraphicsUtils.drawStringUnderlineCharAt(c, g.upgrade, text, mnemIndex,
                              textRect.x.toFloat, textRect.y.toFloat + fm.getAscent())
  }
}