package guarana.swing

import language.implicitConversions
import Binding.dyn

@main def Test: Unit = {
  val scenegraph = Scenegraph()

  scenegraph.update {
    val w = Frame(
      title = "Guarná test",
      bounds = Rect(1300, 300, 300, 300),
      visible = true,
    )
    val label = Label(toolTipText = "Report the location of the mouse")
    label.text := dyn {
      val (x, y) = label.mouseLocation()
      s"Mouse location: $x, $y"
    }

    w.root := label
  }
}