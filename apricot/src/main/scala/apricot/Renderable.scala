package apricot

import io.github.humbleui.skija.{Surface, Canvas}

trait Renderable {
  def render(surface: Surface, canvas: Canvas): Unit
}
