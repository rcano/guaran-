package apricot

import com.codahale.metrics.MetricRegistry
import guarana.*
import guarana.animation.ScriptEngine
import guarana.util.cfor
import io.github.humbleui.skija.{Canvas, Drawable, Surface}
import java.util.concurrent.locks.LockSupport
import java.util.ServiceLoader
import scala.annotation.threadUnsafe
import scala.concurrent.duration._
import scala.util.chaining.*

class ApricotEngine[Tk <: AbstractToolkit](val devMode: Boolean, val tk: Tk) extends internal.WorkersSupport {
  protected var engineStartTime: Long = -1
  val resourceManager = ResourceManager()
  val scriptEngine = ScriptEngine(tk)
  @threadUnsafe lazy val metrics = MetricRegistry()

  if devMode then
    resourceManager.register(new locators.FileSystemResourceLocator(better.files.File("./target/scala-3.1.0/classes/"), this))
    resourceManager.register(new locators.FileSystemResourceLocator(better.files.File("./target/scala-3.1.0/test-classes/"), this))
  else
    resourceManager.register(new locators.ClassLoaderLocator())


  private val pendingTasks = collection.mutable.Queue.empty[() => Any]
  def onNextFrame(task: => Unit): Unit = pendingTasks += (() => task)

  var targetFps = 60d // default to 60, but should probably query the screen where the window is going to show up
  private val fpsTimer = metrics.timer("fps").unn

  private var surface: Surface | Null = null
  private var canvas: Canvas | Null = null

  val updateables = new collection.mutable.ArrayBuffer[Updateable](128)
  val layers = new collection.mutable.ArrayBuffer[Layer](8)

  /** Engine requires a surface for rendering. Call this method after setting up an opengl surface for rendering.
    */
  def setRenderingSurface(surface: Surface, canvas: Canvas): Unit = {
    this.surface = surface
    this.canvas = canvas
  }

  /** This method operates on the canvas performing all the draws of the frame. Should be called from the main loop. If you override this
    * method, you should call into super.renderFrame()
    */
  protected def renderFrame(): Unit = canvas.? { canvas =>
    cfor(0, _ < layers.length) { i =>
      layers(i).draw(surface.unn, canvas)
      i + 1
    }
    surface.unn.flush()
  }

  /** Run an engine loop on the caller's thread. Returns the total time that the step took to compute in nanos. */
  def engineStep(): Long = {
    if (engineStartTime == -1) engineStartTime = System.nanoTime() //on the very first step, set the engine time to start
    val t0 = engineTime()

    tk.update {
      while (pendingTasks.nonEmpty) pendingTasks.dequeue().apply()
      cfor(0, _ < updateables.length) { i =>
        val u = updateables(i)
        if (u.isParallelizable) scheduleTask(u)
        else u.update(t0)
        i + 1
      }
      distributeWork()
      scriptEngine.update(t0)
      renderFrame()
    }

    val dt = engineTime() - t0
    fpsTimer.update(dt, NANOSECONDS)
    dt
  }

  inline def sleepTimeNanos = (1000000000 / targetFps).toLong

  def engineTime(): Long = System.nanoTime() - engineStartTime

  /** Uses the current thread to run the engine continually. This method doesn't return since it enters a while loop until the system exists
    */
  def runEngine(): Nothing = {
    while (true) {
      val dt = engineStep()
      val stn = sleepTimeNanos

      if (stn > dt) LockSupport.parkNanos((stn - dt).toLong)
    }
    throw new IllegalStateException("Loop shouldn't die except via exception. Should not have reached this point")
  }

  /** Creates a new thread and set it to continually run the engineStep(), while sleeping in between */
  def startEngineOnNewThread(): Thread = {
    val res = new Thread("ApricotEngine-main-thread") {
      override def run(): Unit = runEngine()
    }
    res.start()
    res
  }

  locally {
    val loader = ServiceLoader.load(classOf[locators.ResourceLocatorProvider]).unn
    import scala.jdk.CollectionConverters.*
    scribe.info(s"Found locators = ${loader.iterator.unn.asScala.mkString("[", "\n", "]")}")
    loader.forEach(l => resourceManager.register(l.unn.create(this)))
  }
}
