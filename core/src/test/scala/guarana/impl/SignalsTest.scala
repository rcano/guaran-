package guarana
package impl

import language.implicitConversions
import org.agrona.collections.LongHashSet
import org.scalatest.funsuite.AnyFunSuite
import scala.concurrent.duration.*
import guarana.util.DeclaringOwner

class SignalsTest extends AnyFunSuite {

  val noopReporter = new SignalSwitchboard.Reporter {
    def signalRemoved[T](sb: SignalSwitchboard, s: Keyed[Var[T]]): Unit = ()
    def signalInvalidated[T](sb: SignalSwitchboard, v: Var[T], instance: v.ForInstance): Unit = ()
    def signalUpdated[T](
        sb: SignalSwitchboard,
        v: Var[T],
        instance: v.ForInstance,
        oldValue: Option[T],
        newValue: T,
        dependencies: LongHashSet,
        dependents: LongHashSet
    ): Unit = ()
  }
  val varsLookup = VarsLookup()
  val defaultValueProvider = new SignalSwitchboard.DefaultVarValueProvider {
    def defaultValueFor[T](v: Var[T], instance: v.ForInstance): T = v.initialValue(instance)
  }
  given ValueOf[this.type] = ValueOf(this)
  def newVar[T](v: T)(using DeclaringOwner) = {
    val res = Var.autoName(v).forInstance(this)
    varsLookup.recordVarUsage(res, _ => (), _ => ())
    res
  }

  import animation.TransitionType

  extension [T](v: Var[T]) def keyed(using ValueOf[v.ForInstance]) = ObsVal.obs2Keyed(v)

   scribe.Logger("guarana").withHandler(minimumLevel = Some(scribe.Level.Trace)).replace()

  test("simple signal propagation") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(0)
    val text = newVar("")
    sb.bind(text, this, TransitionType.Instant)(ctx => s"current count = ${ctx(count, this)}")

