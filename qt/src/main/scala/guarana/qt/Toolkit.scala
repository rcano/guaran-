package guarana
package qt

import io.qt.core.*
import io.qt.widgets.QApplication

type Toolkit = Toolkit.type
object Toolkit extends AbstractToolkit {
  // private class qtApp extends QApplication(Array[String | Null]()) {

  // }
  /** Set the args for initializing QT, set this _before_ any call to the toolkit or it wont get picked up */
  var initializationArgs: IArray[String] = IArray()
  protected def isOnToolkitThread(): Boolean = 
    qtAppInstance
    true
  protected def runOnToolkitThread(r: () => Any): Unit = 
    qtAppInstance
    r()

  def getMetrics(): guarana.Stylist.Metrics = ???

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


  lazy val qtAppInstance = 
    println(s"initializing with ${initializationArgs.mkString(" ")}")
    val qtAppInstance = QApplication.initialize(initializationArgs.asInstanceOf).nn
    connectVar(applicationState, qtAppInstance.applicationStateChanged.nn)
    qtAppInstance
}