package guarana

trait Node {
  def children: Var[Seq[Node]]
}

object Node {

}