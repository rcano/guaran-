package guarana
package qt

import io.qt.core._
import io.qt.widgets._
import guarana.util.*

opaque type Layout = QLayout
object Layout {
  val Alignment = ExternalVar[Layout, Qt.Alignment | Null]("alignment", _.alignment, _.setAlignment(_), true)
  val ContentsMargins = ExternalVar[Layout, Insets]("contentsMargins", _.contentsMargins().nn.toInsets, (c, v) => c.setContentsMargins(v.toQMargins), true)
  val MenuBar = ExternalVar[Layout, Widget | Null]("menuBar", _.menuBar, (c, v) => c.setMenuBar(v ? (_.unwrap)), true)
  val SizeConstraint = ExternalVar[Layout, QLayout.SizeConstraint]("sizeConstraint", _.sizeConstraint.nn, _.setSizeConstraint(_), true)
  val WidgetSpacing = ExternalVar[Layout, Double]("widgetSpacing", _.widgetSpacing, (c, v) => c.setWidgetSpacing(v.round.toInt), true)


  given Ops: AnyRef with {
    extension (l: Layout) {
      def alignment = Alignment.forInstance(l)
      def contentsMargins = ContentsMargins.forInstance(l)
      def menuBar = MenuBar.forInstance(l)
      def sizeConstraint = SizeConstraint.forInstance(l)
      def widgetSpacing = WidgetSpacing.forInstance(l)

      def activate() = l.activate()
      def invalidate() = l.invalidate()
      def update() = l.update()
    }
  }
}

opaque type BoxLayout <: Layout = QBoxLayout
object BoxLayout {
  val Spacing = ExternalVar[QBoxLayout, Double]("spacing", _.spacing, (c, v) => c.setSpacing(v.round.toInt), true)
  val Nodes: Var[Seq[Widget | LayoutItem]] = Var[Seq[Widget | LayoutItem]]("nodes", Seq.empty, true)

  given Ops: AnyRef with {
    extension (l: Layout) {
      def spacing = Spacing.forInstance(l)
      def nodes = Nodes.forInstance(l)
    }
  }
  def wrap(v: QBoxLayout): BoxLayout = v

  def init(v: BoxLayout): Unit = {
    Toolkit.update {
      v.varUpdates := EventIterator.foreach {
        case v.nodes(oldv, newv) =>
          var oldItem: QLayoutItem | Null = null
          while {oldItem = v.takeAt(0); oldItem != null} do oldItem.dispose()
          newv.foreach {
            case w: QWidget => v.addWidget(w)
            case LayoutItem.Space(s) => v.addSpacing(s().toInt)
            case LayoutItem.Stretch(s) => v.addStretch(s().toInt)
          }
        case _ =>
      }
    }
  }
}

opaque type HBoxLayout <: BoxLayout = QHBoxLayout 
object HBoxLayout {
  def wrap(v: QHBoxLayout): HBoxLayout = v
  def apply(
    widget: Opt[Widget] = UnsetParam,
    alignment: Opt[Binding[Qt.Alignment | Null]] = UnsetParam,
    contentsMargins: Opt[Binding[Insets]] = UnsetParam,
    menuBar: Opt[Binding[Widget | Null]] = UnsetParam,
    sizeConstraint: Opt[Binding[QLayout.SizeConstraint]] = UnsetParam,
    widgetSpacing: Opt[Binding[Double]] = UnsetParam,
    spacing: Opt[Binding[Double]] = UnsetParam,
    nodes: Opt[Binding[Seq[Widget | LayoutItem]]] = UnsetParam,
  ): ToolkitAction[HBoxLayout] = {
    val res = widget match
      case UnsetParam => QHBoxLayout()
      case w: QWidget => QHBoxLayout(w)

    BoxLayout.init(res)
    ifSet(alignment, Layout.Alignment.forInstance(res) := _)
    ifSet(contentsMargins, Layout.ContentsMargins.forInstance(res) := _)
    ifSet(menuBar, Layout.MenuBar.forInstance(res) := _)
    ifSet(sizeConstraint, Layout.SizeConstraint.forInstance(res) := _)
    ifSet(widgetSpacing, Layout.WidgetSpacing.forInstance(res) := _)
    ifSet(spacing, BoxLayout.Spacing.forInstance(res) := _)
    ifSet(nodes, BoxLayout.Nodes.forInstance(res) := _)
    res
  }
}
opaque type VBoxLayout <: BoxLayout = QVBoxLayout 
object VBoxLayout {
  def wrap(v: QVBoxLayout): VBoxLayout = v
  def apply(
    widget: Opt[Widget] = UnsetParam,
    alignment: Opt[Binding[Qt.Alignment | Null]] = UnsetParam,
    contentsMargins: Opt[Binding[Insets]] = UnsetParam,
    menuBar: Opt[Binding[Widget | Null]] = UnsetParam,
    sizeConstraint: Opt[Binding[QLayout.SizeConstraint]] = UnsetParam,
    widgetSpacing: Opt[Binding[Double]] = UnsetParam,
    spacing: Opt[Binding[Double]] = UnsetParam,
    nodes: Opt[Binding[Seq[Widget | LayoutItem]]] = UnsetParam,
  ): ToolkitAction[VBoxLayout] = {
    val res = widget match
      case UnsetParam => QVBoxLayout()
      case w: QWidget => QVBoxLayout(w)

    BoxLayout.init(res)
    ifSet(alignment, Layout.Alignment.forInstance(res) := _)
    ifSet(contentsMargins, Layout.ContentsMargins.forInstance(res) := _)
    ifSet(menuBar, Layout.MenuBar.forInstance(res) := _)
    ifSet(sizeConstraint, Layout.SizeConstraint.forInstance(res) := _)
    ifSet(widgetSpacing, Layout.WidgetSpacing.forInstance(res) := _)
    ifSet(spacing, BoxLayout.Spacing.forInstance(res) := _)
    ifSet(nodes, BoxLayout.Nodes.forInstance(res) := _)
    res
  }
}

