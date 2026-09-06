
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type CellView <: guarana.gtk.Widget  = org.gnome.gtk.CellView & guarana.gtk.Widget
object CellView extends VarsMap {
  @deprecated("", "") val DisplayedRow: ExternalVar.Aux[CellView, org.gnome.gtk.TreePath | Null] = ExternalVar[CellView, org.gnome.gtk.TreePath | Null]("displayed-row", _.getDisplayedRow(), _.setDisplayedRow(_), true)
  @deprecated("", "") val DrawSensitive: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("draw-sensitive", _.getDrawSensitive(), _.setDrawSensitive(_), true)
  @deprecated("", "") val FitModel: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("fit-model", _.getFitModel(), _.setFitModel(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[CellView, org.gnome.gtk.TreeModel | Null] = ExternalVar[CellView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  val Orientation: ExternalVar.Aux[CellView, org.gnome.gtk.Orientation] = ExternalVar[CellView, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)

  

  extension (v: CellView) {
    def unwrap: org.gnome.gtk.CellView = v

    @deprecated("", "") def displayedRow: Var.Aux[org.gnome.gtk.TreePath | Null, v.type] = guarana.gtk.CellView.DisplayedRow.asInstanceOf[Var.Aux[org.gnome.gtk.TreePath | Null, v.type]]
    @deprecated("", "") def drawSensitive: Var.Aux[Boolean, v.type] = guarana.gtk.CellView.DrawSensitive.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def fitModel: Var.Aux[Boolean, v.type] = guarana.gtk.CellView.FitModel.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def model: Var.Aux[org.gnome.gtk.TreeModel | Null, v.type] = guarana.gtk.CellView.Model.asInstanceOf[Var.Aux[org.gnome.gtk.TreeModel | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.CellView.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]

    

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

  def wrap(v: org.gnome.gtk.CellView): CellView = 
    val res = v.asInstanceOf[CellView]
    
    res

  def init(v: CellView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cellArea: Opt[org.gnome.gtk.CellArea], cellAreaContext: Opt[org.gnome.gtk.CellAreaContext], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): CellView = {
    val res = {
      val res = org.gnome.gtk.CellView.builder()
      ifSet(cellArea, v => res.setCellArea(v))
      ifSet(cellAreaContext, v => res.setCellAreaContext(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[CellView]
  }
  
  def apply(
    cellArea: Opt[org.gnome.gtk.CellArea] = UnsetParam, cellAreaContext: Opt[org.gnome.gtk.CellAreaContext] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    displayedRow: Opt[Binding[org.gnome.gtk.TreePath | Null]] = UnsetParam,
    drawSensitive: Opt[Binding[Boolean]] = UnsetParam,
    fitModel: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[org.gnome.gtk.TreeModel | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[CellView] = {
    val res = uninitialized(cellArea, cellAreaContext, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.CellView.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(displayedRow, res.displayedRow := _)
    ifSet(drawSensitive, res.drawSensitive := _)
    ifSet(fitModel, res.fitModel := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
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
        