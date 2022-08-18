package apricot
package vk

import guarana.util.cfor
import java.nio.{FloatBuffer, IntBuffer, LongBuffer}
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan._
import allocators.given

/** Collection of vulkan parameters factory methods */
object VkFactory {

  extension [A, B](a: A)
    inline def opSet(b: B, inline f: (A, B) => Any): A =
      if b != null then f(a, b)
      a

  inline def pipelineInputAssemblyState(
      topology: Int = 0,
      primitiveRestartEnabled: Boolean = false,
      flags: Int = 0,
      inline allocMethod: AllocMethod = stackCalloc
  )(using MemoryStack) =
    alloc[VkPipelineInputAssemblyStateCreateInfo](allocMethod)
      .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO)
      .topology(topology)
      .primitiveRestartEnable(primitiveRestartEnabled)
      .flags(flags)

  inline def pipelineRasterizationState(
      depthClampEnable: Boolean = false,
      rasterizerDiscardEnable: Boolean = false,
      polygonMode: Int = 0,
      cullMode: Int = 0,
      frontFace: Int = 0,
      depthBiasEnable: Boolean = false,
      depthBiasConstantFactor: Float = 0,
      depthBiasClamp: Float = 0,
      depthBiasSlopeFactor: Float = 0,
      lineWidth: Float = 0,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkPipelineRasterizationStateCreateInfo](allocMethod)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO)
    .depthClampEnable(depthClampEnable)
    .rasterizerDiscardEnable(rasterizerDiscardEnable)
    .polygonMode(polygonMode)
    .cullMode(cullMode)
    .frontFace(frontFace)
    .depthBiasEnable(depthBiasEnable)
    .depthBiasConstantFactor(depthBiasConstantFactor)
    .depthBiasClamp(depthBiasClamp)
    .depthBiasSlopeFactor(depthBiasSlopeFactor)
    .lineWidth(lineWidth)

  inline def pipelineColorBlendState(
      logicOpEnable: Boolean = false,
      logicOp: Int = 0,
      attachments: VkPipelineColorBlendAttachmentState.Buffer = null,
      blendConstants: FloatBuffer = null,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkPipelineColorBlendStateCreateInfo](calloc)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO)
    .logicOpEnable(logicOpEnable)
    .logicOp(logicOp)
    .opSet(attachments, _.pAttachments(_))
    .opSet(blendConstants, _.blendConstants(_))

  inline def pipelineViewportState(
      viewportCount: Int = 0,
      viewports: VkViewport.Buffer = null,
      scissorCount: Int = 0,
      scissors: VkRect2D.Buffer = null,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkPipelineViewportStateCreateInfo](allocMethod)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO)
    .viewportCount(viewportCount)
    .opSet(viewports, _.pViewports(_))
    .scissorCount(scissorCount)
    .opSet(scissors, _.pScissors(_))

  def pipelineDynamicState(
      dynamicStates: IntBuffer,
  )(using MemoryStack) = alloc[VkPipelineDynamicStateCreateInfo](stackCalloc)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO)
    .pDynamicStates(dynamicStates)

  def pipelineDepthStencilState(
      depthTestEnable: Boolean = false,
      depthWriteEnable: Boolean = false,
      depthCompareOp: Int = 0,
      depthBoundsTestEnable: Boolean = false,
      stencilTestEnable: Boolean = false,
      maxDepthBounds: Float = 0,
      minDepthBounds: Float = 0,
  )(using MemoryStack) = alloc[VkPipelineDepthStencilStateCreateInfo](stackCalloc)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO)
    .depthTestEnable(depthTestEnable)
    .depthWriteEnable(depthWriteEnable)
    .depthCompareOp(depthCompareOp)
    .depthBoundsTestEnable(depthBoundsTestEnable)
    .stencilTestEnable(stencilTestEnable)
    .maxDepthBounds(maxDepthBounds)
    .minDepthBounds(minDepthBounds)

  inline def pipelineMultisampleState(
      rasterizationSamples: Int = 0,
      sampleShadingEnable: Boolean = false,
      minSampleShading: Float = 0,
      sampleMask: IntBuffer = null,
      alphaToCoverageEnable: Boolean = false,
      alphaToOneEnable: Boolean = false,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkPipelineMultisampleStateCreateInfo](allocMethod)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO)
    .rasterizationSamples(rasterizationSamples)
    .sampleShadingEnable(sampleShadingEnable)
    .minSampleShading(minSampleShading)
    .opSet(sampleMask, _.pSampleMask(_))
    .alphaToCoverageEnable(alphaToCoverageEnable)
    .alphaToOneEnable(alphaToOneEnable)

  inline def pipelineVertexInputState(
      vertexBindingDescriptions: VkVertexInputBindingDescription.Buffer,
      vertexAttributeDescriptions: VkVertexInputAttributeDescription.Buffer,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkPipelineVertexInputStateCreateInfo](allocMethod)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO)
    .pVertexBindingDescriptions(vertexBindingDescriptions)
    .pVertexAttributeDescriptions(vertexAttributeDescriptions)

  inline def vertexBindingDescriptor(binding: Int, stride: Int, inputRate: Int)(using MemoryStack) =
    alloc[VkVertexInputBindingDescription](stackCalloc)
      .binding(binding)
      .stride(stride)
      .inputRate(inputRate)

  inline def vertexAttributeDescriptor(location: Int, binding: Int, format: Int, offset: Int)(using MemoryStack) =
    alloc[VkVertexInputAttributeDescription](stackCalloc)
      .location(location)
      .binding(binding)
      .format(format: Int)
      .offset(offset)

  def pipelineShaderStage(
      stage: Int,
      module: NativeHandle,
      name: String,
      specializationInfo: VkSpecializationInfo = null,
      flags: Int = 0,
  )(using MemoryStack) = alloc[VkPipelineShaderStageCreateInfo](stackCalloc)
    .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO)
    .stage(stage)
    .module(module)
    .pName(name.toNative(stackCalloc))
    .opSet(specializationInfo, _.pSpecializationInfo(_))

  def queueSubmit(
      waitSemaphoreCount: Int,
      waitSemaphores: LongBuffer,
      waitDstStageMask: IntBuffer,
      commandBuffers: PointerBuffer,
      signalSemaphores: LongBuffer,
  )(using MemoryStack) = alloc[VkSubmitInfo](stackCalloc)
    .sType(VK10.VK_STRUCTURE_TYPE_SUBMIT_INFO)
    .waitSemaphoreCount(waitSemaphoreCount)
    .pWaitSemaphores(waitSemaphores)
    .pWaitDstStageMask(waitDstStageMask)
    .pCommandBuffers(commandBuffers)
    .pSignalSemaphores(signalSemaphores)

  def queuePresent(
      waitSemaphores: LongBuffer,
      swapChainCount: Int,
      swapChains: LongBuffer,
      imageIndices: IntBuffer,
      results: IntBuffer = null,
  )(using MemoryStack) = alloc[VkPresentInfoKHR](stackCalloc)
    .sType(KHRSwapchain.VK_STRUCTURE_TYPE_PRESENT_INFO_KHR)
    .pWaitSemaphores(waitSemaphores)
    .swapchainCount(swapChainCount)
    .pSwapchains(swapChains)
    .pImageIndices(imageIndices)
    .pResults(results)

  def clearColor(red: Float, green: Float, blue: Float, alpha: Float)(using MemoryStack) = alloc[VkClearColorValue](stackMalloc)
    .float32(0, red)
    .float32(1, green)
    .float32(2, blue)
    .float32(3, alpha)

  inline def componentMapping(
      r: Int = VK10.VK_COMPONENT_SWIZZLE_IDENTITY,
      g: Int = VK10.VK_COMPONENT_SWIZZLE_IDENTITY,
      b: Int = VK10.VK_COMPONENT_SWIZZLE_IDENTITY,
      a: Int = VK10.VK_COMPONENT_SWIZZLE_IDENTITY,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkComponentMapping](allocMethod).r(r).g(g).b(b).a(a)

  inline def imageSubresourceRange(
      aspectMask: Int,
      baseMipLevel: Int,
      levelCount: Int,
      baseArrayLayer: Int,
      layerCount: Int,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkImageSubresourceRange](allocMethod)
    .aspectMask(aspectMask)
    .baseMipLevel(baseMipLevel)
    .levelCount(levelCount)
    .baseArrayLayer(baseArrayLayer)
    .layerCount(layerCount)

  inline def imageMemoryBarriers(count: Int, inline allocMethod: AllocMethod = stackCalloc) =
    val res = allocBuffer[VkImageMemoryBarrier](count, allocMethod)
    cfor(0, _ < count) { i =>
      res.position(i)
      res.sType(VK10.VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER)
      i + 1
    }
    res.position(0)
    res.limit(count)
    res

  inline def graphicsPipelines(count: Int, inline allocMethod: AllocMethod = stackCalloc) =
    val res = allocBuffer[VkGraphicsPipelineCreateInfo](count, allocMethod)
    cfor(0, _ < count) { i =>
      res.position(i)
      res.sType(VK10.VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO)
      i + 1
    }
    res.position(0)
    res.limit(count)
    res

  inline def pipelineShaderStages(count: Int, inline allocMethod: AllocMethod = stackCalloc) =
    val res = allocBuffer[VkPipelineShaderStageCreateInfo](count, allocMethod)
    cfor(0, _ < count) { i =>
      res.position(i)
      res.sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO)
      i + 1
    }
    res.position(0)
    res.limit(count)
    res

  inline def imageView(
      image: NativeHandle,
      viewType: Int,
      format: Int,
      components: VkComponentMapping,
      subresourceRange: VkImageSubresourceRange,
      flags: Int = 0,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkImageViewCreateInfo](allocMethod)
    .sType(VK10.VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO)
    .image(image)
    .viewType(viewType)
    .format(format)
    .components(components)
    .subresourceRange(subresourceRange)

  inline def rect2d(
      width: Int,
      height: Int,
      offsetX: Int = 0,
      offsetY: Int = 0,
      inline allocMethod: AllocMethod = stackCalloc
  ) = alloc[VkRect2D](allocMethod).offset(_.x(offsetX).y(offsetY)).extent(_.width(width).height(height))

  // def template(
  // )(using MemoryStack) = alloc[](stackCalloc).sType(VK10.)
}
