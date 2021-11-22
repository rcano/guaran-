name := "guarana"
organization := "guarana"
version := "0.0.1"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(Seq(
  scalaVersion := "3.1.0",
  fork := true,

  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test",

  Compile / packageDoc / publishArtifact := false,

  ThisBuild / scalacOptions ++= Seq(
    "-Yexplicit-nulls",
    "-deprecation", "-unchecked",
    "-language:implicitConversions"
  ),
))

lazy val guaraná = Project(id = "guarana", base = file(".")).aggregate(core)

lazy val core = Project(id ="guarana-core", base = file("core")).settings(
  libraryDependencies ++= Seq(
    "com.github.rssh" %% "dotty-cps-async" % "0.9.5",
    "org.agrona" % "agrona" % "1.12.0"
  )
)

lazy val qt = Project(id = "guarana-qt", base = file("qt")).settings(
  // qt/envVars += ("QT_DEBUG_PLUGINS" -> "1"),
  libraryDependencies ++= Seq(
    ("com.github.pathikrit" %% "better-files" % "3.9.1").cross(CrossVersion.for3Use2_13),
  ),
  libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.5",
  libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.5" classifier("linux-x86_64"),
  libraryDependencies += "org.bytedeco" % "qt" % "5.15.2-1.5.5",
  libraryDependencies += "org.bytedeco" % "qt" % "5.15.2-1.5.5" classifier("linux-x86_64"),
  javaOptions ++= Seq("-Djava.library.path=/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib:lib/qtjambi-5.15-binaries-linux64-gcc/lib")
  // javaOptions ++= Seq("-Djava.library.path=lib/binaries:/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib")
).dependsOn(core)


lazy val lwjglVersion = "3.2.3"
lazy val lwjglClassifier = "natives-linux"

lazy val skia = Project(id = "guarana-skia", base = file("skia")).settings(
  libraryDependencies ++= Seq(
    "io.github.humbleui.skija" % "skija-linux" % "0.96.0",
    "org.jetbrains" % "annotations" % "23.0.0",

    "org.lwjgl" % "lwjgl" % lwjglVersion classifier lwjglClassifier,
    "org.lwjgl" % "lwjgl-glfw" % lwjglVersion,
    "org.lwjgl" % "lwjgl-glfw" % lwjglVersion classifier lwjglClassifier,
    "org.lwjgl" % "lwjgl-opengl" % lwjglVersion,
    "org.lwjgl" % "lwjgl-opengl" % lwjglVersion classifier lwjglClassifier,

    "io.dropwizard.metrics" % "metrics-core" % "4.2.4",
  )
).dependsOn(core)

lazy val jmh = Project("jmh", base = file("jmh"))
  .dependsOn(core)
  .enablePlugins(JmhPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "PolyrhythmMania" % "core" % "1.1.0" excludeAll(ExclusionRule("space.earlygrey", "shapedrawer"), ExclusionRule("com.github.JnCrMx", "discord-game-sdk4j"))
    ),
    resolvers += Resolver.mavenLocal
  )
