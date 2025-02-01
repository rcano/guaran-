package guarana.util

inline def timing[R](inline v: => R)(action: Long => Any): R = {
  val start = System.nanoTime()
  val res = v
  action(System.nanoTime() - start)
  res
}
