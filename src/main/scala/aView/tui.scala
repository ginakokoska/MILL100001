package aView

import controller._
import model.Stone

import scala.io.StdIn.readLine

class tui (controller: Controller) {
  def startGame:Unit = {
    println("Welcome to our Game, Mill!")
    println("Player one, please enter your name and select color w(hite) or b(lack):")
    val playerOneName = readLine()
    val playerOneColor = readLine()
    controller.createPlayer1(playerOneName, playerOneColor)

    println("Now Player two:")
    val playerTwoName = readLine()
    controller.createPlayer2(playerTwoName)

    controller.sayHello()
    controller.createGrid()
    controller.printGrid()
  }
}
