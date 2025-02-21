package guarana

import guarana.animation.Interpolator
import scala.annotation.targetName
trait ColorDefs {
  type Color
  given ColorLike: ColorLike[Color]
  given Interpolator[Color] = (min, max, by) => interp(min)(max, by)

  /**
    * A fully transparent color with an ARGB value of #00000000.
    */
  val Transparent = ColorLike(0f, 0f, 0f, 0f)

  /**
    * The color alice blue with an RGB value of #F0F8FF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F0F8FF; float:right; margin: 0 10px 0 0"></div>
    */
  val AliceBlue = ColorLike(0.9411765f, 0.972549f, 1.0f)


  /**
    * The color antique white with an RGB value of #FAEBD7
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FAEBD7; float:right; margin: 0 10px 0 0"></div>
    */
  val AntiqueWhite = ColorLike(0.98039216f, 0.92156863f, 0.84313726f)

  /**
    * The color aqua with an RGB value of #00FFFF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00FFFF; float:right; margin: 0 10px 0 0"></div>
    */
  val Aqua = ColorLike(0.0f, 1.0f, 1.0f)

  /**
    * The color aquamarine with an RGB value of #7FFFD4
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#7FFFD4; float:right; margin: 0 10px 0 0"></div>
    */
  val AquaMarine = ColorLike(0.49803922f, 1.0f, 0.83137256f)

  /**
    * The color azure with an RGB value of #F0FFFF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F0FFFF; float:right; margin: 0 10px 0 0"></div>
    */
  val Azure = ColorLike(0.9411765f, 1.0f, 1.0f)

  /**
    * The color beige with an RGB value of #F5F5DC
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F5F5DC; float:right; margin: 0 10px 0 0"></div>
    */
  val Beige = ColorLike(0.9607843f, 0.9607843f, 0.8627451f)

  /**
    * The color bisque with an RGB value of #FFE4C4
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFE4C4; float:right; margin: 0 10px 0 0"></div>
    */
  val Bisque = ColorLike(1.0f, 0.89411765f, 0.76862746f)

  /**
    * The color black with an RGB value of #000000
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#000000; float:right; margin: 0 10px 0 0"></div>
    */
  val Black = ColorLike(0.0f, 0.0f, 0.0f)

  /**
    * The color blanched almond with an RGB value of #FFEBCD
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFEBCD; float:right; margin: 0 10px 0 0"></div>
    */
  val BlanchedAlmond = ColorLike(1.0f, 0.92156863f, 0.8039216f)

  /**
    * The color blue with an RGB value of #0000FF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#0000FF; float:right; margin: 0 10px 0 0"></div>
    */
  val Blue = ColorLike(0.0f, 0.0f, 1.0f)

  /**
    * The color blue violet with an RGB value of #8A2BE2
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#8A2BE2; float:right; margin: 0 10px 0 0"></div>
    */
  val BlueViolet = ColorLike(0.5411765f, 0.16862746f, 0.8862745f)

  /**
    * The color brown with an RGB value of #A52A2A
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#A52A2A; float:right; margin: 0 10px 0 0"></div>
    */
  val Brown = ColorLike(0.64705884f, 0.16470589f, 0.16470589f)

  /**
    * The color burly wood with an RGB value of #DEB887
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DEB887; float:right; margin: 0 10px 0 0"></div>
    */
  val BurlyWood = ColorLike(0.87058824f, 0.72156864f, 0.5294118f)

  /**
    * The color cadet blue with an RGB value of #5F9EA0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#5F9EA0; float:right; margin: 0 10px 0 0"></div>
    */
  val CadetBlue = ColorLike(0.37254903f, 0.61960787f, 0.627451f)

  /**
    * The color chartreuse with an RGB value of #7FFF00
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#7FFF00; float:right; margin: 0 10px 0 0"></div>
    */
  val Chartreuse = ColorLike(0.49803922f, 1.0f, 0.0f)

  /**
    * The color chocolate with an RGB value of #D2691E
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#D2691E; float:right; margin: 0 10px 0 0"></div>
    */
  val Chocolate = ColorLike(0.8235294f, 0.4117647f, 0.11764706f)

  /**
    * The color coral with an RGB value of #FF7F50
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF7F50; float:right; margin: 0 10px 0 0"></div>
    */
  val Coral = ColorLike(1.0f, 0.49803922f, 0.3137255f)

  /**
    * The color cornflower blue with an RGB value of #6495ED
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#6495ED; float:right; margin: 0 10px 0 0"></div>
    */
  val CornflowerBlue = ColorLike(0.39215687f, 0.58431375f, 0.92941177f)

