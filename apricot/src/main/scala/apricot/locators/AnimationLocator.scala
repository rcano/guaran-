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
      new Resource { self =>
        def tpe = Resource.Type.Animation
        def path = p

        def subscribe[T](resourceSubscriber: Resource.Subscriber[T]) = {
          new Subscription[T] {
            var loadedFrames: Option[Resource.Content] = None
            var loadedScript: Option[Resource.Content] = None

            val framesSub = frames.subscribe({ c =>
                loadedFrames = Some(c)
                notifyIfNeeded()
              },
              _ => ()
            )
            val scriptSub =script.subscribe({ c =>
                loadedScript = Some(c)
                notifyIfNeeded()
              },
              _ => ()
            )

            var content = None
            
            def close() = {
              framesSub.close()
              scriptSub.close()
            }

            def notifyIfNeeded() = for {
              frames <- loadedFrames
              script <- loadedScript
            } {
              content = Some(resourceSubscriber.onLoad(self, frames ++ script.map(_.copy(name = "script"))))
            }
          }
        }

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
