package guarana
package qt

import io.qt.core.QBindable

object QtGuaranaCompat {
  def bindableToVar[T](b: QBindable[T]): Var.Aux[T, QtGuaranaCompat.type] = {
    val res = ExternalVar[QtGuaranaCompat.type, T](s"binding-$b", _ => b.value().unn, (_, v) => b.setValue(v), eagerEvaluation = true).forInstance(QtGuaranaCompat)
    val r: Runnable = () => Toolkit.update(summon[VarContext].externalPropertyUpdated(res, None))
    b.addNotifier(r)
    res
  }
}