  /**
    * The color cornsilk with an RGB value of #FFF8DC
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFF8DC; float:right; margin: 0 10px 0 0"></div>
    */
  val Cornsilk = ColorLike(1.0f, 0.972549f, 0.8627451f)

  /**
    * The color crimson with an RGB value of #DC143C
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DC143C; float:right; margin: 0 10px 0 0"></div>
    */
  val Crimson = ColorLike(0.8627451f, 0.078431375f, 0.23529412f)

  /**
    * The color cyan with an RGB value of #00FFFF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00FFFF; float:right; margin: 0 10px 0 0"></div>
    */
  val Cyan = ColorLike(0.0f, 1.0f, 1.0f)

  /**
    * The color dark blue with an RGB value of #00008B
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00008B; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkBlue = ColorLike(0.0f, 0.0f, 0.54509807f)

  /**
    * The color dark cyan with an RGB value of #008B8B
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#008B8B; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkCyan = ColorLike(0.0f, 0.54509807f, 0.54509807f)

  /**
    * The color dark goldenrod with an RGB value of #B8860B
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#B8860B; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkGoldenrod = ColorLike(0.72156864f, 0.5254902f, 0.043137256f)

  /**
    * The color dark gray with an RGB value of #A9A9A9
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#A9A9A9; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkGray = ColorLike(0.6627451f, 0.6627451f, 0.6627451f)

  /**
    * The color dark green with an RGB value of #006400
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#006400; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkGreen = ColorLike(0.0f, 0.39215687f, 0.0f)

  /**
    * The color dark grey with an RGB value of #A9A9A9
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#A9A9A9; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkGrey = DarkGray

  /**
    * The color dark khaki with an RGB value of #BDB76B
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#BDB76B; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkKhaki = ColorLike(0.7411765f, 0.7176471f, 0.41960785f)

  /**
    * The color dark magenta with an RGB value of #8B008B
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#8B008B; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkMagenta = ColorLike(0.54509807f, 0.0f, 0.54509807f)

  /**
    * The color dark olive green with an RGB value of #556B2F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#556B2F; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkOliveGreen = ColorLike(0.33333334f, 0.41960785f, 0.18431373f)

  /**
    * The color dark orange with an RGB value of #FF8C00
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF8C00; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkOrange = ColorLike(1.0f, 0.54901963f, 0.0f)

  /**
    * The color dark orchid with an RGB value of #9932CC
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#9932CC; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkOrchid = ColorLike(0.6f, 0.19607843f, 0.8f)

  /**
    * The color dark red with an RGB value of #8B0000
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#8B0000; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkRed = ColorLike(0.54509807f, 0.0f, 0.0f)

  /**
    * The color dark salmon with an RGB value of #E9967A
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#E9967A; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkSalmon = ColorLike(0.9137255f, 0.5882353f, 0.47843137f)

  /**
    * The color dark sea green with an RGB value of #8FBC8F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#8FBC8F; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkSeaGreen = ColorLike(0.56078434f, 0.7372549f, 0.56078434f)

  /**
    * The color dark slate blue with an RGB value of #483D8B
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#483D8B; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkSlateBlue = ColorLike(0.28235295f, 0.23921569f, 0.54509807f)

  /**
    * The color dark slate gray with an RGB value of #2F4F4F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#2F4F4F; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkSlateGray = ColorLike(0.18431373f, 0.30980393f, 0.30980393f)

  /**
    * The color dark slate grey with an RGB value of #2F4F4F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#2F4F4F; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkSlateGrey = DarkSlateGray

  /**
    * The color dark turquoise with an RGB value of #00CED1
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00CED1; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkTurquoise = ColorLike(0.0f, 0.80784315f, 0.81960785f)

  /**
    * The color dark violet with an RGB value of #9400D3
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#9400D3; float:right; margin: 0 10px 0 0"></div>
    */
  val DarkViolet = ColorLike(0.5803922f, 0.0f, 0.827451f)

  /**
    * The color deep pink with an RGB value of #FF1493
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF1493; float:right; margin: 0 10px 0 0"></div>
    */
  val DeepPink = ColorLike(1.0f, 0.078431375f, 0.5764706f)

  /**
    * The color deep sky blue with an RGB value of #00BFFF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00BFFF; float:right; margin: 0 10px 0 0"></div>
    */
  val DeepSkyBlue = ColorLike(0.0f, 0.7490196f, 1.0f)

  /**
    * The color dim gray with an RGB value of #696969
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#696969; float:right; margin: 0 10px 0 0"></div>
    */
  val DimGray = ColorLike(0.4117647f, 0.4117647f, 0.4117647f)

