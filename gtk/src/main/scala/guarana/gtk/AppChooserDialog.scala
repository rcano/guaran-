package guarana
package gtk
opaque type AppChooserDialog <: Dialog = org.gnome.gtk.AppChooserDialog & Dialog
object AppChooserDialog {
  val Heading: ExternalVar.Aux[AppChooserDialog, java.lang.String | Null] = ExternalVar[AppChooserDialog, java.lang.String | Null]("heading", _.getHeading(), _.setHeading(_), true)
  ()
  extension (v: AppChooserDialog) {
    def unwrap: org.gnome.gtk.AppChooserDialog = v
  }
}