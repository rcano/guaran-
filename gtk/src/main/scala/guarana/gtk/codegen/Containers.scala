package guarana.gtk.codegen

import guarana.codegen.*

trait Containers { self: run.type =>
  lazy val BoxNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.Box"), "Box", Some(WidgetNode))
    .addProperty(VarProp("nodes", "Seq[Widget]", "Seq.empty", eagerEvaluation = true))
    .addInitExtra(
      Seq(
        "Toolkit.update {",
        "  v.varUpdates := EventIterator.forsome {",
        "    case v.nodes(_, newv) =>",
        "      while (v.getFirstChild() != null) v.remove(v.getFirstChild())",
        "      newv.foreach(w => v.append(w.unwrap))",
        "  }",
        "}"
      )
    )

  lazy val FlowBoxNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.FlowBox"), "FlowBox", Some(WidgetNode))
    .addProperty(VarProp("nodes", "Seq[Widget]", "Seq.empty", eagerEvaluation = true))
    .addInitExtra(
      Seq(
        "Toolkit.update {",
        "  v.varUpdates := EventIterator.forsome {",
        "    case v.nodes(_, newv) =>",
        "      while (v.getFirstChild() != null) v.remove(v.getFirstChild())",
        "      newv.foreach(w => v.append(w.unwrap))",
        "  }",
        "}"
      )
    )

  lazy val GridNode = genNodeDescr(classIndex.scanResult.getClassInfo("org.gnome.gtk.Grid"), "Grid", Some(WidgetNode))
    .copy(companionObjectExtends = Some("VarsMap, internal.GridLayoutSupport"))
    .addInitExtra(Seq("Toolkit.update(initNodesVar(v))"))

  lazy val AllContainers = BoxNode :: FlowBoxNode :: GridNode :: Nil
}
