
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type Window   = io.qt.gui.QWindow
object Window {
  private val WindowInitialized: Var[Boolean] = Var[Boolean]("WindowInitialized", false, false)
  val Active: ExternalObsVal.Aux[Window, Boolean] = ExternalObsVal[Window, Boolean]("active", _.isActive())
  private val ContentOrientation: ExternalVar.Aux[Window, io.qt.core.Qt.ScreenOrientation] = ExternalVar[Window, io.qt.core.Qt.ScreenOrientation]("contentOrientation", _.contentOrientation().unn, (_, _) => (), true)
  val Flags: ExternalVar.Aux[Window, io.qt.core.Qt.WindowFlags | Null] = ExternalVar[Window, io.qt.core.Qt.WindowFlags | Null]("flags", _.flags(), _.setFlags(_), true)
  val Height: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("height", _.height(), _.setHeight(_), true)
  val MaximumHeight: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("maximumHeight", _.maximumHeight(), _.setMaximumHeight(_), true)
  val MaximumWidth: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("maximumWidth", _.maximumWidth(), _.setMaximumWidth(_), true)
  val MinimumHeight: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("minimumHeight", _.minimumHeight(), _.setMinimumHeight(_), true)
  val MinimumWidth: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("minimumWidth", _.minimumWidth(), _.setMinimumWidth(_), true)
  val Modality: ExternalVar.Aux[Window, io.qt.core.Qt.WindowModality] = ExternalVar[Window, io.qt.core.Qt.WindowModality]("modality", _.modality().unn, _.setModality(_), true)
  val ObjectName: ExternalVar.Aux[Window, java.lang.String | Null] = ExternalVar[Window, java.lang.String | Null]("objectName", _.objectName(), _.setObjectName(_), true)
  val Opacity: ExternalVar.Aux[Window, Double] = ExternalVar[Window, Double]("opacity", _.opacity(), _.setOpacity(_), true)
  val Title: ExternalVar.Aux[Window, java.lang.String | Null] = ExternalVar[Window, java.lang.String | Null]("title", _.title(), _.setTitle(_), true)
  val TransientParent: ExternalVar.Aux[Window, io.qt.gui.QWindow | Null] = ExternalVar[Window, io.qt.gui.QWindow | Null]("transientParent", _.transientParent(), _.setTransientParent(_), true)
  val Visibility: ExternalVar.Aux[Window, io.qt.gui.QWindow.Visibility] = ExternalVar[Window, io.qt.gui.QWindow.Visibility]("visibility", _.visibility().unn, _.setVisibility(_), true)
  val Visible: ExternalVar.Aux[Window, Boolean] = ExternalVar[Window, Boolean]("visible", _.isVisible(), _.setVisible(_), true)
  val Width: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("width", _.width(), _.setWidth(_), true)
  val X: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("x", _.x(), _.setX(_), true)
  val Y: ExternalVar.Aux[Window, Int] = ExternalVar[Window, Int]("y", _.y(), _.setY(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Window) {
      def active: ObsVal.Aux[Boolean, v.type] = Window.Active.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def flags: Var.Aux[io.qt.core.Qt.WindowFlags | Null, v.type] = Window.Flags.asInstanceOf[Var.Aux[io.qt.core.Qt.WindowFlags | Null, v.type]]
      def height: Var.Aux[Int, v.type] = Window.Height.asInstanceOf[Var.Aux[Int, v.type]]
      def maximumHeight: Var.Aux[Int, v.type] = Window.MaximumHeight.asInstanceOf[Var.Aux[Int, v.type]]
      def maximumWidth: Var.Aux[Int, v.type] = Window.MaximumWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def minimumHeight: Var.Aux[Int, v.type] = Window.MinimumHeight.asInstanceOf[Var.Aux[Int, v.type]]
      def minimumWidth: Var.Aux[Int, v.type] = Window.MinimumWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def modality: Var.Aux[io.qt.core.Qt.WindowModality, v.type] = Window.Modality.asInstanceOf[Var.Aux[io.qt.core.Qt.WindowModality, v.type]]
      def objectName: Var.Aux[java.lang.String | Null, v.type] = Window.ObjectName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def opacity: Var.Aux[Double, v.type] = Window.Opacity.asInstanceOf[Var.Aux[Double, v.type]]
      def title: Var.Aux[java.lang.String | Null, v.type] = Window.Title.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def transientParent: Var.Aux[io.qt.gui.QWindow | Null, v.type] = Window.TransientParent.asInstanceOf[Var.Aux[io.qt.gui.QWindow | Null, v.type]]
      def visibility: Var.Aux[io.qt.gui.QWindow.Visibility, v.type] = Window.Visibility.asInstanceOf[Var.Aux[io.qt.gui.QWindow.Visibility, v.type]]
      def visible: Var.Aux[Boolean, v.type] = Window.Visible.asInstanceOf[Var.Aux[Boolean, v.type]]
      def width: Var.Aux[Int, v.type] = Window.Width.asInstanceOf[Var.Aux[Int, v.type]]
      def x: Var.Aux[Int, v.type] = Window.X.asInstanceOf[Var.Aux[Int, v.type]]
      def y: Var.Aux[Int, v.type] = Window.Y.asInstanceOf[Var.Aux[Int, v.type]]

      

      def disposeLater() = v.disposeLater()
      def requestActivate() = v.requestActivate()
      def setVisible(arg0: Boolean) = v.setVisible(arg0)
      def show() = v.show()
      def hide() = v.hide()
      def showMinimized() = v.showMinimized()
      def showMaximized() = v.showMaximized()
      def showFullScreen() = v.showFullScreen()
      def showNormal() = v.showNormal()
      def close() = v.close()
      def raise() = v.raise()
      def lower() = v.lower()
      def startSystemResize(edges: io.qt.core.Qt.Edges | Null) = v.startSystemResize(edges)
      def startSystemMove() = v.startSystemMove()
      def setTitle(arg0: java.lang.String | Null) = v.setTitle(arg0)
      def setX(arg0: Int) = v.setX(arg0)
      def setY(arg0: Int) = v.setY(arg0)
      def setWidth(arg0: Int) = v.setWidth(arg0)
      def setHeight(arg0: Int) = v.setHeight(arg0)
      def setGeometry(posx: Int, posy: Int, w: Int, h: Int) = v.setGeometry(posx, posy, w, h)
      def setGeometry(rect: io.qt.core.QRect | Null) = v.setGeometry(rect)
      def setMinimumWidth(arg0: Int) = v.setMinimumWidth(arg0)
      def setMinimumHeight(arg0: Int) = v.setMinimumHeight(arg0)
      def setMaximumWidth(arg0: Int) = v.setMaximumWidth(arg0)
      def setMaximumHeight(arg0: Int) = v.setMaximumHeight(arg0)
      def alert(msec: Int) = v.alert(msec)
      def requestUpdate() = v.requestUpdate()
      def contentOrientation = ContentOrientation.asObsValIn(v)
      def unwrap: io.qt.gui.QWindow = v
    }
  }

