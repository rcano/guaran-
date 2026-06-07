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
    Toolkit.update {
      val nameTextInput = Entry(placeholderText = "name")
      val greeterLabel = Label(
        "",
        text = dyn {
          nameTextInput.text() match {
            case null | "" => "Type your name please"
            case name => s"Hello $name!"
          }
        }
      )

      val b = Button()

      val window = ApplicationWindow(
        app,
        title = "Teset GTK",
        child = Box(
          Orientation.VERTICAL,
          10,
          nodes = Seq(
            Label("a string"),
            Button(child = Label("a button")).tap(_.unwrap.setLabel("and a label?")),
            greeterLabel,
            nameTextInput,
            Label("You wrote something!", visible = dyn { nameTextInput.text().nonEmpty})
          )
        ),
        visible = true,
      )
    }

  } catch {
    case e => e.printStackTrace()
  }
}
