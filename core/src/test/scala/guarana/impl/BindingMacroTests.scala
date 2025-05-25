package guarana
package impl

import org.scalatest.funsuite.AnyFunSuite
import guarana.util.strongRef
import scala.runtime.IntRef

class BindingMacroTests extends AnyFunSuite {

  test("Bindings with no closed over variables don't generate references") {
    Binding.dynDebug {
      val f = 2
      f * 2
    }
  }

  test("Bindings respect types annotated with strongRef") {
    val thisIsStrongRef = new Object() {}: @strongRef
    Binding.dynDebug {
      thisIsStrongRef.toString()
    }
  }

  test("Bindings don't generate references for other bindings") {
    val b1 = Binding.bind(ctx => 1)
    val b2 = Binding.dynDebug { b1() * b1() }
  }

  test("Bindings don't generate references for statically reachable references") {
    Binding.dynDebug {
      Predef.println("this is fine")
    }
    Binding.dynDebug {
      val v = new scala.collection.mutable.ArrayBuffer[String]()
      v.clear()
    }
    Binding.dynDebug {
      val f = new {
        val a = "a"
        val b = 2
      }
    }
  }

  test("Bindings generate references for closed over variables") {
    val foo = "lalala"
    Binding.dynDebug {
      foo.length()
    }
  }

  test("Nested bindings don't propagate weak references") {
    Binding.dynDebug {
      val s = "foo"
      val sub = Binding.dynDebug {
        s * s.length()
      }
      sub()
    }
  }

  test("Prefixes only consider non methods") {
    val someSeq = Seq(1, 2, 3)
    Binding.dynDebug {
      someSeq.iterator.filter(_ % 2 == 0).foreach(println)
    }
  }

  object dontCaptureMe {
    val someSeq = Seq(1, 2, 3)
  }
  test("Bindings generate references to prefix containing 'this'") {
    Binding.dynDebug {
      dontCaptureMe.someSeq.iterator.filter(_ % 2 == 0).foreach(println)
    }
  }

  test("Bindings correctly capture path to varriable assignments") {
    val intRef = IntRef(0)
    class Mut(var text: String)
    val v = Mut("foo")
    Binding.dynDebug {
      intRef.elem = 1
    }
    Binding.dynDebug {
      v.text = "???"
    }
  }
  // MacroUtils.softFail
}
