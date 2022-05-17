package guarana
package swing

type Color = java.awt.Color
object Color extends ColorDefs {
  type Color = guarana.swing.Color 
  def apply(r: Float, g: Float, b: Float, a: Float = 1f) = java.awt.Color(r, g, b, a)
  def rgba(r: Int, g: Int, b: Int, a: Int = 255): Color = java.awt.Color(r, g, b, a)
  def argb(argb: Int): Color = java.awt.Color(argb, true)
  def hsb(hue: Double, saturation: Double, brightness: Double): Color = java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, brightness.toFloat).nn
  def web(code: String): Color = 
    if (code.startsWith("#")) argb(0xFF << 24 | java.lang.Integer.parseInt(code.substring(1), 16)) 
    else throw new IllegalArgumentException("Invalid web code, it should start with #")

  given ColorLike: ColorLike[Color] with {
    def apply(r: Float, g: Float, b: Float, a: Float = 1f) = java.awt.Color(r, g, b, a)
    def rgba(r: Int, g: Int, b: Int, a: Int = 255): Color = java.awt.Color(r, g, b, a)
    def argb(argb: Int): Color = java.awt.Color(argb, true)
    def hsb(hue: Double, saturation: Double, brightness: Double): Color = java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, brightness.toFloat).nn

    extension (c: Color) {
      def red: Int = c.getRed
      def green: Int = c.getGreen
      def blue: Int = c.getBlue
      def alpha: Int = c.getAlpha
      def rgba: Int = c.getRGB
    }
  }
}
type Paint = java.awt.Paint

extension (c: Color) 
  def withOpacity(d: Double): Color = {
    Color.rgba(c.getRed, c.getGreen, c.getBlue, (255 * d).toInt)
  }
  def derive(r: Double = 1, g: Double = 1, b: Double = 1, alpha: Double = 1): Color =
    def clamp(v: Double): Int = if v < 0 then 0 else if v > 255 then 255 else v.toInt  
    Color.rgba(clamp(c.getRed * r), clamp(c.getGreen * g), clamp(c.getBlue * b), clamp(c.getAlpha * alpha))

  def deriveHSB(h: Double = 1, s: Double = 1, b: Double = 1, alpha: Double = 1): Color =
    import scala.language.unsafeNulls
    def clamp(v: Double): Float = (if v < 0 then 0 else if v > 1 then 1 else v).toFloat
    val hsb = java.awt.Color.RGBtoHSB(c.getRed, c.getGreen, c.getBlue, null)
    hsb(0) = (hsb(0) * h).toFloat
    hsb(1) = clamp(hsb(1) * s)
    hsb(2) = clamp(hsb(2) * b)
    Color.hsb(hsb(0), hsb(1), hsb(2)).withOpacity(clamp(alpha * c.getAlpha))

  def interp(b: Color, at: Double): Color = Color.argb(Color.interpolateColors(c.getRGB, b.getRGB, at))
