package guarana.swing
package style

import java.awt.{BasicStroke, Shape}

object CssProperties {
  private val EmptyBackground = style.Background()
  private val EmptyBorder = style.Border()

  val Background = Var[style.Background]("css-background", EmptyBackground)
  val ScrollbarThumbBackground = Var[style.Background]("css-scrollbar-thumb-background", EmptyBackground)
  val ScrollbarThumbBorder = Var[style.Border]("css-scrollbar-thumb-border", EmptyBorder)
  val ProgressBarBarBackground = Var[style.Background]("css-progressbar-bar-background", EmptyBackground)
  val ProgressBarBarBorder = Var[style.Border]("css-progressbar-bar-border", EmptyBorder)
  val Border = Var[style.Border]("css-border", EmptyBorder)

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
}

case class TabInfo(tabIndex: Int, selected: Boolean)