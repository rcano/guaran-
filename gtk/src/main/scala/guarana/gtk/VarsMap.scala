package guarana
package gtk

import org.gnome.gobject.GObject
import guarana.impl.RefCleaner

/** Calculates the map of name→var for this node by using reflection
  */
trait VarsMap {
  protected var ignoreProperties = collection.mutable.Set.empty[String]
  protected lazy val varsMap = getClass.getDeclaredMethods
    .asInstanceOf[Array[java.lang.reflect.Method]]
    .iterator
    .filter(f => classOf[ExternalVar[?]].isAssignableFrom(f.getReturnType) && f.getParameterCount == 0)
    .map(_.invoke(this).asInstanceOf[ExternalVar[?]])
    .map(v => v.name -> v)
    .toMap

  protected def connectVarsListener(instance: GObject, debug: Boolean = false): Unit = {
    val notifyCallback = NotifyBridge { param =>
      try {
        val property = param.getName
        if (!ignoreProperties(property)) {
          // if (debug) scribe.info(s"Trying to update $property")
          varsMap.get(property) foreach { case sv: ExternalVar[t] =>
            // if (debug) scribe.info("  found gtk var")
            Toolkit.update(
              summon[VarContext].externalPropertyUpdated(sv, None)(using
                ValueOf(instance.asInstanceOf[sv.ForInstance])
              )
            )
          }
        }
      } catch case e => e.printStackTrace()
    }
    val conn = instance.connect("notify", notifyCallback, true)
    VarsMap.cleaner.register(instance, () => conn.disconnect()) // hopefully this doesn't cause a sigsev
  }
}

object VarsMap {
  val cleaner = RefCleaner()
}
