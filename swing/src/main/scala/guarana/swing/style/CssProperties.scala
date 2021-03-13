package guarana.swing
package style

import java.awt.{BasicStroke, Shape}

object CssProperties {
  val EmptyBackground = style.Background()
  val EmptyBorder = style.Border()
  val EmptyInsets = Insets()

  val Background = Var[style.Background]("css-background", EmptyBackground)
  val Border = Var[style.Border]("css-border", EmptyBorder)
  val Effect = Var[java.awt.image.BufferedImageOp | Null]("css-effect", null)

  /** Dictates padding for a node's content area.
   * 
   * This area is empty and goes between the border and the content of the node.
   * Note that most Swing controls don't support this concept, and the best we can do
   * is increase their width and height, while the control centers the content. 
   */
  val Padding = Var[Insets]("css-insets", EmptyInsets)

  // Scrollbar specific
  val ScrollbarThumbBackground = Var[style.Background]("css-scrollbar-thumb-background", EmptyBackground)
  val ScrollbarThumbBorder = Var[style.Border]("css-scrollbar-thumb-border", EmptyBorder)

  // Progress bar specific
  val ProgressBarBarBackground = Var[style.Background]("css-progressbar-bar-background", EmptyBackground)
  val ProgressBarBarBorder = Var[style.Border]("css-progressbar-bar-border", EmptyBorder)

  // Text component specifics
  val PlaceholderText = Var[String | Null]("css-placeholder-text", null)
  val PlaceholderTextPaint = Var[Paint | Null]("css-placeholder-text-paint", null)

  // TabbedPane specific
  private val emptyTabBorder = style.Border(strokes = IArray(BorderStroke.simple(Color.Transparent, BasicStroke(), CornerRadii.all(0), Insets(5, 10, 5, 10))))
  val TabBorder = Var[TabInfo => style.Border]("css-tab-borer", _ => emptyTabBorder)
  val TabBackground = Var[TabInfo => style.Background]("css-tab-background", _ => EmptyBackground)

  // Slider specific
  val SliderThumbShape = Var[Shape]("css-slider-thumb-shape", null)
  val SliderThumbBackground = Var[Background]("css-slider-thumb-background", null)
  val SliderThumbBorder = Var[Border]("css-slider-thumb-border", null)
  val SliderTrackBackground = Var[Background]("css-slider-track-background", EmptyBackground)
  val SliderTrackBorder = Var[Border]("css-slider-track-border", EmptyBorder)
  val SliderTrackFillBackground = Var[Background]("css-slider-track-fill-background", EmptyBackground)
  val SliderTrackFillBorder = Var[Border]("css-slider-track-fill-border", EmptyBorder)

  // Radio button specific
  val RadioButtonTickShape = Var[Shape]("css-radio-button-tick-shape", null)
  val RadioButtonTickBackground = Var[Background]("css-radio-buttoncheckbox-tick-background", null)
  val RadioButtonTickBorder = Var[Background]("css-radio-button-tick-border", null)

  // Check box specific
  val CheckboxTickShape = Var[Shape]("css-checkbox-tick-shape", null)
  val CheckboxTickBackground = Var[Background]("css-checkbox-tick-background", null)
  val CheckboxTickBorder = Var[Background]("css-checkbox-tick-border", null)
}

case class TabInfo(tabIndex: Int, selected: Boolean)