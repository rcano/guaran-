package guarana.swing
package table

import language.implicitConversions

import javax.swing.table.{AbstractTableModel, DefaultTableColumnModel, TableColumn}
import scala.util.chaining._

abstract class TableData[T <: Product](val prodFields: util.ProductFields[T]) extends collection.IndexedSeq[T] {

  object tableModel extends AbstractTableModel {
    override def getColumnName(col: Int) = prodFields.fields(col)._1
    override def getColumnClass(col: Int) = prodFields.fields(col)._2.runtimeClass match {
      case java.lang.Boolean.TYPE => classOf[java.lang.Boolean]
      case other => other
    }
    override def getRowCount = size
    override def getColumnCount: Int = prodFields.fields.size
    override def getValueAt(r: Int, c: Int) = apply(r).productElement(c).asInstanceOf
  }

  object columnModel extends DefaultTableColumnModel {
    prodFields.fields.zipWithIndex foreach { case ((label, tpe), idx) =>
      addColumn(TableColumn(idx).tap { c =>
        c.setHeaderValue(label)
      })
    }
  }
}

package immutable {
  class TableData[T <: Product](val elements: IndexedSeq[T])(
    given productFields: util.ProductFields[T]
  ) extends guarana.swing.table.TableData[T](productFields) {
    def apply(i: Int) = elements(i)
    def length = elements.length
  }
}
  
object Test {

  // util.ProductLenses.productLenses[(1,2,3)]
}