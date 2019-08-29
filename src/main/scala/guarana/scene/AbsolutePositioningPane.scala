package guarana
package scene

class AbsolutePositioningPane extends Node {
  def layout = AbsolutePositioningPane.layout.forInstance(this)
  def render(surface: Surface)(implicit ctx: Scenegraph#Context) = {
    surface.update { g2d =>
      for (((x, y), node) <- ctx(layout)) {
        g2d.translate(x, y)
        node.render(surface)
        g2d.translate(-x, -y)
      }
    }
  }
}
object AbsolutePositioningPane {
  val layout = Var.autoName[Seq[((Int, Int), Node)]](Seq.empty)

  def apply(layout: Binding[Seq[((Int, Int), Node)]])(implicit ctx: VarContext): AbsolutePositioningPane = {
    val res = new AbsolutePositioningPane()
    res.layout := layout
    res.children := Binding.dyn { res.layout().map(_._2) }
    res
  }
}
