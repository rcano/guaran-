package apricot.vk

import better.files._
import guarana.unn
import java.nio.{IntBuffer, LongBuffer}
import org.lwjgl.system.{CustomBuffer, MemoryStack, Struct, StructBuffer}
import scala.annotation.transparentTrait
import scala.jdk.CollectionConverters._
import scala.compiletime.{codeOf, error}
import scala.util.Try
import scala.util.chaining._

trait Allocator[S]:
  type NonNullS = S
  type Buffer <: CustomBuffer[_]
  inline def stackCalloc(stack: MemoryStack): NonNullS
  inline def stackMalloc(stack: MemoryStack): NonNullS
  inline def calloc(): NonNullS
  inline def malloc(): NonNullS
  transparent inline def stackCallocBuffer(stack: MemoryStack, capacity: Int): Buffer
  transparent inline def stackMallocBuffer(stack: MemoryStack, capacity: Int): Buffer
  transparent inline def callocBuffer(capacity: Int): Buffer
  transparent inline def mallocBuffer(capacity: Int): Buffer

object Allocator extends LowPrioGivens {
  given Allocator[org.lwjgl.PointerBuffer] with
    type Buffer = org.lwjgl.PointerBuffer
    inline def stackCalloc(stack: MemoryStack) = stack.callocPointer(1).unn
    inline def stackMalloc(stack: MemoryStack) = stack.mallocPointer(1).unn
    inline def calloc() = org.lwjgl.system.MemoryUtil.memCallocPointer(1).unn
    inline def malloc() = org.lwjgl.system.MemoryUtil.memAllocPointer(1).unn
    transparent inline def stackCallocBuffer(stack: MemoryStack, capacity: Int) = stack.callocPointer(capacity).unn
    transparent inline def stackMallocBuffer(stack: MemoryStack, capacity: Int) = stack.mallocPointer(capacity).unn
    transparent inline def callocBuffer(capacity: Int) = org.lwjgl.system.MemoryUtil.memCallocPointer(capacity).unn
    transparent inline def mallocBuffer(capacity: Int) = org.lwjgl.system.MemoryUtil.memAllocPointer(capacity).unn
}

@transparentTrait
private trait LowPrioGivens {
  given Allocator[LongBuffer] with
    type Buffer = Nothing
    inline def stackCalloc(stack: MemoryStack) = stack.callocLong(1).unn
    inline def stackMalloc(stack: MemoryStack) = stack.mallocLong(1).unn
    inline def calloc() = org.lwjgl.system.MemoryUtil.memCallocLong(1).unn
    inline def malloc() = org.lwjgl.system.MemoryUtil.memAllocLong(1).unn
    //can't use a proper generic Buffer because dotty breaks
    transparent inline def stackCallocBuffer(stack: MemoryStack, capacity: Int) = error("Can't create a buffer of LongBuffers")
    transparent inline def stackMallocBuffer(stack: MemoryStack, capacity: Int) = error("Can't create a buffer of LongBuffers")
    transparent inline def callocBuffer(capacity: Int) = error("Can't create a buffer of LongBuffers")
    transparent inline def mallocBuffer(capacity: Int) = error("Can't create a buffer of LongBuffers")

  given Allocator[IntBuffer] with
    type Buffer = Nothing
    inline def stackCalloc(stack: MemoryStack) = stack.callocInt(1).unn
    inline def stackMalloc(stack: MemoryStack) = stack.mallocInt(1).unn
    inline def calloc() = org.lwjgl.system.MemoryUtil.memCallocInt(1).unn
    inline def malloc() = org.lwjgl.system.MemoryUtil.memAllocInt(1).unn
    //can't use a proper generic Buffer because dotty breaks
    transparent inline def stackCallocBuffer(stack: MemoryStack, capacity: Int) = error("Can't create a buffer of IntBuffers")
    transparent inline def stackMallocBuffer(stack: MemoryStack, capacity: Int) = error("Can't create a buffer of IntBuffers")
    transparent inline def callocBuffer(capacity: Int) = error("Can't create a buffer of IntBuffers")
    transparent inline def mallocBuffer(capacity: Int) = error("Can't create a buffer of IntBuffers")
}

@main def genStructAllocators: Unit =
  import scala.language.unsafeNulls
  val structClass = classOf[org.lwjgl.system.Struct[?]]
  val structBufferClass = classOf[org.lwjgl.system.StructBuffer[?, ?]]

  val paths = Set(
    structClass.getProtectionDomain.getCodeSource.getLocation,
    classOf[org.lwjgl.Version].getProtectionDomain.getCodeSource.getLocation,
    classOf[org.lwjgl.vulkan.VkApplicationInfo].getProtectionDomain.getCodeSource.getLocation,
  ).map(File(_).path)

  val finder = java.lang.module.ModuleFinder.of(paths.toSeq: _*)
  val genGivens = for
    className <- finder.findAll.asScala.toSeq.flatMap(_.open().list.iterator.asScala)
    if className.endsWith(".class")

    genCode <-
      try {
        val loaded = Class.forName(className.replace('/', '.').stripSuffix(".class"))
        if (structClass.isAssignableFrom(loaded)) {
          val hasCalloc = Try(loaded.getMethod("calloc")).isSuccess
          val hasMalloc = Try(loaded.getMethod("malloc")).isSuccess
          val hasStackCalloc = Try(loaded.getMethod("calloc"), classOf[MemoryStack]).isSuccess
          val hasStackMalloc = Try(loaded.getMethod("malloc"), classOf[MemoryStack]).isSuccess
          val hasBuffer = Try(loaded.getDeclaredClasses().exists(c => structBufferClass.isAssignableFrom(c))).getOrElse(false)

          if (hasCalloc && hasMalloc && hasStackCalloc && hasStackMalloc) {
            val structName = loaded.getName
            Some(s"""
                |  given Allocator[$structName] with
                |    type Buffer = ${if (hasBuffer) s"$structName.Buffer" else "Nothing"}
                |    inline def stackCalloc(stack: MemoryStack) = $structName.calloc(stack).unn
                |    inline def stackMalloc(stack: MemoryStack) = $structName.malloc(stack).unn
                |    inline def calloc() = $structName.calloc().unn
                |    inline def malloc() = $structName.malloc().unn
                ${if (hasBuffer)
              s"""|    transparent inline def stackCallocBuffer(stack: MemoryStack, capacity: Int) = $structName.calloc(capacity, stack).unn
                  |    transparent inline def stackMallocBuffer(stack: MemoryStack, capacity: Int) = $structName.malloc(capacity, stack).unn
                  |    transparent inline def callocBuffer(capacity: Int) = $structName.calloc(capacity).unn
                  |    transparent inline def mallocBuffer(capacity: Int) = $structName.malloc(capacity).unn
                  """
            else
              s"""|    transparent inline def stackCallocBuffer(stack: MemoryStack, capacity: Int) = error("Can't create a buffer of $structName")
                  |    transparent inline def stackMallocBuffer(stack: MemoryStack, capacity: Int) = error("Can't create a buffer of $structName")
                  |    transparent inline def callocBuffer(capacity: Int) = error("Can't create a buffer of $structName")
                  |    transparent inline def mallocBuffer(capacity: Int) = error("Can't create a buffer of $structName")
                  """}
              """.trim.stripMargin)
          } else None
        } else None
      } catch case ex => None
  yield genCode

  File("gen.txt").clear.appendText(genGivens.mkString("\n"))
