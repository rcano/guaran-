package guarana

trait Node {
  def children = Node.children.forInstance(this)

  def render = Node.render.forInstance(this)
}

object Node {
  val children = Var.autoName[Seq[Node]](Seq.empty)
  val render = Var.autoName[Surface => Unit](_ => ())
}