package guarana

/**
 * Interface for styling properties.
 * Implementors should use appropriate caching systems, since this interface defines notification methods
 * that will be called by the toolkit to inform of nodes not longer being part of it.
 */
trait Stylist {

  transparent inline def toolkit(using t: AbstractToolkit): t.type = t
  
  /**
   * Return the style defined value for a given property, if defined.
   */
  def apply[T](metrics: Stylist.Metrics, property: ObsVal[T], instance: Any): AbstractToolkit ?=> Option[T]

  /** Notifies the stylist that the cache for the given node should be invalidated, typically
    * called when the node is removed from the toolkit.
    * 
    * @param node The node whose cache should be invalidated
    */
  def invalidateCache(node: Any): AbstractToolkit ?=> Unit

  /** This method is called by LaF instances when setting up defaults for a node, giving the stylist
    * a chance to set specific settings.
    * 
    * Typically, opacity or some spacings are configured here.
    */ 
  def installDefaults(node: Any): AbstractToolkit ?=> Unit
  /** Counterpart method to [[installDefaults]].
    * 
    * Stylists are expected to undo settings they set here, so as to avoid conflicts with other LaFs.
    */
  def uninstallDefaults(node: Any): AbstractToolkit ?=> Unit
}

object Stylist {
  val NoOp = new Stylist {
    def apply[T](metrics: Stylist.Metrics, property: ObsVal[T], instance: Any) = None
    def invalidateCache(node: Any) = ()
    def installDefaults(node: Any) = ()
    def uninstallDefaults(node: Any) = ()
  }

  case class Metrics(emSize: Double, screenWidth: Int, screenHeight: Int, fontMetrics: FontMetrics)
  object Metrics {
    val NoOp = Metrics(0, 0, 0, new FontMetrics {
      def ascent(font: Any): Double = 0
      def descent(font: Any): Double = 0
      def stringWidth(font: Any, s: String): Double = 0
    })
  }
  // TODO: find a way to abstract over font in Guaraná itself
  trait FontMetrics {
    def ascent(font: Any): Double
    def descent(font: Any): Double
    def stringWidth(font: Any, s: String): Double
  }
}
