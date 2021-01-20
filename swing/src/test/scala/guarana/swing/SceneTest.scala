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
  DevAppReloader.launch(Array(java.nio.file.Paths.get("target/scala-3.0.0-M3/classes").nn, java.nio.file.Paths.get("target/scala-3.0.0-M3/test-classes").nn), "guarana.swing.*".r, "guarana.swing.FormTest", Array())
}

@main def FormTest: Unit = {
  // val orig = System.getProperties().nn
  // new java.util.Properties() {
  //   override def getProperty(prop: String) = 
  //     println(s"Reading $prop")
  //     orig.getProperty(prop).tap(r => println(s"  = $r"))
  //   override def getProperty(prop: String, default: String) = 
  //     println(s"Reading $prop def $default")
  //     orig.getProperty(prop, default).tap(r => println(s"  = $r"))
  //   override def get(a: Any) = 
  //     println(s"Reading $a")
  //     orig.get(a).tap(r => println(s"  = $r"))
  // } pipe System.setProperties

  val scenegraph = Scenegraph()

  scenegraph.stylist = TestStyle()

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
      orientation = javax.swing.SwingConstants.HORIZONTAL,
      componentOrientation = java.awt.ComponentOrientation.RIGHT_TO_LEFT,
      opaque = true,
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
      model = td.defaultTableModel,
      columnModel = td.columnModel
    )))

    for (i <- 0 until 10) {
      tabs += Tab(title = s"$i", content = Pane())
    }

    Frame(
      title = "Guaraná test",
      // bounds = Rect(1300, 300, 300, 300),
      locationByPlatform = true,
      visible = true,
      defaultCloseOperation = 3, //exit on close
      root = TabbedPane(
        tabs = tabs,
        tabLayoutPolicy = javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT,
      )
      // root = tab1Content
    ).pack()
  }
}

