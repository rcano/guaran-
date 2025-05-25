package guarana
package swing
package plaf

import javax.swing.plaf.ColorUIResource
import javax.swing.plaf.basic.BasicTextUI

trait CssTextComponentCommons extends BasicTextUI, CssSwingControlUi {
  
  override def installDefaults() =
    super.installDefaults()
    val textComponent = TextComponent.wrap(super.getComponent.unn)
    scenegraph.stylist(scenegraph.getMetrics(), textComponent.selectionColor, textComponent)
      .foreach(c => super.getComponent.unn.setSelectionColor(ColorUIResource(c)))
    scenegraph.stylist(scenegraph.getMetrics(), textComponent.selectedTextColor, textComponent)
      .foreach(c => super.getComponent.unn.setSelectedTextColor(ColorUIResource(c)))

}
