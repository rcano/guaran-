package guarana.swing
package plaf

import language.implicitConversions
import java.awt.{Graphics, Graphics2D, Shape, geom}
import javax.swing.plaf.synth._
import scala.util.chaining._

object RegionPainter {
  def paintRegion(sg: Scenegraph, comp: javax.swing.JComponent, g2: Graphics2D, x: Int, y: Int, w: Int, h: Int): Unit = {
    val background = style.CssProperties.Background.forInstance(comp) pipe (sg.stateReader(_))
    for (bf <- background.fills) {
      val style.BackgroundFill(fill, radii, insets) = bf
      g2.setPaint(fill)
      g2.fill(shapeForRegion(x + insets.left, y + insets.top, w - insets.right, h - insets.bot, radii))
    }
  }

  // case class ShapeForRegion(topPart: geom.Path2D.Float, rightPart: geom.Path2D.Float, botPart: geom.Path2D.Float, leftPart: geom.Path2D.Float) {
  //   def merged = {
  //     topPart.append(rightPart, true)
  //     topPart.append(botPart, true)
  //     topPart.append(leftPart, true)
  //     topPart.closePath()
  //     topPart
  //   }
  // }
  def shapeForRegion(x: Double, y: Double, width: Double, height: Double, cornerRadii: style.CornerRadii): Shape = {
    implicit def double2Float(d: Double): Float = d.toFloat //explicitly allow this in this context
    if (cornerRadii.isSquare) new geom.Rectangle2D.Float(x, y, width, height)
    else {
      val topLeftHRadius = cornerRadii.topLeftHorizontalRadius * (if (cornerRadii.topLeftHorizontalRadiusAsPercent) width / 100 else 1 )
      val topLeftVRadius = cornerRadii.topLeftVerticalRadius * (if (cornerRadii.topLeftVerticalRadiusAsPercent) height / 100 else 1 )
      val topRightHRadius = cornerRadii.topRightHorizontalRadius * (if (cornerRadii.topRightHorizontalRadiusAsPercent) width / 100 else 1 )
      val topRightVRadius = cornerRadii.topRightVerticalRadius * (if (cornerRadii.topRightVerticalRadiusAsPercent) height / 100 else 1 )
      val botLeftHRadius = cornerRadii.botLeftHorizontalRadius * (if (cornerRadii.botLeftHorizontalRadiusAsPercent) width / 100 else 1 )
      val botLeftVRadius = cornerRadii.botLeftVerticalRadius * (if (cornerRadii.botLeftVerticalRadiusAsPercent) height / 100 else 1 )
      val botRightHRadius = cornerRadii.botRightHorizontalRadius * (if (cornerRadii.botRightHorizontalRadiusAsPercent) width / 100 else 1 )
      val botRightVRadius = cornerRadii.botRightVerticalRadius * (if (cornerRadii.botRightVerticalRadiusAsPercent) height / 100 else 1 )

      val res = new geom.Path2D.Float()
      res.moveTo(x + topLeftHRadius, y)

      res.lineTo(x + width - topRightHRadius, y)
      if (topRightHRadius != 0 && topRightVRadius != 0)
        res.append(new geom.Arc2D.Float(x + width - topRightHRadius * 2, y, topRightHRadius * 2, topRightVRadius * 2, 90, -90, geom.Arc2D.OPEN), true)
      
      res.lineTo(x + width, y + height - botRightVRadius)
      if (botRightHRadius != 0 && botRightVRadius != 0)
        res.append(new geom.Arc2D.Float(x + width - botRightHRadius * 2, y + height - botRightVRadius * 2, botRightHRadius * 2, botRightVRadius * 2, 0, -90, geom.Arc2D.OPEN), true)
      
      res.lineTo(x + botLeftHRadius, y + height)
      if (botLeftHRadius != 0 && botLeftVRadius != 0)
        res.append(new geom.Arc2D.Float(x, y + height - botLeftVRadius * 2, botLeftHRadius * 2, botLeftVRadius * 2, -90, -90, geom.Arc2D.OPEN), true)
      
      res.lineTo(x, y + topLeftVRadius)
      if (topLeftHRadius != 0 && topLeftVRadius != 0)
        res.append(new geom.Arc2D.Float(x, y, topLeftHRadius * 2, topLeftVRadius * 2, 180, -90, geom.Arc2D.OPEN), true)  
    
      res
    }
  }
  // def shapeForRegionParts(x: Double, y: Double, width: Double, height: Double, cornerRadii: style.CornerRadii): ShapeForRegion = {
  //   implicit def double2Float(d: Double): Float = d.toFloat //explicitly allow this in this context
  //   //produce a romboid that is basically the full square with the corners cut off
  //     //then add the arc corresponding corner radii

