package guarana
package swing

import javax.swing.UIManager

object Fonts {
  given ValueOf[Fonts.type] = ValueOf(this)
  val Base = ExternalObsVal("Fonts.Base", _ => UIManager.getFont("Panel.font")).forInstance(this)
  val Input = ExternalObsVal("Fonts.InputBase", _ => UIManager.getFont("TextField.font")).forInstance(this)
  val Label = ExternalObsVal("Fonts.LabelBase", _ => UIManager.getFont("Label.font")).forInstance(this)
}
