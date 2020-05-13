package guarana.swing

import language.implicitConversions
import better.files._
import java.beans.{IndexedPropertyDescriptor, PropertyDescriptor}
// import java.nio.file.{Path, Paths, Files}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

@main def beanCodeGen(patterns: String*) = {

  val ObjectClass = classOf[Object]
  def adaptType(tpe: java.lang.reflect.Type | Null): String = tpe match {
    case null => ???
    case tpe: Class[_] =>
      if (tpe.isArray) {
        "Array[" + adaptType(tpe.getComponentType.nn) + "]"
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
        tpe.getTypeParameters.nn match {
          case Array() => tpe.getCanonicalName.nn + " | Null"
          case other => tpe.getCanonicalName + other.map(_ => "_").mkString("[", ", ", "]") + " | Null"
        }
      }
      
      //handle cases like SomeType.ThisType[ActualParam1, ActualParam2...]
    case tpe: java.lang.reflect.ParameterizedType => 
//      val owner = Option(tpe.getOwnerType).map(_.asInstanceOf[Class[_]]).map(_ + ".").getOrElse("")
      tpe.getRawType.asInstanceOf[Class[_]].getCanonicalName + tpe.getActualTypeArguments.nn.map {
        case tv: java.lang.reflect.TypeVariable[_] => tv.getName
        case other => adaptType(other.nn)
      }.mkString("[", ", ", "]")  + " | Null"
      
      //handle wildcars like ? extends or ? super
    case tpe: java.lang.reflect.WildcardType =>
      val upper = tpe.getUpperBounds.nn match {
        case Array(ObjectClass) | Array() => None
        case others => Some(" <: " + others.nn.map(adaptType).mkString(" with "))
      }
      val lower = tpe.getLowerBounds.nn match {
        case Array() => None
        case others => Some(" >: " + others.nn.map(adaptType).mkString(" with "))
      }
      
      "_" + upper.getOrElse("") + lower.getOrElse("")
      
    case tpe: java.lang.reflect.TypeVariable[_] =>
      val bounds = tpe.getBounds match {
        case Array(ObjectClass) | Array() => ""
        case others => " <: " + others.nn.map(adaptType).mkString(" with ")
      }
      tpe.getName + bounds
  }

  def toNodeName(n: String) = n match {
    case "Container" => "Node"
    case other if other startsWith "J" => other.drop(1)
    case other => other
  }

  //special property mappings that cause issues with scalac thinking we are trying to access protected internal variables and hence not inferencing the extension methods
  val specialNodeProperties = Map(
    "res.background" -> "Node.ops.background(res)",
    "res.enabled" -> "Node.ops.enabled(res)",
    "res.font" -> "Node.ops.font(res)",
    "res.foreground" -> "Node.ops.foreground(res)",
    "res.minimumSize" -> "Node.ops.minimumSize(res)",
    "res.preferredSize" -> "Node.ops.prefSize(res)",
    "res.visible" -> "Node.ops.visible(res)",
  ).withDefault(identity)

  def generate(destFile: File | Null, classpath: Array[File], patterns: Seq[String]): File | Null = {
    // destFile.parent.createDirectory()

    val inheritedJars = Iterator.iterate[ClassLoader | UncheckedNull](ClassLoader.getSystemClassLoader)(_.getParent).takeWhile(_ != null).flatMap {
      case ucl: java.net.URLClassLoader => ucl.getURLs.nn.map(u => File(u.nn))
      case _ => Iterator.empty
    }.filter(_.extension.exists(".jar".==))
    val fullClasspath = (inheritedJars ++ classpath).toArray
    
    val allClasses = fullClasspath.iterator.flatMap { p =>
      if (p.isDirectory) p.listRecursively.map(f => p.relativize(f).toString)
      else {
        try new java.util.jar.JarFile(p.toJava).stream.iterator.nn.asScala.map(_.getName.nn)
        catch { case NonFatal(ex) => throw new RuntimeException(s"Could not open $p", ex)}
      }
    }.filter(e => e.endsWith(".class") && !e.contains("$")).
      filter(e => patterns.exists(e.matches))
      
    val jmodClasses = if (!scala.util.Properties.javaVersion.matches(raw"1\.\d+.*")) {
      for {
        moduleRef <- java.lang.module.ModuleFinder.ofSystem().findAll().iterator.nn.asScala
        reader = moduleRef.open.nn
        clazz <- reader.list.iterator.nn.asScala if clazz.endsWith(".class") && !clazz.contains("module-info") && !clazz.contains("$") && patterns.exists(clazz.matches)
      } yield clazz
    } else Iterator.empty

    // val out = new java.io.PrintStream(destFile.toJava, "utf-8")
    val out = System.out.nn
    def Option[T](o: T | Null) = scala.Option(o).asInstanceOf[Option[T]]

    var classpathClassLoader = new java.net.URLClassLoader(classpath.map(_.url).to(Array).asInstanceOf[Array[java.net.URL | UncheckedNull]])
    val valueClasses = (for {
        entry <- (allClasses ++ jmodClasses)
        c = Class.forName(entry.replace("/", ".").replace(".class", ""), false, classpathClassLoader).nn
        beanInfo = java.beans.Introspector.getBeanInfo(c).nn
        beanProperties = beanInfo.getPropertyDescriptors().nn.filterNot(_.nn.getName == "class").
            filterNot(_.isInstanceOf[IndexedPropertyDescriptor]).asInstanceOf[Array[PropertyDescriptor]]
            // .filter(prop =>
            //   Option(prop.getWriteMethod).getOrElse(prop.getReadMethod).getDeclaringClass == c)
        if beanProperties.nonEmpty
      } yield {

        val parentMethods = Iterator.unfold(c.getSuperclass: Class[_] | UncheckedNull)(Option(_).map(c => c.nn.getDeclaredMethods -> c.nn.getSuperclass))
          .flatMap(_.nn.map(m => m.nn.getName -> m.nn.getGenericParameterTypes.nn.toSeq)).toSet
        def inParents(m: (String, String, PropertyDescriptor, java.lang.reflect.Method)): Boolean =
          parentMethods(m._4.getName -> m._4.getGenericParameterTypes.nn.toSeq)

        val nodeName = toNodeName(beanInfo.getBeanDescriptor.nn.getName.nn)

        // methods, Left indicates rw property, Right indicates r only
        val properties = beanProperties.flatMap { descriptor =>
          Option(descriptor.getWriteMethod) -> Option(descriptor.getReadMethod) match {
            case (Some(w), Some(r)) => Seq(Left( (descriptor.getName.nn, adaptType(w.getGenericParameterTypes().nn(0)), descriptor, w) ))
            case (_, Some(r)) => Seq(Right( (descriptor.getName.nn, adaptType(r.getGenericReturnType), descriptor, r) ))
            case _ => Nil
          }  
        }

        val readOnlyProps = properties.collect { case Right(t) => t }.filterNot(inParents).map((prop, tpe, descr, m) => s""""def $prop: $tpe = v.${m.getName}"""")
        val varDescrs = properties.collect { case Left(t) => t }
        val varProps = varDescrs.filterNot(inParents).map { (prop, tpe, descr, m) => 
          // s"""val ${prop.capitalize} = SwingVar[$nodeName, $tpe]("${prop}", _.${descr.getReadMethod.getName}, _.${descr.getWriteMethod.getName}(_))"""
          s"""SwingProp("$prop", "$tpe")"""
        }
        // val varExtMethods = varDescrs.filterNot(inParents).map{ (prop, tpe, descr, m) => 
        //   s"""def $prop = $nodeName.${prop.capitalize}.forInstance(v)"""
        // }

        // val ctrParams = varDescrs.map((prop, tpe, descr, m) => s"$prop: Opt[Binding[$tpe]] = UnsetParam")
        // val ctrInitializers = varDescrs.map { (prop, tpe, descr, m) => 
        //   val opsClass = toNodeName(m.getDeclaringClass.getSimpleName.nn)
        //   s"ifSet($prop, ${specialNodeProperties(s"res.$prop")} := _)"
        // }

        // val genericDecls = c.getTypeParameters() match {
        //   case arr if arr.isEmpty => ""
        //   case arr => arr.map(adaptType).mkString("[", ", ", "]")
        // }
        // val generics = c.getTypeParameters() match {
        //   case arr if arr.isEmpty => ""
        //   case arr => arr.map(_.getName).mkString("[", ", ", "]")
        // }

        c.getPackage.nn -> (varProps ++ readOnlyProps).mkString(",\n")
        // c.getPackage.nn -> s"""
        //   |opaque type $nodeName <: Node = ${c.getName}
        //   |object $nodeName extends VarsMap {
        //   |  ${varProps.mkString("\n  ")}

        //   |  given ops: (v: $nodeName) extended with {
        //   |    ${readOnlyProps.mkString("\n    ")}
        //   |    ${varExtMethods.mkString("\n    ")}
        //   |    def unwrap: ${c.getName} = v
        //   |  }
        //   |  
        //   |  def init(n: $nodeName) = (given sc: Scenegraph) => {
        //   |    Component.init(n)
        //   |    n.addPropertyChangeListener(varsPropertyListener(n))
        //   |  }
        //   |
        //   |  def apply(
        //   |    ${ctrParams.mkString(",\n    ")}
        //   |  ): Scenegraph ?=> VarContextAction[$nodeName] = {
        //   |    val res = uninitialized()
        //   |    init(res)
        //   |    ${ctrInitializers.mkString("\n    ")}
        //   |    res
        //   |  }
        //   |}
        //   """.stripMargin
      }).toSeq.groupBy(_._1).view.mapValues(_.map(_._2)).toMap

    for ((pck, valueClasses) <- valueClasses) {
      val pkgParts = pck.getName.split("\\.").asInstanceOf[Array[String]]
      val parentPkg = pkgParts.take(pkgParts.length - 1).mkString(".")
      // out.println(s"package $parentPkg { package object ${pkgParts.last} {\n" +
      //             valueClasses.mkString("\n") + "}}")
      out.println(valueClasses.mkString("\n"))
    }

    java.beans.Introspector.flushCaches()
    System.gc()
    classpathClassLoader.close()
    // classpathClassLoader = null
    System.gc()
    out.flush()
    out.close()

    destFile
  }

  generate(null, Array.empty, patterns)
}