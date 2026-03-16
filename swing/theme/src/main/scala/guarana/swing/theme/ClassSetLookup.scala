package guarana
package swing
package theme

import scala.util.boundary

import ClassSetLookup.*
import FastList.*

/** Inteded for lookups based on classes. Nodes have a set of classes for which rules apply such that the node class set is a superset of
  * the defined rules. When lookup rules that apply to a node class set, multiple rules may be returned.
  *
  * This class builds a tree of hashmaps, and given a sequence of classes, it uses a stable sort lookup to ensure larger class-sets find
  * subsets.
  *
  * This class is mutable and not thread-safe
  */
private[theme] class ClassSetLookup[T](initialCapacity: Int = 10, loadFactor: Double = 0.8) {
  private val rootNode = LookupNode(new scala.collection.mutable.HashMap[String, LookupNode[T]](initialCapacity, loadFactor))

  /** Returns the associated Ts to the given class-set. A non typed requirement is that the class-seq *is sorted* with no repeated elements
    */
  def lookup(classSeq: List[String]): Seq[T] = {
    (rootNode.value match {
      case Unassigned => Nil
      case t: T @unchecked => List(t)
    }) :::
      lookupInNode(classSeq, rootNode)
  }

  private def lookupInNode(classSeq: List[String], node: LookupNode[T]): List[T] = classSeq match {
    case Nil => Nil
    case h :: tail =>
      val res = for {
        lookup <- Option(node.lookup.unn)
        node <- lookup.get(h)
        tailRes = lookupInNode(tail, node)
      } yield node.value match {
        case Unassigned => tailRes
        case t: T @unchecked => t :: tailRes
      }
      res.fold(Nil)(identity) ::: lookupInNode(tail, node)
  }

  /** Records an associated T to the given class-set. */
  def register(classSeq: Set[String], value: T): Unit = {
    val orderedClasses = classSeq.toArray.sortInPlace()
    val node = orderedClasses.foldLeft(rootNode) { (curr, clazz) =>
      if (curr.lookup == null) curr.lookup = new scala.collection.mutable.HashMap[String, LookupNode[T]](2, 1)
      curr.lookup.unn.getOrElseUpdate(clazz, LookupNode())
    }
    node.value = value
  }

  def remove(classSeq: Set[String]): Unit = {
    val orderedClasses = classSeq.toArray.sortInPlace()
    boundary {
      val nodesPath = orderedClasses.scanLeft(rootNode) { (curr, clazz) =>
        val node = curr.lookup.unn.getOrElse(clazz, boundary.break())
        if (node.lookup == null) boundary.break()
        node
      }
      nodesPath.last.value = Unassigned
      // walk it backwards to see if we have to prune
      nodesPath.reverse.sliding(2).zip(orderedClasses.reverse).foreach {
        case (collection.mutable.IndexedSeq(child, parent), clazz) if child.value == Unassigned && child.lookup == null =>
          parent.lookup.unn.remove(clazz)
        case _ =>
      }
    }
  }
}
private[theme] object ClassSetLookup {
  object Unassigned
  case class LookupNode[T](
      var lookup: scala.collection.mutable.HashMap[String, LookupNode[T]] | Null = null,
      var value: T | Unassigned.type = Unassigned
  )

  enum FastList[+T] {
    case #::(head: T, tail: FastList[T])
    case #:::(right: FastList[T], left: FastList[T])
    case FLNil

    def #::[U >: T](head: U) = new #::(head, this)
    def #:::[U >: T](right: FastList[U]) = new #:::(right, this)

    def toList: List[T] = this match {
      case head #:: tail => head :: tail.toList
      case right #::: left => right.toList ::: left.toList
      case FLNil => scala.Nil
    }

  }
}
