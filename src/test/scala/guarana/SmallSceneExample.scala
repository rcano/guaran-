package guarana

import impl.SwingToolkitImpl._, Binding.{dyn, const}
import java.awt.Color
import scene._

object SmallSceneExample {
  def main(args: Array[String]): Unit = {
    val scene = new Scenegraph()

    scene.update { implicit ctx =>

      val rect = Rectangle(100.0, 100.0, Color.GREEN)
      val text = Text(dyn(scene.mouseLocation().toString), Color.BLACK)

      val pane = AbsolutePositioningPane(layout = dyn {
          val loc = scene.mouseLocation()
//          println("repositioning")
          Seq(
            (loc._1 - 50, loc._2 - 50) -> rect,
            loc -> text,
          )
        })

      scene.rootNode := pane

    }

    val window = createWindow()
    window.title = "Guaraná Test"
    window.width = 400
    window.height = 400
    window.bind(scene)

  }
}
