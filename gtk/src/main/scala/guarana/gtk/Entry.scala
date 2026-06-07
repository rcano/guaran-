
package guarana
package gtk

import guarana.util.*

opaque type Entry <: guarana.gtk.Widget  = org.gnome.gtk.Entry & guarana.gtk.Widget
object Entry extends VarsMap {
  val ActivatesDefault: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Alignment: ExternalVar.Aux[Entry, Float] = ExternalVar[Entry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Buffer: ExternalVar.Aux[Entry, org.gnome.gtk.EntryBuffer] = ExternalVar[Entry, org.gnome.gtk.EntryBuffer]("buffer", _.getBuffer(), _.setBuffer(_), true)
  @deprecated("", "") val Completion: ExternalVar.Aux[Entry, org.gnome.gtk.EntryCompletion | Null] = ExternalVar[Entry, org.gnome.gtk.EntryCompletion | Null]("completion", _.getCompletion(), _.setCompletion(_), true)
  val Editable: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val ExtraMenu: ExternalVar.Aux[Entry, org.gnome.gio.MenuModel | Null] = ExternalVar[Entry, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val HasFrame: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val InputHints: ExternalVar.Aux[Entry, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[Entry, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[Entry, org.gnome.gtk.InputPurpose] = ExternalVar[Entry, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val InvisibleChar: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("invisible-char", _.getInvisibleChar(), _.setInvisibleChar(_), true)
  val MaxLength: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("max-length", _.getMaxLength(), _.setMaxLength(_), true)
  val MaxWidthChars: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val OverwriteMode: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("overwrite-mode", _.getOverwriteMode(), _.setOverwriteMode(_), true)
  val PlaceholderText: ExternalVar.Aux[Entry, java.lang.String | Null] = ExternalVar[Entry, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val Position: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ProgressFraction: ExternalVar.Aux[Entry, Double] = ExternalVar[Entry, Double]("progress-fraction", _.getProgressFraction(), _.setProgressFraction(_), true)
  val ProgressPulseStep: ExternalVar.Aux[Entry, Double] = ExternalVar[Entry, Double]("progress-pulse-step", _.getProgressPulseStep(), _.setProgressPulseStep(_), true)
  val Tabs: ExternalVar.Aux[Entry, org.gnome.pango.TabArray | Null] = ExternalVar[Entry, org.gnome.pango.TabArray | Null]("tabs", _.getTabs(), _.setTabs(_), true)
  val Text: ExternalVar.Aux[Entry, java.lang.String] = ExternalVar[Entry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val Visibility: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("visibility", _.getVisibility(), _.setVisibility(_), true)
  val WidthChars: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)

  

  extension (v: Entry) {
    def unwrap: org.gnome.gtk.Entry = v

    def activatesDefault: Var.Aux[Boolean, v.type] = guarana.gtk.Entry.ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alignment: Var.Aux[Float, v.type] = guarana.gtk.Entry.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def buffer: Var.Aux[org.gnome.gtk.EntryBuffer, v.type] = guarana.gtk.Entry.Buffer.asInstanceOf[Var.Aux[org.gnome.gtk.EntryBuffer, v.type]]
    @deprecated("", "") def completion: Var.Aux[org.gnome.gtk.EntryCompletion | Null, v.type] = guarana.gtk.Entry.Completion.asInstanceOf[Var.Aux[org.gnome.gtk.EntryCompletion | Null, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.Entry.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.Entry.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.Entry.ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = guarana.gtk.Entry.HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = guarana.gtk.Entry.InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = guarana.gtk.Entry.InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def invisibleChar: Var.Aux[Int, v.type] = guarana.gtk.Entry.InvisibleChar.asInstanceOf[Var.Aux[Int, v.type]]
    def maxLength: Var.Aux[Int, v.type] = guarana.gtk.Entry.MaxLength.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.Entry.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def overwriteMode: Var.Aux[Boolean, v.type] = guarana.gtk.Entry.OverwriteMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def placeholderText: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Entry.PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.Entry.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def progressFraction: Var.Aux[Double, v.type] = guarana.gtk.Entry.ProgressFraction.asInstanceOf[Var.Aux[Double, v.type]]
    def progressPulseStep: Var.Aux[Double, v.type] = guarana.gtk.Entry.ProgressPulseStep.asInstanceOf[Var.Aux[Double, v.type]]
    def tabs: Var.Aux[org.gnome.pango.TabArray | Null, v.type] = guarana.gtk.Entry.Tabs.asInstanceOf[Var.Aux[org.gnome.pango.TabArray | Null, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.Entry.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def visibility: Var.Aux[Boolean, v.type] = guarana.gtk.Entry.Visibility.asInstanceOf[Var.Aux[Boolean, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.Entry.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivate,
      onDestroy,
      onDirectionChanged,
      onHide,
      onIconPress,
      onIconRelease,
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

  def wrap(v: org.gnome.gtk.Entry): Entry = 
    val res = v.asInstanceOf[Entry]
    
    res

  def init(v: Entry): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(attributes: Opt[org.gnome.pango.AttrList], enableEmojiCompletion: Opt[Boolean], imModule: Opt[java.lang.String], invisibleCharSet: Opt[Boolean], menuEntryIconPrimaryText: Opt[java.lang.String], menuEntryIconSecondaryText: Opt[java.lang.String], primaryIconActivatable: Opt[Boolean], primaryIconGicon: Opt[org.gnome.gio.Icon], primaryIconName: Opt[java.lang.String], primaryIconPaintable: Opt[org.gnome.gdk.Paintable], primaryIconSensitive: Opt[Boolean], primaryIconTooltipMarkup: Opt[java.lang.String], primaryIconTooltipText: Opt[java.lang.String], secondaryIconActivatable: Opt[Boolean], secondaryIconGicon: Opt[org.gnome.gio.Icon], secondaryIconName: Opt[java.lang.String], secondaryIconPaintable: Opt[org.gnome.gdk.Paintable], secondaryIconSensitive: Opt[Boolean], secondaryIconTooltipMarkup: Opt[java.lang.String], secondaryIconTooltipText: Opt[java.lang.String], showEmojiIcon: Opt[Boolean], truncateMultiline: Opt[Boolean], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], editingCanceled: Opt[Boolean], xalign: Opt[Float]): Entry = {
    val res = org.gnome.gtk.Entry.builder()
    ifSet(attributes, v => res.setAttributes(v))
    ifSet(enableEmojiCompletion, v => res.setEnableEmojiCompletion(v))
    ifSet(imModule, v => res.setImModule(v))
    ifSet(invisibleCharSet, v => res.setInvisibleCharSet(v))
    ifSet(menuEntryIconPrimaryText, v => res.setMenuEntryIconPrimaryText(v))
    ifSet(menuEntryIconSecondaryText, v => res.setMenuEntryIconSecondaryText(v))
    ifSet(primaryIconActivatable, v => res.setPrimaryIconActivatable(v))
    ifSet(primaryIconGicon, v => res.setPrimaryIconGicon(v))
    ifSet(primaryIconName, v => res.setPrimaryIconName(v))
    ifSet(primaryIconPaintable, v => res.setPrimaryIconPaintable(v))
    ifSet(primaryIconSensitive, v => res.setPrimaryIconSensitive(v))
    ifSet(primaryIconTooltipMarkup, v => res.setPrimaryIconTooltipMarkup(v))
    ifSet(primaryIconTooltipText, v => res.setPrimaryIconTooltipText(v))
    ifSet(secondaryIconActivatable, v => res.setSecondaryIconActivatable(v))
    ifSet(secondaryIconGicon, v => res.setSecondaryIconGicon(v))
    ifSet(secondaryIconName, v => res.setSecondaryIconName(v))
    ifSet(secondaryIconPaintable, v => res.setSecondaryIconPaintable(v))
    ifSet(secondaryIconSensitive, v => res.setSecondaryIconSensitive(v))
    ifSet(secondaryIconTooltipMarkup, v => res.setSecondaryIconTooltipMarkup(v))
    ifSet(secondaryIconTooltipText, v => res.setSecondaryIconTooltipText(v))
    ifSet(showEmojiIcon, v => res.setShowEmojiIcon(v))
    ifSet(truncateMultiline, v => res.setTruncateMultiline(v))
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    ifSet(editingCanceled, v => res.setEditingCanceled(v))
    ifSet(xalign, v => res.setXalign(v))
    
    res.asInstanceOf[Entry]
  }
  
  def apply(
    attributes: Opt[org.gnome.pango.AttrList] = UnsetParam, enableEmojiCompletion: Opt[Boolean] = UnsetParam, imModule: Opt[java.lang.String] = UnsetParam, invisibleCharSet: Opt[Boolean] = UnsetParam, menuEntryIconPrimaryText: Opt[java.lang.String] = UnsetParam, menuEntryIconSecondaryText: Opt[java.lang.String] = UnsetParam, primaryIconActivatable: Opt[Boolean] = UnsetParam, primaryIconGicon: Opt[org.gnome.gio.Icon] = UnsetParam, primaryIconName: Opt[java.lang.String] = UnsetParam, primaryIconPaintable: Opt[org.gnome.gdk.Paintable] = UnsetParam, primaryIconSensitive: Opt[Boolean] = UnsetParam, primaryIconTooltipMarkup: Opt[java.lang.String] = UnsetParam, primaryIconTooltipText: Opt[java.lang.String] = UnsetParam, secondaryIconActivatable: Opt[Boolean] = UnsetParam, secondaryIconGicon: Opt[org.gnome.gio.Icon] = UnsetParam, secondaryIconName: Opt[java.lang.String] = UnsetParam, secondaryIconPaintable: Opt[org.gnome.gdk.Paintable] = UnsetParam, secondaryIconSensitive: Opt[Boolean] = UnsetParam, secondaryIconTooltipMarkup: Opt[java.lang.String] = UnsetParam, secondaryIconTooltipText: Opt[java.lang.String] = UnsetParam, showEmojiIcon: Opt[Boolean] = UnsetParam, truncateMultiline: Opt[Boolean] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, editingCanceled: Opt[Boolean] = UnsetParam, xalign: Opt[Float] = UnsetParam,
    activatesDefault: Opt[Binding[Boolean]] = UnsetParam,
    alignment: Opt[Binding[Float]] = UnsetParam,
    buffer: Opt[Binding[org.gnome.gtk.EntryBuffer]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    completion: Opt[Binding[org.gnome.gtk.EntryCompletion | Null]] = UnsetParam,
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
    hasFrame: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    inputHints: Opt[Binding[java.util.Set[org.gnome.gtk.InputHints]]] = UnsetParam,
    inputPurpose: Opt[Binding[org.gnome.gtk.InputPurpose]] = UnsetParam,
    invisibleChar: Opt[Binding[Int]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxLength: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    overwriteMode: Opt[Binding[Boolean]] = UnsetParam,
    placeholderText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    progressFraction: Opt[Binding[Double]] = UnsetParam,
    progressPulseStep: Opt[Binding[Double]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tabs: Opt[Binding[org.gnome.pango.TabArray | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visibility: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam
  ): VarContextAction[Entry] = {
    val res = uninitialized(attributes, enableEmojiCompletion, imModule, invisibleCharSet, menuEntryIconPrimaryText, menuEntryIconSecondaryText, primaryIconActivatable, primaryIconGicon, primaryIconName, primaryIconPaintable, primaryIconSensitive, primaryIconTooltipMarkup, primaryIconTooltipText, secondaryIconActivatable, secondaryIconGicon, secondaryIconName, secondaryIconPaintable, secondaryIconSensitive, secondaryIconTooltipMarkup, secondaryIconTooltipText, showEmojiIcon, truncateMultiline, cssName, heightRequest, widthRequest, accessibleRole, editingCanceled, xalign)
    guarana.gtk.Entry.init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(alignment, res.alignment := _)
    ifSet(buffer, res.buffer := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(completion, res.completion := _)
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
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(invisibleChar, res.invisibleChar := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxLength, res.maxLength := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(overwriteMode, res.overwriteMode := _)
    ifSet(placeholderText, res.placeholderText := _)
    ifSet(position, res.position := _)
    ifSet(progressFraction, res.progressFraction := _)
    ifSet(progressPulseStep, res.progressPulseStep := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tabs, res.tabs := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visibility, res.visibility := _)
    ifSet(visible, res.visible := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
  
}
        