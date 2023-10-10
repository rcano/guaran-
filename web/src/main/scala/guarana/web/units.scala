package guarana
package web

opaque type StylableSize >: String = String

object StylableSize {
  extension (s: StylableSize) inline def unwrap: String = s
}

extension (d: Double) {
  // absolute
  def cm: StylableSize = s"${d}cm"
  def mm: StylableSize = s"${d}mm"
  def px: StylableSize = s"${d}px"
  def pt: StylableSize = s"${d}pt"
  def pc: StylableSize = s"${d}pc"

  // relative
  def em: StylableSize = s"${d}em"
  def ex: StylableSize = s"${d}ex"
  def ch: StylableSize = s"${d}ch"
  def rem: StylableSize = s"${d}rem"
  def vw: StylableSize = s"${d}vw"
  def vh: StylableSize = s"${d}vh"
  def vmin: StylableSize = s"${d}vmin"
  def vmax: StylableSize = s"${d}vmax"
  def pct: StylableSize = s"${d}pct"
}

given Conversion[(StylableSize, StylableSize), StylableSize] = t => s"${t._1} ${t._2}"
given Conversion[(StylableSize, StylableSize, StylableSize, StylableSize), StylableSize] = t => s"${t._1} ${t._2} ${t._3} ${t._4}"