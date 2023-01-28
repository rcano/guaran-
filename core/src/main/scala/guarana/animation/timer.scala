package guarana.animation

import scala.concurrent.duration.FiniteDuration

trait TimersDef {
  type Timer
  given TimerLike: TimerLike[Timer]
}

trait TimerLike[T] {
  def apply(delay: FiniteDuration, onUpdate: T => Unit, onRestart: T => Unit): T
  extension (t: T) {
    def start(): Unit
    def stop(): Unit
  }
}