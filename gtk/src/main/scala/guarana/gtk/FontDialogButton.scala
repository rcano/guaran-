package guarana
package gtk
import util.*
opaque type FontDialogButton <: Widget = org.gnome.gtk.FontDialogButton & Widget
object FontDialogButton {
  val FontFeatures: ExternalVar.Aux[FontDialogButton, java.lang.String | Null] = ExternalVar[FontDialogButton, java.lang.String | Null]("font-features", _.getFontFeatures(), _.setFontFeatures(_), true)
  val Language: ExternalVar.Aux[FontDialogButton, org.gnome.pango.Language | Null] = ExternalVar[FontDialogButton, org.gnome.pango.Language | Null]("language", _.getLanguage(), _.setLanguage(_), true)
  val Level: ExternalVar.Aux[FontDialogButton, org.gnome.gtk.FontLevel] = ExternalVar[FontDialogButton, org.gnome.gtk.FontLevel]("level", _.getLevel(), _.setLevel(_), true)
  val UseFont: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  val UseSize: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)
  ()
  extension (v: FontDialogButton) {
    def unwrap: org.gnome.gtk.FontDialogButton = v
    def fontFeatures: Var.Aux[java.lang.String | Null, v.type] = FontFeatures.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def language: Var.Aux[org.gnome.pango.Language | Null, v.type] = Language.asInstanceOf[Var.Aux[org.gnome.pango.Language | Null, v.type]]
    def level: Var.Aux[org.gnome.gtk.FontLevel, v.type] = Level.asInstanceOf[Var.Aux[org.gnome.gtk.FontLevel, v.type]]
    def useFont: Var.Aux[Boolean, v.type] = UseFont.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useSize: Var.Aux[Boolean, v.type] = UseSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def init(v: FontDialogButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FontDialogButton = {
    val res = new org.gnome.gtk.FontDialogButton()
    res.asInstanceOf[FontDialogButton]
  }
  def apply(fontFeatures: Opt[java.lang.String | Null] = UnsetParam, language: Opt[org.gnome.pango.Language | Null] = UnsetParam, level: Opt[org.gnome.gtk.FontLevel] = UnsetParam, useFont: Opt[Boolean] = UnsetParam, useSize: Opt[Boolean] = UnsetParam): VarContextAction[FontDialogButton] = {
    val res = uninitialized()
    init(res)
    ifSet(fontFeatures, res.fontFeatures := _)
    ifSet(language, res.language := _)
    ifSet(level, res.level := _)
    ifSet(useFont, res.useFont := _)
    ifSet(useSize, res.useSize := _)
    res
  }
}