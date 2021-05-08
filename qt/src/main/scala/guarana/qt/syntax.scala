package guarana.qt

/** Instantiate a Seq[Widget | LayoutItem].
  * 
  * This function helps with the fact that by default Scala will not infer the type
  * `Widget | LayoutItem` if you used raw Seq(...)
  */
inline def Nodes(nodes: Widget | LayoutItem*): Seq[Widget | LayoutItem] = nodes
