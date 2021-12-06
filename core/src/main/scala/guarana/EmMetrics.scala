package guarana

/** When an EmMetrics context is given in a scope, it enables the syntax .em for Int, Double and Float*/
final class EmMetrics(val emKey: ObsVal[Double])(using instance: ValueOf[emKey.ForInstance]) {
  extension (d: Double | Float | Int) {
    inline def em(using VarContext) =
      (inline d match {
        case i: Int => i.toDouble
        case d: Double => d
        case f: Float => f.toDouble
      }) * emKey()
  }
}
