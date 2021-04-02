package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{JComponent, LookAndFeel, UIManager}
import javax.swing.plaf.basic.BasicTextAreaUI
import scala.annotation.static
import scala.util.chaining.*

class CssTextAreaUi extends BasicTextAreaUI, CssTextComponentCommons {

  override def installDefaults() =
    super.installDefaults()
    scenegraph.stylist.installDefaults(getComponent)

  override def uninstallDefaults() =
    super.uninstallDefaults()
    scenegraph.stylist.uninstallDefaults(getComponent)

  override protected def paintBackground(g: Graphics): Unit = {
    val textFieldNode = TextComponent.wrap(getComponent)
    if textFieldNode.unwrap.isOpaque then
      val bckgr = style.CssProperties.Background.forInstance(textFieldNode) pipe scenegraph.stateReader.apply
      if bckgr == style.CssProperties.EmptyBackground then super.paintBackground(g)
      else withinRegion(textFieldNode.unwrap)(RegionPainter.paintRegion(bckgr, g.upgrade, _, _, _, _))
  }
}
object CssTextAreaUi {
  @static def createUI(c: JComponent) = CssTextAreaUi()
}