package guarana.swing

type Color = java.awt.Color
object Color {
  def apply(r: Float, g: Float, b: Float, a: Float = 1f) = java.awt.Color(r, g, b, a)
  def rgba(r: Int, g: Int, b: Int, a: Int = 255): Color = java.awt.Color(r, g, b, a)
  def argb(argb: Int): Color = java.awt.Color(argb, true)
  def hsb(hue: Double, saturation: Double, brightness: Double): Color = java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, brightness.toFloat).nn
  def web(code: String): Color = 
    if (code.startsWith("#")) argb(java.lang.Integer.parseInt(code.substring(1), 16)) 
    else throw new IllegalArgumentException("Invalid web code, it should start with #")

  /**
    * A fully transparent color with an ARGB value of #00000000.
    */
  val Transparent = Color(0f, 0f, 0f, 0f)

  /**
    * The color alice blue with an RGB value of #F0F8FF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F0F8FFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val AliceBlue = Color(0.9411765f, 0.972549f, 1.0f)


  /**
    * The color antique white with an RGB value of #FAEBD7
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FAEBD7float:rightmargin: 0 10px 0 0"></div>
    */
  val AntiqueWhite = Color(0.98039216f, 0.92156863f, 0.84313726f)

  /**
    * The color aqua with an RGB value of #00FFFF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00FFFFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Aqua = Color(0.0f, 1.0f, 1.0f)

  /**
    * The color aquamarine with an RGB value of #7FFFD4
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#7FFFD4float:rightmargin: 0 10px 0 0"></div>
    */
  val AquaMarine = Color(0.49803922f, 1.0f, 0.83137256f)

  /**
    * The color azure with an RGB value of #F0FFFF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F0FFFFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Azure = Color(0.9411765f, 1.0f, 1.0f)

  /**
    * The color beige with an RGB value of #F5F5DC
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F5F5DCfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Beige = Color(0.9607843f, 0.9607843f, 0.8627451f)

  /**
    * The color bisque with an RGB value of #FFE4C4
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFE4C4float:rightmargin: 0 10px 0 0"></div>
    */
  val Bisque = Color(1.0f, 0.89411765f, 0.76862746f)

  /**
    * The color black with an RGB value of #000000
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#000000float:rightmargin: 0 10px 0 0"></div>
    */
  val Black = Color(0.0f, 0.0f, 0.0f)

  /**
    * The color blanched almond with an RGB value of #FFEBCD
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFEBCDfloat:rightmargin: 0 10px 0 0"></div>
    */
  val BlanchedAlmond = Color(1.0f, 0.92156863f, 0.8039216f)

  /**
    * The color blue with an RGB value of #0000FF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#0000FFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Blue = Color(0.0f, 0.0f, 1.0f)

  /**
    * The color blue violet with an RGB value of #8A2BE2
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#8A2BE2float:rightmargin: 0 10px 0 0"></div>
    */
  val BlueViolet = Color(0.5411765f, 0.16862746f, 0.8862745f)

  /**
    * The color brown with an RGB value of #A52A2A
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#A52A2Afloat:rightmargin: 0 10px 0 0"></div>
    */
  val Brown = Color(0.64705884f, 0.16470589f, 0.16470589f)

  /**
    * The color burly wood with an RGB value of #DEB887
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DEB887float:rightmargin: 0 10px 0 0"></div>
    */
  val BurlyWood = Color(0.87058824f, 0.72156864f, 0.5294118f)

  /**
    * The color cadet blue with an RGB value of #5F9EA0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#5F9EA0float:rightmargin: 0 10px 0 0"></div>
    */
  val CadetBlue = Color(0.37254903f, 0.61960787f, 0.627451f)

  /**
    * The color chartreuse with an RGB value of #7FFF00
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#7FFF00float:rightmargin: 0 10px 0 0"></div>
    */
  val Chartreuse = Color(0.49803922f, 1.0f, 0.0f)

  /**
    * The color chocolate with an RGB value of #D2691E
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#D2691Efloat:rightmargin: 0 10px 0 0"></div>
    */
  val Chocolate = Color(0.8235294f, 0.4117647f, 0.11764706f)

  /**
    * The color coral with an RGB value of #FF7F50
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF7F50float:rightmargin: 0 10px 0 0"></div>
    */
  val Coral = Color(1.0f, 0.49803922f, 0.3137255f)

  /**
    * The color cornflower blue with an RGB value of #6495ED
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#6495EDfloat:rightmargin: 0 10px 0 0"></div>
    */
  val CornfloweBblue = Color(0.39215687f, 0.58431375f, 0.92941177f)

  /**
    * The color cornsilk with an RGB value of #FFF8DC
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFF8DCfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Cornsilk = Color(1.0f, 0.972549f, 0.8627451f)

  /**
    * The color crimson with an RGB value of #DC143C
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DC143Cfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Crimson = Color(0.8627451f, 0.078431375f, 0.23529412f)

  /**
    * The color cyan with an RGB value of #00FFFF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00FFFFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Cyan = Color(0.0f, 1.0f, 1.0f)

  /**
    * The color dark blue with an RGB value of #00008B
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00008Bfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkBlue = Color(0.0f, 0.0f, 0.54509807f)

  /**
    * The color dark cyan with an RGB value of #008B8B
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#008B8Bfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkCyan = Color(0.0f, 0.54509807f, 0.54509807f)

  /**
    * The color dark goldenrod with an RGB value of #B8860B
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#B8860Bfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkGoldenrod = Color(0.72156864f, 0.5254902f, 0.043137256f)

  /**
    * The color dark gray with an RGB value of #A9A9A9
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#A9A9A9float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkGray = Color(0.6627451f, 0.6627451f, 0.6627451f)

  /**
    * The color dark green with an RGB value of #006400
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#006400float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkGreen = Color(0.0f, 0.39215687f, 0.0f)

  /**
    * The color dark grey with an RGB value of #A9A9A9
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#A9A9A9float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkGrey = DarkGray

  /**
    * The color dark khaki with an RGB value of #BDB76B
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#BDB76Bfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkKhaki = Color(0.7411765f, 0.7176471f, 0.41960785f)

  /**
    * The color dark magenta with an RGB value of #8B008B
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#8B008Bfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkMagenta = Color(0.54509807f, 0.0f, 0.54509807f)

  /**
    * The color dark olive green with an RGB value of #556B2F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#556B2Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkOliveGreen = Color(0.33333334f, 0.41960785f, 0.18431373f)

  /**
    * The color dark orange with an RGB value of #FF8C00
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF8C00float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkOrange = Color(1.0f, 0.54901963f, 0.0f)

  /**
    * The color dark orchid with an RGB value of #9932CC
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#9932CCfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkOrchid = Color(0.6f, 0.19607843f, 0.8f)

  /**
    * The color dark red with an RGB value of #8B0000
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#8B0000float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkRed = Color(0.54509807f, 0.0f, 0.0f)

  /**
    * The color dark salmon with an RGB value of #E9967A
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#E9967Afloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkSalmon = Color(0.9137255f, 0.5882353f, 0.47843137f)

  /**
    * The color dark sea green with an RGB value of #8FBC8F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#8FBC8Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkSeaGreen = Color(0.56078434f, 0.7372549f, 0.56078434f)

  /**
    * The color dark slate blue with an RGB value of #483D8B
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#483D8Bfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkSlateBlue = Color(0.28235295f, 0.23921569f, 0.54509807f)

  /**
    * The color dark slate gray with an RGB value of #2F4F4F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#2F4F4Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkSlateGray = Color(0.18431373f, 0.30980393f, 0.30980393f)

  /**
    * The color dark slate grey with an RGB value of #2F4F4F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#2F4F4Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val DarkSlateGrey = DarkSlateGray

  /**
    * The color dark turquoise with an RGB value of #00CED1
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00CED1float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkTurquoise = Color(0.0f, 0.80784315f, 0.81960785f)

  /**
    * The color dark violet with an RGB value of #9400D3
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#9400D3float:rightmargin: 0 10px 0 0"></div>
    */
  val DarkViolet = Color(0.5803922f, 0.0f, 0.827451f)

  /**
    * The color deep pink with an RGB value of #FF1493
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF1493float:rightmargin: 0 10px 0 0"></div>
    */
  val DeepPink = Color(1.0f, 0.078431375f, 0.5764706f)

  /**
    * The color deep sky blue with an RGB value of #00BFFF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00BFFFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DeepSkyBlue = Color(0.0f, 0.7490196f, 1.0f)

  /**
    * The color dim gray with an RGB value of #696969
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#696969float:rightmargin: 0 10px 0 0"></div>
    */
  val DimGray = Color(0.4117647f, 0.4117647f, 0.4117647f)

  /**
    * The color dim grey with an RGB value of #696969
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#696969float:rightmargin: 0 10px 0 0"></div>
    */
  val DimGrey = DimGray

  /**
    * The color dodger blue with an RGB value of #1E90FF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#1E90FFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val DodgerBlue = Color(0.11764706f, 0.5647059f, 1.0f)

  /**
    * The color firebrick with an RGB value of #B22222
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#B22222float:rightmargin: 0 10px 0 0"></div>
    */
  val FireBrick = Color(0.69803923f, 0.13333334f, 0.13333334f)

  /**
    * The color floral white with an RGB value of #FFFAF0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFAF0float:rightmargin: 0 10px 0 0"></div>
    */
  val FloralWhite = Color(1.0f, 0.98039216f, 0.9411765f)

  /**
    * The color forest green with an RGB value of #228B22
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#228B22float:rightmargin: 0 10px 0 0"></div>
    */
  val ForestGreen = Color(0.13333334f, 0.54509807f, 0.13333334f)

  /**
    * The color fuchsia with an RGB value of #FF00FF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF00FFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Fuchsia = Color(1.0f, 0.0f, 1.0f)

  /**
    * The color gainsboro with an RGB value of #DCDCDC
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DCDCDCfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Gainsboro = Color(0.8627451f, 0.8627451f, 0.8627451f)

  /**
    * The color ghost white with an RGB value of #F8F8FF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F8F8FFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val GhostWhite = Color(0.972549f, 0.972549f, 1.0f)

  /**
    * The color gold with an RGB value of #FFD700
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFD700float:rightmargin: 0 10px 0 0"></div>
    */
  val Gold = Color(1.0f, 0.84313726f, 0.0f)

  /**
    * The color goldenrod with an RGB value of #DAA520
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DAA520float:rightmargin: 0 10px 0 0"></div>
    */
  val Goldenrod = Color(0.85490197f, 0.64705884f, 0.1254902f)

  /**
    * The color gray with an RGB value of #808080
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#808080float:rightmargin: 0 10px 0 0"></div>
    */
  val Gray = Color(0.5019608f, 0.5019608f, 0.5019608f)

  /**
    * The color green with an RGB value of #008000
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#008000float:rightmargin: 0 10px 0 0"></div>
    */
  val Green = Color(0.0f, 0.5019608f, 0.0f)

  /**
    * The color green yellow with an RGB value of #ADFF2F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#ADFF2Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val GreenYellow = Color(0.6784314f, 1.0f, 0.18431373f)

  /**
    * The color grey with an RGB value of #808080
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#808080float:rightmargin: 0 10px 0 0"></div>
    */
  val Grey = Gray

  /**
    * The color honeydew with an RGB value of #F0FFF0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F0FFF0float:rightmargin: 0 10px 0 0"></div>
    */
  val Honeydew = Color(0.9411765f, 1.0f, 0.9411765f)

  /**
    * The color hot pink with an RGB value of #FF69B4
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF69B4float:rightmargin: 0 10px 0 0"></div>
    */
  val HotPink = Color(1.0f, 0.4117647f, 0.7058824f)

  /**
    * The color indian red with an RGB value of #CD5C5C
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#CD5C5Cfloat:rightmargin: 0 10px 0 0"></div>
    */
  val IndianRed = Color(0.8039216f, 0.36078432f, 0.36078432f)

  /**
    * The color indigo with an RGB value of #4B0082
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#4B0082float:rightmargin: 0 10px 0 0"></div>
    */
  val Indigo = Color(0.29411766f, 0.0f, 0.50980395f)

  /**
    * The color ivory with an RGB value of #FFFFF0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFFF0float:rightmargin: 0 10px 0 0"></div>
    */
  val Ivory = Color(1.0f, 1.0f, 0.9411765f)

  /**
    * The color khaki with an RGB value of #F0E68C
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F0E68Cfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Khaki = Color(0.9411765f, 0.9019608f, 0.54901963f)

  /**
    * The color lavender with an RGB value of #E6E6FA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#E6E6FAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Lavender = Color(0.9019608f, 0.9019608f, 0.98039216f)

  /**
    * The color lavender blush with an RGB value of #FFF0F5
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFF0F5float:rightmargin: 0 10px 0 0"></div>
    */
  val LavenderBlush = Color(1.0f, 0.9411765f, 0.9607843f)

  /**
    * The color lawn green with an RGB value of #7CFC00
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#7CFC00float:rightmargin: 0 10px 0 0"></div>
    */
  val LawnGreen = Color(0.4862745f, 0.9882353f, 0.0f)

  /**
    * The color lemon chiffon with an RGB value of #FFFACD
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFACDfloat:rightmargin: 0 10px 0 0"></div>
    */
  val LemonChiffon = Color(1.0f, 0.98039216f, 0.8039216f)

  /**
    * The color light blue with an RGB value of #ADD8E6
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#ADD8E6float:rightmargin: 0 10px 0 0"></div>
    */
  val LightBlue = Color(0.6784314f, 0.84705883f, 0.9019608f)

  /**
    * The color light coral with an RGB value of #F08080
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F08080float:rightmargin: 0 10px 0 0"></div>
    */
  val LightCoral = Color(0.9411765f, 0.5019608f, 0.5019608f)

  /**
    * The color light cyan with an RGB value of #E0FFFF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#E0FFFFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val LightCyan = Color(0.8784314f, 1.0f, 1.0f)

  /**
    * The color light goldenrod yellow with an RGB value of #FAFAD2
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FAFAD2float:rightmargin: 0 10px 0 0"></div>
    */
  val LightGoldenrodYellow = Color(0.98039216f, 0.98039216f, 0.8235294f)

  /**
    * The color light gray with an RGB value of #D3D3D3
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#D3D3D3float:rightmargin: 0 10px 0 0"></div>
    */
  val LightGray = Color(0.827451f, 0.827451f, 0.827451f)

  /**
    * The color light green with an RGB value of #90EE90
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#90EE90float:rightmargin: 0 10px 0 0"></div>
    */
  val LightGreen = Color(0.5647059f, 0.93333334f, 0.5647059f)

  /**
    * The color light grey with an RGB value of #D3D3D3
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#D3D3D3float:rightmargin: 0 10px 0 0"></div>
    */
  val LightGrey = LightGray

  /**
    * The color light pink with an RGB value of #FFB6C1
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFB6C1float:rightmargin: 0 10px 0 0"></div>
    */
  val LightPink = Color(1.0f, 0.7137255f, 0.75686276f)

  /**
    * The color light salmon with an RGB value of #FFA07A
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFA07Afloat:rightmargin: 0 10px 0 0"></div>
    */
  val LightSalmon = Color(1.0f, 0.627451f, 0.47843137f)

  /**
    * The color light sea green with an RGB value of #20B2AA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#20B2AAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val LightSeaGreen = Color(0.1254902f, 0.69803923f, 0.6666667f)

  /**
    * The color light sky blue with an RGB value of #87CEFA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#87CEFAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val LightSkyBlue = Color(0.5294118f, 0.80784315f, 0.98039216f)

  /**
    * The color light slate gray with an RGB value of #778899
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#778899float:rightmargin: 0 10px 0 0"></div>
    */
  val LightSlateGray = Color(0.46666667f, 0.53333336f, 0.6f)


  /**
    * The color light steel blue with an RGB value of #B0C4DE
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#B0C4DEfloat:rightmargin: 0 10px 0 0"></div>
    */
  val LightSteelBlue = Color(0.6901961f, 0.76862746f, 0.87058824f)

  /**
    * The color light yellow with an RGB value of #FFFFE0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFFE0float:rightmargin: 0 10px 0 0"></div>
    */
  val LightYellow = Color(1.0f, 1.0f, 0.8784314f)

  /**
    * The color lime with an RGB value of #00FF00
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00FF00float:rightmargin: 0 10px 0 0"></div>
    */
  val Lime = Color(0.0f, 1.0f, 0.0f)

  /**
    * The color lime green with an RGB value of #32CD32
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#32CD32float:rightmargin: 0 10px 0 0"></div>
    */
  val LimeGreen = Color(0.19607843f, 0.8039216f, 0.19607843f)

  /**
    * The color linen with an RGB value of #FAF0E6
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FAF0E6float:rightmargin: 0 10px 0 0"></div>
    */
  val Linen = Color(0.98039216f, 0.9411765f, 0.9019608f)

  /**
    * The color magenta with an RGB value of #FF00FF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF00FFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Magenta = Color(1.0f, 0.0f, 1.0f)

  /**
    * The color maroon with an RGB value of #800000
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#800000float:rightmargin: 0 10px 0 0"></div>
    */
  val Maroon = Color(0.5019608f, 0.0f, 0.0f)

  /**
    * The color medium aquamarine with an RGB value of #66CDAA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#66CDAAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val MediumAquamarine = Color(0.4f, 0.8039216f, 0.6666667f)

  /**
    * The color medium blue with an RGB value of #0000CD
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#0000CDfloat:rightmargin: 0 10px 0 0"></div>
    */
  val MediumBlue = Color(0.0f, 0.0f, 0.8039216f)

  /**
    * The color medium orchid with an RGB value of #BA55D3
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#BA55D3float:rightmargin: 0 10px 0 0"></div>
    */
  val MediumOrchid = Color(0.7294118f, 0.33333334f, 0.827451f)

  /**
    * The color medium purple with an RGB value of #9370DB
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#9370DBfloat:rightmargin: 0 10px 0 0"></div>
    */
  val MediumPurple = Color(0.5764706f, 0.4392157f, 0.85882354f)

  /**
    * The color medium sea green with an RGB value of #3CB371
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#3CB371float:rightmargin: 0 10px 0 0"></div>
    */
  val MediumSeagreen = Color(0.23529412f, 0.7019608f, 0.44313726f)

  /**
    * The color medium slate blue with an RGB value of #7B68EE
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#7B68EEfloat:rightmargin: 0 10px 0 0"></div>
    */
  val MediumSlateBlue = Color(0.48235294f, 0.40784314f, 0.93333334f)

  /**
    * The color medium spring green with an RGB value of #00FA9A
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00FA9Afloat:rightmargin: 0 10px 0 0"></div>
    */
  val MediumSpringGreen = Color(0.0f, 0.98039216f, 0.6039216f)

  /**
    * The color medium turquoise with an RGB value of #48D1CC
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#48D1CCfloat:rightmargin: 0 10px 0 0"></div>
    */
  val MediumTurquoise = Color(0.28235295f, 0.81960785f, 0.8f)

  /**
    * The color medium violet red with an RGB value of #C71585
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#C71585float:rightmargin: 0 10px 0 0"></div>
    */
  val MediumVioletRed = Color(0.78039217f, 0.08235294f, 0.52156866f)

  /**
    * The color midnight blue with an RGB value of #191970
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#191970float:rightmargin: 0 10px 0 0"></div>
    */
  val MidnightBlue = Color(0.09803922f, 0.09803922f, 0.4392157f)

  /**
    * The color mint cream with an RGB value of #F5FFFA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F5FFFAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val MintCream = Color(0.9607843f, 1.0f, 0.98039216f)

  /**
    * The color misty rose with an RGB value of #FFE4E1
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFE4E1float:rightmargin: 0 10px 0 0"></div>
    */
  val MistyRose = Color(1.0f, 0.89411765f, 0.88235295f)

  /**
    * The color moccasin with an RGB value of #FFE4B5
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFE4B5float:rightmargin: 0 10px 0 0"></div>
    */
  val Moccasin = Color(1.0f, 0.89411765f, 0.70980394f)

  /**
    * The color navajo white with an RGB value of #FFDEAD
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFDEADfloat:rightmargin: 0 10px 0 0"></div>
    */
  val NavajoWhite = Color(1.0f, 0.87058824f, 0.6784314f)

  /**
    * The color navy with an RGB value of #000080
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#000080float:rightmargin: 0 10px 0 0"></div>
    */
  val Navy = Color(0.0f, 0.0f, 0.5019608f)

  /**
    * The color old lace with an RGB value of #FDF5E6
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FDF5E6float:rightmargin: 0 10px 0 0"></div>
    */
  val OldLace = Color(0.99215686f, 0.9607843f, 0.9019608f)

  /**
    * The color olive with an RGB value of #808000
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#808000float:rightmargin: 0 10px 0 0"></div>
    */
  val Olive = Color(0.5019608f, 0.5019608f, 0.0f)

  /**
    * The color olive drab with an RGB value of #6B8E23
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#6B8E23float:rightmargin: 0 10px 0 0"></div>
    */
  val OliveDrab = Color(0.41960785f, 0.5568628f, 0.13725491f)

  /**
    * The color orange with an RGB value of #FFA500
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFA500float:rightmargin: 0 10px 0 0"></div>
    */
  val Orange = Color(1.0f, 0.64705884f, 0.0f)

  /**
    * The color orange red with an RGB value of #FF4500
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF4500float:rightmargin: 0 10px 0 0"></div>
    */
  val OrangeRed = Color(1.0f, 0.27058825f, 0.0f)

  /**
    * The color orchid with an RGB value of #DA70D6
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DA70D6float:rightmargin: 0 10px 0 0"></div>
    */
  val Orchid = Color(0.85490197f, 0.4392157f, 0.8392157f)

  /**
    * The color pale goldenrod with an RGB value of #EEE8AA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#EEE8AAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val PaleGoldenrod = Color(0.93333334f, 0.9098039f, 0.6666667f)

  /**
    * The color pale green with an RGB value of #98FB98
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#98FB98float:rightmargin: 0 10px 0 0"></div>
    */
  val PaleGreen = Color(0.59607846f, 0.9843137f, 0.59607846f)

  /**
    * The color pale turquoise with an RGB value of #AFEEEE
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#AFEEEEfloat:rightmargin: 0 10px 0 0"></div>
    */
  val PaleTurquoise = Color(0.6862745f, 0.93333334f, 0.93333334f)

  /**
    * The color pale violet red with an RGB value of #DB7093
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DB7093float:rightmargin: 0 10px 0 0"></div>
    */
  val PaleVioletRed = Color(0.85882354f, 0.4392157f, 0.5764706f)

  /**
    * The color papaya whip with an RGB value of #FFEFD5
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFEFD5float:rightmargin: 0 10px 0 0"></div>
    */
  val PapayaWhip = Color(1.0f, 0.9372549f, 0.8352941f)

  /**
    * The color peach puff with an RGB value of #FFDAB9
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFDAB9float:rightmargin: 0 10px 0 0"></div>
    */
  val PeachPuff = Color(1.0f, 0.85490197f, 0.7254902f)

  /**
    * The color peru with an RGB value of #CD853F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#CD853Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val Peru = Color(0.8039216f, 0.52156866f, 0.24705882f)

  /**
    * The color pink with an RGB value of #FFC0CB
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFC0CBfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Pink = Color(1.0f, 0.7529412f, 0.79607844f)

  /**
    * The color plum with an RGB value of #DDA0DD
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#DDA0DDfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Plum = Color(0.8666667f, 0.627451f, 0.8666667f)

  /**
    * The color powder blue with an RGB value of #B0E0E6
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#B0E0E6float:rightmargin: 0 10px 0 0"></div>
    */
  val PowderBlue = Color(0.6901961f, 0.8784314f, 0.9019608f)

  /**
    * The color purple with an RGB value of #800080
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#800080float:rightmargin: 0 10px 0 0"></div>
    */
  val Purple = Color(0.5019608f, 0.0f, 0.5019608f)

  /**
    * The color red with an RGB value of #FF0000
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF0000float:rightmargin: 0 10px 0 0"></div>
    */
  val Red = Color(1.0f, 0.0f, 0.0f)

  /**
    * The color rosy brown with an RGB value of #BC8F8F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#BC8F8Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val RosyBrown = Color(0.7372549f, 0.56078434f, 0.56078434f)

  /**
    * The color royal blue with an RGB value of #4169E1
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#4169E1float:rightmargin: 0 10px 0 0"></div>
    */
  val RoyalBlue = Color(0.25490198f, 0.4117647f, 0.88235295f)

  /**
    * The color saddle brown with an RGB value of #8B4513
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#8B4513float:rightmargin: 0 10px 0 0"></div>
    */
  val SaddleBrown = Color(0.54509807f, 0.27058825f, 0.07450981f)

  /**
    * The color salmon with an RGB value of #FA8072
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FA8072float:rightmargin: 0 10px 0 0"></div>
    */
  val Salmon = Color(0.98039216f, 0.5019608f, 0.44705883f)

  /**
    * The color sandy brown with an RGB value of #F4A460
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F4A460float:rightmargin: 0 10px 0 0"></div>
    */
  val SandyBrown = Color(0.95686275f, 0.6431373f, 0.3764706f)

  /**
    * The color sea green with an RGB value of #2E8B57
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#2E8B57float:rightmargin: 0 10px 0 0"></div>
    */
  val SeaGreen = Color(0.18039216f, 0.54509807f, 0.34117648f)

  /**
    * The color sea shell with an RGB value of #FFF5EE
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFF5EEfloat:rightmargin: 0 10px 0 0"></div>
    */
  val SeaShell = Color(1.0f, 0.9607843f, 0.93333334f)

  /**
    * The color sienna with an RGB value of #A0522D
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#A0522Dfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Sienna = Color(0.627451f, 0.32156864f, 0.1764706f)

  /**
    * The color silver with an RGB value of #C0C0C0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#C0C0C0float:rightmargin: 0 10px 0 0"></div>
    */
  val Silver = Color(0.7529412f, 0.7529412f, 0.7529412f)

  /**
    * The color sky blue with an RGB value of #87CEEB
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#87CEEBfloat:rightmargin: 0 10px 0 0"></div>
    */
  val SkyBlue = Color(0.5294118f, 0.80784315f, 0.92156863f)

  /**
    * The color slate blue with an RGB value of #6A5ACD
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#6A5ACDfloat:rightmargin: 0 10px 0 0"></div>
    */
  val SlateBlue = Color(0.41568628f, 0.3529412f, 0.8039216f)

  /**
    * The color slate gray with an RGB value of #708090
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#708090float:rightmargin: 0 10px 0 0"></div>
    */
  val SlateGray = Color(0.4392157f, 0.5019608f, 0.5647059f)

  /**
    * The color slate grey with an RGB value of #708090
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#708090float:rightmargin: 0 10px 0 0"></div>
    */
  val SlateGrey = Color(0.18431373f, 0.30980393f, 0.30980393f)

  /**
    * The color snow with an RGB value of #FFFAFA
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFAFAfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Snow = Color(1.0f, 0.98039216f, 0.98039216f)

  /**
    * The color spring green with an RGB value of #00FF7F
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#00FF7Ffloat:rightmargin: 0 10px 0 0"></div>
    */
  val SpringGreen = Color(0.0f, 1.0f, 0.49803922f)

  /**
    * The color steel blue with an RGB value of #4682B4
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#4682B4float:rightmargin: 0 10px 0 0"></div>
    */
  val SteelBlue = Color(0.27450982f, 0.50980395f, 0.7058824f)

  /**
    * The color tan with an RGB value of #D2B48C
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#D2B48Cfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Tan = Color(0.8235294f, 0.7058824f, 0.54901963f)

  /**
    * The color teal with an RGB value of #008080
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#008080float:rightmargin: 0 10px 0 0"></div>
    */
  val Teal = Color(0.0f, 0.5019608f, 0.5019608f)

  /**
    * The color thistle with an RGB value of #D8BFD8
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#D8BFD8float:rightmargin: 0 10px 0 0"></div>
    */
  val Thistle = Color(0.84705883f, 0.7490196f, 0.84705883f)

  /**
    * The color tomato with an RGB value of #FF6347
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FF6347float:rightmargin: 0 10px 0 0"></div>
    */
  val Tomato = Color(1.0f, 0.3882353f, 0.2784314f)

  /**
    * The color turquoise with an RGB value of #40E0D0
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#40E0D0float:rightmargin: 0 10px 0 0"></div>
    */
  val Turquoise = Color(0.2509804f, 0.8784314f, 0.8156863f)

  /**
    * The color violet with an RGB value of #EE82EE
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#EE82EEfloat:rightmargin: 0 10px 0 0"></div>
    */
  val Violet = Color(0.93333334f, 0.50980395f, 0.93333334f)

  /**
    * The color wheat with an RGB value of #F5DEB3
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F5DEB3float:rightmargin: 0 10px 0 0"></div>
    */
  val Wheat = Color(0.9607843f, 0.87058824f, 0.7019608f)

  /**
    * The color white with an RGB value of #FFFFFF
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFFFFfloat:rightmargin: 0 10px 0 0"></div>
    */
  val White = Color(1.0f, 1.0f, 1.0f)

  /**
    * The color white smoke with an RGB value of #F5F5F5
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#F5F5F5float:rightmargin: 0 10px 0 0"></div>
    */
  val WhiteSmoke = Color(0.9607843f, 0.9607843f, 0.9607843f)

  /**
    * The color yellow with an RGB value of #FFFF00
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#FFFF00float:rightmargin: 0 10px 0 0"></div>
    */
  val Yellow = Color(1.0f, 1.0f, 0.0f)

  /**
    * The color yellow green with an RGB value of #9ACD32
    * <div style="border:1px solid blackwidth:40pxheight:20pxbackground-color:#9ACD32float:rightmargin: 0 10px 0 0"></div>
    */
  val YellowGreen = Color(0.6039216f, 0.8039216f, 0.19607843f)
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
    def clamp(v: Double): Float = (if v < 0 then 0 else if v > 1 then 1 else v).toFloat
    val hsb = java.awt.Color.RGBtoHSB(c.getRed, c.getGreen, c.getBlue, null)
    hsb(0) = (hsb(0) * h).toFloat
    hsb(1) = clamp(hsb(1) * s)
    hsb(2) = clamp(hsb(2) * b)
    Color.hsb(hsb(0), hsb(1), hsb(2)).withOpacity(clamp(alpha * c.getAlpha))
