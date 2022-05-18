package guarana

import org.scalatest.funsuite.AnyFunSuite
import Binding.*

class ToolkitTest extends AnyFunSuite {
  val toolkit = TestToolkit()

  test("simple read and write variables") {
    val myVar = Var.autoName(0)
    toolkit.update {
      assert(myVar() == 0)
      myVar := 2
      assert(myVar() == 2)
    }
  }

  test("lazy variables are not computed until read") {
    val myVar = Var.autoName(0)
    toolkit.update {
      var mutated = false
      myVar := dyn {
        mutated = true
        2
      }
      assert(!mutated)
      myVar()
      assert(mutated)
    }
  }

  test("eager variables are computed when written") {
    val myVar = Var.autoName(0, true)
    toolkit.update {
      var mutated = false
      myVar := dyn {
        mutated = true
        2
      }
      assert(mutated)
    }
  }

  test("variables react to dependencies") {
    val pos = Var.autoName((0, 0), true)
    val sum = Var.autoName(0)
    toolkit.update {
      sum := dyn {
        val p = pos()
        p._1 + p._2
      }
      assert(sum() == 0)
      pos := (1, 2)
      assert(sum() == 3)
    }
  }

  test("eager variables react to dependencies immediately") {
    val pos = Var.autoName((0, 0))
    val sum = Var.autoName(0, true)
    toolkit.update {
      var mutated = false
      sum := dyn {
        val p = pos()
        mutated = true
        p._1 + p._2
      }
      pos := (1, 2)
      assert(mutated)
      assert(sum() == 3)
    }
  }

  test("external eager variables can react to variables") {
    val pos = Var.autoName((0, 0))
    object external {
      var theSum = 0
    }
    val sum = ExternalVar[external.type, Int]("ext-var", _.theSum, _.theSum = _, true).forInstance(external)
    toolkit.update {
      var mutated = false
      sum := dyn {
        val p = pos()
        mutated = true
        p._1 + p._2
      }
      assert(mutated) // assigning to an eager causes it to be evaluated
      mutated = false
      pos := (1, 2)
      assert(mutated)
      assert(sum() == 3)
      assert(external.theSum == 3)
    }
  }

  test("external variables return external values") {
    object external {
      var theSum = 0
    }
    val myVar = ExternalVar[external.type, Int]("ext-var", _.theSum, _.theSum = _, true).forInstance(external)
    toolkit.update {
      assert(myVar() == 0)
      external.theSum = 3
      assert(myVar() == 3)
    }
  }

  test("reacting to external variable changes") {
    object external {
      var theSum = 0
    }
    val extSum = ExternalVar[external.type, Int]("ext-var", _.theSum, _.theSum = _, true).forInstance(external)
    val mySum = Var.autoName(0)
    toolkit.update {
      mySum := dyn { extSum() + 3 }
      assert(mySum() == 3)
      external.theSum = 3
      summon[VarContext].externalPropertyUpdated(extSum, None)
      assert(mySum() == 6)
    }
  }
}
