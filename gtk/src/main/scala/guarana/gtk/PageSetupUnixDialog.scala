package guarana
package gtk
import util.*
opaque type PageSetupUnixDialog <: Dialog = org.gnome.gtk.PageSetupUnixDialog & Dialog
object PageSetupUnixDialog extends VarsMap {
  val PageSetup: ExternalVar.Aux[PageSetupUnixDialog, org.gnome.gtk.PageSetup] = ExternalVar[PageSetupUnixDialog, org.gnome.gtk.PageSetup]("page-setup", _.getPageSetup(), _.setPageSetup(_), true)
  val PrintSettings: ExternalVar.Aux[PageSetupUnixDialog, org.gnome.gtk.PrintSettings | Null] = ExternalVar[PageSetupUnixDialog, org.gnome.gtk.PrintSettings | Null]("print-settings", _.getPrintSettings(), _.setPrintSettings(_), true)
  ()
  extension (v: PageSetupUnixDialog) {
    def unwrap: org.gnome.gtk.PageSetupUnixDialog = v
    def pageSetup: Var.Aux[org.gnome.gtk.PageSetup, v.type] = PageSetup.asInstanceOf[Var.Aux[org.gnome.gtk.PageSetup, v.type]]
    def printSettings: Var.Aux[org.gnome.gtk.PrintSettings | Null, v.type] = PrintSettings.asInstanceOf[Var.Aux[org.gnome.gtk.PrintSettings | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.PageSetupUnixDialog): PageSetupUnixDialog = {
    v.asInstanceOf
  }
  def init(v: PageSetupUnixDialog): ToolkitAction[Toolkit, Unit] = {
    Dialog.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null): PageSetupUnixDialog = {
    val res = new org.gnome.gtk.PageSetupUnixDialog(arg$0, arg$1)
    res.asInstanceOf[PageSetupUnixDialog]
  }
  def apply(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null, application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, decorated: Opt[Binding[Boolean]] = UnsetParam, defaultWidget: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, deletable: Opt[Binding[Boolean]] = UnsetParam, destroyWithParent: Opt[Binding[Boolean]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam, focus: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusVisible: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, gravity: Opt[Binding[org.gnome.gtk.WindowGravity]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, handleMenubarAccel: Opt[Binding[Boolean]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, hideOnClose: Opt[Binding[Boolean]] = UnsetParam, iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam, modal: Opt[Binding[Boolean]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, pageSetup: Opt[Binding[org.gnome.gtk.PageSetup]] = UnsetParam, printSettings: Opt[Binding[org.gnome.gtk.PrintSettings | Null]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, resizable: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, title: Opt[Binding[java.lang.String | Null]] = UnsetParam, titlebar: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, PageSetupUnixDialog] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
    ifSet(application, res.application := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(decorated, res.decorated := _)
    ifSet(defaultWidget, res.defaultWidget := _)
    ifSet(deletable, res.deletable := _)
    ifSet(destroyWithParent, res.destroyWithParent := _)
    ifSet(direction, res.direction := _)
    ifSet(display, res.display := _)
    ifSet(focus, res.focus := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusVisible, res.focusVisible := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(gravity, res.gravity := _)
    ifSet(halign, res.halign := _)
    ifSet(handleMenubarAccel, res.handleMenubarAccel := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hideOnClose, res.hideOnClose := _)
    ifSet(iconName, res.iconName := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(pageSetup, res.pageSetup := _)
    ifSet(printSettings, res.printSettings := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(resizable, res.resizable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(title, res.title := _)
    ifSet(titlebar, res.titlebar := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transientFor, res.transientFor := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}