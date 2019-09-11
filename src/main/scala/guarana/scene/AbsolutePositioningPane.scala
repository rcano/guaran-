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
//      println("try as I might")
      surface => surface.update { g2d =>
        for (((x, y), node) <- res.layout()) {
          println(s"rendering node $node at $x, $y")
          g2d.translate(x, y)
          val renderFunction = ctx(node.render)
          renderFunction(surface)
          g2d.translate(-x, -y)
        }
      }}
    res
  }
}