@main def VirtualImageTestReload(): Unit = {
  System.setProperty("sun.java2d.trace", "count")
  System.setProperty("sun.java2d.opengl", "True")
  val out = new java.io.PrintStream("target/log.txt")
  System.setErr(out)
  System.setOut(out)
  DevAppReloader.launch(Array(java.nio.file.Paths.get("target/scala-0.28/classes").nn, java.nio.file.Paths.get("target/scala-0.28/test-classes").nn), "guarana.swing.*".r, "guarana.swing.VirtualImageTest", Array())
}
@main def VirtualImageTest(): Unit = {
  System.setProperty("sun.java2d.trace", "count")
  System.setProperty("sun.java2d.opengl", "True")
  val scenegraph = Scenegraph()

  lazy val environment = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().nn
  lazy val defaultScreenDevice = environment.getDefaultScreenDevice().nn
  lazy val defaultConfiguration = defaultScreenDevice.getDefaultConfiguration().nn

  import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
  import scala.concurrent.duration._
  val metricsRegistry = MetricRegistry()
  val frameTimer = metricsRegistry.timer("frameTime").nn
  val flakesCounter = metricsRegistry.counter("flakes").nn
  val consoleReporter = ConsoleReporter.forRegistry(metricsRegistry)
    .convertRatesTo(SECONDS)
    .convertDurationsTo(MILLISECONDS)
    .build().nn


  def VolatileImage(width: Int, height: Int, transparent: Boolean = true) =
    defaultConfiguration.createCompatibleVolatileImage(width, height, if transparent then java.awt.Transparency.TRANSLUCENT else java.awt.Transparency.OPAQUE).nn

  var _backbuffer: java.awt.image.VolatileImage | Null = null
  def getBackbuffer() = {
    val recreate = _backbuffer match {
      case null => true
      case b: java.awt.image.VolatileImage if b.contentsLost() =>
        println("VolatileImage lost")
        b.flush()
        true
      case _ => false
    }
    if recreate then _backbuffer = VolatileImage(600, 480, false)
    _backbuffer.nn
  }

  import java.awt.Image, java.awt.image.VolatileImage
  val rawFlake = {
    val res = javax.imageio.ImageIO.read(better.files.Resource.getUrl("dandellion.png")).nn
    val flake = defaultConfiguration.createCompatibleImage(res.getWidth(null), res.getHeight(null), java.awt.Transparency.TRANSLUCENT).nn
    flake.createGraphics().drawImage(res, 0, 0, null)
    flake
  }
  // val possibleFlakes = Array(
  //   rawFlake,
  //   rawFlake.getScaledInstance(8, 8, Image.SCALE_SMOOTH).nn,
  //   rawFlake.getScaledInstance(6, 6, Image.SCALE_SMOOTH).nn,
  //   rawFlake.getScaledInstance(4, 4, Image.SCALE_SMOOTH).nn,
  //   rawFlake.getScaledInstance(3, 3, Image.SCALE_SMOOTH).nn,
  // )
  val possibleFlakes = Array(
    rawFlake.getScaledInstance(25, 25, Image.SCALE_SMOOTH).nn,
    rawFlake.getScaledInstance(30, 30, Image.SCALE_SMOOTH).nn,
    rawFlake.getScaledInstance(35, 35, Image.SCALE_SMOOTH).nn,
  )
  val acceleratedFlakes = new Array[VolatileImage | Null](possibleFlakes.size)

  scenegraph.update {

    val canvas = java.awt.Canvas()
    val canvasNode = Node.wrap(canvas).tap(Node.init)
    // canvas.setSize(java.awt.Dimension(600, 480))
    val contentPane = AbsolutePositioningPane(
      prefSize = (600.0, 480.0)
    )
    var mousePressed: MouseEvent | Null = null
    contentPane.mouseEvents := EventIterator.foreach {
      case e@MousePressed(_) => mousePressed = e
      case e@MouseDragged(_) if mousePressed != null => mousePressed = e
      case e@MouseReleased(_) => mousePressed = null
      case _ =>
    }
    canvasNode.mouseEvents := EventIterator.foreach {
      case e@MousePressed(_) => mousePressed = e
      case e@MouseDragged(_) if mousePressed != null => mousePressed = e
      case e@MouseReleased(_) => mousePressed = null
      case _ =>
    }

    val frame = Frame(
      title = "VirtualImage test.",
      root = BorderPane(
        center = canvasNode,
      ),
      visible = true,
    ).tap(_.pack())
    frame.size(600, 480)
    canvas.createBufferStrategy(2)

    val emitters = {
      val num = 150
      val w = 1000 / num
      Array.tabulate(num)(i => i * w + w / 2)
    }

    new Thread() {
      override def run(): Unit = {
        val timePerFrameNanos = (1000 / 60) * 100000
        val awtTk = java.awt.Toolkit.getDefaultToolkit.nn
        try {
          consoleReporter.start(1, SECONDS)
          while (frame.unwrap.isVisible) {
            val frameStart = System.nanoTime()
            val bufferStrategy = canvas.getBufferStrategy().nn
            // val backbuffer = getBackbuffer()
            // val bbg = backbuffer.createGraphics().nn
            val bbg = bufferStrategy.getDrawGraphics().nn
            bbg.setColor(Color.Black)
            bbg.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
  
            mousePressed.? { mp =>
              for (_ <- 0 until 20) {
                val p = Particles.Particle()
                p.x = (mp.getX() + scala.util.Random.between(-5, 5)).toShort
                p.y = (mp.getY() + scala.util.Random.between(-5, 5)).toShort
                p.img = (math.random() * possibleFlakes.length).toShort
                flakesCounter.inc()
              }
            }

            {
              var i = -1
              while { i += 1; i < emitters.length } do {
                val p = Particles.Particle()
                p.x = (emitters(i) + scala.util.Random.between(-5, 5)).toShort
                p.y = (400 + scala.util.Random.between(-5, 5)).toShort
                p.img = (math.random() * possibleFlakes.length).toShort
                flakesCounter.inc()
              }
            }

            //update and render each particle
            var i = Particles.fireParticles.tail - 1
            while { i = (i + 1) % Particles.fireParticles.len; i != Particles.fireParticles.head } do {
              val p = Particles.Particle(i)
              p.framesAlive = (p.framesAlive + 1).toShort
  
              if (p.framesAlive < 60) {
                p.x = (p.x + scala.util.Random.between(-10, 10)).toShort
                p.y = (p.y + scala.util.Random.between(-15, 0)).toShort
  
                // var vi = acceleratedFlakes(p.img) match {
                //   case null => 
                //     val im = possibleFlakes(p.img)
                //     val vi = VolatileImage(im.getWidth(null), im.getHeight(null))
                //     val g2 = vi.createGraphics().nn
                //     g2.drawImage(im, 0, 0, null)
                //     g2.dispose()
                //     acceleratedFlakes(p.img) = vi
                //     vi
                //   case i: VolatileImage => i
                // }
                // if (vi.contentsLost) {
                //   val im = possibleFlakes(p.img)
                //   val g2 = vi.createGraphics().nn
                //   g2.drawImage(im, 0, 0, null)
                // }
                val vi = possibleFlakes(p.img)

                try bbg.drawImage(vi, p.x, p.y, null)
                catch case i: IndexOutOfBoundsException =>
                  println(s"${p.x}, ${p.y}, ${p.img}, ${p.framesAlive}")
                  throw i 
              } else {
                p.dispose()
                flakesCounter.dec()
              }
            }
            bbg.dispose()
            bufferStrategy.show()
            // contentPane.unwrap.getGraphics.? { g2 => 
            //   g2.drawImage(backbuffer, 0, 0, null) 
            //   g2.dispose()
            //   // awtTk.sync()
            // }
            val frameTotal = System.nanoTime() - frameStart
            frameTimer.update(frameTotal, NANOSECONDS)
            java.util.concurrent.locks.LockSupport.parkNanos(timePerFrameNanos - frameTotal)
          }
        } finally {
          consoleReporter.close()
          println("disposing rendering thread.")
        }
      }
    }.start()
  }

}

object Particles {
  val fireParticles = impl.RingBuffer[Long](32768, -1)
  object Particle {
    opaque type Instance = Int
    def apply(): Instance = {
      val res = fireParticles.head
      fireParticles.put(0)
      res
    }
    def apply(i: Int): Instance = i

    extension (p: Instance) {
      def x = (fireParticles.ring(p) & 0xFFFF).toShort
      def x_=(x: Short) = fireParticles.ring(p) = (fireParticles.ring(p) & 0xFFFFFFFFFFFF0000l) + x
      def y = ((fireParticles.ring(p) >> 16) & 0xFFFF).toShort
      def y_=(y: Short) = fireParticles.ring(p) = (fireParticles.ring(p) & 0xFFFFFFFF0000FFFFl) + (java.lang.Short.toUnsignedLong(y) << 16)
      def img = ((fireParticles.ring(p) >> 32) & 0xFF).toShort
      def img_=(i: Short) = fireParticles.ring(p) = (fireParticles.ring(p) & 0xFFFF0000FFFFFFFFl) + (java.lang.Short.toUnsignedLong(i) << 32)
      def framesAlive = ((fireParticles.ring(p) >>> 48) & 0xFF).toShort
      def framesAlive_=(f: Short) = fireParticles.ring(p) = (fireParticles.ring(p) & 0x0000FFFFFFFFFFFFl) + (java.lang.Short.toUnsignedLong(f) << 48)
      def raw = fireParticles.ring(p)
      def dispose(): Unit = fireParticles.take() 
    }
  }
}