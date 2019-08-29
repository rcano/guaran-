package guarana

class VarExample {
  class BorderPane {
    def north = BorderPane.north.forInstance(this)
    def south = BorderPane.south.forInstance(this)
    def east = BorderPane.east.forInstance(this)
    def west = BorderPane.west.forInstance(this)
    def center = BorderPane.center.forInstance(this)
  }
  object BorderPane {
    val north = Var.autoName[Option[String]](None)
    val south = Var.autoName[Option[String]](None)
    val east = Var.autoName[Option[String]](None)
    val west = Var.autoName[Option[String]](None)
    val center = Var.autoName[Option[String]](None)
    def apply(
      north: Binding[Option[String]] = None,
      south: Binding[Option[String]] = None,
      east: Binding[Option[String]] = None,
      west: Binding[Option[String]] = None,
      center: Binding[Option[String]] = None
    )(implicit ctx: VarContext): BorderPane = {
      val res = new BorderPane
      res.north := north
      res.south := south
      res.east := east
      res.west := west
      res.center := center
      res
    }
  }

  implicit val ctx: VarContext = null
  val bp = BorderPane(north = Some("blah"))

  val lengthOfAll = Var.autoName[Int](0)
  lengthOfAll := Binding.bind(Seq(bp.north, bp.south, bp.east, bp.west, bp.center), Seq()) { implicit ctx =>
    val otherBp = BorderPane(center = Some("center"))
    println("secondary bp built " + otherBp)

    ctx(bp.north).map(_.length).getOrElse(0) +
    ctx(bp.south).map(_.length).getOrElse(0) +
    ctx(bp.east).map(_.length).getOrElse(0) +
    ctx(bp.west).map(_.length).getOrElse(0) +
    ctx(bp.center).map(_.length).getOrElse(0)
  }

  lengthOfAll := Binding.dyn {
    bp.north().map(_.length).getOrElse(0) +
    bp.south().map(_.length).getOrElse(0) +
    bp.east().map(_.length).getOrElse(0) +
    bp.west().map(_.length).getOrElse(0) +
    bp.center().map(_.length).getOrElse(0)
  }

  val someVar = Var.autoName[String]("init")

  someVar := Binding.dyn {
    lengthOfAll()
    val subVar = Var.autoName[String]("init")(implicitly)
    subVar := Binding.dyn {
      subVar := "rebound"
      s"${lengthOfAll()} length"
    }
    subVar()
  }
}
