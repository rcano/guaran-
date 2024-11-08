package guarana
package impl

class RefCleaner {
  val registry = scalajs.js.FinalizationRegistry[Any, () => Unit, Unit](_.apply())
  def register(instance: Any, disposeAction: () => Unit): Unit = registry.register(instance, () => disposeAction())
}
