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
        child = Box(Orientation.VERTICAL, 10).tap { box =>
          box.unwrap.append(Label("a string").unwrap)
          box.unwrap.append(Button(child = Label("a button")).unwrap)
          box.unwrap.append(greeterLabel.unwrap)
          box.unwrap.append(nameTextInput.unwrap)
        },
        visible = true,
      )
    }

  } catch {
    case e => e.printStackTrace()
  }
}
