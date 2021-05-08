package guarana
package qt

import io.qt.core.*
import io.qt.widgets.QApplication

type Toolkit = Toolkit.type
object Toolkit extends AbstractToolkit {
  // private class qtApp extends QApplication(Array[String | Null]()) {

  // }

  val qtAppInstance = QApplication.initialize(Array()).nn
  protected def isOnToolkitThread(): Boolean = true
  protected def runOnToolkitThread(r: () => Any): Unit = r()

  type VarToSignalType[T] = T match {
    case Int => java.lang.Integer
    case Long => java.lang.Long
    case Float => java.lang.Float
    case Double => java.lang.Double
    case Boolean => java.lang.Boolean
    case a | Null => a
    case _ => T
  }
  def connectVar[T](v: Var[T], signal: QObject#Signal1[VarToSignalType[T]])(using ValueOf[v.ForInstance]): Unit = {
    val slot: QMetaObject.Slot1[VarToSignalType[T]] = v match {
      case _: ExternalVar[T] => value => update(summon[VarContext].externalPropertyUpdated(v, None))
      case _ => value => update(v := value.asInstanceOf[T])
    }
    
    signal.connect(slot, Qt.ConnectionType.DirectConnection)
  }
  /** Connects a signal to an emitter */
  def connectEmitter[T](emitter: Emitter[T], signal: QObject#Signal1[VarToSignalType[T]])(using ValueOf[emitter.ForInstance]): Unit = {
    val slot: QMetaObject.Slot1[VarToSignalType[T]] = value => update(summon[Emitter.Context].emit(emitter, value.asInstanceOf[T]))
    signal.connect(slot, Qt.ConnectionType.DirectConnection)
  }

  val applicationState = Var.autoName[Qt.ApplicationState](Qt.ApplicationState.ApplicationInactive)
  connectVar(applicationState, qtAppInstance.applicationStateChanged.nn)
}