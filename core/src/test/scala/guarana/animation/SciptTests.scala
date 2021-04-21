package guarana
package animation

import scala.concurrent.duration.*

import org.scalatest.funsuite.AnyFunSuite
import java.util.BitSet
import ScriptDsl.{*, given}

class SciptTests extends AnyFunSuite {
  val sc = TestToolkit()
  test("simple script runs in 1 step") {
    val engine = new ScriptEngine(sc)
    val bitset = new BitSet(2)

    engine run script {
      bitset flip 0
      bitset flip 1
    }
    engine.update(0)
    assert(bitset.cardinality() == 2)
  }
  
  test("2 steps script") {
    val engine = new ScriptEngine(sc)
    val bitset = new BitSet(2)
    engine run script {
      bitset flip 0
      await(1.milli)
      bitset flip 1
    }
    engine.update(0)
    assert(bitset.cardinality() == 1)
    engine.update(1.milli.toNanos)
    assert(bitset.cardinality() == 2)
  }
  
  test("forking doesn't stall the script") {
    val engine = new ScriptEngine(sc)
    val bitset = new BitSet(3)
    engine run script {
      bitset flip 0
      engine run script {
        await(1.milli)
        bitset flip 2
      }
      bitset flip 1
    }
    engine.update(0)
    assert(bitset.cardinality() == 2)
    engine.update(1.milli.toNanos)
    assert(bitset.cardinality() == 3)
  }
  
  test("Parallel steps get executed at the same time") {
    val engine = new ScriptEngine(sc)
    val bitset = new BitSet(3)
    engine run script {
      parallel(
        script(bitset flip 0),
        script(await(1.milli)),
        script(bitset flip 1),
        script(await(1.milli)),
      )
      bitset flip 2
    }
    engine.update(0)
    assert(bitset.cardinality() == 2)
    assert(!bitset.get(2))
    engine.update(1.milli.toNanos)
    assert(bitset.cardinality() == 3)
  }
  
  test("Fork and parallel behave as expected") {
    val engine = new ScriptEngine(sc)
    val bitset = new BitSet(4)
    engine run script {
      engine run script {
        await(1.milli)
        parallel(
          script(bitset flip 0),
          script(await(1.milli)),
          script(bitset flip 1),
          script(await(1.milli)),
        )
        bitset flip 3
      }
      bitset flip 2
    }
    engine.update(0)
    assert(bitset.cardinality() == 1)
    assert(bitset.get(2))
    engine.update(1.milli.toNanos)
    assert(bitset.cardinality() == 3)
    assert(!bitset.get(3))
    engine.update(2.milli.toNanos)
    assert(bitset.cardinality() == 4)
    assert(bitset.get(3))
  }
  
  test("Useing engine time and waitUntil") {
    val engine = new ScriptEngine(sc)
    val bitset = new BitSet(1)
    engine run script {
      waitUntil(currentTimeMillis > 5)
      bitset.flip(0)
    }
    engine.update(0)
    assert(bitset.cardinality() == 0)
    assert(!bitset.get(0))
    engine.update(1.milli.toNanos)
    assert(bitset.cardinality() == 0)
    assert(!bitset.get(0))
    engine.update(6.milli.toNanos)
    assert(bitset.cardinality() == 1)
    assert(bitset.get(0))
  }

}
  
