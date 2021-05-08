name := "guarana"
organization := "guarana"
version := "0.0.1"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(Seq(
  scalaVersion := "3.0.0-RC3",
  fork := true,

  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.8" % "test",

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
  )
).dependsOn(cpsasync)
lazy val cpsasync = ProjectRef(uri("git://github.com/rssh/dotty-cps-async#5048d636714fed167bb7ec5b06abd9d1937e5c94"), "cpsJVM")

lazy val qt = Project(id = "guarana-qt", base = file("qt")).settings(
  libraryDependencies ++= Seq(
    ("com.github.pathikrit" %% "better-files" % "3.9.1").cross(CrossVersion.for3Use2_13),
  ),
  javaOptions ++= Seq("-Djava.library.path=lib/qtjambi-5.15-binaries-linux64-gcc/lib")
  // javaOptions ++= Seq("-Djava.library.path=lib/binaries:/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib")
).dependsOn(core)

