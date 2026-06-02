package guarana
package gtk
package internal

transparent trait GridLayoutSupport { self: Grid.type =>
  type GridEntrySimple = (node: Widget, col: Int, row: Int)
  type GridEntry = NamedTuple.Concat[GridEntrySimple, (colSpan: Int, rowSpan: Int)]
  val Nodes: Var[Seq[GridEntrySimple | GridEntry]] = Var("nodes", Seq.empty, eagerEvaluation = true)

  extension (v: Grid) {
    def nodes = Nodes.forInstance(v)
  }

  protected def initNodesVar(v: Grid): VarContextAction[Unit] = {
    v.varUpdates := EventIterator.forsome {
      case v.nodes(_, entries) =>
        val grid = v.unwrap
        while (grid.getFirstChild() != null) grid.remove(grid.getFirstChild)
        // this `match` works in runtime due to tuple sizes
        entries.foreach {
          case e: GridEntrySimple => grid.attach(e.node.unwrap, e.col, e.row, 1, 1)
          case e: GridEntry => grid.attach(e.node.unwrap, e.col, e.row, e.colSpan, e.rowSpan)
        }
    }
  }
}
