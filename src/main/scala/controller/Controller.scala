package controller

import model.{GamePlay, Grid, Player, Stone, WhiteTurn}
import util._

class Controller(var player1: Player, var player2: Player, var grid: Grid) extends Observable {
  private val undoManager = new UndoManager
  var gamePlayState = new GamePlay(new WhiteTurn).state

  def createPlayer1(name: String, tmpColor: String):Unit = {
    if(tmpColor == "b") player1 = new Player(name,Stone.black)  //Template? abstract Class: Player; SubMethod: player1
    else player1 = new Player(name,Stone.white)
    notifyObservers
  }

  def createPlayer2(name: String):Unit = {
    if(player1.color == Stone.white) player2 = Player(name,Stone.black)
    else player2 = Player(name,Stone.white)
    notifyObservers
  }

  def sayHello(): String = player1.playerToString(player1, player2)

  def gridSize(gridSize: String): Unit = {
    //command Pattern
    undoManager.doStep(new SetCommand(gridSize, this, "", ' ', ' '))
    notifyObservers
  }

  def printGrid():String = grid.printGrid

  def moveController(pos :String) :Unit = {
    val posArray :Array[String] = pos.split(" ")
    undoManager.doStep(new SetCommand("", this, posArray(0),posArray(1).charAt(0), posArray(1).charAt(1)))

    gamePlayState = gamePlayState.handle(posArray(0), posArray(1).charAt(0), posArray(1).charAt(1), grid)
    notifyObservers
  }
}