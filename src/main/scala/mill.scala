import aView.Gui.{Gui, Gui2, StartGui}
import aView._
import controller.RedrawGrid
import controller.base.Controller
import model.{playerComponent, _}
import model.gridComponent.gridBase.grid
import model.playerComponent.{Player, Stone}

import scala.io.StdIn.readLine
import scala.swing._


object mill  extends Frame{
  val controller = new Controller(Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
  val tui = new tui(controller)
  val gui = new StartGui(controller)
  controller.publish(new RedrawGrid)
//  val gui = new Gui()

  def main(args: Array[String]): Unit = {
    gui.wel()
//    gui.grid
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
//      if(controller.gamePlayState == WhiteTurn()) println("White please set your Stone:")
//      else if(controller.gamePlayState == BlackTurn()) println("Black please set your Stone:")
      tui.gameState()
      val pos = readLine()
      tui.moveTui(pos)
      i += 1
    }

  }
}
