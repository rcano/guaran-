package guarana
package qt

import io.qt.core.Qt.TimerType
import io.qt.core.*
import io.qt.widgets.QApplication
import scala.concurrent.duration.FiniteDuration
import scala.util.control.NonFatal

type Toolkit = Toolkit.type
object Toolkit extends AbstractToolkit, animation.TimersDef {
  // private class qtApp extends QApplication(Array[String | Null]()) {

  // }
  /** Set the args for initializing QT, set this _before_ any call to the toolkit or it wont get picked up */
  var initializationArgs: IArray[String] = IArray()
  protected def isOnToolkitThread(): Boolean = qtAppInstance.thread() == QThread.currentThread()
  protected def runOnToolkitThread(r: () => Any): Unit = QCoreApplication.postEvent(qtAppInstance, RunThunk(r))

  def getMetrics(): guarana.Stylist.Metrics = guarana.Stylist.Metrics.NoOp

  // type VarToSignalType[T] = T match {
  //   case Int => java.lang.Integer
  //   case Long => java.lang.Long
  //   case Float => java.lang.Float
  //   case Double => java.lang.Double
  //   case Boolean => java.lang.Boolean
  //   case _ => T
  // }
  // type VarToSignalType2[T] = Null match {
  //     case T => util.NotNull[T]
  //     case _ => T
  // }

  def connectVar[T, U](v: Var[T], signal: QObject#Signal1[U])(using VarToSignalTypeAdapter[T, U], ValueOf[v.ForInstance]): Unit = {
    val slot: QMetaObject.Slot1[U] = v match {
      case _: ExternalVar[T] => value => update(summon[VarContext].externalPropertyUpdated(v, None))
      case _ => value => update(v := value.asInstanceOf[T])
    }
    
    signal.nn.connect(slot, Qt.ConnectionType.DirectConnection)
  }
  /** Connects a signal to an emitter */
  def connectEmitter[T, U](emitter: Emitter[T], signal: QObject#Signal1[U])(using VarToSignalTypeAdapter[T, U], ValueOf[emitter.ForInstance]): Unit = {
    val slot: QMetaObject.Slot1[U] = value => update(summon[Emitter.Context].emit(emitter, value.asInstanceOf[T]))
    signal.connect(slot, Qt.ConnectionType.DirectConnection)
  }

  val applicationState = Var.autoName[Qt.ApplicationState](Qt.ApplicationState.ApplicationInactive)

  private case class RunThunk(f: () => Any) extends QEvent(QEvent.Type.User)
  private class ToolkitApplication(args: Array[String | Null] | Null) extends QApplication(args) {
    override def event(evt: QEvent | Null): Boolean = {
      evt match {
        case RunThunk(f) =>
          try f()
          catch case NonFatal(ex) => Thread.currentThread().unn.getUncaughtExceptionHandler().?(_.uncaughtException(Thread.currentThread(), ex))
          true
        case _ => super.event(evt)
      }
    }
  }
  lazy val qtAppInstance: QApplication = 
    println(s"initializing with ${initializationArgs.mkString("'", "' '", "'")}")
    val qtAppInstance = QCoreApplication.initialize(null, initializationArgs.asInstanceOf, ToolkitApplication(_)).unn
    connectVar(applicationState, qtAppInstance.applicationStateChanged.nn)
    qtAppInstance

  /////////////////////////////////
  // Timers
  /////////////////////////////////

  type Timer = QTimer
  given TimerLike: animation.TimerLike[Timer] with {

    override def apply(delay: FiniteDuration, onUpdate: Timer => Unit, onRestart: Timer => Unit): Timer = {
      val res = QTimer()
      res.timeout.unn.connect(slot(onUpdate(res)))
      res.setTimerType(TimerType.PreciseTimer)
      res.setInterval(delay.toMillis.toInt)
      res.setProperty("__guarana_qt.onRestart", onRestart)
      res
    }

    extension (t: Timer) {
      override def start(): Unit = {
        t.property("__guarana_qt.onRestart").?(_.asInstanceOf[Timer => Unit].apply(t))
        t.start()
      }
      override def stop(): Unit = t.stop()
      override def isRunning = t.isActive()
    }

  }

  trait VarToSignalTypeAdapter[T, O]
  object VarToSignalTypeAdapter {
    private inline def nullInstance[T]: T = null.asInstanceOf[T]
    inline implicit def int: VarToSignalTypeAdapter[Int, java.lang.Integer] = nullInstance
    inline implicit def long: VarToSignalTypeAdapter[Long, java.lang.Long] = nullInstance
    inline implicit def float: VarToSignalTypeAdapter[Float, java.lang.Float] = nullInstance
    inline implicit def double: VarToSignalTypeAdapter[Double, java.lang.Double] = nullInstance
    inline implicit def boolean: VarToSignalTypeAdapter[Boolean, java.lang.Boolean] = nullInstance
    inline implicit def nullableAnyRef[T]: VarToSignalTypeAdapter[T | Null, T] = nullInstance
    inline implicit def anyRef[T](using scala.util.NotGiven[Null <:< T]): VarToSignalTypeAdapter[T, T] = nullInstance
  }
}