package guarana
package gtk
import util.*
opaque type ComboBox <: Widget = org.gnome.gtk.ComboBox & Widget
object ComboBox extends VarsMap {
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
    @deprecated("", "") def active: Var.Aux[Int, v.type] = Active.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def activeId: Var.Aux[java.lang.String | Null, v.type] = ActiveId.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    @deprecated("", "") def buttonSensitivity: Var.Aux[org.gnome.gtk.SensitivityType, v.type] = ButtonSensitivity.asInstanceOf[Var.Aux[org.gnome.gtk.SensitivityType, v.type]]
    @deprecated("", "") def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    @deprecated("", "") def entryTextColumn: Var.Aux[Int, v.type] = EntryTextColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def idColumn: Var.Aux[Int, v.type] = IdColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def model: Var.Aux[org.gnome.gtk.TreeModel | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gtk.TreeModel | Null, v.type]]
    @deprecated("", "") def popupFixedWidth: Var.Aux[Boolean, v.type] = PopupFixedWidth.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def rowSeparatorFunc: Var.Aux[org.gnome.gtk.TreeViewRowSeparatorFunc | Null, v.type] = RowSeparatorFunc.asInstanceOf[Var.Aux[org.gnome.gtk.TreeViewRowSeparatorFunc | Null, v.type]]
    export unwrap.onActivate, unwrap.onChanged, unwrap.onFormatEntryText, unwrap.onMoveActive, unwrap.onPopdown, unwrap.onPopup
  }
  def _wrap(v: org.gnome.gtk.ComboBox): ComboBox = {
    v.asInstanceOf
  }
  def init(v: ComboBox): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): ComboBox = {
    val res = new org.gnome.gtk.ComboBox()
    res.asInstanceOf[ComboBox]
  }
  def apply(active: Opt[Int] = UnsetParam, activeId: Opt[java.lang.String | Null] = UnsetParam, buttonSensitivity: Opt[org.gnome.gtk.SensitivityType] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, entryTextColumn: Opt[Int] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, idColumn: Opt[Int] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, model: Opt[org.gnome.gtk.TreeModel | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, popupFixedWidth: Opt[Boolean] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, rowSeparatorFunc: Opt[org.gnome.gtk.TreeViewRowSeparatorFunc | Null] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, ComboBox] = {
    val res = uninitialized()
    init(res)
    ifSet(active, res.active := _)
    ifSet(activeId, res.activeId := _)
    ifSet(buttonSensitivity, res.buttonSensitivity := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(entryTextColumn, res.entryTextColumn := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(idColumn, res.idColumn := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(popupFixedWidth, res.popupFixedWidth := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(rowSeparatorFunc, res.rowSeparatorFunc := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}