package guarana
package gtk

import org.gnome.gtk.{Application, Orientation}
import scala.util.chaining.*

import Binding.dyn
import org.gnome.gtk.StringList

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

      val largeDropDown = DropDown()
      largeDropDown.model := StringList.builder().setStrings(Array.tabulate(2000)(i => s"Item $i")).build()

      val window = ApplicationWindow(
        application = app,
        title = "Teset GTK",
        child = Box(
          orientation = Orientation.VERTICAL,
          nodes = Seq(
            Label(text = "a string"),
            Button(child = Label("a button")).tap(_.unwrap.setLabel("and a label?")),
            greeterLabel,
            nameTextInput,
            Label(text = "You wrote something!", visible = dyn { nameTextInput.text().nonEmpty}),
            largeDropDown
          )
        ),
        visible = true,
      )
    }

  } catch {
    case e => e.printStackTrace()
  }
}
