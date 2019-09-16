package guarana
package scene

import java.awt.Color
import java.awt.Paint

class Text extends Node {
  def text = Text.text.forInstance(this)
  def paint = Text.paint.forInstance(this)

//  def render(surface: guarana.Surface)(implicit ctx: VarContext with Emitter.Context) = surface.update(_.drawString(ctx(text), 0, 0))
}
object Text {
  val text = Var.autoName[String]("")
  val paint = Var.autoName[Paint](Color.BLACK)
  def apply(text: Binding[String], paint: Binding[Paint])(implicit ctx: VarContext): Text = {
    val res = new Text
    res.text := text
    res.paint := paint
    res.render := Binding.dyn{
      val p = res.paint()
      val t = res.text()
      _.update { g2d =>
        g2d.setPaint(p)
        g2d.drawString(t, 0, 0)
      }
    }
    res
  }
}
