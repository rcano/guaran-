package apricot
package vk

import apricot.graphics.GraphicsStack
import apricot.util.directmem.{AllocContext, BasicSegmentAllocator, Pointer, Sized, Struct}
import guarana.{?, using}
import java.nio.ByteBuffer
import jdk.incubator.foreign.{MemoryAddress, MemorySegment, ResourceScope}
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.{
  KHRSwapchain,
  VK10,
  VkAttachmentDescription,
  VkAttachmentReference,
  VkMappedMemoryRange,
  VkPipelineColorBlendAttachmentState,
  VkSubpassDependency,
  VkSubpassDescription,
  VkVertexInputAttributeDescription
}
import scala.util.chaining.*

import allocators.given

class VertexAttributesTest extends Layer("vertex4") {
  private var impl: StackSpecificImpl = null
  override def draw(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
    if (impl == null || impl.vkStack != graphicsStack) {
      impl.?(_.dispose())
      impl = StackSpecificImpl(graphicsStack.asInstanceOf)
    }
    val captured = impl //capture var into a val for usage
    captured.draw(gContext.asInstanceOf)
    super.draw(graphicsStack, gContext)
  }

  private class StackSpecificImpl(val vkStack: VulkanGraphicsStack) {
    import vkStack.Color, vkStack.Color.given

    val disposer = Disposer()

    var lastSwapChainId = VK10.VK_NULL_HANDLE
    var frameBuffers: IArray[NativeHandle] = IArray.empty
    var renderPass: VkRenderPass[vkStack.logicalDevice.type] = VkRenderPass.NoInstance
    var pipeline: NativeHandle = VK10.VK_NULL_HANDLE
    val VertexDataSize = 32 // float x, y, z, w + float r, g, b, a

    def draw(vkGc: vkStack.VkGraphicsContext): Unit = if (vkGc.presenter.swapChain != lastSwapChainId) {
      if vkGc.presenter.swapChain != VK10.VK_NULL_HANDLE then dispose()
      setup(vkGc)
    }

    type VertexData = Struct {
      def x: Float; def x_=(v: Float): Unit
      def y: Float; def y_=(v: Float): Unit
      def z: Float; def z_=(v: Float): Unit
      def w: Float; def w_=(v: Float): Unit

      def r: Float; def r_=(v: Float): Unit
      def g: Float; def g_=(v: Float): Unit
      def b: Float; def b_=(v: Float): Unit
      def a: Float; def a_=(v: Float): Unit
    }
    given Sized[VertexData] = Struct.derivedSized[VertexData]
    object VertexData {
      def apply(x: Float, y: Float, z: Float, w: Float)(r: Float, g: Float, b: Float, a: Float)(using
          ctx: AllocContext
      ): Pointer[VertexData, ctx.type] = {
        val res = ctx.allocStruct[VertexData]
        res.x = x
        res.y = y
        res.z = z
        res.w = w
        res.r = r
        res.g = g
        res.b = b
        res.a = a
        res
      }
    }

    private def setup(gc: vkStack.VkGraphicsContext): Unit = withStack { stack ?=>
      renderPass = disposer.track(gc.createDefaultRenderPass())
      pipeline = createPipeline(gc)

      val vertexDataSize = Sized.of[VertexData].size * 4

      // describes the vertex buffer that we'll create
      val vertexBuffer =
        vkStack.logicalDevice.createBuffer(vertexDataSize, VK10.VK_BUFFER_USAGE_VERTEX_BUFFER_BIT, VK10.VK_SHARING_MODE_EXCLUSIVE)

      // allocate actual device memory for the buffer, to do that we need to find the device heap that is suitable
      val bufferReqs = vertexBuffer.getMemoryRequirements(stackCalloc)
      val heapIdx = vertexBuffer.findAppropriateHeap(bufferReqs, vkStack.physicalDevice, VK10.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT)

      // reserve memory of the size of the buffer (could be larger, way larger)
      val bufferMemory = disposer.track(
        vkStack.logicalDevice.allocateMemory(vertexBuffer.getMemoryRequirements(stackCalloc).size(), heapIdx)
      )(vkStack.logicalDevice.destroyMemory)

      vertexBuffer.bindToMemory(bufferMemory, 0)
      val mappedMemoryHandle =
        disposer.track(vkStack.logicalDevice.mapMemory(bufferMemory, 0, vertexDataSize, flags = 0))(vkStack.logicalDevice.unmapMemory)

      // write directly to the mapped memory
      using(new BasicSegmentAllocator {
        val memorySegment = MemoryAddress.ofLong(mappedMemoryHandle).asSegment(vertexDataSize, ResourceScope.globalScope())
      }) {
        VertexData(-0.7f, -0.7f, 0.0f, 1.0f)(1.0f, 0.0f, 0.0f, 0.0f)
        VertexData(-0.7f, 0.7f, 0.0f, 1.0f)(0.0f, 1.0f, 0.0f, 0.0f)
        VertexData(0.7f, -0.7f, 0.0f, 1.0f)(0.0f, 0.0f, 1.0f, 0.0f)
        VertexData(0.7f, 0.7f, 0.0f, 1.0f)(0.3f, 0.3f, 0.3f, 0.0f)
      }

      vkStack.logicalDevice.flushMemoryRanges(
        alloc[VkMappedMemoryRange](stackCalloc).memory(bufferMemory).offset(0).size(VK10.VK_WHOLE_SIZE).buffered(stackCalloc)
      )

      lastSwapChainId = gc.presenter.swapChain
    }

