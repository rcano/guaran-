package guarana
package swing
package plaf

import java.awt.Dimension
import javax.swing.plaf.basic.BasicSeparatorUI
import javax.swing.{JComponent, JSeparator, SwingConstants}
import scala.annotation.static

class CssSeparatorUi extends BasicSeparatorUI, CssBackgroundSupport {

  override def installDefaults(l: JSeparator): Unit =
    super.installDefaults(l)
    scenegraph.stylist.installDefaults(l)

  override def uninstallDefaults(l: JSeparator): Unit =
    super.uninstallDefaults(l)
    scenegraph.stylist.uninstallDefaults(l)

  override def getPreferredSize(c: JComponent): Dimension | Null = {
    val sc = scenegraph
    val em = sc.stateReader(sc.emSize)
    val lineThick = (em * 0.1).toInt.max(2)
    val d = c.asInstanceOf[JSeparator].getOrientation() match {
      case SwingConstants.VERTICAL => Dimension(lineThick, 0)
      case _ => Dimension(0, lineThick)
    }

    val padding = sc.stateReader(style.CssProperties.Padding.forInstance(c))
    d.width += (padding.left + padding.right).toInt
    d.height += (padding.top + padding.bot).toInt
    val componentInsets = c.getInsets(null)
    d.width += componentInsets.left + componentInsets.right
    d.height += componentInsets.top + componentInsets.bottom
    d
  }

  override def paint(g: Graphics, c: JComponent): Unit = () // this is handled 100% as a background
}

object CssSeparatorUi extends CssSeparatorUi {
  @static def createUI(c: JComponent) = this
}
