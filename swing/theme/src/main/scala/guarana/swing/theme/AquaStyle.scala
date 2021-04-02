package guarana.swing
package theme

import com.jhlabs.image.*
import java.awt.{BasicStroke, Dimension}
import javax.swing._
import scala.concurrent.duration.DurationInt
import style.*

class AquaStyle()(using scenegraph: Scenegraph) extends Stylist {
  val NoStroke = BasicStroke(0f)
  val NoCorners = CornerRadii.all(0)
  val NoInsets = Insets.all(0)

  val baseBackgroundColor = Color.web("#f2f3f5")
  val baseBackgroundColorShadow = baseBackgroundColor.deriveHSB(1, 1, 0.9, 1)
  val baseBackgroundColorHighlight = Color.White
  val baseBackgroundColorLowlight = Color.web("#e8e9eb")

  val foregroundColor = Color.DodgerBlue
  val foregroundColorShadow = foregroundColor.deriveHSB(1, 1, 0.9, 1)
  val foregroundColorHighlight = foregroundColor.deriveHSB(1, 0.85, 1, 1)

  val textColor = Color.SlateGray
  val textColorStrong = Color.DimGrey
  val textColorLight = Color.Gainsboro

  val darkBackgroundTextColor = Color.GhostWhite
  val darkBackgroundTextColorStrong = Color.White
  val darkBackgroundTextColorLight = Color.Gainsboro

  val rootBackground = Background(
    fills = IArray(BackgroundFill(baseBackgroundColor, NoCorners, NoInsets))
  )

  val buttonBackground = Background(fills = IArray(BackgroundFill(foregroundColor, NoCorners, NoInsets)))
  val buttonBackgroundArmed = Background(fills = IArray(BackgroundFill(foregroundColorShadow, NoCorners, NoInsets)))

  val textAreaBackground = Background(fills = IArray(BackgroundFill(baseBackgroundColorHighlight, NoCorners, NoInsets)))

  val cellBackgroundEven = Background(fills = IArray(BackgroundFill(baseBackgroundColorLowlight, NoCorners, NoInsets)))
  val cellBackgroundEvenSelected = Background(fills = IArray(BackgroundFill(baseBackgroundColorLowlight.interp(Color.LightBlue, 0.2), NoCorners, NoInsets)))
  val cellBackgroundOdd = Background(fills = IArray(BackgroundFill(baseBackgroundColor, NoCorners, NoInsets)))
  val cellBackgroundOddSelected = Background(fills = IArray(BackgroundFill(baseBackgroundColor.interp(Color.LightBlue, 0.2), NoCorners, NoInsets)))

  var emDependentSettings: Settings = null

  /** emSize dependent settings, these are recomputed when the emSize changes */
  class Settings(val emSize: Double) {

    val shadowEffect = ShadowFilter(emSize.toFloat, 5, -5, 0.5f)

    val scrollBarBackground = Background(fills = IArray(BackgroundFill(baseBackgroundColorLowlight, CornerRadii.all(emSize / 5), NoInsets)))
  }

  def apply[T](prop: Keyed[ObsVal[T]]): Option[T] = {
    val emSize = scenegraph.stateReader.emSize
    val info = scenegraph.stateReader
    if emDependentSettings == null || emDependentSettings.emSize != emSize then emDependentSettings = Settings(emSize)
    val settings = emDependentSettings
    import settings._

    val res: Option[?] = prop match
    case Keyed(CssProperties.Background, _: JRootPane) => Some(rootBackground)
    
    case Keyed(CssProperties.Background, jb: JButton) => Some(if jb.getModel.isPressed then buttonBackgroundArmed else buttonBackground)
    case Keyed(Node.Foreground, _: JButton) => Some(darkBackgroundTextColor)
    case Keyed(CssProperties.Padding, _: JButton) => Some(Insets(left = emSize))
    case Keyed(CssProperties.Effect, _: JButton) => Some(shadowEffect)

    case Keyed(CssProperties.Background, sb: JScrollBar) => Some(scrollBarBackground)
    case Keyed(CssProperties.ScrollbarThumbBackground, jb: JScrollBar) => 
      val prefSize = jb.getPreferredSize
      Some(
        Background(fills = IArray(BackgroundFill(baseBackgroundColorShadow.darker(), CornerRadii.simple(prefSize.width / 2, prefSize.width / 2, prefSize.width / 2,  prefSize.width / 2), NoInsets)))
      )

    case Keyed(CssProperties.Background, sb: JTextArea) => Some(textAreaBackground)
    case Keyed(TextComponent.SelectionColor, _: text.JTextComponent) => Some(baseBackgroundColorShadow)

    case Keyed(CssProperties.ComboBoxValueBackground, _) => Some(Background(fills = IArray(BackgroundFill(baseBackgroundColor, NoCorners, NoInsets))))

    //list cell renderer
    case Keyed(CssProperties.Background, l: JLabel) if info.getOrDefault(CssProperties.Classes.forInstance[l.type])
        .contains(guarana.swing.plaf.CssDefaultListRenderer.Class) =>
      val (item, index, selected, focused) = (
        info.getOrDefault(CssProperties.ListCellRendererItem.forInstance[l.type]),
        info.getOrDefault(CssProperties.ListCellRendererItemIndex.forInstance[l.type]),
        info.getOrDefault(CssProperties.ListCellRendererSelected.forInstance[l.type]),
        info.getOrDefault(CssProperties.ListCellRendererFocused.forInstance[l.type])
      )
      Some(
        if index % 2 == 0 then 
          if selected then cellBackgroundEvenSelected else cellBackgroundEven
        else 
          if selected then cellBackgroundOddSelected else cellBackgroundOdd
      )

    case _ => None

    res.asInstanceOf[Option[T]]
  }

  def invalidateCache(node: Any) = ()

  def installDefaults(node: Any): Unit = {
    // remove opacity for some components
    node match 
      case c: (JButton | JScrollBar) => LookAndFeel.installProperty(c, "opaque", false)
      case _ =>

    node match
      case jsb: JScrollBar =>
        val s = (scenegraph.stateReader.emSize / 2).toInt
        if jsb.getOrientation == java.awt.Adjustable.VERTICAL then jsb.setPreferredSize(Dimension(s, Int.MaxValue))
        else jsb.setPreferredSize(Dimension(Int.MaxValue, s))

        //set up a hover listener, to do so we need to ensure the scroll bar is initialized
        //in order to use the hovered var
        val c = Component.wrap(jsb)
        if !Component.isInitialized(c) then Component.init(c)
        var focusTimer: Timer = null
        import animation.Timeline, Timeline.*
        scenegraph.update {
          val opacity = CssProperties.Opacity.forInstance(c)
          val minOpacity = 0.25
          opacity := minOpacity
          c.varUpdates := EventIterator.foreach {
            case c.hovered(_, hovered) =>
              if focusTimer != null then focusTimer.stop()
              if hovered then
                focusTimer = Timeline(
                  IArray(KeyFrame(250.millis, opacity, minOpacity, 1, EaseBothCurve)),
                  2,
                  ups = 30
                )
              else
                focusTimer = Timeline(
                  IArray(KeyFrame(250.millis, opacity, 1, minOpacity, EaseBothCurve)),
                  2,
                  ups = 30
                )
              focusTimer.start()
            case opacity(_, op) => jsb.repaint()
            case _ =>
          }
        }
      case _ =>
  }
  
  def uninstallDefaults(node: Any): Unit = ()
}
