package guarana
package gtk
opaque type ComboBox <: Widget = org.gnome.gtk.ComboBox & Widget
object ComboBox {
  @deprecated("", "") val Active: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("active", _.getActive(), _.setActive(_), true)
  @deprecated("", "") val ActiveId: ExternalVar.Aux[ComboBox, java.lang.String | Null] = ExternalVar[ComboBox, java.lang.String | Null]("active-id", _.getActiveId(), _.setActiveId(_), true)
  @deprecated("", "") val ButtonSensitivity: ExternalVar.Aux[ComboBox, org.gnome.gtk.SensitivityType] = ExternalVar[ComboBox, org.gnome.gtk.SensitivityType]("button-sensitivity", _.getButtonSensitivity(), _.setButtonSensitivity(_), true)
  @deprecated("", "") val Child: ExternalVar.Aux[ComboBox, org.gnome.gtk.Widget | Null] = ExternalVar[ComboBox, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  @deprecated("", "") val EntryTextColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("entry-text-column", _.getEntryTextColumn(), _.setEntryTextColumn(_), true)
  @deprecated("", "") val IdColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("id-column", _.getIdColumn(), _.setIdColumn(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[ComboBox, org.gnome.gtk.TreeModel | Null] = ExternalVar[ComboBox, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  @deprecated("", "") val PopupFixedWidth: ExternalVar.Aux[ComboBox, Boolean] = ExternalVar[ComboBox, Boolean]("popup-fixed-width", _.getPopupFixedWidth(), _.setPopupFixedWidth(_), true)
  @deprecated("", "") val RowSeparatorFunc: ExternalVar.Aux[ComboBox, org.gnome.gtk.TreeViewRowSeparatorFunc | Null] = ExternalVar[ComboBox, org.gnome.gtk.TreeViewRowSeparatorFunc | Null]("row-separator-func", _.getRowSeparatorFunc(), _.setRowSeparatorFunc(_), true)
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