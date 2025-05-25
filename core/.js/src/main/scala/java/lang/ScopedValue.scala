package java.lang

import java.{util => ju}
import java.util.function.Supplier

trait ScopedValue[T] {
  def get(): T
  def isBound(): scala.Boolean
  def orElse(other: T): T
  def orElseThrow[X <: Throwable](sup: Supplier[X]): T
}

object ScopedValue {
  private class Impl[T] extends ScopedValue[T] {
    var stack: List[T] = Nil
    def get(): T = stack match {
      case Nil => throw ju.NoSuchElementException()
      case e :: _ => e
    }
    def isBound(): scala.Boolean = stack != Nil
    def orElse(other: T): T = stack match {
      case e :: _ => e
      case _ => other
    }
    def orElseThrow[X <: Throwable](sup: Supplier[X]): T = stack match {
      case e :: _ => e
      case _ => throw sup.get()
    }
  }

  def newInstance[T](): ScopedValue[T] = new Impl()
  def where[T](key: ScopedValue[T], value: T, parent: Carrier | Null = null): Carrier = new Carrier {
    inline def withStack[R](op: => R) = {
      val i = key.asInstanceOf[Impl[T]]
      i.stack ::= value
      try {
        op
      } finally {
        i.stack = i.stack.tail
      }
    }

    override def call[R, X <: Throwable](op: CallableOp[R, X]): R = {
      parent match {
        case null => 
          withStack {
            op.call()
          }
        case p =>
          p.withStack {
            withStack {
              op.call()
            }
          }
      }
    }

    override def run(op: Runnable): Unit = {
      parent match {
        case null => 
          withStack {
            op.run()
          }
        case p =>
          p.withStack {
            withStack {
              op.run()
            }
          }
      }
    }
  }

  @FunctionalInterface
  trait CallableOp[T, X <: Throwable] {
    @throws[X]
    def call(): T
  }

  trait Carrier {
    private[ScopedValue] def withStack[R](op: => R): R

    def call[R, X <: Throwable](op: CallableOp[R, X]): R
    def get[T](key: ScopedValue[T]): T = key.asInstanceOf[Impl[T]].stack.head
    def run(op: Runnable): Unit
    def where[T](key: ScopedValue[T], value: T): Carrier = ScopedValue.where(key, value, this)
  }
  // def callWhere[T, R](key: ScopedValue[T], value: T, op: Callable[R]): R = {
  //   val i = key.asInstanceOf[Impl[T]]
  //   i.stack ::= value
  //   val r = op.call()
  //   i.stack = i.stack.tail
  //   r
  // }
  // def getWhere[T, R](key: ScopedValue[T], value: T, op: Supplier[R]): R = {
  //   val i = key.asInstanceOf[Impl[T]]
  //   i.stack ::= value
  //   val r = op.get()
  //   i.stack = i.stack.tail
  //   r
  // }
  // def runWhere[T](key: ScopedValue[T], value: T, op: Runnable): Unit = {
  //   val i = key.asInstanceOf[Impl[T]]
  //   i.stack ::= value
  //   val r = op.run()
  //   i.stack = i.stack.tail
  // }
}