opaque type FormLayout <: Layout = QFormLayout 
object FormLayout {
  val Spacing = ExternalVar[FormLayout, Double]("spacing", _.spacing, (c, v) => c.setSpacing(v.round.toInt), true)
  val FieldGrowthPolicy = ExternalVar[FormLayout, QFormLayout.FieldGrowthPolicy]("fieldGrowthPolicy", _.fieldGrowthPolicy.nn, _.setFieldGrowthPolicy(_), true)
  val Rows: Var[Seq[(Widget, Widget)]] = Var[Seq[(Widget, Widget)]]("rows", Seq.empty, true)
  given Ops: AnyRef with {
    extension (l: Layout) {
      def spacing = Spacing.forInstance(l)
      def rows = Rows.forInstance(l)
    }
  }
  def wrap(v: QFormLayout): FormLayout = v
  def init(v: FormLayout): Unit = {
    Toolkit.update {
      v.varUpdates := EventIterator.foreach {
        case v.rows(oldv, newv) =>
          var oldItem: QFormLayout.TakeRowResult | Null = null
          while {oldItem = v.takeRow(0); oldItem != null} do oldItem.dispose()
          newv.foreach((label, node) => v.addRow(label.unwrap, node.unwrap))
        case _ =>
      }
    }
  }

  def apply(
    widget: Opt[Widget] = UnsetParam,
    alignment: Opt[Binding[Qt.Alignment | Null]] = UnsetParam,
    contentsMargins: Opt[Binding[Insets]] = UnsetParam,
    menuBar: Opt[Binding[Widget | Null]] = UnsetParam,
    sizeConstraint: Opt[Binding[QLayout.SizeConstraint]] = UnsetParam,
    widgetSpacing: Opt[Binding[Double]] = UnsetParam,
    spacing: Opt[Binding[Double]] = UnsetParam,
    fieldGrowthPolicy: Opt[Binding[QFormLayout.FieldGrowthPolicy]] = UnsetParam,
    rows: Opt[Binding[Seq[(Widget, Widget)]]] = UnsetParam,
  ): ToolkitAction[FormLayout] = {
    val res = widget match
      case UnsetParam => QFormLayout()
      case w: QWidget => QFormLayout(w)

    ifSet(alignment, Layout.Alignment.forInstance(res) := _)
    ifSet(contentsMargins, Layout.ContentsMargins.forInstance(res) := _)
    ifSet(menuBar, Layout.MenuBar.forInstance(res) := _)
    ifSet(sizeConstraint, Layout.SizeConstraint.forInstance(res) := _)
    ifSet(widgetSpacing, Layout.WidgetSpacing.forInstance(res) := _)
    ifSet(spacing, FormLayout.Spacing.forInstance(res) := _)
    ifSet(fieldGrowthPolicy, FormLayout.FieldGrowthPolicy.forInstance(res) := _)
    ifSet(rows, FormLayout.Rows.forInstance(res) := _)
    res
  }
}
