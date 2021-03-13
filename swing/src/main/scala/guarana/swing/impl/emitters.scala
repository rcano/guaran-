package guarana.swing
package impl

import language.implicitConversions

trait EmitterStation {
  
  def hasEmitter[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext & Emitter.Context): Unit
  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using ValueOf[emitter.ForInstance]): Unit

  def snapshot: EmitterStation
}
object EmitterStation {
  def apply(): EmitterStation = EmitterStationImpl()
}
private[impl] class EmitterStationImpl extends EmitterStation {
  import EmitterStationImpl.*
  private val emittersData = KeyedWeakHashMap[Emitter[_], EmitterData[_]]

  def hasEmitter[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean = emittersData.contains(emitter)
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext & Emitter.Context): Unit = {
    emittersData.get(emitter) match {
      case Some(prevData) => emittersData(emitter) = prevData.asInstanceOf[EmitterData[A]].emit(evt)
      case _ => //do nothing
    }
  }
  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit = {
    val data = emittersData.getOrElseUpdate(emitter, EmitterData()).asInstanceOf[EmitterData[A]]
    emittersData(emitter) = data.addListener(f)
  }

  def snapshot: EmitterStation = {
    val res = EmitterStationImpl()
    res.emittersData ++= this.emittersData
    res
  }
}
private[impl] object EmitterStationImpl {
  private case class EmitterData[T](itIdx: Int = 0, listeners: collection.immutable.IntMap[EventIterator[T]] = collection.immutable.IntMap.empty[EventIterator[T]]) {
    def addListener(listener: EventIterator[T]): EmitterData[T] = copy(itIdx + 1, listeners.updated(itIdx, listener))

    def emit(evt: T): Scenegraph.ContextAction[EmitterData[T]] = {
      var updatedListeners = collection.immutable.IntMap.empty[EventIterator[T]]
      for ((key, listener) <- listeners) {
        listener.step(evt) foreach (newState => updatedListeners = updatedListeners.updated(key, newState))
      }
      copy(itIdx, updatedListeners)
    }
  }
}

class CopyOnWriteEmitterStation(val copy: EmitterStation) extends EmitterStation {
  var copied: EmitterStation | Null = null
  inline def theInstance = if (copied == null) copy else copied.asInstanceOf[EmitterStation]
  private def createCopy() = if (copied == null) copied = copy.snapshot
  def hasEmitter[A](emitter: Emitter[A])(using ValueOf[emitter.ForInstance]): Boolean = theInstance.hasEmitter(emitter)
  def emit[A](emitter: Emitter[A], evt: A)(using ValueOf[emitter.ForInstance], VarContext & Emitter.Context): Unit = {
    if (theInstance.hasEmitter(emitter)) {
      createCopy()
      theInstance.emit(emitter, evt)
    }
  }
  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using ValueOf[emitter.ForInstance]): Unit =
    theInstance.listen(emitter)(f)

  def snapshot: EmitterStation = CopyOnWriteEmitterStation(theInstance)
}