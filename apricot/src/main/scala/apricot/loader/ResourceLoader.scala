package apricot
package loader

import io.github.humbleui.skija.Image

trait ResourceLoader[Type <: Resource.Type]:
  type Out
  def load(resource: Resource, bytes: Array[Byte]): Out
  def unload(resource: Resource, content: Out): Unit

object ResourceLoader {
  type Aux[Type <: Resource.Type, O] = ResourceLoader[Type] { type Out = O }


  given ResourceLoader[Resource.Type.Text] with
    type Out = String
    def load(r: Resource, bytes: Array[Byte]) = new String(bytes, "utf-8")
    def unload(r: Resource, s: String) = ()


  given ResourceLoader[Resource.Type.Image] with
    type Out = Image  
    def load(r: Resource, bytes: Array[Byte]) = Image.makeFromEncoded(bytes)
    def unload(r: Resource, i: Image) = i.close()

  given (ResourceLoader[Resource.Type.DynamicScript] { type Out = DynamicScript }) = new ByteCodeLoader[Resource.Type.DynamicScript]:
    type Out = DynamicScript
    def load(r: Resource, bytes: Array[Byte]) = loadInstance[DynamicScript](r, bytes)
    def unload(r: Resource, animation: DynamicScript) = unloadInstance(r, animation)
}