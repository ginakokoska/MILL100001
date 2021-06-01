package controller

import model.{GamePlay, grid, Player, Stone, WhiteTurn}
import util._

class Controller(var player1: Player, var player2: Player, var grid: grid) extends Observable {
  private val undoManager = new UndoManager
  var gamePlayState = new GamePlay(new WhiteTurn).state

  def createPlayer1(name: String, tmpColor: String):Unit = {
    if(tmpColor == "b") player1 = new Player(name,Stone.black)
    else player1 = new Player(name,Stone.white)
    player1.fillStone()
    notifyObservers
  }

  def createPlayer2(name: String):Unit = {
    if(player1.color == Stone.white) player2 = Player(name,Stone.black)
    else player2 = Player(name,Stone.white)
    player2.fillStone()
    notifyObservers
  }

  def sayHello(): String = player1.playerToString(player1, player2)

  def gridSize(gridSize: String): Unit = {
    gridSize match {
      case "1" => grid.gridList = grid.gridOutSquare()
      case "2" => grid.gridList = grid.gridOutMidSquare()
      case _ => grid.gridList = grid.createFullGrid()
    }
    notifyObservers
  }

  def printGrid():String = grid.printGrid

  def moveController(pos :String) :Unit = {
    undoManager.doStep(new SetCommand(this, pos))
    notifyObservers
  }
}