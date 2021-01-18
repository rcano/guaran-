package guarana.swing
package util


enum Anchor {
  case Pos(x: Double, y: Double, anchorPoint: Anchor & Anchor.Relative)
  case Center extends Anchor, Anchor.Relative
  case TopLeft extends Anchor, Anchor.Relative
  case Top extends Anchor, Anchor.Relative
  case TopRight extends Anchor, Anchor.Relative
  case Right extends Anchor, Anchor.Relative
  case BotRight extends Anchor, Anchor.Relative
  case Bot extends Anchor, Anchor.Relative
  case BotLeft extends Anchor, Anchor.Relative
  case Left extends Anchor, Anchor.Relative

  def innerInOuter(inner: Bounds, outer: Bounds): (Double, Double) = this match {
    case Pos(x, y, anchorPoint) =>
      val (offsetx, offsety) = anchorPoint.pointIn(inner)
      (outer.getX + x - offsetx, outer.getY + y - offsety)
    case Center => (outer.getCenterX - inner.getWidth / 2, outer.getCenterY - inner.getHeight / 2)
    case TopLeft => (outer.getMinX, outer.getMinY)
    case Top => (outer.getCenterX - inner.getWidth / 2, outer.getMinY)
    case TopRight => (outer.getMaxX - inner.getWidth, outer.getMinY)
    case Right => (outer.getMaxX - inner.getWidth, outer.getCenterY - inner.getHeight / 2)
    case BotRight => (outer.getMaxX - inner.getWidth, outer.getMaxY - inner.getHeight)
    case Bot => (outer.getCenterX - inner.getWidth / 2, outer.getMaxY - inner.getHeight)
    case BotLeft => (outer.getMinX, outer.getMaxY - inner.getHeight)
    case Left => (outer.getMinX, outer.getCenterY - inner.getHeight / 2)
  }
  inline def pointIn(outer: Bounds): (Double, Double) = innerInOuter(Anchor.point, outer)
}
object Anchor {
  trait Relative

  private val point = Bounds()
}

