package guarana.swing

import language.implicitConversions
import Binding.dyn
import scala.util.chaining._

@main def Test: Unit = {
  val scenegraph = Scenegraph()

  scenegraph.update {
    val presses = Var[Int]("presses", 0)

    val root = AbsolutePositioningPane()
    println("binding text")
    val label: Label = Label(
      toolTipText = "Report the location of the mouse",
      text = dyn {
        val (x, y) = root.mouseLocation()
        s"<html>Mouse location: <font color=green>$x, $y</font>\nPressed: ${presses()} times"
      }
    )

    label.bounds := dyn {
      val (x, y) = root.mouseLocation()
      val dim = label.prefSize().nn
      Bounds(x, y, dim._1, dim._2)
    }
      
    root.nodes := Seq(
      label,
      Button(text = "Press me!", bounds = Bounds(10, 10, 100, 50)).tap(
        _.actionEvents := EventIterator.foreach { e =>
          presses := presses() + 1
        }
      )
    )

    Frame(
      title = "Guarná test",
      bounds = Bounds(1300, 300, 300, 300),
      visible = true,
      root = root,
    )
  }
}

@main def FormTest: Unit = {
  // val orig = System.getProperties().nn
  // new java.util.Properties() {
  //   override def getProperty(prop: String | UncheckedNull) = 
  //     println(s"Reading $prop")
  //     orig.getProperty(prop).tap(r => println(s"  = $r"))
  //   override def getProperty(prop: String | UncheckedNull, default: String | UncheckedNull) = 
  //     println(s"Reading $prop def $default")
  //     orig.getProperty(prop, default).tap(r => println(s"  = $r"))
  //   override def get(a: Any) = 
  //     println(s"Reading $a")
  //     orig.get(a).tap(r => println(s"  = $r"))
  // } pipe System.setProperties

  val scenegraph = Scenegraph()

  javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel")
  // javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
  // plaf.CssSynthLaf.install()

  scenegraph.update {

    val userTf = TextField(columns = 5)
    val passwordTf = PasswordField(columns = 5)
    val login = Button(
      text = "Login",
      enabled = dyn { userTf.document().nonEmpty && passwordTf.document().nonEmpty },
      defaultCapable = true
    )

    val emModifier = Slider(min = 5, max = 30, majorTickSpacing = 5, value = 5, paintTicks = true, paintLabels = true, orientation = javax.swing.SwingConstants.VERTICAL)
    scenegraph.emSize := dyn { emModifier.value() }

    Frame(
      title = "Guaraná test",
      // bounds = Rect(1300, 300, 300, 300),
      locationByPlatform = true,
      visible = true,
      root = BorderPane(
        center = GridPane(
          rows = Seq(
            Seq(Label(text = "User:"), userTf),
            Seq(Label(text = "Password:"), passwordTf),
          ),
          hgap = 1.em,
          vgap = 1.em,
          autoCreateContainerGaps = true,
          border = javax.swing.BorderFactory.createTitledBorder("Center thing")
        ),
        right = Vbox(nodes = Seq(Label(text = "Em Size"), emModifier)),
        left = Vbox(nodes = Seq(ToggleButton(text = "toggle"), CheckBox(text = "check"), RadioButton(text = "radio"))),
        bottom = Hbox(nodes = Seq(Box.horizontalGlue(), login)),
        border = dyn { javax.swing.BorderFactory.createEmptyBorder(1.em.toInt, 1.em.toInt, 1.em.toInt, 1.em.toInt) }
      ),
    ).pack()
  }
}