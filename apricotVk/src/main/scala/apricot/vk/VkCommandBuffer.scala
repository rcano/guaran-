package apricot
package vk

import org.lwjgl.system.{MemoryStack, Pointer}
import org.lwjgl.vulkan
import org.lwjgl.vulkan.{
  VK10,
  VkBufferMemoryBarrier,
  VkClearColorValue,
  VkCommandBufferBeginInfo,
  VkCommandBufferInheritanceInfo,
  VkDeviceGroupRenderPassBeginInfo,
  VkImageMemoryBarrier,
  VkImageSubresourceRange,
  VkMemoryBarrier,
  VkRenderPassAttachmentBeginInfoKHR,
  VkRenderPassBeginInfo,
  VkRenderPassSampleLocationsBeginInfoEXT
}
import allocators.given
import VkFactory.opSet
import org.lwjgl.vulkan.VkViewport
import org.lwjgl.vulkan.VkRect2D

type VkCommandBuffer = VkCommandBuffer.opaques.VkCommandBuffer
object VkCommandBuffer {
  object opaques:
    opaque type VkCommandBuffer <: Pointer = vulkan.VkCommandBuffer

  def apply(addr: MemoryAddr, vkDevice: VkLogicalDevice.Any): VkCommandBuffer = vulkan.VkCommandBuffer(addr, vkDevice.unwrap).asInstanceOf

  extension (buff: VkCommandBuffer) {
    inline def unwrap: vulkan.VkCommandBuffer = buff.asInstanceOf

    inline def beginCommandBuffer(flags: Int, inheritanceInfo: VkCommandBufferInheritanceInfo = null)(using MemoryStack): Unit =
      VkUtil.expectResult(
        VK10.vkBeginCommandBuffer(
          buff.unwrap,
          alloc[VkCommandBufferBeginInfo](stackCalloc)
            .sType(VK10.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO)
            .flags(flags)
            .opSet(inheritanceInfo, _.pInheritanceInfo(_))
        ),
        VK10.VK_SUCCESS
      )

    inline def endCommandBuffer(): Unit = VkUtil.expectResult(
      VK10.vkEndCommandBuffer(buff.unwrap),
      VK10.VK_SUCCESS
    )

    inline def draw(vertexCount: Int, instanceCount: Int, firstVertex: Int, firstInstance: Int): Unit =
      VK10.vkCmdDraw(buff.unwrap, vertexCount, instanceCount, firstVertex, firstInstance)

    inline def beginQuery(queryPool: Long, query: Int, flags: Int): Unit = VK10.vkCmdBeginQuery(buff.unwrap, queryPool, query, flags)

    inline def bindPipeline(pipeline: NativeHandle, bindPoint: Int): Unit = VK10.vkCmdBindPipeline(buff.unwrap, bindPoint, pipeline)

    inline def bindVertexBuffer(buffer: VkBuffer.Any): Unit = VK10.vkCmdBindVertexBuffers(buff.unwrap, 0, Array(buffer.unwrap), Array(0L))
    inline def bindVertexBuffers(buffers: IArray[VkBuffer.Any], firstBinding: Int, offsets: IArray[Long]): Unit =
      VK10.vkCmdBindVertexBuffers(buff.unwrap, firstBinding, buffers.asInstanceOf[Array[Long]], offsets.asInstanceOf[Array[Long]])

    inline def beginRenderPass(
        renderPass: VkRenderPass[VkLogicalDevice.Any],
        frameBuffer: NativeHandle,
        renderArea: vulkan.VkRect2D,
        clearValues: vulkan.VkClearValue.Buffer,
        contents: Int,
        next: (VkDeviceGroupRenderPassBeginInfo | VkRenderPassAttachmentBeginInfoKHR | VkRenderPassSampleLocationsBeginInfoEXT)*
    )(using MemoryStack): Unit = {
      val info = alloc[VkRenderPassBeginInfo](stackCalloc)
        .sType(VK10.VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO)
        .renderPass(renderPass.unwrap)
        .framebuffer(frameBuffer)
        .renderArea(renderArea)
        .pClearValues(clearValues)
      val pnext = next.reduceOption { (l, r) =>
        l match {
          case s: VkDeviceGroupRenderPassBeginInfo => s.pNext(r.address)
          case s: VkRenderPassAttachmentBeginInfoKHR => s.pNext(r.address)
          case s: VkRenderPassSampleLocationsBeginInfoEXT => s.pNext(r.address)
        }
        r
      }.orNull
      if (pnext != null) info.pNext(pnext.address)
      VK10.vkCmdBeginRenderPass(buff.unwrap, info, contents)
    }

    inline def endRenderPass(): Unit = VK10.vkCmdEndRenderPass(buff.unwrap)

    inline def pipelineBarrier(
        srcStageMask: Int,
        dstStageMask: Int,
        dependencyFlags: Int,
        memoryBarriers: VkMemoryBarrier.Buffer,
        bufferMemoryBarriers: VkBufferMemoryBarrier.Buffer,
        imageMemoryBarriers: VkImageMemoryBarrier.Buffer,
    )(using MemoryStack): Unit = {
      VK10.vkCmdPipelineBarrier(
        buff.unwrap,
        srcStageMask,
        dstStageMask,
        dependencyFlags,
        memoryBarriers,
        bufferMemoryBarriers,
        imageMemoryBarriers
      )
    }

    inline def clearColorImage(imageHandle: NativeHandle, imageLayout: Int, color: VkClearColorValue, range: VkImageSubresourceRange) =
      VK10.vkCmdClearColorImage(buff.unwrap, imageHandle, imageLayout, color, range)
    inline def clearColorImage(
        imageHandle: NativeHandle,
        imageLayout: Int,
        color: VkClearColorValue,
        ranges: VkImageSubresourceRange.Buffer
    ) =
      VK10.vkCmdClearColorImage(buff.unwrap, imageHandle, imageLayout, color, ranges)

    inline def setViewport(width: Float, height: Float, x: Float = 0, y: Float = 0, minDepth: Float = 0, maxDepth: Float = 1)(using
        MemoryStack
    ): Unit =
      VK10.vkCmdSetViewport(
        buff.unwrap,
        0,
        allocBuffer[VkViewport](1, stackCalloc).width(width).height(height).x(x).y(y).minDepth(minDepth).maxDepth(maxDepth)
      )

    inline def setScissors(scissor: VkRect2D.Buffer): Unit = VK10.vkCmdSetScissor(buff.unwrap, 0, scissor)
  }
}
