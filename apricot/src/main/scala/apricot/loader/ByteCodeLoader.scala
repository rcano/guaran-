package apricot
package loader

import java.lang.reflect.Constructor
import java.net.URL
import java.security.SecureClassLoader
import scala.reflect.ClassTag

/** Base type for loaders that read bytecode in a contained classloader.
  */
trait ByteCodeLoader[Type <: Resource.Type] extends ResourceLoader[Type] {
  protected class LoaderClassLoader extends SecureClassLoader {
    def defineClass(bytes: Array[Byte]): Class[?] = defineClass(null, bytes, 0, bytes.length).asInstanceOf[Class[?]]
  }

  protected def loadInstance[T](resource: Resource, bytes: Array[Byte])(using ct: ClassTag[T]): T = {
    scribe.debug(s"loading class for $resource")
    val cls = LoaderClassLoader().defineClass(bytes)
    scribe.debug(s"class $resource defined")
    if !ct.runtimeClass.isAssignableFrom(cls) then throw new IllegalArgumentException(s"Resource $resource is of type ${cls.getName} which is not a $ct")
    cls.getConstructor() match {
      case null => throw new IllegalArgumentException(s"Resource $resource does not define a class with a no-args main constructor")
      case ctor: Constructor[T @unchecked] => ctor.newInstance().asInstanceOf[T]
    }
  }
  protected def unloadInstance(resource: Resource, instance: AnyRef): Unit = System.gc()
}
