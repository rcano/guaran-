package guarana.swing
package style

import java.awt.{Image, Paint}

case class Background(fills: IArray[BackgroundFill] = IArray.empty, images: IArray[BackgroundImage] = IArray.empty) {
  val outsets = {
    val (top, right, bot, left) = fills.foldLeft((0d, 0d, 0d, 0d)) { case ((top, right, bot, left), fill) => 
      (top min fill.insets.top, right min fill.insets.right, bot min fill.insets.bot, left min fill.insets.left)
    }
    Insets(-top max 0, -right max 0, -bot max 0, -left max 0)
  }
}
case class BackgroundFill(fill: Paint, radii: CornerRadii, insets: Insets)

case class BackgroundImage(
  image: Image,
  repeatX: BackgroundRepeat,
  repeatY: BackgroundRepeat,
  position: BackgroundPosition,
  size: BackgroundSize,
)
case class BackgroundPosition(
  horizontalSide: HorizontalSide,
  horizontalPosition: Double,
  verticalSide: VerticalSide,
  verticalPosition: Double,
  horizontalAsPercentage: Boolean = false,
  verticalAsPercentage: Boolean = false,
)
enum BackgroundRepeat {
  case NoRepeat, Repeat, Round, Spaces
}
trait Side
object Side {
  export HorizontalSide._
  export VerticalSide._
}
enum HorizontalSide extends Side {
  case Right, Left
}
enum VerticalSide extends Side {
  case Top, Bot
}
enum BackgroundSize {
  case Dimension(
    width: Double,
    height: Double,
    widthAsPercentage: Boolean,
    heightAsPercentage: Boolean,
  )
  case Contain
  case Cover
}