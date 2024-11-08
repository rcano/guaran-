package java.lang

import java.{util => ju}
import java.util.function.Supplier
import java.util.concurrent.Callable

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
  def callWhere[T, R](key: ScopedValue[T], value: T, op: Callable[R]): R = {
    val i = key.asInstanceOf[Impl[T]]
    i.stack ::= value
    val r = op.call()
    i.stack = i.stack.tail
    r
  }
  def getWhere[T, R](key: ScopedValue[T], value: T, op: Supplier[R]): R = {
    val i = key.asInstanceOf[Impl[T]]
    i.stack ::= value
    val r = op.get()
    i.stack = i.stack.tail
    r
  }
  def runWhere[T](key: ScopedValue[T], value: T, op: Runnable): Unit = {
    val i = key.asInstanceOf[Impl[T]]
    i.stack ::= value
    val r = op.run()
    i.stack = i.stack.tail
  }
}
