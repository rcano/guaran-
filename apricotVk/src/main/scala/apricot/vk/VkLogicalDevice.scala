package apricot
package vk

import java.nio.{ByteBuffer, IntBuffer, LongBuffer}
import org.lwjgl.system._
import org.lwjgl.vulkan
import org.lwjgl.vulkan.{
  KHRSwapchain,
  VK10,
  VkAttachmentDescription,
  VkBufferCreateInfo,
  VkCommandPoolCreateInfo,
  VkExtent2D,
  VkFramebufferCreateInfo,
  VkGraphicsPipelineCreateInfo,
  VkMappedMemoryRange,
  VkMemoryAllocateInfo,
  VkMemoryRequirements,
  VkPipelineLayoutCreateInfo,
  VkPushConstantRange,
  VkQueue,
  VkRenderPassCreateInfo,
  VkSemaphoreCreateInfo,
  VkShaderModuleCreateInfo,
  VkSubpassDependency,
  VkSubpassDescription,
  VkSwapchainCreateInfoKHR
}

import allocators.given
import VkFactory.opSet
import org.lwjgl.vulkan.VkFenceCreateInfo

type VkLogicalDevice[+phyDev <: VkPhysicalDevice & Singleton] = VkLogicalDevice.opaques.VkLogicalDevice[phyDev]
object VkLogicalDevice:
  object opaques:
    opaque type VkLogicalDevice[+phyDev <: VkPhysicalDevice & Singleton] <: Pointer = vulkan.VkDevice

  def apply(addr: Long, pDevice: VkPhysicalDevice, createInfo: vulkan.VkDeviceCreateInfo): VkLogicalDevice[pDevice.type] =
    vulkan.VkDevice(addr, pDevice.unwrap, createInfo).asInstanceOf

  type Any = VkLogicalDevice[VkPhysicalDevice & Singleton]

  val NoInstance = null.asInstanceOf[VkLogicalDevice[Nothing]]

  given managed[phyDev <: VkPhysicalDevice & Singleton]: Managed[VkLogicalDevice[phyDev]] with
    inline def close(p: VkLogicalDevice[phyDev]): Unit = {
      p.destroy()
    }

  extension [pDev <: VkPhysicalDevice & Singleton](instance: VkLogicalDevice[pDev])
    inline def unwrap: vulkan.VkDevice = instance.asInstanceOf
    def createCommandPool(graphicsQueueFamilyIndex: Int, flags: Int)(using MemoryStack): VkCommandPool[instance.type] =
      val createCommandPoolInfo = alloc[VkCommandPoolCreateInfo](stackCalloc)
        .sType(VK10.VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO)
        .queueFamilyIndex(graphicsQueueFamilyIndex)
        .flags(flags)
      val addr = (VK10
        .vkCreateCommandPool(instance.unwrap, createCommandPoolInfo, null, _: LongBuffer))
        .returning(stackMalloc)(VK10.VK_SUCCESS.==, "failed creating command pool")
        .get(0)
      VkCommandPool(instance, addr)

    def createRenderPass(
        attachments: VkAttachmentDescription.Buffer = null,
        dependencies: VkSubpassDependency.Buffer = null,
        subpasses: VkSubpassDescription.Buffer = null,
    )(using MemoryStack): VkRenderPass[instance.type] =
      val createInfo = alloc[VkRenderPassCreateInfo](stackCalloc).sType(VK10.VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO)
      if (attachments != null) createInfo.pAttachments(attachments)
      if (dependencies != null) createInfo.pDependencies(dependencies)
      if (subpasses != null) createInfo.pSubpasses(subpasses)
      val addr = (VK10
        .vkCreateRenderPass(instance.unwrap, createInfo, null, _: LongBuffer))
        .returning(stackMalloc)(VK10.VK_SUCCESS.==, "failed creating render pass")
        .get(0)
      VkRenderPass(instance, addr)

    def createBuffer(
        size: Long,
        usage: Int,
        sharingMode: Int = 0,
    )(using MemoryStack): VkBuffer[instance.type] =
      val createInfo = alloc[VkBufferCreateInfo](stackCalloc)
        .sType(VK10.VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO)
        .size(size)
        .usage(usage)
        .sharingMode(sharingMode)
      val addr = (VK10
        .vkCreateBuffer(instance.unwrap, createInfo, null, _: LongBuffer))
        .returning(stackMalloc)(VK10.VK_SUCCESS.==, "failed creating buffer")
        .get(0)
      VkBuffer(instance, addr)

    def getBufferMemoryRequirements(buffer: NativeHandle)(using MemoryStack): VkMemoryRequirements =
      (VK10.vkGetBufferMemoryRequirements(instance.unwrap, buffer, _)).returning(stackMalloc)

    def getDeviceQueue(graphicsQueueFamilyIndex: Int, queueIndex: Int)(using MemoryStack): VkQueue =
      VkQueue(
        (VK10.vkGetDeviceQueue(instance.unwrap, graphicsQueueFamilyIndex, queueIndex, _)).returning(stackMalloc).get(0),
        instance.unwrap
      )

    def allocateMemory(size: Long, memoryTypeIndex: Int)(using MemoryStack): NativeHandle =
      (
        VK10
          .vkAllocateMemory(
            instance.unwrap,
            alloc[VkMemoryAllocateInfo](stackCalloc)
              .sType(VK10.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO)
              .allocationSize(size)
              .memoryTypeIndex(memoryTypeIndex),
            null,
            _: LongBuffer
          )
        )
        .returning(stackMalloc)(VK10.VK_SUCCESS.==, "failed allocating memory")
        .get(0)

    def destroyMemory(handle: NativeHandle): Unit = VK10.vkFreeMemory(instance.unwrap, handle, null)

    def mapMemory(memory: MemoryAddr, offset: Long, size: Long, flags: Int)(using MemoryStack): NativeHandle =
      (VK10
        .vkMapMemory(instance.unwrap, memory, offset, size, flags, _))
        .returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed mapping memory")
        .get(0)
    def unmapMemory(memory: MemoryAddr)(using MemoryStack): Unit = VK10.vkUnmapMemory(instance.unwrap, memory)

    def flushMemoryRanges(ranges: VkMappedMemoryRange.Buffer)(using MemoryStack) =
      VkUtil.expectResult(VK10.vkFlushMappedMemoryRanges(instance.unwrap, ranges), VK10.VK_SUCCESS)

    def createPipelineLayout(
        setLayouts: LongBuffer = null,
        pushConstantRanges: VkPushConstantRange.Buffer = null
    )(using MemoryStack): NativeHandle = (
      VK10
        .vkCreatePipelineLayout(
          instance.unwrap,
          alloc[VkPipelineLayoutCreateInfo](stackCalloc)
            .sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO)
            .opSet(setLayouts, _.pSetLayouts(_))
            .opSet(pushConstantRanges, _.pPushConstantRanges(_)),
          null,
          _: LongBuffer
        )
      )
      .returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed creating pieline layout")
      .get(0)

    def destroyPipelineLayout(pipelineLayoutHandle: NativeHandle): Unit =
      VK10.vkDestroyPipelineLayout(instance.unwrap, pipelineLayoutHandle, null)

    inline def createGraphicsPipeline(args: VkGraphicsPipelineCreateInfo.Buffer, inline alloc: AllocMethod = calloc)(using
        MemoryStack
    ): NativeHandle =
      (VK10
        .vkCreateGraphicsPipelines(instance.unwrap, VK10.VK_NULL_HANDLE, args, null, _: LongBuffer))
        .returning(alloc)(VK10.VK_SUCCESS.==, "failed creating pielines")
        .get(0)

    def destroyPipeline(pipeline: NativeHandle): Unit = VK10.vkDestroyPipeline(instance.unwrap, pipeline, null)

    def createShaderModule(shaderModule: ByteBuffer)(using MemoryStack): VkShaderModule[instance.type] =
      val handle = (
        VK10
          .vkCreateShaderModule(
            instance.unwrap,
            alloc[VkShaderModuleCreateInfo](stackCalloc).sType(VK10.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO).pCode(shaderModule),
            null,
            _: LongBuffer
          )
        )
        .returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed loading shader")
        .get(0)
      VkShaderModule(instance, handle)

    def createSemaphore()(using MemoryStack): NativeHandle = (
      VK10
        .vkCreateSemaphore(
          instance.unwrap,
          alloc[VkSemaphoreCreateInfo](stackCalloc).sType(VK10.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO),
          null,
          _: LongBuffer
        )
      )
      .returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed creating semaphore")
      .get(0)

    def destroySemaphore(semaphore: NativeHandle): Unit = VK10.vkDestroySemaphore(instance.unwrap, semaphore, null)

    def createSwapchain(
        surface: NativeHandle,
        minImageCount: Int,
        imageFormat: Int,
        imageColorSpace: Int,
        imageExtent: VkExtent2D,
        imageUsage: Int,
        imageSharingMode: Int,
        queueFamilyIndices: IntBuffer,
        preTransform: Int,
        compositeAlpha: Int,
        presentMode: Int,
        oldSwapChain: NativeHandle,
        clipped: Boolean = true,
        imageArrayLayers: Int = 1,
        flags: Int = 0,
    )(using MemoryStack): NativeHandle = (
      KHRSwapchain
        .vkCreateSwapchainKHR(
          instance.unwrap,
          alloc[VkSwapchainCreateInfoKHR](stackCalloc)
            .sType(KHRSwapchain.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR)
            .flags(flags)
            .surface(surface)
            .minImageCount(minImageCount)
            .imageFormat(imageFormat)
            .imageColorSpace(imageColorSpace)
            .imageExtent(imageExtent)
            .imageUsage(imageUsage)
            .imageSharingMode(imageSharingMode)
            .pQueueFamilyIndices(queueFamilyIndices)
            .preTransform(preTransform)
            .compositeAlpha(compositeAlpha)
            .presentMode(presentMode)
            .oldSwapchain(oldSwapChain)
            .clipped(clipped)
            .imageArrayLayers(imageArrayLayers),
          null,
          _: LongBuffer
        )
      )
      .returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed creating swapchain")
      .get(0)

    def destroySwapchain(swapchain: NativeHandle): Unit = KHRSwapchain.vkDestroySwapchainKHR(instance.unwrap, swapchain, null)

    def createFrameBuffer(renderPass: VkRenderPass[instance.type], attachments: LongBuffer, width: Int, height: Int, layers: Int)(using
        MemoryStack
    ): NativeHandle =
      (
        VK10
          .vkCreateFramebuffer(
            instance.unwrap,
            alloc[VkFramebufferCreateInfo](stackCalloc)
              .sType(VK10.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO)
              .renderPass(renderPass.unwrap)
              .pAttachments(attachments)
              .width(width)
              .height(height)
              .layers(layers),
            null,
            _: LongBuffer
          )
        )
        .returning(stackMalloc)(VK10.VK_SUCCESS.==, "failed creating framebuffer")
        .get(0)

    def destroyFrameBuffer(fb: NativeHandle): Unit = VK10.vkDestroyFramebuffer(instance.unwrap, fb, null)

    def destroy(): Unit = VK10.vkDestroyDevice(unwrap, null)

    def waitIdle(): Unit = VK10.vkDeviceWaitIdle(instance.unwrap)
    def createFence(flags: Int)(using MemoryStack): NativeHandle = (VK10
      .vkCreateFence(
        instance.unwrap,
        alloc[VkFenceCreateInfo](stackCalloc).sType(VK10.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO),
        null,
        _: LongBuffer
      ))
      .returning(stackCalloc)(VK10.VK_SUCCESS.==, "failed creating fence")
      .get(0)

    def destroyFence(semaphore: NativeHandle): Unit = VK10.vkDestroyFence(instance.unwrap, semaphore, null)
    
    def waitForFence(fence: NativeHandle, nanos: Long): Unit = VK10.vkWaitForFences(instance.unwrap, fence, true, nanos)
    def waitForFences(fences: Array[NativeHandle], waitForAll: Boolean, nanos: Long): Unit =
      VK10.vkWaitForFences(instance.unwrap, fences, waitForAll, nanos)
    def waitForFences(fences: LongBuffer, waitForAll: Boolean, nanos: Long): Unit =
      VK10.vkWaitForFences(instance.unwrap, fences, waitForAll, nanos)
    def isFenceSignaled(fence: NativeHandle): Boolean = VK10.vkGetFenceStatus(instance.unwrap, fence) match {
      case VK10.VK_SUCCESS => true
      case VK10.VK_NOT_READY => false
      case other => throw new IllegalStateException(VkUtil.describeResult(other))
    }
    def resetFence(fence: NativeHandle): Unit = VkUtil.expectResult(VK10.vkResetFences(instance.unwrap, fence), VK10.VK_SUCCESS)
