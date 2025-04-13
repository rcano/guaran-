package apricot
package skia

import apricot.graphics.GraphicsStack
import apricot.tools.GlfwWindow
import better.files.{Resource => _, *}
import com.codahale.metrics.ConsoleReporter
import guarana.*
import io.github.humbleui.skija.{Canvas, Font, FontMgr, FontStyle, Paint, Surface, TextLine}
import java.util.concurrent.locks.LockSupport
import org.lwjgl.glfw.GLFW.*
import scala.annotation.threadUnsafe
import scala.concurrent.duration.*
import scala.util.chaining.*

import animation.Script
import animation.ScriptDsl.{*, given}

@main def SimpleApricotTest: Unit = {
  scribe.Logger.root.withHandler(writer = scribe.file.FileWriter(scribe.file.PathBuilder.static(File("log.txt").path))).replace()

  val mainThread = Thread.currentThread
  @threadUnsafe
  lazy val engine: ApricotEngine[AbstractToolkit] = ApricotEngine(true, new AbstractToolkit {

    def timerDefs = animation.TimersDef.UnsupportedTimersDef
    protected def isOnToolkitThread(): Boolean = Thread.currentThread == mainThread
    protected def runOnToolkitThread(r: () => Any): Unit = engine.onNextFrame(r())
    def getMetrics(): Stylist.Metrics = Stylist.Metrics.NoOp

  })
  engine.switchToGraphicsStack(SkiaGraphicsStack())

  for (windowTitle <- Seq("Apricot skia test", "secondary window")) {
    val window = engine.tk.update {
      glfwInitHint(GLFW_PLATFORM, GLFW_PLATFORM_X11)
      val window = tools.GlfwWindow(engine)
  
      window.size := (800, 600)
      window.title := windowTitle
      window.visible := true
  
      engine.windows += window
    }
  
    val background = Layer("background").tap(engine.windows(window).layers.+=)
    background.renderables += new Renderable {
      var red: Int = 120
      var green: Int = 120
      var blue: Int = 120
      def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext) = {
        val canvas = gContext.asInstanceOf[SkiaGraphicsStack#SkiaGraphicsContext].canvas
        red += scala.util.Random.nextInt(2) * (if scala.util.Random.nextBoolean then 1 else -1) % 256
        green += scala.util.Random.nextInt(2) * (if scala.util.Random.nextBoolean then 1 else -1) % 256
        blue += scala.util.Random.nextInt(2) * (if scala.util.Random.nextBoolean then 1 else -1) % 256
        val color = red << 16 | green << 8 | blue
        canvas.clear(0xff000000 | color)
      }
    }
  
    val hud = Layer("HUD").tap(engine.windows(window).layers.+=)
  
    hud.renderables += new Renderable {
      val face = FontMgr.getDefault().nn.matchFamilyStyle("Menlo", FontStyle.NORMAL)
      val textLine = TextLine.make("Hello skia", Font(face).setSize(45))
      val textColor = Paint().setColor(0xff94cbff)
      def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext) =
        val canvas = gContext.asInstanceOf[SkiaGraphicsStack#SkiaGraphicsContext].canvas
        canvas.drawTextLine(textLine, 50, 150, textColor)
    }  
  }



  // ConsoleReporter.forRegistry(engine.metrics).unn.convertDurationsTo(MICROSECONDS).unn.build().unn.start(5, SECONDS)

  while true do {
    val t0 = System.nanoTime()
    glfwPollEvents()
    engine.engineStep()
    for (w <- engine.windows.list)
      glfwSwapBuffers(w.windowHandle) // wait for v-sync
    val stn = engine.sleepTimeNanos
    val dt = System.nanoTime() - t0

    if (stn > dt) LockSupport.parkNanos((stn - dt).toLong)
  }
}
