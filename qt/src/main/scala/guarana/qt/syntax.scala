package guarana
package qt

import io.qt.core.QMargins

/** Instantiate a Seq[Widget | LayoutItem].
  * 
  * This function helps with the fact that by default Scala will not infer the type
  * `Widget | LayoutItem` if you used raw Seq(...)
  */
inline def Nodes(nodes: Widget | LayoutItem*): Seq[Widget | LayoutItem] = nodes

extension (i: Insets) def toQMargins = QMargins(i.left.round.toInt, i.top.round.toInt, i.right.round.toInt, i.bot.round.toInt)
extension (m: QMargins) def toInsets = Insets(m.top, m.right, m.bottom, m.left)

trait WidgetSupplier[-T] {
  def widget(t: T): Widget
}
object WidgetSupplier {
  given WidgetSupplier[Widget] with
    def widget(w: Widget) = w
}