package apricot
package resource


/** A renderable definition.
  *
  * A renderable definition produces an apricot.Renderable once it's bound to a given context, which is a map
  * providing bindings. 
  */
trait DynamicRenderable {
  def bind(context: Map[String, Any]): Renderable
}
