package apricot

import language.experimental.erasedDefinitions

import scala.annotation.experimental
import scala.collection.immutable.SortedSet
import scala.quoted.*

import ResourceManager._
import scala.ref.WeakReference
import java.util.ServiceLoader

class ResourceManager {
  private val locators = collection.mutable.ArrayBuffer.empty[ResourceLocator]
  def register(locator: ResourceLocator): Unit = locators += locator
  def get(path: Path): Iterable[Resource] = locators.flatMap(_.locate(path))
}

object ResourceManager {
  trait ResourceLocator:
    def locate(path: Path): Option[Resource]
    def list(path: Path): Iterable[Path]
    def listRecursively(): Iterator[Path] =
      def rec(path: Path): Iterator[Path] = list(path).iterator.flatMap(rec)
      rec(Path.Root)
}

case class Path(segments: List[String]):
  def /(segment: String): Path = Path(segments :+ segment)
  override def toString() = segments.mkString("/")
object Path:
  def apply(segments: String*): Path = Path(segments.toList)
  val Root = Path()

trait Resource {
  def path: Path
  def tpe: Resource.Type
  private val loadListenersBuff = collection.mutable.ListBuffer.empty[WeakReference[(Resource.Content => Unit)]]
  private val unloadListenersBuff = collection.mutable.ListBuffer.empty[WeakReference[() => Unit]]
  def addOnLoad(l: (Resource.Content => Unit)): Unit = {
    loadListenersBuff += WeakReference(l)
    //on the first listener, load and start monitoring
    if loadListenersBuff.nonEmpty then loadAndMonitor()
  }
  def addOnUnload(l: () => Unit): Unit = unloadListenersBuff += WeakReference(l)
  protected final def loadListeners: Iterable[(Resource.Content => Unit)] = expunge(loadListenersBuff).map(_())
  protected final def unloadListeners: Iterable[() => Unit] = expunge(unloadListenersBuff).map(_())

  /** Implementors must call [[signalLoaded]] and [[signalUnloaded]] appropriately */
  protected def loadAndMonitor(): Unit

  private def expunge[T <: AnyRef](buff: collection.mutable.ListBuffer[WeakReference[T]]): buff.type =
    buff.filterInPlace(ref => ref.get.isDefined)

  protected final def signalLoaded(content: Resource.Content): Unit = loadListeners.foreach(_(content))
  protected final def signalUnloaded(): Unit = unloadListeners.foreach(_())

  protected final def singleContent(bytes: IArray[Byte] | Array[Byte]): Resource.Content = IArray(Resource.ResourcePart(path.toString, bytes.asInstanceOf[IArray[Byte]]))
}

object Resource {
  enum Type:
    case Animation, Image, Sound, DynamicScript, Scene, Text, Directory, Unk
    case Ext(mime: String)
  object Type {
    type Animation = Animation.type
    type Image = Image.type
    type Sound = Sound.type
    type DynamicScript = DynamicScript.type
    type Scene = Scene.type
    type Text = Text.type
    type Directory = Directory.type
    type Unk = Unk.type

    def fromFileExtension(extension: Option[String]): Type =
      extension match
        case Some(".png" | ".jpg" | ".jpeg") => Resource.Type.Image
        case Some(".opus" | ".wav" | ".ogg") => Resource.Type.Sound
        case Some(ext) => Resource.Type.Ext("application/x-" + extension.drop(1))
        case _ => Resource.Type.Unk
  }
  type Content = IArray[ResourcePart]
  case class ResourcePart(name: String, content: IArray[Byte])

  trait Holder {
    private val listeners = collection.mutable.ListBuffer.empty[AnyRef]
    private val trackedLoadedResources = collection.mutable.HashMap.empty[Resource, Any]

    given Holder = this

    def onLoad(r: Resource)(f: Content => Unit): Unit =
      listeners += f
      r.addOnLoad(f)

    def onUnload(r: Resource)(f: () => Unit): Unit =
      listeners += f
      r.addOnUnload(f)

    extension (r: Resource)
      def loadAs[T <: Type](using l: loader.ResourceLoader[T])(f: l.Out => Unit): Unit =
        onLoad(r)(bytes =>
          val loaded = l.load(r, bytes)
          trackedLoadedResources(r) = loaded
          f(loaded)
        )
        onUnload(r)(() => l.unload(r, trackedLoadedResources(r).asInstanceOf[l.Out]))
  }
}
