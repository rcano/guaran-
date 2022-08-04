package apricot
package vk

import java.nio.{ByteBuffer, IntBuffer, LongBuffer}
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.{
  KHRSwapchain,
  VK10
}
import scala.util.chaining._

/* discard this class, a right way to do this would be merging surface and swapchain, since those two go together */

class VkSwapChain (
  val logicalDevice: VkLogicalDevice.Any,
  val surface: NativeHandle,
  private var _swapchain: NativeHandle,
  private var _width: Int,
  private var _height: Int,
) {
  def dispose(): Unit = 
    KHRSwapchain.vkDestroySwapchainKHR(logicalDevice.unwrap, _swapchain, null)

  
}
object VkSwapChain {
  given Managed[VkSwapChain] with
    inline def close(s: VkSwapChain) = s.dispose() 
}