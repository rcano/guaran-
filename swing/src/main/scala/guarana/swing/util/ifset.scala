package guarana.swing.util

type Opt[A] = A | UnsetParam.type

/** Marks a parameter as unset*/
object UnsetParam
inline def ifSet[A, R](v: Opt[A], f: A => R) = v match {
  case UnsetParam => None
  case a: A => Some(f(a))
}

extension [A, U >: A](v: Opt[A]) def getOrElse(u: => U) = v match {
  case UnsetParam => u
  case a: A => a
} 
