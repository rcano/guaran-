package apricot

/** Simple trait for renderables that support scrolling. Code that manages this scrollable (such as maybe a scrollbar) is
  * responsible for modifying the offset
  */
trait Scrollable {
  var offsetX = 0.0
  var offsetY = 0.0

  def scrollTo(x: Double, y: Double): Unit =
    offsetX = x
    offsetY = y
}