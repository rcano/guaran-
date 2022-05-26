package apricot

import apricot.tools.*
import guarana.*
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.ConsoleReporter
import scala.concurrent.duration.*

// @main def InputManagerPerfTest: Unit = {
//   val tk = new AbstractToolkit {
//     def isOnToolkitThread() = true
//     def runOnToolkitThread(f: () => Any): Unit = f()
//   }

//   val posX = Var.autoName(0.0)
//   val posY = Var.autoName(0.0)

//   val inputManager = GlfwKeyboardInputManager()
//   import inputManager.syntax.{*, given}
  
//   inputManager.recordCombination(matchInput(GlfwInput.Keyboard.Down).↓)(_ => tk.update { posY += 10.0 })
//   inputManager.recordCombination(matchInput(GlfwInput.Keyboard.Up).↓)(_ => tk.update { posY -= 10.0 })
//   inputManager.recordCombination(matchInput(GlfwInput.Keyboard.Left).↓)(_ => tk.update { posX -= 10.0 })
//   inputManager.recordCombination(matchInput(GlfwInput.Keyboard.Right).↓)(_ => tk.update { posX += 10.0 })

//   val metrics = MetricRegistry()
//   val eventsMeter = metrics.meter("events").nn
//   ConsoleReporter.forRegistry(metrics).nn.build.nn.start(5, SECONDS)

//   val random = scala.util.Random
//   while (true) {
//     inputManager.dispatch(
//       KeyEvent(
//         (262 + random.nextInt(4)).asInstanceOf[GlfwInput.Keyboard.Key],
//         InputAction.Repeated,
//         0.asInstanceOf[GlfwInput.Keyboard.ModKeys],
//         123
//       )
//     )
//     eventsMeter.mark()
//   }
// }
