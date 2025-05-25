package apricot

import guarana.*
import guarana.animation.ScriptEngine
import resource.DynamicScript

trait Animated[Image] {
  private var _currentAnimation: Animation[Image] | Null = null
  def currentAnimation: Animation[Image] | Null = _currentAnimation
  def currentAnimation_=(a: Animation[Image] | Null)(using se: ScriptEngine): Unit = {
    _currentAnimation.?(a => se.remove(a.script))
    _currentAnimation = a
    _currentAnimation.?(a => se.run(a.script))
  }
}

class Animation[Image](val frames: IArray[Image], val dynScript: DynamicScript) {
  var currentFrame: Int = 0
  val script = dynScript.bind(Map("animation" -> this))
}
