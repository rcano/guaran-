package guarana.swing
package plaf

import java.awt._
import javax.swing.{JComponent, UIManager}
import javax.swing.plaf.synth._

class CssSynthLaf(sceengraph: Scenegraph) extends SynthStyleFactory {

  private val synthLaf = new SynthLookAndFeel()
  private val defaultStyle = new sun.swing.plaf.synth.DefaultSynthStyle()

  private val buttonStyle = new SynthStyle {
    protected def getColorForState(ctx: SynthContext, colorType: ColorType): Color = {
      println(s"color for $ctx - $colorType")
      colorType match {
        case ColorType.BACKGROUND => Color.WHITE
        case _ => Color.BLACK
      }
    }
    protected def getFontForState(ctx: SynthContext): Font = {
      println(s"font for $ctx")
      Font.getFont(Font.DIALOG)
    }

    override def getPainter(ctx: SynthContext) = new SynthPainter {
      override def paintButtonBackground(ctx: SynthContext, g: Graphics, x: Int, y: Int, w: Int, h: Int): Unit = {
        val g2 = g.asInstanceOf[Graphics2D]
        g2.setPaint(new LinearGradientPaint(x, x, w, h, Array(0.0f, 0.5f, 1.0f), Array(Color.WHITE, Color.BLUE, Color.RED)))
        g2.fillRect(x, y, w, h)
      }
    }
  }

  def getStyle(c: JComponent, id: Region): SynthStyle = {
    id match {
      case Region.BUTTON => buttonStyle
      case _ => defaultStyle
    }
  }

  def install(): Unit = {
    UIManager.setLookAndFeel(synthLaf)
    SynthLookAndFeel.setStyleFactory(this)
  }
}