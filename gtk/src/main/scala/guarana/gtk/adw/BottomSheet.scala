
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type BottomSheet <: guarana.gtk.Widget  = org.gnome.adw.BottomSheet & guarana.gtk.Widget
object BottomSheet extends VarsMap {
  val Align: ExternalVar.Aux[BottomSheet, Float] = ExternalVar[BottomSheet, Float]("align", _.getAlign(), _.setAlign(_), true)
  val BottomBar: ExternalVar.Aux[BottomSheet, guarana.gtk.Widget | Null] = ExternalVar[BottomSheet, guarana.gtk.Widget | Null]("bottom-bar", _.getBottomBar().?(guarana.gtk.Widget.wrap), (n, v) => n.setBottomBar(v.?(_.unwrap)), true)
  val CanClose: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("can-close", _.getCanClose(), _.setCanClose(_), true)
  val CanOpen: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("can-open", _.getCanOpen(), _.setCanOpen(_), true)
  val Content: ExternalVar.Aux[BottomSheet, guarana.gtk.Widget | Null] = ExternalVar[BottomSheet, guarana.gtk.Widget | Null]("content", _.getContent().?(guarana.gtk.Widget.wrap), (n, v) => n.setContent(v.?(_.unwrap)), true)
  val FullWidth: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("full-width", _.getFullWidth(), _.setFullWidth(_), true)
  val Modal: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Open: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("open", _.getOpen(), _.setOpen(_), true)
  val RevealBottomBar: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("reveal-bottom-bar", _.getRevealBottomBar(), _.setRevealBottomBar(_), true)
  val Sheet: ExternalVar.Aux[BottomSheet, guarana.gtk.Widget | Null] = ExternalVar[BottomSheet, guarana.gtk.Widget | Null]("sheet", _.getSheet().?(guarana.gtk.Widget.wrap), (n, v) => n.setSheet(v.?(_.unwrap)), true)
  val ShowDragHandle: ExternalVar.Aux[BottomSheet, Boolean] = ExternalVar[BottomSheet, Boolean]("show-drag-handle", _.getShowDragHandle(), _.setShowDragHandle(_), true)

  

  extension (v: BottomSheet) {
    def unwrap: org.gnome.adw.BottomSheet = v

    def align: Var.Aux[Float, v.type] = guarana.gtk.adw.BottomSheet.Align.asInstanceOf[Var.Aux[Float, v.type]]
    def bottomBar: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.BottomSheet.BottomBar.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def canClose: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.CanClose.asInstanceOf[Var.Aux[Boolean, v.type]]
    def canOpen: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.CanOpen.asInstanceOf[Var.Aux[Boolean, v.type]]
    def content: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.BottomSheet.Content.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def fullWidth: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.FullWidth.asInstanceOf[Var.Aux[Boolean, v.type]]
    def modal: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    def open: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.Open.asInstanceOf[Var.Aux[Boolean, v.type]]
    def revealBottomBar: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.RevealBottomBar.asInstanceOf[Var.Aux[Boolean, v.type]]
    def sheet: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.BottomSheet.Sheet.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def showDragHandle: Var.Aux[Boolean, v.type] = guarana.gtk.adw.BottomSheet.ShowDragHandle.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onCloseAttempt,
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

  def wrap(v: org.gnome.adw.BottomSheet): BottomSheet = 
    val res = v.asInstanceOf[BottomSheet]
    
    res

  def init(v: BottomSheet): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): BottomSheet = {
    val res = {
      val res = org.gnome.adw.BottomSheet.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[BottomSheet]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    align: Opt[Binding[Float]] = UnsetParam,
    bottomBar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    canClose: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canOpen: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    content: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    fullWidth: Opt[Binding[Boolean]] = UnsetParam,
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
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    open: Opt[Binding[Boolean]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    revealBottomBar: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    sheet: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    showDragHandle: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[BottomSheet] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.BottomSheet.init(res)
    ifSet(align, res.align := _)
    ifSet(bottomBar, res.bottomBar := _)
    ifSet(canClose, res.canClose := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canOpen, res.canOpen := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(content, res.content := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(fullWidth, res.fullWidth := _)
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
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(open, res.open := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(revealBottomBar, res.revealBottomBar := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(sheet, res.sheet := _)
    ifSet(showDragHandle, res.showDragHandle := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        