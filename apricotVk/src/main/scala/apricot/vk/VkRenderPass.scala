package apricot
package vk

import java.nio.LongBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.{VK10, VkAttachmentDescription, VkRenderPassCreateInfo, VkSubpassDependency, VkSubpassDescription}

opaque type VkRenderPass[+vkDevice <: VkLogicalDevice.Any] = Long
object VkRenderPass:
  def apply(vkInstance: VkLogicalDevice.Any, address: Long): VkRenderPass[vkInstance.type] = address.asInstanceOf

  val NoInstance = 0L.asInstanceOf[VkRenderPass[Nothing]]

  extension [vkDevice <: VkLogicalDevice.Any](instance: VkRenderPass[vkDevice])
    inline def unwrap: Long = instance.asInstanceOf[Long]
    def destroy()(using ValueOf[vkDevice]): Unit = VK10.vkDestroyRenderPass(valueOf[vkDevice].unwrap, unwrap, null)