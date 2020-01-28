package guarana.swing
package util

import javax.swing.{JPanel, UIManager}

// this doesn't work, because we can't create graphics before a window has been shown, so we can't calculate the size
private[swing] def getSystemFontSize() = {
  var systemLaf = UIManager.getSystemLookAndFeelClassName.nn
  if (systemLaf == UIManager.getCrossPlatformLookAndFeelClassName) //kde case, specify gtk
    systemLaf = UIManager.getInstalledLookAndFeels.nn.collectFirst { case f: UIManager.LookAndFeelInfo if f.getName.contains("GTK") => f.getClassName.nn }.get

  val current = UIManager.getLookAndFeel
  UIManager.setLookAndFeel(systemLaf)
  val c = new JPanel()
  val fontMetrics = c.getGraphics.nn.getFontMetrics.nn
  val h = fontMetrics.getHeight
  val w = fontMetrics.stringWidth("M")
  UIManager.setLookAndFeel(current)
  (w.toFloat, h.toFloat)
}