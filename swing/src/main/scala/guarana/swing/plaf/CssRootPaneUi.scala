package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.JComponent
import javax.swing.plaf.metal.MetalRootPaneUI
import scala.annotation.static

class CssRootPaneUi extends MetalRootPaneUI, CssSwingControlUi {
  override def paint(g: Graphics, c: JComponent) = {
    val bckProp = style.CssProperties.Background.forInstance(c)
    val bck = scenegraph.stateReader(bckProp)
    if (bck.fills.isEmpty && bck.images.isEmpty) super.paint(g, c)
    else plaf.RegionPainter.paintRegion(bck, g.upgrade.withAliasing, 0, 0, c.getWidth, c.getHeight)
  }
}
object CssRootPaneUi extends CssRootPaneUi {
  @static def createUI(c: JComponent) = this
}