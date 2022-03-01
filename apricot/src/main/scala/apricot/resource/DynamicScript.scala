package apricot
package resource

import guarana.*
import guarana.animation.Script

/** An animation definition.
  *
  * An animation definition produces a guarana.animation.Script once it's bound to a given context, which is a map
  * providing bindings. 
  */
trait DynamicScript {
  def bind(context: Map[String, Any]): Script
}
