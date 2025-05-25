package guarana
package swing

import language.implicitConversions
import java.io.IOException
import java.net.URLClassLoader
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, FileVisitor, Files, Path, StandardWatchEventKinds, WatchEvent}
import javax.swing.SwingUtilities
import scala.jdk.CollectionConverters.*
import scala.util.control.NonFatal
import scala.util.chaining.*
import scala.util.matching.Regex

/** Main application used during developement for hot reloading of the application using classloaders magic. Note that this class is meant
  * for a sbt like setup, since it the file monitoring will be done over the directories `target/scala-2.12/classes` and
  * `target/scala-2.12/test-classes`
  */
object DevAppReloader {
  val out = System.out.unn

  trait Reloadable {
    def onReload(currentFrame: Option[javax.swing.JFrame]): Unit
  }

  // val classesDirectories = Array(Paths.get("target/scala-0.26/classes").nn, Paths.get("target/scala-0.26/test-classes").nn).filter(Files.exists(_)).map(_.toAbsolutePath.nn)

  def launch(classesDirectories: Array[Path], reloadableClassPattern: Regex, mainClass: String, args: Array[String]) = {
    //install a monitor on the classes to detect a change
    classesDirectories.foreach(f => if (!Files.exists(f)) throw new IllegalStateException(s"directory $f doesn't exists"))
    val fileWatcher = classesDirectories.head.getFileSystem.unn.newWatchService.unn
    classesDirectories foreach { classesDir =>
      Files.walkFileTree(
        classesDir,
        new FileVisitor[Path] {
          override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes) = {
            dir.register(
              fileWatcher,
              StandardWatchEventKinds.ENTRY_CREATE,
              StandardWatchEventKinds.ENTRY_DELETE,
              StandardWatchEventKinds.ENTRY_MODIFY
            )
            FileVisitResult.CONTINUE
          }
          override def postVisitDirectory(dir: Path, excec: IOException) = {
            FileVisitResult.CONTINUE
          }
          override def visitFile(file: Path, attrs: BasicFileAttributes) = {
            FileVisitResult.CONTINUE
          }
          override def visitFileFailed(file: Path, excec: IOException) = {
            FileVisitResult.TERMINATE
          }
        }
      )
      classesDir.register(
        fileWatcher,
        StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_DELETE,
        StandardWatchEventKinds.ENTRY_MODIFY
      )
    }

    new Thread("ClassesChangesWatcher") {
      override def run(): Unit = {
        out.println("watching")
        var updateFound = false
        var lastUpdate = System.currentTimeMillis

        while (!isInterrupted()) {
          val now = System.currentTimeMillis
          val wk = fileWatcher.poll(100, java.util.concurrent.TimeUnit.MILLISECONDS)
          if (wk != null) {
            wk.pollEvents.nn.asScala foreach { case watchEvent: WatchEvent[Path @unchecked] =>
              val context = wk.watchable.asInstanceOf[Path].resolve(watchEvent.context).nn
              out.println(s"detected change $context")

              if (watchEvent.kind == StandardWatchEventKinds.ENTRY_CREATE && Files.isDirectory(context)) {
                context.register(
                  fileWatcher,
                  StandardWatchEventKinds.ENTRY_CREATE,
                  StandardWatchEventKinds.ENTRY_DELETE,
                  StandardWatchEventKinds.ENTRY_MODIFY
                )
              }
            }
            wk.reset()
            updateFound = true
            lastUpdate = now
          }
          if (updateFound && (now - lastUpdate) > 1000) { //if there was some time ago, trigger reloading
            updateFound = false
            reloadApp(classesDirectories, reloadableClassPattern, mainClass, args)
          }
        }
        out.println("watcher dying")
      }
    }.tap(_.setDaemon(true)).start()

