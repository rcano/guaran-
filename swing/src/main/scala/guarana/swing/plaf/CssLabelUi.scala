package guarana.swing
package plaf

import language.implicitConversions
import javax.swing.{AbstractButton, JComponent, JLabel, UIManager}
import javax.swing.plaf.basic.{BasicGraphicsUtils, BasicLabelUI}
import scala.annotation.{static, targetName}
import scala.util.chaining._

class CssLabelUi extends BasicLabelUI, CssSwingControlUi {

  override def installDefaults(l: JLabel): Unit =
    super.installDefaults(l)
    scenegraph.stylist.installDefaults(l)

  override def uninstallDefaults(l: JLabel): Unit =
    super.uninstallDefaults(l)
    scenegraph.stylist.uninstallDefaults(l)

  /** Copied over from ComponentUI and adpated to properly render the background.
    */ 
  override def update(g: Graphics, c: JComponent) = {
    updateSupportingEffects(g, c, (g, c) =>
      val b = c.asInstanceOf[JLabel]

      val background = style.CssProperties.Background.forInstance(b)
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