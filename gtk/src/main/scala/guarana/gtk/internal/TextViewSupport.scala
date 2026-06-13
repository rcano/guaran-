package guarana
package gtk
package internal

import scala.util.Using
import java.lang.foreign.Arena
import org.gnome.gtk.TextIter

trait TextViewSupport { self: TextView.type =>
  val RichText: Var[Seq[String | Widget]] = Var(
    "richText",
    Seq.empty,
    eagerEvaluation = true,
    onFirstAssociation = {
      case tv: TextView =>
        impl.Debug.elidable { scribe.debug(s"installing rich-text listener on $tv") }
        Toolkit.update {
          tv.varUpdates := EventIterator.forsome {
            case tv.richText(_, nodes) => Using(Arena.ofConfined()) { arena =>
              val from = TextIter(arena)
              val to = TextIter(arena)
              val buffer = tv.unwrap.getBuffer()
              buffer.getStartIter(from)
              buffer.getEndIter(to)
              buffer.delete(from, to)

              val ti = TextIter(arena)
              nodes.foreach { elem =>
                buffer.getEndIter(ti)
                elem match {
                  case t: String => buffer.insertMarkup(ti, t, t.length())
                  case widget =>
                    val anchor = buffer.createChildAnchor(ti)
                    tv.unwrap.addChildAtAnchor(widget.asInstanceOf[Widget].unwrap, anchor)
                }
              }
            }.get
          }
        }
      case _ =>
    }
  )

  extension (v: TextView) def richText = RichText.forInstance(v)
}
