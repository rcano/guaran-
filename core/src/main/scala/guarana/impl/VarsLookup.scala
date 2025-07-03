package guarana
package impl

import org.agrona.collections.IntHashSet
import org.agrona.collections.Int2ObjectHashMap

import AgronaUtils.*

class VarsLookup {
  case class InstanceData(instance: util.WeakRef[AnyRef], vars: IntHashSet, emitters: IntHashSet)
  private val instancesData = new Int2ObjectHashMap[InstanceData]()
  private val externalVars = new IntHashSet(1024)
  private val seenVars = new Int2ObjectHashMap[ObsVal[?]]
  private val cleaner = impl.RefCleaner()

  def apply[T](instanceId: Int): InstanceData | Null = instancesData.get(instanceId)

  def lookup[T](s: Keyed[Var[T]]): (v: Var[T], instance: Any) | Null = {
    val data = instancesData.get(s.instanceId).unn
    data.instance.deref match {
      case null => null
      case instance => seenVars.get(s.keyId).unn.asInstanceOf[Var[T]].nullFold(_.asInstanceOf[Var[T]] -> instance, null)
    }
  }

  def recordInstance(instance: Any, onVarCleanup: Keyed[Var[Any]] => Unit, onEmitterCleanup: Keyed[Emitter[Any]] => Unit): InstanceData = {
    val instanceId = Keyed.getId(instance)
    instancesData.get(instanceId) match
      case null =>
        val vars = new IntHashSet(8)
        val emitters = new IntHashSet(4)
        val data = InstanceData(util.WeakRef(instance.asInstanceOf[AnyRef]), vars, emitters)
        instancesData.put(instanceId, data)
        // format: off
        cleaner.register(
          instance,
          () => instancesData.remove(instanceId).?(data =>
            Debug.elidable {scribe.debug(f"instance data 0x$instanceId%H removed") }
            data.vars.fastForeach(v => onVarCleanup(Keyed.raw(v, instanceId)))
            data.emitters.fastForeach(v => onEmitterCleanup(Keyed.raw(v, instanceId)))
          )
        )
        Debug.elidable {scribe.debug(f"instance $instance recorded with id=0x$instanceId%H") }
        data

      case data: InstanceData => data
  }

  def recordVarUsage[T](v: ObsVal[T], onVarCleanup: Keyed[Var[Any]] => Unit, onEmitterCleanup: Keyed[Emitter[Any]] => Unit)(using
      instance: ValueOf[v.ForInstance]
  ): Unit = {
    val varForInstanceAdded = recordInstance(instance.value, onVarCleanup, onEmitterCleanup).vars.add(v.uniqueId)
    seenVars.put(v.uniqueId, v)
    if (v.isInstanceOf[ExternalObsVal[?]]) externalVars.add(v.uniqueId)
    if (varForInstanceAdded) {
      Debug.elidable {scribe.debug(s"Var(${instance.value}, $v) recorded. Key = ${ObsVal.obs2Keyed(v).descrString}") }
      v.onFirstAssociation(instance.value)
    }
    v match {
      case vv: Var[T] @unchecked => vv.projections.foreach(p => recordVarUsage(p.asInstanceOf[Var[?] { type ForInstance = v.ForInstance }], onVarCleanup, onEmitterCleanup))
      case _ =>
    }
  }

  def describe[T](s: Keyed[Var[T]]): String = {
    val data = instancesData.get(s.instanceId).unn
    val theVar = seenVars.get(s.keyId).unn.asInstanceOf[Var[T]]
    describe(theVar, data.instance.deref.asInstanceOf[theVar.ForInstance])
  }
  def describe[T](v: Var[T], instance: v.ForInstance): String = {
    val maxWidth = 60
    var instanceDescr = instance.toString.stripPrefix("javax.swing.")
    instanceDescr = instanceDescr match {
      case s if s.length < maxWidth => s
      case s => s.take(maxWidth - 3) + "..."
    }
    s"$instanceDescr: $v"
  }
}
