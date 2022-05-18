package guarana
package impl

class RefCleaner {
  val cleaner = java.lang.ref.Cleaner.create().unn
  def register(instance: Any, disposeAction: () => Unit): Unit = cleaner.register(instance, () => disposeAction())
}
