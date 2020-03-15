package guarana.swing
package plaf

import better.files._
import java.awt.{Font, GraphicsEnvironment, Toolkit}
import javax.swing.text.StyleContext
import scala.util.control.NonFatal

/** This code is a replica of https://github.com/JFormDesigner/FlatLaf/blob/master/flatlaf-core/src/main/java/com/formdev/flatlaf/LinuxFontPolicy.java
  */
object LinuxFontPolicy {

  def getGnomeFont(tk: Toolkit): Font = {
    val gtkFont = tk.getDesktopProperty("gnome.Gtk/FontName").toOption.collect { case s: String => s }.getOrElse("sans 10")
    var family: String = ""
    var style = Font.PLAIN
    var size = 10

    gtkFont.split(" ").nnn foreach {
      case s if "italic".equalsIgnoreCase(s) => style |= Font.ITALIC
      case s if "bold".equalsIgnoreCase(s) => style |= Font.BOLD
      case s if s(0).isDigit => s.toIntOption foreach (s => size = s)
      case f =>
        if (family.isEmpty) family = f
        else family = family + ' ' + f
    }

    //Gnome font scale
		// see class com.sun.java.swing.plaf.gtk.PangoFonts background information
    val scaling = Toolkit.getDefaultToolkit().getDesktopProperty( "gnome.Xft/DPI" ).toOption match {
      case Some(dpi: Int) =>
        (if (dpi == -1) 96
        else if (dpi < 50) 50
        else dpi) / 72.0
      case _ => 
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
          .getDefaultConfiguration().getNormalizingTransform().getScaleY()
    }
    val derivedSize = (size * scaling + 0.5)
    size = (size + 0.5).toInt
    if (size < 1) size = 1

    // map GTK/fontconfig names to equivalent JDK logical font name
    family.toLowerCase match {
      case "sans" => family = "sansserif"
      case "sans-serif" => family ="sansserif"
      case _ => 
    }

    StyleContext().getFont(family, style, size).deriveFont(derivedSize.toFloat).nn
  }

  def getKdeFont(): Font = {
    Iterator(
      ".config", // KDE 5
      ".kde4/share/config", //KDE 4
      ".kde/share/config" //KDE 3
    ).map(File.home / _).filter(_.exists).nextOption() match {
      case None => StyleContext().getFont("sansserif", Font.PLAIN, 10).nn
      case Some(configDir) =>
        val kdeGlobals = readConfFile(configDir / "kdeglobals")
        val kcmfonts = readConfFile(configDir / "kcmfonts")

        println("kcmfonts:")
        kcmfonts foreach { (k, v) =>
          println("[" + k + "]")
          v foreach println
        }

        var family: String = "sansserif"
        var style = Font.PLAIN
        var size = 10

        kdeGlobals.get("General").flatMap(_ get "font") foreach { font =>
          try {
            val parts = font.split(",", 2).nnn  
            family = parts(0)
            size = parts(1).toInt
            if ("75" == parts(4)) style |= Font.BOLD
            if ("1" == parts(5)) style |= Font.ITALIC
          } catch {
            case NonFatal(e) =>
              val logger = System.getLogger("guarana").nn
              logger.log(System.Logger.Level.ERROR, s"failed to parse KDE font $font", e)
          }
        }

        val scaling = (for {
          section <- kcmfonts.get("General")
          dpiStr <- section.get("forceFontDPI")
          dpi <- dpiStr.toIntOption
        } yield dpi match {
          case i if i <= 0 => 96
          case i if i < 50 => 50
          case i => i
        }).getOrElse(96) / 72.0

        val derivedSize = (size * scaling + 0.5)
        size = (size + 0.5).toInt
        if (size < 1) size = 1

        StyleContext().getFont(family, style, size).deriveFont(derivedSize.toFloat).nn
    }
  }

  private def readConfFile(file: File): Map[String, Map[String, String]] = {
    def rec(lines: Iterator[String], section: String,
      sectionAcc: Map[String, String],
      acc: Map[String, Map[String, String]]): Map[String, Map[String, String]] = lines.nextOption match {
      case None => if (sectionAcc.isEmpty) acc else acc.updated(section, sectionAcc)
      case Some(line) if line.isEmpty => rec(lines, section, sectionAcc, acc)
      case Some(line) if line(0) == '[' => rec(lines, line.drop(1).dropRight(1), Map.empty, acc.updated(section, sectionAcc))
      case Some(line) => 
        val Array(key, value) = line.split("=", 2).nnn
        rec(lines, section, sectionAcc.updated(key, value), acc)
    }
    if (file.isEmpty) Map.empty
    else {
      val it = file.lineIterator()
      rec(it, it.next.drop(1).dropRight(1), Map.empty, Map.empty)
    }
  }
}