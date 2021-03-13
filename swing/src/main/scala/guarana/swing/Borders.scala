package guarana.swing

import javax.swing.{BorderFactory}
import javax.swing.border.Border

object Borders {
  def empty(top: Double = 0, right: Double = 0, bottom: Double = 0, left: Double = 0): Border = 
    BorderFactory.createEmptyBorder(top.toInt, left.toInt, bottom.toInt, right.toInt)
  
  def empty(allSides: Double): Border =
    BorderFactory.createEmptyBorder(allSides.toInt, allSides.toInt, allSides.toInt, allSides.toInt)

  export BorderFactory.{empty => _, createTitledBorder => titled, *}
}
