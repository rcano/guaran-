package guarana
package gtk
opaque type Assistant <: Window = org.gnome.gtk.Assistant & Window
object Assistant {
  ()
  extension (v: Assistant) {
    def unwrap: org.gnome.gtk.Assistant = v
    export unwrap.onApply, unwrap.onCancel, unwrap.onClose, unwrap.onEscape, unwrap.onPrepare
  }
}