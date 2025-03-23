package guarana
package swing
package plaf

import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.swing.{JComponent}
import javax.swing.plaf.ComponentUI
import scala.util.chaining.*

trait CssSwingControlUi extends ComponentUI, CssUi {
  import CssSwingControlUi.*

  /** Helper method providing support for effects.
    * 
    * Normally you would ovefride [[ComponentUI.update]] and just call this.
    * the reason we can just override here is that sometimes you need to override update
    * and perform some updates, but then you want effects to take pace on that, so the method
    * is here for you to call.
    */ 
  protected inline def updateSupportingEffects(g: Graphics, c: JComponent, inline paintFunction: (Graphics, JComponent) => Unit): Unit = {
    LafUtils.paintSupportingEffects(scenegraph, g, c, paintFunction)
  }

  override def update(g: Graphics, c: JComponent) = updateSupportingEffects(g, c, (g, c) => super.update(g, c))

  override def getPreferredSize(c: JComponent): Dimension | Null = {
    val d = super.getPreferredSize(c)
    if d != null then
      val padding = style.CssProperties.Padding.forInstance(c).pipe(scenegraph.stateReader(_))
      d.width += (padding.left + padding.right).toInt
      d.height += (padding.top + padding.bot).toInt
      d
    else null
  }
}

object CssSwingControlUi {
  private var _cachedRaster: BufferedImage | Null = null
  private def cachedRaster(width: Int, height: Int) : BufferedImage =
    val res = 
      if _cachedRaster == null then
        java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.unn.getDefaultScreenDevice.unn.getDefaultConfiguration.unn
          .createCompatibleImage(width, height, java.awt.Transparency.TRANSLUCENT).unn
      else if _cachedRaster.unn.getWidth < width || _cachedRaster.unn.getHeight < height then
        java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.unn.getDefaultScreenDevice.unn.getDefaultConfiguration.unn
          .createCompatibleImage(width max _cachedRaster.unn.getWidth, height max _cachedRaster.unn.getHeight, java.awt.Transparency.TRANSLUCENT).unn
      else _cachedRaster.unn
    _cachedRaster = res
    res

}
