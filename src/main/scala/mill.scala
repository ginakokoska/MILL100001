import aView.Gui.{Gui, StartGui}
import aView._
import controller.RedrawGrid
import controller.base.Controller
import model.{playerComponent, _}
import model.gridComponent.gridBase.Grid
import model.playerComponent.{Player, Stone}

import scala.io.StdIn.readLine
import scala.swing._


object mill  extends Frame{
  val controller = new Controller(Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), Grid())
  val tui = new Tui(controller)
  val gui = new StartGui(controller)
  controller.publish(new RedrawGrid)


  def main(args: Array[String]): Unit = {
    gui.wel()

    println(tui.startGame)
    val playerOne = readLine()
    val playerTwo = readLine()
    tui.createPlayers(playerOne, playerTwo)

    println("Choose your Grid Size(1,2,3):")
    val gridSize = readLine()
    tui.createGrid(gridSize)

    tui.update

    var i = 0
    while (!controller.win()) {
      tui.gameState()
      val pos = readLine()
      tui.moveTui(pos)
      i += 1
    }

  }
}
