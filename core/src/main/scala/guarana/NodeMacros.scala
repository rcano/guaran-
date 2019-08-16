package guarana

import scala.language.experimental.macros
import scala.annotation.StaticAnnotation
import scala.annotation.compileTimeOnly
import scala.reflect.macros.blackbox.Context


@compileTimeOnly("missing macro expansion?")
class AutoNode extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro NodeMacros.autoNode
}

class NodeMacros(val c: Context) {
  import c.universe._

  def autoNode(annottees: Tree*): Tree = {
    val NodeType = rootMirror.staticClass("_root_.guarana.Node")
    
    val generated = annottees.head match {
      case q"$mods class $clas[..$tparams] $ctorMods(...$argss) extends {..$earlydefs} with ..$parents {..$stats}" =>
        var parentsWithNode = parents.map(c.typecheck(_, mode = c.TYPEmode, silent = false))
        if (!parentsWithNode.exists(_.symbol == NodeType)) parentsWithNode :+= tq"$NodeType"

        q"""$mods class $clas[..$tparams] $ctorMods(...$argss) extends {..$earlydefs} with ..$parentsWithNode {..${fillNodeDescr(stats)}}"""
      case other => c.abort(c.enclosingPosition, "Only case classes are supported for AutoNode")
    }
    println("generated\n" + generated)

    q"..${generated +: annottees.tail}"
  }

  private def fillNodeDescr(stats: Seq[Tree]): Seq[Tree] = {
    val NodeType = rootMirror.staticClass("_root_.guarana.Node")
    val childrenSymbol = NodeType.info.member(TermName("children")).asMethod
    println("children symbol = " + childrenSymbol.fullName)
//    NodeType.
//    val VarType = rootMirror.staticClass("_root_.guarana.Var")

    def isMethod(stat: Tree, target: MethodSymbol): Boolean = {
      val typed = c.typecheck(stat, silent = true)
      typed.isDef && {
        val m = typed.symbol.asMethod
        val params1 = m.paramLists.flatten
        val params2 = target.paramLists.flatten
        m.paramLists.size == target.paramLists.size && params1.sizeIs == params2.size &&
        params1.zip(params2).forall { case (p1, p2) => p1.typeSignature =:= p2.typeSignature } &&
        m.returnType <:< target.returnType
      }
    }

//    val mustAddChildren = !stats.exists { case q"def children: Seq[$VarType[$NodeType]]" => true; case _ => false}
    val mustAddChildren = !stats.exists(isMethod(_, childrenSymbol))
    println("must add children ? " + mustAddChildren)
    stats
  }
}
