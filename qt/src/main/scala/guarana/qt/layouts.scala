package guarana
package qt

import io.qt.core._
import io.qt.widgets._
import guarana.util.*

opaque type Layout = QLayout
object Layout {
  val Alignment = ExternalVar[Layout, Qt.Alignment | Null]("alignment", _.alignment, _.setAlignment(_), true)
  val ContentsMargins =
    ExternalVar[Layout, Insets]("contentsMargins", _.contentsMargins().nn.toInsets, (c, v) => c.setContentsMargins(v.toQMargins), true)
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
    extension (l: BoxLayout) {
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
          while { oldItem = v.takeAt(0); oldItem != null } do {
            val w = oldItem.widget().unn
            // there's no other way to make the widget go away from this control than to hide it and reparent it to null. This is a known "feature" of Qt
            w.hide()
            w.setParent(null)
            oldItem.dispose()
          }

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
  given Ops: AnyRef with {
    extension (l: HBoxLayout) {
      def unwrap: QHBoxLayout = l
    }
  }
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
    val res = (widget: @unchecked) match
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
  given Ops: AnyRef with {
    extension (l: VBoxLayout) {
      def unwrap: QVBoxLayout = l
    }
  }
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
    val res = (widget: @unchecked) match
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
  val FieldGrowthPolicy =
    ExternalVar[FormLayout, QFormLayout.FieldGrowthPolicy]("fieldGrowthPolicy", _.fieldGrowthPolicy.nn, _.setFieldGrowthPolicy(_), true)
  val Rows: Var[Seq[(Widget, Widget)]] = Var[Seq[(Widget, Widget)]]("rows", Seq.empty, true)
  given Ops: AnyRef with {
    extension (l: FormLayout) {
      def unwrap: QFormLayout = l
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
          if (v.rowCount() > 0) while { oldItem = v.takeRow(0); oldItem != null } do oldItem.dispose()
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
    val res = (widget: @unchecked) match
      case UnsetParam => QFormLayout()
      case w: QWidget => QFormLayout(w)

    init(res)
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

opaque type GridLayout <: Layout = QGridLayout
case class GridCell(node: Widget, row: Int, col: Int, rowSpan: Int = 1, colSpan: Int = 1, alignment: Qt.Alignment = Qt.Alignment(0))
object GridLayout {
  val HorizontalSpacing =
    ExternalVar[GridLayout, Double]("horizontalSpacing", _.horizontalSpacing(), (c, v) => c.setHorizontalSpacing(v.round.toInt), true)
  val VerticalSpacing =
    ExternalVar[GridLayout, Double]("verticalSpacing", _.verticalSpacing(), (c, v) => c.setVerticalSpacing(v.round.toInt), true)
  val Nodes: Var[Seq[GridCell]] = Var[Seq[GridCell]]("nodes", Seq.empty, true)

  given Ops: AnyRef with {
    extension (l: GridLayout) {
      def unwrap: QGridLayout = l
      def horizontalSpacing = HorizontalSpacing.forInstance(l)
      def verticalSpacing = VerticalSpacing.forInstance(l)
      def nodes = Nodes.forInstance(l)

      def getColumnMinWidth(col: Int): Int = l.columnMinimumWidth(col)
      def getColumnStretch(col: Int): Int = l.columnStretch(col)
      def getRowMinHeight(col: Int): Int = l.rowMinimumHeight(col)
      def getRowStretch(col: Int): Int = l.rowStretch(col)
      def getSpacing(): Int = l.spacing()

      def setColumnMinWidth(col: Int, width: Int): Unit = l.setColumnMinimumWidth(col, width)
      def setColumnStretch(col: Int, stretch: Int): Unit = l.setColumnStretch(col, stretch)
      def setRowMinHeight(row: Int, height: Int): Unit = l.setRowMinimumHeight(row, height)
      def setRowStretch(row: Int, stretch: Int): Unit = l.setRowStretch(row, stretch)
      def setSpacing(spacing: Int): Unit = l.setSpacing(spacing)
    }
  }

  def wrap(v: QGridLayout): GridLayout = v
  def init(v: QGridLayout): Unit = {
    Toolkit.update {
      v.varUpdates := EventIterator.foreach {
        case v.nodes(oldv, newv) =>
          oldv foreach (_ foreach (cell =>
            val item = v.itemAtPosition(cell.row, cell.col)
            item.?(_.dispose())
          ))
          newv foreach (cell => v.addWidget(cell.node.unwrap, cell.row, cell.col, cell.rowSpan, cell.colSpan, cell.alignment))
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
      horizontalSpacing: Opt[Binding[Double]] = UnsetParam,
      verticalSpacing: Opt[Binding[Double]] = UnsetParam,
      nodes: Opt[Binding[Seq[GridCell]]] = UnsetParam,
  ): ToolkitAction[GridLayout] = {
    val res = (widget: @unchecked) match
      case UnsetParam => QGridLayout()
      case w: QWidget => QGridLayout(w)

    init(res)
    ifSet(alignment, Layout.Alignment.forInstance(res) := _)
    ifSet(contentsMargins, Layout.ContentsMargins.forInstance(res) := _)
    ifSet(menuBar, Layout.MenuBar.forInstance(res) := _)
    ifSet(sizeConstraint, Layout.SizeConstraint.forInstance(res) := _)
    ifSet(widgetSpacing, Layout.WidgetSpacing.forInstance(res) := _)
    ifSet(horizontalSpacing, GridLayout.HorizontalSpacing.forInstance(res) := _)
    ifSet(verticalSpacing, GridLayout.VerticalSpacing.forInstance(res) := _)
    ifSet(nodes, GridLayout.Nodes.forInstance(res) := _)

    res
  }
}
