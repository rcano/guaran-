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

  private val fontKeys = collection.mutable.Set.empty[Object]
  private val smallFontKeys = collection.mutable.Set.empty[Object]

  override def getDescription = "Look And Feel integrated to the scenegraph that uses the Var system to style swing components"
  override def getID = "GuaranáCSS"
  override def getName = "Guaraná CSS LAF"
  override def isNativeLookAndFeel = false
  override def isSupportedLookAndFeel = true

  override def getDefaults: UIDefaults = {
    val defaults = super.getDefaults().nn
    defaults.put(CssLaf.UiDefaultsCssLafKey, this)

    defaults.keySet.nn.asScala.foreach {
      case k if k.toString endsWith ".acceleratorFont" => smallFontKeys += k
      case k if k.toString.pipe(s => s.endsWith(".font") || s.endsWith("Font")) => fontKeys += k
      case _ =>
    }

    var rootFont = CssLaf.fontDeterminedByOs.getOrElse(defaults.getFont("TextField.font").asInstanceOf[FontUIResource])
    var smallerFont = FontUIResource(rootFont.deriveFont(rootFont.getSize2D * 0.8f))

    fontKeys foreach (defaults.put(_, rootFont))
    smallFontKeys foreach (defaults.put(_, smallerFont))

    scenegraph.update {
      scenegraph.varUpdates := EventIterator.foreach {
        case scenegraph.emSize(oldv, newv) =>
          rootFont = FontUIResource(rootFont.deriveFont(newv.toFloat))
          smallerFont = FontUIResource(rootFont.deriveFont(rootFont.getSize2D * 0.8f))

          fontKeys foreach (defaults.put(_, rootFont))
          smallFontKeys foreach (defaults.put(_, smallerFont))
        case _ =>
      }
    }

    defaults.keySet.nn.asScala.filter(_.toString endsWith "border") foreach (k => defaults.put(k, CssBorder(scenegraph)))

    defaults
  }

  override protected def initClassDefaults(defaults: UIDefaults) = {
    super.initClassDefaults(defaults)
    defaults.put("RootPaneUI", classOf[CssRootPaneUi].getCanonicalName)
    defaults.put("PanelUI", classOf[CssPanelUi].getCanonicalName)
    defaults.put("ButtonUI", classOf[CssButtonUi].getCanonicalName)
    defaults.put("ToggleButtonUI", classOf[CssButtonUi].getCanonicalName)
    defaults.put("ScrollBarUI", classOf[CssScrollBarUi].getCanonicalName)
    defaults.put("ProgressBarUI", classOf[CssProgressBarUi].getCanonicalName)
    defaults.put("TabbedPaneUI", classOf[CssTabbedPaneUi].getCanonicalName)
  }

  override def uninitialize(): Unit = {
    val defaults = UIManager.getDefaults.nn
    defaults.remove(CssLaf.UiDefaultsCssLafKey)
    defaults.keySet.nn.asScala.filter(_.toString endsWith "border") foreach (k => defaults.remove(k))
  }
}

object CssLaf {

  val UiDefaultsCssLafKey = "CssLafInstance"
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

trait CssUi {
  def scenegraph = UIManager.getDefaults.get(CssLaf.UiDefaultsCssLafKey).toOption.fold(
    throw new IllegalStateException(s"Running CssLaf without a Scenegraph?")
  )(_.asInstanceOf[CssLaf].scenegraph)

  inline protected def withinRegion(c: javax.swing.JComponent)(inline f: (Int, Int, Int, Int) => Unit): Unit = {
    val insets = c.getInsets.nn
    val x = insets.left
    val y = insets.top
    val width = c.getWidth - insets.right - insets.left
    val height = c.getHeight - insets.top - insets.bottom
    f(x, y, width, height)
  }
}