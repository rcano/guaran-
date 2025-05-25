package guarana
package swing
package plaf

import language.implicitConversions
import java.awt.{Dimension, Shape}
import javax.swing.JComponent
import javax.swing.plaf.metal.MetalSliderUI
import scala.annotation.static
import scala.util.chaining.*

class CssSliderUi extends MetalSliderUI, CssSwingControlUi {
  transparent inline def scale(d: Int | Double | Dimension): Any = {
    val sc = scenegraph
    val ratio = sc.stateReader(sc.emSize) / 14.0 // try to adjust the scale based on the em size. This is just an heuristic
    inline d match {
      case i: Int => (i * ratio).round.toInt
      case d: Double => d * ratio
      case d: Dimension => Dimension((d.width * ratio).round.toInt, (d.height * ratio).round.toInt)
    }
  }

  private def getShape() = {
    val s = slider
    style.CssProperties.SliderThumbShape.forInstance(s).pipe(scenegraph.stateReader(_))
  }

  override def getPreferredHorizontalSize = scale(super.getPreferredHorizontalSize.unn)
  override def getPreferredVerticalSize = scale(super.getPreferredVerticalSize.unn)
  override def getMinimumHorizontalSize = scale(super.getMinimumHorizontalSize.unn)
  override def getMinimumVerticalSize = scale(super.getMinimumVerticalSize.unn)
  override def getTickLength = scale(super.getTickLength)
  override def getThumbSize = getShape() match
    case null => scale(super.getThumbSize.unn)
    case shape: Shape => 
      val ti = getThumbInsets()
      Dimension(shape.getBounds.unn.width + (ti.left + ti.right).round.toInt, shape.getBounds.unn.height + (ti.top + ti.bot).round.toInt)

  private def getThumbInsets(): Insets = 
    val s = slider
    val border = style.CssProperties.SliderThumbBorder.forInstance(s).pipe(scenegraph.stateReader(_)).toOption
    border.map(CssBorder.getBorderInsets).getOrElse(Insets(0, 0, 0, 0))

  override def paintThumb(g: Graphics) = getShape() match {
    case null => metalPaintThumb(g)
    case shape: Shape =>
      val g2 = g.upgrade.withAliasing
      val s = slider
      val thumbBorder = style.CssProperties.SliderThumbBorder.forInstance(s).pipe(scenegraph.stateReader(_))
      val thumbBackground = style.CssProperties.SliderThumbBackground.forInstance(s).pipe(scenegraph.stateReader(_))
      require(thumbBorder != null || thumbBackground != null, "Thumb shape specified but no border nor background was defined, this would produce an invisible thumb")

      val thumbInsets = getThumbInsets()

      val b2d = shape.getBounds2D.unn
      val nnThumbRect = thumbRect.unn
      val transformedShape = java.awt.geom.AffineTransform
        .getTranslateInstance(nnThumbRect.x - b2d.getMinX, nnThumbRect.y - b2d.getMinY).unn
        .tap(_.scale(nnThumbRect.width / b2d.getWidth, nnThumbRect.height / b2d.getHeight))
        .createTransformedShape(shape)

      if thumbBackground != null then
        RegionPainter.paintRegion(thumbBackground, g2,
          nnThumbRect.x + thumbInsets.left, nnThumbRect.y + thumbInsets.top,
          nnThumbRect.width - thumbInsets.right - thumbInsets.left,
          nnThumbRect.height - thumbInsets.top - thumbInsets.bot, transformedShape)

      if thumbBorder != null then
        CssBorder.paintBorder(thumbBorder, thumbInsets, g2, nnThumbRect.x, nnThumbRect.y, nnThumbRect.width, nnThumbRect.height, transformedShape)

  }

  // copied over from MetalSliderUI to adjust for scaling
  private def metalPaintThumb(g: Graphics) = {
    val knobBounds = thumbRect.unn
    val g2 = g.create().upgrade
    g2.translate(knobBounds.x, knobBounds.y)
    g2.scale(scale(1.0), scale(1.0))
    if (slider.unn.getOrientation() == javax.swing.SwingConstants.HORIZONTAL)
      MetalSliderUI.horizThumbIcon.unn.paintIcon(slider, g2, 0, 0)
    else
      MetalSliderUI.vertThumbIcon.unn.paintIcon(slider, g2, 0, 0)
    g2.dispose()
  }

  override def paintTrack(g: Graphics) = {
    val g2 = g.create().upgrade.withAliasing
    val slider = this.slider.unn
    val s = slider
    val trackRect = this.trackRect.unn

    {
      val trackBackground = style.CssProperties.SliderTrackBackground.forInstance(s).pipe(scenegraph.stateReader(_))
      val trackBorder = style.CssProperties.SliderTrackBorder.forInstance(s).pipe(scenegraph.stateReader(_))

      val trackInsets = CssBorder.getBorderInsets(trackBorder)
  
      RegionPainter.paintRegion(trackBackground, g2,
        trackRect.x + trackInsets.left, trackRect.y + trackInsets.top,
        trackRect.width - (trackInsets.left + trackInsets.right),
        trackRect.height - (trackInsets.top + trackInsets.bot))

      CssBorder.paintBorder(trackBorder, trackInsets, g2, trackRect.x, trackRect.y, trackRect.width, trackRect.height)
    }

    {
      val trackBackground = style.CssProperties.SliderTrackFillBackground.forInstance(s).pipe(scenegraph.stateReader(_))
      val trackBorder = style.CssProperties.SliderTrackFillBorder.forInstance(s).pipe(scenegraph.stateReader(_))

      val trackInsets = CssBorder.getBorderInsets(trackBorder)
  
      var regionX: Double = trackRect.x
      var regionY: Double = trackRect.y
      var regionW: Double = trackRect.width
      var regionH: Double = trackRect.height

      val ratio = (slider.getValue - slider.getMinimum) / (slider.getMaximum - slider.getMinimum).toDouble

      if (slider.getOrientation == javax.swing.SwingConstants.HORIZONTAL) {
        if (slider.getComponentOrientation().unn.isLeftToRight()) regionW = regionW * ratio
        else {
          val d = regionW - (regionW * ratio)
          regionX += d
          regionW -= d
        }
      } else {
        val d = regionH - (regionH * ratio)
        regionY += d
        regionH -= d
      }

      RegionPainter.paintRegion(trackBackground, g2,
        regionX + trackInsets.left, regionY + trackInsets.top,
        regionW - (trackInsets.left + trackInsets.right),
        regionH - (trackInsets.top + trackInsets.bot))

      CssBorder.paintBorder(trackBorder, trackInsets, g2, regionX, regionY, regionW, regionH)
    }
  }

  override def paint(g: Graphics, c: JComponent) = {
    if (c.isOpaque) {
      val bck = style.CssProperties.Background.forInstance(c).pipe(scenegraph.stateReader(_))
      plaf.RegionPainter.paintRegion(bck, g.upgrade.withAliasing, 0, 0, c.getWidth, c.getHeight)
    }

    super.paint(g, c)
  }
}

object CssSliderUi {
  @static def createUI(c: JComponent) = CssSliderUi() //sadly we have to instantiate a new one, because of how MetalSliderUI is defined...
}
