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

lazy val core = Project(id ="guarana-core", base = file("core")).settings(

).dependsOn(cpsasync)

lazy val cpsasync = ProjectRef(uri("git://github.com/rssh/dotty-cps-async#5048d636714fed167bb7ec5b06abd9d1937e5c94"), "cpsJVM")


lazy val guaraná = Project(id = "guarana", base = file(".")).dependsOn(core)