  /**
    * The color dim grey with an RGB value of #696969
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#696969; float:right; margin: 0 10px 0 0"></div>
    */
  val DimGrey = DimGray

  /**
    * The color dodger blue with an RGB value of #1E90FF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#1E90FF; float:right; margin: 0 10px 0 0"></div>
    */
  val DodgerBlue = ColorLike(0.11764706f, 0.5647059f, 1.0f)

  /**
    * The color firebrick with an RGB value of #B22222
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#B22222; float:right; margin: 0 10px 0 0"></div>
    */
  val FireBrick = ColorLike(0.69803923f, 0.13333334f, 0.13333334f)

  /**
    * The color floral white with an RGB value of #FFFAF0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFAF0; float:right; margin: 0 10px 0 0"></div>
    */
  val FloralWhite = ColorLike(1.0f, 0.98039216f, 0.9411765f)

  /**
    * The color forest green with an RGB value of #228B22
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#228B22; float:right; margin: 0 10px 0 0"></div>
    */
  val ForestGreen = ColorLike(0.13333334f, 0.54509807f, 0.13333334f)

  /**
    * The color fuchsia with an RGB value of #FF00FF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF00FF; float:right; margin: 0 10px 0 0"></div>
    */
  val Fuchsia = ColorLike(1.0f, 0.0f, 1.0f)

  /**
    * The color gainsboro with an RGB value of #DCDCDC
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DCDCDC; float:right; margin: 0 10px 0 0"></div>
    */
  val Gainsboro = ColorLike(0.8627451f, 0.8627451f, 0.8627451f)

  /**
    * The color ghost white with an RGB value of #F8F8FF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F8F8FF; float:right; margin: 0 10px 0 0"></div>
    */
  val GhostWhite = ColorLike(0.972549f, 0.972549f, 1.0f)

  /**
    * The color gold with an RGB value of #FFD700
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFD700; float:right; margin: 0 10px 0 0"></div>
    */
  val Gold = ColorLike(1.0f, 0.84313726f, 0.0f)

  /**
    * The color goldenrod with an RGB value of #DAA520
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DAA520; float:right; margin: 0 10px 0 0"></div>
    */
  val Goldenrod = ColorLike(0.85490197f, 0.64705884f, 0.1254902f)

  /**
    * The color gray with an RGB value of #808080
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#808080; float:right; margin: 0 10px 0 0"></div>
    */
  val Gray = ColorLike(0.5019608f, 0.5019608f, 0.5019608f)

  /**
    * The color green with an RGB value of #008000
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#008000; float:right; margin: 0 10px 0 0"></div>
    */
  val Green = ColorLike(0.0f, 0.5019608f, 0.0f)

  /**
    * The color green yellow with an RGB value of #ADFF2F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#ADFF2F; float:right; margin: 0 10px 0 0"></div>
    */
  val GreenYellow = ColorLike(0.6784314f, 1.0f, 0.18431373f)

  /**
    * The color grey with an RGB value of #808080
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#808080; float:right; margin: 0 10px 0 0"></div>
    */
  val Grey = Gray

  /**
    * The color honeydew with an RGB value of #F0FFF0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F0FFF0; float:right; margin: 0 10px 0 0"></div>
    */
  val Honeydew = ColorLike(0.9411765f, 1.0f, 0.9411765f)

  /**
    * The color hot pink with an RGB value of #FF69B4
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF69B4; float:right; margin: 0 10px 0 0"></div>
    */
  val HotPink = ColorLike(1.0f, 0.4117647f, 0.7058824f)

  /**
    * The color indian red with an RGB value of #CD5C5C
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#CD5C5C; float:right; margin: 0 10px 0 0"></div>
    */
  val IndianRed = ColorLike(0.8039216f, 0.36078432f, 0.36078432f)

  /**
    * The color indigo with an RGB value of #4B0082
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#4B0082; float:right; margin: 0 10px 0 0"></div>
    */
  val Indigo = ColorLike(0.29411766f, 0.0f, 0.50980395f)

  /**
    * The color ivory with an RGB value of #FFFFF0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFFF0; float:right; margin: 0 10px 0 0"></div>
    */
  val Ivory = ColorLike(1.0f, 1.0f, 0.9411765f)

  /**
    * The color khaki with an RGB value of #F0E68C
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F0E68C; float:right; margin: 0 10px 0 0"></div>
    */
  val Khaki = ColorLike(0.9411765f, 0.9019608f, 0.54901963f)

  /**
    * The color lavender with an RGB value of #E6E6FA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#E6E6FA; float:right; margin: 0 10px 0 0"></div>
    */
  val Lavender = ColorLike(0.9019608f, 0.9019608f, 0.98039216f)

