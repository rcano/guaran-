package guarana

import org.scalatest.FunSuite
import Var.Binding

class VarMacroTest extends FunSuite {

  val myVar = Var.autoName[Int]
  val myVar2 = Var.autoName[Int]
  var varBinding: Var.Binding[Int] = null

  test("var constants compile") {
    assertCompiles("varBinding = Binding(3)")
  }
  test("delimited continuation vars compile") {
    assertCompiles("""
      varBinding = Binding {
        myVar() + myVar2().toInt + someInt + 23
      }
    """)
  }
  test("self reference compiles") {
    assertCompiles("""
      implicit val ctx: VarContext = null
      myVar := Binding { myVar() + 1 }
    """)
  }

  test("can't create dependent variable with no dependencies") {
    assertDoesNotCompile("""
      implicit val ctx: VarContext = null
      myVar := Binding {
        val subVar = Var[String]()
        subVar().length
      }
    """)
  }
  test("dependent variabels compile") {
    assertCompiles("""
      implicit val ctx: VarContext = null
      myVar := Binding {
        val subVar = Var.autoName[String]
        myVar2() + subVar().length
      }
    """)
  }

  def someInt = 50
}
