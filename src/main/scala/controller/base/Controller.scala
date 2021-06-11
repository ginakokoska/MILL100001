package controller.base

import model.{playerComponent, _}
import model.gridComponent.gridBase.{GamePlay, Grid, WhiteTurn}
import model.playerComponent.{Player, Stone, StoneState}
import controller.{ControllerInterface, PlayerCreated, RedrawGrid}
import util._

import scala.swing.Publisher


class Controller(var player1: Player, var player2: Player, var grid: Grid) extends ControllerInterface with Publisher {
  private val undoManager = new UndoManager
  var gamePlayState = new GamePlay(new WhiteTurn).state

  override def createPlayer1(name: String, tmpColor: String):Unit = {
    if(tmpColor == "b") player1 = new Player(name,Stone.black)
    else player1 = new Player(name,Stone.white)
    player1.fillStone()
    publish(new PlayerCreated)
  }

  override def createPlayer2(name: String):Unit = {
    if(player1.color == Stone.white) player2 = playerComponent.Player(name,Stone.black)
    else player2 = playerComponent.Player(name,Stone.white)
    player2.fillStone()
    publish(new PlayerCreated)
  }

  override def sayHello(): String = player1.playerToString(player1, player2)

  override def gridSize(gridSize: String): Unit = {
    gridSize match {
      case "1" => grid.gridList = grid.gridOutSquare()
      case "2" => grid.gridList = grid.gridOutMidSquare()
      case _ => grid.gridList = grid.createFullGrid()
    }
    publish(new RedrawGrid)
  }

  override def printGrid():String = grid.printGrid

  override def moveController(pos :String) :Unit = {
    undoManager.doStep(new SetCommand(this, pos))
    publish(new RedrawGrid)
  }

  override def undo(): Unit = {
    undoManager.undoStep
    publish(new RedrawGrid)
  }

  override def win(): Boolean = {
    if (player1.countState(StoneState.outOfGame) >= 7 || player2.countState(StoneState.outOfGame) >= 7) {
      publish(new RedrawGrid)
      true
    }
    else return false
  }
}