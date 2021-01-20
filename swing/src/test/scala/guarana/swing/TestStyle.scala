package guarana.swing

import java.awt.BasicStroke
import javax.swing.SwingConstants

class TestStyle extends Stylist {
 
  val corners = style.CornerRadii.all(4)
  val rootBackground = style.Background(fills = IArray(style.BackgroundFill(Color.WhiteSmoke, corners, Insets.all(0))))
  val tabBackground = style.Background(fills = IArray(style.BackgroundFill(Color.WhiteSmoke, style.CornerRadii.all(0), Insets.all(0))))
  val tabHighlightedBackground = style.Background(fills = IArray(style.BackgroundFill(Color.White, style.CornerRadii.simple(25, 0, 0, 0), Insets.all(0))))
  val bck = style.Background(fills = IArray(style.BackgroundFill(Color.LightSalmon, style.CornerRadii.all(0), Insets.all(0))))
  val hoverBck = style.Background(fills = IArray(style.BackgroundFill(Color.LightPink, style.CornerRadii.all(0), Insets.all(0))))
  val pressedBck = style.Background(fills = IArray(style.BackgroundFill(Color.DarkSalmon, style.CornerRadii.all(0), Insets.all(0))))


  def apply[T](info: Stylist.ScenegraphInfo)(prop: Keyed[ObsVal[T]]) = {
    lazy val emSize = info.emSize
    prop match {
      case Keyed(style.CssProperties.Border, jb: javax.swing.AbstractButton) =>
        val h = jb.getBounds.getHeight
        Some(
          style.Border(strokes = IArray(
            // border,
            style.BorderStroke.simple(
              java.awt.LinearGradientPaint(0, 0, 0, h.toFloat.max(1), Array[Float](0, 1), Array[Color](Color.LavenderBlush, Color.LavenderBlush.darker.nn)),
              BasicStroke((emSize / 10).toFloat), corners, Insets.all(emSize / 10))
          ))
        ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.Background, b: javax.swing.AbstractButton) => Some(
        if (b.getModel.isPressed || b.isSelected) pressedBck 
        else if (b.getModel.isRollover) hoverBck
        else bck
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.Background, _: javax.swing.JRootPane) => Some(
        rootBackground
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.Background, sb: javax.swing.JScrollBar) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.LavenderBlush, corners, Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.ScrollbarThumbBackground, jb: javax.swing.JScrollBar) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.LightSalmon, style.CornerRadii.simple(emSize / 2, emSize / 4, emSize / 2,  emSize / 4), Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.Background, sb: javax.swing.JProgressBar) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.LavenderBlush, corners, Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.ProgressBarBarBackground, sb: javax.swing.JProgressBar) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.LightSalmon, style.CornerRadii.all(0), Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.ProgressBarBarBorder, sb: javax.swing.JProgressBar) => Some(
        style.Border(strokes = IArray(style.BorderStroke.simple(Color.DarkSalmon.darker.nn, BasicStroke(3), corners, Insets(0, 0, 3, 0))))
      ).asInstanceOf[Option[T]]

      // case Keyed(style.CssProperties.TabBorder, sb: javax.swing.JTabbedPane) => Some(
      //   (tabInfo: style.TabInfo) =>  style.Border(
      //     strokes = IArray(style.BorderStroke.simple(if tabInfo.selected then Color.DarkSalmon.darker.nn else Color.LightSalmon, BasicStroke(3), corners, Insets.all(3))))
      // ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.TabBackground, sb: javax.swing.JTabbedPane) => Some(
        (tabInfo: style.TabInfo) =>
          if !tabInfo.selected then tabBackground else tabHighlightedBackground
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderThumbShape, slider: javax.swing.JSlider) => Some(
        // java.awt.Rectangle(0, 0, emSize.toInt, emSize.toInt)
        if (slider.getOrientation == SwingConstants.VERTICAL)
          java.awt.Polygon(Array[Int](0, 0, emSize.toInt), Array[Int](0, 20, 10), 3)
        else
          java.awt.Polygon(Array[Int](0, 10, 20), Array[Int](0, emSize.toInt, 0), 3)
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderThumbBackground, slider: javax.swing.JSlider) => Some(
        bck
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderThumbBorder, slider: javax.swing.JSlider) => Some {
        style.Border(strokes = IArray(
            // border,
            style.BorderStroke.simple(
              java.awt.LinearGradientPaint(0, 0, 0, 20, Array[Float](0, 1), Array[Color](Color.LavenderBlush, Color.LavenderBlush.darker)),
              BasicStroke((emSize / 10).toFloat), corners, Insets.all(emSize / 10))
          ))
      }.asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderTrackBackground, slider: javax.swing.JSlider) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.LightCyan, style.CornerRadii.all(emSize / 4), Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderTrackBorder, slider: javax.swing.JSlider) => Some(
          style.Border(strokes = IArray(
            style.BorderStroke.simple(
              java.awt.LinearGradientPaint(0, 0, 0, slider.getHeight, Array[Float](0, 1), Array[Color](Color.LightBlue, Color.LightBlue.darker)),
              BasicStroke((emSize / 10).toFloat), corners, Insets.all(emSize / 10))
          ))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderTrackFillBackground, slider: javax.swing.JSlider) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.LightSeaGreen, style.CornerRadii.all(emSize / 4), Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.SliderTrackFillBorder, slider: javax.swing.JSlider) => Some(
          style.Border(strokes = IArray(
            style.BorderStroke.simple(
              Color.RoyalBlue,
              BasicStroke((emSize / 10).toFloat), corners, Insets.all(emSize / 10))
          ))
      ).asInstanceOf[Option[T]]

      case Keyed(style.CssProperties.Background, slider: javax.swing.JSlider) => Some(
        style.Background(fills = IArray(style.BackgroundFill(Color.MediumTurquoise, style.CornerRadii.all(5), Insets.all(0))))
      ).asInstanceOf[Option[T]]

      case _ => 
        None
    }
  }
  def invalidateCache(node: Any) = ()
}
