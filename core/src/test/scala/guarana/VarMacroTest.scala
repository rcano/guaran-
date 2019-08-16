package guarana

import org.scalatest.FunSuite

class VarMacroTest extends FunSuite {

  val myVar = Var.const(0)
  val myVar2 = Var.const("2")

  test("var constants compile") {
    assertCompiles("myVar := 3")
  }
  test("delimited continuation vars compile") {
    assertCompiles("""
      myVar := {
        myVar() + myVar2().toInt + someInt
      }
    """)
  }

  def someInt = 444
}
