package guarana
package gtk
import util.*
opaque type Window <: Widget = org.gnome.gtk.Window & Widget
object Window extends VarsMap {
  val Application: ExternalVar.Aux[Window, org.gnome.gtk.Application | Null] = ExternalVar[Window, org.gnome.gtk.Application | Null]("application", _.getApplication(), _.setApplication(_), true)
  val Child: ExternalVar.Aux[Window, org.gnome.gtk.Widget | Null] = ExternalVar[Window, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Decorated: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("decorated", _.getDecorated(), _.setDecorated(_), true)
  val DefaultWidget: ExternalVar.Aux[Window, org.gnome.gtk.Widget | Null] = ExternalVar[Window, org.gnome.gtk.Widget | Null]("default-widget", _.getDefaultWidget(), _.setDefaultWidget(_), true)
  val Deletable: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("deletable", _.getDeletable(), _.setDeletable(_), true)
  val DestroyWithParent: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("destroy-with-parent", _.getDestroyWithParent(), _.setDestroyWithParent(_), true)
  val Display: ExternalVar.Aux[Window, org.gnome.gdk.Display] = ExternalVar[Window, org.gnome.gdk.Display]("display", _.getDisplay(), _.setDisplay(_), true)
  val Focus: ExternalVar.Aux[Window, org.gnome.gtk.Widget | Null] = ExternalVar[Window, org.gnome.gtk.Widget | Null]("focus", _.getFocus(), _.setFocus(_), true)
  val FocusVisible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("focus-visible", _.getFocusVisible(), _.setFocusVisible(_), true)
  val Gravity: ExternalVar.Aux[Window, org.gnome.gtk.WindowGravity] = ExternalVar[Window, org.gnome.gtk.WindowGravity]("gravity", _.getGravity(), _.setGravity(_), true)
  val HandleMenubarAccel: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("handle-menubar-accel", _.getHandleMenubarAccel(), _.setHandleMenubarAccel(_), true)
  val HideOnClose: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("hide-on-close", _.getHideOnClose(), _.setHideOnClose(_), true)
  val IconName: ExternalVar.Aux[Window, java.lang.String | Null] = ExternalVar[Window, java.lang.String | Null]("icon-name", _.getIconName(), _.setIconName(_), true)
  val MnemonicsVisible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("mnemonics-visible", _.getMnemonicsVisible(), _.setMnemonicsVisible(_), true)
  val Modal: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Resizable: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("resizable", _.getResizable(), _.setResizable(_), true)
  val Title: ExternalVar.Aux[Window, java.lang.String | Null] = ExternalVar[Window, java.lang.String | Null]("title", _.getTitle(), _.setTitle(_), true)
  val Titlebar: ExternalVar.Aux[Window, org.gnome.gtk.Widget | Null] = ExternalVar[Window, org.gnome.gtk.Widget | Null]("titlebar", _.getTitlebar(), _.setTitlebar(_), true)
  val TransientFor: ExternalVar.Aux[Window, org.gnome.gtk.Window | Null] = ExternalVar[Window, org.gnome.gtk.Window | Null]("transient-for", _.getTransientFor(), _.setTransientFor(_), true)
  ()
  extension (v: Window) {
    def unwrap: org.gnome.gtk.Window = v
    def application: Var.Aux[org.gnome.gtk.Application | Null, v.type] = Application.asInstanceOf[Var.Aux[org.gnome.gtk.Application | Null, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def decorated: Var.Aux[Boolean, v.type] = Decorated.asInstanceOf[Var.Aux[Boolean, v.type]]
    def defaultWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = DefaultWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def deletable: Var.Aux[Boolean, v.type] = Deletable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def destroyWithParent: Var.Aux[Boolean, v.type] = DestroyWithParent.asInstanceOf[Var.Aux[Boolean, v.type]]
    def display: Var.Aux[org.gnome.gdk.Display, v.type] = Display.asInstanceOf[Var.Aux[org.gnome.gdk.Display, v.type]]
    def focus: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Focus.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def focusVisible: Var.Aux[Boolean, v.type] = FocusVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def gravity: Var.Aux[org.gnome.gtk.WindowGravity, v.type] = Gravity.asInstanceOf[Var.Aux[org.gnome.gtk.WindowGravity, v.type]]
    def handleMenubarAccel: Var.Aux[Boolean, v.type] = HandleMenubarAccel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hideOnClose: Var.Aux[Boolean, v.type] = HideOnClose.asInstanceOf[Var.Aux[Boolean, v.type]]
    def iconName: Var.Aux[java.lang.String | Null, v.type] = IconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def mnemonicsVisible: Var.Aux[Boolean, v.type] = MnemonicsVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def modal: Var.Aux[Boolean, v.type] = Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    def resizable: Var.Aux[Boolean, v.type] = Resizable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def title: Var.Aux[java.lang.String | Null, v.type] = Title.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def titlebar: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Titlebar.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def transientFor: Var.Aux[org.gnome.gtk.Window | Null, v.type] = TransientFor.asInstanceOf[Var.Aux[org.gnome.gtk.Window | Null, v.type]]
    export unwrap.onActivateDefault, unwrap.onActivateFocus, unwrap.onCloseRequest, unwrap.onEnableDebugging, unwrap.onKeysChanged
  }
  def _wrap(v: org.gnome.gtk.Window): Window = {
    v.asInstanceOf
  }
  def init(v: Window): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Window = {
    val res = new org.gnome.gtk.Window()
    res.asInstanceOf[Window]
  }
  def apply(application: Opt[org.gnome.gtk.Application | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, decorated: Opt[Boolean] = UnsetParam, defaultWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, deletable: Opt[Boolean] = UnsetParam, destroyWithParent: Opt[Boolean] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, display: Opt[org.gnome.gdk.Display] = UnsetParam, focus: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusVisible: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, gravity: Opt[org.gnome.gtk.WindowGravity] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, handleMenubarAccel: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hideOnClose: Opt[Boolean] = UnsetParam, iconName: Opt[java.lang.String | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, resizable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String | Null] = UnsetParam, titlebar: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transientFor: Opt[org.gnome.gtk.Window | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Window] = {
    val res = uninitialized()
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