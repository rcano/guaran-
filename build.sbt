name := "guarana"

inThisBuild(
  Seq(
    organization := "guarana",
    version := "0.0.8-SNAPSHOT",
    scalaVersion := "3.5.1",
    fork := true,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test",
    Compile / packageDoc / publishArtifact := false,
    ThisBuild / scalacOptions ++= Seq(
      "-Yexplicit-nulls",
      "-deprecation",
      "-Wunused:all",
      "-unchecked",
      "-language:implicitConversions",
      "-rewrite", "-source", "3.5-migration"
    ),
  ) ++ addCommandAlias("enableDebug", """set javaOptions += "-agentlib:jdwp=transport=dt_socket,server=y,address=5555,suspend=y"""")
)

lazy val guaraná = Project(id = "guarana", base = file(".")).aggregate(coreJvm, swing, qt, apricot, apricotVk)

lazy val scribeVersion = "3.15.0"

lazy val core = // select supported platforms
  crossProject(JVMPlatform, NativePlatform, JSPlatform)
    .crossType(CrossType.Pure)
    .withoutSuffixFor(JVMPlatform)
    .settings(
      libraryDependencies ++= Seq(
        "com.github.rssh" %% "dotty-cps-async" % "0.9.21",
        "com.outr" %%% "scribe" % scribeVersion,
      )
    )
    // configure JVM settings
    .jvmSettings( /* ... */ )
    // configure Scala-Native settings
    .nativeSettings( /* ... */ ) // defined in sbt-scala-native

lazy val coreJvm = core.jvm
lazy val coreNative = core.native
lazy val coreJs = core.js

lazy val qt = Project(id = "guarana-qt", base = file("qt"))
  .dependsOn(coreJvm)
  .settings(
    // qt/envVars += ("QT_DEBUG_PLUGINS" -> "1"),
    libraryDependencies += ("com.github.pathikrit" %% "better-files" % "3.9.2").cross(CrossVersion.for3Use2_13),
    // libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.5",
    // libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.5" classifier "linux-x86_64",
    // libraryDependencies += "org.bytedeco" % "qt" % "5.15.2-1.5.5",
    // libraryDependencies += "org.bytedeco" % "qt" % "5.15.2-1.5.5" classifier "linux-x86_64",
    libraryDependencies ++= {
      val qtJambiVersion = "6.6.2"
      Seq(
        "io.qtjambi" % "qtjambi" % qtJambiVersion,
        "io.qtjambi" % "qtjambi-native-linux-x64" % qtJambiVersion,
        "io.qtjambi" % "qtjambi-svg" % qtJambiVersion,
        "io.qtjambi" % "qtjambi-svgwidgets" % qtJambiVersion,
        "io.qtjambi" % "qtjambi-svg-native-linux-x64" % qtJambiVersion,
        "io.qtjambi" % "qtjambi-svgwidgets-native-linux-x64" % qtJambiVersion,
      )
    },
    // Compile / unmanagedSourceDirectories ++= { if (scalaVersion.value == "3.3.0-RC2") Seq(baseDirectory.value / "src/main/scala-3.3.0") else Seq() },

    // javaOptions ++= Seq(
    //   "-Djava.library.path=/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib:lib/qtjambi-5.15-binaries-linux64-gcc/lib"
    // )
    // javaOptions ++= Seq("-Djava.library.path=lib/binaries:/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib")
  )

lazy val moduleJars = taskKey[Seq[(Attributed[File], java.lang.module.ModuleDescriptor)]]("moduleJars")
lazy val swing = Project(id = "guarana-swing", base = file("swing"))
  .dependsOn(coreJvm)
  .settings(
    // scalacOptions -= "-Yexplicit-nulls",
    libraryDependencies ++= Seq(
      ("com.github.pathikrit" %% "better-files" % "3.9.2").cross(CrossVersion.for3Use2_13),
      "org.apache.xmlgraphics" % "batik-swing" % "1.18",
      "com.formdev" % "flatlaf" % "3.5.1" % "provided",
      "com.jhlabs" % "filters" % "2.0.235-1" % "provided",
      "io.dropwizard.metrics" % "metrics-core" % "4.2.26" % "test",
      "org.scalatest" %% "scalatest-funsuite" % "3.2.19" % "test",
      "com.typesafe.play" %% "play-json" % "2.10.6" % "test",
    ),
    ThisBuild / moduleJars := {
      val attributedJars = (Compile / dependencyClasspathAsJars).value.filterNot(_.metadata(moduleID.key).organization == "org.scala-lang")
      val modules = attributedJars.flatMap { aj =>
        try {
          val module = java.lang.module.ModuleFinder.of(aj.data.toPath).findAll().iterator.next.descriptor
          Some(aj -> module)
        } catch { case _: java.lang.module.FindException => None }
      }
      modules
    },
    // ThisBuild / javaOptions ++= {
    //   val modules = moduleJars.value
    //   if (modules.isEmpty) Seq()
    //   else
    //     Seq(
    //       "--add-modules=" + modules.map(_._2.name).mkString(","),
    //       "--module-path=" + modules.map(_._1.data.getAbsolutePath).mkString(java.io.File.pathSeparator)
    //     )
    // },
    ThisBuild / javaOptions += "-Xmx100m",
    ThisBuild / javaOptions ++= Seq("-Dsun.java2d.uiScale.enabled=true", "-Dsun.java2d.uiScale=2"),
    ThisBuild / outputStrategy := Some(StdoutOutput)
  )

lazy val guaranaTheme = Project(id = "theme", base = file("swing/theme"))
  .dependsOn(swing)
  .settings(
    libraryDependencies ++= Seq(
      "com.jhlabs" % "filters" % "2.0.235-1",
    )
  )

lazy val webDefsGen = Project(id = "web-defs-gen", base = file("webDefsGen"))
  .dependsOn(coreJvm)
  .settings(
    scalacOptions -= "-Yexplicit-nulls",
    libraryDependencies += ("com.github.pathikrit" %% "better-files" % "3.9.2" cross CrossVersion.for3Use2_13)
  )

import org.scalajs.linker.interface.ModuleSplitStyle
lazy val web = Project(id = "guarana-web", base = file("web"))
  .dependsOn(coreJs)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalacOptions -= "-Yexplicit-nulls",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.4.0",
    ),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule).withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("guarana.web")))
    },
    scalaJSUseMainModuleInitializer := true
  )

