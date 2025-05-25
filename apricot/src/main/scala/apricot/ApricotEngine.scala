package apricot

import com.codahale.metrics.{Gauge, MetricRegistry}
import guarana.*
import guarana.animation.ScriptEngine
import guarana.util.cfor
import java.util.concurrent.locks.LockSupport
import java.util.ServiceLoader
import scala.annotation.threadUnsafe
import scala.annotation.unchecked.uncheckedStable
import scala.concurrent.duration._
import apricot.graphics.GraphicsStack
import apricot.tools.GlfwWindow

class ApricotEngine[Tk <: AbstractToolkit](
    val devMode: Boolean,
    val tk: Tk,
) extends internal.WorkersSupport {
  protected var engineStartTime: Long = -1
  val resourceManager = ResourceManager()
  val scriptEngine = ScriptEngine(tk)
  private var _graphicsStack: GraphicsStack = GraphicsStack.NoOp
  @threadUnsafe lazy val metrics = MetricRegistry()

  @uncheckedStable
  def graphicsStack: GraphicsStack = _graphicsStack
  def switchToGraphicsStack(graphicsStack: GraphicsStack): Unit = {
    for (_, ctx) <- windows._windows do ctx.gContext.close()
    _graphicsStack.shutdown()
    _graphicsStack = graphicsStack
    for (win, ctx) <- windows._windows do ctx.gContext = _graphicsStack.setup(win)
  }

  given ApricotEngine[Tk] = this
  given ScriptEngine = scriptEngine
  given ResourceManager = resourceManager
  given GraphicsStack = graphicsStack

  protected def devClassFolders: Set[better.files.File] = Set(
    better.files.File("./target/scala-3.6.2/classes/"),
    better.files.File("./target/scala-3.6.2-RC2/test-classes/")
  )

  if devMode then
    devClassFolders.filter(_.exists).foreach(f => resourceManager.register(new locators.FileSystemResourceLocator(f, this)))
  else resourceManager.register(new locators.ClassLoaderLocator(this))

  private val pendingTasks = collection.mutable.Queue.empty[() => Any]
  def onNextFrame(task: => Unit): Unit = pendingTasks += (() => task)

  var targetFps = 60d // default to 60, but should probably query the screen where the window is going to show up
  /** If set to true, will clear the window at the start of the frame */
  var autoClearWindow = true
  var autoClearColor: Int = 0xffffffff

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

  val updateables = new collection.mutable.ArrayBuffer[Updateable](128)
  object windows {
    private val windowCounter = java.util.concurrent.atomic.AtomicInteger(0)
    class WindowContext private[windows](val layers: Layers, var gContext: GraphicsStack#GraphicsContext) {
      private[ApricotEngine] val windowIdx = windowCounter.incrementAndGet()
      private[ApricotEngine] val gpuFlushTimer = metrics.timer(s"gpuFlushTimer-$windowIdx").unn
      private[ApricotEngine] var lastRenderTime: Long = -1
      val renderTimeGauge =
        metrics.gauge[Gauge[Long]](s"renderTime-$windowIdx", (() => () => lastRenderTime): MetricRegistry.MetricSupplier[Gauge[Long]]).unn
      private[ApricotEngine] var gpuFlushTime: Long = -1
      val gpuFlushGauge =
        metrics.gauge[Gauge[Long]](s"gpuFlushTime-$windowIdx", (() => () => gpuFlushTime): MetricRegistry.MetricSupplier[Gauge[Long]]).unn

    }
    private[ApricotEngine] val _windows = collection.mutable.LinkedHashMap.empty[GlfwWindow, WindowContext]
    def +=(window: GlfwWindow): window.type = {
      this -= window
      _windows(window) = WindowContext(Layers(), graphicsStack.setup(window))
      window
    }
    def -=(window: GlfwWindow): window.type = {
      _windows.get(window).foreach(_.gContext.close())
      window
    }

    def list: Iterable[GlfwWindow] = _windows.keys

    def apply(window: GlfwWindow): WindowContext =
      _windows.getOrElse(window, throw new IllegalStateException("window has not been registered with this engine"))
  }
  class Layers extends collection.mutable.LinkedHashMap[String, Layer] {
    def +=(layer: Layer): layer.type =
      update(layer.name, layer)
      layer
  }

  /** This method operates on the canvas performing all the draws of the frame. Should be called from the main loop. If you override this
    * method, you should call into super.renderFrame()
    */
  protected def renderFrame(): Unit = {
    for (window, ctx) <- windows._windows do {
      val t0 = engineTime()
      ctx.gContext.renderLayers(ctx.layers.values, autoClearWindow, autoClearColor)
      ctx.gpuFlushTime = engineTime() - t0
      ctx.gpuFlushTimer.update(gpuFlushTime, NANOSECONDS)
    }
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
