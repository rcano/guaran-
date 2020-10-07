package guarana.swing

import language.implicitConversions
import Binding.dyn
import scala.util.chaining._
import util.UnsetParam

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
      bounds = Bounds(1300, 300, 300, 300),
      root = root,
      title = "Guarná test",
      visible = true,
    )
  }
}

@main def FormTestReload: Unit = {
  val out = new java.io.PrintStream("target/log.txt")
  System.setErr(out)
  System.setOut(out)
  DevAppReloader.launch(Array(java.nio.file.Paths.get("target/scala-0.28/classes").nn, java.nio.file.Paths.get("target/scala-0.28/test-classes").nn), "guarana.swing.*".r, "guarana.swing.FormTest", Array())
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

  scenegraph.stylist = new Stylist {
    import java.awt.BasicStroke
    val corners = style.CornerRadii.all(4)
    val rootBackground = style.Background(fills = IArray(style.BackgroundFill(Color.WhiteSmoke, corners, Insets.all(0))))
    val bck = style.Background(fills = IArray(style.BackgroundFill(Color.LightSalmon, style.CornerRadii.all(0), Insets.all(0))))
    val hoverBck = style.Background(fills = IArray(style.BackgroundFill(Color.LightPink, style.CornerRadii.all(0), Insets.all(0))))
    val pressedBck = style.Background(fills = IArray(style.BackgroundFill(Color.DarkSalmon, style.CornerRadii.all(0), Insets.all(0))))
    def apply[T](info: Stylist.ScenegraphInfo)(prop: Keyed[ObsVal[T]]) = {
      lazy val emSize = scenegraph.stateReader(scenegraph.emSize)
      prop match {
        case Keyed(style.CssProperties.Border, jb: javax.swing.AbstractButton) =>
          val h = jb.getBounds.getHeight
          Some(
            style.Border(strokes = IArray(
              // border,
              style.BorderStroke.simple(
                java.awt.LinearGradientPaint(0, 0, 0, h.toFloat.max(1), Array[Float](0, 1), Array[Color | UncheckedNull](Color.LavenderBlush, Color.LavenderBlush.darker.nn)),
                BasicStroke((emSize / 10).toInt), corners, Insets.all(emSize / 10))
            ))
          ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.Background, b: javax.swing.AbstractButton) => Some(
          if (b.getModel.isPressed || b.isSelected) pressedBck 
          else if (b.getModel.isRollover) hoverBck
          else bck
        ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.Background, rp: javax.swing.JRootPane) => Some(
          rootBackground
        ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.Background, sb: javax.swing.JScrollBar) => Some(
          style.Background(fills = IArray(style.BackgroundFill(Color.LavenderBlush, corners, Insets.all(0))))
        ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.ScrollbarThumbBackground, jb: javax.swing.JScrollBar) => Some(
          style.Background(fills = IArray(style.BackgroundFill(Color.LightSalmon, style.CornerRadii.simple(emSize / 2, emSize / 4, emSize / 2,  emSize / 4), Insets.all(0))))
        ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.Background, sb: javax.swing.JProgressBar) => Some(
          style.Background(fills = IArray(style.BackgroundFill(Color.LavenderBlush, corners, Insets.all(0))))
        ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.ProgressBarBarBackground, sb: javax.swing.JProgressBar) => Some(
          style.Background(fills = IArray(style.BackgroundFill(Color.LightSalmon, style.CornerRadii.all(0), Insets.all(0))))
        ).asInstanceOf[Option[T]]
        case Keyed(style.CssProperties.ProgressBarBarBorder, sb: javax.swing.JProgressBar) => Some(
          style.Border(strokes = IArray(style.BorderStroke.simple(Color.DarkSalmon.darker.nn, BasicStroke(3), corners, Insets(0, 0, 3, 0))))
        ).asInstanceOf[Option[T]]
        case _ => 
          None
      }
    }
    def invalidateCache(node: Any) = ()
  }

  // com.formdev.flatlaf.FlatLightLaf.install()
  // javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel")
  // javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
  // plaf.CssSynthLaf.install()

  scenegraph.update {
    plaf.CssLaf.install()

    val userTf = TextField(columns = 5)
    val passwordTf = PasswordField(columns = 5)
    val login = Button(
      text = "Login",
      enabled = dyn { userTf.currentText().nonEmpty && passwordTf.currentText().nonEmpty },
      defaultCapable = true
    )

    val emModifier = Slider(
      min = 5,
      max = 30,
      majorTickSpacing = 5,
      value = scenegraph.emSize().toInt,
      paintTicks = true,
      paintLabels = true,
      orientation = javax.swing.SwingConstants.VERTICAL
    )
      
    scenegraph.emSize := dyn { emModifier.value() }

    val combobox = ComboBox[String](items = Seq("A", "B", "C"))
    val spinner = Spinner[Int](model = javax.swing.SpinnerNumberModel(10, 0, 100, 1))

    val tab1Content = SplitPane(
      componentA = BorderPane(
        top = ProgressBar(min = dyn(emModifier.min()), max = dyn(emModifier.max()), value = dyn(emModifier.value())),
        center = GridPane(
          rows = Seq(
            Seq(Label(text = "User:"), userTf),
            Seq(Label(text = "Password:"), passwordTf),
          ),
          hgap = dyn(1.em),
          vgap = dyn(1.5.em),
          autoCreateContainerGaps = true,
          border = javax.swing.BorderFactory.createTitledBorder("Center thing")
        ),
        right = Vbox(nodes = Seq(Label(text = "Em Size"), emModifier)),
        left = Vbox(nodes = Seq(
          ToggleButton(text = "toggle"),
          CheckBox(text = "check"),
          RadioButton(text = "radio"),
          combobox,
          Label(text = dyn { s"selected ${combobox.selectedItem()}" }),
          Separator(),
          spinner,
          Label(text = dyn { s"selected ${spinner.value()}" })
        )),
        bottom = Hbox(nodes = Seq(Box.horizontalGlue(), login)),
        border = dyn { javax.swing.BorderFactory.createEmptyBorder(1.em.toInt, 1.em.toInt, 1.em.toInt, 1.em.toInt) }
      ),
      componentB = ScrollPane(content = ListView(model = Seq.tabulate(20)(i => s"elem $i"): javax.swing.ListModel[String])),
      horizontal = false,
      background = Color.WhiteSmoke
    )


    val tabs = ObsBuffer[Tab]()
    tabs += Tab(title = "demo1", content = tab1Content)
    tabs += Tab(title = "tab2", content = Label(text = "second tab"), tip = "try me", tabNode = Button(text = "press me")).tap { t =>
      val b = Button(text = "update tab title")
      t.content := b
      b.actionEvents := EventIterator.foreach { _ =>
        t.tabNode := Binding.Null
        t.title := s"${t.title()}!"
        if (t.title().toOption.map(_.length).getOrElse(0) > 20) {
          tabs -= t
        }
      }
    }
    case class Movie(title: String, year: String, rating: Int, watched: Boolean)
    val td = table.immutable.TableData[Movie](IndexedSeq(
      Movie("Castle in the Sky", "1986", 8, true),
      Movie("Grave of the Fireflies", "1988", 10, true),
      Movie("My Neighbor Totoro", "1988", 9, true),
      Movie("Pom Poko", "1994", 6, false),
    ))
    tabs += Tab(title = "A table", content = ScrollPane(content = TableView(
      model = td.tableModel,
      columnModel = td.columnModel
    )))

    Frame(
      title = "Guaraná test",
      // bounds = Rect(1300, 300, 300, 300),
      locationByPlatform = true,
      visible = true,
      defaultCloseOperation = 3, //exit on close
      root = TabbedPane(tabs = tabs)
      // root = tab1Content
    ).pack()
  }
}
