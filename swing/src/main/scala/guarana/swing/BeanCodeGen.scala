package guarana.swing

import language.implicitConversions
import better.files._
import java.beans.IndexedPropertyDescriptor
// import java.nio.file.{Path, Paths, Files}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

@main def beanCodeGen(patterns: String*) = {

  val ObjectClass = classOf[Object]
  def adaptType(tpe: java.lang.reflect.Type): String = tpe match {
    case tpe: Class[_] =>
      if (tpe.isArray) {
        "Array[" + adaptType(tpe.getComponentType) + "]"
      } else if (tpe.isPrimitive) {
        tpe match {
          case java.lang.Byte.TYPE => "Byte"
          case java.lang.Short.TYPE => "Short"
          case java.lang.Integer.TYPE => "Int"
          case java.lang.Long.TYPE => "Long"
          case java.lang.Float.TYPE => "Float"
          case java.lang.Double.TYPE => "Double"
          case java.lang.Boolean.TYPE => "Boolean"
          case java.lang.Character.TYPE => "Char"
          case java.lang.Void.TYPE => "Unit"
        }
      } else {
        tpe.getTypeParameters match {
          case Array() => tpe.getCanonicalName
          case other => tpe.getCanonicalName + other.map(_ => "_").mkString("[", ", ", "]")
        }
      }
      
      //handle cases like SomeType.ThisType[ActualParam1, ActualParam2...]
    case tpe: java.lang.reflect.ParameterizedType => 
//      val owner = Option(tpe.getOwnerType).map(_.asInstanceOf[Class[_]]).map(_ + ".").getOrElse("")
      tpe.getRawType.asInstanceOf[Class[_]].getCanonicalName + tpe.getActualTypeArguments.map {
        case tv: java.lang.reflect.TypeVariable[_] => tv.getName
        case other => adaptType(other)
      }.mkString("[", ", ", "]")
      
      //handle wildcars like ? extends or ? super
    case tpe: java.lang.reflect.WildcardType =>
      val upper = tpe.getUpperBounds match {
        case Array(ObjectClass) | Array() => None
        case others => Some(" <: " + others.map(adaptType).mkString(" with "))
      }
      val lower = tpe.getLowerBounds match {
        case Array() => None
        case others => Some(" >: " + others.map(adaptType).mkString(" with "))
      }
      
      "_" + upper.getOrElse("") + lower.getOrElse("")
      
    case tpe: java.lang.reflect.TypeVariable[_] =>
      val bounds = tpe.getBounds match {
        case Array(ObjectClass) | Array() => ""
        case others => " <: " + others.map(adaptType).mkString(" with ")
      }
      tpe.getName + bounds
  }

  def generate(destFile: File, classpath: Array[File], patterns: Seq[String]): File = {
    // destFile.parent.createDirectory()

    val inheritedJars = Iterator.iterate(ClassLoader.getSystemClassLoader)(_.getParent).takeWhile(_ != null).flatMap {
      case ucl: java.net.URLClassLoader => ucl.getURLs.map(u => File(u))
      case _ => Iterator.empty
    }.filter(_.extension.exists(".jar".==))
    val fullClasspath = (inheritedJars ++ classpath).toArray
    
    val allClasses = fullClasspath.iterator.flatMap { p =>
      if (p.isDirectory) p.listRecursively.map(f => p.relativize(f).toString)
      else {
        try new java.util.jar.JarFile(p.toJava).stream.iterator.asScala.map(_.getName)
        catch { case NonFatal(ex) => throw new RuntimeException(s"Could not open $p", ex)}
      }
    }.filter(e => e.endsWith(".class") && !e.contains("$")).
      filter(e => patterns.exists(e.matches))
      
    val jmodClasses = if (!scala.util.Properties.javaVersion.matches(raw"1\.\d+.*")) {
      for {
        moduleRef <- java.lang.module.ModuleFinder.ofSystem().findAll().iterator.asScala
        reader = moduleRef.open
        clazz <- reader.list.iterator.asScala if clazz.endsWith(".class") && !clazz.contains("module-info") && !clazz.contains("$") && patterns.exists(clazz.matches)
      } yield clazz
    } else Iterator.empty

    // val out = new java.io.PrintStream(destFile.toJava, "utf-8")
    val out = System.out

    var classpathClassLoader = new java.net.URLClassLoader(classpath.map(_.url).to(Array))
    val valueClasses = (for {
        entry <- (allClasses ++ jmodClasses)
        c = Class.forName(entry.replace("/", ".").replace(".class", ""), false, classpathClassLoader)
        beanInfo = java.beans.Introspector.getBeanInfo(c)
        beanProperties = beanInfo.getPropertyDescriptors().filterNot(_.getName == "class").
            filterNot(_.isInstanceOf[IndexedPropertyDescriptor]).filter(prop =>
              Option(prop.getWriteMethod).getOrElse(prop.getReadMethod).getDeclaringClass == c)
        if beanProperties.nonEmpty
      } yield {

        val nodeName = beanInfo.getBeanDescriptor.getName

        // methods, Left indicates rw property, Right indicates r only
        val properties = beanProperties.flatMap { descriptor =>
          Option(descriptor.getWriteMethod) -> Option(descriptor.getReadMethod) match {
            case (Some(w), Some(r)) => Seq(Left( (descriptor.getName, adaptType(w.getGenericParameterTypes()(0)), descriptor) ))
            case (_, Some(r)) => Seq(Right( (descriptor.getName, adaptType(r.getGenericReturnType), descriptor) ))
            case _ => Nil
          }  
        }

        val readOnlyProps = properties.collect { case Right(t) => t }.map((prop, tpe, descr) => s"def $prop = v.${descr.getReadMethod.getName}")
        val varDescrs = properties.collect { case Left(t) => t }
        val varProps = varDescrs.map{ (prop, tpe, descr) => 
          s"""val ${prop.capitalize} = SwingVar[${beanInfo.getBeanDescriptor.getName}, $tpe]("${prop}", _.${descr.getReadMethod.getName}, _.${descr.getWriteMethod.getName}(_))"""
        }
        val varExtMethods = varDescrs.map{ (prop, tpe, descr) => 
          s"""def $prop = $nodeName.${prop.capitalize}.forInstance(v)"""
        }

        val ctrParams = varDescrs.map((prop, tpe, descr) => s"$prop: Opt[Binding[$tpe]] = UnsetParam")
        val ctrInitializers = varDescrs.map((prop, tpe, descr) => s"ifSet($prop, $nodeName.ops.$prop(res) := _)")

        val genericDecls = c.getTypeParameters() match {
          case arr if arr.isEmpty => ""
          case arr => arr.map(adaptType).mkString("[", ", ", "]")
        }
        val generics = c.getTypeParameters() match {
          case arr if arr.isEmpty => ""
          case arr => arr.map(_.getName).mkString("[", ", ", "]")
        }


        c.getPackage -> s"""
          |opaque type $nodeName <: Node = ${c.getName}
          |object $nodeName {
          |  ${varProps.mkString("\n  ")}

          |  given ops: (v: $nodeName) extended with {
          |    ${readOnlyProps.mkString("\n    ")}
          |    ${varExtMethods.mkString("\n    ")}
          |    def unwrap: ${c.getName} = v
          |  }

          |  def apply(
          |    ${ctrParams.mkString(",\n    ")}
          |  ): VarContextAction[$nodeName] = {
          |    val res = uninitialized()
          |    ${ctrInitializers.mkString("\n    ")}
          |    res
          |  }
          |}
          """.stripMargin
      }).toSeq.groupBy(_._1).view.mapValues(_.map(_._2)).toMap

    for ((pck, valueClasses) <- valueClasses) {
      val pkgParts = pck.getName.split("\\.")
      val parentPkg = pkgParts.take(pkgParts.length - 1).mkString(".")
      // out.println(s"package $parentPkg { package object ${pkgParts.last} {\n" +
      //             valueClasses.mkString("\n") + "}}")
      out.println(valueClasses.mkString("\n"))
    }

    java.beans.Introspector.flushCaches()
    System.gc()
    classpathClassLoader.close()
    classpathClassLoader = null
    System.gc()
    out.flush()
    out.close()

    destFile
  }

  generate(null, Array.empty, patterns)
}