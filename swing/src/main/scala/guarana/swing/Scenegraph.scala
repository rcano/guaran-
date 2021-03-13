package guarana.swing

import language.implicitConversions
import javax.swing.SwingUtilities
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.Duration
import scala.util.Try
import guarana.swing.impl.{CopyOnWriteEmitterStation, CopyOnWriteSignalSwitchboard, EmitterStation, SignalSwitchboard}

object Scenegraph {
  type ContextAction[+R] = VarContext & Emitter.Context ?=> R
}
class Scenegraph {

  type Signal[+T] = ObsVal[T]
  private[this] var switchboard = SignalSwitchboard[Signal](reporter)
  private[this] var emitterStation = EmitterStation()
  var stylist: Stylist = Stylist.NoOp

  private val systemEm: Double = plaf.CssLaf.fontDeterminedByOs.map(_.getSize2D.toDouble).getOrElse(14)

  val emSize = Var[Double]("emSize", systemEm).forInstance(Scenegraph.this) 

  private[this] val threadLocalContext = new ThreadLocal[ContextImpl] {
    override def initialValue = null
  }

  def update[R](f: Scenegraph ?=> Scenegraph.ContextAction[R]): R = Await.result(updateAsync(f), Duration.Inf)
  def updateAsync[R](f: Scenegraph ?=> Scenegraph.ContextAction[R]): Future[R] = {
    val res = Promise[R]()
    def impl = res.complete(Try {
      var locallyCreatedContext = false
      var ctx = threadLocalContext.get()
      if (ctx == null) {
        ctx = new ContextImpl(CopyOnWriteSignalSwitchboard(switchboard, reporter), CopyOnWriteEmitterStation(emitterStation))
        threadLocalContext.set(ctx)
        locallyCreatedContext = true
      }
      val res = f(using this)(using ctx)
      if (locallyCreatedContext) {
        switchboard = ctx.switchboard.theInstance
        emitterStation = ctx.emitterStation.theInstance
        threadLocalContext.set(null)
      }
      res
    })

    if (SwingUtilities.isEventDispatchThread) impl
    else {
      val r: Runnable = () => impl
      SwingUtilities.invokeLater(r)
    }
    
    res.future
  }
  
  private val reactingSwingVars = collection.mutable.HashSet.empty[Keyed[Signal[_]]]
  /** Executes the given thunk preventing swing property changes from being propagated to the signals.
    * Reason: When computing the values for swing properties, they'll notify that they were change, and we want to avoid resetting the value
    * and discarding the user provided Binding.
    */
  private def reactingToVar[R](k: Keyed[Signal[_]])(f: => R): R = {
    reactingSwingVars += k
    val res = f
    reactingSwingVars -= k
    res
  }
  private object reporter extends SignalSwitchboard.Reporter[Signal] {

