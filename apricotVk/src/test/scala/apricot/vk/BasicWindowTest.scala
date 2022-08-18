package apricot
package vk

import better.files.*
import guarana.{AbstractToolkit, Stylist}
import scala.annotation.threadUnsafe
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWVulkan
import org.lwjgl.vulkan.VK11
import org.lwjgl.vulkan.EXTDebugReport
import org.lwjgl.vulkan.VK10
import java.util.concurrent.locks.LockSupport
import org.lwjgl.vulkan.VkDebugReportCallbackEXT
import org.lwjgl.vulkan.VkDebugReportCallbackCreateInfoEXT
import java.nio.LongBuffer

object BasicWindowTest {
  def main(args: Array[String]) = {
    scribe.Logger.root
      .withHandler(writer = scribe.file.FileWriter(scribe.file.PathBuilder.static(File("log.txt").path)))
      .withMinimumLevel(scribe.Level.Debug)
      .replace()

    val mainThread = Thread.currentThread
    @threadUnsafe
    lazy val engine: ApricotEngine[AbstractToolkit] = ApricotEngine(
      true,
      new AbstractToolkit {
        protected def isOnToolkitThread(): Boolean = Thread.currentThread == mainThread
        protected def runOnToolkitThread(r: () => Any): Unit = engine.onNextFrame(r())
        def getMetrics(): Stylist.Metrics = Stylist.Metrics.NoOp
      }
    )

    val vkInstance = withStack {
      require(GLFW.glfwInit(), "Failed to initialize GLFW")
      println(s"Vulkan supported by glfw: ${GLFWVulkan.glfwVulkanSupported()}")
      val glfwReqExtensions = GLFWVulkan.glfwGetRequiredInstanceExtensions()
      require(glfwReqExtensions != null, "Failed to obtained required GLFW extensions")

      VkInstance.create(
        vkVersion = VK11.VK_API_VERSION_1_1,
        enabledExtensions = (GLFWVulkan.glfwGetRequiredInstanceExtensions().toIterator(_.getStringUTF8).toIndexedSeq ++
          Seq(EXTDebugReport.VK_EXT_DEBUG_REPORT_EXTENSION_NAME)).toPointerBuffer(stackMalloc),
        enabledLayers = Seq("VK_LAYER_LUNARG_standard_validation").toPointerBuffer(stackMalloc)
      )
    }

    setupDebugCallback(vkInstance)

    val physicalDevice = withStack {
      vkInstance.enumeratePhysicalDevices().find(_.properties.deviceType() == VK10.VK_PHYSICAL_DEVICE_TYPE_DISCRETE_GPU)
    }.getOrElse(throw new IllegalStateException("No GPU provided vulkan implementation"))

    val graphicsStack = VulkanGraphicsStack(physicalDevice)
    import graphicsStack.Color, graphicsStack.Color.ColorLike
    engine.switchToGraphicsStack(graphicsStack)

    val window1 = engine.tk.update {
      val window = tools.GlfwWindow(engine, Map(GLFW.GLFW_CLIENT_API -> GLFW.GLFW_NO_API))

      window.size := (800, 600)
      window.title := "Apricot Vulkan test"
      window.visible := true

      engine.windows += window
      engine.windows(window).layers += SimpleTriangleLayer(Color.AntiqueWhite.asArgb, Color.BlueViolet.asArgb)
      window
    }

    val window2 = engine.tk.update {
      val window = tools.GlfwWindow(engine, Map(GLFW.GLFW_CLIENT_API -> GLFW.GLFW_NO_API))

      window.size := (800, 600)
      window.title := "Apricot Vulkan sub window"
      window.visible := true

      engine.windows += window
      engine.windows(window).layers += VertexAttributesTest()
      window
    }

    while true do {
      val t0 = System.nanoTime()
      GLFW.glfwPollEvents()
      engine.engineStep()
      val stn = engine.sleepTimeNanos
      val dt = System.nanoTime() - t0

      if (stn > dt) LockSupport.parkNanos((stn - dt).toLong)
    }
  }

  def setupDebugCallback(instance: VkInstance): Long = withStack {
    val debugCallback = new VkDebugReportCallbackEXT() {
      def invoke(
          flags: Int,
          objectType: Int,
          obj: Long,
          location: Long,
          messageCode: Int,
          pLayerPrefix: Long,
          pMessage: Long,
          pUserData: Long
      ): Int =
        println("ERROR OCCURED: " + VkDebugReportCallbackEXT.getString(pMessage))
        0
    }
    val callbackCreateInfo = VkDebugReportCallbackCreateInfoEXT
      .calloc(summon)
      .sType(EXTDebugReport.VK_STRUCTURE_TYPE_DEBUG_REPORT_CALLBACK_CREATE_INFO_EXT)
      .pfnCallback(debugCallback)
      .flags(EXTDebugReport.VK_DEBUG_REPORT_ERROR_BIT_EXT | EXTDebugReport.VK_DEBUG_REPORT_WARNING_BIT_EXT)
    (EXTDebugReport
      .vkCreateDebugReportCallbackEXT(instance.unwrap, callbackCreateInfo, null, _: LongBuffer))
      .returning(stackMalloc)(VK10.VK_SUCCESS.==, "failed to register callback")
      .get(0)
  }
}