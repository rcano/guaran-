package guarana

import impl.SwingToolkitImpl._, Binding.{dyn, const}
import java.awt.Color
import scene._


object Pong {
  def main(args: Array[String]): Unit = {
    val scene = new Scenegraph()

    val (leftPaddlePosition, rightPaddlePosition, ballX, ballY) = scene.update { implicit ctx =>

      val paddle = Rectangle(dyn { scene.width() * 0.02 }, dyn { scene.height() * 0.20 }, Color.GREEN)

      val leftPaddlePosition = Var.autoName[Float](0.5f)
      val rightPaddlePosition = Var.autoName[Float](0.5f)

      val ball = Rectangle(dyn { scene.width() * 0.02 }, dyn { scene.height() * 0.02 }, Color.GREEN)

      val ballX = Var.autoName[Float](0.5f)
      val ballY = Var.autoName[Float](0.5f)

      val pane = AbsolutePositioningPane(layout = dyn {
          Seq(
            (scene.width() / 35, (scene.height() * leftPaddlePosition() - paddle.height() / 2).toInt) -> paddle,
            (scene.width() - (scene.width() / 35) - paddle.width().toInt, (scene.height() * rightPaddlePosition() - paddle.height() / 2).toInt) -> paddle,
            ((ballX() * scene.width() - ball.width() / 2).toInt, (ballY() * scene.height() - ball.height() / 2).toInt) -> ball,
          )
        })

      scene.rootNode := pane

      (leftPaddlePosition, rightPaddlePosition, ballX, ballY)
    }

    val window = createWindow()
    window.title = "Guaraná Pong"
    window.width = 600
    window.height = 600
    window.bind(scene)

    

    val timer = new javax.swing.Timer(20, evt => {
        scene.update { implicit ctx =>
          //left paddle
          val mouseY = scene.mouseLocation()._2.toFloat / scene.height()
          val delta = (mouseY - leftPaddlePosition()).max(-0.025f).min(0.025f)
          leftPaddlePosition += delta

          //right paddle
          rightPaddlePosition += (ballY() - rightPaddlePosition()).max(-0.0025f).min(0.0025f)
        }
      })
    timer.setRepeats(true)
    timer.setCoalesce(true)
    timer.start()
  }
}
