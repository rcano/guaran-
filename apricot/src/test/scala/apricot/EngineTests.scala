package apricot

import com.codahale.metrics.ConsoleReporter
import guarana.{unn, AbstractToolkit, Stylist}
import guarana.animation.ScriptDsl.{given, *}
import io.github.humbleui.skija.Surface
import scala.concurrent.duration.*
import scala.util.chaining.*

val NullOffscreenSurfaceFactory = new ApricotEngine.OffscreenSurfaceFactory {
  class NullHandle(val surface: Surface) extends HandleLike {
    def makeCurrent(): Unit = ()
  }
  type Handle = NullHandle
  def create(width: Int, height: Int) = NullHandle(Surface.makeNull(width, height))
  def destroy(handle: Handle): Unit = handle.surface.close()
}

val HeadlessToolkit = new AbstractToolkit {
  protected def isOnToolkitThread() = true
  protected def runOnToolkitThread(r: () => Any): Unit = r()
  
  def getMetrics(): Stylist.Metrics = Stylist.Metrics.NoOp
}

@main def WorkersTest: Unit = {
  val engine = ApricotEngine(true, HeadlessToolkit)

  ConsoleReporter.forRegistry(engine.metrics).unn.convertDurationsTo(MICROSECONDS).unn.build().unn.start(10, SECONDS)

  engine.scriptEngine run script {
    doUntil { _ =>
      for (_ <- 0 until 8) engine.scheduleTask((time, elapsed) => Thread.sleep((math.random() * 20).toInt))
      // and submit a bunch of really small ones
      for (_ <- 0 until 100) engine.scheduleTask((time, elapsed) =>
        val t0 = System.nanoTime()
        while ((System.nanoTime() - t0) < 200000) Thread.onSpinWait()
      )
      false
    }
  }
  engine.startEngineOnNewThread()
}

@main def ResourceReloadTest: Unit = {
  val engine = ApricotEngine(true, HeadlessToolkit)

  val subscription = engine.resourceManager
    .get(Path("readme.txt"))
    .headOption
    .get
    .subscribeAs[Resource.Type.Text](t => println(s"Loaded:\n$t"))


  engine.startEngineOnNewThread()
}
