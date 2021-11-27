package apricot

import guarana.util.cfor
import io.github.humbleui.skija.*
import scala.collection.mutable.ArrayBuffer

class Layer(val name: String) {
  val renderables = new ArrayBuffer[Renderable](128)
  var blendMode: Paint | Null = null

  def draw(surface: Surface, canvas: Canvas): Unit = {
    if blendMode != null then canvas.saveLayer(0, 0, 0, 0, blendMode)
    cfor(0, _ < renderables.length) { i =>
      renderables(i).render(surface, canvas)
      i + 1
    }
    if blendMode != null then canvas.restore()
  }
}