    private def withContext(f: ContextImpl => Any): Unit = {
      val ctx = threadLocalContext.get()
      if (ctx != null) //during scenegraph bootstrapping, context is null
        f(ctx)
    }

    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit = ()
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]) = {
      //swing keys need to be eagerly computed
      s.keyed match {
        case v: Var[_] if v.eagerEvaluation => withContext { ctx =>
          reactingToVar(s) { ctx.switchboard(s) }
        }
        case _ =>
      }
    }

    def signalUpdated[T](sb: SignalSwitchboard[Signal], s: Keyed[Signal[T]], oldValue: Option[T], newValue: T, dependencies: collection.Set[Keyed[Signal[_]]], dependents: collection.Set[Keyed[Signal[_]]]): Unit = {
      withContext { ctx =>
        given Emitter.Context = ctx
        ctx.emit(s.instance.varUpdates, VarValueChanged(s, oldValue, newValue))
      }
    }
  }

  /** Stylist.ScenegraphInfo for this scenegraph.
    * You don't typically access this instance, since when you are implementing a Stylist
    * it is passed to you when query properties.
    * It's public mainly to be used by LaF implementors.
    */ 
  val scenegraphInfo = new Stylist.ScenegraphInfo {
    def get[T](property: Keyed[ObsVal[T]]): Option[T] = switchboard.get(property)
    def emSize: Double = switchboard.get(ObsVal.obs2Keyed(Scenegraph.this.emSize)(ValueOf(Scenegraph.this)))
      .getOrElse(Scenegraph.this.emSize.initialValue(Scenegraph.this))
  }

  private class SwitchboardAsVarContext(switchboard: SignalSwitchboard[Signal]) extends VarContext {
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      switchboard.get(v) orElse stylist(scenegraphInfo)(v) getOrElse v.initialValue(instance.value)
    }
    def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      binding match {
        case Binding.Const(c) => switchboard(v) = c()
        case Binding.Compute(c) => switchboard.bind(v)(sb => c(new SwitchboardAsVarContext(sb)))
      }
    }
    def externalPropertyUpdated[T](v: Var[T], value: T)(using instance: ValueOf[v.ForInstance]): Unit = {
      if (!reactingSwingVars(v) && !switchboard.get(v).exists(_ == value)) {
        switchboard(v) = value
      }
    }
  }

  /**
   * ContextImpl uses a snapshotted switchboard so the changes can be applied: they are seen (this is in order to properly implement sequence in code)
   * After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our tracking.
   */
  private class ContextImpl(
    val switchboard: CopyOnWriteSignalSwitchboard[Signal],
    var emitterStation: CopyOnWriteEmitterStation,
  ) extends SwitchboardAsVarContext(switchboard) with Emitter.Context {

    //Emitter.Context

    def emit[A](emitter: Emitter[A], evt: A)(using instance: ValueOf[emitter.ForInstance]): Unit =
      emitterStation.emit(emitter, evt)(using summon, this)
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit =
      emitterStation.listen(emitter)(f)
  }

  /** Read-only view of the current state of vars value in the scenegraph */
  object stateReader {
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      switchboard.get(v) orElse stylist(scenegraphInfo)(v) getOrElse v.initialValue(instance.value)
    }
  }

  object awtInputListener extends javax.swing.event.MouseInputListener, java.awt.event.KeyListener {
    // Members declared in java.awt.event.KeyListener
    private def keyEvent(rawEvent: java.awt.event.KeyEvent, evt: KeyEvent): Unit = {
      val source = rawEvent.getSource()
      val emitter = Node.KeyEvents.forInstance(source)
      if (emitterStation.hasEmitter(emitter)) update(summon[Emitter.Context].emit(emitter, evt))
    }
    def keyPressed(evt: java.awt.event.KeyEvent): Unit = keyEvent(evt.nn, KeyPressed(evt.nn))
    def keyReleased(evt: java.awt.event.KeyEvent): Unit = keyEvent(evt.nn, KeyReleased(evt.nn))
    def keyTyped(evt: java.awt.event.KeyEvent): Unit = keyEvent(evt.nn, KeyTyped(evt.nn))
    
    // Members declared in java.awt.event.MouseListener
    private def mouseEvent(rawEvent: java.awt.event.MouseEvent, evt: MouseEvent): Unit = {
      val source = rawEvent.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (emitterStation.hasEmitter(emitter)) update(summon[Emitter.Context].emit(emitter, evt))
    }
    def mouseClicked(evt: java.awt.event.MouseEvent): Unit = mouseEvent(evt.nn, MouseClicked(evt.nn))
    def mouseEntered(evt: java.awt.event.MouseEvent): Unit = update {
      mouseEvent(evt.nn, MouseEntered(evt.nn))
      val source = evt.getSource()
      val hovered = Node.HoveredMut.forInstance(source)
      hovered := true
    }
    def mouseExited(evt: java.awt.event.MouseEvent): Unit = update {
      mouseEvent(evt.nn, MouseExited(evt.nn))
      val source = evt.getSource()
      val hovered = Node.HoveredMut.forInstance(source)
      hovered := false
    }
    def mousePressed(evt: java.awt.event.MouseEvent): Unit = mouseEvent(evt.nn, MousePressed(evt.nn))

    /** variable for tracking dragging. Only one is needed because only one node can be
        dragged at any given time */
    private var dragStart: Option[java.awt.event.MouseEvent] = None

    // Members declared in java.awt.event.MouseMotionListener
    def mouseDragged(evt: java.awt.event.MouseEvent): Unit = update {
      // manually inline mouseEvent since we are running update anyway 
      val source = evt.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (emitterStation.hasEmitter(emitter)) summon[Emitter.Context].emit(emitter, MouseDragged(evt.nn))
      dragStart match {
        case None => dragStart = Some(evt.nn)
        case Some(origin) => Node.MouseDragMut.forInstance(source) :=
          Some(MouseDrag(
            MousePosition(origin.getX, origin.getY, origin.getXOnScreen, origin.getYOnScreen),
            MousePosition(evt.getX, evt.getY, evt.getXOnScreen, evt.getYOnScreen),
            false,
            evt.nn,
            origin
          ))
      }
    }

    def mouseReleased(evt: java.awt.event.MouseEvent): Unit = update {
      // manually inline mouseEvent since we are running update anyway 
      val source = evt.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (emitterStation.hasEmitter(emitter)) summon[Emitter.Context].emit(emitter, MouseReleased(evt.nn))
      dragStart match {
        case None =>
        case Some(origin) => 
          Node.MouseDragMut.forInstance(source) :=
            Some(MouseDrag(
              MousePosition(origin.getX, origin.getY, origin.getXOnScreen, origin.getYOnScreen),
              MousePosition(evt.getX, evt.getY, evt.getXOnScreen, evt.getYOnScreen),
              true,
              evt.nn,
              origin
            ))
          dragStart = None
      }
    }
    def mouseMoved(evt: java.awt.event.MouseEvent): Unit = update {
      // manually inline mouseEvent since we are running update anyway 
      val source = evt.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (emitterStation.hasEmitter(emitter)) summon[Emitter.Context].emit(emitter, MouseMoved(evt.nn))
      Node.MouseDragMut.forInstance(source) := None
    }

  }
}
