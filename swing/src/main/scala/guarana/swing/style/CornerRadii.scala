package guarana.swing.style

case class CornerRadii(
  topLeftHorizontalRadius: Double,
  topLeftVerticalRadius: Double,
  topRightVerticalRadius: Double,
  topRightHorizontalRadius: Double,
  botRightHorizontalRadius: Double,
  botRightVerticalRadius: Double,
  botLeftVerticalRadius: Double,
  botLeftHorizontalRadius: Double,
  topLeftHorizontalRadiusAsPercent: Boolean = false,
  topLeftVerticalRadiusAsPercent: Boolean = false,
  topRightVerticalRadiusAsPercent: Boolean = false,
  topRightHorizontalRadiusAsPercent: Boolean = false,
  botRightHorizontalRadiusAsPercent: Boolean = false,
  botRightVerticalRadiusAsPercent: Boolean = false,
  botLeftVerticalRadiusAsPercent: Boolean = false,
  botLeftHorizontalRadiusAsPercent: Boolean = false
) {
  def isSquare: Boolean = 
    topLeftHorizontalRadius == 0 &&
    topLeftVerticalRadius == 0 &&
    topRightVerticalRadius == 0 &&
    topRightHorizontalRadius == 0 &&
    botRightHorizontalRadius == 0 &&
    botRightVerticalRadius == 0 &&
    botLeftVerticalRadius == 0 &&
    botLeftHorizontalRadius == 0
}
object CornerRadii {
  def simple(topLeft: Double, topRight: Double, botRight: Double, botLeft: Double, percentage: Boolean = false) =
    CornerRadii(topLeft, topLeft, topRight, topRight, botRight, botRight, botLeft, botLeft, percentage, percentage, percentage, percentage, percentage, percentage, percentage, percentage)
  def all(radii: Double) = simple(radii, radii, radii, radii)
}