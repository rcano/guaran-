package apricot
package vk

import apricot.graphics.GraphicsStack
import apricot.vk.VulkanGraphicsStack
import guarana.util.cfor
import java.nio.ByteBuffer
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.{KHRSwapchain, VK10, VkClearValue, VkPipelineColorBlendAttachmentState, VkRect2D, VkViewport}
import scala.util.chaining.*

import allocators.given

class SimpleTriangleLayer(
  val vkStack: VulkanGraphicsStack, bgColor: vkStack.Color, fgColor: vkStack.Color
) extends Layer("triangle") {
  import vkStack.Color.given

  var lastSwapChainId = VK10.VK_NULL_HANDLE
  private var frameBuffers: IArray[NativeHandle] = IArray.empty
  private var pipeline: NativeHandle = VK10.VK_NULL_HANDLE
  private var pipelineLayout: NativeHandle = VK10.VK_NULL_HANDLE

  override def draw(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
    val vkGc = gContext.asInstanceOf[vkStack.VkGraphicsContext]
    if (vkGc.presenter.swapChain != lastSwapChainId) setup(vkStack, vkGc)
  }

  private def setup(_vkStack: vkStack.type, gc: vkStack.VkGraphicsContext): Unit = {
    import vkStack.Color
    if (frameBuffers.length > 0) frameBuffers.foreach(vkStack.logicalDevice.destroyFrameBuffer)
    frameBuffers = withStack { stack ?=>
      val size = gc.presenter.swapchainSize
      for img <- gc.presenter.imageViews
      yield vkStack.logicalDevice.createFrameBuffer(gc.defaultRenderPass, stack.longs(img), size._1, size._2, 1)
    }.asInstanceOf[IArray[NativeHandle]]

    if (pipeline != VK10.VK_NULL_HANDLE) vkStack.logicalDevice.destroyPipeline(pipeline)
    pipeline = createPipeline(vkStack.physicalDevice, vkStack.logicalDevice, gc.presenter, gc.defaultRenderPass)

    withStack {
      // clearing images to a fixed color
      scribe.info(s"using bg = ${bgColor.rgba.toHexString}")
      val clearColor = VkFactory.clearColor(bgColor.red / 255f, bgColor.green / 255f, bgColor.blue / 255f, bgColor.alpha / 255f)
      val subresourceRange = VkFactory.imageSubresourceRange(VK10.VK_IMAGE_ASPECT_COLOR_BIT, 0, 1, 0, 1)

      cfor(0, _ < gc.presenter.images.length) { i =>
        val imageHandle = gc.presenter.images(i)

        gc.graphicsCommandBuffers(i).beginCommandBuffer(VK10.VK_COMMAND_BUFFER_USAGE_SIMULTANEOUS_USE_BIT)

        // here our presentation queue and our drawing queue are the same, but we put this for completion sake
        val presentQueue = vkStack.graphicsQueueFamilyIndex
        if (vkStack.graphicsQueueFamilyIndex != presentQueue) {
          //barrier from present to draw
          gc.graphicsCommandBuffers(i).pipelineBarrier(
            srcStageMask = VK10.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT,
            dstStageMask = VK10.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT,
            dependencyFlags = 0,
            memoryBarriers = null,
            bufferMemoryBarriers = null,
            imageMemoryBarriers = VkFactory
              .imageMemoryBarriers(1)
              .srcAccessMask(VK10.VK_ACCESS_MEMORY_READ_BIT)
              .dstAccessMask(VK10.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT)
              .oldLayout(KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR) //no need to change layouts, renderpass does this automatically
              .newLayout(KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR)
              .srcQueueFamilyIndex(presentQueue)
              .dstQueueFamilyIndex(vkStack.graphicsQueueFamilyIndex)
              .image(imageHandle)
              .subresourceRange(subresourceRange)
          )
        }

        gc.graphicsCommandBuffers(i).beginRenderPass(
          renderPass = gc.defaultRenderPass,
          frameBuffer = frameBuffers(i),
          renderArea = VkFactory.rect2d(gc.presenter.swapchainSize._1, gc.presenter.swapchainSize._2),
          clearValues = allocBuffer[VkClearValue](1, stackMalloc).color(clearColor),
          contents = VK10.VK_SUBPASS_CONTENTS_INLINE
        )

        gc.graphicsCommandBuffers(i).bindPipeline(pipeline, VK10.VK_PIPELINE_BIND_POINT_GRAPHICS)
        gc.graphicsCommandBuffers(i).draw(vertexCount = 3, instanceCount = 1, firstVertex = 0, firstInstance = 0)
        gc.graphicsCommandBuffers(i).endRenderPass()

        if (vkStack.graphicsQueueFamilyIndex != presentQueue) {
          //barrier from draw to present
          gc.graphicsCommandBuffers(i).pipelineBarrier(
            srcStageMask = VK10.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT,
            dstStageMask = VK10.VK_PIPELINE_STAGE_BOTTOM_OF_PIPE_BIT,
            dependencyFlags = 0,
            memoryBarriers = null,
            bufferMemoryBarriers = null,
            imageMemoryBarriers = VkFactory
              .imageMemoryBarriers(1)
              .srcAccessMask(VK10.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT)
              .dstAccessMask(VK10.VK_ACCESS_MEMORY_READ_BIT)
              .oldLayout(KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR) //no need to change layouts, renderpass does this automatically
              .newLayout(KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR)
              .srcQueueFamilyIndex(vkStack.graphicsQueueFamilyIndex)
              .dstQueueFamilyIndex(presentQueue)
              .image(imageHandle)
              .subresourceRange(subresourceRange)
          )
        }

        gc.graphicsCommandBuffers(i).endCommandBuffer()
        i + 1
      }
    }

    lastSwapChainId = gc.presenter.swapChain
  }

  private def createPipeline(
      device: VkPhysicalDevice,
      logicalDevice: VkLogicalDevice.Any,
      presentationEngine: VkPresenter,
      renderPass: VkRenderPass[logicalDevice.type]
  ): NativeHandle = withStack {
    if (pipelineLayout == 0) pipelineLayout = logicalDevice.createPipelineLayout()

    val PolygonMode = VK10.VK_POLYGON_MODE_FILL
    val CullMode = VK10.VK_CULL_MODE_BACK_BIT
    val FrontFace = VK10.VK_FRONT_FACE_COUNTER_CLOCKWISE

    val pipelineCreateInfo = VkFactory
      .graphicsPipelines(1)
      .renderPass(renderPass.unwrap)
      .pVertexInputState(VkFactory.pipelineVertexInputState(null, null))
      .pInputAssemblyState(VkFactory.pipelineInputAssemblyState(topology = VK10.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST))
      .pViewportState(
        VkFactory.pipelineViewportState(
          viewportCount = 1,
          viewports = allocBuffer[VkViewport](1, calloc)
            .width(presentationEngine.swapchainSize._1)
            .height(presentationEngine.swapchainSize._2)
            .maxDepth(1),
          scissorCount = 1,
          scissors = allocBuffer[VkRect2D](1, calloc)
            .put(0, VkFactory.rect2d(presentationEngine.swapchainSize._1, presentationEngine.swapchainSize._2))
        )
      )
      .pRasterizationState(
        VkFactory.pipelineRasterizationState(polygonMode = PolygonMode, cullMode = CullMode, frontFace = FrontFace, lineWidth = 1.0f)
      )
      .pMultisampleState(VkFactory.pipelineMultisampleState(rasterizationSamples = VK10.VK_SAMPLE_COUNT_1_BIT))
      .pColorBlendState(
        VkFactory.pipelineColorBlendState(
          logicOp = VK10.VK_LOGIC_OP_COPY,
          attachments = allocBuffer[VkPipelineColorBlendAttachmentState](1, calloc)
            .srcColorBlendFactor(VK10.VK_BLEND_FACTOR_ONE)
            .dstColorBlendFactor(VK10.VK_BLEND_FACTOR_ZERO)
            .colorBlendOp(VK10.VK_BLEND_OP_ADD)
            .srcAlphaBlendFactor(VK10.VK_BLEND_FACTOR_ONE)
            .dstAlphaBlendFactor(VK10.VK_BLEND_FACTOR_ZERO)
            .alphaBlendOp(VK10.VK_BLEND_OP_ADD)
            .colorWriteMask(
              VK10.VK_COLOR_COMPONENT_R_BIT | VK10.VK_COLOR_COMPONENT_G_BIT | VK10.VK_COLOR_COMPONENT_B_BIT | VK10.VK_COLOR_COMPONENT_A_BIT
            ),
          blendConstants = MemoryUtil.memCallocFloat(4) //floats(0, 0, 0, 0)
        )
      )
      .layout(pipelineLayout)
      // .pDepthStencilState(VkFactory.pipelineDepthStencilState(depthCompareOp = VK10.VK_COMPARE_OP_ALWAYS).tap { stencil =>
      //   stencil.back()
      //     .failOp(VK10.VK_STENCIL_OP_KEEP)
      //     .passOp(VK10.VK_STENCIL_OP_KEEP)
      //     .compareOp(VK10.VK_COMPARE_OP_ALWAYS)
      //   stencil.front(stencil.back())
      // })
      .pStages(
        VkFactory
          .pipelineShaderStages(2)
          .position(0)
          .stage(VK10.VK_SHADER_STAGE_VERTEX_BIT)
          .pName("main".nUtf8)
          .module(
            logicalDevice
              .createShaderModule(loadShader("/home/randa/Development/vk-dotty/target/scala-3.1.2/classes/shader/triangle.vert.spv"))
              .unwrap
              .tap(l => println(f"triangle.vert shader created at 0x$l%X"))
          )
          .position(1)
          .stage(VK10.VK_SHADER_STAGE_FRAGMENT_BIT)
          .pName("main".nUtf8)
          .module(
            logicalDevice
              .createShaderModule(loadShader("/home/randa/Development/vk-dotty/target/scala-3.1.2/classes/shader/triangle.frag.spv"))
              .unwrap
              .tap(l => println(f"triangle.frag shader created at 0x$l%X"))
          )
          .position(0)
      )
    // Enable dynamic states
    // Describes the dynamic states to be used with this pipeline
    // Dynamic states can be set even after the pipeline has been created
    // So there is no need to create new pipelines just for changing
    // a viewport's dimensions or a scissor box
    // .pDynamicState(VkFactory.pipelineDynamicState(MemoryUtil.memAllocInt(2)))
    logicalDevice.createGraphicsPipeline(pipelineCreateInfo)
  }

  private def loadShader(path: String): ByteBuffer =
    val content = better.files.File(path).byteArray
    MemoryUtil.memAlloc(content.length).put(content).flip()
}
