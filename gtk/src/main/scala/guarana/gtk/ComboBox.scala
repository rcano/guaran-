package guarana
package gtk
opaque type ComboBox <: Widget = org.gnome.gtk.ComboBox & Widget
object ComboBox {
  val Active: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("active", _.getActive(), _.setActive(_), true)
  val ActiveId: ExternalVar.Aux[ComboBox, java.lang.String | Null] = ExternalVar[ComboBox, java.lang.String | Null]("active-id", _.getActiveId(), _.setActiveId(_), true)
  val ButtonSensitivity: ExternalVar.Aux[ComboBox, org.gnome.gtk.SensitivityType | Null] = ExternalVar[ComboBox, org.gnome.gtk.SensitivityType | Null]("button-sensitivity", _.getButtonSensitivity(), _.setButtonSensitivity(_), true)
  val Child: ExternalVar.Aux[ComboBox, org.gnome.gtk.Widget | Null] = ExternalVar[ComboBox, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val EntryTextColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("entry-text-column", _.getEntryTextColumn(), _.setEntryTextColumn(_), true)
  val IdColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("id-column", _.getIdColumn(), _.setIdColumn(_), true)
  val Model: ExternalVar.Aux[ComboBox, org.gnome.gtk.TreeModel | Null] = ExternalVar[ComboBox, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  val PopupFixedWidth: ExternalVar.Aux[ComboBox, Boolean] = ExternalVar[ComboBox, Boolean]("popup-fixed-width", _.getPopupFixedWidth(), _.setPopupFixedWidth(_), true)
  ()
  extension (v: ComboBox) {
    def unwrap: org.gnome.gtk.ComboBox = v
    export unwrap.onActivate, unwrap.onChanged, unwrap.onFormatEntryText, unwrap.onMoveActive, unwrap.onPopdown, unwrap.onPopup
  }
}