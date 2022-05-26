package apricot

import guarana.{ColorDefs, ColorLike}
import io.github.humbleui.skija

type Color = Colors.Color
object Colors extends ColorDefs {

  opaque type Color <: Int = Int

  given ColorLike: ColorLike[Color] with {
    def apply(r: Float, g: Float, b: Float, a: Float = 1f) = rgba((r * 255).toInt, (g * 255).toInt, (b * 255).toInt, (a * 255).toInt)
    def rgba(r: Int, g: Int, b: Int, a: Int = 255) = skija.Color.makeARGB(a, r, g, b)
    def argb(argb: Int) = argb
    def hsb(hue: Double, saturation: Double, brightness: Double) = ???

    extension (c: Color) {
      def red: Int = skija.Color.getR(c)
      def green: Int = skija.Color.getG(c)
      def blue: Int = skija.Color.getB(c)
      def alpha: Int = skija.Color.getA(c)
      def rgba: Int = c
    }
  }
}