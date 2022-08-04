package apricot

import apricot.graphics.GraphicsStack
import guarana.*
import guarana.animation.{Script, ScriptEngine}
import resource.DynamicScript

trait Animated {
  private var _currentAnimation: Animation | Null = null
  def currentAnimation: Animation | Null = _currentAnimation
  def currentAnimation_=(a: Animation | Null)(using se: ScriptEngine): Unit = {
    _currentAnimation.?(a => se.remove(a.script))
    _currentAnimation = a
    _currentAnimation.?(a => se.run(a.script))
  }
}

class Animation(val graphicsStack: GraphicsStack, val frames: IArray[graphicsStack.Image], val dynScript: DynamicScript) {
  var currentFrame: Int = 0
  val script = dynScript.bind(Map("animation" -> this))
}
