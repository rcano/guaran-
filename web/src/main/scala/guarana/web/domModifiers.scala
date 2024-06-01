package guarana
package web

import org.scalajs.dom

object child {
  def :=(b: Binding[HtmlElement]): Modifier[HtmlElement] = Modifier { base =>
    val v = Var[Option[HtmlElement]]("child", None, eagerEvaluation = true).forInstance(base)
    val baseWr = guarana.impl.WeakRefFactory(base)
    v := Binding.dyn {
      val prev = v()
      val newv = b()
      prev match
        case None => base.append(newv.unwrap)
        case Some(value) => base.replaceChild(newv, value)

      Some(newv)
    }
  }

  object text {
    def :=(b: Binding[String]): Modifier[HtmlElement] = Modifier { base =>
      val v = Var[String]("child.text", "", eagerEvaluation = true).forInstance(base)
      val tn = dom.document.createTextNode("")
      base.unwrap.append(tn)
      v := Binding.dynDebug {
        val newv = b()
        tn.textContent = newv
        newv
      }
    }
  }
}
