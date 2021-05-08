package guarana
package qt

import io.qt.core.Qt
import io.qt.gui.QScreen

object WindowEmMetrics {
  def apply(widget: Widget): EmMetrics = {
    val em = ExternalObsVal[Widget, Double]("em", w => w.unwrap.fontMetrics.nn.horizontalAdvance('M'))
      .asInstanceOf[ObsVal.Aux[Double, widget.type]]

    def setupWindowListener() = 
      widget.unwrap.windowHandle.nn.screenChanged.nn.connect(slot((w: QScreen) => 
        Toolkit.update(summon[VarContext].externalPropertyUpdated(em, None))
      ))
    widget.windowHandle match {
      case Some(window) => setupWindowListener()
      case _ =>
        Toolkit.update {
          Toolkit.applicationState.varUpdates := EventIterator
            .filter[VarValueChanged[ObsVal]](_.curr == Qt.ApplicationState.ApplicationActive)
            .take(1)
            .foreach(_ => setupWindowListener())
        }
    }
    EmMetrics(em)
  }
}
