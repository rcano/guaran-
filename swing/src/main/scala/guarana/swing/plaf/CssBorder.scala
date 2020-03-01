package guarana.swing
package plaf

import language.implicitConversions
import java.awt.{Component => AwtComponent, Graphics, Graphics2D, Polygon, geom}
import scala.util.chaining._

class CssBorder(scenegraph: Scenegraph) extends javax.swing.border.Border {

  private var lastComputedInsets: Option[java.awt.Insets] = None
  def getBorderInsets(awtc: AwtComponent | UncheckedNull): java.awt.Insets | UncheckedNull = {
    val c = Component(awtc.asInstanceOf)
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
    val c = Component(awtc.asInstanceOf)
    val borderSpec = style.CssProperties.Border.forInstance(c) pipe (scenegraph.stateReader(_))

    val insets = lastComputedInsets.get

    /** helper function to relocate x, y, width and height accordingly to its insets */
    inline def atBorder(stroke: style.BorderStroke)(f: (Double, Double, Double, Double) => Unit): Unit = {
      f(x + insets.left - stroke.insets.left,
        y + insets.top - stroke.insets.top,
        width - (insets.left - stroke.insets.left) - (insets.right - stroke.insets.right),
        height - (insets.top - stroke.insets.top) - (insets.bottom - stroke.insets.bot))
    } 


    for (stroke <- borderSpec.strokes) atBorder(stroke){ (x, y, width, height) =>
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
    g2.dispose()
  }

}