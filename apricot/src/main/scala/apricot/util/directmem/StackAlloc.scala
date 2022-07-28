package apricot.util.directmem

import guarana.unn
import jdk.incubator.foreign.{MemoryAccess, MemorySegment}

object StackAlloc {

  private val threadLocalStack = ThreadLocal.withInitial[Stack](() => Stack()).unn

  def withFrame[T](f: AllocContext ?=> T): T = {
    val stack = threadLocalStack.get().unn
    val mark = stack.position // record position
    val res = f(using stack.ctx)
    // bump off the stack
    stack.position = mark
    res
  }

  private class Stack() {
    var memSeg = MemorySegment.ofArray(new Array[Byte](1024 * 1024)).unn
    var position: Int = 0

    object ctx extends AllocContext {
      def alloc(size: Int) = {
        if (position + size >= memSeg.byteSize) { // must resize
          val newSeg = MemorySegment.ofArray(new Array[Byte](nextPowerOf2(position + size))).unn
          newSeg.copyFrom(memSeg)
        }

        val res = position
        position += size
        Address.unsafe(res)
      }
      private given Conversion[Address, Int] = _.value.toInt
      def getByte(addr: Address, offset: Int): Byte = MemoryAccess.getByteAtOffset(memSeg, addr + offset)
      def getInt(addr: Address, offset: Int): Int = MemoryAccess.getIntAtOffset(memSeg, addr + offset)
      def getLong(addr: Address, offset: Int): Long = MemoryAccess.getLongAtOffset(memSeg, addr + offset)
      def getFloat(addr: Address, offset: Int): Float = MemoryAccess.getFloatAtOffset(memSeg, addr + offset)
      def getDouble(addr: Address, offset: Int): Double = MemoryAccess.getDoubleAtOffset(memSeg, addr + offset)
      def getBytes(addr: Address, offset: Int, len: Int): Array[Byte] = memSeg.asSlice(addr + offset, len).unn.toByteArray.unn
      def setByte(addr: Address, offset: Int, v: Byte): Unit = MemoryAccess.setByteAtOffset(memSeg, addr + offset, v)
      def setInt(addr: Address, offset: Int, v: Int): Unit = MemoryAccess.setIntAtOffset(memSeg, addr + offset, v)
      def setLong(addr: Address, offset: Int, v: Long): Unit = MemoryAccess.setLongAtOffset(memSeg, addr + offset, v)
      def setFloat(addr: Address, offset: Int, v: Float): Unit = MemoryAccess.setFloatAtOffset(memSeg, addr + offset, v)
      def setDouble(addr: Address, offset: Int, v: Double): Unit = MemoryAccess.setDoubleAtOffset(memSeg, addr + offset, v)
      def setBytes(addr: Address, offset: Int, bytes: Array[Byte]): Unit =
        memSeg.asSlice(addr + offset, bytes.length).unn.copyFrom(MemorySegment.ofArray(bytes))
    }

    def nextPowerOf2(size: Int): Int = math.pow(2, (math.log(size) / math.log(2)).ceil).toInt
  }
}
