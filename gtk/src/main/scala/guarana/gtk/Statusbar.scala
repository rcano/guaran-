package guarana
package gtk
opaque type Statusbar <: Widget = org.gnome.gtk.Statusbar & Widget
object Statusbar {
  ()
  extension (v: Statusbar) {
    def unwrap: org.gnome.gtk.Statusbar = v
    export unwrap.onTextPopped, unwrap.onTextPushed
  }
}