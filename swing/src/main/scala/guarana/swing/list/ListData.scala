package guarana
package swing
package list

import javax.swing.AbstractListModel

class ListData[T](items: ObsBuffer[T]) extends AbstractListModel[T] {
  def getElementAt(at: Int) = items(at)
  def getSize(): Int = items.length

  items.observers += {
    case ObsBuffer.Event.Added(elems) => fireIntervalAdded(this, items.length - elems.length, items.length)
    case ObsBuffer.Event.Inserted(elems, at) => fireIntervalAdded(this, at, at + elems.length)
    case ObsBuffer.Event.Removed(elems, at) => fireIntervalRemoved(this, at, at + elems.length)
    case ObsBuffer.Event.Replaced(oldv, newv, at) => fireContentsChanged(this, at, at)
    case ObsBuffer.Event.Cleared(elems) => fireIntervalRemoved(this, 0, elems)
  }
}
