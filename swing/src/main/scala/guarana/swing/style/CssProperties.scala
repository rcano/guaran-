package guarana
package swing
package style

import java.awt.{BasicStroke, Shape}

object CssProperties {
  val EmptyBackground = style.Background()
  val EmptyBorder = style.Border()
  val EmptyInsets = Insets()
  val TransparentBackground = style.Background(fills = IArray(BackgroundFill(Color.Transparent, CornerRadii.all(0), EmptyInsets)))

  val Background = Var[style.Background]("css-background", EmptyBackground)
  val Border = Var[style.Border]("css-border", EmptyBorder)
  val Effect = Var[java.awt.image.BufferedImageOp | Null]("css-effect", null)
  val Opacity = Var[Double]("css-opacity", 1)

  /** Extra classes that you can associate to nodes. It's up to the stylist to use these
    *
    */ 
  val Classes = Var[Set[String]]("css-classes", Set.empty)

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
  val SliderThumbShape = Var[Shape | Null]("css-slider-thumb-shape", null)
  val SliderThumbBackground = Var[Background | Null]("css-slider-thumb-background", null)
  val SliderThumbBorder = Var[Border | Null]("css-slider-thumb-border", null)
  val SliderTrackBackground = Var[Background]("css-slider-track-background", EmptyBackground)
  val SliderTrackBorder = Var[Border]("css-slider-track-border", EmptyBorder)
  val SliderTrackFillBackground = Var[Background]("css-slider-track-fill-background", EmptyBackground)
  val SliderTrackFillBorder = Var[Border]("css-slider-track-fill-border", EmptyBorder)

  //ComboBox specific
  val ComboBoxValueBackground = Var[Background]("css-slider-thumb-background", TransparentBackground)
  val ComboBoxValueBorder = Var[Border]("css-slider-thumb-background", EmptyBorder)

  //ListCellRenderer specific
  val ListCellRendererItem = Var[Any | Null]("css-list-cell-renderer-item", null)
  val ListCellRendererItemIndex = Var[Int]("css-list-cell-renderer-item-index", -1)
  val ListCellRendererSelected = Var[Boolean]("css-list-cell-renderer-selected", false)
  val ListCellRendererFocused = Var[Boolean]("css-list-cell-renderer-focused", false)
}

case class TabInfo(tabIndex: Int, selected: Boolean)