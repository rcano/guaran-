package apricot
package vk

import org.lwjgl.system._
import org.lwjgl.vulkan.{
  VK10,
  VkCommandBufferAllocateInfo,
}
import allocators.given

type VkCommandPool[+vkDevice <: VkLogicalDevice.Any] = VkCommandPool.opaques.VkCommandPool[vkDevice]

object VkCommandPool:
  object opaques:
    opaque type VkCommandPool[+vkDevice <: VkLogicalDevice.Any] = Long
  
  def apply(vkInstance: VkLogicalDevice.Any, addr: Long): VkCommandPool[vkInstance.type] = addr.asInstanceOf[VkCommandPool[vkInstance.type]]

  given [vkDevice <: VkLogicalDevice.Any & Singleton]: Managed[VkCommandPool[vkDevice]] with
    inline def close(p: VkCommandPool[vkDevice]): Unit = scala.compiletime.summonFrom {
      case v: ValueOf[`vkDevice`] => p.destroy()(using v)
      case _ => ???
    }

  extension [vkDevice <: VkLogicalDevice.Any & Singleton](instance: VkCommandPool[vkDevice])
    inline def unwrap: Long = instance.asInstanceOf[Long]
    inline def destroy()(using ValueOf[vkDevice]): Unit = VK10.vkDestroyCommandPool(valueOf[vkDevice].unwrap, unwrap, null)

    def createCommandBuffer(
      bufferCount: Int,
      level: VK10.VK_COMMAND_BUFFER_LEVEL_PRIMARY.type | VK10.VK_COMMAND_BUFFER_LEVEL_SECONDARY.type = VK10.VK_COMMAND_BUFFER_LEVEL_PRIMARY
    )(using MemoryStack, ValueOf[vkDevice]): Array[VkCommandBuffer] =
      val vkDevice = valueOf[vkDevice]
      val createInfo = alloc[VkCommandBufferAllocateInfo](stackCalloc)
        .sType(VK10.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO)
        .commandPool(instance.unwrap)
        .level(level)
        .commandBufferCount(bufferCount)
      val buffers = summon[MemoryStack].mallocPointer(bufferCount)
      VkUtil.expectResult(
        VK10.vkAllocateCommandBuffers(vkDevice.unwrap, createInfo, buffers),
        VK10.VK_SUCCESS
      )
      buffers.toIterator(b => VkCommandBuffer(b.get(), vkDevice)).toArray