package apricot
package locators

import better.files.{Resource => bfResource, File}
import guarana.AbstractToolkit
import guarana.unn
import scala.concurrent.Future
import scala.util.Using
import ResourceManager.*

class ClassLoaderLocator(engine: ApricotEngine[? <: AbstractToolkit]) extends ResourceLocator {
  import engine.resourceManager.given
  def list(path: Path): Iterable[Path] = Iterable.empty //class loader is not navigable
  def locate(path: Path): Option[Resource] = bfResource.url(path.toString).map(_ => ClasspathResource(path))

  private class ClasspathResource(val path: Path) extends Resource {
    def tpe = Resource.Type.fromFileExtension(File(path.segments.last).extension)
    def subscribe[T](resourceSubscriber: Resource.Subscriber[T]) = {
      val res = new SubscriptionImpl(resourceSubscriber)
      Future {
        val bytes = Using(bfResource.getAsStream(path.toString))(_.readAllBytes().unn).get
        res.load(singleContent(bytes))
      }
      res
    }
    class SubscriptionImpl[T](resourceSubscriber: Resource.Subscriber[T]) extends Subscription[T] {
      var content: Option[T] = None
      def close() = {}

      def load(c: Resource.Content): Unit = engine.onNextFrame {
        content = Some(resourceSubscriber.onLoad(ClasspathResource.this, c))
      }
      def unload(): Unit = engine.onNextFrame {
        content foreach (t => resourceSubscriber.onUnload(ClasspathResource.this, t))
      }
    }
  }
}
