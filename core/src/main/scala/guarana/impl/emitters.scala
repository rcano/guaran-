package guarana
package impl

import language.implicitConversions
import org.agrona.collections.Long2ObjectHashMap
import java.util.function.LongFunction
import scala.collection.mutable.ListBuffer

trait EmitterStation {

  def hasEmitter[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean
  def hasListeners[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext): Unit
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
  def emit[A](emitter: Emitter[A], evt: A)(using v: ValueOf[emitter.ForInstance], ctx: VarContext): Unit = {
    val key = Keyed(emitter, v.value).id
    emittersData.get(key) match {
      case null => //do nothing
      case prevData =>
        scribe.debug(s"dispatching event $evt to (${v.value}, $emitter)")
        // as a result of emitting, event iterators might register new listeners, and even to this every emitter's instance, which
        // would be lost of we simply did
        // `val updatedData = prevData.asInstanceOf[EmitterData[A]].emit(evt, emitter, v.value)`
        // that's why we must track if listeners to this instance are attempted to be added, and merge them with the result of emit
        val trackingContext = TrackingVarContext(emitter, v.value)
        var updatedData = prevData.asInstanceOf[EmitterData[A]].emit(evt, emitter, v.value)(using trackingContext)
        if (trackingContext.newListeners.nonEmpty) {
          trackingContext.newListeners.foreach(it => updatedData = updatedData.addListener(it.asInstanceOf[EventIterator[A]]))
        }
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

  private class TrackingVarContext(trackingEmitter: Emitter[?], trackingInstance: Any)(using delegate: VarContext) extends VarContext {
    val newListeners = ListBuffer[EventIterator[?]]()
    override def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit = delegate.update(v, binding)
    override def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = delegate(v)
    override def listen[A](emitter: Emitter[A])(f: EventIterator[A])(implicit v: ValueOf[emitter.ForInstance]): Unit =
      if (trackingEmitter.uniqueId == emitter.uniqueId && trackingInstance == v.value) newListeners += f
      delegate.listen(emitter)(f)
    override def emit[A](emitter: Emitter[A], evt: A)(implicit v: ValueOf[emitter.ForInstance]): Unit = delegate.emit(emitter, evt)
    override def externalPropertyUpdated[T](v: ObsVal[T], oldValue: Option[T])(using instance: ValueOf[v.ForInstance]): Unit =
      delegate.externalPropertyUpdated(v, oldValue)

  }
}
private[impl] object EmitterStationImpl {
  private case class EmitterData[T](
      itIdx: Int = 0,
      listeners: collection.immutable.IntMap[EventIterator[T]] = collection.immutable.IntMap.empty,
  ) {
    def addListener(listener: EventIterator[T]): EmitterData[T] = copy(itIdx + 1, listeners.updated(itIdx, listener))

    def emit(evt: T, emitter: Emitter[T], instance: emitter.ForInstance): VarContextAction[EmitterData[T]] = {
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
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext): Unit = {
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
