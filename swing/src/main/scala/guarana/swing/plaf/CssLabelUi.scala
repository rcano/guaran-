package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{AbstractButton, JComponent, JLabel, UIManager}
import javax.swing.plaf.basic.{BasicGraphicsUtils, BasicLabelUI}
import scala.annotation.{static, targetName}
import scala.util.chaining._

class CssLabelUi extends BasicLabelUI, CssSwingControlUi, CssBackgroundSupport {

  override def installDefaults(l: JLabel): Unit =
    super.installDefaults(l)
    scenegraph.stylist.installDefaults(l)

  override def uninstallDefaults(l: JLabel): Unit =
    super.uninstallDefaults(l)
    scenegraph.stylist.uninstallDefaults(l)

  override protected def paintEnabledText(l: JLabel, g: Graphics, text: String, textX: Int, textY: Int): Unit =
    g.setColor(getUiProperty(Node.Foreground, l, _.getForeground()))
    g.setFont(getUiProperty(Node.Font, l, _.getFont()))
    BasicGraphicsUtils.drawStringUnderlineCharAt(l, g.upgrade, text, l.getDisplayedMnemonicIndex(), textX.toFloat, textY.toFloat)

  /** redirect to paintEnabledText, different fonts and colors should be handled via css properties */
  override protected def paintDisabledText(l: JLabel, g: Graphics, text: String, textX: Int, textY: Int): Unit =
    paintEnabledText(l, g, text, textX, textY)
}
object CssLabelUi extends CssLabelUi {
  @static def createUI(c: JComponent): CssLabelUi = this

}