  /**
    * The color lavender blush with an RGB value of #FFF0F5
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFF0F5; float:right; margin: 0 10px 0 0"></div>
    */
  val LavenderBlush = ColorLike(1.0f, 0.9411765f, 0.9607843f)

  /**
    * The color lawn green with an RGB value of #7CFC00
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#7CFC00; float:right; margin: 0 10px 0 0"></div>
    */
  val LawnGreen = ColorLike(0.4862745f, 0.9882353f, 0.0f)

  /**
    * The color lemon chiffon with an RGB value of #FFFACD
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFACD; float:right; margin: 0 10px 0 0"></div>
    */
  val LemonChiffon = ColorLike(1.0f, 0.98039216f, 0.8039216f)

  /**
    * The color light blue with an RGB value of #ADD8E6
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#ADD8E6; float:right; margin: 0 10px 0 0"></div>
    */
  val LightBlue = ColorLike(0.6784314f, 0.84705883f, 0.9019608f)

  /**
    * The color light coral with an RGB value of #F08080
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F08080; float:right; margin: 0 10px 0 0"></div>
    */
  val LightCoral = ColorLike(0.9411765f, 0.5019608f, 0.5019608f)

  /**
    * The color light cyan with an RGB value of #E0FFFF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#E0FFFF; float:right; margin: 0 10px 0 0"></div>
    */
  val LightCyan = ColorLike(0.8784314f, 1.0f, 1.0f)

  /**
    * The color light goldenrod yellow with an RGB value of #FAFAD2
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FAFAD2; float:right; margin: 0 10px 0 0"></div>
    */
  val LightGoldenrodYellow = ColorLike(0.98039216f, 0.98039216f, 0.8235294f)

  /**
    * The color light gray with an RGB value of #D3D3D3
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#D3D3D3; float:right; margin: 0 10px 0 0"></div>
    */
  val LightGray = ColorLike(0.827451f, 0.827451f, 0.827451f)

  /**
    * The color light green with an RGB value of #90EE90
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#90EE90; float:right; margin: 0 10px 0 0"></div>
    */
  val LightGreen = ColorLike(0.5647059f, 0.93333334f, 0.5647059f)

  /**
    * The color light grey with an RGB value of #D3D3D3
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#D3D3D3; float:right; margin: 0 10px 0 0"></div>
    */
  val LightGrey = LightGray

  /**
    * The color light pink with an RGB value of #FFB6C1
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFB6C1; float:right; margin: 0 10px 0 0"></div>
    */
  val LightPink = ColorLike(1.0f, 0.7137255f, 0.75686276f)

  /**
    * The color light salmon with an RGB value of #FFA07A
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFA07A; float:right; margin: 0 10px 0 0"></div>
    */
  val LightSalmon = ColorLike(1.0f, 0.627451f, 0.47843137f)

  /**
    * The color light sea green with an RGB value of #20B2AA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#20B2AA; float:right; margin: 0 10px 0 0"></div>
    */
  val LightSeaGreen = ColorLike(0.1254902f, 0.69803923f, 0.6666667f)

  /**
    * The color light sky blue with an RGB value of #87CEFA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#87CEFA; float:right; margin: 0 10px 0 0"></div>
    */
  val LightSkyBlue = ColorLike(0.5294118f, 0.80784315f, 0.98039216f)

  /**
    * The color light slate gray with an RGB value of #778899
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#778899; float:right; margin: 0 10px 0 0"></div>
    */
  val LightSlateGray = ColorLike(0.46666667f, 0.53333336f, 0.6f)


  /**
    * The color light steel blue with an RGB value of #B0C4DE
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#B0C4DE; float:right; margin: 0 10px 0 0"></div>
    */
  val LightSteelBlue = ColorLike(0.6901961f, 0.76862746f, 0.87058824f)

  /**
    * The color light yellow with an RGB value of #FFFFE0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFFE0; float:right; margin: 0 10px 0 0"></div>
    */
  val LightYellow = ColorLike(1.0f, 1.0f, 0.8784314f)

  /**
    * The color lime with an RGB value of #00FF00
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00FF00; float:right; margin: 0 10px 0 0"></div>
    */
  val Lime = ColorLike(0.0f, 1.0f, 0.0f)

  /**
    * The color lime green with an RGB value of #32CD32
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#32CD32; float:right; margin: 0 10px 0 0"></div>
    */
  val LimeGreen = ColorLike(0.19607843f, 0.8039216f, 0.19607843f)

