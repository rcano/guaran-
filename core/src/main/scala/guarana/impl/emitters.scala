package guarana
package impl

import language.implicitConversions
import org.agrona.collections.Long2ObjectHashMap
import java.util.function.LongFunction

trait EmitterStation {

  def hasEmitter[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean
  def hasListeners[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext & Emitter.Context): Unit
  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using ValueOf[emitter.ForInstance]): Unit
  def remove[A](emitter: Keyed[Emitter[A]]): Unit

  def snapshot: EmitterStation
}
object EmitterStation {
  def apply(): EmitterStation = EmitterStationImpl()
}
private[impl] class EmitterStationImpl extends EmitterStation {
  import EmitterStationImpl.*
  private val emittersData = new Long2ObjectHashMap[EmitterData[?]](32, 0.8)

  def hasEmitter[A](emitter: Emitter[A])(using v: ValueOf[emitter.ForInstance]): Boolean =
    emittersData.containsKey(Keyed(emitter, v.value).id)
  def hasListeners[A](emitter: Emitter[A])(using v: ValueOf[emitter.ForInstance]): Boolean = emittersData
    .get(Keyed(emitter, v.value).id)
    .nullFold(
      _.listeners.nonEmpty,
      false
    )
  def emit[A](emitter: Emitter[A], evt: A)(using v: ValueOf[emitter.ForInstance], ctx: VarContext & Emitter.Context): Unit = {
    val key = Keyed(emitter, v.value).id
    emittersData.get(key) match {
      case null => //do nothing
      case prevData =>
        scribe.debug(s"dispatching event $evt to (${v.value}, $emitter)")
        val updatedData = prevData.asInstanceOf[EmitterData[A]].emit(evt, emitter, v.value)
        emittersData.put(key, updatedData)
        if (!hasListeners(emitter)) {
          scribe.debug(s"triggering on-no-listener callback for (${v.value}, $emitter)")
          emitter.onNoListener(v.value)
        }
    }
  }
  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit = {
    val key = Keyed(emitter, instance.value).id
    val data = emittersData.computeIfAbsent(key, (_ => EmitterData()): LongFunction[EmitterData[A]]).asInstanceOf[EmitterData[A]]
    val wasEmpty = data.listeners.isEmpty
    val updatedData = emittersData.put(key, data.addListener(f)).unn.asInstanceOf[EmitterData[A]]
    scribe.debug(s"adding listener on ($instance, $emitter)")
    if (wasEmpty) {
      scribe.debug(s"triggering on-first-listener callback for (${instance.value}, $emitter)")
      emitter.onFirstListener(instance.value)
    }
  }
  def remove[A](emitter: Keyed[Emitter[A]]): Unit = {
    scribe.debug(s"removing emitter $emitter")
    emittersData.remove(emitter.id)
  }

  def snapshot: EmitterStation = {
    val res = EmitterStationImpl()
    res.emittersData `putAll` this.emittersData
    res
  }
}
private[impl] object EmitterStationImpl {
  private case class EmitterData[T](
      itIdx: Int = 0,
      listeners: collection.immutable.IntMap[EventIterator[T]] = collection.immutable.IntMap.empty,
  ) {
    def addListener(listener: EventIterator[T]): EmitterData[T] = copy(itIdx + 1, listeners.updated(itIdx, listener))

    def emit(evt: T, emitter: Emitter[T], instance: emitter.ForInstance): ToolkitAction[EmitterData[T]] = {
      var updatedListeners = collection.immutable.IntMap.empty[EventIterator[T]]
      for ((key, listener) <- listeners) {
        listener.step(evt) foreach (newState => updatedListeners = updatedListeners.updated(key, newState))
      }
      // if (updatedListeners.isEmpty) onNoListenersCallbacks.foreach(_(emitter, instance))
      copy(itIdx, updatedListeners)
    }
  }
}

class CopyOnWriteEmitterStation(val copy: EmitterStation) extends EmitterStation {
  var copied: EmitterStation | Null = null
  inline def theInstance = if (copied == null) copy else copied.asInstanceOf[EmitterStation]
  private def createCopy() = if (copied == null) copied = copy.snapshot
  def hasEmitter[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean = theInstance.hasEmitter(emitter)
  def hasListeners[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean = theInstance.hasListeners(emitter)
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext & Emitter.Context): Unit = {
    if (theInstance.hasEmitter(emitter)) {
      createCopy()
      theInstance.emit(emitter, evt)
    }
  }
  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using ValueOf[emitter.ForInstance]): Unit =
    theInstance.listen(emitter)(f)

  def remove[A](emitter: Keyed[Emitter[A]]): Unit = theInstance.remove(emitter)

  def snapshot: EmitterStation = CopyOnWriteEmitterStation(theInstance)
}
