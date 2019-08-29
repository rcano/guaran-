package guarana

import language.implicitConversions

trait VarOps {
  import VarOps._
  implicit def IntVarOps(v: Var[Int]) = new IntVarOps(v)
  implicit def ImmutableSeqVarOps[T, Instance <: Singleton](v: Var[collection.immutable.Seq[T]] { type ForInstance = Instance }) = new ImmutableSeqVarOps[T, Instance](v)
  implicit def GrowableSeqVarOps[T, Instance <: Singleton](v: Var[collection.mutable.Growable[T]] { type ForInstance = Instance }) = new GrowableSeqVarOps[T, Instance](v)
  implicit def ShrinkableSeqVarOps[T, Instance <: Singleton](v: Var[collection.mutable.Shrinkable[T]] { type ForInstance = Instance }) = new ShrinkableSeqVarOps[T, Instance](v)
}
object VarOps {
  class IntVarOps(private val v: Var[Int]) extends AnyVal {
    def +=(b: Binding[Int])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(prev) => v := v() + prev()
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) + compute(ctx))
    }
    def -=(b: Binding[Int])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(prev) => v := v() - prev()
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) - compute(ctx))
    }
  }

  class ImmutableSeqVarOps[T, Instance <: Singleton](val v: Var[collection.immutable.Seq[T]] { type ForInstance = Instance }) extends AnyVal {
    def +=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() :+ e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) :+ compute(ctx))
    }
    def ++=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[Instance]): Unit = b match {
      case Binding.Const(e) => v := (v() ++ e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) ++ compute(ctx))
    }
  }
  class GrowableSeqVarOps[T, Instance <: Singleton](private val v: Var[collection.mutable.Growable[T]] { type ForInstance = Instance }) extends AnyVal {
    def +=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() += e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) += compute(ctx))
    }
    def ++=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() ++= e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) ++= compute(ctx))
    }
  }
  class ShrinkableSeqVarOps[T, Instance <: Singleton](private val v: Var[collection.mutable.Shrinkable[T]] { type ForInstance = Instance }) extends AnyVal {
    def -=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() -= e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) -= compute(ctx))
    }
    def --=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() --= e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) --= compute(ctx))
    }
  }
}