  /**
    * The color linen with an RGB value of #FAF0E6
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FAF0E6; float:right; margin: 0 10px 0 0"></div>
    */
  val Linen = ColorLike(0.98039216f, 0.9411765f, 0.9019608f)

  /**
    * The color magenta with an RGB value of #FF00FF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF00FF; float:right; margin: 0 10px 0 0"></div>
    */
  val Magenta = ColorLike(1.0f, 0.0f, 1.0f)

  /**
    * The color maroon with an RGB value of #800000
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#800000; float:right; margin: 0 10px 0 0"></div>
    */
  val Maroon = ColorLike(0.5019608f, 0.0f, 0.0f)

  /**
    * The color medium aquamarine with an RGB value of #66CDAA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#66CDAA; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumAquamarine = ColorLike(0.4f, 0.8039216f, 0.6666667f)

  /**
    * The color medium blue with an RGB value of #0000CD
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#0000CD; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumBlue = ColorLike(0.0f, 0.0f, 0.8039216f)

  /**
    * The color medium orchid with an RGB value of #BA55D3
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#BA55D3; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumOrchid = ColorLike(0.7294118f, 0.33333334f, 0.827451f)

  /**
    * The color medium purple with an RGB value of #9370DB
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#9370DB; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumPurple = ColorLike(0.5764706f, 0.4392157f, 0.85882354f)

  /**
    * The color medium sea green with an RGB value of #3CB371
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#3CB371; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumSeagreen = ColorLike(0.23529412f, 0.7019608f, 0.44313726f)

  /**
    * The color medium slate blue with an RGB value of #7B68EE
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#7B68EE; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumSlateBlue = ColorLike(0.48235294f, 0.40784314f, 0.93333334f)

  /**
    * The color medium spring green with an RGB value of #00FA9A
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00FA9A; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumSpringGreen = ColorLike(0.0f, 0.98039216f, 0.6039216f)

  /**
    * The color medium turquoise with an RGB value of #48D1CC
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#48D1CC; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumTurquoise = ColorLike(0.28235295f, 0.81960785f, 0.8f)

  /**
    * The color medium violet red with an RGB value of #C71585
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#C71585; float:right; margin: 0 10px 0 0"></div>
    */
  val MediumVioletRed = ColorLike(0.78039217f, 0.08235294f, 0.52156866f)

  /**
    * The color midnight blue with an RGB value of #191970
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#191970; float:right; margin: 0 10px 0 0"></div>
    */
  val MidnightBlue = ColorLike(0.09803922f, 0.09803922f, 0.4392157f)

  /**
    * The color mint cream with an RGB value of #F5FFFA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F5FFFA; float:right; margin: 0 10px 0 0"></div>
    */
  val MintCream = ColorLike(0.9607843f, 1.0f, 0.98039216f)

  /**
    * The color misty rose with an RGB value of #FFE4E1
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFE4E1; float:right; margin: 0 10px 0 0"></div>
    */
  val MistyRose = ColorLike(1.0f, 0.89411765f, 0.88235295f)

  /**
    * The color moccasin with an RGB value of #FFE4B5
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFE4B5; float:right; margin: 0 10px 0 0"></div>
    */
  val Moccasin = ColorLike(1.0f, 0.89411765f, 0.70980394f)

  /**
    * The color navajo white with an RGB value of #FFDEAD
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFDEAD; float:right; margin: 0 10px 0 0"></div>
    */
  val NavajoWhite = ColorLike(1.0f, 0.87058824f, 0.6784314f)

  /**
    * The color navy with an RGB value of #000080
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#000080; float:right; margin: 0 10px 0 0"></div>
    */
  val Navy = ColorLike(0.0f, 0.0f, 0.5019608f)

  /**
    * The color old lace with an RGB value of #FDF5E6
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FDF5E6; float:right; margin: 0 10px 0 0"></div>
    */
  val OldLace = ColorLike(0.99215686f, 0.9607843f, 0.9019608f)

  /**
    * The color olive with an RGB value of #808000
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#808000; float:right; margin: 0 10px 0 0"></div>
    */
  val Olive = ColorLike(0.5019608f, 0.5019608f, 0.0f)

  /**
    * The color olive drab with an RGB value of #6B8E23
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#6B8E23; float:right; margin: 0 10px 0 0"></div>
    */
  val OliveDrab = ColorLike(0.41960785f, 0.5568628f, 0.13725491f)

  /**
    * The color orange with an RGB value of #FFA500
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFA500; float:right; margin: 0 10px 0 0"></div>
    */
  val Orange = ColorLike(1.0f, 0.64705884f, 0.0f)

