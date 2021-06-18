package mill.aView

import com.google.inject.name.Names
import com.google.inject.{Guice, Injector}
import mill.MillModule
import mill.controller.ControllerInterface
import mill.util._
import mill.controller.base.Controller
import mill.model.gridComponent.GridInterface
import mill.model.gridComponent.gridBase.{BlackTurn, WhiteTurn}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector


class Tui(controller: ControllerInterface) extends Observer {

  val injector: Injector = Guice.createInjector(new MillModule)

  def startGame():String = {
    val welcome = "Welcome to our Game, Mill!\n" + "Players, please enter your name and select color w(hite) or b(lack):"
    welcome
  }

  def createPlayers(player1 :String, player2 :String):Unit = {
    val playerOne :Array[String] = player1.split(" ")
    controller.createPlayer1(playerOne(0), playerOne(1))
    controller.createPlayer2(player2)
  }

  def createGrid(size :String):Unit = {
    size match {
      case "1" => controller.grid.gridOutSquare(injector.instance[GridInterface](Names.named("Out")))
      case "2" => controller.grid.gridOutMidSquare(injector.instance[GridInterface](Names.named("Mid")))
      case "3" => controller.grid.createFullGrid(injector.instance[GridInterface](Names.named("Full")))
      case _ =>
    }

  }

  def moveTui (pos :String) :Unit = {
    controller.moveController(pos)
  }

  def gameState(): Unit = {
    if(controller.gamePlayState == WhiteTurn()) println("White please set your Stone:")
    else if(controller.gamePlayState == BlackTurn()) println("Black please set your Stone:")
    else println("")
  }

  def stoneSet(pos :String, act :String): Unit = {
    println("Stone " + act + pos)
  }

  override def update :Boolean = {
//    println(startGame())
    println(controller.sayHello())
    //println(controller.printGrid())
    true
  }
}