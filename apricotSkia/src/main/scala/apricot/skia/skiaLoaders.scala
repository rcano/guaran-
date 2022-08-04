package apricot
package skia

import apricot.loader.ResourceLoader
import io.github.humbleui.skija.Image

given ResourceLoader[Resource.Type.Image] with
  type Out = IArray[Image]
  def load(r: Resource, content: Resource.Content) =
    content.map(part => Image.makeFromEncoded(part.content.asInstanceOf[Array[Byte]]))
  def unload(r: Resource, i: Out) = i.foreach(_.close())
