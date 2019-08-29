package guarana

trait Node {
  def children = Node.children.forInstance(this)

  def render(surface: Surface)(implicit ctx: Scenegraph#Context): Unit
}

object Node {
  val children = Var.autoName[Seq[Node]](Seq.empty)
}