  /**
    * The color orange red with an RGB value of #FF4500
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF4500; float:right; margin: 0 10px 0 0"></div>
    */
  val OrangeRed = ColorLike(1.0f, 0.27058825f, 0.0f)

  /**
    * The color orchid with an RGB value of #DA70D6
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DA70D6; float:right; margin: 0 10px 0 0"></div>
    */
  val Orchid = ColorLike(0.85490197f, 0.4392157f, 0.8392157f)

  /**
    * The color pale goldenrod with an RGB value of #EEE8AA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#EEE8AA; float:right; margin: 0 10px 0 0"></div>
    */
  val PaleGoldenrod = ColorLike(0.93333334f, 0.9098039f, 0.6666667f)

  /**
    * The color pale green with an RGB value of #98FB98
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#98FB98; float:right; margin: 0 10px 0 0"></div>
    */
  val PaleGreen = ColorLike(0.59607846f, 0.9843137f, 0.59607846f)

  /**
    * The color pale turquoise with an RGB value of #AFEEEE
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#AFEEEE; float:right; margin: 0 10px 0 0"></div>
    */
  val PaleTurquoise = ColorLike(0.6862745f, 0.93333334f, 0.93333334f)

  /**
    * The color pale violet red with an RGB value of #DB7093
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DB7093; float:right; margin: 0 10px 0 0"></div>
    */
  val PaleVioletRed = ColorLike(0.85882354f, 0.4392157f, 0.5764706f)

  /**
    * The color papaya whip with an RGB value of #FFEFD5
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFEFD5; float:right; margin: 0 10px 0 0"></div>
    */
  val PapayaWhip = ColorLike(1.0f, 0.9372549f, 0.8352941f)

  /**
    * The color peach puff with an RGB value of #FFDAB9
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFDAB9; float:right; margin: 0 10px 0 0"></div>
    */
  val PeachPuff = ColorLike(1.0f, 0.85490197f, 0.7254902f)

  /**
    * The color peru with an RGB value of #CD853F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#CD853F; float:right; margin: 0 10px 0 0"></div>
    */
  val Peru = ColorLike(0.8039216f, 0.52156866f, 0.24705882f)

  /**
    * The color pink with an RGB value of #FFC0CB
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFC0CB; float:right; margin: 0 10px 0 0"></div>
    */
  val Pink = ColorLike(1.0f, 0.7529412f, 0.79607844f)

  /**
    * The color plum with an RGB value of #DDA0DD
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#DDA0DD; float:right; margin: 0 10px 0 0"></div>
    */
  val Plum = ColorLike(0.8666667f, 0.627451f, 0.8666667f)

  /**
    * The color powder blue with an RGB value of #B0E0E6
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#B0E0E6; float:right; margin: 0 10px 0 0"></div>
    */
  val PowderBlue = ColorLike(0.6901961f, 0.8784314f, 0.9019608f)

  /**
    * The color purple with an RGB value of #800080
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#800080; float:right; margin: 0 10px 0 0"></div>
    */
  val Purple = ColorLike(0.5019608f, 0.0f, 0.5019608f)

  /**
    * The color red with an RGB value of #FF0000
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF0000; float:right; margin: 0 10px 0 0"></div>
    */
  val Red = ColorLike(1.0f, 0.0f, 0.0f)

  /**
    * The color rosy brown with an RGB value of #BC8F8F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#BC8F8F; float:right; margin: 0 10px 0 0"></div>
    */
  val RosyBrown = ColorLike(0.7372549f, 0.56078434f, 0.56078434f)

  /**
    * The color royal blue with an RGB value of #4169E1
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#4169E1; float:right; margin: 0 10px 0 0"></div>
    */
  val RoyalBlue = ColorLike(0.25490198f, 0.4117647f, 0.88235295f)

  /**
    * The color saddle brown with an RGB value of #8B4513
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#8B4513; float:right; margin: 0 10px 0 0"></div>
    */
  val SaddleBrown = ColorLike(0.54509807f, 0.27058825f, 0.07450981f)

  /**
    * The color salmon with an RGB value of #FA8072
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FA8072; float:right; margin: 0 10px 0 0"></div>
    */
  val Salmon = ColorLike(0.98039216f, 0.5019608f, 0.44705883f)

  /**
    * The color sandy brown with an RGB value of #F4A460
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F4A460; float:right; margin: 0 10px 0 0"></div>
    */
  val SandyBrown = ColorLike(0.95686275f, 0.6431373f, 0.3764706f)

  /**
    * The color sea green with an RGB value of #2E8B57
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#2E8B57; float:right; margin: 0 10px 0 0"></div>
    */
  val SeaGreen = ColorLike(0.18039216f, 0.54509807f, 0.34117648f)

