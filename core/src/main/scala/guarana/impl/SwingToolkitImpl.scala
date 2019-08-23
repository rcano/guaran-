package guarana
package impl

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.swing._

object SwingToolkitImpl extends Toolkit {

  def createSurface(width: Int, height: Int): Surface = new BufferedImageSurface(width, height)


  def createWindow(): Window = new AwtWindow

  private class BufferedImageSurface(val width: Int, val height: Int) extends Surface {
    val image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    def getPixels(arr: Array[Int]) = image.getRGB(0, 0, width, height, arr, 0, width)
    def setPixels(arr: Array[Int]) = image.setRGB(0, 0, width, height, arr, 0, width)
    def update(f: Graphics2D => Any): Unit = f(image.createGraphics)
  }

  private class AwtWindow extends Window {
    val peer = new JFrame()
    def title = peer.getTitle
    def title_=(t: String) = peer.setTitle(t)
    def width = peer.getWidth
    def width_=(w: Int) = peer.setSize(w, peer.getHeight)
    def height = peer.getHeight
    def height_=(h: Int) = peer.setSize(peer.getWidth, h)
    def iconified = (peer.getExtendedState | java.awt.Frame.ICONIFIED) == java.awt.Frame.ICONIFIED
    def iconified_=(b: Boolean) = peer.setExtendedState(peer.getExtendedState | java.awt.Frame.ICONIFIED)
    def maximized = (peer.getExtendedState | java.awt.Frame.MAXIMIZED_BOTH) == java.awt.Frame.MAXIMIZED_BOTH
    def maximized_=(b: Boolean) = peer.setExtendedState(peer.getExtendedState | java.awt.Frame.MAXIMIZED_BOTH)
    def focused = peer.isFocused
    def focused_=(b: Boolean) = peer.requestFocus()
    def decorated = !peer.isUndecorated
    def decorated_=(b: Boolean) = peer.setUndecorated(!b)
    def transparent = !peer.isOpaque
    def transparent_=(b: Boolean) = peer.setOpacity(if (b) 0 else 1)

    object surface extends Surface {
      def width = AwtWindow.this.width
      def height = AwtWindow.this.height

      var image = new BufferedImageSurface(width, height)

      def getPixels(arr: Array[Int]) = image.getPixels(arr)
      def setPixels(arr: Array[Int]) = image.setPixels(arr)
      def update(f: Graphics2D => Any): Unit = {
        image.update(f)
        if (SwingUtilities.isEventDispatchThread) peer.getRootPane.getGraphics.asInstanceOf[Graphics2D].drawImage(image.image, 0, 0, null)
        else SwingUtilities.invokeAndWait(() => peer.getRootPane.getGraphics.asInstanceOf[Graphics2D].drawImage(image.image, 0, 0, null))
      }
    }

    registerListener {
      case Window.WindowSizeChanged(_, _, neww, newh) => surface.image = new BufferedImageSurface(neww, newh)
    }
  }
}
