package guarana.swing
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

  override def getPreferredSize(c: JComponent): Dimension = {
    val d = super.getPreferredSize(c)
    val padding = style.CssProperties.Padding.forInstance(c).pipe(scenegraph.stateReader(_))
    if d != null then
      d.width += (padding.left + padding.right).toInt
      d.height += (padding.top + padding.bot).toInt
      d
    else null
  }
}

object CssSwingControlUi {
  private var _cachedRaster: BufferedImage = null
  private def cachedRaster(width: Int, height: Int) : BufferedImage =
    val res = 
      if _cachedRaster == null then
        java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
          .createCompatibleImage(width, height, java.awt.Transparency.TRANSLUCENT)
      else if _cachedRaster.getWidth < width || _cachedRaster.getHeight < height then
        java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
          .createCompatibleImage(width max _cachedRaster.getWidth, height max _cachedRaster.getHeight, java.awt.Transparency.TRANSLUCENT)
      else _cachedRaster
    _cachedRaster = res
    res

}
