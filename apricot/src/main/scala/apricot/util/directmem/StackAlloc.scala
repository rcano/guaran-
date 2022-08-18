package apricot.util.directmem

import guarana.unn
import jdk.incubator.foreign.{MemoryAccess, MemorySegment}

object StackAlloc {

  private val threadLocalStack = ThreadLocal.withInitial[Stack](() => Stack()).unn

  def withFrame[T](f: AllocContext ?=> T): T = {
    val stack = threadLocalStack.get().unn
    val mark = stack.position // record position
    val res = f(using stack)
    // bump off the stack
    stack.position = mark
    res
  }

  private class Stack() extends BasicSegmentAllocator {
    var memSeg = MemorySegment.ofArray(new Array[Byte](1024 * 1024)).unn
    def memorySegment: MemorySegment = memSeg
    override def alloc(size: Int) = {
      if (position + size >= memSeg.byteSize) { // must resize
        val newSeg = MemorySegment.ofArray(new Array[Byte](BasicSegmentAllocator.nextPowerOf2(position + size))).unn
        newSeg.copyFrom(memSeg)
        memSeg = newSeg
      }
      super.alloc(size)
    }
  }
}
