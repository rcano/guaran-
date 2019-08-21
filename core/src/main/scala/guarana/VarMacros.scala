package guarana

import scala.reflect.macros.blackbox.Context
class VarMacros(val c: Context) {
  import c.universe._

  def thunk2Binding[T: WeakTypeTag](f: Tree): Tree = {
    val VarType = typeOf[Var[_]]
    val VarObjSym = symbolOf[Var.type].asClass.module
    val VarContextType = typeOf[VarContext]
    val SeqApply = q"_root_.scala.Seq.apply"

//    println("Input\n" + f)

    val locallyDeclaredVals = f.collect { case decl@ValDef(_, _, _, _) => decl.symbol }.toSet
    //collect dependencies by finding calls to var(), not that if the var is defined locally, it cannot be a dependency
    val callsToVarApply = f.collect { case t@q"$vari.apply()" if vari.tpe <:< VarType => t -> vari }.toMap
    val dependencies = callsToVarApply.toSeq.filterNot(t => locallyDeclaredVals(t._2.symbol)).
      map(_._1 match { case q"$pref.apply()" => c.untypecheck(pref)})
    val callsToVarContrs = f.collect {
      case t@q"$sym.apply[$varTpe]()" if sym.symbol == VarObjSym => t -> (TermName(c.freshName("var")) -> q"$VarObjSym.apply[$varTpe]()")
    }.toMap

    if (dependencies.isEmpty && callsToVarContrs.nonEmpty) {
      //if this binding is not reacting on any other outside var, then it's not a binding per se and it doesn't make sense
      c.abort(callsToVarContrs.head._1.pos, "Can't create bindings with sub Vars when there are no dependencies. Create a dependency by making this binding depend on a Var declared elsewhere.")
    }

    val result =
      if (callsToVarApply.isEmpty) { //just a constant
        q"${VarObjSym}.const($f)"
      } else {
        val ctxName = TermName(c.freshName("ctx"))
        val rewriter = new Transformer {
          override def transform(tree: Tree): Tree = (
            callsToVarApply.get(tree).map(vari => q"$ctxName.apply($vari)") orElse
            callsToVarContrs.get(tree).map { case (newName, _) => q"$newName" }
          ).getOrElse(super.transform(tree))
        }
        val rewrittenThunk = c untypecheck rewriter.transform(f) //need to untypecheck here so that the nodes are properly retyped and re parented

        val dependentsDecls = callsToVarContrs.toSeq.map { case (_, (name, app)) => q"val $name = $app" }
//        println("Dependents " + q"..$dependentsDecls")
        q"""
         ..$dependentsDecls
         $VarObjSym.bind($SeqApply(..$dependencies), $SeqApply(..${callsToVarContrs.values.map(_._1)}))(($ctxName: $VarContextType) => $rewrittenThunk)
         """
      }
//    println("Result\n" + result + "\n" + showRaw(result))

    result
  }
}
