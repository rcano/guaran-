package guarana
package web

import org.scalajs.dom

object child {
  private val ChildVar = Var[Option[HtmlElement]]("child", None, eagerEvaluation = true)
  def :=(b: Binding[HtmlElement]): Modifier[HtmlElement] = Modifier { base =>
    val v = ChildVar.forInstance(base)
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
    private val ChildTextVar = Var[String]("child.text", "", eagerEvaluation = true)
    def :=(b: Binding[String]): Modifier[HtmlElement] = Modifier { base =>
      val v = ChildTextVar.forInstance(base)
      val tn = dom.document.createTextNode("")
      base.unwrap.append(tn)
      v := Binding.dyn {
        val newv = b()
        tn.textContent = newv
        newv
      }
    }
  }
}

object addClass {
  private val AddClassVar = Var[String]("addClass", "", eagerEvaluation = true)
  def :=(b: Binding[String]): Modifier[HtmlElement] = Modifier { base =>
    val v = AddClassVar.forInstance(base)
    v := Binding.dyn {
      val prev = v()
      val newv = b()
      if (prev.nonEmpty) base.classList.remove(prev)
      if (newv.nonEmpty) base.classList.add(newv)
      newv
    }
  }
}
object addClasses {
  private val AddClassesVar = Var[Seq[String]]("addClasses", Nil, eagerEvaluation = true)
  def :=(b: Binding[Seq[String]]): Modifier[HtmlElement] = Modifier { base =>
    val v = AddClassesVar.forInstance(base)
    v := Binding.dyn {
      val prev = v()
      val newv = b()
      if (prev.nonEmpty) prev.foreach(base.classList.remove)
      if (newv.nonEmpty) newv.foreach(base.classList.add)
      newv
    }
  }
}
