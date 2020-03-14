package guarana.swing
package plaf

import language.implicitConversions
import java.awt.{Component => AwtComponent, Graphics, Graphics2D, Polygon, geom}
import scala.util.chaining._

class CssBorder(scenegraph: Scenegraph) extends javax.swing.border.Border {

  private var lastComputedInsets: Option[java.awt.Insets] = None
  def getBorderInsets(awtc: AwtComponent | UncheckedNull): java.awt.Insets | UncheckedNull = {
    val c = Component.wrap(awtc.asInstanceOf)
    val border = style.CssProperties.Border.forInstance(c) pipe (scenegraph.stateReader(_))
    val res = (border.strokes.iterator.map(_.insets) ++ border.images.iterator.map(_.insets)).foldLeft(java.awt.Insets(0, 0, 0, 0)) { (res, i) =>
      res.top = i.top.round.toInt max res.top
      res.left = i.left.round.toInt max res.left
      res.bottom = i.bot.round.toInt max res.bottom
      res.right = i.right.round.toInt max res.right
      res
    }
    lastComputedInsets = Some(res)
    res
  }
  def isBorderOpaque(): Boolean = true
  def paintBorder(awtc: AwtComponent | UncheckedNull, g: Graphics | UncheckedNull, x: Int, y: Int, width: Int, height: Int): Unit = {
    val g2 = g.create().asInstanceOf[Graphics2D]
    val c = Component.wrap(awtc.asInstanceOf)
    val borderSpec = style.CssProperties.Border.forInstance(c) pipe (scenegraph.stateReader(_))

    val insets = lastComputedInsets.get

    /** helper function to relocate x, y, width and height accordingly to its insets */
    inline def atBorder(strokeInsets: Insets)(f: (Double, Double, Double, Double) => Unit): Unit = {
      f(x + insets.left - strokeInsets.left,
        y + insets.top - strokeInsets.top,
        width - (insets.left - strokeInsets.left) - (insets.right - strokeInsets.right),
        height - (insets.top - strokeInsets.top) - (insets.bottom - strokeInsets.bot))
    } 

    // For each stroke border, we need to ensure each of the sides is properly clipper
    // to respect the color chosen. For that, we compute a diagonal line from the corner
    // to the middle of the rectangle, and the proceed to render with the specified paint
    for (stroke <- borderSpec.strokes) atBorder(stroke.insets) { (x, y, width, height) =>
      val shape = RegionPainter.shapeForRegion(
        x + stroke.leftStyle.getLineWidth / 2,
        y + stroke.topStyle.getLineWidth / 2,
        width - stroke.leftStyle.getLineWidth / 2 - stroke.rightStyle.getLineWidth / 2,
        height - stroke.topStyle.getLineWidth / 2 - stroke.botStyle.getLineWidth / 2,
        stroke.radii
      )

      val boxSize = width.min(height) / 2

      val clip = geom.Path2D.Float()
      if (stroke.topStyle.getLineWidth > 0) {
        clip.moveTo(x, y)
        clip.lineTo(x + width, y)
        clip.lineTo(x + width - boxSize, y + boxSize)
        clip.lineTo(x + boxSize, y + boxSize)
        clip.closePath()
        
        g2.setClip(clip)
        g2.setPaint(stroke.topStroke)
        g2.setStroke(stroke.topStyle)
        g2.draw(shape)
      }

      if (stroke.rightStyle.getLineWidth > 0) {
        clip.reset()
        clip.moveTo(x + width, y)
        clip.lineTo(x + width, y + height)
        clip.lineTo(x + width - boxSize, y + height - boxSize)
        clip.lineTo(x + width - boxSize, y + boxSize)
        clip.closePath()
        
        g2.setClip(clip)
        g2.setPaint(stroke.rightStroke)
        g2.setStroke(stroke.rightStyle)
        g2.draw(shape)
      }
      
      if (stroke.botStyle.getLineWidth > 0) {
        clip.reset()
        clip.moveTo(x + width, y + height)
        clip.lineTo(x, y + height)
        clip.lineTo(x + boxSize, y + height - boxSize)
        clip.lineTo(x + width - boxSize, y + height - boxSize)
        clip.closePath()
        
        g2.setClip(clip)
        g2.setPaint(stroke.botStroke)
        g2.setStroke(stroke.botStyle)
        g2.draw(shape)
      }

      if (stroke.leftStyle.getLineWidth > 0) {
        clip.reset()
        clip.moveTo(x, y + height)
        clip.lineTo(x, y)
        clip.lineTo(x + boxSize, y + boxSize)
        clip.lineTo(x + boxSize, y + height - boxSize)
        clip.closePath()
        
        g2.setClip(clip)
        g2.setPaint(stroke.leftStroke)
        g2.setStroke(stroke.leftStyle)
        g2.draw(shape)
      }

    }

    // now we render image borders
    for (image <- borderSpec.images) atBorder(image.insets) { (x, y, w, h) =>
      inline def widthOrSlice(width: Double, slice: Double) = if (width <= 0) slice else width
      val slices = image.slices
      val topWidth = widthOrSlice(image.widths.top, slices.top)
      val rightWidth = widthOrSlice(image.widths.right, slices.right)
      val botWidth = widthOrSlice(image.widths.bottom, slices.bottom)
      val leftWidth = widthOrSlice(image.widths.left, slices.left)

      val topSlice = image.slices.top
      val rightSlice = image.slices.right
      val botSlice = image.slices.bottom
      val leftSlice = image.slices.left
      val imageWidth = image.image.getWidth(null)
      val imageHeight = image.image.getHeight(null)

      //↑ slice
      drawSlice(g2, image.image,
        x + leftWidth, y, x + w - rightWidth, y + topWidth,
        leftSlice, 0, imageWidth - rightSlice, topSlice,
        image.repeatX, image.repeatY)

      //→ slice
      drawSlice(g2, image.image,
        x + w - rightWidth, y + topWidth, x + w, y + h - botWidth,
        imageWidth - rightSlice, topSlice, imageWidth, imageHeight - botSlice,
        image.repeatX, image.repeatY)

      //↓ slice
      drawSlice(g2, image.image,
        x + leftWidth, y + h - botWidth, x + w - rightWidth, y + h,
        leftSlice, imageHeight - botSlice, imageWidth - rightSlice, imageHeight,
        image.repeatX, image.repeatY)

      //← slice
      drawSlice(g2, image.image,
        x, y + topWidth, x + leftWidth, y + h - botWidth,
        0, topSlice, leftSlice, imageHeight - botSlice,
        image.repeatX, image.repeatY)

      //↖ slice
      drawSlice(g2, image.image,
        x, y, x + leftWidth, y + topWidth,
        0, 0, leftSlice, topSlice,
        image.repeatX, image.repeatY)

      //↗ slice
      drawSlice(g2, image.image,
        x + w - rightWidth, y, x + w, y + topWidth,
        imageWidth - rightSlice, 0, imageWidth, topSlice,
        image.repeatX, image.repeatY)

      //↘ slice
      drawSlice(g2, image.image,
        x + w - rightWidth, y + h - botWidth, x + w, y + h,
        imageWidth - rightSlice, imageHeight - botSlice, imageWidth, imageHeight,
        image.repeatX, image.repeatY)

      //↙ slice
      drawSlice(g2, image.image,
        x, y + h - botWidth, x + leftWidth, y + h,
        0, imageHeight - botSlice, leftSlice, imageHeight,
        image.repeatX, image.repeatY)
    }

    g2.dispose()
  }


