package guarana
package scene

import java.awt.Color
import java.awt.Paint

class Text extends Node {
  def text = Text.text.forInstance(this)
  def paint = Text.paint.forInstance(this)

  def render(surface: guarana.Surface)(implicit ctx: guarana.Scenegraph#Context) = surface.update(_.drawString(ctx(text), 0, 0))
}
object Text {
  val text = Var.autoName[String]("")
  val paint = Var.autoName[Paint](Color.BLACK)
  def apply(text: Binding[String], paint: Binding[Paint])(implicit ctx: VarContext): Text = {
    val res = new Text
    res.text := text
    res
  }
}
