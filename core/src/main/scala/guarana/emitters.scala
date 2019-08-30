package guarana

sealed trait Emitter[T] {
  type ForInstance <: Singleton

  def init(source: ForInstance, ctx: Emitter.Context): Unit

  def zip[U](emitter2: Emitter[U])(implicit instance2: ValueOf[emitter2.ForInstance]): Emitter.Aux[(T, U), ForInstance] = {
    type fi = ForInstance
    new Emitter[(T, U)] {
      type ForInstance = fi
      def init(source: fi, ctx: Emitter.Context) = {
        ctx.listen(emitter2) { case evt => () }
        ctx.listen(this) { case evt => () }(new ValueOf(source))
      }
    }
  }
}
object Emitter {
  type Aux[T, Instance <: Singleton] = Emitter[T] { type ForInstance = Instance }

  trait Context {
    def listen[T](emitter: Emitter[T])(f: PartialFunction[T, Any])(implicit instance: ValueOf[emitter.ForInstance]): Unit
    def emit[T](emitter: Emitter[T], evt: T)(implicit instance: ValueOf[emitter.ForInstance]): Unit
  }
}
