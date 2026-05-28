package guarana
package gtk
import util.*
opaque type PasswordEntry <: Widget = org.gnome.gtk.PasswordEntry & Widget
object PasswordEntry {
  val Alignment: ExternalVar.Aux[PasswordEntry, Float] = ExternalVar[PasswordEntry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val ExtraMenu: ExternalVar.Aux[PasswordEntry, org.gnome.gio.MenuModel | Null] = ExternalVar[PasswordEntry, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val MaxWidthChars: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Position: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ShowPeekIcon: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("show-peek-icon", _.getShowPeekIcon(), _.setShowPeekIcon(_), true)
  val Text: ExternalVar.Aux[PasswordEntry, java.lang.String] = ExternalVar[PasswordEntry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  ()
  extension (v: PasswordEntry) {
    def unwrap: org.gnome.gtk.PasswordEntry = v
    def alignment: Var.Aux[Float, v.type] = Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def showPeekIcon: Var.Aux[Boolean, v.type] = ShowPeekIcon.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    export unwrap.onActivate
  }
  def init(v: PasswordEntry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PasswordEntry = {
    val res = new org.gnome.gtk.PasswordEntry()
    res.asInstanceOf[PasswordEntry]
  }
  def apply(alignment: Opt[Float] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, extraMenu: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, position: Opt[Int] = UnsetParam, showPeekIcon: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, widthChars: Opt[Int] = UnsetParam): VarContextAction[PasswordEntry] = {
    val res = uninitialized()
    init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(extraMenu, res.extraMenu := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(position, res.position := _)
    ifSet(showPeekIcon, res.showPeekIcon := _)
    ifSet(text, res.text := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
}