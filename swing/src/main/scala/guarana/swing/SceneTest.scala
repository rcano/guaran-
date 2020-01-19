package guarana.swing

import language.implicitConversions
import Binding.dyn
import scala.util.chaining._

@main def Test: Unit = {
  val scenegraph = Scenegraph()

  scenegraph.update {
    val presses = Var[Int]("presses", 0)

    val root = AbsolutePositioningPane()
    println("binding text")
    val label: Label = Label(
      toolTipText = "Report the location of the mouse",
      text = dyn {
        val (x, y) = root.mouseLocation()
        s"<html>Mouse location: <font color=green>$x, $y</font>\nPressed: ${presses()} times"
      }
    )

    label.bounds := dyn {
      val (x, y) = root.mouseLocation()
      val dim = label.prefSize().nn
      Rect(x, y, dim.getWidth.toInt, dim.getHeight.toInt)
    }
      
    root.nodes := Seq(
      label,
      Button(text = "Press me!", bounds = Rect(10, 10, 100, 50)).tap(
        _.actionEvents := EventIterator.foreach { e =>
          presses := presses() + 1
        }
      )
    )

    Frame(
      title = "Guarná test",
      bounds = Rect(1300, 300, 300, 300),
      visible = true,
      root = root,
    )
  }
}

@main def FormTest: Unit = {
  val scenegraph = Scenegraph()

  scenegraph.update {

    val userTf = TextField(columns = 5)
    val passwordTf = PasswordField(columns = 5)
    val login = Button(
      text = "Login",
      enabled = dyn { userTf.document().nonEmpty && passwordTf.document().nonEmpty },
      defaultCapable = true
    )

    Frame(
      title = "Guaraná test",
      bounds = Rect(1300, 300, 300, 300),
      visible = true,
      root = BorderPane(
        center = GridPane(
          rows = Seq(
            Seq(Label(text = "User:"), userTf),
            Seq(Label(text = "Password:"), passwordTf),
          ),
          hgap = 10,
          vgap = 10,
          autoCreateContainerGaps = true,
        ),
        bottom = login,
      ),
    )
  }
}