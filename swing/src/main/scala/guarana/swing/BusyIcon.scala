package guarana
package swing

import guarana.animation.Timeline
import guarana.util.*
import java.awt.geom.AffineTransform
import org.apache.batik.anim.dom.SAXSVGDocumentFactory
import org.apache.batik.swing.JSVGCanvas
import org.apache.batik.util.XMLResourceDescriptor
import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.util.Using
import scala.util.chaining.*
import org.apache.batik.swing.svg.JSVGComponent

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
  )(using Scenegraph): ToolkitAction[Component] = {
    import iconDefinition.*
    if (!icon.endsWith(".svg")) throw IllegalStateException("Only svg are supported")

    val svgc = JSVGComponent()
    svgc.setRecenterOnResize(true)
    svgc.setBackground(Color.Transparent)
    val res = Component.wrap(svgc).tap(Component.init)

    // manual load the document because batik fails to parse some jar urls that do work. Anyway, just fake the uri so that it always works.
    val factory = SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName())
    val doc = Using(java.net.URI.create(icon).toURL().openStream())(in => factory.createSVGDocument("icon.svg", in)).get
    doc
      .getRootElement()
      .getViewBox()
      .?(rect => svgc.setPreferredSize(java.awt.Dimension(rect.getBaseVal().getWidth().toInt, rect.getBaseVal().getHeight().toInt)))
    svgc.setSVGDocument(doc)
    ifSet(maxSize, res.maxSize := _)
    ifSet(minSize, res.minSize := _)
    ifSet(prefSize, res.prefSize := _)
    ifSet(visible, res.visible := _)

    var frame = 0L
    val timeline = Timeline(
      IArray(Timeline.after(interval) {
        svgc.setRenderingTransform(AffineTransform.getRotateInstance(rotation * frame, svgc.getWidth() / 2, svgc.getHeight() / 2), true)
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
