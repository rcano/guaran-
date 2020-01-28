package guarana.swing.plaf

import java.awt._
import javax.swing.{JComponent, UIManager}
import javax.swing.plaf.synth._

object CssSynthLaf extends SynthStyleFactory {

  private val synthLaf = new SynthLookAndFeel()
  private val defaultStyle = new sun.swing.plaf.synth.DefaultSynthStyle()

  private val buttonStyle = new SynthStyle {
    protected def getColorForState(ctx: SynthContext | UncheckedNull, colorType: ColorType | UncheckedNull): Color | UncheckedNull = {
      println(s"color for $ctx - $colorType")
      colorType match {
        case ColorType.BACKGROUND => Color.WHITE
        case _ => Color.BLACK
      }
    }
    protected def getFontForState(ctx: SynthContext | UncheckedNull): Font | UncheckedNull = {
      println(s"font for $ctx")
      Font.getFont(Font.DIALOG)
    }

    override def getPainter(ctx: SynthContext | UncheckedNull) = new SynthPainter {
      override def paintButtonBackground(ctx: SynthContext | UncheckedNull, g: Graphics | UncheckedNull, x: Int, y: Int, w: Int, h: Int): Unit = {
        val g2 = g.asInstanceOf[Graphics2D]
        g2.setPaint(new LinearGradientPaint(x, x, w, h, Array(0.0f, 0.5f, 1.0f), Array(Color.WHITE, Color.BLUE, Color.RED)))
        g2.fillRect(x, y, w, h)
      }
    }
  }

  def getStyle(c: JComponent | UncheckedNull, id: Region | UncheckedNull): SynthStyle | UncheckedNull = {
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