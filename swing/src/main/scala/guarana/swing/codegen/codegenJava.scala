package guarana.swing.codegen

def describeJava(n: NodeDescr): (Seq[String], Seq[String], Seq[String]) = {

  val tpeParamsDecls = if (n.tpeParams.nonEmpty) n.tpeParams.mkString("<", ", ", ">") else ""
  val tpeParams = tpeParamsDecls.replaceAll("[+-]", "").nn

  val emptyTpeParams = tpeParams.replaceAll(raw"\w|_ [><]: \w+", "Object")

  def propDecl(p: Property): String = {
    val tpeInStaticPos = p.tpeInStaticPos.replace("| Null", "")
    p.visibility.map(s => s + " ").getOrElse("") + (p match {
    case p @ SwingProp(name = name, getter = getter, setter = setter) =>
      s"""public static ExternalVarDescr<$tpeInStaticPos, ${n.name}$emptyTpeParams> ${name.capitalize} = ExternalVarDescr.create("$name", ${getter.replace("=>", "->")}, ${setter.replace("=>", "->")}, true);"""
    case p @ VarProp(name = name, initValue = initValue, eagerEvaluation = eval) =>
      s"""public static VarDescr<$tpeInStaticPos, ${n.name}$emptyTpeParams> ${name.capitalize} = VarDescr.create("$name", $initValue, $eval);"""
  })
  }


  val decls = n.props.sortBy(_.name).map(propDecl)

  val instanceProps = n.props.sortBy(_.name).map { p =>
    s"""|public default Var<${p.tpeInStaticPos.replace("| Null", "")}, ${n.name}$emptyTpeParams> ${p.name}() {
        |    return ${p.name.capitalize}.forInstanceVar(this);
        |}""".stripMargin
  }

  val modifiers = Seq.empty

  (decls, instanceProps, modifiers)
}

@main def codegenJava(): Unit = {
  val parts = describeJava(Node)
  println(parts._1.mkString("\n"))
  println("\n\n")
  println(parts._2.mkString("\n"))
}
