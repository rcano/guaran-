package guarana
package gtk
opaque type Window <: Widget = org.gnome.gtk.Window & Widget
object Window {
  val Decorated: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("decorated", _.getDecorated(), _.setDecorated(_), true)
  val Deletable: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("deletable", _.getDeletable(), _.setDeletable(_), true)
  val DestroyWithParent: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("destroy-with-parent", _.getDestroyWithParent(), _.setDestroyWithParent(_), true)
  val FocusVisible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("focus-visible", _.getFocusVisible(), _.setFocusVisible(_), true)
  val Gravity: ExternalVar.Aux[Window, org.gnome.gtk.WindowGravity | Null] = ExternalVar[Window, org.gnome.gtk.WindowGravity | Null]("gravity", _.getGravity(), _.setGravity(_), true)
  val HandleMenubarAccel: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("handle-menubar-accel", _.getHandleMenubarAccel(), _.setHandleMenubarAccel(_), true)
  val HideOnClose: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("hide-on-close", _.getHideOnClose(), _.setHideOnClose(_), true)
  val MnemonicsVisible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("mnemonics-visible", _.getMnemonicsVisible(), _.setMnemonicsVisible(_), true)
  val Modal: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Resizable: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("resizable", _.getResizable(), _.setResizable(_), true)
  ()
  extension (v: Window) {
    def unwrap: org.gnome.gtk.Window = v
    export unwrap.onActivateDefault, unwrap.onActivateFocus, unwrap.onCloseRequest, unwrap.onEnableDebugging, unwrap.onKeysChanged
  }
  def init(v: Window): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Window = {
    val res = new org.gnome.gtk.Window()
    res.asInstanceOf[Window]
  }
}