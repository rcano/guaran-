package guarana
package gtk
opaque type LinkButton <: Button = org.gnome.gtk.LinkButton & Button
object LinkButton {
  val Uri: ExternalVar.Aux[LinkButton, java.lang.String | Null] = ExternalVar[LinkButton, java.lang.String | Null]("uri", _.getUri(), _.setUri(_), true)
  val Visited: ExternalVar.Aux[LinkButton, Boolean] = ExternalVar[LinkButton, Boolean]("visited", _.getVisited(), _.setVisited(_), true)
  ()
  extension (v: LinkButton) {
    def unwrap: org.gnome.gtk.LinkButton = v
    export unwrap.onActivateLink
  }
}