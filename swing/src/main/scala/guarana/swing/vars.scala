package guarana
package swing

trait SwingVar[T] extends ExternalVar[T]
object SwingVar {
  type Aux[N, T] = SwingVar[T] {
    type ForInstance <: N & Singleton
  }
  def apply[N, T](varName: => String, getter: N => T, setter: (N, T) => Unit): Aux[N, T] = {
    new SwingVar[T] {
      lazy val name = varName
      type ForInstance <: N & Singleton
      def get(n: ForInstance) = getter(n)
      def set(n: ForInstance, t: T) = setter(n, t)
      def eagerEvaluation = true
    }
  }
}
