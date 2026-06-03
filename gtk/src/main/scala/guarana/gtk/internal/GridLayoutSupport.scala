package guarana
package gtk
package internal

transparent trait GridLayoutSupport { self: Grid.type =>
  case class GridEntry(node: Widget, col: Int, row: Int, colSpan: Int = 1, rowSpan: Int = 1)
  val Nodes: Var[Seq[GridEntry]] = Var("nodes", Seq.empty, eagerEvaluation = true)

  extension (v: Grid) {
    def nodes = Nodes.forInstance(v)
  }

  protected def initNodesVar(v: Grid): VarContextAction[Unit] = {
    v.varUpdates := EventIterator.forsome { case v.nodes(_, entries) =>
      val grid = v.unwrap
      while (grid.getFirstChild() != null) grid.remove(grid.getFirstChild)
      // this `match` works in runtime due to tuple sizes
      entries.foreach(e => grid.attach(e.node.unwrap, e.col, e.row, 1, 1))
    }
  }
}
