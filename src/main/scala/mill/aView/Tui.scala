package mill.aView


import com.google.inject.{Guice, Injector}
import mill.MillModule
import mill.aView.Gui.{Board, StartGui}
import mill.controller.ControllerInterface
import mill.util._
import mill.model.gridComponent.gridBase.{BlackTurn, WhiteTurn}

/*
  This class creates the TUI.
 */

class Tui(controller: ControllerInterface) extends Observer {

  val injector: Injector = Guice.createInjector(new MillModule)

  def startGame():String = {
    val welcome = "Welcome to our game, Mill!\n" + "Players, please enter your name and select color w(hite) or b(lack):"
    welcome
  }

  def createPlayers(player1 :String, player2 :String):Unit = {
    if (player1.contains(" ")) {
      val playerOne :Array[String] = player1.split(" ")
      controller.createPlayer1(playerOne(0), playerOne(1))
      controller.createPlayer2(player2)
    }
    else {
      controller.createPlayer1(player1, "w")
      controller.createPlayer2(player2)
    }
  }

  def createGrid(size :String):Unit = {
    size match {
      case "1" => controller.grid.gridList = controller.grid.gridOutSquare()
      case "2" => controller.grid.gridList = controller.grid.gridOutMidSquare()
      case _ => controller.grid.gridList = controller.grid.createFullGrid()
    }
    StartGui(controller).board()
  }

  def moveTui (pos :String) :Unit = {
    controller.moveController(pos)
    //if(hat funktioniert)
    Gui.ValidMove().TuiToGui(pos, controller)
  }

  def gameState(): Unit = {
    if(controller.gamePlayState == WhiteTurn()) println("White please set your stone:")
    else if(controller.gamePlayState == BlackTurn()) println("Black please set your stone:")
    else println("")
  }

  def stoneSet(pos :String, act :String): Unit = {
    println("Stone " + act + pos)
  }

  override def update :Boolean = {
    println(controller.sayHello())
    println(controller.printGrid())
    true
  }
}