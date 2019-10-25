package guarana

trait Node {
  def children = Node.children.forInstance(this)
  private[guarana] def parentsMut = Node.parentsMut.forInstance(this)
  def parents = Node.parentsMut.asObsValIn(this)
  def render = Node.render.forInstance(this)

  def show(implicit ctx: VarContext): String = toString()
}

object Node {
  val children = Var.autoName[Seq[Node]](Seq.empty)
  private[guarana] val parentsMut = Var.autoName[Set[Node]](Set.empty)
  def parents = parentsMut.asObsValIn(parentsMut)
  val render = Var.autoName[Surface => Unit](_ => ())
}