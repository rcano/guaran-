name := "guaraná-swing"
version := "0.1.0-SNAPSHOT"

Global / onChangedBuildSource := ReloadOnSourceChanges

scalaVersion := "0.26.0-RC1"

scalacOptions ++= Seq(
  "-Yexplicit-nulls",
   "-deprecation", "-unchecked")

Compile / packageDoc / publishArtifact := false

fork := true

//scalacOptions ++= Seq("-Yinfer-argument-types")
//scalacOptions += "-Yexplicit-nulls"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"


libraryDependencies ++= Seq(
  ("com.github.pathikrit" % "better-files_2.13" % "3.8.0"), //.withDottyCompat(scalaVersion.value),
  "org.codehaus.griffon.plugins" % "griffon-lookandfeel-napkin" % "2.0.0",
  "com.formdev" % "flatlaf" % "0.26",

  //"de.vandermeer" % "asciitable" % "0.3.2",
  //"io.github.classgraph" % "classgraph" % "4.8.60",
  // "org.scalameta" % "munit_0.24" % "0.7.7" % Test,
)
// testFrameworks += new TestFramework("munit.Framework")

(reStart/mainClass) := Some("guarana.swing.ShapesTest")


lazy val moduleJars = taskKey[Seq[(Attributed[File], java.lang.module.ModuleDescriptor)]]("moduleJars")
moduleJars := {
  val attributedJars = (Compile/dependencyClasspathAsJars).value.filterNot(_.metadata(moduleID.key).organization == "org.scala-lang")
  val modules = attributedJars.flatMap { aj =>
    try {
      val module = java.lang.module.ModuleFinder.of(aj.data.toPath).findAll().iterator.next.descriptor
      Some(aj -> module)
    } catch { case _: java.lang.module.FindException => None }
  }
  modules
}

javaOptions ++= {
  val modules = moduleJars.value
  if (modules.isEmpty) Seq()
  else Seq(
    "--add-modules=" + modules.map(_._2.name).mkString(","),
    "--module-path=" + modules.map(_._1.data.getAbsolutePath).mkString(java.io.File.pathSeparator)
  )
}

javaOptions += "-Xmx100m"
// javaOptions ++= Seq("-Dsun.java2d.uiScale.enabled=true", "-Dsun.java2d.uiScale=2")
outputStrategy := Some(StdoutOutput)
