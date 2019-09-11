package guarana

import scala.reflect.macros.blackbox.Context
import scala.util.chaining._

class VarMacros(val c: Context) {
  import c.universe._

  def thunk2Binding[T: WeakTypeTag](f: Tree): Tree = {
    val ObsValType = typeOf[ObsVal[_]]
    val VarObjSym = symbolOf[Var.type].asClass.module
    val BindingObjSym = symbolOf[Binding.type].asClass.module
    val VarContextType = typeOf[VarContext]
    val SeqApply = q"_root_.scala.Seq.apply"

//    println("Input\n" + f)

    //need to find out locally declared vals and see which of those are for Var/ObsVal or are assigned to one
    val locallyDeclaredValSyms = collection.mutable.Map.empty[Symbol, Tree]
    val varRefs = collection.mutable.Set.empty[Tree]
    new Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          case decl@ValDef(mods, _, _, _) => 
            locallyDeclaredValSyms(decl.symbol) = decl
            super.traverse(tree)

          case ident@Ident(_) if ident.tpe <:< ObsValType => varRefs += ident
          case ident@q"$prefx.$term" if ident.tpe <:< ObsValType => varRefs += ident
          case _ => super.traverse(tree)
        }
      }
    }.apply(f)
    val nonLocalVarsRefs = varRefs.filterNot(t => locallyDeclaredValSyms.contains(t.symbol))
    val localValsExternalVarRefs: Map[Tree, List[Tree]] = locallyDeclaredValSyms.values.map(valdef => valdef -> valdef.collect { case t if nonLocalVarsRefs(t) => t }).toMap

    //collect dependencies by finding calls to var(), not that if the var is defined locally, it cannot be a dependency
    val callsToVarApply = f.collect { case t@q"$vari.apply()($_, $_)" if vari.tpe <:< ObsValType => t -> vari }.toMap
//    println("call to var applies \n  " + callsToVarApply.mkString("\n  "))

    val dependencies = callsToVarApply.toSeq.flatMap { case (q"$pref.apply(...$_)", varIdent) =>
      locallyDeclaredValSyms.get(varIdent.symbol) match {
        case Some(localValTree) => //if it is a local val, then we must check if it transitively depends on external var refs and list those here
          localValsExternalVarRefs.get(localValTree).toSeq.flatten.map(c.untypecheck)
        case _ => // if there are no local vals for this sym, then it is external already
          Seq(c.untypecheck(pref))
      }
    }

    val callsToVarContrs = f.collect {
      case t@q"$sym.apply[$varTpe](..$params)" if sym.symbol == VarObjSym => t -> (TermName(c.freshName("var")) -> q"$VarObjSym.apply[$varTpe](..$params)")
      case t@q"$sym.autoName[$varTpe](..$params)(..$params2)" if sym.symbol == VarObjSym => t -> (TermName(c.freshName("var")) -> q"$VarObjSym.autoName[$varTpe](..$params)(..$params2)")
    }.toMap


    if (dependencies.isEmpty && callsToVarContrs.nonEmpty) {
      //if this binding is not reacting on any other outside var, then it's not a binding per se and it doesn't make sense
      c.abort(callsToVarContrs.head._1.pos, "Can't create bindings with sub Vars when there are no dependencies. Create a dependency by making this binding depend on a Var declared elsewhere.")
    }

    val result =
      if (callsToVarApply.isEmpty) { //just a constant
        q"$BindingObjSym.const($f)"
      } else {
        val implCtx = c.inferImplicitValue(VarContextType, true, false, callsToVarApply.head._1.pos)

        val ctxName = TermName(c.freshName("ctx"))
        val rewriter = new Transformer {
          override def transform(tree: Tree): Tree = (
            callsToVarApply.get(tree).map(vari => q"$ctxName.apply($vari)") orElse
            callsToVarContrs.get(tree).map { case (newName, _) => q"$newName" }
          ).getOrElse {

            def checkForVarContextBeingPassedAsArgument(apply: Apply): Option[Tree] = {
              if (apply.args.exists(_.symbol == implCtx.symbol)) {
//                println("A VarContext is being passed to " + apply.symbol.asMethod)
                val varContextArgs = apply.symbol.asMethod.paramLists.flatten.filter(_.info =:= VarContextType)
//                println("  varContext args " + varContextArgs)
                if (varContextArgs.sizeIs > 1) c.abort(apply.pos, "Can't process calling a method that take more than one VarContext as arguments, because the macro is not smart enough to process it")
                else if (varContextArgs.nonEmpty)
                  Some(Apply(apply.fun, apply.args.map(arg => if (arg.symbol == implCtx.symbol) q"$ctxName" else arg))) //.tap(tree => println("  replacing context as " + tree))
                else None
              } else None
            }

            tree match {
              case apply@Apply(Apply(_, _), params) => checkForVarContextBeingPassedAsArgument(apply).getOrElse(super.transform(tree))

              case apply@Apply(method, params) =>
                if (VarContextType.members.exists(_ == method.symbol)) q"$ctxName.${method.symbol}(..$params)"
                else checkForVarContextBeingPassedAsArgument(apply).getOrElse(super.transform(tree))

              case t if t.tpe != null && t.tpe <:< VarContextType => c.abort(t.pos, "Leaking a reference to a context defined outside the lambda. This will introduce memory leaks and is forbidden.")

              case _ => super.transform(tree)
            }
          }
        }
        val rewrittenThunk = c untypecheck rewriter.transform(f) //need to untypecheck here so that the nodes are properly retyped and re parented

        val dependentsDecls = callsToVarContrs.toSeq.map { case (_, (name, app)) => q"val $name = $app" }
//        println("Dependents " + q"..$dependentsDecls")
        val implicitCtx = q"implicit val $ctxName: ${tq""}"
        q"""
         ..$dependentsDecls
         $BindingObjSym.bind($SeqApply(..$dependencies), $SeqApply(..${callsToVarContrs.values.map(_._1)}))($implicitCtx => $rewrittenThunk)
         """
      }
    println("Result\n" + result + "\n--------------------\n" + showRaw(result) + "\n")

    result
  }
}
