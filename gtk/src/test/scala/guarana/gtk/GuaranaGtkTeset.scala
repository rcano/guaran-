package guarana
package gtk

import org.gnome.gtk.{Application, Orientation}

import Binding.dyn

object GuaranaGtkTeset {
  def main(args: Array[String]): Unit = {
    val gtkApp = Application("guarana.gtk")
    gtkApp.onActivate(() => setup(gtkApp))
    gtkApp.run(null)
  }

  def setup(app: Application): Unit = try {

    Toolkit.loadCss("""
    button {
      background-color: red;
    }
    """)

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

      val window = ApplicationWindow(
        app,
        title = "Teset GTK",
        child = Box(
          Orientation.VERTICAL,
          10,
          nodes = Seq(
            Label("a string"),
            Button(child = Label("a button")),
            greeterLabel,
            nameTextInput
          )
        ),
        visible = true,
      )
    }

  } catch {
    case e => e.printStackTrace()
  }
}
