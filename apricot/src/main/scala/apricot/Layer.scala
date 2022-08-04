package apricot

import guarana.util.cfor
import apricot.graphics.GraphicsStack
import scala.collection.mutable.ArrayBuffer

/** Base definition of a Layer. Can be extended to overwrite draw and apply pre and post effects.
  */
open class Layer(val name: String) {
  val renderables = new ArrayBuffer[Renderable](128)

  def draw(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
    cfor(0, _ < renderables.length) { i =>
      renderables(i).render(graphicsStack, gContext)
      i + 1
    }
  }
}
