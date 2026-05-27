package guarana
package gtk
opaque type EmojiChooser <: Popover = org.gnome.gtk.EmojiChooser & Popover
object EmojiChooser {
  ()
  extension (v: EmojiChooser) {
    def unwrap: org.gnome.gtk.EmojiChooser = v
    export unwrap.onEmojiPicked
  }
  def init(v: EmojiChooser): Unit = {
    Popover.init(v)
  }
  def uninitialized(): EmojiChooser = {
    val res = new org.gnome.gtk.EmojiChooser()
    res.asInstanceOf[EmojiChooser]
  }
}