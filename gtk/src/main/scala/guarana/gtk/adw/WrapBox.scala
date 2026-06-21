
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type WrapBox <: guarana.gtk.Widget  = org.gnome.adw.WrapBox & guarana.gtk.Widget
object WrapBox extends VarsMap {
  val Align: ExternalVar.Aux[WrapBox, Float] = ExternalVar[WrapBox, Float]("align", _.getAlign(), _.setAlign(_), true)
  val ChildSpacing: ExternalVar.Aux[WrapBox, Int] = ExternalVar[WrapBox, Int]("child-spacing", _.getChildSpacing(), _.setChildSpacing(_), true)
  val ChildSpacingUnit: ExternalVar.Aux[WrapBox, org.gnome.adw.LengthUnit] = ExternalVar[WrapBox, org.gnome.adw.LengthUnit]("child-spacing-unit", _.getChildSpacingUnit(), _.setChildSpacingUnit(_), true)
  val Justify: ExternalVar.Aux[WrapBox, org.gnome.adw.JustifyMode] = ExternalVar[WrapBox, org.gnome.adw.JustifyMode]("justify", _.getJustify(), _.setJustify(_), true)
  val JustifyLastLine: ExternalVar.Aux[WrapBox, Boolean] = ExternalVar[WrapBox, Boolean]("justify-last-line", _.getJustifyLastLine(), _.setJustifyLastLine(_), true)
  val LineHomogeneous: ExternalVar.Aux[WrapBox, Boolean] = ExternalVar[WrapBox, Boolean]("line-homogeneous", _.getLineHomogeneous(), _.setLineHomogeneous(_), true)
  val LineSpacing: ExternalVar.Aux[WrapBox, Int] = ExternalVar[WrapBox, Int]("line-spacing", _.getLineSpacing(), _.setLineSpacing(_), true)
  val LineSpacingUnit: ExternalVar.Aux[WrapBox, org.gnome.adw.LengthUnit] = ExternalVar[WrapBox, org.gnome.adw.LengthUnit]("line-spacing-unit", _.getLineSpacingUnit(), _.setLineSpacingUnit(_), true)
  val NaturalLineLength: ExternalVar.Aux[WrapBox, Int] = ExternalVar[WrapBox, Int]("natural-line-length", _.getNaturalLineLength(), _.setNaturalLineLength(_), true)
  val NaturalLineLengthUnit: ExternalVar.Aux[WrapBox, org.gnome.adw.LengthUnit] = ExternalVar[WrapBox, org.gnome.adw.LengthUnit]("natural-line-length-unit", _.getNaturalLineLengthUnit(), _.setNaturalLineLengthUnit(_), true)
  val Nodes: Var[Seq[Widget]] = Var[Seq[Widget]]("nodes", Seq.empty, true)
  val Orientation: ExternalVar.Aux[WrapBox, org.gnome.gtk.Orientation] = ExternalVar[WrapBox, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val PackDirection: ExternalVar.Aux[WrapBox, org.gnome.adw.PackDirection] = ExternalVar[WrapBox, org.gnome.adw.PackDirection]("pack-direction", _.getPackDirection(), _.setPackDirection(_), true)
  val WrapPolicy: ExternalVar.Aux[WrapBox, org.gnome.adw.WrapPolicy] = ExternalVar[WrapBox, org.gnome.adw.WrapPolicy]("wrap-policy", _.getWrapPolicy(), _.setWrapPolicy(_), true)
  val WrapReverse: ExternalVar.Aux[WrapBox, Boolean] = ExternalVar[WrapBox, Boolean]("wrap-reverse", _.getWrapReverse(), _.setWrapReverse(_), true)

  

  extension (v: WrapBox) {
    def unwrap: org.gnome.adw.WrapBox = v

    def align: Var.Aux[Float, v.type] = guarana.gtk.adw.WrapBox.Align.asInstanceOf[Var.Aux[Float, v.type]]
    def childSpacing: Var.Aux[Int, v.type] = guarana.gtk.adw.WrapBox.ChildSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def childSpacingUnit: Var.Aux[org.gnome.adw.LengthUnit, v.type] = guarana.gtk.adw.WrapBox.ChildSpacingUnit.asInstanceOf[Var.Aux[org.gnome.adw.LengthUnit, v.type]]
    def justify: Var.Aux[org.gnome.adw.JustifyMode, v.type] = guarana.gtk.adw.WrapBox.Justify.asInstanceOf[Var.Aux[org.gnome.adw.JustifyMode, v.type]]
    def justifyLastLine: Var.Aux[Boolean, v.type] = guarana.gtk.adw.WrapBox.JustifyLastLine.asInstanceOf[Var.Aux[Boolean, v.type]]
    def lineHomogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.adw.WrapBox.LineHomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def lineSpacing: Var.Aux[Int, v.type] = guarana.gtk.adw.WrapBox.LineSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def lineSpacingUnit: Var.Aux[org.gnome.adw.LengthUnit, v.type] = guarana.gtk.adw.WrapBox.LineSpacingUnit.asInstanceOf[Var.Aux[org.gnome.adw.LengthUnit, v.type]]
    def naturalLineLength: Var.Aux[Int, v.type] = guarana.gtk.adw.WrapBox.NaturalLineLength.asInstanceOf[Var.Aux[Int, v.type]]
    def naturalLineLengthUnit: Var.Aux[org.gnome.adw.LengthUnit, v.type] = guarana.gtk.adw.WrapBox.NaturalLineLengthUnit.asInstanceOf[Var.Aux[org.gnome.adw.LengthUnit, v.type]]
    def nodes: Var.Aux[Seq[Widget], v.type] = guarana.gtk.adw.WrapBox.Nodes.asInstanceOf[Var.Aux[Seq[Widget], v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.WrapBox.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def packDirection: Var.Aux[org.gnome.adw.PackDirection, v.type] = guarana.gtk.adw.WrapBox.PackDirection.asInstanceOf[Var.Aux[org.gnome.adw.PackDirection, v.type]]
    def wrapPolicy: Var.Aux[org.gnome.adw.WrapPolicy, v.type] = guarana.gtk.adw.WrapBox.WrapPolicy.asInstanceOf[Var.Aux[org.gnome.adw.WrapPolicy, v.type]]
    def wrapReverse: Var.Aux[Boolean, v.type] = guarana.gtk.adw.WrapBox.WrapReverse.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
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

  def wrap(v: org.gnome.adw.WrapBox): WrapBox = 
    val res = v.asInstanceOf[WrapBox]
    
    res

  def init(v: WrapBox): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    Toolkit.update {
      v.varUpdates := EventIterator.forsome {
        case v.nodes(_, newv) =>
          while (v.getFirstChild() != null) v.remove(v.getFirstChild())
          newv.foreach(w => v.append(w.unwrap))
      }
    }
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): WrapBox = {
    val res = {
      val res = org.gnome.adw.WrapBox.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[WrapBox]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    align: Opt[Binding[Float]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childSpacing: Opt[Binding[Int]] = UnsetParam,
    childSpacingUnit: Opt[Binding[org.gnome.adw.LengthUnit]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    justify: Opt[Binding[org.gnome.adw.JustifyMode]] = UnsetParam,
    justifyLastLine: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    lineHomogeneous: Opt[Binding[Boolean]] = UnsetParam,
    lineSpacing: Opt[Binding[Int]] = UnsetParam,
    lineSpacingUnit: Opt[Binding[org.gnome.adw.LengthUnit]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    naturalLineLength: Opt[Binding[Int]] = UnsetParam,
    naturalLineLengthUnit: Opt[Binding[org.gnome.adw.LengthUnit]] = UnsetParam,
    nodes: Opt[Binding[Seq[Widget]]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    packDirection: Opt[Binding[org.gnome.adw.PackDirection]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    wrapPolicy: Opt[Binding[org.gnome.adw.WrapPolicy]] = UnsetParam,
    wrapReverse: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[WrapBox] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.WrapBox.init(res)
    ifSet(align, res.align := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childSpacing, res.childSpacing := _)
    ifSet(childSpacingUnit, res.childSpacingUnit := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(justify, res.justify := _)
    ifSet(justifyLastLine, res.justifyLastLine := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(lineHomogeneous, res.lineHomogeneous := _)
    ifSet(lineSpacing, res.lineSpacing := _)
    ifSet(lineSpacingUnit, res.lineSpacingUnit := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(naturalLineLength, res.naturalLineLength := _)
    ifSet(naturalLineLengthUnit, res.naturalLineLengthUnit := _)
    ifSet(nodes, res.nodes := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(packDirection, res.packDirection := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(wrapPolicy, res.wrapPolicy := _)
    ifSet(wrapReverse, res.wrapReverse := _)
    
    res
  }
  
}
        