package guarana
package gtk
opaque type LockButton <: Button = org.gnome.gtk.LockButton & Button
object LockButton {
  ()
  extension (v: LockButton) {
    def unwrap: org.gnome.gtk.LockButton = v
  }
  def init(v: LockButton): Unit = {
    Button.init(v)
  }
  def uninitialized(): LockButton = {
    val res = new org.gnome.gtk.LockButton()
    res.asInstanceOf[LockButton]
  }
}