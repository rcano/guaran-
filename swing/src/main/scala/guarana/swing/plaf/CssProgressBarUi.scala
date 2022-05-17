package guarana
package swing
package plaf

import language.implicitConversions
import javax.swing.{AbstractButton, JComponent, SwingConstants, UIManager}
import javax.swing.plaf.metal.MetalProgressBarUI
import scala.annotation.static
import scala.util.chaining.*

class CssProgressBarUi extends MetalProgressBarUI, CssSwingControlUi {
  override def paintDeterminate(g: Graphics, c: JComponent): Unit = withinRegion(c.nn) { (x, y, w, h) =>
    val sg = scenegraph
    val bckgr = style.CssProperties.Background.forInstance(c) pipe sg.stateReader.apply
    RegionPainter.paintRegion(bckgr, g.upgrade, x, y, w, h)

    val targetAmount = getAmountFull(null, w, h)
    var barWidth, barHeight = 0
    if (progressBar.unn.getOrientation() == SwingConstants.HORIZONTAL) {
      barWidth = targetAmount
      barHeight = h
    } else {
      barHeight = targetAmount
      barWidth = w
    }

    val barBorder = style.CssProperties.ProgressBarBarBorder.forInstance(c) pipe sg.stateReader.apply
    val borderInsets = CssBorder.getBorderInsets(barBorder)
    CssBorder.paintBorder(barBorder, borderInsets, g.nn, x, y, barWidth, barHeight)

    val barBckgr = style.CssProperties.ProgressBarBarBackground.forInstance(c) pipe sg.stateReader.apply
    RegionPainter.paintRegion(barBckgr, g.upgrade, x + borderInsets.left, y + borderInsets.top, barWidth - borderInsets.left - borderInsets.right, barHeight - borderInsets.top - borderInsets.bot)
  }
  override def paintIndeterminate(g: Graphics, c: JComponent): Unit = {
    super.paintIndeterminate(g, c)
  }
}

object CssProgressBarUi {
  @static def createUI(c: JComponent): CssProgressBarUi = CssProgressBarUi() //sadly we have to instantiate a new one, because of how BasicProgressBarUI is defined...
}