package guarana

import language.existentials

import Emitter._
sealed trait Emitter[T] {
  type ForInstance <: Singleton

  def init(source: ForInstance, ctx: Context): Unit

  def zip[U](emitter2: Emitter[U], zipStrategy: ZipStrategy = new ZipStrategy.ClosestEvents())(
      implicit instance2: ValueOf[emitter2.ForInstance]): Emitter.Aux[(T, U), ForInstance] = {
      
    val outer: this.type = this
    new Emitter[(T, U)] {
      type ForInstance = outer.ForInstance
      def init(source: outer.ForInstance, ctx: Context) = {
        val info = ZipStrategy.ZippingInfo(ctx, this, source)
        ctx.listen(outer) { case evt => zipStrategy.handleEvent(Left(evt), info) }(new ValueOf(source))
        ctx.listen(emitter2) { case evt => zipStrategy.handleEvent(Right(evt), info) }
      }
    }
  }
}
object Emitter {
  type Aux[T, Instance <: Singleton] = Emitter[T] { type ForInstance = Instance }

  private[guarana] trait Context {
    def listen[T](emitter: Emitter[T])(f: PartialFunction[T, Any])(implicit instance: ValueOf[emitter.ForInstance]): Unit
    def emit[T](emitter: Emitter[T], evt: T)(implicit instance: ValueOf[emitter.ForInstance]): Unit
    def dispose(emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Unit
  }

  trait ZipStrategy {
    def handleEvent[T, U](evt: T Either U, info: ZipStrategy.ZippingInfo[T, U, _]): Unit
  }
  object ZipStrategy {
    final case class ZippingInfo[T, U, Instance <: Singleton](context: Context, emitter: Emitter.Aux[(T, U), Instance], emitterKey: Instance)

    class ClosestEvents extends ZipStrategy {
      private val events = new Array[Any](2)
      def handleEvent[T, U](evt: T Either U, info: ZipStrategy.ZippingInfo[T, U, I forSome { type I }]): Unit = {
        evt match {
          case Left(t) => events(0) = t
          case Right(u) => events(1) = u
        }
        if (events(0) != null && events(1) != null) {
          info.context.emit(info.emitter, (events(0).asInstanceOf[T], events(1).asInstanceOf[U]))(new ValueOf(info.emitterKey))
          events(0) = null
          events(1) = null
        }
      }
    }

  }
}
