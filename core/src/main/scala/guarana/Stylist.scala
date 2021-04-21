package guarana

/**
 * Interface for styling properties.
 * Implementors should use appropriate caching systems, since this interface defines notification methods
 * that will be called by the toolkit to inform of nodes not longer being part of it.
 */
trait Stylist {

  /**
   * Return the style defined value for a given property, if defined.
   */
  def apply[T](property: Keyed[ObsVal[T]]): Option[T]

  /** Notifies the stylist that the cache for the given node should be invalidated, typically
    * called when the node is removed from the toolkit.
    * 
    * @param node The node whose cache should be invalidated
    */
  def invalidateCache(node: Any): Unit

  /** This method is called by LaF instances when setting up defaults for a node, giving the stylist
    * a chance to set specific settings.
    * 
    * Typically, opacity or some spacings are configured here.
    */ 
  def installDefaults(node: Any): Unit
  /** Counterpart method to [[installDefaults]].
    * 
    * Stylists are expected to undo settings they set here, so as to avoid conflicts with other LaFs.
    */
  def uninstallDefaults(node: Any): Unit
}

object Stylist {
  val NoOp = new Stylist {
    def apply[T](prop: Keyed[ObsVal[T]]) = None
    def invalidateCache(node: Any) = ()
    def installDefaults(node: Any): Unit = ()
    def uninstallDefaults(node: Any): Unit = ()
  }
}
