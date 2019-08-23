package guarana

/**
 * Definition of a Desktop Manager window.
 */
trait Window {

  var title: String
  var width: Int
  var height: Int
  var iconified: Boolean
  var maximized: Boolean
  var focused: Boolean
  var decorated: Boolean
  var transparent: Boolean

  def surface: Surface

  private[this] val listeners = collection.mutable.ArrayBuffer.empty[PartialFunction[Window.Event, Unit]]
  def registerListener(listener: PartialFunction[Window.Event, Unit]): AutoCloseable = {
    listeners += listener
    () => listeners -= listener
  }

  def fireEvent(evt: Window.Event): Unit = listeners foreach (l => if (l.isDefinedAt(evt)) l(evt))
}

object Window {
  sealed trait Event
  case class MouseMoved(x: Int, y: Int) extends Event
  case class WindowSizeChanged(oldWidth: Int, oldHeight: Int, newWidth: Int, newHeight: Int) extends Event
}
