package apricot
package loader

import guarana.unn
import guarana.animation.ScriptEngine

class AnimationLoader()(using se: ScriptEngine) extends ResourceLoader[Resource.Type.Animation] {
  type Out = Animation
  def load(resource: Resource, content: Resource.Content): Out = {
    var scriptPart: Resource.ResourcePart | Null = null
    val frames = new collection.mutable.ArrayBuffer[Resource.ResourcePart](content.length - 1)
    content.foreach { part =>
      part.name match
        case "script" => scriptPart = part
        case _ => frames += part
    }
    if scriptPart == null then throw new IllegalStateException("Animation has no defined script")

    val dynScript = ResourceLoader.of[Resource.Type.DynamicScript].load(resource, IArray(scriptPart.unn))
    val images = ResourceLoader.of[Resource.Type.Image].load(resource, frames.toArray.asInstanceOf[IArray[Resource.ResourcePart]])
    Animation(images, dynScript)
  }
  def unload(resource: Resource, content: Out): Unit =
    se.remove(content.script)
    ResourceLoader.of[Resource.Type.DynamicScript].unload(resource, content.dynScript)
    ResourceLoader.of[Resource.Type.Image].unload(resource, content.frames)
}
