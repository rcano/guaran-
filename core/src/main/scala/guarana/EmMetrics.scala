package guarana

/** When an EmMetrics context is given in a scope, it enables the syntax .em for Int, Double and Float*/
final class EmMetrics(val emVar: Keyed[ObsVal[Double]]) {
  extension (d: Double | Float | Int) {
    inline def em(using VarContext) =
      (inline d match {
        case i: Int => i.toDouble
        case d: Double => d
        case f: Float => f.toDouble
      }) * emVar.keyed()(using ValueOf(emVar.instance.asInstanceOf[emVar.keyed.ForInstance]))
  }
}
