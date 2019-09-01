package guarana

trait Emitter[T] {
  protected val listeners: mutable.Set[T => Any] = mutable.Set.empty
  protected def push[E <: T](e: E): Unit = listeners.foreach(_.apply(e))
  def on(func: T => Any): Unit = listeners += func
  
  def map[U](func: T => U): Emitter[U] = new Emitter[U] { mapped =>
    self.on(t => mapped.push(func(t)))
  }
  
  def filter(func: T => Boolean): Emitter[T] = new Emitter[T] { filtered =>
    self.on(t => if (func(t)) filtered.push(t))
  }
}

object Emitter {

  case class Either[L, R](lEm: Emitter[L], rEm: Emitter[R]) extends Emitter[Either[L, R]] {
    lEm.on(this push Left(_))
    rEm.on(this push Right(_))
  }

  trait Zip[L, R] extends Emitter[(L, R)]
  object Zip {
    case class Closest[L, R](lEm: Emitter[L], rEm: Emitter[R]) extends Zip[L, R] {
      private var lastL: L = null[L]
      private var lastR: R = null[R]
      
      private def attemptPush(): Unit = if (lastL != null && lastR != null) {
        push( (lastL, lastR) )
        lastL = null[L]
        lastR = null[R]
      }

      lEm.on { l => lastL = l; attemptPush() }
      rEm.on { r => lastR = r; attemptPush() }
    }
    
    case class First[L, R](lEm: Emitter[L], rEm: Emitter[R]) extends Zip[L, R] {
      private var lastL: L = null[L]
      private var lastR: R = null[R]

      private def attemptPush(): Unit = if (lastL != null && lastR != null) {
        push( (lastL, lastR) )
        lastL = null[L]
        lastR = null[R]
      }

      lEm.on { l => if (lastL == null) lastL = l; attemptPush() }
      rEm.on { r => if (lastR == null) lastR = r; attemptPush() }
    }

    case class Updated[L, R](lEm: Emitter[L], rEm: Emitter[R]) extends Zip[L, R] {
      private var lastL: L = null[L]
      private var lastR: R = null[R]

      private def attemptPush(): Unit = if (lastL != null && lastR != null) {
        push( (lastL, lastR) )
      }

      lEm.on { l => lastL = l; attemptPush() }
      rEm.on { r => lastR = r; attemptPush() }
    }
  }
}
