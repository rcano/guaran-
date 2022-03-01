package apricot
package loader

import apricot.resource.*
import io.github.humbleui.skija.Image
import guarana.animation.ScriptEngine

trait ResourceLoader[Type <: Resource.Type]:
  type Out
  def load(resource: Resource, content: Resource.Content): Out
  def unload(resource: Resource, content: Out): Unit

object ResourceLoader {
  type Aux[Type <: Resource.Type, O] = ResourceLoader[Type] { type Out = O }

  def of[Type <: Resource.Type](using r: ResourceLoader[Type]): r.type = r

  given ResourceLoader[Resource.Type.Text] with
    type Out = String
    def load(r: Resource, content: Resource.Content) = new String(content(0).content.asInstanceOf[Array[Byte]], "utf-8")
    def unload(r: Resource, s: String) = ()


  given ResourceLoader[Resource.Type.Image] with
    type Out = IArray[Image]
    def load(r: Resource, content: Resource.Content) = 
      content.map(part => Image.makeFromEncoded(part.content.asInstanceOf[Array[Byte]]))
    def unload(r: Resource, i: Out) = i.foreach(_.close())

  given (ResourceLoader[Resource.Type.DynamicScript] { type Out = DynamicScript }) = new ByteCodeLoader[Resource.Type.DynamicScript]:
    type Out = DynamicScript
    def load(r: Resource, content: Resource.Content) = loadInstance[DynamicScript](r, content(0).content.asInstanceOf[Array[Byte]])
    def unload(r: Resource, animation: DynamicScript) = unloadInstance(r, animation)

  given (ResourceLoader[Resource.Type.Renderable] { type Out = DynamicRenderable }) = new ByteCodeLoader[Resource.Type.Renderable]:
    type Out = DynamicRenderable
    def load(r: Resource, content: Resource.Content) = loadInstance[DynamicRenderable](r, content(0).content.asInstanceOf[Array[Byte]])
    def unload(r: Resource, animation: DynamicRenderable) = ()

  given (using ScriptEngine): ResourceLoader.Aux[Resource.Type.Animation, Animation] = AnimationLoader()
}
