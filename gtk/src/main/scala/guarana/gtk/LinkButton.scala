package guarana
package gtk
import util.*
opaque type LinkButton <: Button = org.gnome.gtk.LinkButton & Button
object LinkButton {
  val Uri: ExternalVar.Aux[LinkButton, java.lang.String] = ExternalVar[LinkButton, java.lang.String]("uri", _.getUri(), _.setUri(_), true)
  val Visited: ExternalVar.Aux[LinkButton, Boolean] = ExternalVar[LinkButton, Boolean]("visited", _.getVisited(), _.setVisited(_), true)
  ()
  extension (v: LinkButton) {
    def unwrap: org.gnome.gtk.LinkButton = v
    def uri: Var.Aux[java.lang.String, v.type] = Uri.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def visited: Var.Aux[Boolean, v.type] = Visited.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivateLink
  }
  def init(v: LinkButton): Unit = {
    Button.init(v)
  }
  def uninitialized(): LinkButton = {
    val res = new org.gnome.gtk.LinkButton()
    res.asInstanceOf[LinkButton]
  }
  def apply(uri: Opt[java.lang.String] = UnsetParam, visited: Opt[Boolean] = UnsetParam): VarContextAction[LinkButton] = {
    val res = uninitialized()
    init(res)
    ifSet(uri, res.uri := _)
    ifSet(visited, res.visited := _)
    res
  }
}