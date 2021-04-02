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
  private[this] val switchboard = SignalSwitchboard[Signal](reporter)
  private[this] val emitterStation = EmitterStation()
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
        //in an ideal world, we would use a context like the following:
        // ctx = new ContextImpl(CopyOnWriteSignalSwitchboard(switchboard, reporter), CopyOnWriteEmitterStation(emitterStation))
        // this would make the scenegraph immutable, allowing you to rollback change sin case of exception.
        // Unfortunately, due to the already mutable nature of swing, once you impact a swing property, it would not matter rolling
        // back, and it doesn't  make sense to track all changes and try to undo them (even though possible) beacuse it's already
        // contrarian to how swing works anyway. Given all of this, we'll prefer speed here and hence directly mutate the
        // scenegraph state.
        ctx = ContextImpl(switchboard, emitterStation)
        threadLocalContext.set(ctx)
        locallyCreatedContext = true
      }
      val res = f(using this)(using ctx)
      if (locallyCreatedContext) {
        // in an immutable world, we would need to update our internal state:
        // switchboard = ctx.switchboard.theInstance
        // emitterStation = ctx.emitterStation.theInstance
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

  private class SwitchboardAsVarContext(switchboard: SignalSwitchboard[Signal]) extends VarContext {
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      switchboard.get(v) orElse stylist(v) getOrElse v.initialValue(instance.value)
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
    val switchboard: SignalSwitchboard[Signal],
    var emitterStation: EmitterStation,
  ) extends SwitchboardAsVarContext(switchboard) with Emitter.Context {

    //Emitter.Context

    def emit[A](emitter: Emitter[A], evt: A)(using instance: ValueOf[emitter.ForInstance]): Unit =
      emitterStation.emit(emitter, evt)(using summon, this)
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit =
      emitterStation.listen(emitter)(f)
  }

  /** Read-only view of the current state of vars value in the scenegraph */
  object stateReader {
    /** Read the current value of ObsVal as it is read by doing `prop()` within a VarContext */
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      val keyed: Keyed[ObsVal[T]] = v
      switchboard.get(keyed) orElse stylist(keyed) getOrElse v.initialValue(instance.value)
    }
    /** Reads the stored value of the property, if any.*/
    def get[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): Option[T] = switchboard.get(property)

    /** Reads the stored value of the property or its default (it doesn't asks the stylist) */
    def getOrDefault[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): T = get(property).getOrElse {
      val k = property.instance.asInstanceOf[property.keyed.ForInstance]
      property.keyed.initialValue(k)
    }

    def emSize: Double = 
      val prop = Scenegraph.this.emSize
      getOrDefault(prop)(using ValueOf(Scenegraph.this))
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
