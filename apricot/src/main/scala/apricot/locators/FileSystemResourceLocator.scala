package apricot
package locators

import language.experimental.erasedDefinitions

import better.files.{File, FileExtensions, FileMonitor}
import guarana.{nnn, unn, AbstractToolkit}
import guarana.animation.ScriptDsl.{given, *}
import java.util.concurrent.Executors
import java.nio.file.{Path => JPath, StandardWatchEventKinds, WatchEvent, WatchKey}
import scala.annotation.experimental
import scala.collection.mutable.{ArrayBuffer, HashMap}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.chaining.*
import scala.util.control.{NoStackTrace, NonFatal}
import ResourceManager.*

class FileSystemResourceLocator(root: File, engine: ApricotEngine[? <: AbstractToolkit]) extends ResourceLocator {

  import engine.resourceManager.given
  def mountPoint: Path = Path(Nil)
  def locate(subpath: Path): Option[Resource] =
    val res = root./(subpath.segments.mkString("/"))
    scribe.debug(s"locating path $res. Found? ${res.exists}")
    if res.exists then Some(FileResource(res, subpath))
    else None
  def list(path: Path) = root./(path.segments.mkString("/")).list.map(f => Path(path.segments :+ f.name)).toSeq

  private val trackedFiles = HashMap.empty[File, FileResource]
  private val subscribedFiles = HashMap.empty[File, ArrayBuffer[FileResource#FileSubscription[?]]]

  FileSystemWatcher // initialize early
  engine.scriptEngine run script {
    doUntil { _ =>
      FileSystemWatcher.poll()
      false
    }
  }

  private class FileResource(val file: File, val path: Path) extends Resource {
    lazy val tpe =
      if file.isDirectory then Resource.Type.Directory
      else Resource.Type.fromFileExtension(file.extension)

    def subscribe[T](resourceSubscriber: Resource.Subscriber[T]): Subscription[T] = {
      val res = FileSubscription(resourceSubscriber)
      trackedFiles(file) = this
      subscribedFiles.getOrElseUpdate(file, ArrayBuffer.empty) += res
      scribe.debug(s"tracking $file")
      Future { loadContent() }
      res
    }

    class FileSubscription[T](resourceSubscriber: Resource.Subscriber[T]) extends Subscription[T] {
      var content: Option[T] = None
      def close() = subscribedFiles.get(file) foreach (list =>
        list -= this
        content foreach resourceSubscriber.onUnload
        if (list.isEmpty) {
          subscribedFiles -= file
          trackedFiles -= file
          scribe.debug(s"stopped tracking $file")
        }
      )

      def load(c: Resource.Content): Unit = engine.onNextFrame {
        content = Some(resourceSubscriber.onLoad(c))
      }
      def unload(): Unit = engine.onNextFrame {
        content foreach resourceSubscriber.onUnload
      }
    }

    def loadContent() = {
      val data =
        if !file.isDirectory then
          scribe.debug(s"Loading file ${file}'s content")
          singleContent(file.byteArray)
        else
          scribe.debug(s"Loading directory ${file}'s content")
          file.list
            .map(f => Resource.ResourcePart(s"$path/${f.name}", f.byteArray.asInstanceOf[IArray[Byte]]))
            .toArray
            .sortBy(_.name)
            .tap(files => scribe.debug(files.mkString("Parts: [\n", "\n", "\n]")))
            .asInstanceOf[IArray[Resource.ResourcePart]]

      subscribedFiles.get(file) foreach (_ foreach (_.load(data)))
    }

    def reload(): Unit = {
      try subscribedFiles.get(file) foreach (_ foreach (_.unload()))
      catch case NonFatal(e) => scribe.error(s"Error unloading $this", e)
      try loadContent()
      catch case NonFatal(e) => scribe.error(s"Error loading $this", e)
    }
    override def toString() = s"FileResource(/$path)"
  }

  object FileSystemWatcher {
    import scala.jdk.CollectionConverters.*
    val watcher = root.newWatchService
    (Iterator(root) ++ root.listRecursively).filter(_.isDirectory).foreach(f =>
      scribe.debug(s"Monitoring $f")
      f.register(watcher, File.Events.all)
    )

    var seenLastFrame = collection.mutable.Set.empty[File]
    var seeingThisFrame = collection.mutable.Set.empty[File]

    def poll(): Unit = {
      var key: WatchKey | Null = null
      while { key = watcher.poll(); key != null } do {
        key.pollEvents.unn.forEach { evt =>
          scribe.trace(s"${key.unn.watchable}/${evt.unn.context} - ${evt.unn.kind} - count ${evt.unn.count}")
          evt match
            case evt: WatchEvent[JPath @unchecked]
                if evt.kind == StandardWatchEventKinds.ENTRY_MODIFY || evt.kind == StandardWatchEventKinds.ENTRY_CREATE =>
              val keyPath = File(key.unn.watchable.asInstanceOf[JPath])
              val fullPath = keyPath / evt.context.unn.getFileName().toString
              seeingThisFrame += keyPath
              seeingThisFrame += fullPath
              seenLastFrame -= keyPath
              seenLastFrame -= fullPath

              // recursively monitor new directories
              if evt.kind == StandardWatchEventKinds.ENTRY_CREATE && fullPath.isDirectory then
                (Iterator(fullPath) ++ fullPath.listRecursively).foreach { p =>
                  if p.isDirectory then
                    scribe.trace(s"monitoring new direcotry $p")
                    p.register(watcher, File.Events.all)

                  seeingThisFrame += p // add to the detected list for reporting
                }
            case _ =>
        }
        key.reset()
      }
      // anything that remains in the seenLastFrame means it wasn't seen this frame, we must try to notify
      for (p <- seenLastFrame) maybeNotify(p)
      seenLastFrame.clear()
      // swap the lists
      seenLastFrame = seeingThisFrame.tap(_ => seeingThisFrame = seenLastFrame)
    }

    private def maybeNotify(f: File): Unit = {
      scribe.debug(s"maybe notify $f")
      trackedFiles
        .get(f)
        .foreach(r =>
          scribe.debug(s"reloading $r")
          Future { r.reload() }
        )
    }
  }
}

// class FileSystemResourceLocatorProvider extends ResourceLocatorProvider:
//   @experimental erased def spi = registerSpi
//   def create(engine: ApricotEngine[? <: AbstractToolkit]) = new FileSystemResourceLocator(File.currentWorkingDirectory, engine)
