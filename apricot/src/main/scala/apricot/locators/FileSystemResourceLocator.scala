package apricot
package locators

import language.experimental.erasedDefinitions

import better.files.{File, FileExtensions, FileMonitor}
import guarana.{nnn, unn, AbstractToolkit}
import guarana.animation.ScriptDsl.{given, *}
import java.util.concurrent.Executors
import java.nio.file.{Path => JPath, StandardWatchEventKinds, WatchEvent, WatchKey}
import scala.annotation.experimental
import scala.concurrent.ExecutionContext
import scala.util.chaining.*
import scala.util.control.{NoStackTrace, NonFatal}
import ResourceManager.*

class FileSystemResourceLocator(root: File, engine: ApricotEngine[? <: AbstractToolkit]) extends ResourceLocator {

  def mountPoint: Path = Path(Nil)
  def locate(subpath: Path): Option[Resource] =
    val res = root./(subpath.segments.mkString("/"))
    if res.exists then Some(FileResource(res, subpath))
    else None
  def list(path: Path) = root./(path.segments.mkString("/")).list.map(f => Path(path.segments :+ f.name)).toSeq

  private val trackedFiles = collection.mutable.HashMap.empty[File, FileResource]

  FileSystemWatcher // initialize early
  engine.scriptEngine run script {
    doUntil { _ =>
      FileSystemWatcher.poll()
      false
    }
  }

  private def watch(r: FileResource) = {
    val p = root./(r.path.segments.mkString("/"))
    trackedFiles(p) = r
    scribe.debug(s"tracking $p")
  }

  private class FileResource(file: File, val path: Path) extends Resource {
    lazy val tpe =
      if file.isDirectory then Resource.Type.Directory
      else Resource.Type.fromFileExtension(file.extension)

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

      signalLoaded(data)
    }

    protected def loadAndMonitor(): Unit = {
      watch(this)
      loadContent()
    }

    def reload(): Unit = {
      try signalUnloaded()
      catch case NonFatal(e) => new Exception(s"Error unloading $this", e).printStackTrace()
      try loadContent()
      catch case NonFatal(e) => new Exception(s"Error loading $this", e).printStackTrace()
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
          r.reload()
        )
    }
  }
}

// class FileSystemResourceLocatorProvider extends ResourceLocatorProvider:
//   @experimental erased def spi = registerSpi
//   def create(engine: ApricotEngine[? <: AbstractToolkit]) = new FileSystemResourceLocator(File.currentWorkingDirectory, engine)
