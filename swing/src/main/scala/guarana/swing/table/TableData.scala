package guarana
package swing
package table

import language.implicitConversions

import javax.swing.table.{AbstractTableModel, DefaultTableColumnModel, TableColumn}
import scala.util.chaining.*

abstract class TableData[T <: Product](val prodFields: guarana.util.ProductFields[T]) extends collection.IndexedSeq[T] {

  class TableModel extends AbstractTableModel {
    override def getColumnName(col: Int) = prodFields.fields(col)._1
    override def getColumnClass(col: Int) = prodFields.fields(col)._2.runtimeClass match {
      case java.lang.Boolean.TYPE => classOf[java.lang.Boolean]
      case other => other
    }
    override def isCellEditable(row: Int, col: Int) = classOf[java.awt.Component].isAssignableFrom(prodFields.fields(col)._2.runtimeClass)
    override def getRowCount = size
    override def getColumnCount: Int = prodFields.fields.size
    override def getValueAt(r: Int, c: Int) = apply(r).productElement(c).asInstanceOf
  }
  object defaultTableModel extends TableModel

  object columnModel extends DefaultTableColumnModel {
    prodFields.fields.zipWithIndex foreach { case ((label, tpe), idx) =>
      addColumn(TableColumn(idx).tap { c =>
        c.setHeaderValue(label)
        if classOf[java.awt.Component].isAssignableFrom(tpe.runtimeClass) then
          c.setCellRenderer(TableData.ComponentCellRenderer)
          c.setCellEditor(TableData.ComponentCellRenderer)
      })
    }
  }
}
object TableData {
  private[TableData] object ComponentCellRenderer extends 
    javax.swing.AbstractCellEditor, javax.swing.table.TableCellRenderer, javax.swing.table.TableCellEditor {
    def getTableCellRendererComponent(table: javax.swing.JTable, value: AnyRef, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int) =
      value.asInstanceOf[java.awt.Component]

    var lastEditedValue: Any = null
    def getTableCellEditorComponent(table: javax.swing.JTable, value: AnyRef, isSelected: Boolean, row: Int, column: Int) =
      lastEditedValue = value
      value.asInstanceOf[java.awt.Component]

    def getCellEditorValue = lastEditedValue
  }
}

package immutable {
  class TableData[T <: Product](val elements: IndexedSeq[T])(using productFields: guarana.util.ProductFields[T])
      extends guarana.swing.table.TableData[T](productFields) {
    def apply(i: Int) = elements(i)
    def length = elements.length
  }
}

package mutable {
  class TableData[T <: Product](val elements: ObsBuffer[T])(using productFields: guarana.util.ProductFields[T])
  extends guarana.swing.table.TableData[T](productFields) {
    def apply(i: Int) = elements(i)
    def length = elements.length

    /** Install the elements observer into the target abstract model, which will be used for notifying the UI when the ObsBuffer changes.
      * This method doesn't assume that you use the defaultTableModel and so you must manually call it.
      */
    def installElementsObserver(tableModel: AbstractTableModel): Unit = {
      elements.observers += {
        case ObsBuffer.Event.Added(elems) => tableModel.fireTableRowsInserted(elements.length - elems.length, elems.length)
        case ObsBuffer.Event.Inserted(elems, at) => tableModel.fireTableRowsInserted(at, at + elems.length)
        case _ => tableModel.fireTableDataChanged()
      }
    }
  }
}