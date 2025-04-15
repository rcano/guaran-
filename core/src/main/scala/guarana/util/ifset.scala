package guarana.util

/** Type alias to model optionally specified parameters (this is different from just Option) */
type Opt[A] = A | UnsetParam.type

/** Marks a parameter as unset */
object UnsetParam
inline def ifSet[A, R](v: Opt[A], f: A => R): Option[R] = v match {
  case UnsetParam => None
  case a: A => Some(f(a))
}

extension [A](v: Opt[A]) {
  inline def getOrElse[U >: A](inline u: U) = v match {
    case UnsetParam => u
    case a: A @unchecked => a
  }

  inline def get: A = v.asInstanceOf[A]
}
