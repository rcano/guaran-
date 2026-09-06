
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type PasswordEntry <: guarana.gtk.Widget  = org.gnome.gtk.PasswordEntry & guarana.gtk.Widget
object PasswordEntry extends VarsMap {
  val Alignment: ExternalVar.Aux[PasswordEntry, Float] = ExternalVar[PasswordEntry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val ExtraMenu: ExternalVar.Aux[PasswordEntry, org.gnome.gio.MenuModel | Null] = ExternalVar[PasswordEntry, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val MaxWidthChars: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Position: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ShowPeekIcon: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("show-peek-icon", _.getShowPeekIcon(), _.setShowPeekIcon(_), true)
  val Text: ExternalVar.Aux[PasswordEntry, java.lang.String] = ExternalVar[PasswordEntry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)

  

  extension (v: PasswordEntry) {
    def unwrap: org.gnome.gtk.PasswordEntry = v

    def alignment: Var.Aux[Float, v.type] = guarana.gtk.PasswordEntry.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.PasswordEntry.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.PasswordEntry.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.PasswordEntry.ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.PasswordEntry.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.PasswordEntry.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def showPeekIcon: Var.Aux[Boolean, v.type] = guarana.gtk.PasswordEntry.ShowPeekIcon.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.PasswordEntry.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.PasswordEntry.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivate,
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

  def wrap(v: org.gnome.gtk.PasswordEntry): PasswordEntry = 
    val res = v.asInstanceOf[PasswordEntry]
    
    res

  def init(v: PasswordEntry): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(activatesDefault: Opt[Boolean], placeholderText: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], xalign: Opt[Float]): PasswordEntry = {
    val res = {
      val res = org.gnome.gtk.PasswordEntry.builder()
      ifSet(activatesDefault, v => res.setActivatesDefault(v))
      ifSet(placeholderText, v => res.setPlaceholderText(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(xalign, v => res.setXalign(v))
      res.build()
    }
    
    res.asInstanceOf[PasswordEntry]
  }
  
  def apply(
    activatesDefault: Opt[Boolean] = UnsetParam, placeholderText: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, xalign: Opt[Float] = UnsetParam,
    alignment: Opt[Binding[Float]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enableUndo: Opt[Binding[Boolean]] = UnsetParam,
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
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showPeekIcon: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam
  ): VarContextAction[PasswordEntry] = {
    val res = uninitialized(activatesDefault, placeholderText, cssName, heightRequest, widthRequest, accessibleRole, xalign)
    guarana.gtk.PasswordEntry.init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
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
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showPeekIcon, res.showPeekIcon := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(widthChars, res.widthChars := _)
    
    res
  }
  
}
        