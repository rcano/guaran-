package apricot

import guarana.*
import guarana.animation.Script

/** An animation definition.
  *
  * An animation definition produces a guarana.animation.Script once it's bound to a given context, which is a map
  * providing bindings. 
  */
trait DynamicScript {
  type AssignableVar[T] = DynamicScript.AssignableVar[T]
  def bind(context: Map[String, Any]): Script
}

object DynamicScript {
  class AssignableVar[T](`var`: Var[T], instance: `var`.ForInstance) {
    def apply():  VarContext ?=> T = `var`.apply()(using ValueOf(instance))
    def :=(v: T): VarContext ?=> Unit = `var`.:=(Binding.const(v))(using ValueOf(instance))
  }
  object AssignableVar:
    def apply[T](v: Var[T])(using value: ValueOf[v.ForInstance]): AssignableVar[T] = new AssignableVar(v, value.value)
}