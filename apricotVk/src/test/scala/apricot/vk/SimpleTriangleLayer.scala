package apricot
package vk

import apricot.graphics.GraphicsStack
import apricot.vk.VulkanGraphicsStack
import guarana.?
import guarana.util.cfor
import java.nio.ByteBuffer
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.{KHRSwapchain, VK10, VkClearValue, VkPipelineColorBlendAttachmentState, VkRect2D, VkViewport}
import scala.util.chaining.*

import allocators.given

class SimpleTriangleLayer(
    bgColorArgb: Int,
    fgColorArgb: Int
) extends Layer("triangle") {

  private var impl: StackSpecificImpl = null
  override def draw(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
    if (impl == null || impl.vkStack != graphicsStack) {
      impl.?(_.dispose())
      impl = StackSpecificImpl(graphicsStack.asInstanceOf)
    }
    val captured = impl //capture var into a val for usage
    captured.draw(gContext.asInstanceOf)
  }

  private class StackSpecificImpl(val vkStack: VulkanGraphicsStack) {
    import vkStack.Color, vkStack.Color.ColorLike

    val disposer = Disposer()
    var frameBuffers: IArray[NativeHandle] = IArray.empty
    var renderPass: VkRenderPass[vkStack.logicalDevice.type] = VkRenderPass.NoInstance
    var pipeline: NativeHandle = VK10.VK_NULL_HANDLE

    var lastSwapChainId = VK10.VK_NULL_HANDLE
    def draw(gc: vkStack.VkGraphicsContext): Unit = {
      if (renderPass == VkRenderPass.NoInstance) {
        // if this is our first draw, we'll create some context-specific (i.e window specific) resources to reuse
        renderPass = gc.disposer.track(gc.createDefaultRenderPass())
        pipeline = gc.disposer.track(createPipeline(vkStack.logicalDevice, renderPass))(vkStack.logicalDevice.destroyPipeline)
      }
      if (gc.presenter.swapChain != lastSwapChainId) {
        if gc.presenter.swapChain != VK10.VK_NULL_HANDLE then dispose()
        setup(gc)
      }

      withStack {
        gc.recordBatch { batch =>
          // clearing images to a fixed color
          val bgColor = ColorLike.argb(bgColorArgb)
          val clearColor = VkFactory.clearColor(bgColor.red / 255f, bgColor.green / 255f, bgColor.blue / 255f, bgColor.alpha / 255f)
          val subresourceRange = VkFactory.imageSubresourceRange(VK10.VK_IMAGE_ASPECT_COLOR_BIT, 0, 1, 0, 1)

          val imageHandle = gc.presenter.images(gc.presenter.currentImage)

          batch.commandBuffer.beginCommandBuffer(VK10.VK_COMMAND_BUFFER_USAGE_SIMULTANEOUS_USE_BIT)

          // here our presentation queue and our drawing queue are the same, but we put this for completion sake
          val presentQueue = vkStack.graphicsQueueFamilyIndex
          if (vkStack.graphicsQueueFamilyIndex != presentQueue) {
            //barrier from present to draw
            batch.commandBuffer
              .pipelineBarrier(
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

          batch.commandBuffer
            .beginRenderPass(
              renderPass = renderPass,
              frameBuffer = frameBuffers(gc.presenter.currentImage),
              renderArea = VkFactory.rect2d(gc.presenter.swapchainSize._1, gc.presenter.swapchainSize._2),
              clearValues = allocBuffer[VkClearValue](1, stackMalloc).color(clearColor),
              contents = VK10.VK_SUBPASS_CONTENTS_INLINE
            )

          batch.commandBuffer.bindPipeline(pipeline, VK10.VK_PIPELINE_BIND_POINT_GRAPHICS)

          batch.commandBuffer.setViewport(gc.presenter.swapchainSize._1.toFloat, gc.presenter.swapchainSize._2.toFloat, maxDepth = 1)
          batch.commandBuffer.setScissors(
            allocBuffer[VkRect2D](1, calloc).put(0, VkFactory.rect2d(gc.presenter.swapchainSize._1, gc.presenter.swapchainSize._2))
          )
          
          batch.commandBuffer.draw(vertexCount = 3, instanceCount = 1, firstVertex = 0, firstInstance = 0)
          batch.commandBuffer.endRenderPass()

          if (vkStack.graphicsQueueFamilyIndex != presentQueue) {
            //barrier from draw to present
            batch.commandBuffer
              .pipelineBarrier(
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

          batch.commandBuffer.endCommandBuffer()
        }
      }
    }

    private def setup(gc: vkStack.VkGraphicsContext): Unit = {
      import vkStack.Color
      frameBuffers = withStack { stack ?=>
        val size = gc.presenter.swapchainSize
        for (img <- gc.presenter.imageViews)
          yield disposer.track(vkStack.logicalDevice.createFrameBuffer(renderPass, stack.longs(img), size._1, size._2, 1))(
            vkStack.logicalDevice.destroyFrameBuffer
          )
      }.asInstanceOf[IArray[NativeHandle]]

      lastSwapChainId = gc.presenter.swapChain
    }

    private def createPipeline(
        // device: VkPhysicalDevice,
        logicalDevice: VkLogicalDevice.Any,
        // presentationEngine: VkPresenter,
        renderPass: VkRenderPass[logicalDevice.type]
    ): NativeHandle = withStack { stack ?=>
      val pipelineLayout = disposer.track(logicalDevice.createPipelineLayout())(logicalDevice.destroyPipelineLayout)

      val PolygonMode = VK10.VK_POLYGON_MODE_FILL
      val CullMode = VK10.VK_CULL_MODE_BACK_BIT
      val FrontFace = VK10.VK_FRONT_FACE_COUNTER_CLOCKWISE

      val pipelineCreateInfo = VkFactory
        .graphicsPipelines(1)
        .pDynamicState(VkFactory.pipelineDynamicState(stack.ints(VK10.VK_DYNAMIC_STATE_VIEWPORT, VK10.VK_DYNAMIC_STATE_SCISSOR)))
        .renderPass(renderPass.unwrap)
        .pVertexInputState(VkFactory.pipelineVertexInputState(null, null))
        .pInputAssemblyState(VkFactory.pipelineInputAssemblyState(topology = VK10.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST))
        .pViewportState(
          VkFactory.pipelineViewportState(
            viewportCount = 1,
            // viewports = allocBuffer[VkViewport](1, calloc)
            //   .width(presentationEngine.swapchainSize._1)
            //   .height(presentationEngine.swapchainSize._2)
            //   .maxDepth(1),
            scissorCount = 1,
            // scissors = allocBuffer[VkRect2D](1, calloc)
            //   .put(0, VkFactory.rect2d(presentationEngine.swapchainSize._1, presentationEngine.swapchainSize._2))
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
                .createShaderModule(loadShader("shader/triangle.vert.spv"))
                .unwrap
                .tap(l => println(f"triangle.vert shader created at 0x$l%X"))
            )
            .position(1)
            .stage(VK10.VK_SHADER_STAGE_FRAGMENT_BIT)
            .pName("main".nUtf8)
            .module(
              logicalDevice
                .createShaderModule(loadShader("shader/triangle.frag.spv"))
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
      val content = better.files.Resource.getAsStream(path).readAllBytes()
      MemoryUtil.memAlloc(content.length).put(content).flip()

    def dispose(): Unit = disposer.close()
  }
}
