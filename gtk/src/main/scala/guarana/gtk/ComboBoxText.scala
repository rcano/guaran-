package guarana
package gtk
import util.*
opaque type ComboBoxText <: ComboBox = org.gnome.gtk.ComboBoxText & ComboBox
object ComboBoxText extends VarsMap {
  ()
  extension (v: ComboBoxText) {
    def unwrap: org.gnome.gtk.ComboBoxText = v
  }
  def _wrap(v: org.gnome.gtk.ComboBoxText): ComboBoxText = {
    v.asInstanceOf
  }
  def init(v: ComboBoxText): ToolkitAction[Toolkit, Unit] = {
    ComboBox.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): ComboBoxText = {
    val res = new org.gnome.gtk.ComboBoxText()
    res.asInstanceOf[ComboBoxText]
  }
  def apply(active: Opt[Int] = UnsetParam, activeId: Opt[java.lang.String | Null] = UnsetParam, buttonSensitivity: Opt[org.gnome.gtk.SensitivityType] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, entryTextColumn: Opt[Int] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, idColumn: Opt[Int] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, model: Opt[org.gnome.gtk.TreeModel | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, popupFixedWidth: Opt[Boolean] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, rowSeparatorFunc: Opt[org.gnome.gtk.TreeViewRowSeparatorFunc | Null] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, ComboBoxText] = {
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