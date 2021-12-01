package apricot
package locators

import better.files.{Resource => bfResource, File}
import guarana.unn
import scala.util.Using
import ResourceManager.*

class ClassLoaderLocator extends ResourceLocator {
  
  def list(path: Path): Iterable[Path] = Iterable.empty //class loader is not navigable
  def locate(path: Path): Option[Resource] = bfResource.url(path.toString).map(_ => ClasspathResource(path))

  private class ClasspathResource(val path: Path) extends Resource {
    def tpe = Resource.Type.fromFileExtension(File(path.segments.last).extension)
    def loadAndMonitor(): Unit = {
      val bytes = Using(bfResource.getAsStream(path.toString))(_.readAllBytes().unn).get
      signalLoaded(singleContent(bytes))
    }
  }
}
