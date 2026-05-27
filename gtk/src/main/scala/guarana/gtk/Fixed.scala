package guarana
package gtk
opaque type Fixed <: Widget = org.gnome.gtk.Fixed & Widget
object Fixed {
  ()
  extension (v: Fixed) {
    def unwrap: org.gnome.gtk.Fixed = v
  }
  def init(v: Fixed): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Fixed = {
    val res = new org.gnome.gtk.Fixed()
    res.asInstanceOf[Fixed]
  }
}