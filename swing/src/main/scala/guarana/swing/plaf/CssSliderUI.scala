package guarana.swing
package plaf

import language.implicitConversions
import java.awt.{Dimension, Rectangle}
import javax.swing.{AbstractButton, JComponent, JButton, UIManager}
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

  override def getPreferredHorizontalSize = scale(super.getPreferredHorizontalSize)
  override def getPreferredVerticalSize = scale(super.getPreferredVerticalSize)
  override def getMinimumHorizontalSize = scale(super.getMinimumHorizontalSize)
  override def getMinimumVerticalSize = scale(super.getMinimumVerticalSize)
  override def getTickLength = scale(super.getTickLength)
  override def getThumbSize = getShape() match
    case null => scale(super.getThumbSize)
    case shape => 
      val ti = getThumbInsets()
      Dimension(shape.getBounds.width + (ti.left + ti.right).round.toInt, shape.getBounds.height + (ti.top + ti.bot).round.toInt)

  private def getThumbInsets(): Insets = 
    val s = slider
    val border = Option(style.CssProperties.SliderThumbBorder.forInstance(s).pipe(scenegraph.stateReader(_)))
    border.map(CssBorder.getBorderInsets).getOrElse(Insets(0, 0, 0, 0))

  override def paintThumb(g: Graphics) = getShape() match {
    case null => metalPaintThumb(g)
    case shape =>
      val g2 = g.upgrade.withAliasing
      val s = slider
      val thumbBorder = style.CssProperties.SliderThumbBorder.forInstance(s).pipe(scenegraph.stateReader(_))
      val thumbBackground = style.CssProperties.SliderThumbBackground.forInstance(s).pipe(scenegraph.stateReader(_))
      require(thumbBorder != null || thumbBackground != null, "Thumb shape specified but no border nor background was defined, this would produce an invisible thumb")

      val thumbInsets = getThumbInsets()

      val b2d = shape.getBounds2D
      val transformedShape = java.awt.geom.AffineTransform
        .getTranslateInstance(thumbRect.x - b2d.getMinX, thumbRect.y - b2d.getMinY)
        .tap(_.scale(thumbRect.width / b2d.getWidth, thumbRect.height / b2d.getHeight))
        .createTransformedShape(shape)

      if thumbBackground != null then
        RegionPainter.paintRegion(thumbBackground, g2,
          thumbRect.x + thumbInsets.left, thumbRect.y + thumbInsets.top,
          thumbRect.width - thumbInsets.right - thumbInsets.left,
          thumbRect.height - thumbInsets.top - thumbInsets.bot, transformedShape)

      if thumbBorder != null then
        CssBorder.paintBorder(thumbBorder, thumbInsets, g2, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, transformedShape)

  }

  // copied over from MetalSliderUI to adjust for scaling
  private def metalPaintThumb(g: Graphics) = {
    val knobBounds = thumbRect
    val g2 = g.create().upgrade
    g2.translate(knobBounds.x, knobBounds.y)
    g2.scale(scale(1.0), scale(1.0))
    if (slider.getOrientation() == javax.swing.SwingConstants.HORIZONTAL)
      MetalSliderUI.horizThumbIcon.paintIcon(slider, g2, 0, 0)
    else
      MetalSliderUI.vertThumbIcon.paintIcon(slider, g2, 0, 0)
    g2.dispose()
  }

  override def paintTrack(g: Graphics) = {
    val g2 = g.create().upgrade.withAliasing
    val s = slider

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
        if (slider.getComponentOrientation().isLeftToRight()) regionW = regionW * ratio
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
