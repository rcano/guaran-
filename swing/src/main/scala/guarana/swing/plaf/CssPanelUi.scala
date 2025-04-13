package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{JComponent, LookAndFeel}
import javax.swing.plaf.basic.BasicPanelUI
import scala.annotation.static

class CssPanelUi extends BasicPanelUI, CssSwingControlUi {

  override def installDefaults(p: javax.swing.JPanel) = {
    super.installDefaults(p)
    LookAndFeel.installProperty(p, "opaque", false)
  }

  override def paint(g: Graphics, c: JComponent) = {
    val bckProp = style.CssProperties.Background.forInstance(c)
    val bck = scenegraph.stateReader(bckProp)
    if (bck.fills.isEmpty && bck.images.isEmpty) super.paint(g, c)
    else withinRegion(c)(plaf.RegionPainter.paintRegion(bck, g.upgrade.withAliasing, _, _, _, _))
  }
}
object CssPanelUi extends CssPanelUi {
  @static def createUI(c: JComponent) = this
}
