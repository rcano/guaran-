package guarana
package util

enum Anchor {

  /** Absolute positioning. Makes the anchorPoint of the source Bounds be placed exactly at (x, y) */
  case Pos(x: Double, y: Double, anchorPoint: Anchor & Anchor.Relative)

  /** Relative positioning. Makes the anchorPoint of the source Bounds be placed at the relative point */
  case PosRel(rel: Anchor & Anchor.Relative, anchorPoint: Anchor & Anchor.Relative)
  case Center extends Anchor, Anchor.Relative
  case TopLeft extends Anchor, Anchor.Relative
  case Top extends Anchor, Anchor.Relative
  case TopRight extends Anchor, Anchor.Relative
  case Right extends Anchor, Anchor.Relative
  case BotRight extends Anchor, Anchor.Relative
  case Bot extends Anchor, Anchor.Relative
  case BotLeft extends Anchor, Anchor.Relative
  case Left extends Anchor, Anchor.Relative

  def innerInOuter[Bounds: BoundsLike](inner: Bounds, outer: Bounds): (Double, Double) = this match {
    case Pos(x, y, anchorPoint) =>
      val (offsetx, offsety) = anchorPoint.pointIn(inner)
      (outer.x + x - offsetx, outer.y + y - offsety)
    case PosRel(rel, anchor) =>
      val (offsetx, offsety) = anchor.pointIn(inner)
      val (targetx, targety) = rel.pointIn(outer)
      (targetx - offsetx, targety - offsety)
    case Center => (outer.centerX - inner.width / 2, outer.centerY - inner.height / 2)
    case TopLeft => (outer.minX, outer.minY)
    case Top => (outer.centerX - inner.width / 2, outer.minY)
    case TopRight => (outer.maxX - inner.width, outer.minY)
    case Right => (outer.maxX - inner.width, outer.centerY - inner.height / 2)
    case BotRight => (outer.maxX - inner.width, outer.maxY - inner.height)
    case Bot => (outer.centerX - inner.width / 2, outer.maxY - inner.height)
    case BotLeft => (outer.minX, outer.maxY - inner.height)
    case Left => (outer.minX, outer.centerY - inner.height / 2)
  }
  inline def pointIn[Bounds: BoundsLike](outer: Bounds): (Double, Double) = innerInOuter(Anchor.point, outer)
}
object Anchor {
  trait Relative

  private def point[Bounds: BoundsLike] = summon[BoundsLike[Bounds]]()
}