  /**
    * The color sea shell with an RGB value of #FFF5EE
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFF5EE; float:right; margin: 0 10px 0 0"></div>
    */
  val SeaShell = ColorLike(1.0f, 0.9607843f, 0.93333334f)

  /**
    * The color sienna with an RGB value of #A0522D
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#A0522D; float:right; margin: 0 10px 0 0"></div>
    */
  val Sienna = ColorLike(0.627451f, 0.32156864f, 0.1764706f)

  /**
    * The color silver with an RGB value of #C0C0C0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#C0C0C0; float:right; margin: 0 10px 0 0"></div>
    */
  val Silver = ColorLike(0.7529412f, 0.7529412f, 0.7529412f)

  /**
    * The color sky blue with an RGB value of #87CEEB
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#87CEEB; float:right; margin: 0 10px 0 0"></div>
    */
  val SkyBlue = ColorLike(0.5294118f, 0.80784315f, 0.92156863f)

  /**
    * The color slate blue with an RGB value of #6A5ACD
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#6A5ACD; float:right; margin: 0 10px 0 0"></div>
    */
  val SlateBlue = ColorLike(0.41568628f, 0.3529412f, 0.8039216f)

  /**
    * The color slate gray with an RGB value of #708090
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#708090; float:right; margin: 0 10px 0 0"></div>
    */
  val SlateGray = ColorLike(0.4392157f, 0.5019608f, 0.5647059f)

  /**
    * The color slate grey with an RGB value of #708090
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#708090; float:right; margin: 0 10px 0 0"></div>
    */
  val SlateGrey = ColorLike(0.18431373f, 0.30980393f, 0.30980393f)

  /**
    * The color snow with an RGB value of #FFFAFA
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFAFA; float:right; margin: 0 10px 0 0"></div>
    */
  val Snow = ColorLike(1.0f, 0.98039216f, 0.98039216f)

  /**
    * The color spring green with an RGB value of #00FF7F
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#00FF7F; float:right; margin: 0 10px 0 0"></div>
    */
  val SpringGreen = ColorLike(0.0f, 1.0f, 0.49803922f)

  /**
    * The color steel blue with an RGB value of #4682B4
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#4682B4; float:right; margin: 0 10px 0 0"></div>
    */
  val SteelBlue = ColorLike(0.27450982f, 0.50980395f, 0.7058824f)

  /**
    * The color tan with an RGB value of #D2B48C
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#D2B48C; float:right; margin: 0 10px 0 0"></div>
    */
  val Tan = ColorLike(0.8235294f, 0.7058824f, 0.54901963f)

  /**
    * The color teal with an RGB value of #008080
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#008080; float:right; margin: 0 10px 0 0"></div>
    */
  val Teal = ColorLike(0.0f, 0.5019608f, 0.5019608f)

  /**
    * The color thistle with an RGB value of #D8BFD8
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#D8BFD8; float:right; margin: 0 10px 0 0"></div>
    */
  val Thistle = ColorLike(0.84705883f, 0.7490196f, 0.84705883f)

  /**
    * The color tomato with an RGB value of #FF6347
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FF6347; float:right; margin: 0 10px 0 0"></div>
    */
  val Tomato = ColorLike(1.0f, 0.3882353f, 0.2784314f)

  /**
    * The color turquoise with an RGB value of #40E0D0
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#40E0D0; float:right; margin: 0 10px 0 0"></div>
    */
  val Turquoise = ColorLike(0.2509804f, 0.8784314f, 0.8156863f)

  /**
    * The color violet with an RGB value of #EE82EE
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#EE82EE; float:right; margin: 0 10px 0 0"></div>
    */
  val Violet = ColorLike(0.93333334f, 0.50980395f, 0.93333334f)

  /**
    * The color wheat with an RGB value of #F5DEB3
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F5DEB3; float:right; margin: 0 10px 0 0"></div>
    */
  val Wheat = ColorLike(0.9607843f, 0.87058824f, 0.7019608f)

  /**
    * The color white with an RGB value of #FFFFFF
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFFFF; float:right; margin: 0 10px 0 0"></div>
    */
  val White = ColorLike(1.0f, 1.0f, 1.0f)

  /**
    * The color white smoke with an RGB value of #F5F5F5
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#F5F5F5; float:right; margin: 0 10px 0 0"></div>
    */
  val WhiteSmoke = ColorLike(0.9607843f, 0.9607843f, 0.9607843f)

  /**
    * The color yellow with an RGB value of #FFFF00
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#FFFF00; float:right; margin: 0 10px 0 0"></div>
    */
  val Yellow = ColorLike(1.0f, 1.0f, 0.0f)

