package apricot

import apricot.graphics.GraphicsStack

/** Base type for things that can be renderer
  */
trait Renderable {
  def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit
}