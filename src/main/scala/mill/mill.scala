package mill

import com.google.inject.{Guice, Injector}
import controller.RedrawGrid
import controller.base.Controller
import model.gridComponent.State
import model.gridComponent.gridBase.Grid
import model._
import aView._
import aView.Gui._

import scala.io.StdIn.readLine
import scala.swing.Frame

object mill  extends Frame{
  val injector: Injector = Guice.createInjector(new MillModule)
  val gameState: State = injector.getInstance(classOf[State])

  val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
  val tui = new Tui(controller)
  val gui: StartGui = StartGui(controller)
  controller.publish(new RedrawGrid)


  def main(args: Array[String]): Unit = {
    gui.wel()

    println(tui.startGame())
    val playerOne = readLine()
    val playerTwo = readLine()
    tui.createPlayers(playerOne, playerTwo)

    println("Choose your Grid Size(1,2,3):")
    val gridSize = readLine()
    tui.createGrid(gridSize)
    tui.update

    while (!controller.win()) {
      tui.gameState()
      val pos = readLine()
      tui.moveTui(pos)
    }
  }
}
