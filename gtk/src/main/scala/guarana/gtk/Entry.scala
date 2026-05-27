package guarana
package gtk
opaque type Entry <: Widget = org.gnome.gtk.Entry & Widget
object Entry {
  val ActivatesDefault: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Buffer: ExternalVar.Aux[Entry, org.gnome.gtk.EntryBuffer | Null] = ExternalVar[Entry, org.gnome.gtk.EntryBuffer | Null]("buffer", _.getBuffer(), _.setBuffer(_), true)
  val HasFrame: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val InputHints: ExternalVar.Aux[Entry, java.util.Set[org.gnome.gtk.InputHints] | Null] = ExternalVar[Entry, java.util.Set[org.gnome.gtk.InputHints] | Null]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[Entry, org.gnome.gtk.InputPurpose | Null] = ExternalVar[Entry, org.gnome.gtk.InputPurpose | Null]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val InvisibleChar: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("invisible-char", _.getInvisibleChar(), _.setInvisibleChar(_), true)
  val MaxLength: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("max-length", _.getMaxLength(), _.setMaxLength(_), true)
  val OverwriteMode: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("overwrite-mode", _.getOverwriteMode(), _.setOverwriteMode(_), true)
  val ProgressFraction: ExternalVar.Aux[Entry, Double] = ExternalVar[Entry, Double]("progress-fraction", _.getProgressFraction(), _.setProgressFraction(_), true)
  val ProgressPulseStep: ExternalVar.Aux[Entry, Double] = ExternalVar[Entry, Double]("progress-pulse-step", _.getProgressPulseStep(), _.setProgressPulseStep(_), true)
  val Visibility: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("visibility", _.getVisibility(), _.setVisibility(_), true)
  ()
  extension (v: Entry) {
    def unwrap: org.gnome.gtk.Entry = v
    export unwrap.onActivate, unwrap.onIconPress, unwrap.onIconRelease
  }
  def init(v: Entry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Entry = {
    val res = new org.gnome.gtk.Entry()
    res.asInstanceOf[Entry]
  }
}