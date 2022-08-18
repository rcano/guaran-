package apricot
package vk

import org.lwjgl.system._
import org.lwjgl.vulkan.{VK10, VkMemoryRequirements}
import allocators.given

type VkBuffer[+vkDevice <: VkLogicalDevice.Any] = VkBuffer.opaques.VkBuffer[vkDevice]
object VkBuffer:
  object opaques:
    opaque type VkBuffer[+vkDevice <: VkLogicalDevice.Any] = Long

  def apply(vkInstance: VkLogicalDevice.Any, addr: Long): VkBuffer[vkInstance.type] = addr.asInstanceOf[VkBuffer[vkInstance.type]]

  given [vkDevice <: VkLogicalDevice.Any & Singleton]: Managed[VkBuffer[vkDevice]] with
    inline def close(p: VkBuffer[vkDevice]): Unit = scala.compiletime.summonFrom {
      case v: ValueOf[`vkDevice`] => p.destroy()(using v)
      case _ => ???
    }

  extension [vkDevice <: VkLogicalDevice.Any & Singleton](instance: VkBuffer[vkDevice])
    inline def unwrap: Long = instance.asInstanceOf[Long]
    inline def destroy()(using ValueOf[vkDevice]): Unit = VK10.vkDestroyBuffer(valueOf[vkDevice].unwrap, unwrap, null)
    inline def getMemoryRequirements(allocMethod: AllocMethod)(using ValueOf[vkDevice]): VkMemoryRequirements =
      (VK10.vkGetBufferMemoryRequirements(valueOf[vkDevice].unwrap, unwrap, _)).returning(allocMethod)
    inline def bindToMemory(memAddr: NativeHandle, offset: Int)(using ValueOf[vkDevice]): Unit =
      VkUtil.expectResult(VK10.vkBindBufferMemory(valueOf[vkDevice].unwrap, unwrap, memAddr, offset), VK10.VK_SUCCESS)

    def findAppropriateHeap(reqs: VkMemoryRequirements, device: VkPhysicalDevice, heapFlags: Int)(using
        MemoryStack,
        ValueOf[vkDevice]
    ): Int = {
      VkUtil
        .BufferWrapper(device.memoryProperties.memoryTypes())
        .zipWithIndex
        .collectFirst {
          case (memTpe, idx) if reqs.memoryTypeBits().hasFlags(1 << idx) && memTpe.propertyFlags().hasFlags(heapFlags) =>
            idx
        }
        .getOrElse(throw new IllegalStateException("Couldn't find appropriate heap with the given flags"))
    }
