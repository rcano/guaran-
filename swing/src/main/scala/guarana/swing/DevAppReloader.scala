package guarana.swing

import language.implicitConversions
import java.io.IOException
import java.net.URLClassLoader
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{Files , StandardWatchEventKinds, FileVisitor, FileVisitResult, Path, Paths, WatchEvent}
import javax.swing.SwingUtilities
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal
import scala.util.chaining._
import scala.util.matching.Regex
import scala.reflect.Selectable.reflectiveSelectable
import System.out.println

/**
 * Main application used during developement for hot reloading of the application using classloaders magic.
 * Note that this class is meant for a sbt like setup, since it the file monitoring will be done over the directories `target/scala-2.12/classes` and `target/scala-2.12/test-classes`
 */
object DevAppReloader {

  // val classesDirectories = Array(Paths.get("target/scala-0.26/classes").nn, Paths.get("target/scala-0.26/test-classes").nn).filter(Files.exists(_)).map(_.toAbsolutePath.nn)

  def launch(classesDirectories: Array[Path], reloadableClassPattern: Regex, mainClass: String, args: Array[String]) = {
    //install a monitor on the classes to detect a change
    val fileWatcher = classesDirectories.head.getFileSystem.newWatchService.nn
    classesDirectories foreach { classesDir =>
      Files.walkFileTree(classesDir, new FileVisitor[Path] {
          override def preVisitDirectory(dir: Path | UncheckedNull, attrs: BasicFileAttributes | UncheckedNull) = {
            dir.register(fileWatcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY)
            FileVisitResult.CONTINUE
          }
          override def postVisitDirectory(dir: Path | UncheckedNull, excec: IOException | UncheckedNull) = {
            FileVisitResult.CONTINUE
          }
          override def visitFile(file: Path | UncheckedNull, attrs: BasicFileAttributes | UncheckedNull) = {
            FileVisitResult.CONTINUE
          }
          override def visitFileFailed(file: Path | UncheckedNull, excec: IOException | UncheckedNull) = {
            FileVisitResult.TERMINATE
          }
        })
      classesDir.register(fileWatcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY)
    }

    new Thread("ClassesChangesWatcher") {
      override def run(): Unit = {
        println("watching")
        var updateFound = false
        var lastUpdate = System.currentTimeMillis

        while(!isInterrupted()) {
          val now = System.currentTimeMillis
          val wk = fileWatcher.poll(100, java.util.concurrent.TimeUnit.MILLISECONDS)
          if (wk != null) {
            wk.pollEvents.nn.asScala foreach {
              case watchEvent: WatchEvent[Path @unchecked] =>
                val context = wk.watchable.asInstanceOf[Path].resolve(watchEvent.context).nn

                if (watchEvent.kind == StandardWatchEventKinds.ENTRY_CREATE && Files.isDirectory(context)) {
                  context.register(fileWatcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY)
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
        println("watcher dying")
      }
    }.tap(_.setDaemon(true)).start()


    reloadApp(classesDirectories, reloadableClassPattern, mainClass, args)
  }

  var reloadCounter = 0
  var lastApplication: Class[?] | UncheckedNull = _
  @volatile var recompiling = false
  def reloadApp(classesDirectories: Array[Path], reloadableClassPattern: Regex, mainApp: String, otherArgs: Array[String]): Unit = {
    if (!recompiling) { // if I'm already recompiling, ignore the request. This might happen if the watcher thread detects many file changing in quick not not so quick intervals
      reloadCounter += 1
      println(Console.CYAN + "RELOADING #" + reloadCounter + Console.RESET)
      recompiling = true

      val classLoadingMxBean = java.lang.management.ManagementFactory.getClassLoadingMXBean().nn
      println("currently loaded classes " + classLoadingMxBean.getLoadedClassCount)
      SwingUtilities invokeLater new Runnable {
        def run: Unit = {
          var framePosition: Option[java.awt.Rectangle] = None

          //is there was an application, we need to dispose of it first
          if (lastApplication != null) {
            val loadedClassesBefore = classLoadingMxBean.getLoadedClassCount
            try lastApplication.getDeclaredMethod("close").nn.invoke(null)
            catch {
              case _: NoSuchMethodException => //expected
              case NonFatal(e) => println("stopping application failed"); e.printStackTrace()
            }
            framePosition = java.awt.Frame.getFrames.asInstanceOf[Array[java.awt.Frame]]
              .collect { case f: javax.swing.JFrame => f }
              .map { jf =>
                val pos = jf.getBounds.nn
                jf.setVisible(false)
                jf.dispose()
                pos
              }.headOption
            
            val cl = lastApplication.getClassLoader.asInstanceOf[URLClassLoader]
            System.gc()
            cl.close()
            val classesDifference = classLoadingMxBean.getLoadedClassCount - loadedClassesBefore
            if (classesDifference > 0) println(Console.YELLOW + s"WARNING: classes failed to be unloaded, leaked: $classesDifference" + Console.RESET)
            else println("unloaded classes " + classLoadingMxBean.getUnloadedClassCount)
          }

          println(classesDirectories.mkString("Root urls:[\n", "\n", "\n]"))
          val loader = new URLClassLoader(classesDirectories.map(_.toAbsolutePath.toUri.toURL).toArray) {
            //override default class loader behaviour to prioritize classes in this classloader
            override def loadClass(name: String | UncheckedNull, resolve: Boolean): Class[_] | UncheckedNull = {
              var res: Class[_] | UncheckedNull = findLoadedClass(name)
              val startTime = System.currentTimeMillis


              if reloadableClassPattern.findPrefixOf(name.asInstanceOf[String]).isDefined
                while res == null && System.currentTimeMillis - startTime < 60000 do//will retry for an entire minute for this class to appear  
                  try res = findClass(name)
                  catch case _: ClassFormatError | _: ClassNotFoundException => Thread.sleep(50) //give it some time
              else
                res = super.loadClass(name, false)

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
              res
            }
          }

          lastApplication = loader.loadClass(mainApp)
          lastApplication.getDeclaredMethod("main", classOf[Array[String]]).nn.invoke(null, otherArgs)

          framePosition foreach { pos =>
            java.awt.Frame.getFrames.asInstanceOf[Array[java.awt.Frame]]
              .collectFirst { case f: javax.swing.JFrame => f }
              .foreach(_.setBounds(pos))
          }
          recompiling = false
        }
      }
    }
  }
}
