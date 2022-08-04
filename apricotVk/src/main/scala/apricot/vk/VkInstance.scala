package apricot
package vk

import java.nio.IntBuffer
import org.lwjgl.PointerBuffer
import org.lwjgl.system._
import org.lwjgl.vulkan
import allocators.given

type VkInstance = VkInstance.opaques.VkInstance
object VkInstance:
  val NoInstance: VkInstance = null.asInstanceOf
  object opaques:
    opaque type VkInstance <: Pointer = vulkan.VkInstance

  def create(
    vkVersion: Int,
    enabledExtensions: PointerBuffer,
    enabledLayers: PointerBuffer,
  )(using MemoryStack): VkInstance =
    val appInfo = alloc[vulkan.VkApplicationInfo](stackMalloc)
      .sType(vulkan.VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO)
      .apiVersion(vkVersion)
    val createInfo = alloc[vulkan.VkInstanceCreateInfo](stackMalloc)
      .sType(vulkan.VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO)
      .pApplicationInfo(appInfo)
      .ppEnabledExtensionNames(enabledExtensions)
      .ppEnabledLayerNames(enabledLayers)

    val instanceAddr = (vulkan.VK10.vkCreateInstance(createInfo, null, _))
      .returning(stackCalloc)(vulkan.VK10.VK_SUCCESS.==, "failed creating vulkan instance")
      .get(0)
    wrap(vulkan.VkInstance(instanceAddr, createInfo))
  end create

  given Managed[VkInstance] with {
    inline def close(r: VkInstance): Unit = r.destroy()
  }

  def wrap(instance: vulkan.VkInstance): VkInstance = instance.asInstanceOf[VkInstance]

  extension (instance: VkInstance)
    inline def unwrap: vulkan.VkInstance = instance.asInstanceOf[vulkan.VkInstance]
    def destroy(): Unit = vulkan.VK10.vkDestroyInstance(instance.unwrap, null)
    def enumeratePhysicalDevices()(using MemoryStack): Seq[VkPhysicalDevice] =
      (vulkan.VK10.vkEnumeratePhysicalDevices(instance.unwrap, _: IntBuffer, _: PointerBuffer)).countAndAlloc(stackCalloc)
        .toIterator(b => VkPhysicalDevice(b.get, instance)).toIndexedSeq


    def getInstanceProcAddr(procedure: String)(using MemoryStack) = vulkan.VK10.vkGetInstanceProcAddr(instance.unwrap, procedure.nUtf8Stack(summon))
  end extension