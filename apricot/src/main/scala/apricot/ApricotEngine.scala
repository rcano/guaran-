package apricot

import com.codahale.metrics.{Gauge, MetricRegistry}
import guarana.*
import guarana.animation.ScriptEngine
import guarana.util.cfor
import io.github.humbleui.skija.{Canvas, Drawable, Surface}
import java.util.concurrent.locks.LockSupport
import java.util.ServiceLoader
import scala.annotation.threadUnsafe
import scala.concurrent.duration._
import scala.util.chaining.*

class ApricotEngine[Tk <: AbstractToolkit](
    val devMode: Boolean,
    val tk: Tk,
    // val surfaceFactory: ApricotEngine.OffscreenSurfaceFactory
) extends internal.WorkersSupport {
  protected var engineStartTime: Long = -1
  val resourceManager = ResourceManager()
  val scriptEngine = ScriptEngine(tk)
  @threadUnsafe lazy val metrics = MetricRegistry()

  given ApricotEngine[Tk] = this
  given ScriptEngine = scriptEngine
  given ResourceManager = resourceManager

  if devMode then
    resourceManager.register(new locators.FileSystemResourceLocator(better.files.File("./target/scala-3.1.1/classes/"), this))
    val testClasses = better.files.File("./target/scala-3.1.1/test-classes/")
    if testClasses.exists then resourceManager.register(new locators.FileSystemResourceLocator(testClasses, this))
  else resourceManager.register(new locators.ClassLoaderLocator(this))

  private val pendingTasks = collection.mutable.Queue.empty[() => Any]
  def onNextFrame(task: => Unit): Unit = pendingTasks += (() => task)

  var targetFps = 60d // default to 60, but should probably query the screen where the window is going to show up
  val fpsTimer = metrics.timer("fps").unn
  val updateTimer = metrics.timer("updateTimer").unn
  val renderTimer = metrics.timer("renderTimer").unn
  val gpuFlushTimer = metrics.timer("gpuFlushTimer").unn
  private var lastFrameTime: Long = -1
  val frameTimeGauge = metrics.gauge[Gauge[Long]]("frameTime", (() => () => lastFrameTime): MetricRegistry.MetricSupplier[Gauge[Long]]).unn
  private var lastRenderTime: Long = -1
  val renderTimeGauge =
    metrics.gauge[Gauge[Long]]("renderTime", (() => () => lastRenderTime): MetricRegistry.MetricSupplier[Gauge[Long]]).unn
  private var gpuFlushTime: Long = -1
  val gpuFlushGauge = metrics.gauge[Gauge[Long]]("gpuFlushTime", (() => () => gpuFlushTime): MetricRegistry.MetricSupplier[Gauge[Long]]).unn
  private var lastUpdateTime: Long = -1
  val updateTimeGauge =
    metrics.gauge[Gauge[Long]]("updateTime", (() => () => lastUpdateTime): MetricRegistry.MetricSupplier[Gauge[Long]]).unn

  private var surface: Surface = Surface.makeNull(1, 1)
  private var canvas: Canvas = surface.getCanvas

  val updateables = new collection.mutable.ArrayBuffer[Updateable](128)
  object layers extends collection.mutable.LinkedHashMap[String, Layer] {
    def +=(layer: Layer): layer.type = 
      update(layer.name, layer)
      layer
  }
  // private val layerSurfaces = new collection.mutable.ArrayBuffer[surfaceFactory.Handle](8)

  /** Engine requires a surface for rendering. Call this method after setting up an opengl surface for rendering.
    */
  def setRenderingSurface(surface: Surface, canvas: Canvas): Unit = {
    this.surface = surface
    this.canvas = canvas
    // scribe.debug("clearing old surfaces")
    // layerSurfaces.foreach(surfaceFactory.destroy)
    // layerSurfaces.clear()
    // scribe.debug(s"creating ${layers.length} secondary surfaces")
    // layers.foreach(_ => layerSurfaces += surfaceFactory.create(surface.getWidth, surface.getHeight))
  }

  /** This method operates on the canvas performing all the draws of the frame. Should be called from the main loop. If you override this
    * method, you should call into super.renderFrame()
    */
  protected def renderFrame(): Unit = {
    // import scala.collection.parallel.CollectionConverters.*
    // layers.view.zipWithIndex.foreach((l, idx) =>
    //   val handle = layerSurfaces(idx)
    //   handle.makeCurrent()
    //   val canvas = handle.surface.getCanvas
    //   canvas.clear(0xffffffff)
    //   l.draw(handle.surface, canvas)
    //   handle.surface.flush()
    // )
    canvas.clear(0xffffffff)
    // cfor(0, _ < layers.length) { i =>
    //   layerSurfaces(i).surface.draw(canvas, 0, 0, null)
    //   i + 1
    // }
    for ((_, layer) <- layers) layer.draw(surface.unn, canvas)
    // cfor(0, _ < layers.length) { i =>
    //   layers(i).draw(surface.unn, canvas)
    //   i + 1
    // }
    val t0 = engineTime()
    surface.flush()
    gpuFlushTime = engineTime() - t0
    gpuFlushTimer.update(gpuFlushTime, NANOSECONDS)
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
      scriptEngine.update(t0)
      lastUpdateTime = engineTime() - t0
      updateTimer.update(lastUpdateTime, NANOSECONDS)

      renderFrame()
      lastRenderTime = engineTime() - (lastUpdateTime + t0) //use lastUpdate time to get the timestamp at the end up update
      renderTimer.update(lastRenderTime, NANOSECONDS)
      
      distributeWork()
    }

    val dt = engineTime() - t0
    fpsTimer.update(dt, NANOSECONDS)
    lastFrameTime = dt
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

object ApricotEngine {
  trait OffscreenSurfaceFactory {

    trait HandleLike {
      def surface: Surface
      def makeCurrent(): Unit
    }
    type Handle <: HandleLike

    /** create a framebuffer with the specified dimensions and return its id */
    def create(width: Int, height: Int): Handle
    def destroy(handle: Handle): Unit
  }
}
