package guarana
package swing
package style

import java.awt.{BasicStroke, Image, Paint}

case class Border(strokes: IArray[BorderStroke] = IArray.empty, images: IArray[BorderImage] = IArray.empty)

case class BorderStroke(
  topStroke: Paint,
  rightStroke: Paint,
  botStroke: Paint,
  leftStroke: Paint,
  topStyle: BasicStroke,
  rightStyle: BasicStroke,
  botStyle: BasicStroke,
  leftStyle: BasicStroke,
  radii: CornerRadii,
  insets: Insets
)
object BorderStroke {
  def simple(color: Paint, style: BasicStroke, radii: CornerRadii, insets: Insets) = 
    BorderStroke(color, color, color, color, style, style, style, style, radii, insets)
}

case class BorderWidths​(
  top: Double,
  right: Double,
  bottom: Double,
  left: Double,
  topAsPercentage: Boolean = false,
  rightAsPercentage: Boolean = false,
  bottomAsPercentage: Boolean = false,
  leftAsPercentage: Boolean = false
)
object BorderWidths​ {
  def all(size: Double) = BorderWidths​(size, size, size, size)
}

case class BorderImage(image: Image, widths: BorderWidths​, insets: Insets, slices: BorderWidths​, filled: Boolean, repeatX: BorderRepeat, repeatY: BorderRepeat)
enum BorderRepeat {
  case Repeat, Round, Spaces, Stretch
}