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
  def apply(active: Opt[Binding[Int]] = UnsetParam, activeId: Opt[Binding[java.lang.String | Null]] = UnsetParam, buttonSensitivity: Opt[Binding[org.gnome.gtk.SensitivityType]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, entryTextColumn: Opt[Binding[Int]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, idColumn: Opt[Binding[Int]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, model: Opt[Binding[org.gnome.gtk.TreeModel | Null]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, popupFixedWidth: Opt[Binding[Boolean]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, rowSeparatorFunc: Opt[Binding[org.gnome.gtk.TreeViewRowSeparatorFunc | Null]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, ComboBoxText] = {
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