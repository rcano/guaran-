package apricot.util.directmem

import guarana.unn
import jdk.incubator.foreign.{MemoryAccess, MemorySegment}

trait BasicSegmentAllocator extends AllocContext {
  def memorySegment: MemorySegment
  var position: Int = 0

  def alloc(size: Int) = {
    val res = position
    position += size
    Address.unsafe(res)
  }
  private given Conversion[Address, Int] = _.value.toInt
  def getByte(addr: Address, offset: Int): Byte = MemoryAccess.getByteAtOffset(memorySegment, addr + offset)
  def getInt(addr: Address, offset: Int): Int = MemoryAccess.getIntAtOffset(memorySegment, addr + offset)
  def getLong(addr: Address, offset: Int): Long = MemoryAccess.getLongAtOffset(memorySegment, addr + offset)
  def getFloat(addr: Address, offset: Int): Float = MemoryAccess.getFloatAtOffset(memorySegment, addr + offset)
  def getDouble(addr: Address, offset: Int): Double = MemoryAccess.getDoubleAtOffset(memorySegment, addr + offset)
  def getBytes(addr: Address, offset: Int, len: Int): Array[Byte] = memorySegment.asSlice(addr + offset, len).unn.toByteArray.unn
  def setByte(addr: Address, offset: Int, v: Byte): Unit = MemoryAccess.setByteAtOffset(memorySegment, addr + offset, v)
  def setInt(addr: Address, offset: Int, v: Int): Unit = MemoryAccess.setIntAtOffset(memorySegment, addr + offset, v)
  def setLong(addr: Address, offset: Int, v: Long): Unit = MemoryAccess.setLongAtOffset(memorySegment, addr + offset, v)
  def setFloat(addr: Address, offset: Int, v: Float): Unit = MemoryAccess.setFloatAtOffset(memorySegment, addr + offset, v)
  def setDouble(addr: Address, offset: Int, v: Double): Unit = MemoryAccess.setDoubleAtOffset(memorySegment, addr + offset, v)
  def setBytes(addr: Address, offset: Int, bytes: Array[Byte]): Unit =
    memorySegment.asSlice(addr + offset, bytes.length).unn.copyFrom(MemorySegment.ofArray(bytes))
}
object BasicSegmentAllocator {
  def nextPowerOf2(size: Int): Int = math.pow(2, (math.log(size) / math.log(2)).ceil).toInt
}
