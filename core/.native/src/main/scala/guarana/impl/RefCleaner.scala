package guarana
package impl

import java.lang.ref.WeakReference
import scala.util.chaining.*
import scalanative.posix.errno.*
import scalanative.posix.pthread.*
import scalanative.posix.sys.types.*
import scalanative.unsafe
import java.lang.ref.Reference

object RefCleaner
// We must wait for proper multithreading support before making this run in parallel, in the meantime, do it parasitically on the caller thread
class RefCleaner {
  // private given zone: unsafe.Zone = unsafe.Zone.open()
  // private val cleanerThread = {
  //   val attrs = unsafe.stackalloc[pthread_attr_t]()
  //   pthread_attr_init(attrs).tap(res => if res != 0 then throw new IllegalStateException(s"Failed to create attrs for thread, errno: $res"))
  //   pthread_attr_setdetachstate(attrs, PTHREAD_CREATE_DETACHED)
  //   val resThread = unsafe.alloc[pthread_t]()
  //   pthread_create(resThread, attrs, cleanerLoop, null.asInstanceOf).tap { res =>
  //     if res == EAGAIN then throw new IllegalArgumentException("Insufficient resources for creating cleaner thread")
  //     else if res == EINVAL then throw new IllegalStateException("Invalid attributes for cleaner thread")
  //     else if res == EPERM then throw new IllegalStateException("insuffient permissions for creating thread")
  //     else throw new IllegalStateException(s"Unexpected error creating thread, errno: $res")
  //   }
  //   resThread
  // }
  private val refsQueue = java.lang.ref.ReferenceQueue[Any]()
  private val cleanerActions = collection.mutable.Map.empty[WeakReference[Any], () => Unit]

  def register(instance: Any, disposeAction: () => Unit): Unit = {
    // synchronized {
      val ref = new WeakReference(instance, refsQueue)
      cleanerActions(ref) = disposeAction
      //when registering new things, try to clean up existing ones
      var toClean: Reference[?] | Null = null
      while ({toClean = refsQueue.poll(); toClean != null}) {
        cleanerActions.remove(toClean.asInstanceOf[WeakReference[Any]]).foreach(_())
      }
    // }
  }

  // private def cleanerLoop(args: unsafe.Ptr[Byte]): unsafe.Ptr[Byte] = {
  //   println("cleaner thread started")
  //   while (true) {
  //     println("polling ref")
  //     val ref = refsQueue.remove().nn.asInstanceOf[WeakReference[Any]]
  //     println(s"got ref $ref")
  //     cleanerActions.remove(ref).foreach(_())
  //   }
  //   null.asInstanceOf[unsafe.Ptr[Byte]]
  // }
}