    reloadApp(classesDirectories, reloadableClassPattern, mainClass, args)
  }

  var reloadCounter = 0
  var lastApplication: Class[?] | Null = scala.compiletime.uninitialized
  @volatile var recompiling = false
  def reloadApp(classesDirectories: Array[Path], reloadableClassPattern: Regex, mainApp: String, otherArgs: Array[String]): Unit = {
    if (!recompiling) { // if I'm already recompiling, ignore the request. This might happen if the watcher thread detects many file changing in quick not not so quick intervals
      reloadCounter += 1
      out.println(Console.CYAN + s"RELOADING #${reloadCounter} - ${java.time.Instant.now()}" + Console.RESET)
      recompiling = true

      val classLoadingMxBean = java.lang.management.ManagementFactory.getClassLoadingMXBean().nn
      out.println("currently loaded classes " + classLoadingMxBean.getLoadedClassCount)
      SwingUtilities `invokeLater` new Runnable {
        def run: Unit = {
          var framePosition: Option[java.awt.Rectangle] = None

          //if there was an application, we need to dispose of it first
          if (lastApplication != null) {
            val loadedClassesBefore = classLoadingMxBean.getLoadedClassCount
            try lastApplication.unn.getDeclaredMethod("close").nn.invoke(null)
            catch {
              case _: NoSuchMethodException => //expected
              case NonFatal(e) => out.println("stopping application failed"); e.printStackTrace()
            }
            framePosition = java.awt.Frame.getFrames
              .asInstanceOf[Array[java.awt.Frame]]
              .collect { case f: javax.swing.JFrame => f }
              .map { jf =>
                val pos = jf.getBounds.nn
                jf.setVisible(false)
                jf.dispose()
                println(s"disposing frame $jf and retaining bounds $pos")
                pos
              }
              .lastOption

            val cl = lastApplication.unn.getClassLoader.asInstanceOf[URLClassLoader]
            System.gc()
            cl.close()
            val classesDifference = classLoadingMxBean.getLoadedClassCount - loadedClassesBefore
            if (classesDifference > 0)
              out.println(Console.YELLOW + s"WARNING: classes failed to be unloaded, leaked: $classesDifference" + Console.RESET)
            else out.println("unloaded classes " + classLoadingMxBean.getUnloadedClassCount)
          }

          out.println(classesDirectories.mkString("Root urls:[\n", "\n", "\n]"))
          val loader = new URLClassLoader(classesDirectories.map(_.toAbsolutePath.unn.toUri.unn.toURL.unn).toArray) {
            //override default class loader behaviour to prioritize classes in this classloader
            override def loadClass(name: String, resolve: Boolean): Class[?] = {
              var res: Class[?] | Null = findLoadedClass(name)
              val startTime = System.currentTimeMillis

              if reloadableClassPattern.findPrefixOf(name.asInstanceOf[String]).isDefined then
                while res == null && System.currentTimeMillis - startTime < 60000 do //will retry for an entire minute for this class to appear
                  try res = findClass(name)
                  catch case _: ClassFormatError | _: ClassNotFoundException => Thread.sleep(50) //give it some time
              else res = super.loadClass(name, false)

              // while (res == null && System.currentTimeMillis - startTime < 60000) {//will retry for an entire minute for this class to appear
              //   try res = findClass(name)
              //   catch {
              //     case e: ClassNotFoundException =>
              //       try res = super.loadClass(name, false)
              //       catch { case e: ClassNotFoundException => Thread.sleep(50) } //sleep 50ms and retry
              //     case e: ClassFormatError => Thread.sleep(50) //give it some time
              //   }
              // }
              if (res == null) throw new ClassNotFoundException(name)
              if (resolve) resolveClass(res)
              res.unn
            }
          }

          lastApplication = loader.loadClass(mainApp)
          lastApplication.unn.getDeclaredMethod("main", classOf[Array[String]]).unn.invoke(null, otherArgs)

          framePosition foreach { pos =>
            SwingUtilities.invokeLater(() =>
              val frames = java.awt.Frame.getFrames
                .asInstanceOf[Array[java.awt.Frame]]
                .collect { case f: javax.swing.JFrame => f }
              val newFrame = frames.lastOption
              out.println(s"Attempting to set bounds $pos to found frame $newFrame")
              newFrame.foreach(_.setBounds(pos))
            )
          }
          recompiling = false
        }
      }
    }
  }
}
