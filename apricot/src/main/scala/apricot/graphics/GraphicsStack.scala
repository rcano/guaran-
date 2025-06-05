package apricot
package graphics

import apricot.tools.GlfwWindow
import guarana.{ColorDefs, ColorLike}

/** Basic definitions required for a graphic stack.
  */
trait GraphicsStack {

  type Image
  object Image {
    export imageSupport.{*, given}
  }
  protected trait ImageSupport {
    def load(bytes: Array[Byte], format: String): Image
    given managedImage: Managed[Image]
  }
  protected val imageSupport: ImageSupport


  type Color
  object Color extends ColorDefs {
    type Color = GraphicsStack.this.Color

    override given ColorLike: ColorLike[Color] = colorLike
  }
  protected def colorLike: ColorLike[Color]

  type GraphicsContext <: GraphicsStack.GraphicsContext

  def setup(window: GlfwWindow): GraphicsContext
  def shutdown(): Unit

}

object GraphicsStack {
  trait GraphicsContext {
    /** Draws the entirety of the layers, presenting at the end */
    def renderLayers(layers: Iterable[Layer], clearFirst: Boolean, color: Int): Unit
    /** Closes this context and frees all associated resources */
    def close(): Unit
  }

  

  object NoOp extends GraphicsStack {
    type Image = Unit
    type Color = Int
    type GraphicsContext = GraphicsStack.GraphicsContext

    def err = throw new IllegalStateException("Uninitialized graphics context")
    protected object imageSupport extends ImageSupport {
      override def load(bytes: Array[Byte], format: String): Image = err
      override given managedImage: Managed[Image] = err
    }

    override protected def colorLike: ColorLike[Int] = err

    override def setup(window: GlfwWindow): GraphicsStack.GraphicsContext = err

    override def shutdown(): Unit = ()

  }
}
