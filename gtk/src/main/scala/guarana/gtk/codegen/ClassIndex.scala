package guarana
package gtk.codegen

import io.github.classgraph.{ClassInfo, ScanResult}
import scala.jdk.CollectionConverters.*

class ClassIndex(val scanResult: ScanResult) {
  private val indexedClasses = collection.mutable.HashMap.empty[ClassInfo, WidgetInfo]

  private val getterPat = "(?:get|is)\\p{Upper}.*".r
  private val setterPat = "(?:set)\\p{Upper}.*".r
  private val signalPat = "on\\p{Upper}.*".r

  def getWidgetInfo(classInfo: ClassInfo): WidgetInfo = {
    lazy val computedProperties: Set[GtkPropertyInfo] = {
      val allMethods = classInfo.getMethodInfo()
      (for {
        potentialGetter <- allMethods.filter(p => getterPat.matches(p.getName())).asScala
        if potentialGetter.isPublic() && !potentialGetter.isStatic()
        // _ = println(s"Testing getter ${potentialGetter}")
        propName = computeNameParts(potentialGetter.getName().stripPrefix("get").stripPrefix("is"))
        propType = potentialGetter.getTypeSignatureOrTypeDescriptor().getResultType()
        res = GtkPropertyInfo(propName, ReflectedType(ReflectedTypeParser.toType(propType)), potentialGetter.hasAnnotation(classOf[Deprecated]))(Some(potentialGetter.getName()))
        // before returning, we must make sure we find a matching setter
        // _ = println(s"Found matching setters ${allMethods.asScala.filter(_.getName() == s"set${res.nameInPascalCase}")}")
        matchingSetter <- allMethods.asScala.filter(_.getName() == s"set${res.nameInPascalCase}")
        if matchingSetter.getParameterInfo().size == 1 &&
          matchingSetter.getParameterInfo()(0).getTypeSignatureOrTypeDescriptor() == propType
      } yield res).toSet
    }

    lazy val computedSignals: Set[GtkSignalInfo] = {
      val builderInfo = classInfo.getInnerClasses().filter(_.getSimpleName() == "Builder").getFirst()
      (for {
        signal <- builderInfo.getMethodInfo.filter(p => signalPat.matches(p.getName())).asScala
        signalName = computeNameParts(signal.getName().stripPrefix("on"))
      } yield GtkSignalInfo(signalName, signal.hasAnnotation(classOf[Deprecated]))).toSet
    }

    lazy val constructorParams: Seq[GtkPropertyInfo] = {
      val builderClass = classInfo.getInnerClasses().filter(ci => ci.isStatic() && ci.getSimpleName() == "Builder").getFirst()
      val existingProperties = computedProperties.map(_.nameInCamelCase)
      (for {
        setter <- builderClass.getMethodInfo().filter(m => setterPat.matches(m.getName())).asScala
        if setter.isPublic() && !setter.isStatic()
        propName = computeNameParts(setter.getName().stripPrefix("set"))
        propType = setter.getTypeSignatureOrTypeDescriptor().getResultType()
        res = GtkPropertyInfo(propName, ReflectedType(ReflectedTypeParser.toType(propType)), setter.hasAnnotation(classOf[Deprecated]))(Some(setter.getName()))
        if (!existingProperties(res.nameInCamelCase))
      } yield res).toIndexedSeq
      // val specialConstructors = classInfo
      //   .getDeclaredConstructorInfo()
      //   .asScala
      //   .filterNot(m =>
      //     m.getParameterInfo().isEmpty || (m
      //       .getParameterInfo()
      //       .size == 1 && m.getParameterInfo()(0).getTypeDescriptor().toString == "java.lang.foreign.MemorySegment")
      //   )
      // if (specialConstructors.nonEmpty) {
      //   scribe.info(s"Class ${classInfo.getSimpleName()} has constructors: ${specialConstructors.mkString("\n")}")
      //   if (specialConstructors.size > 1)
      //     scribe.warn(s"Class ${classInfo.getSimpleName()} has more than one possible constructor! selecting first")
      //   val primary = specialConstructors.head
      //   primary
      //     .getParameterInfo()
      //     .zipWithIndex
      //     .map((param, i) =>
      //       GtkPropertyInfo(
      //         computeNameParts(param.getName().nullFold(identity, s"arg$$$i")),
      //         ReflectedType(ReflectedTypeParser.toType(param.getTypeSignatureOrTypeDescriptor())),
      //         false
      //       )(None)
      //     )
      //     .toIndexedSeq
      // } else Seq.empty
    }

    indexedClasses.getOrElseUpdate(classInfo, WidgetInfo(computedProperties, constructorParams, computedSignals))
  }

  private def computeNameParts(s: String): Seq[String] = {
    val sb = StringBuilder()
    val res = scala.collection.mutable.ListBuffer.empty[String]
    for (c <- s) {
      if (c.isUpper && sb.nonEmpty) {
        res += sb.result()
        sb.clear()
      }
      sb += c.toLower
    }
    if (sb.nonEmpty) res += sb.result()
    res.toList
  }
}
