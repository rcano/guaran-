package guarana
package web

import guarana.Stylist.Metrics
import org.scalajs.dom

object Toolkit extends AbstractToolkit {

  override protected def isOnToolkitThread(): Boolean = true

  override protected def runOnToolkitThread(r: () => Any): Unit = r()

  override def getMetrics(): Metrics = Metrics.NoOp

  // there's no await functionality for promises in js, but we know that the toolkit also doesn't support multiple threads, so we
  // override and just call updateAsync and get the result straight
  override def update[R](f: this.type ?=> ToolkitAction[R]): R = {
    val res = updateAsync(f)
    res.value.get.get
  }

  private[web] val resizeObserver = new dom.ResizeObserver((entries, observer) => {
    update {
      entries.foreach(e =>
        val t = e.target
        summon[VarContext].externalPropertyUpdated(HtmlProps.Dimensions.forInstance(t), None)
      )
    }
  })
}
