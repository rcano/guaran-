package guarana.qt

import io.qt.gui.QIcon

final case class Tab(widget: Widget, label: String, icon: Option[QIcon] = None)
