package guarana
package impl

import scala.quoted.*

object BindingMacro {

  inline def dyn[T](inline f: VarContextAction[T]): Binding[T] = ${ cleanUpBindingClosure('{ f }, false) }
  inline def dynDebug[T](inline f: VarContextAction[T]): Binding[T] = ${ cleanUpBindingClosure('{ f }, true) }

  private def cleanUpBindingClosure[T: Type](expr: Expr[VarContextAction[T]], debug: Boolean)(using Quotes): Expr[Binding[T]] = {
    Macros(debug).cleanUpBindingClosure(expr)
  }

  // TODO: unit test macros, somehow.
  private class Macros(debug: Boolean)(using val quotes: Quotes) {
    import quotes.reflect.*
    def println(a: Any): Unit = if (debug) Predef.println(a)
    val AnyRefTypeRepr = TypeRepr.of[AnyRef]
    val DerefSym = TypeRepr.of[guarana.util.WeakRef[?]].typeSymbol.declaredMethod("deref").head
    val InstanceofSym = defn.AnyClass.declaredMethod("asInstanceOf").head
    val BindingTypeRepr = TypeRepr.of[Binding[?]]
    val WeakRefTypeRepr = TypeRepr.of[util.WeakRef[?]]
    val StrongRefAnnotTypeRepr = TypeRepr.of[util.strongRef]

    def cleanUpBindingClosure[T: Type](expr: Expr[VarContextAction[T]]): Expr[Binding[T]] = {

      println(s"Raw code:\n  ${expr.asTerm.show}")
      println("")

      val referencesFinder = new TreeAccumulator[List[Ref]] {
        override def foldTree(x: List[Ref], tree: Tree)(owner: Symbol): List[Ref] = tree match {
          case r: Ref => extractHoistablePrefix(r) match {
            case Some(prefix) if shouldHoistSymbol(prefix) => prefix :: x
            case _ => x
          }
          case other => foldOverTree(x, other)(owner)
        }
      }
      val lambdaTerm = expr.asTerm
      val foundReferences = referencesFinder.foldTree(Nil, lambdaTerm)(Symbol.spliceOwner)
      println(s"Found references:\n  ${foundReferences
        .map(r => s"${r.show}: ${r.symbol}")
        .mkString("\n  ")}")
      println("")

      println("Generating:")
      val weakRefsToRefs = foundReferences.groupBy(_.symbol).map { (sym, refs) =>
        val wr = createWeakRef(refs.head)
        println(wr.show)
        wr -> refs
      }
      val refsToSelects = weakRefsToRefs.flatMap((vd, refs) =>
        refs.map(ref => ref -> Ref(vd.symbol).select(DerefSym).appliedToArgss(Nil).select(InstanceofSym).appliedToType(ref.tpe))
      )

      val mapper = new TreeMap {
        override def transformTerm(tree: Term)(owner: Symbol): Term = tree match {
          case r: Ref => refsToSelects.get(r).getOrElse(super.transformTerm(tree)(owner))
          case _ => super.transformTerm(tree)(owner)
        }
      }

      val transformedLambda = mapper.transformTerm(lambdaTerm)(Symbol.spliceOwner)
      val transformedLambdaExpr = transformedLambda.asExprOf[VarContextAction[T]]
      val res = Block(weakRefsToRefs.keys.toList, '{ Binding.bind[T](c => ${ transformedLambdaExpr }(using c)) }.asTerm)

      println("")
      println("Result:\n" + res.show)
      if (debug) report.info(res.show)

      res.asExprOf[Binding[T]]
    }

    /** Compute the longest prefix (ltr) that needs hoisting, if any. SymbolPath here is reversed, meaning head the symbol of the current
      * Ref
      */
    def extractHoistablePrefix(term: Term): Option[Ref] = {
      val annotations = List
        .unfold(term.tpe.widen) {
          case AnnotatedType(core, annot) => Some(annot -> core)
          case _ => None
        }

      if (annotations.exists(_.tpe <:< StrongRefAnnotTypeRepr)) return None

      val res: Option[Ref] = term match {
        case Ident("_root_") => None
        case r @ Select(This(_), _) => Some(r)
        case r @ Ident(_) =>
          val ownershipPath = calcOwnershipPath(Left(r)).reverse
          // println(s"${Console.YELLOW_B}$ownershipPath${Console.RESET}: sr=${isStaticallyReachable(ownershipPath)},ml=${isMacroLocalSymbol(ownershipPath)},nmr${!isNonMethodRef(r.symbol)}")
          if (isStaticallyReachable(ownershipPath) || isMacroLocalSymbol(ownershipPath) || !isNonMethodRef(r.symbol)) None 
          else Some(r)
        case r @ Select(prefix, _) =>
          val hoistedPrefix = extractHoistablePrefix(prefix)
          if (isNonMethodRef(r.symbol)) hoistedPrefix.map(_ => r) // if the prefix needs hoisting, so does this
          else hoistedPrefix
        case TypeApply(Select(prefix, _), _) => extractHoistablePrefix(prefix)
        case Apply(Select(prefix, _), _) => extractHoistablePrefix(prefix)
        case _ => None
      }
      // println(s"${Console.MAGENTA}$term = $res${Console.RESET}")
      res
    }

    def shouldHoistSymbol(r: Ref) = {
      val s = r.symbol
      val isAnyRef = r.tpe <:< AnyRefTypeRepr
      val isBinding = r.tpe <:< BindingTypeRepr
      val isWeakRef = r.tpe <:< WeakRefTypeRepr

      println(s"""|testing $r: ${r.tpe.show} -- wide: ${r.tpe.widen.show}:
                  |  is <:< AnyRef = $isAnyRef is <:< Binding = $isBinding is <:< WeakRef = $isWeakRef
                  |""".stripMargin)

      isAnyRef && !isBinding && !isWeakRef
    }

    def isMacroLocalSymbol(symbolPath: List[Symbol]): Boolean = symbolPath.exists(_ == Symbol.spliceOwner)

    def isNonMethodRef(s: Symbol): Boolean = s.isValDef || s.flags.is(Flags.Module)
    def isStaticallyReachable(symbolPath: List[Symbol]): Boolean = {
      symbolPath.iterator.takeWhile(isNonMethodRef).exists(s => s == defn.RootClass || s == defn.RootPackage)
    }

    /** buld the path of references ownership */
    def calcOwnershipPath(ref: Ref Either Symbol, res: List[Symbol] = Nil): List[Symbol] = ref match {
      case Left(ref @ Select(prefix: Ref, _)) => calcOwnershipPath(Left(prefix), ref.symbol :: res)
      case Left(ref @ Ident("_root_")) => ref.symbol.typeRef.typeSymbol :: res
      case Left(ref) => 
        if (ref.symbol.maybeOwner.isNoSymbol) ref.symbol :: res
        else calcOwnershipPath(Right(ref.symbol.owner), ref.symbol :: res)
      case Right(refSymbol) =>
        if (refSymbol == defn.RootClass) refSymbol :: res
        else {
          val owner = refSymbol.maybeOwner
          if (owner.isNoSymbol) refSymbol :: res
          else calcOwnershipPath(Right(owner), refSymbol :: res)
        }
    }

    def createWeakRef(r: Ref) = {
      type F[x <: AnyRef] = x
      r.asExpr match {
        case '{ $e: F[t] } =>
          ValDef(
            Symbol.newVal(
              Symbol.spliceOwner,
              s"_wr_${r.symbol.name}${nextFreshNameIdx()}",
              TypeRepr.of[guarana.util.WeakRef[t]],
              Flags.EmptyFlags,
              Symbol.noSymbol
            ),
            Some('{ guarana.impl.WeakRefFactory(${ e }) }.asTerm)
          )
      }
    }

    var freshNameIdx = 0
    def nextFreshNameIdx() = {
      val r = freshNameIdx
      freshNameIdx += 1
      r
    }
  }
}
