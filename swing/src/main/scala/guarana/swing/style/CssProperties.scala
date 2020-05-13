package guarana.swing
package style

object CssProperties {
  val Background = Var[style.Background]("css-background", style.Background())
  val ScrollbarThumbBackground = Var[style.Background]("css-scrollbar-thumb-background", style.Background())
  val ScrollbarThumbBorder = Var[style.Border]("css-scrollbar-thumb-border", style.Border())
  val ProgressBarBarBackground = Var[style.Background]("css-progressbar-bar-background", style.Background())
  val ProgressBarBarBorder = Var[style.Border]("css-progressbar-bar-border", style.Border())
  val Border = Var[style.Border]("css-border", style.Border())
}