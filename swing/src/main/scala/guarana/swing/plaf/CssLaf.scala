package guarana.swing
package plaf

import language.implicitConversions
import java.awt.Font
import javax.swing.{UIManager, UIDefaults}
import javax.swing.plaf.FontUIResource
import javax.swing.plaf.metal.MetalLookAndFeel
import javax.swing.plaf.basic.BasicLookAndFeel
import scala.jdk.CollectionConverters._
import scala.util.chaining._

class CssLaf(val scenegraph: Scenegraph) extends MetalLookAndFeel {

  override def getDescription = "Look And Feel integrated to the scenegraph that uses the Var system to style swing components"
  override def getID = "GuaranáCSS"
  override def getName = "Guaraná CSS LAF"
  override def isNativeLookAndFeel = false
  override def isSupportedLookAndFeel = true

  override def getDefaults: UIDefaults = {
    val defaults = super.getDefaults().nn

    CssLaf.fontDeterminedByOs foreach { font =>
      val smallerFont = FontUIResource(font.deriveFont(font.getSize2D * 0.8f))
      defaults.keySet.nn.asScala foreach {
        case k if k.toString endsWith ".acceleratorFont" => defaults.put(k, smallerFont)
        case k if k.toString.pipe(s => s.endsWith(".font") || s.endsWith("Font")) => defaults.put(k, font)
        case _ =>
      }
    }
    defaults.put(CssLaf, this)

    defaults
  }

  override protected def initClassDefaults(defaults: UIDefaults | UncheckedNull) = {
    super.initClassDefaults(defaults)
    // defaults.put("ButtonUI", classOf[CssButtonUi].getCanonicalName)
  }

}

object CssLaf {
  def install(): Scenegraph ?=> Unit = {
    UIManager.setLookAndFeel(CssLaf(summon))
  }


  private enum DesktopEnvironment {
    case Gtk, Kde, Macos, Windows
  }
  private val desktopEnvironment: DesktopEnvironment = 
    if (scala.util.Properties.isLinux) scala.util.Properties.envOrNone("KDE_FULL_SESSION").map(_ => DesktopEnvironment.Kde).getOrElse(DesktopEnvironment.Gtk)
    else if (scala.util.Properties.isWin) DesktopEnvironment.Windows
    else DesktopEnvironment.Macos

  lazy val fontDeterminedByOs: Option[FontUIResource] = {
    val tk = java.awt.Toolkit.getDefaultToolkit().nn
      desktopEnvironment match {
      case DesktopEnvironment.Windows => tk.getDesktopProperty("win.messagebox.font").toOption.map(f => FontUIResource(f.asInstanceOf[Font]))
      case DesktopEnvironment.Macos => Some(FontUIResource("Luida Grande", Font.PLAIN, 13))
      case DesktopEnvironment.Kde => Some(FontUIResource(LinuxFontPolicy.getKdeFont()))
      case DesktopEnvironment.Gtk => Some(FontUIResource(LinuxFontPolicy.getGnomeFont(tk)))
    }
  }
}