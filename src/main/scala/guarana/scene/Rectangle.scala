package guarana
package scene

import java.awt.Color
import java.awt.Paint

class Rectangle extends Node {
  def width = Rectangle.width.forInstance(this)
  def height = Rectangle.height.forInstance(this)
  def fill = Rectangle.fill.forInstance(this)

  override def show(implicit ctx: VarContext) = s"Rectangle(w=${width()},h=${height()},f=${fill()})"
}
object Rectangle {
  val width = Var.autoName[Double](0)
  val height = Var.autoName[Double](0)
  val fill = Var.autoName[Paint](Color.BLACK)

  def apply(
    width: Binding[Double],
    height: Binding[Double],
    fill: Binding[Paint],
  )(implicit ctx: VarContext): Rectangle = {
    val res = new Rectangle
    res.width := width
    res.height := height
    res.fill := fill
    res.render := Binding.dyn {
      val w = res.width()
      val h = res.height()
      val fill = res.fill()
      _.update { g2d =>
        g2d.setPaint(fill)
        g2d.fillRect(0, 0, w.toInt, h.toInt)
      }
    }
    res
  }
}