    private def createPipeline(gc: vkStack.VkGraphicsContext): NativeHandle = withStack { stack ?=>
      val pipelineLayout = disposer.track(vkStack.logicalDevice.createPipelineLayout())(vkStack.logicalDevice.destroyPipelineLayout)

      val pipelineCreateInfo = VkFactory
        .graphicsPipelines(1)
        .layout(pipelineLayout)
        .renderPass(renderPass.unwrap)
        .pVertexInputState(
          VkFactory.pipelineVertexInputState(
            VkFactory.vertexBindingDescriptor(0, Sized.of[VertexData].size, VK10.VK_VERTEX_INPUT_RATE_VERTEX).buffered(stackCalloc),
            allocBuffer[VkVertexInputAttributeDescription](2, stackCalloc)
              .put(
                0,
                VkFactory
                  .vertexAttributeDescriptor(0, 0, VK10.VK_FORMAT_R32G32B32A32_SFLOAT, summon[Pointer.FieldOffset["x", VertexData]].value)
              )
              .put(
                1,
                VkFactory
                  .vertexAttributeDescriptor(1, 0, VK10.VK_FORMAT_R32G32B32A32_SFLOAT, summon[Pointer.FieldOffset["r", VertexData]].value)
              )
          )
        )
        .pStages(
          VkFactory
            .pipelineShaderStages(2)
            .position(0)
            .stage(VK10.VK_SHADER_STAGE_VERTEX_BIT)
            .pName("main".nUtf8)
            .module(
              vkStack.logicalDevice
                .createShaderModule(loadShader("shader/intropart4/shader.vert.spv"))
                .unwrap
                .tap(l => println(f"triangle.vert shader created at 0x$l%X"))
            )
            .position(1)
            .stage(VK10.VK_SHADER_STAGE_FRAGMENT_BIT)
            .pName("main".nUtf8)
            .module(
              vkStack.logicalDevice
                .createShaderModule(loadShader("shader/intropart4/shader.frag.spv"))
                .unwrap
                .tap(l => println(f"triangle.frag shader created at 0x$l%X"))
            )
            .position(0)
        )
        .pInputAssemblyState(VkFactory.pipelineInputAssemblyState(topology = VK10.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_STRIP))
        .pViewportState(
          // we'll configure a viewport and scissor (because it's mandatory) but make the contents null, because we'll dynamically specify them later
          VkFactory.pipelineViewportState(
            viewportCount = 1,
            viewports = null,
            scissorCount = 1,
            scissors = null
          )
        )
        .pDynamicState(VkFactory.pipelineDynamicState(stack.ints(VK10.VK_DYNAMIC_STATE_VIEWPORT, VK10.VK_DYNAMIC_STATE_SCISSOR)))
        .pRasterizationState(
          VkFactory.pipelineRasterizationState(
            polygonMode = VK10.VK_POLYGON_MODE_FILL,
            cullMode = VK10.VK_CULL_MODE_BACK_BIT,
            frontFace = VK10.VK_FRONT_FACE_COUNTER_CLOCKWISE,
            lineWidth = 1.0f
          )
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

      disposer.track(vkStack.logicalDevice.createGraphicsPipeline(pipelineCreateInfo))(vkStack.logicalDevice.destroyPipeline)
    }

    private def loadShader(path: String): ByteBuffer =
      val content = better.files.Resource.getAsStream(path).readAllBytes()
      MemoryUtil.memAlloc(content.length).put(content).flip()

    def dispose(): Unit = disposer.close()
  }
}
