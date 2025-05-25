package guarana
package swing

import com.github.weisj.jsvg.attributes.ViewBox
import com.github.weisj.jsvg.parser.SVGLoader
import guarana.animation.Timeline
import guarana.swing.plaf.{upgrade, withAliasing}
import guarana.util.*
import java.awt.Graphics
import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.util.chaining.*

object BusyIcon {
  case class Definition(icon: String, rotation: Double, interval: FiniteDuration)
  val DefaultDefinition = Definition(
    better.files.Resource.getUrl("guarana/busy-spinner.svg").toString,
    // "file:/home/randa/Development/guarana/swing/src/main/resources/guarana/busy-spinner.svg",
    30.toRadians,
    100.millis
  )

  def apply(
      iconDefinition: Definition = DefaultDefinition,
      maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
      minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
      prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
      visible: Opt[Binding[Boolean]] = UnsetParam,
  ): ToolkitAction[Scenegraph, Component] = {
    import iconDefinition.*
    if (!icon.endsWith(".svg")) throw IllegalStateException("Only svg are supported")

    val loader = SVGLoader()
    val svgDoc = loader.load(java.net.URI.create(icon).toURL())

    var iconRotation: Double = 0
    val iconComp = new javax.swing.JComponent {
      override protected def paintComponent(g: Graphics): Unit = {
        val g2 = g.upgrade.withAliasing
        g2.rotate(iconRotation, getWidth() / 2, getHeight() / 2)
        svgDoc.render(this: java.awt.Component, g2, new ViewBox(0, 0, getWidth().toFloat, getHeight().toFloat))
      }
    }
    iconComp.setBackground(Color.Transparent)
    val res = Component.wrap(iconComp).tap(Component.init)

    ifSet(maxSize, res.maxSize := _)
    ifSet(minSize, res.minSize := _)
    ifSet(prefSize, res.prefSize := _)
    ifSet(visible, res.visible := _)

    var frame = 0L
    val timeline = Timeline(
      IArray(Timeline.after(interval) {
        iconRotation = rotation * frame
        iconComp.repaint()
        frame += 1
      }),
      Timeline.Cycles.Infinite,
      1000 / interval.toMillis.toInt,
    )(scenegraph)

    res.varUpdates := EventIterator.forsome { case res.visible(_, visible) =>
      if (visible) timeline.timer.start() else timeline.timer.stop()
    }
    res.onFirstTimeVisible(_ =>
       timeline.timer.start())
    res
  }
}
