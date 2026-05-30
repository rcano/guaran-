package guarana
package gtk

import org.gnome.gtk.{Application, Orientation}
import scala.util.chaining.*

import Binding.dyn

object GuaranaGtkTeset {
  def main(args: Array[String]): Unit = {
    val gtkApp = Application("guarana.gtk")
    gtkApp.onActivate(() => setup(gtkApp))
    gtkApp.run(null)
  }

  def setup(app: Application): Unit = try {
    val toolkit = Toolkit(app)

    toolkit.update {
      val nameTextInput = Entry(placeholderText = "name")
      val greeterLabel = Label("", text = dyn { 
        nameTextInput.text() match {
          case null | "" => "Type your name please"
          case name => s"Hello $name!"
        }
      })

      val window = ApplicationWindow(
        app,
        title = "Teset GTK",
        child = Box(Orientation.VERTICAL, 10).unwrap.tap { box =>
          box.append(Label("a string").unwrap)
          box.append(Button(child = Label("a button").unwrap).unwrap)
          box.append(greeterLabel.unwrap)
          box.append(nameTextInput.unwrap)
        },
        visible = true,
      )
    }

  } catch {
    case e => e.printStackTrace()
  }
}
