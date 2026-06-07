
package guarana
package gtk

import guarana.util.*

opaque type FileChooserWidget <: guarana.gtk.Widget  = org.gnome.gtk.FileChooserWidget & guarana.gtk.Widget
object FileChooserWidget extends VarsMap {
  @deprecated("", "") val Action: ExternalVar.Aux[FileChooserWidget, org.gnome.gtk.FileChooserAction] = ExternalVar[FileChooserWidget, org.gnome.gtk.FileChooserAction]("action", _.getAction(), _.setAction(_), true)
  @deprecated("", "") val CreateFolders: ExternalVar.Aux[FileChooserWidget, Boolean] = ExternalVar[FileChooserWidget, Boolean]("create-folders", _.getCreateFolders(), _.setCreateFolders(_), true)
  @deprecated("", "") val CurrentFolder: ExternalVar.Aux[FileChooserWidget, org.gnome.gio.File | Null] = ExternalVar[FileChooserWidget, org.gnome.gio.File | Null]("current-folder", _.getCurrentFolder(), _.setCurrentFolder(_), true)
  @deprecated("", "") val SelectMultiple: ExternalVar.Aux[FileChooserWidget, Boolean] = ExternalVar[FileChooserWidget, Boolean]("select-multiple", _.getSelectMultiple(), _.setSelectMultiple(_), true)

  

  extension (v: FileChooserWidget) {
    def unwrap: org.gnome.gtk.FileChooserWidget = v

    @deprecated("", "") def action: Var.Aux[org.gnome.gtk.FileChooserAction, v.type] = guarana.gtk.FileChooserWidget.Action.asInstanceOf[Var.Aux[org.gnome.gtk.FileChooserAction, v.type]]
    @deprecated("", "") def createFolders: Var.Aux[Boolean, v.type] = guarana.gtk.FileChooserWidget.CreateFolders.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def currentFolder: Var.Aux[org.gnome.gio.File | Null, v.type] = guarana.gtk.FileChooserWidget.CurrentFolder.asInstanceOf[Var.Aux[org.gnome.gio.File | Null, v.type]]
    @deprecated("", "") def selectMultiple: Var.Aux[Boolean, v.type] = guarana.gtk.FileChooserWidget.SelectMultiple.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onDesktopFolder,
      onDestroy,
      onDirectionChanged,
      onDownFolder,
      onHide,
      onHomeFolder,
      onKeynavFailed,
      onLocationPopup,
      onLocationPopupOnPaste,
      onLocationTogglePopup,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onPlacesShortcut,
      onQueryTooltip,
      onQuickBookmark,
      onRealize,
      onRecentShortcut,
      onSearchShortcut,
      onShow,
      onShowHidden,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize,
      onUpFolder
    }
  }

  def wrap(v: org.gnome.gtk.FileChooserWidget): FileChooserWidget = 
    val res = v.asInstanceOf[FileChooserWidget]
    
    res

  def init(v: FileChooserWidget): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(searchMode: Opt[Boolean], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], filter: Opt[org.gnome.gtk.FileFilter]): FileChooserWidget = {
    val res = {
      val res = org.gnome.gtk.FileChooserWidget.builder()
      ifSet(searchMode, v => res.setSearchMode(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(filter, v => res.setFilter(v))
      res.build()
    }
    
    res.asInstanceOf[FileChooserWidget]
  }
  
  def apply(
    searchMode: Opt[Boolean] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, filter: Opt[org.gnome.gtk.FileFilter] = UnsetParam,
    action: Opt[Binding[org.gnome.gtk.FileChooserAction]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    createFolders: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    currentFolder: Opt[Binding[org.gnome.gio.File | Null]] = UnsetParam,
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
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    selectMultiple: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[FileChooserWidget] = {
    val res = uninitialized(searchMode, cssName, heightRequest, widthRequest, accessibleRole, filter)
    guarana.gtk.FileChooserWidget.init(res)
    ifSet(action, res.action := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(createFolders, res.createFolders := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(currentFolder, res.currentFolder := _)
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
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(selectMultiple, res.selectMultiple := _)
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
        