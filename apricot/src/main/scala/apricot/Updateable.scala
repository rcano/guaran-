package apricot

trait Updateable {
  private var previousUpdateTime: Long = -1
  private var isParallel = false
  /** Internal variable to prevent reentrantcy. The work scheduler is responsible for setting this variable to running
    * when scheduling in the main thread, to ensure proper memory visibility
    */ 
  private[apricot] var running = false

  def update(currentTimeNanos: Long): Unit = 
    val dt = currentTimeNanos - previousUpdateTime
    try updateImpl(currentTimeNanos, dt)
    finally {
      previousUpdateTime = currentTimeNanos
      running = false
    }

  protected def updateImpl(currentTimeNanos: Long, elapsed: Long): Unit

  def isParallelizable: Boolean = isParallel
  protected def setParallelizable(b: Boolean): Unit = isParallel = b
}
