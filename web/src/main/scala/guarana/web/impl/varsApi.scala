package guarana.web
package impl

import scala.annotation.{experimental, MacroAnnotation}
import scala.quoted.*
import guarana.{ExternalVar, Var}

@experimental
class varsApi[VarsDefinitionType] extends MacroAnnotation {
  def transform(using Quotes)(tree: quotes.reflect.Definition): List[quotes.reflect.Definition] = {
    import quotes.reflect.*
    val VarInstanceTpe = TypeRepr.of[Var[?]]
    val ExtVarInstanceTpe = TypeRepr.of[ExternalVar[?]]
    // val extVarInstanceAuxTpe = TypeRepr.of[ExternalVar.type].typeSymbol.typeMember("Aux").typeRef
    val ModifierVar = TypeRepr.of[ModifierVar]
    println(tree.show(using Printer.TreeStructure))
    tree match {
      case ClassDef(name, ctr, parents, self, body) =>
        val annot = tree.symbol.getAnnotation(TypeRepr.of[varsApi[?]].typeSymbol).get
        annot.asExpr match {
          case '{ varsApi[t]() } =>
            val typeToInspect = TypeRepr.of[t]
            val foundVars = typeToInspect.typeSymbol.fieldMembers.filter(v => v.typeRef <:< VarInstanceTpe && v.isValDef)
            val newMembers = foundVars flatMap { valDefSym =>
              valDefSym.tree match {
                case ValDef(name, tpe, _) =>
                  // println(s"type ${tpe.show} is extVar ? ${tpe.tpe <:< extVarInstanceTpe}\n  type args: ${tpe.tpe.typeArgs}")
                  val resultType = ModifierVar.appliedTo(
                    if (tpe.tpe <:< ExtVarInstanceTpe) tpe.tpe.typeArgs else List(TypeRepr.of[Element], tpe.tpe.typeArgs.head)
                  )
                  val varName = uncapitalize(valDefSym.name)
                  val varRef = Ref(typeToInspect.typeSymbol.fieldMember(name)) //.asExprOf[Var[Any]]
                  // println(s"Result type ${resultType.show} args: ${resultType.typeArgs}")

                  val modifierValTpe: ValDef = {
                    val modifiedVar = New(TypeTree.ref(resultType.typeSymbol))
                      .select(resultType.typeSymbol.primaryConstructor)
                      .appliedToTypes(resultType.typeArgs)
                      .appliedTo(varRef)
                    ValDef(
                      Symbol.newVal(tree.symbol, varName, resultType, valDefSym.flags, Symbol.noSymbol),
                      Some(modifiedVar)
                    )
                  }

                  val extensionVal: DefDef = { // compute an extension method over the base type
                    // trying to rehydrate the types to use in quotes
                    (Ref(valDefSym).asExpr, Ref(modifierValTpe.symbol).asExpr) match {
                      case ('{ $x: varTpe } & '{ $y: Var[varElemTpe] }, '{ $z: ModifierVar[nodeTpe, _] }) =>
                        val f = varRef.asExprOf[varTpe & Var[varElemTpe]]

                        val mt = MethodType(List("e"))(
                          _ => List(resultType.typeArgs.head),
                          _ => TypeRepr.of[varTpe { type ForInstance = varElemTpe }]
                        )
                        val ms = Symbol.newMethod(tree.symbol, varName, mt, Flags.ExtensionMethod, Symbol.noSymbol)
                        val extensionMethod = DefDef(
                          ms,
                          paramss =>
                            val List(List(rawE)) = paramss
                            val e = rawE.asExprOf[nodeTpe & scala.Singleton]
                            Some('{ $f.forInstance($e) }.asTerm)
                        )
                        // val dd = '{ extension (e: nodeTpe) def synth = $f.forInstance(e) }
                        // var extensionMethod = new TreeAccumulator[DefDef | Null] {
                        //   override def foldTree(x: DefDef | Null, tree: Tree)(owner: Symbol): DefDef | Null = tree match {
                        //     case d: DefDef => d
                        //     case _ => foldOverTree(x, tree)(owner)
                        //   }
                        // }.foldOverTree(null, dd.asTerm)(tree.symbol).asInstanceOf[DefDef]
                        // extensionMethod = DefDef.copy(dd.asTerm)(
                        //   varName,
                        //   extensionMethod.paramss,
                        //   extensionMethod.returnTpt,
                        //   extensionMethod.rhs
                        // )
                        // // println(extensionMethod.symbol)
                        // extensionMethod
                        extensionMethod
                    }
                  }

                  modifierValTpe :: extensionVal :: Nil
              }
            }

            println(s"""|flags = ${tree.symbol.flags.show}
                        |module flags: ${tree.symbol.moduleClass.flags.show}
                        |companion class flags: ${tree.symbol.companionClass.flags.show}
                        |""".stripMargin)

            val newClassSym = Symbol.newModule(
              Symbol.spliceOwner,
              name,
              tree.symbol.flags,
              tree.symbol.companionClass.flags,
              parents.map(_.symbol.typeRef),
              newClassSym => {
                val newCtr = ctr.changeOwner(newClassSym)
                println(s"ncs = $newClassSym")
                println(s"newctr sym = ${newCtr.symbol}")
                println(s"newctr sym owner = ${newCtr.symbol.owner}")
                assert(newCtr.symbol.owner == newClassSym)
                body.foreach(_.changeOwner(newClassSym))
                newMembers.foreach(_.changeOwner(newClassSym))
                ctr.symbol :: body.map(_.symbol) ::: newMembers.map(_.symbol)
              },
              tree.symbol.privateWithin.map(_.typeSymbol).getOrElse(Symbol.noSymbol)
            )
            val res = ClassDef(newClassSym, parents, ctr :: body ::: newMembers)

            // val res = ClassDef.copy(tree)(name, ctr, parents, self, body ++ newMembers)
            // report.info(res.show)
            println(res.symbol.fieldMembers.mkString("\n"))
            List(res)
        }
      case _ =>
        report.error("varsApi only supports class/object/trait definitions")
        List(tree)
    }
  }

  // private def computeModifier(using Quotes)(valDefSym: quotes.reflect.Symbol, name: String, tpe: quotes.reflect.TypeTree): quotes.reflect.ValDef =

  private def uncapitalize(s: String): String = s(0).toLower +: s.tail
  private object & {
    def unapply[T](t: T): (T, T) = (t, t)
  }
}
