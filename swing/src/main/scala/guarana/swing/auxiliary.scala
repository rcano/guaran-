package guarana.swing

given metrics: (d: Double | Float | Int) extended with {
  inline def em(given sc: Scenegraph) = Binding.dyn {
    (inline d match {
      case i: Int => i.toDouble
      case d: Double => d
      case f: Float => f.toDouble
    }) * sc.emSize()
  }
}

// export java.awt.{Color, Shape, Paint, Stroke, Transparency, Font}