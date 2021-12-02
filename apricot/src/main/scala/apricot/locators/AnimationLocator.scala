package apricot
package locators

import language.experimental.erasedDefinitions
import scala.annotation.experimental

class AnimationLocator(engine: ApricotEngine[? <: guarana.AbstractToolkit]) extends SyntheticLocator(Path("animations")) {
  import engine.resourceManager as manager
  def list(path: Path) = Iterable.empty

  override def locateSynth(path: Path) = {
    for {
      frames <- manager.get(Path("assets" :: "animations":: path.segments.tail ::: "frames" :: Nil)).headOption
      script <- manager.get(Path("assets" :: "scripts" :: path.segments.tail ::: "AnimationScript.class" :: Nil)).headOption  
    } yield {
      val p = path
      new Resource {
        def tpe = Resource.Type.Animation
        def path = p

        private var loadedFrames: Option[Resource.Content] = None
        private var loadedScript: Option[Resource.Content] = None

        def loadAndMonitor() = {
          frames.addOnLoad(c =>
            loadedFrames = Some(c)
            notifyIfNeeded()
          )
          script.addOnLoad(c =>
            loadedScript = Some(c)
            notifyIfNeeded()
          )
        }
        def notifyIfNeeded() = for {
          frames <- loadedFrames
          script <- loadedScript
        } signalLoaded(frames ++ script.map(_.copy(name = "script")))

        override def toString = s"Animation($path)"
      }
    }
  }
}

class AnimationLocatorProvider extends ResourceLocatorProvider, tools.SpiProvider[ResourceLocatorProvider] {
  @experimental
  erased def spi = registerSpi
  def create(engine: ApricotEngine[? <: guarana.AbstractToolkit]) = AnimationLocator(engine)
}
