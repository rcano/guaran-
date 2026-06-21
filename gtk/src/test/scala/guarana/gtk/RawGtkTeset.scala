package guarana.gtk

import org.gnome.gobject.GObject
import org.gnome.gtk.{Unit as MetricUnit, *}
import org.gnome.gobject.ParamSpec
import scala.util.Try
import scala.util.Using
import java.lang.foreign.Arena
import org.gnome.glib.MarkupParseContext
import org.gnome.glib.MarkupParser
import org.gnome.glib.MarkupParseFlags
import scala.util.chaining.*

object RawGtkTeset {
  def main(args: Array[String]): Unit = {
    val gtkApp = Application("guarana.gtk")
    gtkApp.onActivate(() => setup(gtkApp))
    gtkApp.run(null)
  }

  def setup(app: Application): Unit = Using.Manager { use =>
  }.failed.foreach(_.printStackTrace())
}
