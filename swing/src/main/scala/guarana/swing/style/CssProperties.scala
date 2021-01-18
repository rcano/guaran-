package guarana.swing
package style

import java.awt.BasicStroke

object CssProperties {
  val Background = Var[style.Background]("css-background", style.Background())
  val ScrollbarThumbBackground = Var[style.Background]("css-scrollbar-thumb-background", style.Background())
  val ScrollbarThumbBorder = Var[style.Border]("css-scrollbar-thumb-border", style.Border())
  val ProgressBarBarBackground = Var[style.Background]("css-progressbar-bar-background", style.Background())
  val ProgressBarBarBorder = Var[style.Border]("css-progressbar-bar-border", style.Border())
  val Border = Var[style.Border]("css-border", style.Border())

  // TabbedPane specific
  private val emptyTabBorder = style.Border(strokes = IArray(BorderStroke.simple(Color.Transparent, BasicStroke(), CornerRadii.all(0), Insets(5, 10, 5, 10))))
  val TabBorder = Var[TabInfo => style.Border]("css-tab-borer", _ => emptyTabBorder)
  val TabBackground = Var[TabInfo => style.Background]("css-tab-background", _ => style.Background())
}

case class TabInfo(tabIndex: Int, selected: Boolean)