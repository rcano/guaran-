package guarana
package impl

import java.lang.ref.{Reference, WeakReference}

object RefCleaner
// We must wait for proper multithreading support before making this run in parallel, in the meantime, do it parasitically on the caller thread
class RefCleaner {
  private val refsQueue = java.lang.ref.ReferenceQueue[Any]()
  private val cleanerActions = collection.mutable.Map.empty[WeakReference[Any], () => Unit]

  def register(instance: Any, disposeAction: () => Unit): Unit = {
      val ref = new WeakReference(instance, refsQueue)
      cleanerActions(ref) = disposeAction
      //when registering new things, try to clean up existing ones
      var toClean: Reference[?] | Null = null
      while ({toClean = refsQueue.poll(); toClean != null}) {
        cleanerActions.remove(toClean.asInstanceOf[WeakReference[Any]]).foreach(_())
      }
  }
}
