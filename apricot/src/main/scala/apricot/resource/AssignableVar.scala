package apricot.resource

import guarana.*

class AssignableVar[T](`var`: Var[T], instance: `var`.ForInstance) {
  def apply():  VarContext ?=> T = `var`.apply()(using ValueOf(instance))
  def :=(v: T): VarContext ?=> Unit = `var`.:=(Binding.const(v))(using ValueOf(instance))
}
object AssignableVar:
  def apply[T](v: Var[T])(using value: ValueOf[v.ForInstance]): AssignableVar[T] = new AssignableVar(v, value.value)