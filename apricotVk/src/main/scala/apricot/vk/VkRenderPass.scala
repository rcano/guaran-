package apricot
package vk

import org.lwjgl.vulkan.VK10

opaque type VkRenderPass[+vkDevice <: VkLogicalDevice.Any] = Long
object VkRenderPass:
  def apply(vkInstance: VkLogicalDevice.Any, address: Long): VkRenderPass[vkInstance.type] = address.asInstanceOf

  val NoInstance = 0L.asInstanceOf[VkRenderPass[Nothing]]
  given [vkDevice <: VkLogicalDevice.Any]: Managed[VkRenderPass[vkDevice]] with {
    inline def close(r: VkRenderPass[vkDevice]): Unit = r.destroy()(using scala.compiletime.summonInline)
  }

  extension [vkDevice <: VkLogicalDevice.Any](instance: VkRenderPass[vkDevice])
    inline def unwrap: Long = instance.asInstanceOf[Long]
    def destroy()(using ValueOf[vkDevice]): Unit = VK10.vkDestroyRenderPass(valueOf[vkDevice].unwrap, unwrap, null)