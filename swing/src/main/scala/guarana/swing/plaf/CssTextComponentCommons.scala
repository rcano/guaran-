package guarana.swing
package plaf

import javax.swing.plaf.ColorUIResource
import javax.swing.plaf.basic.BasicTextUI

trait CssTextComponentCommons extends BasicTextUI, CssSwingControlUi {
  
  override def installDefaults() =
    super.installDefaults()
    val textComponent = TextComponent.wrap(getComponent)
    scenegraph.stylist(textComponent.selectionColor)
      .foreach(c => getComponent.setSelectionColor(ColorUIResource(c)))
    scenegraph.stylist(textComponent.selectedTextColor)
      .foreach(c => getComponent.setSelectedTextColor(ColorUIResource(c)))

}