lazy val lwjglVersion = "3.3.4"
lazy val lwjglClassifier = "natives-linux"


lazy val apricot = Project(id = "apricot", base = file("apricot"))
  .settings(
    scalacOptions += "-experimental",
    libraryDependencies ++= Seq(
      ("com.github.pathikrit" %% "better-files" % "3.9.2").cross(CrossVersion.for3Use2_13),
      "com.google.code.findbugs" % "jsr305" % "3.0.2",
      "org.lwjgl" % "lwjgl" % lwjglVersion classifier lwjglClassifier,
      "org.lwjgl" % "lwjgl-glfw" % lwjglVersion,
      "org.lwjgl" % "lwjgl-glfw" % lwjglVersion classifier lwjglClassifier,
      "org.lwjgl" % "lwjgl-opengl" % lwjglVersion,
      "org.lwjgl" % "lwjgl-opengl" % lwjglVersion classifier lwjglClassifier,
      "io.dropwizard.metrics" % "metrics-core" % "4.2.26",
      "com.outr" %% "scribe" % scribeVersion,
      "com.outr" %% "scribe-file" % scribeVersion,
      "org.scala-lang" %% "scala3-tasty-inspector" % scalaVersion.value % "provided,runtime"
    ),
    javaOptions ++= Seq(
      "-Djava.library.path=/home/randa/Development/guarana/qt/lib/qtjambi-5.15-binaries-linux64-gcc/lib:/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib",
      // "--add-modules",
      // "jdk.incubator.foreign"
    )
    // javaOptions ++= Seq("-Djava.library.path=/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib:/home/randa/Development/guarana/qt/lib/qtjambi-5.15-binaries-linux64-gcc/lib")
  )
  .dependsOn(coreJvm)

lazy val apricotSkia = Project(id = "apricotSkia", base = file("apricotSkia"))
  .settings(
    scalacOptions += "-experimental",
    libraryDependencies ++= Seq(
      "io.github.humbleui.skija" % "skija-linux" % "0.96.0",
      "com.github.blemale" %% "scaffeine" % "5.1.2",
    )
  )
  .dependsOn(apricot, qt)

import scala.sys.process._
import java.nio.file.{Files, Path}
import sbt.nio.Keys._

val shaderObjects = taskKey[Seq[Path]]("Compiles .frag and .vert files into spir-v files")
lazy val apricotVk = Project(id = "apricotVk", base = file("apricotVk"))
  .settings(
    scalacOptions -= "-Yexplicit-nulls",
    scalacOptions ++= Seq("-Ydebug", "-Ydebug-error", "-Ydebug-unpickling", "-experimental"),
    libraryDependencies ++= Seq(
      "org.lwjgl" % "lwjgl-vulkan" % lwjglVersion,
    ),
    shaderObjects / fileInputs ++= Seq(
      baseDirectory.value.toGlob / "src" / "main" / "resources" / "shader" / RecursiveGlob / "*.vert",
      baseDirectory.value.toGlob / "src" / "main" / "resources" / "shader" / RecursiveGlob / "*.frag",
    ),
    shaderObjects := {
      val logger = streams.value.log
      val baseDir = baseDirectory.value / "src" / "main" / "resources" / "shader"
      val destDir = (Compile / classDirectory).value / "shader"
      val fileMapper = sbt.io.Path.rebase(baseDir, destDir)
      def outputPath(p: Path) = (fileMapper(p.toFile).get.getParentFile / (p.getFileName.toString + ".spv")).toPath
      val changes = shaderObjects.inputFileChanges
      changes.deleted.foreach { p =>
        logger.info(s"deleting $p")
        Files.deleteIfExists(outputPath(p))
      }
      (changes.created ++ changes.modified).toSet foreach { (p: Path) =>
        logger.info(s"compiling shader $p")
        val dest: java.nio.file.Path = outputPath(p)
        Files.createDirectories(dest.getParent)
        Process(
          Seq("glslangValidator", "-H", "-o", dest.toString, p.toString)
        ) ! logger
      }
      shaderObjects.inputFiles.map(f => outputPath(f))
    },
    Compile / compile / compileInputs := (Compile / compile / compileInputs)
      .dependsOn(shaderObjects)
      .value,
    javaOptions ++= Seq(
      // "--add-modules",
      // "jdk.incubator.foreign",
      "--enable-native-access=ALL-UNNAMED",
    )
  )
  .dependsOn(apricot)

lazy val jmh = Project("jmh", base = file("jmh"))
  .dependsOn(coreJvm)
  .enablePlugins(JmhPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.chrislo27" % "paintbox" % "0.1.1"
    ),
    resolvers += "jitpack.io" at "https://jitpack.io",
    resolvers += Resolver.mavenLocal
  )
