package guarana

import impl.SwingToolkitImpl._, Binding.{dyn, const}
import java.awt.Color
import scene._

object SmallSceneExample {
  def main(args: Array[String]): Unit = {
    val scene = new Scenegraph()

    scene.update { implicit ctx =>

      val pane = AbsolutePositioningPane(layout = dyn {
          val loc = scene.mouseLocation()
          Seq(
            (loc._1 - 50, loc._2 - 50) -> Rectangle(100.0, 100.0, Color.GREEN),
            loc -> Text(dyn(scene.mouseLocation().toString), Color.BLACK),
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
