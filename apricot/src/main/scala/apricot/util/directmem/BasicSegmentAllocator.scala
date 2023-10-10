package apricot.util.directmem

import guarana.unn
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

trait BasicSegmentAllocator extends AllocContext {
  import BasicSegmentAllocator._
  def memorySegment: MemorySegment
  var position: Int = 0

  def alloc(size: Int) = {
    val res = position
    position += size
    Address.unsafe(res)
  }
  private given Conversion[Address, Int] = _.value.toInt
  def getByte(addr: Address, offset: Int): Byte = memorySegment.get(ValueLayout.JAVA_BYTE, addr + offset)
  def getInt(addr: Address, offset: Int): Int = memorySegment.get(ByteAlignedInt, addr + offset)
  def getLong(addr: Address, offset: Int): Long = memorySegment.get(ByteAlignedLong, addr + offset)
  def getFloat(addr: Address, offset: Int): Float = memorySegment.get(ByteAlignedFloat, addr + offset)
  def getDouble(addr: Address, offset: Int): Double = memorySegment.get(ByteAlignedDouble, addr + offset)
  def getBytes(addr: Address, offset: Int, len: Int): Array[Byte] = memorySegment.asSlice(addr + offset, len).unn.toArray(ValueLayout.JAVA_BYTE).unn
  def setByte(addr: Address, offset: Int, v: Byte): Unit = memorySegment.set(ValueLayout.JAVA_BYTE, addr + offset, v)
  def setInt(addr: Address, offset: Int, v: Int): Unit = memorySegment.set(ByteAlignedInt, addr + offset, v)
  def setLong(addr: Address, offset: Int, v: Long): Unit = memorySegment.set(ByteAlignedLong, addr + offset, v)
  def setFloat(addr: Address, offset: Int, v: Float): Unit = memorySegment.set(ByteAlignedFloat, addr + offset, v)
  def setDouble(addr: Address, offset: Int, v: Double): Unit = memorySegment.set(ByteAlignedDouble, addr + offset, v)
  def setBytes(addr: Address, offset: Int, bytes: Array[Byte]): Unit =
    memorySegment.asSlice(addr + offset, bytes.length).unn.copyFrom(MemorySegment.ofArray(bytes))
}
object BasicSegmentAllocator {
  def nextPowerOf2(size: Int): Int = math.pow(2, (math.log(size) / math.log(2)).ceil).toInt

  val ByteAlignedInt = ValueLayout.JAVA_INT.unn.withByteAlignment(8).unn
  val ByteAlignedLong = ValueLayout.JAVA_LONG.unn.withByteAlignment(8).unn
  val ByteAlignedFloat = ValueLayout.JAVA_FLOAT.unn.withByteAlignment(8).unn
  val ByteAlignedDouble = ValueLayout.JAVA_DOUBLE.unn.withByteAlignment(8).unn
}
