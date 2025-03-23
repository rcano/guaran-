package guarana
package swing.util

import guarana.util.*
import java.awt.Image
import java.awt.image.BufferedImage
import com.github.weisj.jsvg.parser.SVGLoader

object SvgUtils {

  def loadAsImage(uri: String, width: Opt[Float] = UnsetParam, height: Opt[Float] = UnsetParam): Image = {
    val loader = new SVGLoader()
    val loaded = loader.load(java.net.URI.create(uri).toURL())
    val shape = loaded.computeShape()
    val w = width.getOrElse(shape.getBounds2D().getWidth().toFloat).toInt
    val h = width.getOrElse(shape.getBounds2D().getHeight().toFloat).toInt
    
    val res = BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
    loaded.render(null, res.createGraphics())
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
