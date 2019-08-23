name := "guaraná"
organization := "guaraná"
version := "0.0.0.0.0.0.0.0.0.0.0.1"

inThisBuild(Seq(
  scalaVersion := "2.13.0",
  fork := true,

  scalacOptions += "-Ymacro-annotations",
  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-Xlint", "-opt:_", "-opt-warnings:_", "-opt:l:inline", "-opt-inline-from:scala.**,guarana.**"),
  scalacOptions in (Compile, console) --= Seq("-opt:_", "-Xlint"),

  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test",
))

lazy val core = Project(id ="guaraná-core", base = file("core")).settings(
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided",
    "com.lihaoyi" %% "sourcecode" % "0.1.7",
  )
)

lazy val guaraná = Project(id = "guaraná", base = file(".")).dependsOn(core).settings(
)