package guarana.swing
package util

import java.awt.{AlphaComposite, BasicStroke, RenderingHints, PaintContext, Transparency}
import java.awt.geom.*
import java.awt.image.{BufferedImage, ColorModel, DataBufferInt, Raster, WritableRaster}
import scala.util.chaining.*
import System.out.println

trait AbstractGradientPaint(fractions: Array[Float], colors: Array[Color], colorGranularity: Int) extends Paint {

  override def getTransparency = Transparency.TRANSLUCENT

  // calculate lookup table.
  // we'll keep a granularity of 4 colors per degree, which for an image of 10000x10000 gives you a variation of color every 2.7
  // pixels at the worst 
  protected val colorLookupTable = new Array[Int](colorGranularity)
  private def frac2Idx(frac: Double) = (frac * colorGranularity).floor.toInt
  for Array((frac1, idx1), (frac2, idx2)) <- fractions.zipWithIndex.sliding(2) do {
    val tableIdx1 = frac2Idx(frac1)
    val tableIdx2 = frac2Idx(frac2)
    val color1 = colors(idx1).getRGB
    val color2 = colors(idx2).getRGB

    util.cfor(tableIdx1, _ < tableIdx2) { idx =>
      val interp = (idx - tableIdx1) / (tableIdx2 - tableIdx1).toDouble
      colorLookupTable(idx) = interpolateColors(color1, color2, interp)
      idx + 1
    }
  }

  export Color.interpolateColors

  trait GradientPixelContext(requestedColorModel: ColorModel) extends PaintContext {
    final override lazy val getColorModel =
      if requestedColorModel.getTransparency == Transparency.TRANSLUCENT then
        requestedColorModel
      else
        java.awt.GraphicsEnvironment
          .getLocalGraphicsEnvironment()
          .getDefaultScreenDevice()
          .getDefaultConfiguration()
          .getColorModel(Transparency.TRANSLUCENT)
    
    def getRaster(tileWidth: Int, tileHeight: Int): WritableRaster = {
      val res = AbstractGradientPaint.cachedRaster.get match
        case Some(w) =>
          if w.getWidth < tileWidth || w.getHeight < tileHeight || !getColorModel.isCompatibleRaster(w) then
            getColorModel.createCompatibleWritableRaster(w.getWidth max tileWidth, w.getHeight max tileHeight)
          else w

        case None => getColorModel.createCompatibleWritableRaster(tileWidth, tileHeight)
      AbstractGradientPaint.cachedRaster = ref.WeakReference(res)
      res
    }
  }
}
object AbstractGradientPaint {
  private[AbstractGradientPaint] var cachedRaster: ref.WeakReference[WritableRaster] = 
    ref.WeakReference(null)
}

class ConicalGradientPaint private(
  center: Point2D,
  rotationOffset: Float,
  fractions: Array[Float],
  colors: Array[Color]
) extends AbstractGradientPaint(fractions, colors, 360 * 4) {

  override def createContext(
    cm: ColorModel,
    deviceBounds: Bounds,
    userBounds: Rectangle2D,
    xform: AffineTransform,
    hints: RenderingHints
  ): PaintContext = 
    val transformedCenter = xform.transform(center, null)
    new GradientPixelContext(cm) {
      def dispose(): Unit = {}
      def getRaster(deviceSpaceX: Int, deviceSpaceY: Int, tileWidth: Int, tileHeight: Int): Raster =
        val rotationCenterX = transformedCenter.getX - deviceSpaceX
        val rotationCenterY = transformedCenter.getY - deviceSpaceY

        val res = getRaster(tileWidth, tileHeight)
        val data = res.getDataBuffer.asInstanceOf[DataBufferInt].getData(0)
        cfor(0, _ < tileHeight) { y =>
          cfor(0, _ < tileWidth) { x =>
            //calculate angle as a number between 0 and 1
            val angle = {
              val a = math.atan2(x - rotationCenterX, y - rotationCenterY) / (math.Pi * 2)
              ((if a < 0 then 1 + a else a) + rotationOffset) % 1.0
            }
            data(y * tileWidth + x) = colorLookupTable((angle * colorLookupTable.length).toInt)
            x + 1
          }
          y + 1
        }

        res
    }
}

object ConicalGradientPaint {
  def apply(
    center: Point2D,
    rotationOffset: Float,
    fractions: Array[Float],
    colors: Array[Color]
  ): ConicalGradientPaint =
    require(fractions.length == colors.length, "Fractions and colors must have same length")
    var targetFracs = fractions
    var targetColors = colors
    if targetFracs(0) != 0.0f then 
      targetFracs = 0.0f +: targetFracs
      targetColors = colors(0) +: targetColors
    if targetFracs.last != 1.0f then 
      targetFracs = targetFracs :+ 1.0f
      targetColors = targetColors :+ colors(0)
    new ConicalGradientPaint(center, rotationOffset, targetFracs, targetColors)
}

