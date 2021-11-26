package apricot

trait Updateable {
  private var previousUpdateTime: Long = -1
  private var isParallel = false

  def update(currentTimeNanos: Long): Unit = 
    val dt = currentTimeNanos - previousUpdateTime
    try updateImpl(currentTimeNanos, dt)
    finally previousUpdateTime = currentTimeNanos

  protected def updateImpl(currentTimeNanos: Long, elapsed: Long): Unit

  def isParallelizable: Boolean = isParallel
  protected def setParallelizable(b: Boolean): Unit = isParallel = b
}
