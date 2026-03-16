package guarana.swing.theme

import org.scalatest.funsuite.AnyFunSuite

class ClassSetLookupTests extends AnyFunSuite {
  class Fixture {
    val lookup = ClassSetLookup[String]()
  }

  test("recording single value")(new Fixture {
    lookup.register(Set("a"), "ok")
  })

  test("recording single value to a path of classes")(new Fixture {
    lookup.register(Set("a", "b", "c"), "ok")
  })

  test("recording single value to empty path (root)")(new Fixture {
    lookup.register(Set(), "ok")
  })

  test("find key in empty lookup")(new Fixture {
    assert(lookup.lookup(List("a", "b")).isEmpty)
  })

  test("find key in empty path (root)")(new Fixture {
    lookup.register(Set(), "ok")
    assert(lookup.lookup(List()) == List("ok"))
  })

  test("values recorded in empty path (root) are always returned")(new Fixture {
    lookup.register(Set(), "ok")
    assert(lookup.lookup(List("foo")) == List("ok"))
  })

  test("find class-set as exact match")(new Fixture {
    lookup.register(Set("a", "b", "c"), "ok")
    assert(lookup.lookup(List("a", "b", "c")) == List("ok"))
  })

  
  test("find class-set with multiple matches")(new Fixture {
    lookup.register(Set(), "ok0")
    lookup.register(Set("a", "b"), "ok1")
    lookup.register(Set("a", "b", "c"), "ok2")
    lookup.register(Set("a", "c"), "ok3")
    assert(lookup.lookup(List("a", "b", "c", "d")) == List("ok0", "ok1", "ok2", "ok3"))
  })

}
