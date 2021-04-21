package guarana

class TestToolkit extends Toolkit {
  protected def isOnToolkitThread(): Boolean = true
  protected def runOnToolkitThread(r: () => Any): Unit = r()
  protected def systemEm: Double = 14
}