  /**
    * The color yellow green with an RGB value of #9ACD32
    * <div style="border:1px solid black; width:40px; height:20px; background-color:#9ACD32; float:right; margin: 0 10px 0 0"></div>
    */
  val YellowGreen = ColorLike(0.6039216f, 0.8039216f, 0.19607843f)

  inline def interpolateColors(inline c1: Int, inline c2: Int, inline interp: Double): Int = ColorDefs.interpolateColors(c1, c2, interp)


  extension (c: Color) 
    def withOpacity(d: Double): Color = {
      ColorLike.rgba(c.red, c.green, c.blue, (255 * d).toInt)
    }
    def derive(r: Double = 1, g: Double = 1, b: Double = 1, alpha: Double = 1): Color =
      def clamp(v: Double): Int = if v < 0 then 0 else if v > 255 then 255 else v.toInt  
      ColorLike.rgba(clamp(c.red * r), clamp(c.green * g), clamp(c.blue * b), clamp(c.alpha * alpha))

    def deriveHSB(h: Double = 1, s: Double = 1, b: Double = 1, alpha: Double = 1): Color =
      def clamp(v: Double): Float = (if v < 0 then 0 else if v > 1 then 1 else v).toFloat
      val hsb = java.awt.Color.RGBtoHSB(c.red, c.green, c.blue, null)
      import language.unsafeNulls
      hsb(0) = (hsb(0) * h).toFloat
      hsb(1) = clamp(hsb(1) * s)
      hsb(2) = clamp(hsb(2) * b)
      ColorLike.hsb(hsb(0), hsb(1), hsb(2)).withOpacity(clamp(alpha * c.alpha))

    def interp(b: Color, at: Double): Color = ColorLike.argb(interpolateColors(c.asArgb, b.asArgb, at))
}

object ColorDefs {
  def interpolateColors(c1: Int, c2: Int, interp: Double): Int =
    val a1 = (c1 >> 24) & 0xFF
    val a2 = (c2 >> 24) & 0xFF
    val r1 = (c1 >> 16) & 0xFF
    val r2 = (c2 >> 16) & 0xFF
    val g1 = (c1 >> 8) & 0xFF
    val g2 = (c2 >> 8) & 0xFF
    val b1 = (c1 >> 0) & 0xFF
    val b2 = (c2 >> 0) & 0xFF
    ((a2 - a1) * interp + a1).toInt << 24 |
    ((r2 - r1) * interp + r1).toInt << 16 |
    ((g2 - g1) * interp + g1).toInt << 8 |
    ((b2 - b1) * interp + b1).toInt
}


trait ColorLike[C] {
  def apply(r: Float, g: Float, b: Float, a: Float = 1f): C
  def rgba(r: Int, g: Int, b: Int, a: Int = 255): C
  def argb(argb: Int): C

  /** Sets the RGB Color components using the specified Hue-Saturation-Brightness/Value. Note that HSB components are voluntary not clamped
	  * to preserve high range color and can range beyond typical values.
	  * @param h The Hue in degree from 0 to 360
	  * @param s The Saturation from 0 to 1
	  * @param v The Value (brightness) from 0 to 1
	  * @return The color.
    */
  def hsb(hue: Double, saturation: Double, brightness: Double): C = {
    // code taken from libgdx's Color class
    val x = ((hue / 60f + 6) % 6)
    val i = x.toInt
    val f = x - i
    inline def clamp(v: Float): Float = if v < 0 then 0 else if v > 1 then 1 else v
    val p = clamp((brightness * (1 - saturation)).toFloat)
    val q = clamp((brightness * (1 - saturation * f)).toFloat)
    val t = clamp((brightness * (1 - saturation * (1 - f))).toFloat)
    val v = clamp(brightness.toFloat)
    i match {
      case 0 => apply(v, t, p)
      case 1 => apply(q, v, p)
      case 2 => apply(p, v, t)
      case 3 => apply(p, q, v)
      case 4 => apply(t, p, v)
      case _ => apply(v, q, p)
    }
  }

  inline def hsv(hue: Double, saturation: Double, value: Double): C = hsb(hue, saturation, value)
  def web(code: String): C = 
    if (code.startsWith("#")) argb(0xFF << 24 | java.lang.Integer.parseInt(code.substring(1), 16)) 
    else throw new IllegalArgumentException("Invalid web code, it should start with #")

  extension (c: C) {
    def red: Int
    def green: Int
    def blue: Int
    def alpha: Int
    def asRgba: Int
    def asArgb: Int
  }
}