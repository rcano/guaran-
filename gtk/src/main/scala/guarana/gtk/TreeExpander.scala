package guarana
package gtk
import util.*
opaque type TreeExpander <: Widget = org.gnome.gtk.TreeExpander & Widget
object TreeExpander extends VarsMap {
  val Child: ExternalVar.Aux[TreeExpander, org.gnome.gtk.Widget | Null] = ExternalVar[TreeExpander, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HideExpander: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("hide-expander", _.getHideExpander(), _.setHideExpander(_), true)
  val IndentForDepth: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("indent-for-depth", _.getIndentForDepth(), _.setIndentForDepth(_), true)
  val IndentForIcon: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("indent-for-icon", _.getIndentForIcon(), _.setIndentForIcon(_), true)
  val ListRow: ExternalVar.Aux[TreeExpander, org.gnome.gtk.TreeListRow | Null] = ExternalVar[TreeExpander, org.gnome.gtk.TreeListRow | Null]("list-row", _.getListRow(), _.setListRow(_), true)
  ()
  extension (v: TreeExpander) {
    def unwrap: org.gnome.gtk.TreeExpander = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hideExpander: Var.Aux[Boolean, v.type] = HideExpander.asInstanceOf[Var.Aux[Boolean, v.type]]
    def indentForDepth: Var.Aux[Boolean, v.type] = IndentForDepth.asInstanceOf[Var.Aux[Boolean, v.type]]
    def indentForIcon: Var.Aux[Boolean, v.type] = IndentForIcon.asInstanceOf[Var.Aux[Boolean, v.type]]
    def listRow: Var.Aux[org.gnome.gtk.TreeListRow | Null, v.type] = ListRow.asInstanceOf[Var.Aux[org.gnome.gtk.TreeListRow | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.TreeExpander): TreeExpander = {
    v.asInstanceOf
  }
  def init(v: TreeExpander): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): TreeExpander = {
    val res = new org.gnome.gtk.TreeExpander()
    res.asInstanceOf[TreeExpander]
  }
  def apply(canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, hideExpander: Opt[Binding[Boolean]] = UnsetParam, indentForDepth: Opt[Binding[Boolean]] = UnsetParam, indentForIcon: Opt[Binding[Boolean]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, listRow: Opt[Binding[org.gnome.gtk.TreeListRow | Null]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, TreeExpander] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hideExpander, res.hideExpander := _)
    ifSet(indentForDepth, res.indentForDepth := _)
    ifSet(indentForIcon, res.indentForIcon := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(listRow, res.listRow := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}