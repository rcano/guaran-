package apricot

import io.github.humbleui.skija.{Surface, Canvas}

/** Base type for things that can be renderer
  */
trait Renderable {
  def render(surface: Surface, canvas: Canvas): Unit
}