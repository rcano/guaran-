package guarana.swing
package tree

import javax.swing.tree.{DefaultTreeModel, TreeModel, TreeNode => JTreeNode, DefaultMutableTreeNode}
import scala.collection.IndexedSeq
import scala.compiletime.{erasedValue, summonAll, summonInline, codeOf}
import TreeData._

object TreeData {
  def from[T: TreeNode](t: T): TreeModel =
    val tn = summon[TreeNode[T]]
    def rec(t: T): DefaultMutableTreeNode =
      val res = DefaultMutableTreeNode(t)
      if !tn.isLeaf(t) then tn.children(t).foreach(c => res.add(rec(c)))
      res
    DefaultTreeModel(rec(t))

  trait TreeNode[T]:
    def children(node: T): IndexedSeq[T]
    def isLeaf(node: T): Boolean

  object TreeNode:
    inline given derived[T](using mirror: deriving.Mirror.Of[T]) as TreeNode[T] =
      inline mirror match {
        case m: deriving.Mirror.SumOf[T] =>
          val treeNodes = deriveSumType[m.MirroredElemTypes, T]
          new TreeNode[T] {
            def isLeaf(node: T) = 
              val ord = m.ordinal(node)
              treeNodes.productElement(ord).asInstanceOf[TreeNode[T]].isLeaf(node)
            def children(node: T) =
              val ord = m.ordinal(node)
              treeNodes.productElement(ord).asInstanceOf[TreeNode[T]].children(node)
          }
          
        case m: deriving.Mirror.ProductOf[T] => deriveProductType[T, T](m)
      }

    inline private def deriveSumType[SumElems <: Tuple, ParentType]: Tuple =
      inline erasedValue[SumElems] match 
        case _: (Product *: tail) => 
          inline erasedValue[SumElems] match 
            case _: (p *: _) => deriveProductType[p, ParentType](summonInline[deriving.Mirror.ProductOf[p]]) *: deriveSumType[tail, ParentType]
        case _: EmptyTuple => EmptyTuple

    inline private def deriveProductType[P, ParentType](m: deriving.Mirror.ProductOf[P]): TreeNode[ParentType] =
      val pos = findRecursiveElem[ParentType, m.MirroredElemTypes](0)
      val isProductLeaf = pos == -1
      new TreeNode[ParentType] {
        def isLeaf(node: ParentType) = isProductLeaf
        def children(node: ParentType) = 
          if isProductLeaf then IndexedSeq.empty
          else node.asInstanceOf[Product].productElement(pos).asInstanceOf[IndexedSeq[ParentType]]
      }

    inline private def findRecursiveElem[Target, Tup <: Tuple](inline iter: Int): Int = inline erasedValue[Tup] match
      case _: (IndexedSeq[Target] *: tail) => iter
      case _: (h *: tail) => findRecursiveElem[Target, tail](iter + 1)
      case _: EmptyTuple => - 1
}
