package guarana

class TestToolkit extends AbstractToolkit {
  protected def isOnToolkitThread(): Boolean = true
  protected def runOnToolkitThread(r: () => Any): Unit = r()
}
