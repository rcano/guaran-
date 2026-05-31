
package guarana
package gtk

import guarana.util.*

opaque type Window <: guarana.gtk.Widget  = org.gnome.gtk.Window & guarana.gtk.Widget
object Window extends VarsMap {
  val Application: ExternalVar.Aux[Window, org.gnome.gtk.Application | Null] = ExternalVar[Window, org.gnome.gtk.Application | Null]("application", _.getApplication(), _.setApplication(_), true)
  val Child: ExternalVar.Aux[Window, guarana.gtk.Widget | Null] = ExternalVar[Window, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Decorated: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("decorated", _.getDecorated(), _.setDecorated(_), true)
  val DefaultWidget: ExternalVar.Aux[Window, guarana.gtk.Widget | Null] = ExternalVar[Window, guarana.gtk.Widget | Null]("default-widget", _.getDefaultWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setDefaultWidget(v.?(_.unwrap)), true)
  val Deletable: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("deletable", _.getDeletable(), _.setDeletable(_), true)
  val DestroyWithParent: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("destroy-with-parent", _.getDestroyWithParent(), _.setDestroyWithParent(_), true)
  val Display: ExternalVar.Aux[Window, org.gnome.gdk.Display] = ExternalVar[Window, org.gnome.gdk.Display]("display", _.getDisplay(), _.setDisplay(_), true)
  val Focus: ExternalVar.Aux[Window, guarana.gtk.Widget | Null] = ExternalVar[Window, guarana.gtk.Widget | Null]("focus", _.getFocus().?(guarana.gtk.Widget.wrap), (n, v) => n.setFocus(v.?(_.unwrap)), true)
  val FocusVisible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("focus-visible", _.getFocusVisible(), _.setFocusVisible(_), true)
  val Gravity: ExternalVar.Aux[Window, org.gnome.gtk.WindowGravity] = ExternalVar[Window, org.gnome.gtk.WindowGravity]("gravity", _.getGravity(), _.setGravity(_), true)
  val HandleMenubarAccel: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("handle-menubar-accel", _.getHandleMenubarAccel(), _.setHandleMenubarAccel(_), true)
  val HideOnClose: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("hide-on-close", _.getHideOnClose(), _.setHideOnClose(_), true)
  val IconName: ExternalVar.Aux[Window, java.lang.String | Null] = ExternalVar[Window, java.lang.String | Null]("icon-name", _.getIconName(), _.setIconName(_), true)
  val MnemonicsVisible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("mnemonics-visible", _.getMnemonicsVisible(), _.setMnemonicsVisible(_), true)
  val Modal: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Resizable: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("resizable", _.getResizable(), _.setResizable(_), true)
  val Title: ExternalVar.Aux[Window, java.lang.String | Null] = ExternalVar[Window, java.lang.String | Null]("title", _.getTitle(), _.setTitle(_), true)
  val Titlebar: ExternalVar.Aux[Window, guarana.gtk.Widget | Null] = ExternalVar[Window, guarana.gtk.Widget | Null]("titlebar", _.getTitlebar().?(guarana.gtk.Widget.wrap), (n, v) => n.setTitlebar(v.?(_.unwrap)), true)
  val TransientFor: ExternalVar.Aux[Window, org.gnome.gtk.Window | Null] = ExternalVar[Window, org.gnome.gtk.Window | Null]("transient-for", _.getTransientFor(), _.setTransientFor(_), true)

  

  extension (v: Window) {
    def unwrap: org.gnome.gtk.Window = v

    def application: Var.Aux[org.gnome.gtk.Application | Null, v.type] = guarana.gtk.Window.Application.asInstanceOf[Var.Aux[org.gnome.gtk.Application | Null, v.type]]
    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Window.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def decorated: Var.Aux[Boolean, v.type] = guarana.gtk.Window.Decorated.asInstanceOf[Var.Aux[Boolean, v.type]]
    def defaultWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Window.DefaultWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def deletable: Var.Aux[Boolean, v.type] = guarana.gtk.Window.Deletable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def destroyWithParent: Var.Aux[Boolean, v.type] = guarana.gtk.Window.DestroyWithParent.asInstanceOf[Var.Aux[Boolean, v.type]]
    def display: Var.Aux[org.gnome.gdk.Display, v.type] = guarana.gtk.Window.Display.asInstanceOf[Var.Aux[org.gnome.gdk.Display, v.type]]
    def focus: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Window.Focus.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def focusVisible: Var.Aux[Boolean, v.type] = guarana.gtk.Window.FocusVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def gravity: Var.Aux[org.gnome.gtk.WindowGravity, v.type] = guarana.gtk.Window.Gravity.asInstanceOf[Var.Aux[org.gnome.gtk.WindowGravity, v.type]]
    def handleMenubarAccel: Var.Aux[Boolean, v.type] = guarana.gtk.Window.HandleMenubarAccel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hideOnClose: Var.Aux[Boolean, v.type] = guarana.gtk.Window.HideOnClose.asInstanceOf[Var.Aux[Boolean, v.type]]
    def iconName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Window.IconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def mnemonicsVisible: Var.Aux[Boolean, v.type] = guarana.gtk.Window.MnemonicsVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def modal: Var.Aux[Boolean, v.type] = guarana.gtk.Window.Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    def resizable: Var.Aux[Boolean, v.type] = guarana.gtk.Window.Resizable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def title: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Window.Title.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def titlebar: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Window.Titlebar.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def transientFor: Var.Aux[org.gnome.gtk.Window | Null, v.type] = guarana.gtk.Window.TransientFor.asInstanceOf[Var.Aux[org.gnome.gtk.Window | Null, v.type]]

    

    export unwrap.{
      onActivateDefault,
      onActivateFocus,
      onCloseRequest,
      onDestroy,
      onDirectionChanged,
      onEnableDebugging,
      onHide,
      onKeynavFailed,
      onKeysChanged,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Window): Window = 
    val res = v.asInstanceOf[Window]
    
    res

  def init(v: Window): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): Window = {
    val res = new org.gnome.gtk.Window()
    
    res.asInstanceOf[Window]
  }
  
  def apply(
    
    application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    decorated: Opt[Binding[Boolean]] = UnsetParam,
    defaultWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    deletable: Opt[Binding[Boolean]] = UnsetParam,
    destroyWithParent: Opt[Binding[Boolean]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam,
    focus: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusVisible: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    gravity: Opt[Binding[org.gnome.gtk.WindowGravity]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    handleMenubarAccel: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hideOnClose: Opt[Binding[Boolean]] = UnsetParam,
    iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    titlebar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Toolkit ?=> VarContextAction[Window] = {
    val res = uninitialized()
    guarana.gtk.Window.init(res)
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
        