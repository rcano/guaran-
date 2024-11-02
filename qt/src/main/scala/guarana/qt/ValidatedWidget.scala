package guarana
package qt

import Binding.dyn

class ValidatedWidget[W <: Widget, T](val widget: W)(using vs: ValidatedWidget.ValidationSupport[W, T])(validate: T => Option[String])(using
    VarContext
) {
  def validatedValue = ValidatedWidget.ValidatedValue.asInstanceOf[ObsVal.Aux[String Either T, widget.type]]
  def validValue = ValidatedWidget.ValidValue.asInstanceOf[ObsVal.Aux[Option[T], widget.type]]

  {
    val valueObsVal = vs.valueObsVal(widget)
    ValidatedWidget.ValidatedValue.forInstance(widget) := dyn {
      val value = valueObsVal()
      validate(value) match {
        case None =>
          vs.clearError(widget)
          Right(value)
        case Some(value) =>
          vs.displayError(widget, value)
          Left(value)
      }
    }
    ValidatedWidget.ValidValue.forInstance(widget) := dyn { validatedValue().toOption }
  }
}

object ValidatedWidget {
  val ValidatedValue = Var.autoName[String Either Any](Left("no value"))
  val ValidValue = Var.autoName[Option[Any]](None)

  trait ValidationSupport[W <: Widget, T] {
    def valueObsVal(w: W): ObsVal.Aux[T, w.type]
    def displayError(w: W, error: String)(using VarContext): Unit
    def clearError(w: W)(using VarContext): Unit
  }

  given ValidationSupport[TextField, String | Null] with {
    override def valueObsVal(w: TextField): ObsVal.Aux[String | Null, w.type] = w.text.asObsValIn(w)
    override def displayError(w: TextField, error: String)(using VarContext): Unit = {
      w.toolTip := error
      w.styleSheet := "background: #ffb4b5"
    }
    override def clearError(w: TextField)(using VarContext): Unit = {
      w.toolTip := Binding.Null
      w.styleSheet := Binding.Null
    }
  }
}
