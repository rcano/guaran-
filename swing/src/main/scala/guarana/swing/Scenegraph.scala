package guarana
package swing

import language.implicitConversions
import javax.swing.SwingUtilities
import scala.concurrent.duration.FiniteDuration
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeEvent
import javax.swing.UIManager

trait SwingTimers extends animation.TimersDef {
  type Timer = javax.swing.Timer
  given TimerLike: animation.TimerLike[Timer] with {

    override def apply(delay: FiniteDuration, onUpdate: Timer => Unit, onRestart: Timer => Unit): Timer = {
      val res = new javax.swing.Timer(delay.toMillis.toInt, null) {
        override def start(): Unit = {
          onRestart(this)
          super.start()
        }
      }
      val al: java.awt.event.ActionListener = _ => onUpdate(res)
      res.addActionListener(al)
      res
    }
    extension (t: Timer) {
      override def start(): Unit = t.start()
      override def stop(): Unit = t.stop()
      override def restart(): Unit = t.restart()
      override def isRunning = t.isRunning()
    }
  }
}

object Scenegraph extends SwingTimers {
}
class Scenegraph extends AbstractToolkit, SwingTimers {

  def timerDefs = this
  protected def isOnToolkitThread() = SwingUtilities.isEventDispatchThread
  protected def runOnToolkitThread(r: () => Any): Unit = SwingUtilities.invokeLater(() => r())

  private val systemEm: Double = plaf.CssLaf.fontDeterminedByOs.map(_.getSize2D.toDouble).getOrElse(14)
  val emSize = Var[Double]("emSize", systemEm).forInstance[this.type]

  extension (sr: stateReader.type) {
    def emSize: Double =
      val prop = Scenegraph.this.emSize
      sr.getOrDefault(prop)(using ValueOf(Scenegraph.this))
  }

  private val sysMetrics = Var[Stylist.Metrics]("metrics", Stylist.Metrics.NoOp)
  private val fontMetrics = new Stylist.FontMetrics {
    val peer = new javax.swing.JPanel()
    def ascent(font: Any): Double = peer.getFontMetrics(font.asInstanceOf).unn.getAscent
    def descent(font: Any): Double = peer.getFontMetrics(font.asInstanceOf).unn.getDescent
    def stringWidth(font: Any, s: String): Double = peer.getFontMetrics(font.asInstanceOf).unn.charsWidth(s.toCharArray, 0, s.length)
  }

  update {
    sysMetrics := Binding.dyn {
      val currSize = emSize()(using ValueOf(this))
      Stylist.Metrics(currSize, -1, -1, fontMetrics)
    }
  }

  UIManager.getDefaults().addPropertyChangeListener(fontsDefaultsChangeListener)

  def getMetrics(): Stylist.Metrics = stateReader.getOrDefault(sysMetrics)

  object awtInputListener extends javax.swing.event.MouseInputListener, java.awt.event.KeyListener {
    // Members declared in java.awt.event.KeyListener
    private def keyEvent(rawEvent: java.awt.event.KeyEvent, evt: KeyEvent): Unit = {
      val source = rawEvent.getSource()
      val emitter = Node.KeyEvents.forInstance(source)
      if (stateReader.hasEmitter(emitter)) update(summon[VarContext].emit(emitter, evt))
    }
    def keyPressed(evt: java.awt.event.KeyEvent): Unit = keyEvent(evt.nn, KeyPressed(evt.nn))
    def keyReleased(evt: java.awt.event.KeyEvent): Unit = keyEvent(evt.nn, KeyReleased(evt.nn))
    def keyTyped(evt: java.awt.event.KeyEvent): Unit = keyEvent(evt.nn, KeyTyped(evt.nn))

    // Members declared in java.awt.event.MouseListener
    private def mouseEvent(rawEvent: java.awt.event.MouseEvent, evt: MouseEvent): Unit = {
      val source = rawEvent.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (stateReader.hasEmitter(emitter)) update(summon[VarContext].emit(emitter, evt))
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

    /** variable for tracking dragging. Only one is needed because only one node can be dragged at any given time
      */
    private var dragStart: Option[java.awt.event.MouseEvent] = None

    // Members declared in java.awt.event.MouseMotionListener
    def mouseDragged(evt: java.awt.event.MouseEvent): Unit = update {
      // manually inline mouseEvent since we are running update anyway
      val source = evt.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (stateReader.hasEmitter(emitter)) summon[VarContext].emit(emitter, MouseDragged(evt.nn))
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
      if (stateReader.hasEmitter(emitter)) summon[VarContext].emit(emitter, MouseReleased(evt.nn))
      dragStart match {
        case None =>
        case Some(origin) =>
          Node.MouseDragMut.forInstance(source) :=
            Some(
              MouseDrag(
                MousePosition(origin.getX, origin.getY, origin.getXOnScreen, origin.getYOnScreen),
                MousePosition(evt.getX, evt.getY, evt.getXOnScreen, evt.getYOnScreen),
                true,
                evt.nn,
                origin
              )
            )
          dragStart = None
          SwingUtilities.invokeLater(() => update(Node.MouseDragMut.forInstance(source) := None))
      }
    }
    def mouseMoved(evt: java.awt.event.MouseEvent): Unit = update {
      // manually inline mouseEvent since we are running update anyway
      val source = evt.getSource()
      val emitter = Node.MouseEvents.forInstance(source)
      if (stateReader.hasEmitter(emitter)) summon[VarContext].emit(emitter, MouseMoved(evt.nn))
      // Node.MouseDragMut.forInstance(source) := None
    }

  }

  private object fontsDefaultsChangeListener extends PropertyChangeListener {

    override def propertyChange(evt: PropertyChangeEvent): Unit = {
      evt.getPropertyName() match {
        case "Panel.font" => update(summon[VarContext].externalPropertyUpdated(Fonts.Base, Option(evt.getOldValue())))
        case "TextField.font" => update(summon[VarContext].externalPropertyUpdated(Fonts.Input, Option(evt.getOldValue())))
        case "Label.font" => update(summon[VarContext].externalPropertyUpdated(Fonts.Label, Option(evt.getOldValue())))
        case Color.themeColorVars(colorVar) => update(summon[VarContext].externalPropertyUpdated(colorVar, Option(evt.getOldValue())))
        case _ => 
      }
    }

  }
}
