package guarana.swing
package plaf

import language.implicitConversions
import java.awt.{geom, Graphics2D, Shape}

object RegionPainter {
  def paintRegion(
      background: style.Background,
      g2: Graphics2D,
      x: Double,
      y: Double,
      w: Double,
      h: Double,
      regionShape: Shape | Null = null
  ): Unit = {
    for (bf <- background.fills) {
      val style.BackgroundFill(fill, radii, insets) = bf
      g2.setPaint(fill)
      if regionShape == null then g2.fill(shapeForRegion(x + insets.left, y + insets.top, w - insets.right, h - insets.bot, radii))
      else g2.fill(regionShape)
    }

    val regionAspectRatio = w / h
    for (bi <- background.images) {
      val (imWidth, imHeight) = (bi.image.getWidth(null).toDouble, bi.image.getHeight(null).toDouble)
      val imageAspectRatio = imWidth / imHeight
      var (newImWidth, newImHeight) = bi.size match {
        case style.BackgroundSize.Dimension(tw, th, wp, hp) =>
          if (tw == style.Auto && th == style.Auto) (tw, th)
          else {
            val tentativeW = if (wp) tw * w else tw
            val tentativeH = if (hp) th * h else th
            (
              if (tw == style.Auto) tentativeH * imageAspectRatio else tentativeW,
              if (th == style.Auto) tentativeW * imHeight / imWidth else tentativeH
            )
          }

        case style.BackgroundSize.Contain =>
          if (regionAspectRatio > imageAspectRatio) (h * imageAspectRatio, h)
          else (w, w * imHeight / imWidth)

        case style.BackgroundSize.Cover =>
          if (regionAspectRatio > imageAspectRatio) (w, w * imHeight / imWidth)
          else (h * imageAspectRatio, h)
      }

      val hSpacing = bi.repeatX match {
        //if there's remaining space, the modulo with newImWidth is the remaining space after repeating the image
        case style.BackgroundRepeat.Spaces => (w - newImWidth).max(0) % newImWidth
        case style.BackgroundRepeat.Round =>
          // we have to compute a new width making the image stretch between 1 and 2 times. Spacing will be 0
          newImWidth = newImWidth * ((w / newImWidth).ceil % 2)
          0
        case _ => 0
      }
      val vSpacing = bi.repeatY match {
        //if there's remaining space, the modulo with newImHeight is the remaining space after repeating the image
        case style.BackgroundRepeat.Spaces => (h - newImHeight).max(0) % newImHeight
        case style.BackgroundRepeat.Round =>
          // we have to compute a new height making the image stretch between 1 and 2 times. Spacing will be 0
          newImHeight = newImHeight / ((h / newImHeight).ceil % 2)
          0
        case _ => 0
      }

      var imageX = x + {
        var offset = bi.position.horizontalPosition
        if (bi.position.horizontalAsPercentage) offset = offset * w
        if (bi.position.horizontalSide == style.HorizontalSide.Right) offset = w - offset - newImWidth
        offset
      }
      var imageY = y + {
        var offset = bi.position.verticalPosition
        if (bi.position.verticalAsPercentage) offset = offset * h
        if (bi.position.verticalSide == style.VerticalSide.Bot) offset = h - offset - newImHeight
        offset
      }

      val xLimit = if (bi.repeatX == style.BackgroundRepeat.NoRepeat) imageX + newImWidth else x + w
      val yLimit = if (bi.repeatY == style.BackgroundRepeat.NoRepeat) imageY + newImHeight else y + h

      while (imageY < yLimit) {
        while (imageX < xLimit) {
          // val bestImg = bi.image match {
          //   case mi: MultiResolutionImage => mi.getResolutionVariant(newImWidth, newImHeight)
          //   case other => other
          // }
          g2.drawImage(bi.image, imageX.toInt, imageY.toInt, newImWidth.toInt, newImHeight.toInt, null)
          imageX += newImWidth + hSpacing
        }
        imageY += newImHeight + vSpacing
      }
    }
  }

  def shapeForRegion(x: Double, y: Double, width: Double, height: Double, cornerRadii: style.CornerRadii): Shape = {
    implicit def double2Float(d: Double): Float = d.toFloat //explicitly allow this in this context
    if (cornerRadii.isSquare) new geom.Rectangle2D.Float(x, y, width, height)
    else {
      val topLeftHRadius = cornerRadii.topLeftHorizontalRadius * (if (cornerRadii.topLeftHorizontalRadiusAsPercent) width / 100 else 1)
      val topLeftVRadius = cornerRadii.topLeftVerticalRadius * (if (cornerRadii.topLeftVerticalRadiusAsPercent) height / 100 else 1)
      val topRightHRadius = cornerRadii.topRightHorizontalRadius * (if (cornerRadii.topRightHorizontalRadiusAsPercent) width / 100 else 1)
      val topRightVRadius = cornerRadii.topRightVerticalRadius * (if (cornerRadii.topRightVerticalRadiusAsPercent) height / 100 else 1)
      val botLeftHRadius = cornerRadii.botLeftHorizontalRadius * (if (cornerRadii.botLeftHorizontalRadiusAsPercent) width / 100 else 1)
      val botLeftVRadius = cornerRadii.botLeftVerticalRadius * (if (cornerRadii.botLeftVerticalRadiusAsPercent) height / 100 else 1)
      val botRightHRadius = cornerRadii.botRightHorizontalRadius * (if (cornerRadii.botRightHorizontalRadiusAsPercent) width / 100 else 1)
      val botRightVRadius = cornerRadii.botRightVerticalRadius * (if (cornerRadii.botRightVerticalRadiusAsPercent) height / 100 else 1)

      val res = new geom.Path2D.Float()
      res.moveTo(x + topLeftHRadius, y)

      res.lineTo(x + width - topRightHRadius, y)
      if (topRightHRadius != 0 && topRightVRadius != 0)
        res.append(
          new geom.Arc2D.Float(x + width - topRightHRadius * 2, y, topRightHRadius * 2, topRightVRadius * 2, 90, -90, geom.Arc2D.OPEN),
          true
        )

      res.lineTo(x + width, y + height - botRightVRadius)
      if (botRightHRadius != 0 && botRightVRadius != 0)
        res.append(
          new geom.Arc2D.Float(
            x + width - botRightHRadius * 2,
            y + height - botRightVRadius * 2,
            botRightHRadius * 2,
            botRightVRadius * 2,
            0,
            -90,
            geom.Arc2D.OPEN
          ),
          true
        )

      res.lineTo(x + botLeftHRadius, y + height)
      if (botLeftHRadius != 0 && botLeftVRadius != 0)
        res.append(
          new geom.Arc2D.Float(x, y + height - botLeftVRadius * 2, botLeftHRadius * 2, botLeftVRadius * 2, -90, -90, geom.Arc2D.OPEN),
          true
        )

      res.lineTo(x, y + topLeftVRadius)
      if (topLeftHRadius != 0 && topLeftVRadius != 0)
        res.append(new geom.Arc2D.Float(x, y, topLeftHRadius * 2, topLeftVRadius * 2, 180, -90, geom.Arc2D.OPEN), true)

      res.closePath()

      res
    }
  }
}
