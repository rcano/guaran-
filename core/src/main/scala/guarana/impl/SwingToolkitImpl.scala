package guarana
package impl

import java.awt.{Graphics2D, GraphicsEnvironment, Image, Transparency}
import java.awt.event._
import java.awt.image.{BufferedImage, VolatileImage}
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.locks.LockSupport
import javax.swing._
import scala.util.chaining._
import scala.util.control.NonFatal

object SwingToolkitImpl extends Toolkit {

  def createSurface(width: Int, height: Int): Surface = new BufferedImageSurface(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB), width, height)


  def createWindow(): Window = new AwtWindow

  private trait J2dImageSurface extends Surface {
    def g2d: Graphics2D
    def image: Image
  }
  private class BufferedImageSurface(val image: BufferedImage, val width: Int, val height: Int) extends J2dImageSurface {
    val g2d = image.createGraphics
    g2d.clearRect(0, 0, width, height)
    def getPixels(arr: Array[Int]) = image.getRGB(0, 0, width, height, arr, 0, width)
    def setPixels(arr: Array[Int]) = image.setRGB(0, 0, width, height, arr, 0, width)
    def update(f: Graphics2D => Any): Unit = f(g2d)
  }
  private class VolatileImageSurface(val image: VolatileImage, val width: Int, val height: Int) extends J2dImageSurface {
    val g2d = image.createGraphics
    g2d.clearRect(0, 0, width, height)
    def getPixels(arr: Array[Int]) = image.getSnapshot.getRGB(0, 0, width, height, arr, 0, width)
    def setPixels(arr: Array[Int]) = ???
    def update(f: Graphics2D => Any): Unit = {
      if (image.contentsLost) image.validate(null) //FIXME: skipping the compatibility test here, which will not work for arbitrary graphic devices
      f(g2d)
    }
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

      def recreateSurface() = image = {
        peer.createVolatileImage(width, height) match {
          case null =>
            val img = GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration.
              createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT).tap(_.setAccelerationPriority(1))
              new VolatileImageSurface(img, width, height)

          case img: VolatileImage => new VolatileImageSurface(img, width, height)
          case img: BufferedImage => new BufferedImageSurface(img, width, height)
        }
      }
      var image: J2dImageSurface = _
      recreateSurface()

      def getPixels(arr: Array[Int]) = image.getPixels(arr)
      def setPixels(arr: Array[Int]) = image.setPixels(arr)

//      val observer: java.awt.image.ImageObserver = (img, flags, x, y, width, height) => true

      def update(f: Graphics2D => Any): Unit = image.synchronized {
        try {
          image.update(f)
          RenderThread.tasks.add(runtime.BoxedUnit.UNIT)
        } catch {
          case NonFatal(e) => e.printStackTrace()
        }
      }

      object RenderThread extends Thread("Guaraná Render Thread") {
        val tasks = new LinkedBlockingDeque[runtime.BoxedUnit]()
        override def run() = {
          var lastRender = System.nanoTime
          while (true) {
            tasks.takeFirst()
            LockSupport.parkNanos(5_000_000) //wait 5 millis to see if there are more updates
            val now = System.nanoTime
            if (tasks.peekFirst.eq(null) || (now - lastRender > 15_000_000)) { //nothing new was added
              def doRender() = Option(peer.getBufferStrategy) foreach { bs =>
                val g = bs.getDrawGraphics
                g.drawImage(image.image, 0, 0, null)
                g.dispose()
                bs.show()
              }
              SwingUtilities.invokeAndWait(() => doRender())
              lastRender = now
            }
          }
        }
      }
    }

    peer.getRootPane.addMouseMotionListener(new MouseMotionListener {
        def mouseMoved(evt: MouseEvent) = fireEvent(Window.MouseMoved(evt.getX, evt.getY))
        def mouseDragged(evt: MouseEvent) = ()

      })
    peer.addComponentListener(new ComponentListener {
        def componentHidden(evt: ComponentEvent): Unit = ()
        def componentMoved(evt: ComponentEvent): Unit = ()
        def componentResized(evt: ComponentEvent): Unit = fireEvent(Window.WindowSizeChanged(0, 0, width, height))
        def componentShown(evt: ComponentEvent): Unit = ()
      })


    registerListener {
      case Window.WindowSizeChanged(_, _, neww, newh) => surface.recreateSurface()
    }

    def bind(scene: Scenegraph): Unit = {
      def renderScene(): Unit = scene.update { implicit ctx =>
        val rn = scene.rootNode()
        if (rn != null) {
          surface.image.g2d.clearRect(0, 0, width, height)
          val renderFunction = rn.render()
          renderFunction(surface)
        }
      }

      scene.requestRenderPass = renderScene _

      registerListener {
        case Window.MouseMoved(x, y) => 
          scene.update { implicit ctx => scene.mutableMouseLocation := ((x,y)) }

        case Window.WindowSizeChanged(_, _, neww, newh) =>
          scene.update { implicit ctx =>
            scene.width := neww
            scene.height := newh

            renderScene()
          }

      }

      renderScene()

      peer.setVisible(true)
      peer.createBufferStrategy(2)
      surface.RenderThread.start()
    }
  }
}
