package apricot.util

trait ObjectPool[T] {
  def withContext[R](f: ObjectPool.Context[T] ?=> R): R
}

object ObjectPool {
  trait Context[T] {
    def take(): T
  }

  trait Allocator[T] {
    def apply(): T
  }

  def apply[T](initialSize: Int)(using allocator: ObjectPool.Allocator[T]) = new ObjectPool[T] { self =>
    val pool = collection.mutable.ArrayDeque.fill(initialSize)(allocator())
    def take(): T = {
      if (pool.isEmpty) pool += allocator()
      pool.removeHead(false)
    }

    def withContext[R](f: ObjectPool.Context[T] ?=> R): R = {
      val r = f(using ctx)
      ctx.tracked foreach pool.+=
      ctx.tracked.clear()
      r
    }

    object ctx extends ObjectPool.Context[T] {
      val tracked = collection.mutable.ArrayBuffer[T]()
      def take() = {
        val res = self.take()
        tracked += res
        res
      }
    }
  }

  class AllocatingPool[T: Allocator] extends ObjectPool[T] {
    def withContext[R](f: ObjectPool.Context[T] ?=> R): R = f(using ctx)
    object ctx extends ObjectPool.Context[T] {
      def take() = summon[Allocator[T]]()
    }
  }
}
