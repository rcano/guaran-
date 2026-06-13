package guarana
package gtk
package internal

transparent trait NotebookTabsSupport { self: Notebook.type =>
  case class Tab(title: String | Widget, content: Widget, menuLabel: Option[Widget] = None, reorderable: Boolean = false, detachable: Boolean = false)

  val Tabs: Var[Seq[Tab]] = Var("nodes", Seq.empty, eagerEvaluation = true)

  extension (v: Notebook) {
    def tabs = Tabs.forInstance(v)
    private def _unwrap = v.unwrap

    export _unwrap.{nextPage, prevPage}
  }

  protected def initVars(v: Notebook): VarContextAction[Unit] = {
    v.varUpdates := EventIterator.forsome {
      case Tabs.generic(_, _, newv) => 
        // remove existing tabs
        val peer = v.unwrap
        (0 until peer.getNPages()).foreach(_ => peer.removePage(0))

        newv.foreach { tab => 
          val title = tab.title match {
            case s: String => Label(text = s)
            case w => w.asInstanceOf[Widget]
          }
          peer.appendPageMenu(tab.content.unwrap, title.unwrap, tab.menuLabel.map(_.unwrap).getOrElse(null))
          peer.setTabReorderable(tab.content.unwrap, tab.reorderable)
          peer.setTabDetachable(tab.content.unwrap, tab.detachable)
        }
    }
  }
}
