package apricot.vk

import scala.language.unsafeNulls
import apricot.*
import java.nio.{ByteBuffer, IntBuffer, LongBuffer}
import org.lwjgl.PointerBuffer
import org.lwjgl.system.{CustomBuffer, MemoryStack, MemoryUtil, Struct, StructBuffer}
import org.lwjgl.vulkan.VK10
import scala.compiletime._

// import apricot.managed.{given, _}

sealed trait AllocMethod
case object calloc extends AllocMethod
case object malloc extends AllocMethod
case object stackCalloc extends AllocMethod
case object stackMalloc extends AllocMethod

type MemoryAddr = Long
type NativeHandle = Long

inline def alloc[S](inline method: AllocMethod)(using a: Allocator[S]): S =
  inline method match
    case _: calloc.type => a.calloc()
    case _: malloc.type => a.malloc()
    case _ =>
      val memoryStack = summonFrom {
        case ms: MemoryStack => ms
        case _ => error("No given memory stack available")
      }
      inline method match
        case _: stackCalloc.type => a.stackCalloc(memoryStack)
        case _: stackMalloc.type => a.stackMalloc(memoryStack)

transparent inline def allocBuffer[S](capacity: Int, inline method: AllocMethod)(using a: Allocator[S]) =
  inline method match
    case _: calloc.type => a.callocBuffer(capacity)
    case _: malloc.type => a.mallocBuffer(capacity)
    case _ =>
      val memoryStack = summonFrom {
        case ms: MemoryStack => ms
        case _ => error("No given memory stack available")
      }
      inline method match
        case _: stackCalloc.type => a.stackCallocBuffer(memoryStack, capacity)
        case _: stackMalloc.type => a.stackMallocBuffer(memoryStack, capacity)

extension [S](inline cFunction: S => Unit)
  inline def returning(inline allocMethod: AllocMethod)(using a: Allocator[S]): S = 
    val s = alloc[S](allocMethod)
    cFunction(s)
    s

extension [S](inline cFunction: S => Int)
  inline def returning(inline allocMethod: AllocMethod)(inline onValidCode: Int => Boolean, errorMsg: String)(using a: Allocator[S]): S =
    val s = alloc[S](allocMethod)
    val ret = cFunction(s)
    if (!onValidCode(ret))
      throw new IllegalStateException(s"$errorMsg: expected ${codeOf(onValidCode)} but got $ret: ${VkUtil.describeResult(ret)}")
    else s

extension [S1, S2](inline cFunction: (S1, S2) => Int)
  inline def returning(inline allocMethod1: AllocMethod, inline allocMethod2: AllocMethod)(
      inline onValidCode: Int => Boolean,
      inline errorMsg: String
  )(using Allocator[S1], Allocator[S2]): (S1, S2) =
    val s1 = alloc[S1](allocMethod1)
    val s2 = alloc[S2](allocMethod2)
    val ret = cFunction(s1, s2)
    if (!onValidCode(ret))
      throw new IllegalStateException(s"$errorMsg: expected ${codeOf(onValidCode)} but got $ret: ${VkUtil.describeResult(ret)}")
    else (s1, s2)

inline def withStack[R](inline f: MemoryStack ?=> R): R =
  autoClose(MemoryStack.stackPush())(f)

extension (s: String)
  inline def nUtf8 = MemoryUtil.memUTF8(s)
  inline def nUtf8Stack(stack: MemoryStack) = stack.UTF8(s)

extension (b: java.nio.ByteBuffer) inline def sUtf8 = MemoryUtil.memUTF8(b)

extension (i: Int) inline def hasFlags(f: Int) = (i & f) == f
extension (i: Long) inline def hasFlags(f: Long) = (i & f) == f
extension (i: Int) inline def isBitSet(bitNum: Int) = (i & (1 << bitNum)) != 0

extension [S <: Struct[S]](buff: StructBuffer[S, ?])
  def iterator: Iterator[S] = new Iterator[S] {
    var curr = 0
    def hasNext = curr < buff.capacity
    def next() =
      val r: S = buff.get(curr)
      curr += 1
      r
  }

extension [S <: Struct[S]](s: S)
  inline def buffered(allocMethod: AllocMethod)(using a: Allocator[S] { type Buffer <: StructBuffer[S, ?] }): a.Buffer = {
    val res = allocBuffer[S](1, allocMethod)
    res.put(0, s)
    res
  }

