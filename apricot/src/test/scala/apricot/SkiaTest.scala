package apricot

import guarana.*
import scala.language.unsafeNulls

import animation.ScriptDsl.{given, *}
import com.codahale.metrics.{ConsoleReporter, MetricRegistry, Timer}
import java.nio.IntBuffer
import java.util.*
import io.github.humbleui.skija.*
import io.github.humbleui.skija.impl.*
import io.qt.gui.{QOpenGLWindow, QSurfaceFormat}
import org.lwjgl.glfw.*, Callbacks.*, GLFW.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import scala.concurrent.duration.*

object SkiaTest {

  val tk = new AbstractToolkit {
    def isOnToolkitThread() = true
    def runOnToolkitThread(f: () => Any): Unit = f()
  }
  val textPosition = Var.autoName[Double](0)
  val circleCentre = Var.autoName[(Double, Double)]((0, 0))
  val circleRadius = Var.autoName[Double](0)

  def main(args: Array[String]): Unit = {
    GLFWErrorCallback.createPrint(System.err).unn.set()
    assert(glfwInit(), "frigging glfw")

    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    val wWidth = 800
    val wHeight = 800
    val windowHandle = glfwCreateWindow(wWidth, wHeight, "Skija LWJGL Demo", NULL, NULL)

    glfwMakeContextCurrent(windowHandle)
    glfwSwapInterval(1) // v-sync
    glfwShowWindow(windowHandle)

    // init ogl
    GL.createCapabilities()

    // Create Skia OpenGL context
    // Do once per app launch
    val skiaContext = DirectContext.makeGL()
    // Create render target, surface and retrieve canvas from it
    val fbId = GL11.glGetInteger(0x8ca6) // GL_FRAMEBUFFER_BINDING, yes, that magic constant, comes from somewhere
    val renderTarget = BackendRenderTarget.makeGL(wWidth, wHeight, /*samples*/ 0, /*stencil*/ 8, fbId, FramebufferFormat.GR_GL_RGBA8)

    val surface = Surface.makeFromBackendRenderTarget(
      skiaContext,
      renderTarget,
      SurfaceOrigin.BOTTOM_LEFT,
      SurfaceColorFormat.RGBA_8888,
      ColorSpace.getSRGB()
    )

    val canvas = surface.getCanvas()
    // should close and recreate the render target and canvas on resize but ¯\_(ツ)_/¯

    val scriptEngine = animation.ScriptEngine(tk, 10)

    scriptEngine run script {
      // FIXME: sigh, bugged again...
      // while true do
      //   interp(2.seconds)(pct => textPosition := pct * wWidth)
      //   interp(2.seconds)(pct => textPosition := (1 - pct) * wWidth)
    }
    scriptEngine run script {
      // FIXME: sigh, bugged again...
    //   while true do
    //     interp(0.5.seconds)(pct => circleRadius := pct * 120 + 50)
    //     interp(0.5.seconds)(pct => circleRadius := (1 - pct) * 120 + 50)
    }

    val face = FontMgr.getDefault().nn.matchFamilyStyle("Menlo", FontStyle.NORMAL)

    val metrics = MetricRegistry()
    val circleTimer = metrics.timer("drawCircle")
    val lineTimer = metrics.timer("drawLine")
    val present = metrics.timer("present")

    extension (t: Timer)
      inline def time[R](inline f: => R): R =
        val t0 = System.nanoTime
        val res = f
        t.update(System.nanoTime - t0, NANOSECONDS)
        res

    // ConsoleReporter.forRegistry(metrics).nn.build.nn.start(5, SECONDS)

    val semiTransparent = Paint().setAlphaf(0.98f)

    val textLine = TextLine.make("Hello skia", Font(face).setSize(45))
    // Render loop
    while (!glfwWindowShouldClose(windowHandle)) {
      processEvents(windowHandle)
      scriptEngine.update(System.nanoTime)

      val prevFrame = surface.makeImageSnapshot()
      canvas.clear(0xff000000)

      canvas.drawImage(prevFrame, 0, 0, semiTransparent)

      prevFrame.close()

      // render here
      val circlePos = tk.stateReader(circleCentre)
      canvas.drawCircle(
        circlePos._1.toFloat,
        circlePos._2.toFloat,
        tk.stateReader(circleRadius).toFloat,
        Paint().setARGB(255, 30, 160, 200)
      )
      val textPos = tk.stateReader(textPosition).toFloat
      canvas.drawTextLine(textLine, textPos, 150, Paint().setColor(0xff94cbff))

      skiaContext.flush()
      glfwSwapBuffers(windowHandle) // wait for v-sync
      glfwPollEvents()
    }
  }

  def processEvents(window: Long): Unit = {
    val x = new Array[Double](1)
    val y = new Array[Double](1)
    glfwGetCursorPos(window, x, y)
    tk.update {
      circleCentre := (x(0), y(0))
    }
  }
}

object SkiaQtTest {
  import guarana.*
  import guarana.qt.*

  val tk = Toolkit
  val textPosition = Var.autoName[Double](0)
  val circleCentre = Var.autoName[(Double, Double)]((0, 0))
  val circleRadius = Var.autoName[Double](0)

  var skiaDirectContext: DirectContext = compiletime.uninitialized
  var skiaSurface: Surface = compiletime.uninitialized
  var canvas: Canvas = compiletime.uninitialized

  def main(args: Array[String]): Unit = {
    tk.update {
      val openglWindow = new QOpenGLWindow() {
        locally {
          val fmt = QSurfaceFormat()
          fmt.setDepthBufferSize(24)
          fmt.setProfile(QSurfaceFormat.OpenGLContextProfile.CoreProfile)
          fmt.setMajorVersion(4)
          fmt.setMajorVersion(6)
          setFormat(fmt)

          setWidth(600)
          setHeight(600)
        }
        override def initializeGL(): Unit = {
          skiaDirectContext = DirectContext.makeGL()
        }
        override def resizeGL(w: Int, h: Int): Unit = {
          if canvas != null then skiaSurface.close()
          val renderTarget = BackendRenderTarget.makeGL(
            w,
            h, /*samples*/ 0, /*stencil*/ 8,
            context.unn.defaultFramebufferObject,
            FramebufferFormat.GR_GL_RGBA8
          )

          skiaSurface = Surface.makeFromBackendRenderTarget(
            skiaDirectContext,
            renderTarget,
            SurfaceOrigin.BOTTOM_LEFT,
            SurfaceColorFormat.RGBA_8888,
            ColorSpace.getSRGB()
          )

          canvas = skiaSurface.getCanvas()
        }
        override def paintGL(): Unit = {
          canvas.clear(0xff000000)
          canvas.drawCircle(
            200,
            200,
            30,
            Paint().setARGB(255, 30, 160, 200)
          )
          skiaDirectContext.flush()
        }
      }
      val listener: io.qt.core.QMetaObject.Slot0 = () => openglWindow.update()
      openglWindow.frameSwapped.unn.connect(listener)
      val window = Window.wrap(openglWindow)

      window.title := "Qt + Skia test"
      window.visible := true
    }

    io.qt.widgets.QApplication.exec()
  }
}
