package guarana

import org.scalatest.funsuite.AnyFunSuite
import Binding.dyn

class VarMacroTest extends AnyFunSuite {

  val myVar = Var.autoName[Int](0)
  val myVar2 = Var.autoName[Int](0)
  var varBinding: Binding[Int] = null.asInstanceOf[Binding[Int]]

  test("var constants compile") {
    assertCompiles("varBinding = dyn(3)")
  }
  test("delimited continuation vars compile") {
    assertCompiles("""
      varBinding = dyn {
        myVar() + myVar2().toInt + someInt + 23
      }
    """)
  }
  test("self reference compiles") {
    assertCompiles("""
      implicit val ctx: VarContext = null
      myVar := dyn { myVar() + 1 }
    """)
  }

  test("can't create dependent variable with no dependencies") {
    assertDoesNotCompile("""
      implicit val ctx: VarContext = null
      myVar := dyn {
        val subVar = Var[String]("subVar", "init")
        subVar().length
      }
    """)
  }
  test("dependent variabels compile") {
    assertCompiles("""
      implicit val ctx: VarContext = null
      myVar := dyn {
        val subVar = Var.autoName[String]("init")
        myVar2() + subVar().length
      }
    """)
  }

  def someInt = 50
}
