package apricot.util.directmem

import scala.annotation.implicitNotFound

@implicitNotFound("Cannot serialize/deserialize type ${T}")
trait Serde[T] {
  final type Out = T
  def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): T
  def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: T): Unit
}
object Serde {
  given Serde[Byte] with {
    def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): Byte = ctx.getByte(p, offset)
    def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: Byte): Unit = ctx.setByte(p, offset, v)
  }
  given Serde[Int] with {
    def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): Int = ctx.getInt(p, offset)
    def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: Int): Unit = ctx.setInt(p, offset, v)
  }
  given Serde[Long] with {
    def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): Long = ctx.getLong(p, offset)
    def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: Long): Unit = ctx.setLong(p, offset, v)
  }
  given Serde[Float] with {
    def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): Float = ctx.getFloat(p, offset)
    def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: Float): Unit =
      ctx.setFloat(p, offset, v)
  }
  given Serde[Double] with {
    def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): Double = ctx.getDouble(p, offset)
    def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: Double): Unit =
      ctx.setDouble(p, offset, v)
  }

  given [S <: Struct](using ctx: AllocContext): Serde[Inlined[S]] = StructSerde.asInstanceOf

  private object StructSerde extends Serde[Inlined[Struct]] {
    def read[S <: Struct](ctx: AllocContext)(p: Address, offset: Int): Inlined[Struct] =
      (p + offset).asInstanceOf[Inlined[Struct]]
    def write[S <: Struct](ctx: AllocContext)(p: Address, offset: Int, v: Inlined[Struct]): Unit =
      throw new IllegalStateException("Cannot write an entire struct, instead you first dereference the field and write to its fields")
  }
}
