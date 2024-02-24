package apricot.vk

import scala.language.unsafeNulls
import org.lwjgl.PointerBuffer
import org.lwjgl.system.{MemoryStack, Struct, StructBuffer}
import org.lwjgl.vulkan.VK10._
import org.lwjgl.vulkan.KHRSwapchain.{VK_SUBOPTIMAL_KHR, VK_ERROR_OUT_OF_DATE_KHR}
import org.lwjgl.vulkan.KHRSurface.{VK_ERROR_SURFACE_LOST_KHR, VK_ERROR_NATIVE_WINDOW_IN_USE_KHR}
import org.lwjgl.vulkan.KHRDisplaySwapchain.VK_ERROR_INCOMPATIBLE_DISPLAY_KHR
import org.lwjgl.vulkan.EXTDebugReport.VK_ERROR_VALIDATION_FAILED_EXT
import scala.compiletime.{codeOf, error, summonFrom}
import scala.jdk.CollectionConverters._

object VkUtil:
  def describeResult(r: Int): String = r match
    case VK_SUCCESS => "Command successfully completed."
    case VK_NOT_READY => "A fence or query has not yet completed."
    case VK_TIMEOUT => "A wait operation has not completed in the specified time."
    case VK_EVENT_SET => "An event is signaled."
    case VK_EVENT_RESET => "An event is unsignaled."
    case VK_INCOMPLETE => "A array was too small for the result."
    case VK_SUBOPTIMAL_KHR => "A swapchain no longer matches the surface properties exactly, but can still be used to present to the surface successfully."

        // Error codes
    case VK_ERROR_OUT_OF_HOST_MEMORY => "A host memory allocation has failed."
    case VK_ERROR_OUT_OF_DEVICE_MEMORY => "A device memory allocation has failed."
    case VK_ERROR_INITIALIZATION_FAILED => "Initialization of an object could not be completed for implementation-specific reasons."
    case VK_ERROR_DEVICE_LOST => "The logical or physical device has been lost."
    case VK_ERROR_MEMORY_MAP_FAILED => "Mapping of a memory object has failed."
    case VK_ERROR_LAYER_NOT_PRESENT => "A requested layer is not present or could not be loaded."
    case VK_ERROR_EXTENSION_NOT_PRESENT => "A requested extension is not supported."
    case VK_ERROR_FEATURE_NOT_PRESENT => "A requested feature is not supported."
    case VK_ERROR_INCOMPATIBLE_DRIVER => "The requested version of Vulkan is not supported by the driver or is otherwise incompatible for implementation-specific reasons."
    case VK_ERROR_TOO_MANY_OBJECTS => "Too many objects of the type have already been created."
    case VK_ERROR_FORMAT_NOT_SUPPORTED => "A requested format is not supported on this device."
    case VK_ERROR_SURFACE_LOST_KHR => "A surface is no longer available."
    case VK_ERROR_NATIVE_WINDOW_IN_USE_KHR => "The requested window is already connected to a VkSurfaceKHR, or to some other non-Vulkan API."
    case VK_ERROR_OUT_OF_DATE_KHR => "A surface has changed in such a way that it is no longer compatible with the swapchain, and further presentation requests using the "
                + "swapchain will fail. Applications must query the new surface properties and recreate their swapchain if they wish to continue presenting to the surface."
    case VK_ERROR_INCOMPATIBLE_DISPLAY_KHR => "The display used by a swapchain does not use the same presentable image layout, or is incompatible in a way that prevents sharing an  image."
    case VK_ERROR_VALIDATION_FAILED_EXT => "A validation layer found an error."
    case other => s"Unknown code $other"

  inline def expectResult(result: Int, inline expected: => Int): Unit =
    if (result != expected) 
        throw new IllegalStateException(s"Expected ${codeOf(expected)} but got ${codeOf(result)}: " + describeResult(result))

  def showFieldData(s: Any, indentSize: Int = 0): String =
    val sc = s.getClass

    val indent = "    " * indentSize
    s match {
      case sb: StructBuffer[s, _] =>
        val elems = sb.iterator.asScala.map(showFieldData(_, indentSize + 1)).mkString(indent, "\n" + indent, "")
        f"Buff ${sc.getName} @ 0x${sb.address}%X p:${sb.position} l:${sb.limit} c:${sb.capacity} " + s"[\n$elems\n$indent]"
      case other =>
        val extraDescr = s match
          case v: Struct[?] => f" @ 0x${v.address}%X s:${v.sizeof} "
          case _ => ""
    
        def describeValue(v: Any): String = v match
          case _: Struct[?] | _: StructBuffer[_, _] => showFieldData(v, indentSize + 1)
          case other => String.valueOf(other)
    
        val fieldContent = sc.getDeclaredFields.filterNot(_.getModifiers.hasFlags(java.lang.reflect.Modifier.STATIC))
          .filter(_.getModifiers.hasFlags(java.lang.reflect.Modifier.PUBLIC))
          .iterator.map(f => s"$indent${f.getName} = ${describeValue(f.get(s))}").mkString("\n")
        val gettersContent = sc.getDeclaredMethods.filterNot(_.getModifiers.hasFlags(java.lang.reflect.Modifier.STATIC))
          .filter(_.getModifiers.hasFlags(java.lang.reflect.Modifier.PUBLIC))
          .filter(_.getParameterCount == 0)
          .iterator.map(m => s"$indent${m.getName}() = ${describeValue(m.invoke(s))}").mkString("\n")
    
        s"${sc.getName}$extraDescr(\n$fieldContent\n$gettersContent\n$indent)"
    }


  class BufferWrapper[T <: Struct[T]](val structBuffer: StructBuffer[T, ?]) extends IndexedSeq[T], AutoCloseable:
    def apply(i: Int) = structBuffer.get(i)
    def length = structBuffer.capacity
    def close() = structBuffer.free()

  inline def toNativeString(s: String, method: AllocMethod): java.nio.ByteBuffer = 
    inline method match
      case _: calloc.type | _: malloc.type => 
        s.nUtf8
      case _ =>
        val memoryStack = summonFrom {
          case ms: MemoryStack => ms
          case _ => error(s"No given memory stack available for requested method ${codeOf(method)}")
        }
        s.nUtf8Stack(memoryStack)

  inline def toNativeStringBuffer(strings: Seq[String], method: AllocMethod): PointerBuffer =
    val allocStrings = inline method match
      case _: calloc.type | _: malloc.type => 
        strings.map(_.nUtf8)
      case _ =>
        val memoryStack = summonFrom {
          case ms: MemoryStack => ms
          case _ => error("No given memory stack available")
        }
        strings.map(_.nUtf8Stack(memoryStack))

    val res = allocBuffer[PointerBuffer](strings.size, stackCalloc)
    allocStrings foreach (res.put)
    res.flip()
    res

