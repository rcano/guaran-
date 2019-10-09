package guarana

import impl.SwingToolkitImpl._, Binding.{dyn, const}
import java.awt.Color
import scene._
import scala.util.chaining._


object Pong {

  val PaddleMoveSpeed = 0.8f / 50
  val MaxBallSpeed = 1.1f / 50
  val PaddleMarging = 0.028f
  val PaddleWidth = 0.02f
  val PaddleHeight = 0.2f

  def main(args: Array[String]): Unit = {
    val scene = new Scenegraph()

    val (leftPaddlePosition, rightPaddlePosition, ballX, ballY) = scene.update { implicit ctx =>

      val background = Rectangle(dyn(scene.width()), dyn(scene.height()), Color.BLACK)
      val paddle = Rectangle(dyn { scene.width() * PaddleWidth }, dyn { scene.height() * PaddleHeight }, Color.GREEN)

      val leftPaddlePosition = Var.autoName[Float](0.5f)
      val rightPaddlePosition = Var.autoName[Float](0.5f)

      val ball = Rectangle(dyn { scene.width() * 0.02 }, dyn { scene.height() * 0.02 }, Color.GREEN)

      val ballX = Var.autoName[Float](0.5f)
      val ballY = Var.autoName[Float](0.5f)

      val pane = AbsolutePositioningPane(layout = dyn {
          Seq(
            (0, 0) -> background,
            ((scene.width() * PaddleMarging).toInt, (scene.height() * leftPaddlePosition() - paddle.height() / 2).toInt) -> paddle,
            (scene.width() - (scene.width() * PaddleMarging).toInt - paddle.width().toInt, (scene.height() * rightPaddlePosition() - paddle.height() / 2).toInt) -> paddle,
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

    var ballSpeed = MaxBallSpeed / 3
    var ballDirection = 330

    def clampSpeed(speed: Float) = if (MaxBallSpeed - speed.abs < 0.0001) MaxBallSpeed * speed.sign
      else if (speed.abs < 0.0001) 0f
      else speed

    val timer = new javax.swing.Timer(20, evt => {
        scene.update { implicit ctx =>
          //left paddle
          val mouseY = scene.mouseLocation()._2.toFloat / scene.height()
          val delta = (mouseY - leftPaddlePosition()).max(-PaddleMoveSpeed).min(PaddleMoveSpeed)
          leftPaddlePosition += delta

          //right paddle
          rightPaddlePosition += (ballY() - rightPaddlePosition()).max(-PaddleMoveSpeed).min(PaddleMoveSpeed)

          //ball physics
          ballX += clampSpeed((ballSpeed * math.cos(math.toRadians(ballDirection))).toFloat)
          ballY += clampSpeed((ballSpeed * math.sin(math.toRadians(ballDirection))).toFloat)

          def bounceY() = ballDirection = (ballDirection match {
             case 270 => 90
             case 90 => 270
             case _ => 360 - ballDirection
            })
          def bounceX() = ballDirection = (ballDirection match {
             case 90 => 270
             case 270 => 90
             case _ => 180 - ballDirection
            })

          if (ballY() <= 0 || ballY() >= 1) bounceY()
          if (ballX() >= PaddleMarging && ballX() <= (PaddleMarging + PaddleWidth)) bounceX()
          if (ballX() >= (1 - PaddleMarging - PaddleWidth) && ballX() <= (1 - PaddleMarging)) bounceX()
          if (ballDirection < 0) ballDirection = ((360 + ballDirection) % 360).tap(d => println(s"Adjusting to $d"))
//          if (ballX())
        }
      })
    timer.setRepeats(true)
    timer.setCoalesce(true)
    timer.start()
  }
}
