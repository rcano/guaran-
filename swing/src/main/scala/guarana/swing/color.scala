package guarana
package swing

import javax.swing.UIManager

type Color = java.awt.Color
object Color extends ColorDefs {
  given ValueOf[Color.type] = ValueOf(this)
  type Color = guarana.swing.Color
  def apply(r: Float, g: Float, b: Float, a: Float = 1f) = java.awt.Color(r, g, b, a)
  def rgba(r: Int, g: Int, b: Int, a: Int = 255): Color = java.awt.Color(r, g, b, a)
  def argb(argb: Int): Color = java.awt.Color(argb, true)
  def hsb(hue: Double, saturation: Double, brightness: Double): Color =
    java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, brightness.toFloat).nn
  def web(code: String): Color =
    if (code.startsWith("#")) argb(0xff << 24 | java.lang.Integer.parseInt(code.substring(1), 16))
    else throw new IllegalArgumentException("Invalid web code, it should start with #")

  override given ColorLike: ColorLike[Color] with {
    def apply(r: Float, g: Float, b: Float, a: Float = 1f) = java.awt.Color(r, g, b, a)
    def rgba(r: Int, g: Int, b: Int, a: Int = 255): Color = java.awt.Color(r, g, b, a)
    def argb(argb: Int): Color = java.awt.Color(argb, true)
    override def hsb(hue: Double, saturation: Double, brightness: Double): Color =
      java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, brightness.toFloat).nn

    extension (c: Color) {
      def red: Int = c.getRed
      def green: Int = c.getGreen
      def blue: Int = c.getBlue
      def alpha: Int = c.getAlpha
      def asRgba: Int = (c.getRGB & 0x00ffffff) << 24 + c.getAlpha()
      def asArgb: Int = c.getRGB
    }
  }

  private[swing] val themeColorVars = collection.mutable.HashMap[String, ExternalObsVal[Color] { type ForInstance = Color.type }]()
  private def colorVar(prop: String) = {
    val res = ExternalObsVal(prop, _ => UIManager.getColor(prop)).forInstance(Color)
    themeColorVars(prop) = res
    res
  }
  object theme {
    val Desktop = colorVar("desktop")
    val ActiveCaption = colorVar("activeCaption")
    val ActiveCaptionText = colorVar("activeCaptionText")
    val ActiveCaptionBorder = colorVar("activeCaptionBorder")
    val InactiveCaption = colorVar("inactiveCaption")
    val InactiveCaptionText = colorVar("inactiveCaptionText")
    val InactiveCaptionBorder = colorVar("inactiveCaptionBorder")
    val Window = colorVar("window")
    val WindowBorder = colorVar("windowBorder")
    val WindowText = colorVar("windowText")
    val Menu = colorVar("menu")
    val MenuText = colorVar("menuText")
    val Text = colorVar("text")
    val TextText = colorVar("textText")
    val TextHighlight = colorVar("textHighlight")
    val TextHighlightText = colorVar("textHighlightText")
    val TextInactiveText = colorVar("textInactiveText")
    val Control = colorVar("control")
    val ControlText = colorVar("controlText")
    val ControlHighlight = colorVar("controlHighlight")
    val ControlLtHighlight = colorVar("controlLtHighlight")
    val ControlShadow = colorVar("controlShadow")
    val ControlDkShadow = colorVar("controlDkShadow")
    val Scrollbar = colorVar("scrollbar")
    val Info = colorVar("info")
    val InfoText = colorVar("infoText")
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
    def clamp(v: Double): Float = (if v < 0 then 0 else if v > 1 then 1 else v) .toFloat
    val hsb = java.awt.Color.RGBtoHSB(c.getRed, c.getGreen, c.getBlue, null)
    hsb(0) = (hsb(0) * h).toFloat
    hsb(1) = clamp(hsb(1) * s)
    hsb(2) = clamp(hsb(2) * b)
    Color.hsb(hsb(0), hsb(1), hsb(2)).withOpacity(clamp(alpha * c.getAlpha))

  def interp(b: Color, at: Double): Color = Color.argb(Color.interpolateColors(c.getRGB, b.getRGB, at))
