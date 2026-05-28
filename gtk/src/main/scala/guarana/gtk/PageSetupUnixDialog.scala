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
  def apply(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null, application: Opt[org.gnome.gtk.Application | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, decorated: Opt[Boolean] = UnsetParam, defaultWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, deletable: Opt[Boolean] = UnsetParam, destroyWithParent: Opt[Boolean] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, display: Opt[org.gnome.gdk.Display] = UnsetParam, focus: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusVisible: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, gravity: Opt[org.gnome.gtk.WindowGravity] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, handleMenubarAccel: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hideOnClose: Opt[Boolean] = UnsetParam, iconName: Opt[java.lang.String | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, pageSetup: Opt[org.gnome.gtk.PageSetup] = UnsetParam, printSettings: Opt[org.gnome.gtk.PrintSettings | Null] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, resizable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String | Null] = UnsetParam, titlebar: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transientFor: Opt[org.gnome.gtk.Window | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, PageSetupUnixDialog] = {
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