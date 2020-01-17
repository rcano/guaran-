package guarana.swing

import language.implicitConversions
import Binding.dyn

@main def Test: Unit = {
  val scenegraph = Scenegraph()

  scenegraph.update {

    val root = AbsolutePositioningPane()
    val label: Label = Label(
      toolTipText = "Report the location of the mouse",
      text = dyn {
        val (x, y) = root.mouseLocation()
        s"<html>Mouse location: <font color=green>$x, $y</font>"
      }
    )
    label.bounds := dyn {
      val (x, y) = root.mouseLocation()
      val dim = label.prefSize()
      Rect(x, y, dim.getWidth.toInt, dim.getHeight.toInt)
    }
      
    root.nodes := Seq(label)

    Frame(
      title = "Guarná test",
      bounds = Rect(1300, 300, 300, 300),
      visible = true,
      root = root,
    )
  }
}