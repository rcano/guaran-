package apricot
package vk

import apricot.graphics.GraphicsStack
import apricot.tools.GlfwWindow
import guarana.ColorLike
import java.nio.LongBuffer
import org.lwjgl.glfw.GLFWVulkan
import org.lwjgl.vulkan.{
  KHRSurface,
  KHRSwapchain,
  VK10,
  VkAttachmentDescription,
  VkAttachmentReference,
  VkSubpassDependency,
  VkSubpassDescription
}

import allocators.given
import guarana.impl.RingBuffer
import scala.collection.mutable.ArrayDeque

class VulkanGraphicsStack(
    val physicalDevice: VkPhysicalDevice
) extends GraphicsStack {
  import VulkanGraphicsStack.*
  type Image = Nothing
  type Color = VkColor
  type GraphicsContext = VkGraphicsContext

  override protected object imageSupport extends ImageSupport {

    override def load(bytes: Array[Byte], format: String): Image = ???

    override given managedImage: Managed[Image] with {
      override inline def close(r: Image): Unit = ()
    }
  }
  opaque type VkColor = Int

  override protected object colorLike extends ColorLike[Color] {
    private inline def clampToInt(v: Float): Int = ((if v < 0 then 0 else if v > 1 then 1 else v) * 255).toInt
    def apply(r: Float, g: Float, b: Float, a: Float = 1f) = rgba(clampToInt(r), clampToInt(g), clampToInt(b), clampToInt(a))
    def rgba(r: Int, g: Int, b: Int, a: Int = 255) = (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff)
    def argb(argb: Int) = argb

    extension (c: Color) {
      def red: Int = c >> 16 & 0xff
      def green: Int = c >> 8 & 0xff
      def blue: Int = c & 0xff
      def alpha: Int = c >>> 24 & 0xff
      def asRgba: Int = (c & 0x00ffffff) << 24 + ((c >>> 24) & 0xff)
      def asArgb: Int = c
    }
  }

  val graphicsQueueFamilyIndex = withStack {
    physicalDevice.queueFamilyProperties.zipWithIndex
      .find((props, idx) => props.queueFlags().hasFlags(VK10.VK_QUEUE_GRAPHICS_BIT))
      .getOrElse(throw new IllegalStateException("Failed to find graphics queue family"))
      ._2
  }

  val logicalDevice = withStack {
    physicalDevice.createDevice(
      queueFamilyIndex = graphicsQueueFamilyIndex,
      queuePriorities = Array(0),
      enabledExtensions = Seq(KHRSwapchain.VK_KHR_SWAPCHAIN_EXTENSION_NAME).toPointerBuffer(stackMalloc),
      enabledLayers = Seq("VK_LAYER_LUNARG_standard_validation").toPointerBuffer(stackMalloc)
    )
  }
  scribe.debug(s"Vulkan ogical device initialized: $logicalDevice")

  val graphicsCommandPool = withStack {
    logicalDevice.createCommandPool(
      graphicsQueueFamilyIndex,
      VK10.VK_COMMAND_POOL_CREATE_RESET_COMMAND_BUFFER_BIT | VK10.VK_COMMAND_POOL_CREATE_TRANSIENT_BIT
    )
  }
  scribe.debug(s"Vulkan graphics command pool created: ${graphicsCommandPool.unwrap.toHexString}")

  /** Represents a command buffer and associated sempahores and fences. */
  class CommandBatch(val commandBuffer: VkCommandBuffer) {

    /** Fence signaled once this command batch has been presented */
    val imagePresentedFence: NativeHandle = withStack { logicalDevice.createFence(0) }

    /** Semaphore used for image acquiring */
    val acquireImageSemaphore: NativeHandle = withStack { logicalDevice.createSemaphore() }
    val imagePresentedSempahore: NativeHandle = withStack { logicalDevice.createSemaphore() }
  }

  class VkGraphicsContext private[VulkanGraphicsStack] (window: GlfwWindow) extends GraphicsStack.GraphicsContext {
    private var closed: Boolean = false

    scribe.debug(s"initializing Vulkan on window $window...")

    val surfaceHandle: NativeHandle = (GLFWVulkan
      .glfwCreateWindowSurface(physicalDevice.vulkanInstance.unwrap, window.windowHandle, null, _: LongBuffer))
      .returning(malloc)(VK10.VK_SUCCESS.==, "failed creating window surface")
      .get(0)
    scribe.debug(s" - vulkan suface created: ${surfaceHandle.toHexString}")

    val presenter = VkPresenter(physicalDevice, logicalDevice, surfaceHandle)

    /** Graphics queue for the device (the 0 index one). This queue is by default presented on each frame, if you use more queues, you'll
      * have to call into the presenter to present them
      */
    val graphicsQueue = withStack { logicalDevice.getDeviceQueue(graphicsQueueFamilyIndex, 0) }

    /** Optional default render pass.
      *
      * It renders directly to the swapchain using its format, 1 sample, load operation is clear, no stencils, and the final layout is
      * directly the one in the presentation window.
      */
    def createDefaultRenderPass(): VkRenderPass[logicalDevice.type] = withStack {
      logicalDevice.createRenderPass(
        attachments = allocBuffer[VkAttachmentDescription](1, stackCalloc)
          .format(presenter.firstColorFormat) //use the format of the swapchain, because we are rendering directly to it
          .samples(VK10.VK_SAMPLE_COUNT_1_BIT)
          .loadOp(VK10.VK_ATTACHMENT_LOAD_OP_CLEAR)
          .storeOp(VK10.VK_ATTACHMENT_STORE_OP_STORE)
          .stencilLoadOp(VK10.VK_ATTACHMENT_LOAD_OP_DONT_CARE) //ignored for color attachments
          .stencilStoreOp(VK10.VK_ATTACHMENT_STORE_OP_DONT_CARE) //ignored for color attachments
          .initialLayout(KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR) //image is provided by presentation engine, so this is its layout
          .finalLayout(KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR),
        subpasses = allocBuffer[VkSubpassDescription](1, stackCalloc)
          .pipelineBindPoint(VK10.VK_PIPELINE_BIND_POINT_GRAPHICS) //subpass used in a graphics pipeline (not a compute one)
          .colorAttachmentCount(1)
          .pColorAttachments(
            allocBuffer[VkAttachmentReference](1, stackCalloc).attachment(0).layout(VK10.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL)
          ),
        dependencies = allocBuffer[VkSubpassDependency](2, stackCalloc)
          .srcSubpass(VK10.VK_SUBPASS_EXTERNAL)
          .dstSubpass(VK10.VK_SUBPASS_CONTENTS_INLINE)
          .srcStageMask(VK10.VK_PIPELINE_STAGE_BOTTOM_OF_PIPE_BIT)
          .dstStageMask(VK10.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT)
          .srcAccessMask(VK10.VK_ACCESS_MEMORY_READ_BIT)
          .dstAccessMask(VK10.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT)
          .dependencyFlags(VK10.VK_DEPENDENCY_BY_REGION_BIT)
          .position(1)
          .srcSubpass(VK10.VK_SUBPASS_CONTENTS_INLINE)
          .dstSubpass(VK10.VK_SUBPASS_EXTERNAL)
          .srcStageMask(VK10.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT)
          .dstStageMask(VK10.VK_PIPELINE_STAGE_BOTTOM_OF_PIPE_BIT)
          .srcAccessMask(VK10.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT)
          .dstAccessMask(VK10.VK_ACCESS_MEMORY_READ_BIT)
          .dependencyFlags(VK10.VK_DEPENDENCY_BY_REGION_BIT)
      )
    }

    private val commandBuffers = withStack { graphicsCommandPool.createCommandBuffer(3) }.asInstanceOf[IArray[VkCommandBuffer]]

    private val availableBatches = ArrayDeque[CommandBatch](
      CommandBatch(commandBuffers(0)),
      CommandBatch(commandBuffers(1)),
      CommandBatch(commandBuffers(2)),
    )
    private val recordedBatches = new ArrayDeque[CommandBatch](3)
    private val submittedBatches = new ArrayDeque[CommandBatch](3)

    def recordBatch(f: CommandBatch => Unit): Unit = {
      val b = availableBatches.removeHead()
      f(b)
      recordedBatches += b
    }

    presenter.createSwapchain(() => scribe.debug(" - swapchain initialized\nWindow ready."))

    override def renderLayers(layers: Iterable[Layer], clearFirst: Boolean, color: Int): Unit = withStack { stack ?=>
      if closed then throw new IllegalStateException("already closed context")
      submittedBatches
        .removeHeadWhile(batch => logicalDevice.isFenceSignaled(batch.imagePresentedFence))
        .foreach(batch =>
          availableBatches += batch
          logicalDevice.resetFence(batch.imagePresentedFence)
        )

      if (availableBatches.isEmpty) return // skip this tick, there's no available buffer to use
      layers.foreach(_.draw(VulkanGraphicsStack.this, this))
      if (recordedBatches.isEmpty) return // no layer recorded anything

      val toPresent = recordedBatches.removeHead()
      val img = presenter.acquireNextImage(1_000_000, toPresent.acquireImageSemaphore, VK10.VK_NULL_HANDLE)
      VkUtil.expectResult(
        VK10.vkQueueSubmit(
          graphicsQueue,
          VkFactory.queueSubmit(
            waitSemaphoreCount = 1,
            waitSemaphores = stack.longs(toPresent.acquireImageSemaphore),
            waitDstStageMask = stack.ints(VK10.VK_PIPELINE_STAGE_TRANSFER_BIT),
            commandBuffers = stack.pointers(toPresent.commandBuffer),
            signalSemaphores = stack.longs(toPresent.imagePresentedSempahore)
          ),
          toPresent.imagePresentedFence
        ),
        VK10.VK_SUCCESS
      )
      presenter.present(
        graphicsQueue,
        Array(img),
        Array(toPresent.imagePresentedSempahore)
      )
      submittedBatches += toPresent
    }

    override def close(): Unit = {
      closed = true
      logicalDevice.waitIdle() //wait for the graphics device to be done with all our resources in order to dispose
      (availableBatches ++ recordedBatches ++ submittedBatches).foreach { batch =>
        logicalDevice.destroyFence(batch.imagePresentedFence)
        logicalDevice.destroySemaphore(batch.acquireImageSemaphore)
        logicalDevice.destroySemaphore(batch.imagePresentedSempahore)
      }
      graphicsCommandPool.destroy()
      KHRSurface.vkDestroySurfaceKHR(physicalDevice.vulkanInstance.unwrap, surfaceHandle, null)
      logicalDevice.destroy()
    }

  }

  override def setup(window: GlfwWindow): GraphicsContext = VkGraphicsContext(window)

  override def shutdown(): Unit = ()
}

object VulkanGraphicsStack {}
