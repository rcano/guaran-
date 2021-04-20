package guarana.swing
package plaf

import javax.swing.{JButton, JComponent, JLabel, JList, JPopupMenu, ListCellRenderer, UIManager}
import javax.swing.plaf.basic.{BasicComboBoxUI, BasicComboPopup}
import scala.annotation.static
import scala.util.chaining._

class CssComboBoxUi extends BasicComboBoxUI, CssSwingControlUi, CssBackgroundSupport  {
  
  private var uninstalled = false
  override def installDefaults(): Unit =
    super.installDefaults()
    scenegraph.stylist.installDefaults(comboBox)
    scenegraph.update {
      val cb = comboBox
      val paddingProp = style.CssProperties.Padding.forInstance[cb.type]
      padding = paddingProp().toAwt
      cb.varUpdates := EventIterator.takeWhile(_ => !uninstalled).foreach {
        case paddingProp(_, newPadding) => 
          padding = newPadding.toAwt
          comboBox.revalidate()
        case _ =>
      }
    }

  override def uninstallDefaults(): Unit =
    super.uninstallDefaults()
    scenegraph.stylist.uninstallDefaults(comboBox)
    uninstalled = true

  override protected def createArrowButton(): JButton = JButton("⮟")
  override protected def createPopup() = Popup()
  
  override def paintCurrentValueBackground(g: Graphics, bounds: Bounds, hasFocus: Boolean): Unit = {
    val sc = scenegraph
    val cb = comboBox
    val bck = style.CssProperties.ComboBoxValueBackground.forInstance(cb).pipe(sc.stateReader(_))
    RegionPainter.paintRegion(bck, g.upgrade, bounds.x, bounds.y, bounds.width, bounds.height)
    val valueBorder = style.CssProperties.ComboBoxValueBorder.forInstance(cb).pipe(sc.stateReader(_))
    val borderInsets = CssBorder.getBorderInsets(valueBorder)
    CssBorder.paintBorder(
      valueBorder,
      borderInsets,
      g,
      bounds.x - borderInsets.left,
      bounds.y - borderInsets.top,
      bounds.width + borderInsets.left + borderInsets.right,
      bounds.height + borderInsets.top + borderInsets.bot
    )
  }

  override protected def getInsets() = {
    val res = super.getInsets()
    val cb = comboBox
    val valueBorder = style.CssProperties.ComboBoxValueBorder.forInstance(cb).pipe(scenegraph.stateReader(_))
    if valueBorder != style.CssProperties.EmptyBorder then
      val insets = CssBorder.getBorderInsets(valueBorder)
      if cb.getComponentOrientation.isLeftToRight then res.left += insets.left.toInt
      else res.right += insets.right.toInt
      res.top += insets.top.toInt
      res.bottom += insets.bot.toInt
    res
  }

  override protected def createRenderer() = CssDefaultListRenderer(scenegraph)

  class Popup extends BasicComboPopup(comboBox) {
    // BasicComboPopup listens to JComboBox.componentOrientation and updates
    // the component orientation of the list, scroller and popup, but when
    // switching the LaF and a new combo popup is created, the component
    // orientation is not applied.
    locally {
      val o = comboBox.getComponentOrientation()
      list.setComponentOrientation(o)
      scroller.setComponentOrientation(o)
      setComponentOrientation(o)
    }

    override protected def configurePopup() = {
      super.configurePopup()
      val border = UIManager.getBorder("PopupMenu.border")
      if border != null then setBorder(border)
      setOpaque(false)
    }
  }
}

object CssComboBoxUi {
  @static def createUI(c: JComponent): CssComboBoxUi = CssComboBoxUi()
}