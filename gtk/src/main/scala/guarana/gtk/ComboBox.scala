package guarana
package gtk
opaque type ComboBox <: Widget = org.gnome.gtk.ComboBox & Widget
object ComboBox {
  val Active: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("active", _.getActive(), _.setActive(_), true)
  val ButtonSensitivity: ExternalVar.Aux[ComboBox, org.gnome.gtk.SensitivityType | Null] = ExternalVar[ComboBox, org.gnome.gtk.SensitivityType | Null]("button-sensitivity", _.getButtonSensitivity(), _.setButtonSensitivity(_), true)
  val EntryTextColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("entry-text-column", _.getEntryTextColumn(), _.setEntryTextColumn(_), true)
  val IdColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("id-column", _.getIdColumn(), _.setIdColumn(_), true)
  val PopupFixedWidth: ExternalVar.Aux[ComboBox, Boolean] = ExternalVar[ComboBox, Boolean]("popup-fixed-width", _.getPopupFixedWidth(), _.setPopupFixedWidth(_), true)
  ()
  extension (v: ComboBox) {
    def unwrap: org.gnome.gtk.ComboBox = v
    export unwrap.onActivate, unwrap.onChanged, unwrap.onFormatEntryText, unwrap.onMoveActive, unwrap.onPopdown, unwrap.onPopup
  }
  def init(v: ComboBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ComboBox = {
    val res = new org.gnome.gtk.ComboBox()
    res.asInstanceOf[ComboBox]
  }
}