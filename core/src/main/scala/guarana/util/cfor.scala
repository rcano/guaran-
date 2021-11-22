package guarana.util

inline def cfor[T](inline init: T, inline cond: T => Boolean)(inline action: T => T): Unit = {
  var i = init
  while (cond(i)) i = action(i)
}