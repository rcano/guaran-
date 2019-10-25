package guarana

/**
 * Interface for styling properties.
 * Implementors should use appropriate caching systems, since this interface defines notification methods
 * that will be called by the scenegraph to inform of nodes not longer being part of it.
 */
trait Stylist {

  /**
   * Return the style defined value for a given property, if defined.
   */
  def apply[T](scenegraphInfo: Stylist.ScenegraphInfo)(property: Keyed[ObsVal[T]]): Option[T]

  def invalidateCache(node: Any): Unit
}

object Stylist {
  trait ScenegraphInfo {
    /**
     * Reads a property from the scenegraph state system. May not be defined and hence return None
     */
    def get[T](property: Keyed[ObsVal[T]]): Option[T]
  }

  val NoOp = new Stylist {
    def apply[T](info: ScenegraphInfo)(prop: Keyed[ObsVal[T]]) = None
    def invalidateCache(node: Any) = ()
  }
}
