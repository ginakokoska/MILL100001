import controller.Controller
import aView._
import model._

import scala.io.StdIn.readLine


object mill {
  val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
  val tui = new tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    println(tui.startGame)
    val playerOne = readLine()
    val playerTwo = readLine()
    tui.createPlayers(playerOne, playerTwo)
//    tui.update

    println("Choose your Grid Size(1,2,3):")
    val gridSize = readLine()
    tui.createGrid(gridSize)

    tui.update

//    if (controller.player1.color.equals(Stone.white)) {
//      println(controller.player1.name + " please set your Stone!")
//    } else {
//      println(controller.player2.name + " please set your Stone!")
//    }
    var i = 0
    while (i < 10) {
      if(controller.gamePlayState == WhiteTurn()) println("White please set your Stone:")
      else println("Black please set your Stone:")

      val pos = readLine()
      tui.moveTui(pos)
      i += 1
    }

  }
}
