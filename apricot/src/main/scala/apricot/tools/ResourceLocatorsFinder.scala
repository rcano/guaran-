package apricot
package tools

import better.files.*
import scala.quoted.*
import scala.tasty.inspector.*

@main def ResourceLocatorsFinder(directories: String*): Unit = {
  val directoryFiles = directories.map(File(_))
  require(directoryFiles.forall(_.isDirectory), "All passed in arguments must be directories")
  Console.err.println("Looking in directories " + directories)
  val tastyFiles = directoryFiles.iterator.flatMap(_.glob("**.tasty")).map(_.toString).filter(_.contains("apricot/")).toList

  object resourceLocatorInspector extends Inspector {

    def inspect(using Quotes)(tastys: List[Tasty[quotes.type]]): Unit =
      import quotes.reflect.*
      val resourceManagerTasty = tastys.find(_.path.contains("apricot/ResourceManager.class")).getOrElse(throw IllegalStateException("Couldn't locate ResourceManager class definition"))
      
      val resourceLocatorSym = Symbol.classSymbol("apricot.ResourceManager.ResourceLocator")

      val accum = new TreeAccumulator[List[Symbol]] {
        def foldTree(acc: List[Symbol], tree: Tree)(owner: Symbol): List[Symbol] = tree match
          case cls: ClassDef if cls.parents.exists(_.symbol == resourceLocatorSym) => cls.symbol :: acc
          case _ => foldOverTree(acc, tree)(owner)
      }
      val trees = tastys.map(_.ast: Tree)
      val locators = accum.foldTrees(Nil, trees)(Symbol.noSymbol)
      locators foreach println
  }
  val succeeded = TastyInspector.inspectAllTastyFiles(tastyFiles, Nil, directories.toList)(resourceLocatorInspector)
  Console.err.println(s"succeeded $succeeded")
}
