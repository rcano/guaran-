package apricot

import guarana.unn
import scala.collection.immutable.SortedSet
import scala.concurrent.ExecutionContext
import scala.quoted.*
import scala.ref.WeakReference
import java.util.ServiceLoader
import java.util.concurrent.Executors
import ResourceManager._

class ResourceManager {
  private val locators = collection.mutable.ArrayBuffer.empty[ResourceLocator]
  def register(locator: ResourceLocator): Unit = locators += locator
  def get(path: Path): Iterable[Resource] = locators.flatMap(_.locate(path))

  /** Shared threadpool for asynchronously reading resources */
  given ioExecutorService: ExecutionContext = ExecutionContext.fromExecutorService(
    Executors
      .newFixedThreadPool(
        2,
        r =>
          val t = new Thread(r, "ResourceManager_EC")
          t.setDaemon(true)
          t
      )
      .unn
  )
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
  def fileName: String = segments.last
  override def toString() = segments.mkString("/")
object Path:
  def apply(segments: String*): Path = Path(segments.toList)
  val Root = Path()

trait Resource {
  def path: Path
  def tpe: Resource.Type

  def subscribe[T](resourceSubscriber: Resource.Subscriber[T]): Subscription[T]
  def subscribe[T](onLoadFunction: Resource.Content => T, onUnloadFunction: T => Unit): Subscription[T] = subscribe(
    new Resource.Subscriber {
      def onLoad(resource: Resource, content: Resource.Content): T = onLoadFunction(content)
      def onUnload(resource: Resource, t: T): Unit = onUnloadFunction(t)
    }
  )

  def subscribeAs[T <: Resource.Type](using l: loader.ResourceLoader[T])(
      onLoad: l.Out => Unit = Resource.ignoreListener[l.Out],
      onUnload: l.Out => Unit = Resource.ignoreListener[l.Out]
  ): Subscription[l.Out] =
    subscribe(
      c =>
        val res = l.load(this, c)
        onLoad(res)
        res,
      c => l.unload(this, c)
    )

  trait Subscription[T] extends AutoCloseable {
    def content: Option[T]
    def resource: Resource = Resource.this

    def map[U](f: T => U): Subscription[U] = 
      val outer = this
      new Subscription[U] {
        lazy val content = outer.content map f
        override def resource = outer.resource
        def close() = outer.close()
      }
  }

  protected final def singleContent(bytes: IArray[Byte] | Array[Byte]): Resource.Content = IArray(
    Resource.ResourcePart(path.toString, bytes.asInstanceOf[IArray[Byte]])
  )
}

object Resource {
  enum Type:
    case Animation, Image, Sound, DynamicScript, Scene, Text, Directory, Renderable, Unk
    case Ext(mime: String)
  object Type {
    type Animation = Animation.type
    type Image = Image.type
    type Sound = Sound.type
    type DynamicScript = DynamicScript.type
    type Scene = Scene.type
    type Text = Text.type
    type Directory = Directory.type
    type Renderable = Renderable.type
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

  private[Resource] val ignoreListener = [C] => (c: C) => ()

  trait Subscriber[T] {
    def onLoad(resource: Resource, content: Content): T
    def onUnload(resource: Resource, t: T): Unit
  }
}
