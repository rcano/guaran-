package guarana
package swing.util

import guarana.util.*
import java.awt.Image
import java.awt.image.BufferedImage
import com.github.weisj.jsvg.parser.SVGLoader
import com.github.weisj.jsvg.attributes.ViewBox

object SvgUtils {

  def loadAsImage(uri: String, width: Opt[Double] = UnsetParam, height: Opt[Double] = UnsetParam): Image = {
    val loader = new SVGLoader()
    val loaded = loader.load(java.net.URI.create(uri).toURL())
    val shape = loaded.computeShape()
    val bounds2d = shape.getBounds2D()
    val aspectRatio = bounds2d.getWidth() / bounds2d.getHeight()
    var w, h = 0.0
    if (width == UnsetParam && height == UnsetParam) {
      w = bounds2d.getWidth()
      h = bounds2d.getHeight()
    } else {
      w = width.getOrElse(height.get * aspectRatio)
      h = height.getOrElse(width.get / aspectRatio)
    }

    val res = BufferedImage(w.ceil.toInt, h.ceil.toInt, BufferedImage.TYPE_INT_ARGB)
    loaded.render(null, res.createGraphics(), ViewBox(0, 0, w.toFloat, h.toFloat))
    res

    // val transcoder = SvgTranscoder()
    // ifSet(width, transcoder.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, _))
    // ifSet(height, transcoder.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, _))
    // transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation())
    // transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI)
    // transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG)
    // transcoder.transcode(TranscoderInput(uri), null)
    // transcoder.createdImage.unn
  }
}
