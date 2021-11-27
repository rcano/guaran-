package apricot
package locators

import language.experimental.erasedDefinitions

import better.files.{File, FileMonitor, FileExtensions}
import guarana.{nnn, unn, AbstractToolkit}
import guarana.animation.ScriptDsl.{given, *}
import java.util.concurrent.Executors
import java.nio.file.{Path => JPath, StandardWatchEventKinds, WatchEvent, WatchKey}
import scala.annotation.experimental
import scala.concurrent.ExecutionContext
import scala.util.chaining.*
import scala.util.control.{NonFatal, NoStackTrace}
import ResourceManager.*

class FileSystemResourceLocator(root: File, engine: ApricotEngine[? <: AbstractToolkit]) extends ResourceLocator {

  def mountPoint: Path = Path(Nil)
  def locate(subpath: Path): Option[Resource] = 
    val res = root./(subpath.segments.mkString("/"))
    if res.exists then Some(FileResource(res, subpath))
    else None
  def list(path: Path) = root./(path.segments.mkString("/")).list.map(f => Path(path.segments :+ f.name)).toSeq


  private val trackedFiles = collection.mutable.HashMap.empty[File, FileResource]

  engine.scriptEngine run script {
    doUntil { _ =>
      FileSystemWatcher.poll()
      false
    }
  }

  private def watch(r: FileResource) = {
    val p = root./(r.path.segments.mkString("/"))
    trackedFiles(p) = r
    p.parent.register(FileSystemWatcher.watcher)
    scribe.debug(s"Monitoring ${p.parent}")
  }

  private class FileResource(file: File, val path: Path) extends Resource {
    lazy val tpe =
      if file.isDirectory then Resource.Type.Directory
      else file.extension match
        case Some(".png" | ".jpg" | ".jpeg") => Resource.Type.Image
        case Some(".opus" | ".wav" | ".ogg") => Resource.Type.Sound
        case Some(ext) => Resource.Type.Ext("application/x-" + ext.drop(1))
        case _ => Resource.Type.Unk

    protected def loadAndMonitor(): Unit = {
      if file.isRegularFile then watch(this)
      val data = file.byteArray
      signalLoaded(data)
    }

    def reload(): Unit = {
      try signalUnloaded() catch case NonFatal(e) => new Exception(s"Error unloading $this", e).printStackTrace()
      try {
        val data = file.byteArray
        signalLoaded(data)
      } catch case NonFatal(e) => new Exception(s"Error loading $this", e).printStackTrace()
    }

    override def toString() = s"FileResource(/$path)"
  }

  object FileSystemWatcher {
    import scala.jdk.CollectionConverters.*
    val watcher = root.newWatchService
    root.register(watcher, File.Events.all)

    val seenLastFrame = collection.mutable.Set.empty[JPath]
    val seeingThisFrame = collection.mutable.Set.empty[JPath]

    def poll(): Unit = {
      var key: WatchKey | Null = null
      while {key = watcher.poll(); key != null} do
        key.pollEvents.unn.forEach { evt =>
          scribe.trace(s"${key.unn.watchable}/${evt.unn.context} - ${evt.unn.kind} - count ${evt.unn.count}")
          evt match
          case evt: WatchEvent[JPath @unchecked] if evt.kind == StandardWatchEventKinds.ENTRY_MODIFY || evt.kind == StandardWatchEventKinds.ENTRY_CREATE =>
            val fullPath = File(key.unn.watchable.asInstanceOf[JPath]) / evt.context.unn.getFileName().toString
            scribe.debug(s"maybe notify $fullPath")
            maybeNotify(fullPath)
          case _ => 
        }
        key.reset()
    }

    private def maybeNotify(f: File): Unit = trackedFiles.get(f).foreach(r =>
      scribe.debug(s"reloading $r")
      r.reload()
    )
  }
}

// class FileSystemResourceLocatorProvider extends ResourceLocatorProvider:
//   @experimental erased def spi = registerSpi
//   def create(engine: ApricotEngine[? <: AbstractToolkit]) = new FileSystemResourceLocator(File.currentWorkingDirectory, engine)