  def wrap(v: io.qt.gui.QWindow): Window = 
    val res = v.asInstanceOf[Window]
    if !Toolkit.stateReader(WindowInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: Window): Unit = {
    
    Toolkit.connectVar(ContentOrientation.forInstance[v.type], v.contentOrientationChanged.unn)
    Toolkit.connectVar(Height.forInstance[v.type], v.heightChanged.unn)
    Toolkit.connectVar(MaximumHeight.forInstance[v.type], v.maximumHeightChanged.unn)
    Toolkit.connectVar(MaximumWidth.forInstance[v.type], v.maximumWidthChanged.unn)
    Toolkit.connectVar(MinimumHeight.forInstance[v.type], v.minimumHeightChanged.unn)
    Toolkit.connectVar(MinimumWidth.forInstance[v.type], v.minimumWidthChanged.unn)
    Toolkit.connectVar(Modality.forInstance[v.type], v.modalityChanged.unn)
    Toolkit.connectVar(Opacity.forInstance[v.type], v.opacityChanged.unn)
    Toolkit.connectVar(TransientParent.forInstance[v.type], v.transientParentChanged.unn)
    Toolkit.connectVar(Visibility.forInstance[v.type], v.visibilityChanged.unn)
    Toolkit.connectVar(Visible.forInstance[v.type], v.visibleChanged.unn)
    Toolkit.connectVar(Width.forInstance[v.type], v.widthChanged.unn)
    Toolkit.connectVar(X.forInstance[v.type], v.xChanged.unn)
    Toolkit.connectVar(Y.forInstance[v.type], v.yChanged.unn)
    Toolkit.update(WindowInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): Window = {
    val res = new io.qt.gui.QWindow()
    
    res.asInstanceOf[Window]
  }
  
  def apply(
    
    flags: Opt[Binding[io.qt.core.Qt.WindowFlags | Null]] = UnsetParam,
    height: Opt[Binding[Int]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    modality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transientParent: Opt[Binding[io.qt.gui.QWindow | Null]] = UnsetParam,
    visibility: Opt[Binding[io.qt.gui.QWindow.Visibility]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    width: Opt[Binding[Int]] = UnsetParam,
    x: Opt[Binding[Int]] = UnsetParam,
    y: Opt[Binding[Int]] = UnsetParam
  ): ToolkitAction[Window] = {
    val res = uninitialized()
    Window.init(res)
    ifSet(flags, Window.ops.flags(res) := _)
    ifSet(height, Window.ops.height(res) := _)
    ifSet(maximumHeight, Window.ops.maximumHeight(res) := _)
    ifSet(maximumWidth, Window.ops.maximumWidth(res) := _)
    ifSet(minimumHeight, Window.ops.minimumHeight(res) := _)
    ifSet(minimumWidth, Window.ops.minimumWidth(res) := _)
    ifSet(modality, Window.ops.modality(res) := _)
    ifSet(objectName, Window.ops.objectName(res) := _)
    ifSet(opacity, Window.ops.opacity(res) := _)
    ifSet(title, Window.ops.title(res) := _)
    ifSet(transientParent, Window.ops.transientParent(res) := _)
    ifSet(visibility, Window.ops.visibility(res) := _)
    ifSet(visible, Window.ops.visible(res) := _)
    ifSet(width, Window.ops.width(res) := _)
    ifSet(x, Window.ops.x(res) := _)
    ifSet(y, Window.ops.y(res) := _)
    res
  }
  
}
        