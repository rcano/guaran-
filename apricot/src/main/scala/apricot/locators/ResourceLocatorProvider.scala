package apricot
package locators

import guarana.AbstractToolkit

trait ResourceLocatorProvider extends tools.SpiProvider[ResourceLocatorProvider]:
  def create(engine: ApricotEngine[? <: AbstractToolkit]): ResourceManager.ResourceLocator
