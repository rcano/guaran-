package guarana.gtk

import org.gnome.gobject.GObject
import org.gnome.gtk.{Unit as MetricUnit, *}
import org.gnome.gobject.ParamSpec

object RawGtkTeset {
  def main(args: Array[String]): Unit = {
    val gtkApp = Application("guarana.gtk")
    gtkApp.onActivate(() => setup(gtkApp))
    gtkApp.run(null)
  }

  def setup(app: Application): Unit = try {
    val window = ApplicationWindow(app)
    
    val notifyCallback = NotifyBridge(param => println(s"${param.getName} changed to ${param}"))
    window.setTitle("Test")
    window.connect("notify", notifyCallback, true)

    val box = Box.builder().setSpacing(10).setOrientation(Orientation.VERTICAL).build()
    box.append(Label("a string"))
    val button = Button.builder().setLabel("a button").build()
    button.onClicked(() => ())
    button.connect("notify", notifyCallback, true)
    box.append(button)

    window.setChild(box)
    window.setVisible(true)
  } catch {
    case e => e.printStackTrace()
  }
}
