name := "guaraná-swing"
version := "0.1.0-SNAPSHOT"

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion := "3.0.0-RC1"

ThisBuild / scalacOptions ++= Seq(
  //"-Yexplicit-nulls",
  "-deprecation", "-unchecked",
  "-language:implicitConversions"
)

lazy val root = Project(id = "guarana-swing", base = file("."))

lazy val guaranaTheme = Project(id = "theme", base = file("theme"))
  .dependsOn(root)


ThisBuild / fork := true

Compile / packageDoc / publishArtifact := false


//scalacOptions += "-Yexplicit-nulls"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies ++= Seq(
  ("com.github.pathikrit" % "better-files_2.13" % "3.9.1"), //.withDottyCompat(scalaVersion.value),
  "org.codehaus.griffon.plugins" % "griffon-lookandfeel-napkin" % "2.0.0",
  "com.formdev" % "flatlaf" % "1.0",
  "com.jhlabs" % "filters" % "2.0.235-1",

  "io.dropwizard.metrics" % "metrics-core" % "4.1.18" % "test",
  "org.scalatest" %% "scalatest-funsuite" % "3.2.5" % "test",
  ("com.typesafe.play" %% "play-json" % "2.9.2" % "test").withDottyCompat(scalaVersion.value),

)
libraryDependencies += "com.github.rssh" %% "dotty-cps-async" % "0.4.0"

lazy val moduleJars = taskKey[Seq[(Attributed[File], java.lang.module.ModuleDescriptor)]]("moduleJars")
ThisBuild / moduleJars := {
  val attributedJars = (Compile/dependencyClasspathAsJars).value.filterNot(_.metadata(moduleID.key).organization == "org.scala-lang")
  val modules = attributedJars.flatMap { aj =>
    try {
      val module = java.lang.module.ModuleFinder.of(aj.data.toPath).findAll().iterator.next.descriptor
      Some(aj -> module)
    } catch { case _: java.lang.module.FindException => None }
  }
  modules
}

ThisBuild / javaOptions ++= {
  val modules = moduleJars.value
  if (modules.isEmpty) Seq()
  else Seq(
    "--add-modules=" + modules.map(_._2.name).mkString(","),
    "--module-path=" + modules.map(_._1.data.getAbsolutePath).mkString(java.io.File.pathSeparator)
  )
}

ThisBuild / javaOptions += "-Xmx100m"
ThisBuild / javaOptions ++= Seq("-Dsun.java2d.uiScale.enabled=true", "-Dsun.java2d.uiScale=2")
ThisBuild / outputStrategy := Some(StdoutOutput)