    assert(sb(text, this) == "current count = 0")
    assert(sb.relationships(text, this).exists(_.dependencies.contains(count.keyed.id))) //relationships get computed lazily with the value
    sb(count, this) = 1
    assert(sb(text, this) == "current count = 1")
  }

  test("complex signal propagation") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(0)
    val name = newVar("Unk")
    val text = newVar("")
    sb.bind(text, this, TransitionType.Instant)(ctx => s"${ctx(count, this)} for ${ctx(name, this)}")

    assert(sb(text, this) == "0 for Unk")
    assert(sb.relationships(text, this).exists(r => r.dependencies.contains(count.keyed.id) && r.dependencies.contains(name.keyed.id)))
    sb(count, this) = 1
    assert(sb(text, this) == "1 for Unk")
    sb(name, this) = "P1"
    assert(sb(text, this) == "1 for P1")
  }

  test("signal rebinding disposes old binding") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(0)
    val text = newVar("")
    sb.bind(text, this, TransitionType.Instant)(ctx => s"current count = ${ctx(count, this)}")

    assert(sb(text, this) == "current count = 0")
    assert(sb.relationships(text, this).exists(_.dependencies.contains(count.keyed.id)))
    sb(count, this) = 1
    assert(sb(text, this) == "current count = 1")
    sb(text, this) = "new value"
    assert(sb(text, this) == "new value")
    sb(count, this) = 2
    assert(sb(text, this) == "new value")
  }

  test("signal self reference") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(0)
    sb.bind(count, this, TransitionType.Instant) { ctx =>
      println(s"current count = ${ctx(count, this)}")
      ctx(count, this) + 1
    }

    assert(sb(count, this) == 1) //on bind it gets called so it equals 1
    assert(sb.relationships(count, this).exists(_.dependencies.isEmpty))
    sb(count, this) = 1
    assert(sb(count, this) == 1) //but the signal doesn't trigger itself
    sb(count, this) = 2
    assert(sb(count, this) == 2)
  }

  test("signal transitions on values") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(0)
    sb.update(count, this, 8, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    // advance time some
    Thread.sleep(55)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(count, this) == 1)
    Thread.sleep(55)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(count, this) == 2)
  }

  test("signal transitions on bindings") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(4)
    val mult = newVar(0)
    sb.bind(mult, this, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))(ctx => ctx(count, this) * 2)

    assert(sb(mult, this) == 0)
    assert(sb.relationships(mult, this).exists(_.dependencies.contains(count.keyed.id))) //relationships get computed lazily with the value
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    // beacuse we are linearly inteprolating from 0 to 8, in 400 millis, it means the numbre goes up by one every 50ms

    for (expected <- 1 to 8) {
      // advance time some
      Thread.sleep(55)
      ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
      assert(sb(mult, this) == expected)
    }

    assert(ManualTimers.createdTimers.isEmpty)
  }

  test("signal transitions on bindings can be interrupted when a dependency changes") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    val count = newVar(4)
    val mult = newVar(0)
    sb.bind(mult, this, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))(ctx => ctx(count, this) * 2)

    assert(sb(mult, this) == 0)
    assert(sb.relationships(mult, this).exists(_.dependencies.contains(count.keyed.id))) //relationships get computed lazily with the value
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    // beacuse we are linearly inteprolating from 0 to 8, in 400 millis, it means the numbre goes up by one every 50ms

    // advance time some
    Thread.sleep(105)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(mult, this) == 2)
    sb.update(count, this, 10)
    assert(ManualTimers.createdTimers.isEmpty)

    assert(sb(mult, this) == 2) // value must continue to be it's last known value
    assert(ManualTimers.createdTimers.size == 1)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime()) // this call causes the Timeline to start tracking time
    // after reading it, it must retrigger a transition, this time to the number 20
    Thread.sleep(105) // wait two "steps"
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(mult, this) == 6)
  }

  test("simple var projections") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    case class Vec2(x: Double, y: Double)
    object VecVar extends Var[Vec2] {
      def name: String = "VecVar"
      def initialValue(v: Any)(using v.type <:< ForInstance): Vec2 = Vec2(0, 0)
      def eagerEvaluation: Boolean = false

      val x = Projection("x", _.x, (v, x) => v.copy(x = x))
      val y = Projection("y", _.y, (v, y) => v.copy(y = y))
    }

    val myVar = VecVar.forInstancePrecise(this)
    varsLookup.recordVarUsage(myVar, _ => (), _ => ())
    assert(sb(myVar, this) == Vec2(0, 0))
    sb(myVar, this) = Vec2(1, 2)
    assert(sb(myVar, this) == Vec2(1, 2))
    assert(sb(myVar.x, this) == 1)
    sb(myVar.x, this) = 5
    assert(sb(myVar, this) == Vec2(5, 2))
    assert(sb(myVar.x, this) == 5)
    assert(sb(myVar.y, this) == 2)
    sb(myVar.y, this) = 10
    assert(sb(myVar, this) == Vec2(5, 10))
    assert(sb(myVar.y, this) == 10)
  }

   test("var projections with bindings") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    case class Vec2(x: Double, y: Double)
    object VecVar extends Var[Vec2] {
      def name: String = "VecVar"
      def initialValue(v: Any)(using v.type <:< ForInstance): Vec2 = Vec2(0, 0)
      def eagerEvaluation: Boolean = false

      val x = Projection("x", _.x, (v, x) => v.copy(x = x))
      val y = Projection("y", _.y, (v, y) => v.copy(y = y))
    }
    val xOffset = newVar(1)

    val myVar = VecVar.forInstancePrecise(this)
    varsLookup.recordVarUsage(myVar, _ => (), _ => ())
    sb(myVar, this) = Vec2(5, 5)
    // when binding a projection, we need to bind the projected var to all the parts
    sb.bind(myVar.x, this, TransitionType.Instant)(sb => sb(xOffset, this) * 2.0)
    assert(sb(myVar.x, this) == 2)
    assert(sb(myVar, this) == Vec2(2, 5))

    sb(xOffset, this) = 10
    assert(sb(myVar.x, this) == 20)
    assert(sb(myVar, this) == Vec2(20, 5))
  }

  test("projected signal transitions on bindings") {
    // clone of the basic test but with projected variables now
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    case class Vec2(x: Int, y: Int)
    object VecVar extends Var[Vec2] {
      def name: String = "VecVar"
      def initialValue(v: Any)(using v.type <:< ForInstance): Vec2 = Vec2(0, 0)
      def eagerEvaluation: Boolean = false

      val x = Projection("x", _.x, (v, x) => v.copy(x = x))
      val y = Projection("y", _.y, (v, y) => v.copy(y = y))
    }
    val myVar = VecVar.forInstancePrecise(this)
    varsLookup.recordVarUsage(myVar, _ => (), _ => ())
    sb(myVar, this) = Vec2(0, 2)
    val count = newVar(4)
    sb.bind(myVar.x, this, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))(ctx => ctx(count, this) * 2)

    assert(sb(myVar.x, this) == 0)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    for (expected <- 1 to 8) {
      // advance time some
      Thread.sleep(55)
      ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
      assert(sb(myVar.x, this) == expected)
      assert(sb(myVar, this) == Vec2(expected, 2))
    }

    assert(ManualTimers.createdTimers.isEmpty)
  }

  test("nested projections") {
    implicit val sb = BetterSignalSwitchboardImpl(noopReporter, defaultValueProvider, varsLookup, false, ManualTimers)
    case class Bounds(x: Double, y: Double, width: Double, height: Double)
    object BoundsVar extends Var[Bounds] {
      def name: String = "VecVar"
      def initialValue(v: Any)(using v.type <:< ForInstance): Bounds = Bounds(0, 0, 0, 0)
      def eagerEvaluation: Boolean = false

      object loc extends Projection[(x: Double, y: Double)](
        "loc",
        b => (b.x, b.y),
        (bounds, loc) => bounds.copy(x = loc.x, y = loc.y)
      ) {
        val x = Projection("x", _.x, (v, x) => (x = x, y = v.y)).asInstanceOf[Var.Aux[Double, ForInstance]]
        val y = Projection("y", _.y, (v, y) => (x = v.x, y = y)).asInstanceOf[Var.Aux[Double, ForInstance]]
      }
      object dim extends Projection[(width: Double, height: Double)](
        "dim",
        b => (b.width, b.height),
        (bounds, loc) => bounds.copy(width = loc.width, height = loc.height)
      ) {
        val width = Projection("x", _.width, (v, w) => (w, v.height)).asInstanceOf[Var.Aux[Double, ForInstance]]
        val height = Projection("y", _.height, (v, h) => (v.width, h)).asInstanceOf[Var.Aux[Double, ForInstance]]
      }
    }

    val myVar = BoundsVar.forInstancePrecise(this)
    varsLookup.recordVarUsage(myVar, _ => (), _ => ())
    sb(myVar, this) = Bounds(10, 10, 20, 20)
    assert(sb(myVar.loc, this).toTuple == (10, 10))
    assert(sb(myVar.dim, this).toTuple == (20, 20))
    // val vv: Var.Aux[Double, this.type] = myVar.loc.x
    assert(sb(myVar.loc.x, this) == 10)
    assert(sb(myVar.loc.y, this) == 10)
    assert(sb(myVar.dim.width, this) == 20)
    assert(sb(myVar.dim.height, this) == 20)

    sb(myVar.loc.x, this) = 15
    assert(sb(myVar.loc, this).toTuple == (15, 10))
    assert(sb(myVar.dim, this).toTuple == (20, 20))
    sb(myVar.dim.height, this) = 10
    assert(sb(myVar.loc, this).toTuple == (15, 10))
    assert(sb(myVar.dim, this).toTuple == (20, 10))
  }
}
