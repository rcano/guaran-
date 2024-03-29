package apricot
package extra

import guarana.unn
import guarana.qt.Toolkit
import io.qt.gui.{QOpenGLWindow, QSurfaceFormat}
import io.github.humbleui.skija.*

// FIXME: for reasons beyond me, qt isn't updating the window unless there's a composition event (such as window hiding, or resizing). According
// to the documentation, it should honor the update() request being done, but oh well.

/** Setup a QOpenGLWindow with Skia and bind the canvas (every time it changes) to the engine. VSync is also setup, and on each frame the
  * engine is stepped. Note that the canvas is not cleared, all work is delegated entirely to the engine
  */
@deprecated(
  "Qt refuses to update the content on update request, as per Qt's documentation, so it can't be used. Use GlfwWindow instead",
  since = "the beginning"
)
class QtSkiaWindow(engine: ApricotEngine[Toolkit]) extends QOpenGLWindow {
  var skiaDirectContext: DirectContext = compiletime.uninitialized
  var skiaSurface: Surface = compiletime.uninitialized
  var canvas: Canvas = compiletime.uninitialized
  locally {
    val fmt = QSurfaceFormat()
    fmt.setDepthBufferSize(24)
    fmt.setProfile(QSurfaceFormat.OpenGLContextProfile.CoreProfile)
    fmt.setMajorVersion(4)
    fmt.setMajorVersion(6)
    setFormat(fmt)

    setWidth(600)
    setHeight(600)

    val listener: io.qt.core.QMetaObject.Slot0 = update _
    frameSwapped.unn.connect(listener)
  }
  override def initializeGL(): Unit = {
    skiaDirectContext = DirectContext.makeGL()
  }
  override def resizeGL(w: Int, h: Int): Unit = {
    if canvas.asInstanceOf[Canvas | Null] != null then skiaSurface.close()
    val renderTarget = BackendRenderTarget.makeGL(
      w,
      h, /*samples*/ 0, /*stencil*/ 8,
      context.unn.defaultFramebufferObject,
      FramebufferFormat.GR_GL_RGBA8
    )

    skiaSurface = Surface.makeFromBackendRenderTarget(
      skiaDirectContext,
      renderTarget,
      SurfaceOrigin.BOTTOM_LEFT,
      SurfaceColorFormat.RGBA_8888,
      ColorSpace.getSRGB()
    )

    canvas = skiaSurface.getCanvas()
    // engine.setRenderingSurface(skiaSurface, canvas)
  }
  override def paintGL(): Unit = {
    new Exception().printStackTrace()
    engine.engineStep()
  }
}
