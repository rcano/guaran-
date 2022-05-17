package guarana.swing
package plaf

import javax.swing.{JLabel, JList, ListCellRenderer}
import javax.swing.plaf.UIResource

object CssDefaultListRenderer {
  val Class = "list-renderer"
}
class CssDefaultListRenderer(scenegraph:  Scenegraph) extends JLabel, ListCellRenderer[Object], UIResource {
  val thisAsLabel = Label.wrap(this)
  scenegraph.update {
    Label.init(thisAsLabel)
    style.CssProperties.Classes.forInstance[thisAsLabel.type] += CssDefaultListRenderer.Class
  }
  def getListCellRendererComponent(
    list: JList[? <: Object],
    value: Object | Null,
    index: Int,
    isSelected: Boolean,
    cellHasFocus: Boolean
  ): java.awt.Component = {
    val listNode = Node.wrap(list)
    setFont(scenegraph.stateReader.getOrDefault(listNode.font))
    scenegraph.update {
      style.CssProperties.ListCellRendererItem.forInstance[thisAsLabel.type] := value
      style.CssProperties.ListCellRendererItemIndex.forInstance[thisAsLabel.type] := index
      style.CssProperties.ListCellRendererSelected.forInstance[thisAsLabel.type] := isSelected
      style.CssProperties.ListCellRendererFocused.forInstance[thisAsLabel.type] := cellHasFocus
    }
    value match
      case i: javax.swing.Icon => setIcon(i)
      case null => setText("")
      case v => setText(v.toString())
    this
  }
}
