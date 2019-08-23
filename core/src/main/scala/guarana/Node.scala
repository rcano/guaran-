package guarana

trait Node {
  def children: Var[Seq[Node]]

  def render(ctx: Scenegraph#Context, surface: Surface): Unit
}

object Node {

}