package guarana
package swing.util

import guarana.util.*
import java.awt.Image
import java.awt.image.BufferedImage
import org.apache.batik.anim.dom.SVGDOMImplementation
import org.apache.batik.transcoder.image.ImageTranscoder
import org.apache.batik.transcoder.{SVGAbstractTranscoder, TranscoderInput, TranscoderOutput, XMLAbstractTranscoder}
import org.apache.batik.util.SVGConstants

object SvgUtils {

  private class SvgTranscoder extends ImageTranscoder {
    var createdImage: BufferedImage | Null = null
    // we don't need to write this to anywhere
    override def writeImage(img: BufferedImage, output: TranscoderOutput): Unit = ()

    override def createImage(width: Int, height: Int): BufferedImage = {
      createdImage = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
        .createCompatibleImage(width, height, java.awt.Transparency.TRANSLUCENT)
      createdImage.unn
    }

  }

  def loadAsImage(uri: String, width: Opt[Float] = UnsetParam, height: Opt[Float] = UnsetParam): Image = {
    val transcoder = SvgTranscoder()
    ifSet(width, transcoder.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, _))
    ifSet(height, transcoder.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, _))
    transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation())
    transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI)
    transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG)
    transcoder.transcode(TranscoderInput(uri), null)
    transcoder.createdImage.unn
  }
}
