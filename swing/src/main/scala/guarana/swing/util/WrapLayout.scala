package guarana.swing.util

import java.awt.*
import javax.swing.{JScrollPane, SwingUtilities}

/** FlowLayout subclass that fully supports wrapping of components.
  *
  * @constructor
  *   Creates a new flow layout manager with the indicated alignment and the indicated horizontal and vertical gaps. <p> The value of the
  *   alignment argument must be one of <code>WrapLayout</code>, <code>WrapLayout</code>, or <code>WrapLayout</code>.
  * @param align
  *   the alignment value
  * @param hgap
  *   the horizontal gap between components
  * @param vgap
  *   the vertical gap between components
  */
class WrapLayout(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5) extends FlowLayout(align, hgap, vgap) {

  /** Returns the preferred dimensions for this layout given the <i>visible</i> components in the specified target container.
    * @param target
    *   the component which needs to be laid out
    * @return
    *   the preferred dimensions to lay out the subcomponents of the specified container
    */
  override def preferredLayoutSize(target: Container): Dimension =
    layoutSize(target, preferred = true)

  /** Returns the minimum dimensions needed to layout the <i>visible</i> components contained in the specified target container.
    * @param target
    *   the component which needs to be laid out
    * @return
    *   the minimum dimensions to lay out the subcomponents of the specified container
    */
  override def minimumLayoutSize(target: Container): Dimension = {
    val minimum = layoutSize(target, preferred = false)
    minimum.width -= (getHgap() + 1)
    minimum
  }

  /** Returns the minimum or preferred dimension needed to layout the target container.
    *
    * @param target
    *   target to get layout size for
    * @param preferred
    *   should preferred size be calculated
    * @return
    *   the dimension to layout the target container
    */
  private def layoutSize(target: Container, preferred: Boolean): Dimension = {
    target.getTreeLock.synchronized {
      var targetWidth = target.getSize.width
      var container = target

      while (container.getSize.width == 0 && container.getParent != null) {
        container = container.getParent
      }

      targetWidth = container.getSize.width

      if (targetWidth == 0)
        targetWidth = Int.MaxValue

      val hgap = getHgap()
      val vgap = getVgap()
      val insets = target.getInsets
      val horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2)
      val maxWidth = targetWidth - horizontalInsetsAndGap

      val dim = new Dimension(0, 0)
      var rowWidth = 0
      var rowHeight = 0

      val nmembers = target.getComponentCount

      for (i <- 0 until nmembers) {
        val m = target.getComponent(i)

        if (m.isVisible) {
          val d = if (preferred) m.getPreferredSize else m.getMinimumSize

          if (rowWidth + d.width > maxWidth) {
            addRow(dim, rowWidth, rowHeight)
            rowWidth = 0
            rowHeight = 0
          }

          if (rowWidth != 0) {
            rowWidth += hgap
          }

          rowWidth += d.width
          rowHeight = Math.max(rowHeight, d.height)
        }
      }

      addRow(dim, rowWidth, rowHeight)

      dim.width += horizontalInsetsAndGap
      dim.height += insets.top + insets.bottom + vgap * 2

      val scrollPane = SwingUtilities.getAncestorOfClass(classOf[JScrollPane], target)

      if (scrollPane != null && target.isValid) {
        dim.width -= (hgap + 1)
      }

      dim
    }
  }

  /** A new row has been completed. Use the dimensions of this row to update the preferred size for the container.
    *
    * @param dim
    *   update the width and height when appropriate
    * @param rowWidth
    *   the width of the row to add
    * @param rowHeight
    *   the height of the row to add
    */
  private def addRow(dim: Dimension, rowWidth: Int, rowHeight: Int): Unit = {
    dim.width = Math.max(dim.width, rowWidth)

    if (dim.height > 0) {
      dim.height += getVgap()
    }

    dim.height += rowHeight
  }
}
