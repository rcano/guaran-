package apricot
package vk

import guarana.util.cfor
import java.nio.{IntBuffer, LongBuffer}
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.{KHRSurface, KHRSwapchain, VK10, VkExtent2D, VkQueue, VkSurfaceCapabilitiesKHR}
import allocators.given

/** This class encapsulates the all the needed steps to handle swap chains and images in order to presen them. */
final class VkPresenter(
    val physicalDevice: VkPhysicalDevice,
    val logicalDevice: VkLogicalDevice[physicalDevice.type],
    val surface: NativeHandle,
    val swapChainConfigurer: (VkSurfaceCapabilitiesKHR, VkPresenter.SwapchainConfigBuilder) => Unit = null
) {

  val surfaceFormats = physicalDevice.getSurfaceFormats(surface, calloc)
  val firstColorFormat = surfaceFormats.get(0).format match {
    case VK10.VK_FORMAT_UNDEFINED => VK10.VK_FORMAT_B8G8R8A8_UNORM
    case other => other
  }

  def surfaceCapabilities(using MemoryStack) = physicalDevice.getSurfaceCapabilitiesKhr(surface, stackCalloc)

  private var onSwapchainCreatedAction: () => Any = null

  private var currentSwapchain: NativeHandle = VK10.VK_NULL_HANDLE
  final def swapChain: NativeHandle = currentSwapchain
  private var _images: Array[NativeHandle] = null
  final def images = _images //I wished I could make this iarrays
  private var _imageViews: Array[NativeHandle] = null
  final def imageViews = _imageViews //I wished I could make this iarrays
  private var _swapchainExtent: (Int, Int) = null
  def swapchainSize = _swapchainExtent
  private var _currentImage: Int = 0 // always start with the very first image
  def currentImage: Int = _currentImage

  def createSwapchain(onSwapchainCreatedAction: () => Any): Unit = {
    withStack {
      this.onSwapchainCreatedAction = onSwapchainCreatedAction
      val currentCapabilities = surfaceCapabilities

      val conf = VkPresenter.SwapchainConfigBuilder()
      conf.imagesCount = {
        val res = currentCapabilities.minImageCount + 1
        if currentCapabilities.maxImageCount > 0 then res min currentCapabilities.maxImageCount else res
      }
      conf.presentationMode =
        if conf.imagesCount > 2 && physicalDevice.getSurfacePresentModesKhr(surface).contains(KHRSurface.VK_PRESENT_MODE_MAILBOX_KHR) then
          KHRSurface.VK_PRESENT_MODE_MAILBOX_KHR
        else KHRSurface.VK_PRESENT_MODE_FIFO_KHR

      if swapChainConfigurer != null then swapChainConfigurer(currentCapabilities, conf)
      val swapchainExtent =
        if conf.swapchainExtent != null then conf.swapchainExtent
        else if currentCapabilities.currentExtent.width == -1 then alloc[VkExtent2D](stackMalloc).set(800, 680)
        else currentCapabilities.currentExtent
      val surfaceTansform =
        if currentCapabilities.supportedTransforms.hasFlags(KHRSurface.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR) then
          KHRSurface.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR
        else currentCapabilities.currentTransform

      val newSwapchain = logicalDevice.createSwapchain(
        surface,
        conf.imagesCount,
        firstColorFormat,
        surfaceFormats.get(0).colorSpace,
        swapchainExtent,
        conf.usageFlags,
        VK10.VK_SHARING_MODE_EXCLUSIVE,
        null,
        surfaceTansform,
        conf.compositeAlpha,
        conf.presentationMode,
        currentSwapchain,
        clipped = true
      )

      _swapchainExtent = swapchainExtent.width() -> swapchainExtent.height()

      disposeSwapchain()
      currentSwapchain = newSwapchain

      _images =
        (KHRSwapchain.vkGetSwapchainImagesKHR(logicalDevice.unwrap, newSwapchain, _: IntBuffer, _: LongBuffer)).countAndAlloc().toArray()
      _imageViews = new Array[NativeHandle](_images.length)
      val createImageViewInfo = VkFactory.imageView(
        0,
        VK10.VK_IMAGE_VIEW_TYPE_2D,
        firstColorFormat,
        VkFactory.componentMapping(),
        VkFactory.imageSubresourceRange(VK10.VK_IMAGE_ASPECT_COLOR_BIT, 0, 1, 0, 1)
      )
      cfor(0, _ < _images.length) { i =>
        createImageViewInfo.image(_images(i))
        _imageViews(i) = (VK10
          .vkCreateImageView(logicalDevice.unwrap, createImageViewInfo, null, _: LongBuffer))
          .returning(stackCalloc)(VK10.VK_SUCCESS.==, s"failed to create image view for image $i")
          .get(0)
        i + 1
      }
    }
    this.onSwapchainCreatedAction()
  }

  @scala.annotation.tailrec
  def acquireNextImage(awaitNanos: Long, semaphore: NativeHandle, fence: NativeHandle)(using MemoryStack): Int = {
    var outOfDate = false
    val ord = withStack {
      val res = (KHRSwapchain
        .vkAcquireNextImageKHR(logicalDevice.unwrap, currentSwapchain, awaitNanos, semaphore, fence, _: IntBuffer))
        .returning(stackMalloc)(
          {
            case VK10.VK_SUCCESS | KHRSwapchain.VK_SUBOPTIMAL_KHR => true
            case KHRSwapchain.VK_ERROR_OUT_OF_DATE_KHR =>
              outOfDate = true
              true
            case _ => false
          },
          "failed to acquire image from surface"
        )
        .get
      _currentImage = res
      res
    }
    if (!outOfDate) ord
    else {
      createSwapchain(onSwapchainCreatedAction)
      acquireNextImage(awaitNanos, semaphore, fence)
    }
  }

  def present(queue: VkQueue, images: Array[Int], waitSempahores: Array[NativeHandle])(using stack: MemoryStack): Unit = {
    KHRSwapchain.vkQueuePresentKHR(
      queue,
      VkFactory.queuePresent(
        waitSemaphores = stack.longs(waitSempahores*),
        swapChainCount = 1,
        swapChains = stack.longs(currentSwapchain),
        imageIndices = stack.ints(images*)
      )
    ) match {
      case VK10.VK_SUCCESS | KHRSwapchain.VK_SUBOPTIMAL_KHR =>
      case KHRSwapchain.VK_ERROR_OUT_OF_DATE_KHR => createSwapchain(onSwapchainCreatedAction)
      case other => throw new IllegalStateException(VkUtil.describeResult(other))
    }
  }

  def disposeSwapchain(): Unit = {
    if (currentSwapchain != VK10.VK_NULL_HANDLE) {
      logicalDevice.destroySwapchain(currentSwapchain)
      cfor(0, _ < _images.length) { i =>
        VK10.vkDestroyImageView(logicalDevice.unwrap, _imageViews(i), null)
        i + 1
      }
      _images = null
      _imageViews = null
    }
  }
  def destroy(): Unit = {
    surfaceFormats.free()
    disposeSwapchain()
  }
}
object VkPresenter {
  given Managed[VkPresenter] with
    inline def close(pe: VkPresenter) = pe.destroy()

  class SwapchainConfigBuilder private[VkPresenter] () {
    var imagesCount: Int = 0
    var usageFlags: Int = VK10.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT | VK10.VK_IMAGE_USAGE_TRANSFER_DST_BIT
    var presentationMode: Int = 0
    var swapchainExtent: VkExtent2D = null
    var compositeAlpha = KHRSurface.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR
  }
}
