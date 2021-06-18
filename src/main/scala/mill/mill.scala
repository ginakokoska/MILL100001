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
//  val controller = new Controller(Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), Grid())
//  val tui = new Tui(controller)
//  val gui = new StartGui(controller)
  val injector: Injector = Guice.createInjector(new MillModule)
  val gameState = injector.getInstance(classOf[State])
//  val controller = injector.getInstance(classOf[ControllerInterface])
//  val grid = injector.getInstance(classOf[GridInterface](Names.named("MockImpl")))



  val controller1 = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
  val tui = new Tui(controller1)
  val gui = new StartGui(controller1)
  controller1.publish(new RedrawGrid)


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

    while (!controller1.win()) {
      tui.gameState()
      val pos = readLine()
      tui.moveTui(pos)
    }

  }
}
