package guarana.swing
package util

import java.awt.BorderLayout
import javax.swing.{JPanel, Scrollable, SwingConstants}

enum Scrollable {
  case Horizontal, Vertical

  def apply(
    n: Node,
    horizontalScrollUnit: Opt[Double] = UnsetParam,
    verticalScrollUnit: Opt[Double] = UnsetParam,
    horizontalScrollBlock: Opt[Double] = UnsetParam,
    verticalScrollBlock: Opt[Double] = UnsetParam,
  )(using sc: Scenegraph): Node = {
    val scrollable = new JPanel(BorderLayout()) with javax.swing.Scrollable {
      def getPreferredScrollableViewportSize() = getPreferredSize
      def getScrollableBlockIncrement(rect: java.awt.Rectangle | UncheckedNull, orientation: Int, direction: Int): Int = {
        val em = sc.stateReader(sc.emSize)
        orientation match {
          case SwingConstants.HORIZONTAL => horizontalScrollBlock.getOrElse(em * 10).toInt
          case SwingConstants.VERTICAL => verticalScrollBlock.getOrElse(em * 4).toInt
        }
      }
      def getScrollableTracksViewportHeight(): Boolean = Scrollable.this == Scrollable.Horizontal
      def getScrollableTracksViewportWidth(): Boolean = Scrollable.this == Scrollable.Vertical
      def getScrollableUnitIncrement(rect: java.awt.Rectangle | UncheckedNull, orientation: Int, direction: Int): Int = {
        val em = sc.stateReader(sc.emSize)
        orientation match {
          case SwingConstants.HORIZONTAL => horizontalScrollUnit.getOrElse(em * 4).toInt
          case SwingConstants.VERTICAL => verticalScrollUnit.getOrElse(em).toInt
        }
      }
    }
    scrollable.add(n.unwrap, BorderLayout.CENTER)
    Node.wrap(scrollable)
  }

}