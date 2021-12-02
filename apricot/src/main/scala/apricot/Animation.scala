package apricot

import apricot.DynamicScript
import guarana.*
import guarana.animation.{Script, ScriptEngine}
import io.github.humbleui.skija.Image

trait Animated {
  private var _currentAnimation: Animation | Null = null
  def currentAnimation: Animation | Null = _currentAnimation
  def currentAnimation_=(a: Animation | Null)(using se: ScriptEngine): Unit = {
    _currentAnimation.?(a => se.remove(a.script))
    _currentAnimation = a
    _currentAnimation.?(a => se.run(a.script))
  }
}

class Animation(val frames: IArray[Image], val dynScript: DynamicScript) {
  var currentFrame: Int = 0
  val script = dynScript.bind(Map("animation" -> this))
}
