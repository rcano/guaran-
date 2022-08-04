package apricot
package vk

import org.lwjgl.vulkan.VK10

opaque type VkShaderModule[+vkDevice <: VkLogicalDevice.Any] = NativeHandle
object VkShaderModule {
  def apply(device: VkLogicalDevice.Any, moduleHandle: NativeHandle): VkShaderModule[device.type] = moduleHandle.asInstanceOf[VkShaderModule[device.type]]
  given [vkDevice <: VkLogicalDevice.Any & Singleton]: Managed[VkShaderModule[vkDevice]] with
    inline def close(shaderModule: VkShaderModule[vkDevice]) = scala.compiletime.summonFrom {
      case v: ValueOf[`vkDevice`] => shaderModule.destroy()(using v)
      case _ => ???
    }

  extension [vkDevice <: VkLogicalDevice.Any & Singleton](instance: VkShaderModule[vkDevice]) {
    inline def unwrap: NativeHandle = instance.asInstanceOf[NativeHandle]
    def destroy()(using ValueOf[vkDevice]): Unit = VK10.vkDestroyShaderModule(valueOf[vkDevice].unwrap, instance.unwrap, null)
  }
}
