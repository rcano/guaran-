package apricot
package internal

import guarana.{unn, AbstractToolkit}
import guarana.util.cfor
import java.lang.invoke.VarHandle
import java.util.concurrent.locks.LockSupport
import scala.annotation.threadUnsafe
import scala.concurrent.duration.*
import scala.util.control.NonFatal

trait WorkersSupport { self: ApricotEngine[? <: AbstractToolkit] =>
  
  protected val workersTasks = new collection.mutable.ArrayBuffer[Updateable]()

  /** Schedules the given task to be run in background workers.
    * Background workers have specific semantics regarding memory usage because they are intended to co-work with the
    * main thread.
    * Every frame, the engine distributes the scheduled work among workers and issues a load fence, "publishing" existing memory to them, then
    * the frame continues processing as usual. Once a worker finishes its batch of work, it runs a memory write fence so that changes are
    * visible again by the main engine thread.
    * 
    * This means that variables changed by a background task executed by a worker will be visible by main engine thread as soon as the worker
    * completes its batch of work, in following frames.
    */
  def scheduleTask(task: Updateable): Unit = workersTasks += task

  private val workerThreadsPq = scala.collection.mutable.PriorityQueue.tabulate(Runtime.getRuntime.unn.availableProcessors.max(4))(new WorkerThread(_))
  private val workerThreads = workerThreadsPq.toArray //store them in an array for fast iteration
  workerThreads.foreach(_.start())

  protected val distributeWorkTimer = metrics.timer("distribute-work").unn
  private val needsStarted = collection.mutable.Set.empty[WorkerThread]
  protected def distributeWork(): Unit = if (workersTasks.nonEmpty) {
    val t0 = System.nanoTime()
    VarHandle.acquireFence()
    needsStarted.clear()
    cfor(0, _ < workersTasks.length) { i =>
      val worker = workerThreadsPq.dequeue().unn
      worker.pendingWork.getAcquire().unn += workersTasks(i)
      worker.pendingWorkGauge.inc()
      needsStarted += worker
      workerThreadsPq.enqueue(worker)
      i + 1
    }
    needsStarted.foreach(LockSupport.unpark(_))
    workersTasks.clear()
    distributeWorkTimer.update(System.nanoTime() - t0, NANOSECONDS)
  }

  /** This threads carry parallel computations per frame in a coordinated way with the main thread.
    * At the beginnign of the frame, all job scheduled for workers is divvied up and executed amongst
    * workers. Workers run in alignment with the main loop, starting when it starts, and completing their
    * work during the same frame.
    */ 
  private class WorkerThread(ordinal: Int) extends Thread(s"WorkerThread-$ordinal") {
    setDaemon(true)
    val pendingWork = new java.util.concurrent.atomic.AtomicReference(collection.mutable.Queue.empty[Updateable])
    // used for "page flipping" the pending work
    private var workingList = collection.mutable.Queue.empty[Updateable]
    val pendingWorkGauge = metrics.counter(s"${getName}-queue-size").unn
    private val processedGauge = metrics.counter(s"${getName}-processed").unn
    
    override def run(): Unit = while (true) {
      LockSupport.park()
      //when waking up, we must ensure all stores happened
      VarHandle.acquireFence()
      workingList = pendingWork.getAndSet(workingList).unn
      var t0 = engineTime()
      while (workingList.nonEmpty) {
        val task = workingList.dequeue()
        try task.update(t0)
        catch case NonFatal(e) => new Exception(s"Uncaught exception on worker ${getName}", e)
        pendingWorkGauge.dec()
        processedGauge.inc()
        
        // we don't know how long it'll take to process the current queue of work, but if it goes beyond a frame duration
        // we'll resync as best as possible
        if (workingList.nonEmpty) {
          val stn = sleepTimeNanos
          val now = engineTime()
          if (now - t0 > (stn * 0.9).toLong) VarHandle.releaseFence()
          if (now - t0 > stn) { // a full frame has passed, update our internal state before continuing processing
            VarHandle.acquireFence()
            t0 = engineTime()
          }
        }
      }
      VarHandle.releaseFence()
    }
  }

  @threadUnsafe
  private given Ordering[WorkerThread] = (t1, t2) => (t2.pendingWorkGauge.getCount - t1.pendingWorkGauge.getCount).toInt //reverse order, because we prefer shorter queues
}
