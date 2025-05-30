package guarana
package swing
package plaf

import java.awt.image.BufferedImage
import javax.swing.*

/** General purpose utilities for working with java2d and swing.
  *
  */ 
object LafUtils {

  /** Helper method providing support for effects.
    */ 
  inline def paintSupportingEffects(scenegraph: Scenegraph, g: Graphics, c: JComponent, inline paintFunction: (Graphics, JComponent) => Unit): Unit = {
    val g2 = g.upgrade
    val opacity = scenegraph.stateReader(style.CssProperties.Opacity.forInstance[c.type])
    val prevComposite = g2.getComposite()
    if opacity != 1 then
      g2.setComposite(java.awt.AlphaComposite.SrcOver.unn.derive(opacity.toFloat))

    scenegraph.stateReader(style.CssProperties.Effect.forInstance[c.type]) match {
      case null => paintFunction(g, c)
      // case _ if c.getRootPane == null => paintFunction(g, c)
      case eff =>
        val xform = g2.getTransform.unn
        
        val raster = {
          val rootPane = c.getRootPane
          val rootBounds = 
            if rootPane != null then
              rootPane.getBounds()
            else
              //since we don't know *where* we are drawing, we are going to be pessimistic and find the largest Frame instantiated and use that
              val frames = java.awt.Frame.getFrames.nnn
              Bounds(width = frames.map(_.getWidth).max, height = frames.map(_.getHeight).max)
          val finalBounds = xform.createTransformedShape(rootBounds).unn.getBounds().unn
          cachedRaster(finalBounds.width, finalBounds.height)
        }
        val rasterGraphics = raster.createGraphics().unn
        rasterGraphics.setRenderingHints(g2.getRenderingHints)
        rasterGraphics.setFont(g2.getFont)
        rasterGraphics.setTransform(xform)
        
        val expandedClip = {
          val c = g2.getClipBounds().unn
          c.x -= c.width
          c.y -= c.height
          c.width *= 3
          c.height *= 3
          c
        }

        rasterGraphics.setClip(expandedClip)
        locally {
          val prevComposite = rasterGraphics.getComposite
          rasterGraphics.setComposite(java.awt.AlphaComposite.Src)
          rasterGraphics.setPaint(Color.Transparent)
          rasterGraphics.fill(expandedClip)
          rasterGraphics.setComposite(prevComposite)
        }
        
        paintFunction(rasterGraphics, c)

        val xformedBounds = xform.createTransformedShape(expandedClip).unn.getBounds.unn
        if xformedBounds.x < 0 then 
          xformedBounds.width += xformedBounds.x
          xformedBounds.x = 0
        if xformedBounds.y < 0 then 
          xformedBounds.height += xformedBounds.y
          xformedBounds.y = 0

        val renderedImage = raster.getSubimage(
          xformedBounds.x, xformedBounds.y,
          if xformedBounds.x + xformedBounds.width > raster.getWidth then raster.getWidth - xformedBounds.x else xformedBounds.width,
          if xformedBounds.y + xformedBounds.height > raster.getHeight then raster.getHeight - xformedBounds.y else xformedBounds.height,
        )
        val prevClip = g.getClip()

        g2.setClip(null)
        g2.setTransform(java.awt.geom.AffineTransform())
        g2.drawImage(renderedImage, eff, xformedBounds.x, xformedBounds.y)
        g2.setClip(prevClip)
        g2.setTransform(xform)
    }
    g2.setComposite(prevComposite)
  }

  private var _cachedRaster: BufferedImage | Null = null
  private def cachedRaster(width: Int, height: Int) : BufferedImage =
    val res = // TODO: use foldNull here
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
