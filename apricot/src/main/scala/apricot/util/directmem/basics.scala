package apricot.util.directmem

import scala.compiletime.summonAll

trait Sized[T] {
  def size: Int
}

object Sized {
  def apply[T](s: Int): Sized[T] = new Sized[T] { def size = s }
  given Sized[Byte] = apply(1)
  given Sized[Int] = apply(4)
  given Sized[Long] = apply(8)
  given Sized[Float] = apply(4)
  given Sized[Double] = apply(8)

  transparent inline def of[T](using s: Sized[T]): s.type = s
}

opaque type Address = Long
object Address {
  def unsafe(l: Long): Address = l
  extension (a: Address) {
    def value: Long = a
    def +(offset: Long): Address = a + offset
  }
}

trait AllocContext {
  def alloc[S: Sized]: Address = alloc(summon[Sized[S]].size)
  def allocStruct[S <: Struct: Sized]: Pointer[S, this.type] = Pointer(alloc(summon[Sized[S]].size))
  def alloc(size: Int): Address
  def getByte(addr: Address, offset: Int): Byte
  def getInt(addr: Address, offset: Int): Int
  def getLong(addr: Address, offset: Int): Long
  def getFloat(addr: Address, offset: Int): Float
  def getDouble(addr: Address, offset: Int): Double
  def getBytes(addr: Address, offset: Int, len: Int): Array[Byte]
  def setByte(addr: Address, offset: Int, v: Byte): Unit
  def setInt(addr: Address, offset: Int, v: Int): Unit
  def setLong(addr: Address, offset: Int, v: Long): Unit
  def setFloat(addr: Address, offset: Int, v: Float): Unit
  def setDouble(addr: Address, offset: Int, v: Double): Unit
  def setBytes(addr: Address, offset: Int, bytes: Array[Byte]): Unit
}

opaque type Pointer[S <: Struct, Allocator <: AllocContext] <: S = S
object Pointer {
  def apply[S <: Struct, Allocator <: AllocContext](a: Address): Pointer[S, Allocator] = a.asInstanceOf

  implicit class PointerSelectable[S <: Struct, A <: AllocContext](private val s: Pointer[S, A]) extends AnyVal with Selectable {
    inline def selectDynamic(using m: Struct.StructOf[S], ctx: A)(using inline sized: Sized[S])(x: String & Singleton)(using
        inline offset: FieldOffset[x.type, S],
        serde: Serde[FieldType[x.type, Tuple.Zip[m.MirroredElemLabels, m.MirroredElemTypes]]]
    ): serde.Out = serde.read(ctx)(s, offset)

    inline def applyDynamic[V](using ctx: A)(using inline sized: Sized[S])(x: String & Singleton)(using
        getter: Getter[x.type],
        inline offset: FieldOffset[getter.Out, S]
    )(param: V)(using serde: Serde[V]): Unit = serde.write(ctx)(s, offset, param)
  }
  import scala.compiletime.*

  opaque type FieldOffset[Name <: String & Singleton, S <: Struct] = Int
  object FieldOffset {
    inline given [Name <: String & Singleton, S <: Struct](using s: Struct.StructOf[S]): FieldOffset[Name, S] =
      fieldOffset[Name, Tuple.Zip[s.MirroredElemLabels, s.MirroredElemTypes]](0)

    inline def fieldOffset[Name <: String & Singleton, Fields <: Tuple](offset: Int): Int = {
      inline erasedValue[Fields] match {
        case _: EmptyTuple => error("unreachable? this should be guaranteed by Selectable + StructOf")
        case _: ((nameTpe, sizedTpe) *: tail) =>
          val sized = summonInline[Sized[sizedTpe]]
          inline if (constValue[nameTpe] == constValue[Name]) offset
          else fieldOffset[Name, tail](offset + sized.size)
      }
    }

    extension [Name <: String & Singleton, S <: Struct](fo: FieldOffset[Name, S]) def value: Int = fo
  }

  trait Getter[Name <: String] { type Out <: String & Singleton }
  object Getter {
    transparent inline given [N <: String]: Getter[N] = ${ getterMacro[N] }

    import scala.quoted.*
    def getterMacro[N <: String: Type](using Quotes) = {
      import quotes.reflect.*
      val getterName = Type.valueOfConstant[N].get.stripSuffix("_$eq")
      val tpe = ConstantType(StringConstant(getterName)).asType.asInstanceOf[Type[String]]
      '{ null.asInstanceOf[Getter[N] { type Out = tpe.Underlying }] }
    }
  }

  type FieldType[Name <: String, Fields <: Tuple] = Fields match {
    case (Name, tpe) *: tail => tpe
    case ? *: tail => FieldType[Name, tail]
  }

  extension [S <: Struct, A <: AllocContext](s: Pointer[S, A])(using m: Struct.StructOf[S]) {
    inline def show: String = "[" + showFields[S, m.MirroredElemLabels, A](s) + "]"
  }
  private inline def showFields[S <: Struct, Names <: Tuple, A <: AllocContext](
      s: Pointer[S, A]
  )(using m: Struct.StructOf[S]): String = {
    inline erasedValue[Names] match {
      case _: (h *: other *: tail) =>
        inline constValue[h] match
          case h: String =>
            toStringField(
              s.selectDynamic(using m, summonInline)(using summonInline)(h)(using
                summonInline[Pointer.FieldOffset[h.type, S]],
                summonInline[Serde[Pointer.FieldType[h.type, Tuple.Zip[m.MirroredElemLabels, m.MirroredElemTypes]]]]
              )
            ) + ", " + showFields[S, other *: tail, A](s)
      case _: (h *: EmptyTuple) =>
        inline constValue[h] match
          case h: String =>
            toStringField(
              s.selectDynamic(using m, summonInline)(using summonInline)(h)(using
                summonInline[Pointer.FieldOffset[h.type, S]],
                summonInline[Serde[Pointer.FieldType[h.type, Tuple.Zip[m.MirroredElemLabels, m.MirroredElemTypes]]]]
              )
            )
    }
  }
  private inline def toStringField(inline f: Any): String = inline f match {
    case v: Inlined[s] =>
      val alloc = summonInline[AllocContext]
      Pointer[s, alloc.type](v).show(using summonInline)
    case other => other.toString
  }
}

opaque type Inlined[S <: Struct] <: S = S
object Inlined {
  inline given [S <: Struct](using s: Sized[S]): Sized[Inlined[S]] = s.asInstanceOf
  given inlinedAsPointer[S <: Struct](using ctx: AllocContext): Conversion[Inlined[S], Pointer.PointerSelectable[S, ctx.type]] =
    Pointer.PointerSelectable(_)
}

/** Struct definition. It cannot be inherited, only refined */
opaque type Struct = Address
object Struct {

  /** Custom mirror type for Structs. Main difference with ProductOf is that MirroredType does not reflect the struct source, because this
    * can be either a `Pointer[?] & Struct` or the actual struct and we need the actual struct
    */
  type StructOf[T] = deriving.Mirror.Product { type MirroredType; type MirroredMonoType = T; type MirroredElemTypes <: Tuple }
  transparent inline given structMirror[S <: Struct]: StructOf[S] = ${ StructMacros.structMirror[S] }

  inline def derivedSized[S <: Struct](using m: StructOf[S]): Sized[S] = {
    val res = summonAll[Tuple.Map[m.MirroredElemTypes, Sized]].toList.foldLeft(0)((acc, f) => acc + f.asInstanceOf[Sized[?]].size)
    Sized[S](res)
  }
}
