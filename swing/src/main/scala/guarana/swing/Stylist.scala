package guarana.swing

/**
 * Interface for styling properties.
 * Implementors should use appropriate caching systems, since this interface defines notification methods
 * that will be called by the scenegraph to inform of nodes not longer being part of it.
 */
trait Stylist {

  type StateChange = Stylist.StateChange
  val StateChange = Stylist.StateChange

  /**
   * Return the style defined value for a given property, if defined.
   */
  def apply[T](scenegraphInfo: Stylist.ScenegraphInfo)(property: Keyed[ObsVal[T]]): Option[T]

  /** Notifies the stylist that the cache for the given node should be invalidated, typically
    * called when the node is removed from the scenegraph.
    * 
    * @param node The node whose cache should be invalidated
    */
  def invalidateCache(node: Any): Unit

  /** This method is called by LaF instances no notify the stylist about a state change, such as
    * a node being hovered, a button being armed, etc.
    * 
    * @param node The node whose state is changing.
    * @param state The new state entered
    */
  def stateChanged(node: Any, state: Any, change: StateChange): Unit

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
  trait ScenegraphInfo {
    /**
     * Reads a property from the scenegraph state system. May not be defined and hence return None
     */
    def get[T](property: Keyed[ObsVal[T]]): Option[T]
    def getOrDefault[T](property: Keyed[ObsVal[T]]): T = get(property).getOrElse {
      val k = property.instance.asInstanceOf[property.keyed.ForInstance]
      property.keyed.initialValue(k)
    }

    def emSize: Double
  }

  val NoOp = new Stylist {
    def apply[T](info: ScenegraphInfo)(prop: Keyed[ObsVal[T]]) = None
    def invalidateCache(node: Any) = ()
    def stateChanged(node: Any, state: Any, change: Stylist.StateChange): Unit = ()
    def installDefaults(node: Any): Unit = ()
    def uninstallDefaults(node: Any): Unit = ()
  }

  enum StateChange {
    case Entered, Exited
  }
}
