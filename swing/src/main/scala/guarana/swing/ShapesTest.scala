package guarana.swing

import language.implicitConversions
import better.files._
import Binding.dyn
import java.awt.BasicStroke
import javax.swing.JToggleButton
import scala.util.chaining._
import plaf.{upgrade, withAliasing}

@main def ShapesTest: Unit = {
  // scala.sys.props("") = ""
  val scenegraph = Scenegraph()
  scenegraph.stylist = new Stylist {

    val corners = style.CornerRadii.simple(4, 4, 4, 4)
    val bck = style.Background(fills = IArray(style.BackgroundFill(Color.AliceBlue, corners, Insets.all(0))))
    val hoverBck = style.Background(fills = IArray(style.BackgroundFill(Color.White, corners, Insets.all(0))))
    val pressedBck = style.Background(fills = IArray(style.BackgroundFill(Color.Gainsboro, corners, Insets.all(0))))
    def apply[T](info: Stylist.ScenegraphInfo)(prop: Keyed[ObsVal[T]]) = {
      prop match {
        case Keyed(style.CssProperties.Border, jb: javax.swing.AbstractButton) =>
          val emSize = scenegraph.stateReader(scenegraph.emSize)
          val h = jb.getBounds.getHeight
          Some(
            style.Border(strokes = IArray(
              // border,
              style.BorderStroke.simple(
                java.awt.LinearGradientPaint(0, 0, 0, h.toFloat.max(1), Array[Float](0, 1), Array[Color](Color.Lavender, Color.Lavender.darker.nn)),
                BasicStroke((emSize / 14).toInt), corners, Insets.all(emSize * 0.3))
            ))
          ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.Background, b: javax.swing.AbstractButton) => Some(
          if (b.getModel.isPressed || b.getModel.isSelected) pressedBck 
          else if (b.getModel.isRollover) hoverBck
          else bck
        ).asInstanceOf[Option[T]]
        case _ => 
          None
      }
    }
    def invalidateCache(node: Any) = ()
  }

  scenegraph.update {
    val presses = Var[Int]("presses", 0)

    val root = AbsolutePositioningPane()

    val p = Pane.wrap(new javax.swing.JPanel {

      override def paintComponent(g: java.awt.Graphics): Unit = {
        val g2 = g.create().upgrade.withAliasing
        val zeroX = getWidth() * 0.2
        val zeroY = getHeight() * 0.2
        val w = getWidth() * 0.8 - zeroX
        val h = getHeight() * 0.8 - zeroY

        val cornerRadii = style.CornerRadii.simple(40, 0, 40, 0, true)

        val shape = plaf.RegionPainter.shapeForRegion(zeroX, zeroY, w, h, cornerRadii)

        g2.setColor(java.awt.Color.green)
        g2.fill(shape)
        g2.setColor(java.awt.Color.black)
        g2.setStroke(java.awt.BasicStroke(3))
        g2.draw(shape)
        g2.dispose()
      }
    })
    Pane.init(p)
    
    val buttonPlaf = new javax.swing.plaf.basic.BasicButtonUI() {
      override def installDefaults(b: javax.swing.AbstractButton) = {
        super.installDefaults(b)
        b.setBorder(plaf.CssBorder(summon[Scenegraph]))
      }
      override def paint(g: java.awt.Graphics, comp: javax.swing.JComponent): Unit = {
        plaf.RegionPainter.paintRegion(style.CssProperties.Background.forInstance(comp)(), g.upgrade.withAliasing, 0, 0, comp.getWidth, comp.getHeight)
        super.paint(g, comp)
      }
    }


    Frame(
      title = "Guarná test",
      bounds = Bounds(600, 300, 300, 300),
      visible = true,
      root = BorderPane(
        center = p,
        bottom = Hbox(nodes = Seq(
            Button(
              UI = buttonPlaf,
              text = "button1",
            ).tap { b => 
              style.CssProperties.Border.forInstance(b) := style.Border(
                images = IArray(style.BorderImage(
                  javax.imageio.ImageIO.read(File("../border.png").toJava).nn,
                  style.BorderWidths​.all(27),
                  Insets.all(27),
                  style.BorderWidths​.all(27),
                  false,
                  style.BorderRepeat.Round,
                  style.BorderRepeat.Stretch
                ))
              )  
            },
            Button(
              UI = buttonPlaf,
              text = "button2",
            ),
            Button(
              UI = buttonPlaf,
              text = "button3",
            ).tap { b =>
              style.CssProperties.Background.forInstance(b) := style.Background(
                images = IArray(style.BackgroundImage(
                  javax.imageio.ImageIO.read(File("../guarana.png").toJava).nn,
                  style.BackgroundRepeat.Spaces,
                  style.BackgroundRepeat.NoRepeat,
                  style.BackgroundPosition(style.HorizontalSide.Left, 0, style.VerticalSide.Top, 0, true, true),
                  style.BackgroundSize.Contain
                ))
              )
            },
          )
        ),
        border = javax.swing.BorderFactory.createEmptyBorder(0.5.em.toInt, 0.5.em.toInt, 0.5.em.toInt, 0.5.em.toInt)
      )
    )

  }
}