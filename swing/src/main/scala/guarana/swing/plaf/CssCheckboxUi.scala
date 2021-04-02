package guarana.swing
package plaf

import javax.swing.JComponent
import scala.annotation.static
class CssCheckboxUi() extends CssRadioButtonUi {
  
  override def getPropertyPrefix() = "CheckBox."
}
object CssCheckboxUi extends CssCheckboxUi {
  @static def createUI(c: JComponent): CssCheckboxUi = this
}