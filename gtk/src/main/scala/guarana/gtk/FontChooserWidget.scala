package guarana
package gtk
opaque type FontChooserWidget <: Widget = org.gnome.gtk.FontChooserWidget & Widget
object FontChooserWidget {
  ()
  extension (v: FontChooserWidget) {
    def unwrap: org.gnome.gtk.FontChooserWidget = v
  }
  def init(v: FontChooserWidget): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FontChooserWidget = {
    val res = new org.gnome.gtk.FontChooserWidget()
    res.asInstanceOf[FontChooserWidget]
  }
}