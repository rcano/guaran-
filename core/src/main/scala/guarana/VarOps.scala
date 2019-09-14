package guarana

import language.implicitConversions

trait VarOps {
  import VarOps._
  implicit def IntVarOps[Instance <: Singleton](v: Var.Aux[Int, Instance]) = new IntVarOps[Instance](v)
  implicit def ImmutableSeqVarOps[T, Instance <: Singleton](v: Var.Aux[collection.immutable.Seq[T], Instance]) = new ImmutableSeqVarOps[T, Instance](v)
  implicit def GrowableSeqVarOps[T, Instance <: Singleton](v: Var.Aux[collection.mutable.Growable[T], Instance]) = new GrowableSeqVarOps[T, Instance](v)
  implicit def ShrinkableSeqVarOps[T, Instance <: Singleton](v: Var.Aux[collection.mutable.Shrinkable[T], Instance]) = new ShrinkableSeqVarOps[T, Instance](v)
}
object VarOps {
  class IntVarOps[Instance <: Singleton](private val v: Var.Aux[Int, Instance]) extends AnyVal {
    def +=(b: Binding[Int])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(prev) => v := v() + prev()
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) + compute(ctx))
    }
    def -=(b: Binding[Int])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(prev) => v := v() - prev()
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) - compute(ctx))
    }
  }

  class ImmutableSeqVarOps[T, Instance <: Singleton](val v: Var.Aux[collection.immutable.Seq[T], Instance]) extends AnyVal {
    def +=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() :+ e())
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) :+ compute(ctx))
    }
    def ++=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() ++ e())
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) ++ compute(ctx))
    }
  }
  class GrowableSeqVarOps[T, Instance <: Singleton](private val v: Var.Aux[collection.mutable.Growable[T], Instance]) extends AnyVal {
    def +=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() += e())
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) += compute(ctx))
    }
    def ++=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() ++= e())
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) ++= compute(ctx))
    }
  }
  class ShrinkableSeqVarOps[T, Instance <: Singleton](private val v: Var.Aux[collection.mutable.Shrinkable[T], Instance]) extends AnyVal {
    def -=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() -= e())
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) -= compute(ctx))
    }
    def --=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() --= e())
      case Binding.Compute(compute) => Binding.Compute(ctx => v()(instance, ctx) --= compute(ctx))
    }
  }
}
