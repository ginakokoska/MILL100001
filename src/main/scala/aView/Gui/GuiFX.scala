package aView.Gui


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.ButtonType.Apply.text
import scalafx.scene.control.{TextField, TextInputDialog}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.{Cyan, DodgerBlue, PaleGreen, Red, SeaGreen}
import scalafx.scene.paint._
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalafx.Includes._

import scala.reflect.internal.util.NoPosition.{end, start}

object GuiFX extends JFXApp {
//  override def main(args: Array[String]): Unit = {
    stage = new PrimaryStage {
      title = "ScalaFX Hello World"
      scene = new Scene {
        fill = Color.White

          val rectangle = new Rectangle { fill = Color.web("RED", 0.5d) }
          def update(): Unit = {
            rectangle.x = 25
            rectangle.y = 25
            rectangle.width = 100
            rectangle.height = 100
          }

        content = new Rectangle {
          x = 25
          y = 25
          width = 100
          height = 100
          stroke = Color.web("BLACK", 0.5d)
        }
//        content = new HBox {
//          padding = Insets(20)
//          children =
//            Seq(
//            new Text {
//              text = "Hello "
//              style = "-fx-font-size: 48pt"
//              fill = new LinearGradient(
//                endX = 0,
//                stops = Stops(PaleGreen, SeaGreen))
//            },
//            new Text {
//              text = "World"
//              style = "-fx-font-size: 48pt"
//              fill = new LinearGradient(
//                endX = 0,
//                stops = Stops(Cyan, DodgerBlue)
//              )
//              effect = new DropShadow {
//                color = DodgerBlue
//                radius = 25
//                spread = 0.25
//              }
//            }
//          )
          val d = new TextInputDialog(defaultValue = "player1") {
            initOwner(stage)
            title = "Welcome to Mill"
            headerText = "Welcome to Mill"
            contentText = "Player 1 please enter your name: "
          }
          val d2 = new TextInputDialog(defaultValue = "player 2") {
            initOwner(stage)
            contentText = "Player 2 please enter your name: "

          }
          val res = d.showAndWait()
          val res2 = d2.showAndWait()
        }
      }
//    }
//  }
}
