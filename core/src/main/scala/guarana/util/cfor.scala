package guarana.util

import scala.util.boundary

inline def cfor[T](inline init: T, inline cond: T => Boolean)(inline action: boundary.Label[Unit] ?=> T => T): Unit = {
  var i = init
  boundary {
    while (cond(i)) i = action(i)
  }
}