  private def drawSlice(
    g2: Graphics2D, image: java.awt.Image,
    dx1: Double, dy1: Double, dx2: Double, dy2: Double, //area to render on target g2
    sx1: Double, sy1: Double, sx2: Double, sy2: Double, //area from the image to be rendered
    repeatX: style.BorderRepeat, repeatY: style.BorderRepeat
  ): Unit = {
    inline implicit def double2Int(d: Double): Int = d.toInt //add this implicit because it otherwise gets really annoying to work with g2

    val areaWidth = dx2 - dx1
    val areaHeight = dy2 - dy1
    val imWidth = (sx2 - sx1)
    val imHeight = (sy2 - sy1)

    var newImWidth = imWidth
    var newImHeight = imHeight
    var hspace = repeatX match {
      case style.BorderRepeat.Stretch => 
        newImWidth = areaWidth
        0
      case style.BorderRepeat.Spaces => (areaWidth - imWidth).max(0) % imWidth
      case style.BorderRepeat.Round => 
        newImWidth = areaWidth / (areaWidth / imWidth).ceil
        0
      case _ => 0
    }
    var vspace = repeatY match {
      case style.BorderRepeat.Stretch => 
        newImHeight = areaHeight
        0
      case style.BorderRepeat.Spaces => (areaHeight - imHeight).max(0) % imHeight
      case style.BorderRepeat.Round => 
        newImHeight = areaHeight / (areaHeight / imHeight).ceil
        0
      case _ => 0
    }


    var mx = dx1
    var my = dy1
    while (my < dy2) {
      while (mx < dx2) {
        g2.drawImage(image,
          mx, my, mx + newImWidth, my + newImHeight,
          sx1, sy1, sx2, sy2,
          null)
        mx += newImWidth + hspace
      }
      my += newImHeight + vspace
    }
    
  }
}