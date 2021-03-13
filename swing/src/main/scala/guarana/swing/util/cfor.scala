package guarana.swing.util

inline def cfor[T](inline init: T, inline cond: T => Boolean)(action: T => T): Unit = {
  var i = init
  while (cond(i)) i = action(i)
}