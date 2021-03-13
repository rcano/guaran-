package guarana.swing
package plaf

import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.swing.JComponent
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
    style.CssProperties.Effect.forInstance(c).pipe(scenegraph.stateReader(_)) match
    case null => paintFunction(g, c)
    case eff =>
      val g2 = g.upgrade
      val xform = g2.getTransform
      val rootBounds = xform.createTransformedShape(c.getRootPane.getBounds()).getBounds

      val raster = cachedRaster(rootBounds.width, rootBounds.height)
      val rasterGraphics = raster.createGraphics()
      rasterGraphics.setRenderingHints(g2.getRenderingHints)
      rasterGraphics.setFont(g2.getFont)
      rasterGraphics.setTransform(xform)
      
      val expandedClip = {
        val c = g2.getClipBounds()
        c.x -= c.width
        c.y -= c.height
        c.width *= 3
        c.height *= 3
        c
      }

      rasterGraphics.setClip(expandedClip)
      rasterGraphics.setPaint(Color.Transparent)
      rasterGraphics.fillRect(0, 0, raster.getWidth, raster.getHeight)
      
      paintFunction(rasterGraphics, c)

      val xformedBounds = xform.createTransformedShape(expandedClip).getBounds
      if xformedBounds.x < 0 then 
        xformedBounds.width += xformedBounds.x
        xformedBounds.x = 0
      if xformedBounds.y < 0 then 
        xformedBounds.height += xformedBounds.y
        xformedBounds.y = 0

      // beacuse the rendering to the offscreen raster has the same transforms, if it was scaled in any way
      // it'd get scaled a 2nd time when we render to the actual component, for this reason
      // we check for existing scaling a descale

      if xform.getScaleX != 1 || xform.getScaleY != 1 then
        val tempXform = java.awt.geom.AffineTransform(xform)
        tempXform.scale(1 / xform.getScaleX, 1 / xform.getScaleY)
        g2.setTransform(tempXform)

      val renderedImage = raster.getSubimage(
        xformedBounds.x, xformedBounds.y,
        if xformedBounds.x + xformedBounds.width > raster.getWidth then raster.getWidth - xformedBounds.x else xformedBounds.width,
        if xformedBounds.y + xformedBounds.height > raster.getHeight then raster.getHeight - xformedBounds.y else xformedBounds.height,
      )
      val prevClip = g.getClip()
      g.setClip(null)

      val fitInRectangle = xform.createTransformedShape(c.getBounds()).getBounds()
      if fitInRectangle.x < 0 then 
        fitInRectangle.width += fitInRectangle.x
        fitInRectangle.x = 0
      if fitInRectangle.y < 0 then 
        fitInRectangle.height += fitInRectangle.y
        fitInRectangle.y = 0

      println(s"$xformedBounds → $fitInRectangle")
      val location = util.Anchor.Center.innerInOuter(
        Bounds(0, 0, xformedBounds.getWidth, xformedBounds.getHeight),
        fitInRectangle.tap(_.setLocation(0, 0))
      )
      g.upgrade.drawImage(renderedImage, eff, location._1.toInt, location._2.toInt)
      g.setClip(prevClip)
      g2.setTransform(xform)
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
    if _cachedRaster == null then
      java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
        .createCompatibleImage(width, height, java.awt.Transparency.TRANSLUCENT)
    else if _cachedRaster.getWidth < width || _cachedRaster.getHeight < height then
      java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
        .createCompatibleImage(width max _cachedRaster.getWidth, height max _cachedRaster.getHeight, java.awt.Transparency.TRANSLUCENT)
    else _cachedRaster

}
