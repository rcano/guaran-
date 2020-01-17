name := "guaraná-swing"
version := "0.1.0"

scalaVersion := "0.21.0-RC1"

fork := true

//scalacOptions ++= Seq("-Yinfer-argument-types")
//scalacOptions += "-Yexplicit-nulls"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"


libraryDependencies ++= Seq(
  ("com.github.pathikrit" % "better-files_2.13" % "3.8.0"), //.withDottyCompat(scalaVersion.value),

  //"de.vandermeer" % "asciitable" % "0.3.2",
  //"io.github.classgraph" % "classgraph" % "4.8.60",
)

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