package guarana
package swing
package theme

import scala.util.chaining._
import Binding.dyn

def println(a: Any) = System.out.unn.println(a)

object TestAqua {
  def main(args: Array[String]): Unit = {
    scala.sys.props("sun.java2d.trace") = "count"
    scala.sys.props("sun.java2d.opengl") = "True"
    scala.sys.props("sun.java2d.opengl.fbobjec") = "true"
    scala.sys.props("sun.java2d.uiScale.enabled") = "true"
    scala.sys.props("sun.java2d.uiScale") = "2"
    val scenegraph = Scenegraph()
    scenegraph.update {
      scenegraph.stylist = AquaStyle()
      plaf.CssLaf.install()
      // com.formdev.flatlaf.FlatLightLaf.install()
      val frame = Frame.mainWindow.getOrElse {
        Frame(
          title = "Test Aqua Theme",
          visible = true,
          defaultCloseOperation = 3,
        ).tap(_.size(600, 600))
      }
      frame.contentPane().removeAll()

      frame.root := BorderPane(
        border = Borders.empty(1.em.toInt),
        vgap = 1.em,
        hgap = 1.em,
        center = BorderPane(
          border = Borders.titled("<html><u>Notes</u>").tap(_.setTitleColor(Color.Gray)),
          vgap = 0.5.em,
          top = Hbox(
            nodes = Seq(
              Button(text = "Ａ"),
              Button(text = "🗛"),
              Button(text = "𐌆", font = Font("dialog", size = 1.em, style = java.awt.Font.ITALIC)),
              Button(text = "✎", icon = ColorIcon()),
              Box.horizontalStrut(1.em),
              ComboBox(
                items = "this is a combobox" +: Seq.tabulate(50)(i => s"item #$i"),
                editable = false,
                background = Color.Transparent
              )
            ),
          ),
          center = ScrollPane(
            opaque = false,
            content = TextArea(
              lineWrap = true,
              margin = Insets.all(20).toAwt
            )
          )
        ),
        bottom = Hbox(
          nodes = Seq(
            // left
            Button(text = "Press me", focusPainted = false),
            Box.horizontalStrut(1.em),
            Label(text = "left status things"),
            Box.horizontalStrut(1.em),
            Separator(horizontal = false),
            // middle
            Box.horizontalGlue(),
            // right
            Separator(horizontal = false),
            Box.horizontalStrut(1.em),
            Button(text = "↺", focusPainted = false),
          )
        )
      )
      frame.unwrap.validate()
    }
  }

  class ColorIcon extends javax.swing.Icon {
    val getIconWidth = 12
    val getIconHeight = 12
    def paintIcon(c: java.awt.Component, g: java.awt.Graphics, x: Int, y: Int) =
      g.setColor(Color.Orange)
      g.fillRect(x, y, getIconWidth, getIconHeight)
  }

  def close(): Unit = {}
}

@main def TestAquaReload = {
  val out = new java.io.PrintStream("target/log.txt")
  System.setErr(out)
  System.setOut(out)
  DevAppReloader.launch(Array(java.nio.file.Paths.get("target/scala-3.6.3/classes").nn, java.nio.file.Paths.get("target/scala-3.6.3/test-classes").nn),
    "guarana.swing.theme.*".r, "guarana.swing.theme.TestAqua", Array())
}
