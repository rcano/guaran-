package guarana

import org.scalatest.funsuite.AnyFunSuite

class EventIteratorTest extends AnyFunSuite {
  val scenegraph = TestToolkit()
  // test("foreach") {
  //   var res: String | Null = null
  //   var et: Option[EventIterator[String]] = Some(EventIterator.foreach(res = _))
  //   et = et.get.step("res")
  //   assert(et.nonEmpty)
  //   assert(res == "res")
  // }
  // test("filter") {
  //   var res: String | Null = null
  //   var et: Option[EventIterator[String]] = Some(EventIterator.filter[String](_.nonEmpty).foreach(res = _))
  //   et = et.get.step("")
  //   assert(res == null)
  //   et = et.get.step("res")
  //   assert(res == "res")
  // }
  // test("take") {
  //   var res: String | Null = ""
  //   var et: Option[EventIterator[String]] = Some(EventIterator.take(3).foreach(res += _))
  //   et = et.get.step("1")
  //   et = et.get.step("2")
  //   et = et.get.step("3")
  //   assert(et.isEmpty)
  //   assert(res == "123")
  // }
  // test("takeWhile") {
  //   var res: String | Null = ""
  //   var et: Option[EventIterator[String]] = Some(EventIterator.takeWhile[String](_.length >= 2).foreach(res += _))
  //   et = et.get.step("12")
  //   et = et.get.step("345")
  //   et = et.get.step("6")
  //   assert(et.isEmpty)
  //   assert(res == "12345")
  // }
  // test("drop") {
  //   var res: String | Null = ""
  //   var et: Option[EventIterator[String]] = Some(EventIterator.drop(3).foreach(res += _))
  //   et = et.get.step("1")
  //   et = et.get.step("2")
  //   et = et.get.step("3")
  //   et = et.get.step("4")
  //   et = et.get.step("5")
  //   assert(et.nonEmpty)
  //   assert(res == "45")
  // }
  // test("dropWhile") {
  //   var res: String | Null = ""
  //   var et: Option[EventIterator[String]] = Some(EventIterator.dropWhile[String](_.length < 3).foreach(res += _))
  //   et = et.get.step("1")
  //   et = et.get.step("2")
  //   et = et.get.step("3") //rejected up to here
  //   et = et.get.step("456") //after this point, nothing else should be dropped
  //   et = et.get.step("7") //so this should be accepted
  //   assert(et.nonEmpty)
  //   assert(res == "4567")
  // }
}
