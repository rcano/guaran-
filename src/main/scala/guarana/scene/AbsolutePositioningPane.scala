package guarana
package scene

class AbsolutePositioningPane extends Node {
  def layout = AbsolutePositioningPane.layout.forInstance(this)
}
object AbsolutePositioningPane {
  val layout = Var.autoName[Seq[((Int, Int), Node)]](Seq.empty)

  def apply(layout: Binding[Seq[((Int, Int), Node)]])(implicit ctx: VarContext): AbsolutePositioningPane = {
    val res = new AbsolutePositioningPane()
    res.layout := layout
    res.children := Binding.dyn { res.layout().map(_._2) }
    res.render := Binding.dyn {
      val currLayout = res.layout()
      val toRender = currLayout.map(e => e._1 -> ctx(e._2.render))
      surface => surface.update { g2d =>
        for (((x, y), renderFunction) <- toRender) {
          g2d.translate(x, y)
          renderFunction(surface)
          g2d.translate(-x, -y)
        }
      }}
    res
  }
}