class RoundRectangleGradientPaint private(
  fractions: Array[Float],
  colors: Array[Color],
  rectX: Double = 0,
  rectY: Double = 0,
  rectWidth: Double = 1,
  rectHeight: Double = 1,
  arcWidth: Double = 0,
  arcHeight: Double = 0,
  percentageBased: Boolean = true
) extends AbstractGradientPaint(fractions, colors, 1500) {
  override def createContext(
    inheritedColorModel: ColorModel,
    deviceBounds: Bounds,
    userBounds: Rectangle2D,
    xform: AffineTransform,
    hints: RenderingHints
  ): PaintContext = new PaintContext {
    val xformedUserBounds = xform.createTransformedShape(userBounds).getBounds2D
    var image = {
      if inheritedColorModel.getTransparency == Transparency.TRANSLUCENT then
        val raster = inheritedColorModel.createCompatibleWritableRaster(
          deviceBounds.getWidth.ceil.toInt,
          deviceBounds.getHeight.ceil.toInt
        )
        BufferedImage(inheritedColorModel, raster, true, null)  
      else
        java.awt.GraphicsEnvironment
          .getLocalGraphicsEnvironment()
          .getDefaultScreenDevice()
          .getDefaultConfiguration()
          .createCompatibleImage(
            deviceBounds.getWidth.ceil.toInt,
            deviceBounds.getHeight.ceil.toInt,
            Transparency.TRANSLUCENT
          )
    }
    var compatibleRaster =  image.getRaster
    var emptyTile = getColorModel.createCompatibleWritableRaster(128, 128)
    
    // println(s"""inherited color model $inheritedColorModel - transparency: ${inheritedColorModel.getTransparency} - components#: ${inheritedColorModel.getNumComponents}
    //            |device bounds: $deviceBounds
    //            |user bounds: $userBounds
    //            |xform $xform
    //            |xformed user bounds $xformedUserBounds
    //            |using color model ${getColorModel}
    //            |--------------------------------------""".stripMargin)

    locally {
      val im = BufferedImage(getColorModel, emptyTile, true, null).createGraphics()
      val g2 = im
      g2.setPaint(colors.last)
      g2.fillRect(0, 0, compatibleRaster.getWidth, compatibleRaster.getHeight)
      g2.dispose()
      im.dispose()
    }

    locally {
      val g2 = image.createGraphics()
      g2.setRenderingHints(hints)
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
      g2.setComposite(AlphaComposite.Src)

      g2.setPaint(colors.last)
      g2.fillRect(0, 0, compatibleRaster.getWidth, compatibleRaster.getHeight)

      g2.setTransform(
        AffineTransform()
          .tap(_.translate(-deviceBounds.getX, -deviceBounds.getY))
          .tap(_.concatenate(xform))
      )

      val maxWidth = if percentageBased then userBounds.getWidth * rectWidth else rectWidth
      val maxHeight = if percentageBased then userBounds.getHeight * rectHeight else rectHeight
      val maxX = (if percentageBased then userBounds.getWidth * rectX else rectX) + userBounds.getX
      val maxY = (if percentageBased then userBounds.getHeight * rectY else rectY) + userBounds.getY
      val maxArcw = if percentageBased then userBounds.getWidth * arcWidth else arcWidth
      val maxArch = if percentageBased then userBounds.getWidth * arcHeight else arcHeight

      // need to calculate the two min points of the rectangle, these are the points
      // that coincide with either height or width being 0
      val isLonger = maxWidth < maxHeight
      val maxSide = if isLonger then maxHeight else maxWidth
      val minSide = if isLonger then maxWidth else maxHeight
      val minX = minSide / 2 + maxX
      val minY = minSide / 2 + maxY

      val maxRectDistance = math.sqrt(math.pow(minX - maxX, 2) + math.pow(minY - maxY, 2))

      g2.setStroke(BasicStroke(2f))
      val rrect = RoundRectangle2D.Double(maxX, maxY, maxWidth, maxHeight, maxArcw, maxArch)
      // println(s"rrect: ${rrect.getBounds2D} xformed = ${g2.getTransform.createTransformedShape(rrect).getBounds2D}")
      while rrect.width > 0 && rrect.height > 0 do
        val rectDist = math.sqrt(math.pow(minX - rrect.x, 2) + math.pow(minY - rrect.y, 2))
        val color = colorLookupTable((rectDist / maxRectDistance * colorLookupTable.length).min(colorLookupTable.length - 1).toInt)
        g2.setPaint(Color.argb(color))
        g2.draw(rrect)
        rrect.setRoundRect(
          rrect.x + 1,
          rrect.y + 1,
          rrect.width - 2,
          rrect.height - 2,
          rrect.arcwidth - (maxArcw / minX),
          rrect.archeight - (maxArcw / minY),
        )
    }

    override def dispose =
      emptyTile = null
      compatibleRaster = null
      image = null

    override def getColorModel = image.getColorModel

    def getRaster(deviceSpaceX: Int, deviceSpaceY: Int, tileWidth: Int, tileHeight: Int): Raster =
      val x = (deviceSpaceX - deviceBounds.getX).ceil.toInt
      val y = (deviceSpaceY - deviceBounds.getY).ceil.toInt
      val isEmptyTile = x < 0 || x >= compatibleRaster.getWidth || y < 0 || y >= compatibleRaster.getHeight
      if isEmptyTile then
        emptyTile
      else 
        compatibleRaster.createChild(
          x,
          y,
          tileWidth min (compatibleRaster.getWidth - x),
          tileHeight min (compatibleRaster.getHeight - y),
          0,
          0,
          null
        )
  }

}
object RoundRectangleGradientPaint {
  def apply(
    fractions: Array[Float],
    colors: Array[Color],
    rectX: Double = 0,
    rectY: Double = 0,
    rectWidth: Double = 1,
    rectHeight: Double = 1,
    arcWidth: Double = 0,
    arcHeight: Double = 0,
    percentageBased: Boolean = true
  ) =
    require(fractions.length == colors.length, "Fractions and colors must have same length")
    var targetFracs = fractions
    var targetColors = colors
    if targetFracs(0) != 0.0f then 
      targetFracs = 0.0f +: targetFracs
      targetColors = colors(0) +: targetColors
    if targetFracs.last != 1.0f then 
      targetFracs = targetFracs :+ 1.0f
      targetColors = targetColors :+ colors.last
    new RoundRectangleGradientPaint(targetFracs, targetColors, rectX, rectY, rectWidth, rectHeight, arcWidth, arcHeight, percentageBased)
}