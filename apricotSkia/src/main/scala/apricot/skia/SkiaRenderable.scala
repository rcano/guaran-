package apricot
package skia

import apricot.graphics.GraphicsStack

trait SkiaRenderable extends Renderable {
  final def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
    val skia = graphicsStack.asInstanceOf[SkiaGraphicsStack]
    renderSkia(skia, gContext.asInstanceOf[skia.GraphicsContext])
  }
  
  protected def renderSkia(skia: SkiaGraphicsStack, gContext: skia.GraphicsContext): Unit
}

object SkiaRenderable {
  inline def apply(inline f: (skia: SkiaGraphicsStack) => (gContext: skia.GraphicsContext) => Unit): SkiaRenderable = new SkiaRenderable {
    protected def renderSkia(skia: SkiaGraphicsStack, gContext: skia.GraphicsContext): Unit = f(skia)(gContext)
  }
}
