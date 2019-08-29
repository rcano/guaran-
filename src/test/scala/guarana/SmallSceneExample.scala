package guarana

import impl.SwingToolkitImpl._, Binding.{dyn, const}
import java.awt.Color
import scene._

object SmallSceneExample {
  def main(args: Array[String]): Unit = {
    val scene = new Scenegraph()

    scene.update { implicit ctx =>

      val pane = AbsolutePositioningPane(layout = dyn {
          val loc = ctx.mouseLocation()
          Seq(
            (loc._1 - 50, loc._2 - 50) -> Rectangle(100.0, 100.0, Color.GREEN),
            loc -> Text(loc.toString, Color.BLACK),
          )
        })

      ctx.rootNode := pane

      pane.layout := dyn { pane.layout() :+ ((10, 10) -> Text("hello world", Color.BLACK)) }
    }
  }
}
