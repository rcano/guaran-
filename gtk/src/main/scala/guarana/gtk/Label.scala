
package guarana
package gtk

import guarana.util.*

opaque type Label <: guarana.gtk.Widget  = org.gnome.gtk.Label & guarana.gtk.Widget
object Label extends VarsMap {
  val Attributes: ExternalVar.Aux[Label, org.gnome.pango.AttrList | Null] = ExternalVar[Label, org.gnome.pango.AttrList | Null]("attributes", _.getAttributes(), _.setAttributes(_), true)
  val Ellipsize: ExternalVar.Aux[Label, org.gnome.pango.EllipsizeMode] = ExternalVar[Label, org.gnome.pango.EllipsizeMode]("ellipsize", _.getEllipsize(), _.setEllipsize(_), true)
  val ExtraMenu: ExternalVar.Aux[Label, org.gnome.gio.MenuModel | Null] = ExternalVar[Label, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val Justify: ExternalVar.Aux[Label, org.gnome.gtk.Justification] = ExternalVar[Label, org.gnome.gtk.Justification]("justify", _.getJustify(), _.setJustify(_), true)
  val Label: ExternalVar.Aux[Label, java.lang.String] = ExternalVar[Label, java.lang.String]("label", _.getLabel(), _.setLabel(_), true)
  val Lines: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("lines", _.getLines(), _.setLines(_), true)
  val MaxWidthChars: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val MnemonicWidget: ExternalVar.Aux[Label, guarana.gtk.Widget | Null] = ExternalVar[Label, guarana.gtk.Widget | Null]("mnemonic-widget", _.getMnemonicWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setMnemonicWidget(v.?(_.unwrap)), true)
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

  

  extension (v: Label) {
    def unwrap: org.gnome.gtk.Label = v

    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = guarana.gtk.Label.Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def ellipsize: Var.Aux[org.gnome.pango.EllipsizeMode, v.type] = guarana.gtk.Label.Ellipsize.asInstanceOf[Var.Aux[org.gnome.pango.EllipsizeMode, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.Label.ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def justify: Var.Aux[org.gnome.gtk.Justification, v.type] = guarana.gtk.Label.Justify.asInstanceOf[Var.Aux[org.gnome.gtk.Justification, v.type]]
    def label: Var.Aux[java.lang.String, v.type] = guarana.gtk.Label.Label.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def lines: Var.Aux[Int, v.type] = guarana.gtk.Label.Lines.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.Label.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def mnemonicWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Label.MnemonicWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def naturalWrapMode: Var.Aux[org.gnome.gtk.NaturalWrapMode, v.type] = guarana.gtk.Label.NaturalWrapMode.asInstanceOf[Var.Aux[org.gnome.gtk.NaturalWrapMode, v.type]]
    def selectable: Var.Aux[Boolean, v.type] = guarana.gtk.Label.Selectable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def singleLineMode: Var.Aux[Boolean, v.type] = guarana.gtk.Label.SingleLineMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabs: Var.Aux[org.gnome.pango.TabArray | Null, v.type] = guarana.gtk.Label.Tabs.asInstanceOf[Var.Aux[org.gnome.pango.TabArray | Null, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.Label.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def useMarkup: Var.Aux[Boolean, v.type] = guarana.gtk.Label.UseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = guarana.gtk.Label.UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.Label.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def wrap: Var.Aux[Boolean, v.type] = guarana.gtk.Label.Wrap.asInstanceOf[Var.Aux[Boolean, v.type]]
    def wrapMode: Var.Aux[org.gnome.pango.WrapMode, v.type] = guarana.gtk.Label.WrapMode.asInstanceOf[Var.Aux[org.gnome.pango.WrapMode, v.type]]
    def xalign: Var.Aux[Float, v.type] = guarana.gtk.Label.Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = guarana.gtk.Label.Yalign.asInstanceOf[Var.Aux[Float, v.type]]

    

    export unwrap.{
      onActivateCurrentLink,
      onActivateLink,
      onCopyClipboard,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveCursor,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Label): Label = 
    val res = v.asInstanceOf[Label]
    
    res

  def init(v: Label): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Label = {
    val res = {
      val res = org.gnome.gtk.Label.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Label]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    attributes: Opt[Binding[org.gnome.pango.AttrList | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    ellipsize: Opt[Binding[org.gnome.pango.EllipsizeMode]] = UnsetParam,
    extraMenu: Opt[Binding[org.gnome.gio.MenuModel | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    justify: Opt[Binding[org.gnome.gtk.Justification]] = UnsetParam,
    label: Opt[Binding[java.lang.String]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    lines: Opt[Binding[Int]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    mnemonicWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    naturalWrapMode: Opt[Binding[org.gnome.gtk.NaturalWrapMode]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    selectable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    singleLineMode: Opt[Binding[Boolean]] = UnsetParam,
    tabs: Opt[Binding[org.gnome.pango.TabArray | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useMarkup: Opt[Binding[Boolean]] = UnsetParam,
    useUnderline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam,
    wrap: Opt[Binding[Boolean]] = UnsetParam,
    wrapMode: Opt[Binding[org.gnome.pango.WrapMode]] = UnsetParam,
    xalign: Opt[Binding[Float]] = UnsetParam,
    yalign: Opt[Binding[Float]] = UnsetParam
  ): VarContextAction[Label] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Label.init(res)
    ifSet(attributes, res.attributes := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        