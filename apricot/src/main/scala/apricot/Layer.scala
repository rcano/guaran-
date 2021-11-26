package apricot

import guarana.util.cfor
import io.github.humbleui.skija.*
import scala.collection.mutable.ArrayBuffer

class Layer(val name: String) {
  val drawables = new ArrayBuffer[Drawable](128)
  var blendMode: Paint | Null = null

  def draw(canvas: Canvas): Unit = {
    if blendMode != null then canvas.saveLayer(0, 0, 0, 0, blendMode)
    cfor(0, _ < drawables.length) { i =>
      canvas.drawDrawable(drawables(i))
      i + 1
    }
    if blendMode != null then canvas.restore()
  }
}
