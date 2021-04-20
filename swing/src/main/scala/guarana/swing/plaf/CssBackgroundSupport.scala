package guarana.swing
package plaf

import javax.swing.JComponent

trait CssBackgroundSupport extends CssSwingControlUi {
  
  /** Copied over from ComponentUI and adpated to properly render the background.
  */ 
  override def update(g: Graphics, c: JComponent) = {
    updateSupportingEffects(g, c, (g, c) =>

      val background = style.CssProperties.Background.forInstance(c)
      if background != style.CssProperties.EmptyBackground then
        withinRegion(c.nn)((x, y, w, h) => 
          plaf.RegionPainter.paintRegion(scenegraph.stateReader(background), g.upgrade.withAliasing, x, y, w, h)
        )
      else if c.isOpaque() then
        g.setColor(c.getBackground())
        g.fillRect(0, 0, c.getWidth(), c.getHeight())

      super.paint(g, c)
    )
  }
}