  //     val topLeftHRadius = cornerRadii.topLeftHorizontalRadius * (if (cornerRadii.topLeftHorizontalRadiusAsPercent) width / 100 else 1 )
  //     val topLeftVRadius = cornerRadii.topLeftVerticalRadius * (if (cornerRadii.topLeftVerticalRadiusAsPercent) height / 100 else 1 )
  //     val topRightHRadius = cornerRadii.topRightHorizontalRadius * (if (cornerRadii.topRightHorizontalRadiusAsPercent) width / 100 else 1 )
  //     val topRightVRadius = cornerRadii.topRightVerticalRadius * (if (cornerRadii.topRightVerticalRadiusAsPercent) height / 100 else 1 )
  //     val botLeftHRadius = cornerRadii.botLeftHorizontalRadius * (if (cornerRadii.botLeftHorizontalRadiusAsPercent) width / 100 else 1 )
  //     val botLeftVRadius = cornerRadii.botLeftVerticalRadius * (if (cornerRadii.botLeftVerticalRadiusAsPercent) height / 100 else 1 )
  //     val botRightHRadius = cornerRadii.botRightHorizontalRadius * (if (cornerRadii.botRightHorizontalRadiusAsPercent) width / 100 else 1 )
  //     val botRightVRadius = cornerRadii.botRightVerticalRadius * (if (cornerRadii.botRightVerticalRadiusAsPercent) height / 100 else 1 )

  //     val top = new geom.Path2D.Float()
  //     if (topLeftHRadius != 0 && topLeftVRadius != 0)
  //       top.append(new geom.Arc2D.Float(x, y, topLeftHRadius * 2, topLeftVRadius * 2, 135, -45, geom.Arc2D.OPEN), true)
  //     else top.moveTo(x, y)
  //     top.lineTo(x + width - topRightHRadius, y)
  //     if (topRightHRadius != 0 && topRightVRadius != 0)
  //       top.append(new geom.Arc2D.Float(x + width - topRightHRadius * 2, y, topRightHRadius * 2, topRightVRadius * 2, 90, -45, geom.Arc2D.OPEN), true)
      
  //     val right = new geom.Path2D.Float()
  //     if (topRightHRadius != 0 && topRightVRadius != 0)
  //       right.append(new geom.Arc2D.Float(x + width - topRightHRadius * 2, y, topRightHRadius * 2, topRightVRadius * 2, 45, -45, geom.Arc2D.OPEN), true)
  //     else right.moveTo(x + width, y)
  //     right.lineTo(x + width, y + height - botRightVRadius)
  //     if (botRightHRadius != 0 && botRightVRadius != 0)
  //       right.append(new geom.Arc2D.Float(x + width - botRightHRadius * 2, y + height - botRightVRadius * 2, botRightHRadius * 2, botRightVRadius * 2, 0, -45, geom.Arc2D.OPEN), true)
      
  //     val bot = new geom.Path2D.Float()
  //     if (botRightHRadius != 0 && botRightVRadius != 0)
  //       bot.append(new geom.Arc2D.Float(x + width - botRightHRadius * 2, y + height - botRightVRadius * 2, botRightHRadius * 2, botRightVRadius * 2, -45, -45, geom.Arc2D.OPEN), true)
  //     else bot.moveTo(x + width, y + height)
  //     bot.lineTo(x + botLeftHRadius, y + height)
  //     if (botLeftHRadius != 0 && botLeftVRadius != 0)
  //       bot.append(new geom.Arc2D.Float(x, y + height - botLeftVRadius * 2, botLeftHRadius * 2, botLeftVRadius * 2, -90, -45, geom.Arc2D.OPEN), true)
      
  //     val left = new geom.Path2D.Float()
  //     if (botLeftHRadius != 0 && botLeftVRadius != 0)
  //       left.append(new geom.Arc2D.Float(x, y + height - botLeftVRadius * 2, botLeftHRadius * 2, botLeftVRadius * 2, -135, -45, geom.Arc2D.OPEN), true)
  //     else left.moveTo(x, y + height)
  //     left.lineTo(x, y + topLeftVRadius)
  //     if (topLeftHRadius != 0 && topLeftVRadius != 0)
  //       left.append(new geom.Arc2D.Float(x, y, topLeftHRadius * 2, topLeftVRadius * 2, 180, -45, geom.Arc2D.OPEN), true)

  //     ShapeForRegion(top, right, bot, left)
  // }
}