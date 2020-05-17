package guarana.swing
package impl

class Spoopy()

class MyTestKey(val name: String)
class Instance(name: String) {
  override def toString = s"Instance($name)"
}

object KeyedWeakHashMapTest {
  def main(args: Array[String]): Unit = {
    val key = "some key"
    val m = new KeyedWeakHashMap[MyTestKey, Seq[Instance]]()
    
    m(Keyed(MyTestKey("caca"), Spoopy())) = Seq(Instance(""))
    assert(m.size == 1)
    System.gc()
    Thread.sleep(100)
    assert(m.size == 0)

    /* a more complex scenario with transitive dependencies */

    new Runnable {
      // must do this in a separate method so that the keys can be GCd after it is over
      def run = {
        val k1 = Instance("k1")
        val k2 = Instance("k2")
        m(Keyed(MyTestKey("k1"), k1)) = Seq.empty
        m(Keyed(MyTestKey("k2"), k2)) = Seq(k1)
        m(Keyed(MyTestKey("k3"), Instance("res"))) = Seq(k2)
      }
    }.run
    assert(m.size == 3)
    System.gc()
    Thread.sleep(100)
    println(m.size)
    while (true) {
      System.gc()
      Thread.sleep(2000)
      println(m.size)
    }

  }
}