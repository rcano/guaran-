name := "guarana"

inThisBuild(
  Seq(
    organization := "guarana",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.2.0-RC1",
    fork := true,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test",
    Compile / packageDoc / publishArtifact := false,
    ThisBuild / scalacOptions ++= Seq(
      "-Yexplicit-nulls",
      "-deprecation",
      "-unchecked",
      "-language:implicitConversions"
    ),
  ) ++ addCommandAlias("enableDebug", """set javaOptions += "-agentlib:jdwp=transport=dt_socket,server=y,address=5555,suspend=y"""")
)

lazy val guaraná = Project(id = "guarana", base = file(".")).aggregate(coreJvm)

lazy val core = // select supported platforms
  crossProject(JVMPlatform, NativePlatform)
    .crossType(CrossType.Pure)
    .withoutSuffixFor(JVMPlatform)
    .settings(
      libraryDependencies ++= Seq(
        "com.github.rssh" %% "dotty-cps-async" % "0.9.8",
      )
    )
    // configure JVM settings
    .jvmSettings( /* ... */ )
    // configure Scala-Native settings
    .nativeSettings( /* ... */ ) // defined in sbt-scala-native

lazy val coreJvm = core.jvm
lazy val coreNative = core.native

lazy val qt = Project(id = "guarana-qt", base = file("qt"))
  .settings(
    // qt/envVars += ("QT_DEBUG_PLUGINS" -> "1"),
    libraryDependencies ++= Seq(
      ("com.github.pathikrit" %% "better-files" % "3.9.1").cross(CrossVersion.for3Use2_13),
    ),
    libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.5",
    libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.5" classifier "linux-x86_64",
    libraryDependencies += "org.bytedeco" % "qt" % "5.15.2-1.5.5",
    libraryDependencies += "org.bytedeco" % "qt" % "5.15.2-1.5.5" classifier "linux-x86_64",
    javaOptions ++= Seq(
      "-Djava.library.path=/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib:lib/qtjambi-5.15-binaries-linux64-gcc/lib"
    )
    // javaOptions ++= Seq("-Djava.library.path=lib/binaries:/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib")
  )
  .dependsOn(coreJvm)

lazy val moduleJars = taskKey[Seq[(Attributed[File], java.lang.module.ModuleDescriptor)]]("moduleJars")
lazy val swing = Project(id = "guarana-swing", base = file("swing"))
  .settings(
    scalacOptions -= "-Yexplicit-nulls",
    libraryDependencies ++= Seq(
      ("com.github.pathikrit" %% "better-files" % "3.9.1").cross(CrossVersion.for3Use2_13),
      "org.codehaus.griffon.plugins" % "griffon-lookandfeel-napkin" % "2.0.0",
      "com.formdev" % "flatlaf" % "1.0",
      "com.jhlabs" % "filters" % "2.0.235-1",
      "io.dropwizard.metrics" % "metrics-core" % "4.1.18" % "test",
      "org.scalatest" %% "scalatest-funsuite" % "3.2.12" % "test",
      "com.typesafe.play" %% "play-json" % "2.10.0-RC5" % "test",
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
    ThisBuild / javaOptions ++= {
      val modules = moduleJars.value
      if (modules.isEmpty) Seq()
      else
        Seq(
          "--add-modules=" + modules.map(_._2.name).mkString(","),
          "--module-path=" + modules.map(_._1.data.getAbsolutePath).mkString(java.io.File.pathSeparator)
        )
    },
    ThisBuild / javaOptions += "-Xmx100m",
    ThisBuild / javaOptions ++= Seq("-Dsun.java2d.uiScale.enabled=true", "-Dsun.java2d.uiScale=2"),
    ThisBuild / outputStrategy := Some(StdoutOutput)
  )
  .dependsOn(coreJvm)

lazy val guaranaTheme = Project(id = "theme", base = file("swing/theme"))
  .dependsOn(swing)

lazy val lwjglVersion = "3.3.0"
lazy val lwjglClassifier = "natives-linux"

lazy val scribeVersion = "3.6.3"

lazy val apricot = Project(id = "apricot", base = file("apricot"))
  .settings(
    libraryDependencies ++= Seq(
      "io.github.humbleui.skija" % "skija-linux" % "0.96.0",
      "org.jetbrains" % "annotations" % "23.0.0",
      "org.lwjgl" % "lwjgl" % lwjglVersion classifier lwjglClassifier,
      "org.lwjgl" % "lwjgl-glfw" % lwjglVersion,
      "org.lwjgl" % "lwjgl-glfw" % lwjglVersion classifier lwjglClassifier,
      "org.lwjgl" % "lwjgl-opengl" % lwjglVersion,
      "org.lwjgl" % "lwjgl-opengl" % lwjglVersion classifier lwjglClassifier,
      "io.dropwizard.metrics" % "metrics-core" % "4.2.4",
      "com.outr" %% "scribe" % scribeVersion,
      "com.outr" %% "scribe-file" % scribeVersion,
      "com.github.blemale" %% "scaffeine" % "5.1.2",
      "org.scala-lang" %% "scala3-tasty-inspector" % scalaVersion.value % "provided,runtime"
    ),
    javaOptions ++= Seq(
      "-Djava.library.path=/home/randa/Development/guarana/qt/lib/qtjambi-5.15-binaries-linux64-gcc/lib:/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib"
    )
    // javaOptions ++= Seq("-Djava.library.path=/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib:/home/randa/Development/guarana/qt/lib/qtjambi-5.15-binaries-linux64-gcc/lib")
  )
  .dependsOn(coreJvm, qt)

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
