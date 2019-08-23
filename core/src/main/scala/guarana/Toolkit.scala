package guarana

/**
 * Toolkit defines all the infrastructure required for the Guarana toolkit to work.
 */
trait Toolkit {

  def createSurface(width: Int, height: Int): Surface
  def createWindow(): Window
}