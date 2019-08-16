package guarana

import scala.reflect.macros.blackbox.Context

class VarMacros(val c: Context) {
  import c.universe._

  def thunk2Var[T: WeakTypeTag](f: Tree): Tree = {
    val VarType = typeOf[Var[_]]
    val VarApplySym = VarType.member(TermName("apply"))

    val callsToApply = f.collect { case t@q"$_.apply()" if t.symbol == VarApplySym => t }.zipWithIndex.toMap

    val result =
      if (callsToApply.isEmpty) { //just a constant
        q"${symbolOf[Var.type]}.const($f)"

      } else {
        val arrName = TermName(c.freshName("arr"))
        val rewriter = new Transformer {
          override def transform(tree: Tree): Tree = callsToApply.get(tree) match {
            case Some(i) => q"$arrName.apply($i).asInstanceOf[${tree.tpe}]"
            case _ => super.transform(tree)
          }
        }
        val rewrittenThunk = c untypecheck rewriter.transform(f) //need to untypecheck here so that the nodes are properly retyped and re parented
        val vars = callsToApply.toSeq.sortBy(_._2).map(_._1 match { case q"$pref.apply()" => c.untypecheck(pref)})
        c.typecheck(q"_root_.guarana.Var.apply(_root_.scala.Seq.apply(..$vars))(($arrName: Array[Any]) => $rewrittenThunk)")
      }
//    println("Result\n" + result)

    result
  }
}
