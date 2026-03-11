package guarana

import language.unsafeNulls
import org.scalatest.funsuite.AnyFunSuite

class VarMacroTest extends AnyFunSuite {

  val myVar = Var.autoName[Int](0)
  val myVar2 = Var.autoName[Int](0)
  var varBinding: Binding[Int] = null

  test("var constants compile") {
    assertCompiles("varBinding = Binding.dyn(3)")
  }
  test("delimited continuation vars compile") {
    def someInt = 50
    assertCompiles("""
      varBinding = Binding.dyn {
        myVar() + myVar2().toInt + someInt + 23
      }
    """)
  }
  test("self reference compiles") {
    assertCompiles("""
      implicit val ctx: VarContext = null
      myVar := Binding.dyn { myVar() + 1 }
    """)
  }

  test("dependent variabels compile") {
    assertCompiles("""
      implicit val ctx: VarContext = null
      myVar := Binding.dyn {
        val subVar = Var.autoName[String]("init")
        myVar2() + subVar().length
      }
    """)
  }

}