extension [R](buff: PointerBuffer)
  def toIterator(of: PointerBuffer => R): Iterator[R] =
    Iterator.unfold(buff)(b => Option.when(b.hasRemaining)(of(buff) -> b))

extension [S <: CustomBuffer[S]](cv: CustomBuffer[S]) def putAll(s2: S) = cv.put(s2)

// cant ask for B to be <: CustomBuffer becuase then the type inferencer becomes unhelpful, so we rather use casts here. It's
// is sort of okay since Allocator will already guarantee the correctness

sealed trait CountAndAllocReturnValidator[R] {
  inline def validateResult(r: R)(inline functionCode: String): Unit
}
given NoReturnCountAndAllocValidator: CountAndAllocReturnValidator[Unit] with {
  inline def validateResult(r: Unit)(inline functionCode: String): Unit = {}
}
given IntCountAndAllocValidator: CountAndAllocReturnValidator[Int] with {
  inline def validateResult(r: Int)(inline functionCode: String): Unit =
    if r != VK10.VK_SUCCESS then throw new IllegalStateException(s"failed to enumerate $functionCode resulting code: $r")
}

extension [S, Buff, R <: Int | Unit](inline f: (IntBuffer, Buff) => R)
  inline def countAndAlloc(
      inline allocMethod: AllocMethod
  )
   (using a: Allocator[S] { type Buffer = Buff })
   (using validator: CountAndAllocReturnValidator[R]): Buff =
    val countPtr = MemoryStack.stackMallocInt(1)
    validator.validateResult(f(countPtr, null.asInstanceOf[Buff]))(codeOf(f))
    val buff = allocBuffer[S](countPtr.get(0), allocMethod)
    validator.validateResult(f(countPtr, buff))(codeOf(f))
    buff

//due to dotty's compiler bugs, I have to duplicate this
extension [S, Buff >: Null, R <: Int | Unit](inline f: (IntBuffer, LongBuffer) => R)
  inline def countAndAlloc()(using stack: MemoryStack, validator: CountAndAllocReturnValidator[R], di: DummyImplicit): LongBuffer =
    val countPtr = MemoryStack.stackMallocInt(1)
    validator.validateResult(f(countPtr, null))(codeOf(f))
    val buff = stack.callocLong(countPtr.get(0))
    validator.validateResult(f(countPtr, buff))(codeOf(f))
    buff
extension [S, Buff >: Null, R <: Int | Unit](inline f: (IntBuffer, IntBuffer) => R)
  inline def countAndAlloc()(using stack: MemoryStack, validator: CountAndAllocReturnValidator[R], d1: DummyImplicit, d2: DummyImplicit): IntBuffer =
    val countPtr = MemoryStack.stackMallocInt(1)
    validator.validateResult(f(countPtr, null))(codeOf(f))
    val buff = stack.callocInt(countPtr.get(0))
    validator.validateResult(f(countPtr, buff))(codeOf(f))
    buff

type StringLike = String | ByteBuffer
extension (s: StringLike)
  inline def toNative(inline allocMethod: AllocMethod): ByteBuffer = inline s match
    case s: String => VkUtil.toNativeString(s, allocMethod)
    case b: ByteBuffer => b
type StringLikeBuffer = PointerBuffer | Seq[String] | Seq[ByteBuffer] | Seq[StringLike]
extension (inline buff: StringLikeBuffer)
  inline def toPointerBuffer(inline allocMethod: AllocMethod): PointerBuffer =
    inline buff match
      case pb: PointerBuffer => pb
      case seq: Seq[String] => VkUtil.toNativeStringBuffer(seq, allocMethod)
      case seq: Seq[ByteBuffer] =>
        val res = allocBuffer[PointerBuffer](seq.size, allocMethod)
        seq foreach res.put
        res.flip()
      case seq: Seq[StringLike] =>
        val res = allocBuffer[PointerBuffer](seq.size, allocMethod)
        seq foreach (s => res put s.toNative(allocMethod))
        res.flip()

extension (buff: IntBuffer | LongBuffer)
  transparent inline def toArray(): Array[_] = inline buff match {
    case buff: IntBuffer =>
      val res = new Array[Int](buff.capacity)
      buff.position(0)
      buff.get(res)
      res
    case buff: LongBuffer =>
      val res = new Array[Long](buff.capacity)
      buff.position(0)
      buff.get(res)
      res
  }
