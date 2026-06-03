
package guarana
package gtk

import guarana.util.*

opaque type ComboBox <: guarana.gtk.Widget  = org.gnome.gtk.ComboBox & guarana.gtk.Widget
object ComboBox extends VarsMap {
  @deprecated("", "") val Active: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("active", _.getActive(), _.setActive(_), true)
  @deprecated("", "") val ActiveId: ExternalVar.Aux[ComboBox, java.lang.String | Null] = ExternalVar[ComboBox, java.lang.String | Null]("active-id", _.getActiveId(), _.setActiveId(_), true)
  @deprecated("", "") val ButtonSensitivity: ExternalVar.Aux[ComboBox, org.gnome.gtk.SensitivityType] = ExternalVar[ComboBox, org.gnome.gtk.SensitivityType]("button-sensitivity", _.getButtonSensitivity(), _.setButtonSensitivity(_), true)
  @deprecated("", "") val Child: ExternalVar.Aux[ComboBox, guarana.gtk.Widget | Null] = ExternalVar[ComboBox, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  @deprecated("", "") val EntryTextColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("entry-text-column", _.getEntryTextColumn(), _.setEntryTextColumn(_), true)
  @deprecated("", "") val IdColumn: ExternalVar.Aux[ComboBox, Int] = ExternalVar[ComboBox, Int]("id-column", _.getIdColumn(), _.setIdColumn(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[ComboBox, org.gnome.gtk.TreeModel | Null] = ExternalVar[ComboBox, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  @deprecated("", "") val PopupFixedWidth: ExternalVar.Aux[ComboBox, Boolean] = ExternalVar[ComboBox, Boolean]("popup-fixed-width", _.getPopupFixedWidth(), _.setPopupFixedWidth(_), true)
  @deprecated("", "") val RowSeparatorFunc: ExternalVar.Aux[ComboBox, org.gnome.gtk.TreeViewRowSeparatorFunc | Null] = ExternalVar[ComboBox, org.gnome.gtk.TreeViewRowSeparatorFunc | Null]("row-separator-func", _.getRowSeparatorFunc(), _.setRowSeparatorFunc(_), true)

  

  extension (v: ComboBox) {
    def unwrap: org.gnome.gtk.ComboBox = v

    @deprecated("", "") def active: Var.Aux[Int, v.type] = guarana.gtk.ComboBox.Active.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def activeId: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.ComboBox.ActiveId.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    @deprecated("", "") def buttonSensitivity: Var.Aux[org.gnome.gtk.SensitivityType, v.type] = guarana.gtk.ComboBox.ButtonSensitivity.asInstanceOf[Var.Aux[org.gnome.gtk.SensitivityType, v.type]]
    @deprecated("", "") def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.ComboBox.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    @deprecated("", "") def entryTextColumn: Var.Aux[Int, v.type] = guarana.gtk.ComboBox.EntryTextColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def idColumn: Var.Aux[Int, v.type] = guarana.gtk.ComboBox.IdColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def model: Var.Aux[org.gnome.gtk.TreeModel | Null, v.type] = guarana.gtk.ComboBox.Model.asInstanceOf[Var.Aux[org.gnome.gtk.TreeModel | Null, v.type]]
    @deprecated("", "") def popupFixedWidth: Var.Aux[Boolean, v.type] = guarana.gtk.ComboBox.PopupFixedWidth.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def rowSeparatorFunc: Var.Aux[org.gnome.gtk.TreeViewRowSeparatorFunc | Null, v.type] = guarana.gtk.ComboBox.RowSeparatorFunc.asInstanceOf[Var.Aux[org.gnome.gtk.TreeViewRowSeparatorFunc | Null, v.type]]

    

    export unwrap.{
      onActivate,
      onChanged,
      onDestroy,
      onDirectionChanged,
      onFormatEntryText,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveActive,
      onMoveFocus,
      onNotify,
      onPopdown,
      onPopup,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.ComboBox): ComboBox = 
    val res = v.asInstanceOf[ComboBox]
    
    res

  def init(v: ComboBox): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): ComboBox = {
    val res = new org.gnome.gtk.ComboBox()
    
    res.asInstanceOf[ComboBox]
  }
  
  def apply(
    
    active: Opt[Binding[Int]] = UnsetParam,
    activeId: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    buttonSensitivity: Opt[Binding[org.gnome.gtk.SensitivityType]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    entryTextColumn: Opt[Binding[Int]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    idColumn: Opt[Binding[Int]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[org.gnome.gtk.TreeModel | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    popupFixedWidth: Opt[Binding[Boolean]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    rowSeparatorFunc: Opt[Binding[org.gnome.gtk.TreeViewRowSeparatorFunc | Null]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ComboBox] = {
    val res = uninitialized()
    guarana.gtk.ComboBox.init(res)
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
        