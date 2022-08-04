package apricot
package vk

import guarana.unn
import java.nio.IntBuffer
import org.lwjgl.PointerBuffer
import org.lwjgl.system._
import org.lwjgl.vulkan
import org.lwjgl.vulkan.{KHRSurface, VK10, VkSurfaceCapabilitiesKHR, VkSurfaceFormatKHR}
import allocators.given
import VkUtil.BufferWrapper

class VkPhysicalDevice private (
    private val device: vulkan.VkPhysicalDevice,
    val features: vulkan.VkPhysicalDeviceFeatures,
    val properties: vulkan.VkPhysicalDeviceProperties,
    val memoryProperties: vulkan.VkPhysicalDeviceMemoryProperties,
    val queueFamilyProperties: BufferWrapper[vulkan.VkQueueFamilyProperties],
    val layerProperties: BufferWrapper[vulkan.VkLayerProperties],
) {
  def unwrap: vulkan.VkPhysicalDevice = device
  def name: String = properties.deviceNameString.unn

  def createDevice(
      queueFamilyIndex: Int,
      queuePriorities: Array[Float],
      enabledExtensions: PointerBuffer,
      enabledLayers: PointerBuffer,
  )(using MemoryStack): VkLogicalDevice[this.type] = {
    val queuePrioritiesBuff = MemoryStack.stackMallocFloat(queuePriorities.length).unn.put(queuePriorities).unn.flip().unn
    val queueCreateInfo = allocBuffer[vulkan.VkDeviceQueueCreateInfo](1, stackCalloc)
      .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO)
      .queueFamilyIndex(queueFamilyIndex)
      .pQueuePriorities(queuePrioritiesBuff)

    val createInfo = alloc[vulkan.VkDeviceCreateInfo](stackCalloc)
      .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO)
      .pQueueCreateInfos(queueCreateInfo)
      .ppEnabledExtensionNames(enabledExtensions)
      .ppEnabledLayerNames(enabledLayers)
    val deviceAddr =
      (VK10.vkCreateDevice(device, createInfo, null, _)).returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed creating logical device").get(0)
    // note, VkDevice uses deviceCreateInfo in the constructor to fetch the device capabilities but it wont store it, so it's
    // safe to pass a reference to a stack alloc'd one that will get bumped
    VkLogicalDevice(deviceAddr, this, createInfo)
  }

  def vulkanInstance: VkInstance = VkInstance.wrap(device.getInstance().unn)

  inline def getSurfaceFormats(surfaceAddr: Long, allocMethod: AllocMethod) =
    (KHRSurface
      .vkGetPhysicalDeviceSurfaceFormatsKHR(device, surfaceAddr, _: IntBuffer, _: VkSurfaceFormatKHR.Buffer))
      .countAndAlloc(allocMethod)

  inline def getSurfaceCapabilitiesKhr(surface: NativeHandle, allocMethod: AllocMethod): VkSurfaceCapabilitiesKHR =
    (KHRSurface
      .vkGetPhysicalDeviceSurfaceCapabilitiesKHR(device, surface, _))
      .returning(allocMethod)(VK10.VK_SUCCESS.==, "failed obtaining physical device surface capabilities KHR")

  def getSurfacePresentModesKhr(surface: NativeHandle)(using MemoryStack): Array[Int] = {
    val count = new Array[Int](1)
    VkUtil.expectResult(
      KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(device, surface, count, null),
      VK10.VK_SUCCESS
    )
    val res = new Array[Int](count(0))
    VkUtil.expectResult(
      KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(device, surface, count, res),
      VK10.VK_SUCCESS
    )
    res
  }
}

object VkPhysicalDevice {
  def apply(address: Long, vkInstance: VkInstance)(using MemoryStack): VkPhysicalDevice =
    val peer = vulkan.VkPhysicalDevice(address, vkInstance.unwrap)
    val features = (VK10.vkGetPhysicalDeviceFeatures(peer, _)).returning(calloc)
    val properties = (VK10.vkGetPhysicalDeviceProperties(peer, _)).returning(calloc)
    val memoryProperties = (VK10.vkGetPhysicalDeviceMemoryProperties(peer, _)).returning(calloc)
    val queueFamilyProperties = BufferWrapper((VK10.vkGetPhysicalDeviceQueueFamilyProperties(peer, _: IntBuffer, _: vulkan.VkQueueFamilyProperties.Buffer)).countAndAlloc(calloc))
    val layerProperties = BufferWrapper((VK10.vkEnumerateDeviceLayerProperties(peer, _: IntBuffer, _: vulkan.VkLayerProperties.Buffer)).countAndAlloc(calloc))
    new VkPhysicalDevice(peer, features, properties, memoryProperties, queueFamilyProperties, layerProperties)

  given Managed[VkPhysicalDevice] with {
    inline def close(d: VkPhysicalDevice) =
      d.features.free()
      d.properties.free()
      d.memoryProperties.free()
      d.queueFamilyProperties.close()
  }
}
