package guarana

import language.implicitConversions

trait VarOps {
  import VarOps._
  implicit def intVarOps(v: Var[Int]) = new IntVarOps(v)
  implicit def GrowableSeqVarOps[T](v: Var[collection.mutable.Growable[T]]) = new GrowableSeqVarOps(v)
  implicit def ShrinkableSeqVarOps[T](v: Var[collection.mutable.Shrinkable[T]]) = new ShrinkableSeqVarOps(v)
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

  class ImmutableSeqVarOps[T](private val v: Var[collection.immutable.Seq[T]]) extends AnyVal {
    def +=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() :+ e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) :+ compute(ctx))
    }
    def ++=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() ++ e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) ++ compute(ctx))
    }
  }
  class GrowableSeqVarOps[T](private val v: Var[collection.mutable.Growable[T]]) extends AnyVal {
    def +=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() += e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) += compute(ctx))
    }
    def ++=(b: Binding[IterableOnce[T]])(implicit context: VarContext, instance: ValueOf[v.ForInstance]): Unit = b match {
      case Binding.Const(e) => v := (v() ++= e())
      case Binding.Compute(deps, depnts, compute) => Binding.Compute(deps, depnts, ctx => v()(instance, ctx) ++= compute(ctx))
    }
  }
  class ShrinkableSeqVarOps[T](private val v: Var[collection.mutable.Shrinkable[T]]) extends AnyVal {
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
