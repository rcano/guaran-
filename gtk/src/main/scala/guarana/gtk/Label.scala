package guarana
package gtk
import util.*
opaque type Label <: Widget = org.gnome.gtk.Label & Widget
object Label extends VarsMap {
  val Attributes: ExternalVar.Aux[Label, org.gnome.pango.AttrList | Null] = ExternalVar[Label, org.gnome.pango.AttrList | Null]("attributes", _.getAttributes(), _.setAttributes(_), true)
  val Ellipsize: ExternalVar.Aux[Label, org.gnome.pango.EllipsizeMode] = ExternalVar[Label, org.gnome.pango.EllipsizeMode]("ellipsize", _.getEllipsize(), _.setEllipsize(_), true)
  val ExtraMenu: ExternalVar.Aux[Label, org.gnome.gio.MenuModel | Null] = ExternalVar[Label, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val Justify: ExternalVar.Aux[Label, org.gnome.gtk.Justification] = ExternalVar[Label, org.gnome.gtk.Justification]("justify", _.getJustify(), _.setJustify(_), true)
  val Label: ExternalVar.Aux[Label, java.lang.String] = ExternalVar[Label, java.lang.String]("label", _.getLabel(), _.setLabel(_), true)
  val Lines: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("lines", _.getLines(), _.setLines(_), true)
  val MaxWidthChars: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val MnemonicWidget: ExternalVar.Aux[Label, org.gnome.gtk.Widget | Null] = ExternalVar[Label, org.gnome.gtk.Widget | Null]("mnemonic-widget", _.getMnemonicWidget(), _.setMnemonicWidget(_), true)
  val NaturalWrapMode: ExternalVar.Aux[Label, org.gnome.gtk.NaturalWrapMode] = ExternalVar[Label, org.gnome.gtk.NaturalWrapMode]("natural-wrap-mode", _.getNaturalWrapMode(), _.setNaturalWrapMode(_), true)
  val Selectable: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)
  val SingleLineMode: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("single-line-mode", _.getSingleLineMode(), _.setSingleLineMode(_), true)
  val Tabs: ExternalVar.Aux[Label, org.gnome.pango.TabArray | Null] = ExternalVar[Label, org.gnome.pango.TabArray | Null]("tabs", _.getTabs(), _.setTabs(_), true)
  val Text: ExternalVar.Aux[Label, java.lang.String] = ExternalVar[Label, java.lang.String]("text", _.getText(), _.setText(_), true)
  val UseMarkup: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("use-markup", _.getUseMarkup(), _.setUseMarkup(_), true)
  val UseUnderline: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  val WidthChars: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  val Wrap: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("wrap", _.getWrap(), _.setWrap(_), true)
  val WrapMode: ExternalVar.Aux[Label, org.gnome.pango.WrapMode] = ExternalVar[Label, org.gnome.pango.WrapMode]("wrap-mode", _.getWrapMode(), _.setWrapMode(_), true)
  val Xalign: ExternalVar.Aux[Label, Float] = ExternalVar[Label, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  val Yalign: ExternalVar.Aux[Label, Float] = ExternalVar[Label, Float]("yalign", _.getYalign(), _.setYalign(_), true)
  ()
  extension (v: Label) {
    def unwrap: org.gnome.gtk.Label = v
    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def ellipsize: Var.Aux[org.gnome.pango.EllipsizeMode, v.type] = Ellipsize.asInstanceOf[Var.Aux[org.gnome.pango.EllipsizeMode, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def justify: Var.Aux[org.gnome.gtk.Justification, v.type] = Justify.asInstanceOf[Var.Aux[org.gnome.gtk.Justification, v.type]]
    def label: Var.Aux[java.lang.String, v.type] = Label.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def lines: Var.Aux[Int, v.type] = Lines.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def mnemonicWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = MnemonicWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def naturalWrapMode: Var.Aux[org.gnome.gtk.NaturalWrapMode, v.type] = NaturalWrapMode.asInstanceOf[Var.Aux[org.gnome.gtk.NaturalWrapMode, v.type]]
    def selectable: Var.Aux[Boolean, v.type] = Selectable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def singleLineMode: Var.Aux[Boolean, v.type] = SingleLineMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabs: Var.Aux[org.gnome.pango.TabArray | Null, v.type] = Tabs.asInstanceOf[Var.Aux[org.gnome.pango.TabArray | Null, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def useMarkup: Var.Aux[Boolean, v.type] = UseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def wrap: Var.Aux[Boolean, v.type] = Wrap.asInstanceOf[Var.Aux[Boolean, v.type]]
    def wrapMode: Var.Aux[org.gnome.pango.WrapMode, v.type] = WrapMode.asInstanceOf[Var.Aux[org.gnome.pango.WrapMode, v.type]]
    def xalign: Var.Aux[Float, v.type] = Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = Yalign.asInstanceOf[Var.Aux[Float, v.type]]
    export unwrap.onActivateCurrentLink, unwrap.onActivateLink, unwrap.onCopyClipboard, unwrap.onMoveCursor
  }
  def _wrap(v: org.gnome.gtk.Label): Label = {
    v.asInstanceOf
  }
  def init(v: Label): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String | Null): Label = {
    val res = new org.gnome.gtk.Label(arg$0)
    res.asInstanceOf[Label]
  }
  def apply(arg$0: java.lang.String | Null, attributes: Opt[org.gnome.pango.AttrList | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, ellipsize: Opt[org.gnome.pango.EllipsizeMode] = UnsetParam, extraMenu: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, justify: Opt[org.gnome.gtk.Justification] = UnsetParam, label: Opt[java.lang.String] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, lines: Opt[Int] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, mnemonicWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, naturalWrapMode: Opt[org.gnome.gtk.NaturalWrapMode] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, selectable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, singleLineMode: Opt[Boolean] = UnsetParam, tabs: Opt[org.gnome.pango.TabArray | Null] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useMarkup: Opt[Boolean] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, widthChars: Opt[Int] = UnsetParam, wrap: Opt[Boolean] = UnsetParam, wrapMode: Opt[org.gnome.pango.WrapMode] = UnsetParam, xalign: Opt[Float] = UnsetParam, yalign: Opt[Float] = UnsetParam): ToolkitAction[Toolkit, Label] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(attributes, res.attributes := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(ellipsize, res.ellipsize := _)
    ifSet(extraMenu, res.extraMenu := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(justify, res.justify := _)
    ifSet(label, res.label := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(lines, res.lines := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(mnemonicWidget, res.mnemonicWidget := _)
    ifSet(name, res.name := _)
    ifSet(naturalWrapMode, res.naturalWrapMode := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(selectable, res.selectable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(singleLineMode, res.singleLineMode := _)
    ifSet(tabs, res.tabs := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useMarkup, res.useMarkup := _)
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(widthChars, res.widthChars := _)
    ifSet(wrap, res.wrap := _)
    ifSet(wrapMode, res.wrapMode := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    res
  }
}