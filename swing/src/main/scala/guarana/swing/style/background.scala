package guarana.swing
package style

import java.awt.{Image, Paint}

case class Background(fills: IArray[BackgroundFill] = IArray.empty, images: IArray[BackgroundImage] = IArray.empty)
case class BackgroundFill(fill: Paint, radii: CornerRadii, insets: Insets)

case class BackgroundImage(
  image: Image,
  repeatX: BackgroundRepeat,
  repeatY: BackgroundRepeat,
  position: (Double, Double),
  size: Double
)
case class BackgroundPosition(
  horizontalSide: HorizontalSide,
  horizontalPosition: Double,
  verticalSide: VerticalSide,
  verticalPosition: Double,
  horizontalAsPercentage: Boolean = false,
  verticalAsPercentage: Boolean = false,
)

enum BackgroundRepeat extends java.lang.Enum[BackgroundRepeat] {
  case NoRepeat, Repeat, Round, Spaces
}
trait Side
object Side {
  export HorizontalSide._
  export VerticalSide._
}
enum HorizontalSide extends java.lang.Enum[HorizontalSide], Side {
  case Right, Left
}
enum VerticalSide extends java.lang.Enum[VerticalSide], Side {
  case Top, Bot
}