package apricot

import language.experimental.erasedDefinitions
import java.nio.Buffer
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryUtil

trait Managed[R]:
  inline def close(r: R): Unit

// extension (r: R) {
//   inline def foreach(inline f: R => Unit)(using inline d: Managed[R]): Unit =
//     try f(r)
//     finally d.close(r)

//   @experimental
//   inline def map[U](inline f: R => U)(using erased d: Managed[R]): U = f(r)
//   @experimental
//   inline def flatMap[U](inline f: R => U)(using erased d: Managed[R]): U = f(r)
// }

object Managed:
  given [T <: AutoCloseable]: Managed[T] with {
    inline def close(t: T) = t.close()
  }
  transparent inline def apply[T](using inline m: Managed[T]): Managed[T] = m

  given [B <: Buffer]: Managed[B] with {
    inline def close(b: B) = MemoryUtil.memFree(b)
  }

  given Managed[PointerBuffer] with {
    inline def close(b: PointerBuffer) = b.free()
  }

opaque type ManagedInstance[T] = ManagedInstance.opaques.Type[T]
object ManagedInstance {
  object opaques {
    opaque type Type[T] = T
  }

  extension [R](r: ManagedInstance[R]) {
    inline def unwrap: R = r.asInstanceOf
    inline def foreach(inline f: R => Unit)(using inline d: Managed[R]): Unit =
      try f(r.unwrap)
      finally d.close(r.unwrap)

    inline def map[U](inline f: R => U)(using inline d: Managed[R]): U =
      try f(r.unwrap)
      finally d.close(r.unwrap)

    inline def flatMap[U](inline f: R => U)(using inline d: Managed[R]): U =
      try f(r.unwrap)
      finally d.close(r.unwrap)
  }
}

extension [R](r: R) {
  inline def managed(using inline d: Managed[R]): ManagedInstance[R] = r.asInstanceOf
}

inline def autoClose[C, R](c: C)(inline f: C ?=> R)(using inline m: => Managed[C]): R =
  try f(using c)
  finally m.close(c)

/** Tracks a bunch of things that can be disposed, similar in spirit to scala.util.Using.Manager but not limited to a scope (also we cannot
  * reuse that class for it's final and the constructor is private).
  * 
  * Tracked resources are disposed/closed in reverse order with regard to addition, so latest added is first closed.
  */
class Disposer() {
  private var toDispose: List[() => Unit] = Nil
  def addToDispose(f: () => Unit): Unit = toDispose ::= f
  def track[T](t: T)(f: T => Unit): T =
    addToDispose(() => f(t))
    t
  inline def track[T](t: T)(using inline m: Managed[T]): t.type = {
    addToDispose(() => m.close(t))
    t
  }

  def close(): Unit = {
    toDispose foreach (_())
    toDispose = Nil
  }
}
