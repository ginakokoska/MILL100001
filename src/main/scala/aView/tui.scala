package aView

import util._
import controller._
import controller.base.Controller
import model.gridComponent.gridBase.{BlackTurn, WhiteTurn}
import model.playerComponent.Stone

import scala.io.StdIn.readLine

class tui (controller: Controller) extends Observer {
//  controller.add(this)

  def startGame():String = {
    val welcome = "Welcome to our Game, Mill!\n" + "Players, please enter your name and select color w(hite) or b(lack):"
    welcome
  }

  def createPlayers(player1 :String, player2 :String):Unit = {
    val playerOne :Array[String] = player1.split(" ")
    controller.createPlayer1(playerOne(0), playerOne(1))
    controller.createPlayer2(player2)
  }

  def createGrid(size :String):Unit = controller.gridSize(size)

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
    println(controller.printGrid())
    true
  }
}