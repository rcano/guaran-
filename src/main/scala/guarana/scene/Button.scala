package guarana
package scene

class Button {
  def text = Button.text.forInstance(this)
//  def mouseClicks = Button.mouseClicks.forInstance(this)
//  def actions = Button.actions.forInstance(this)
}
object Button {
  val text = Var.autoName[String]("")
//  val mouseClicks = Emitter[MousEvent](ctx => ctx.mouseClicks.collect { case av: ActionEvent => av })
//  val actions = Emitter[Button, ActionEvent]((btn, ctx) => btn.mouseClicks.collect { case av: ActionEvent => av })
}

trait Emitter[T]