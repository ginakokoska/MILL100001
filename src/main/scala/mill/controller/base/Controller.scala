package mill.controller.base

import mill.controller.{ControllerInterface, PlayerCreated, RedrawGrid}
import mill.model.gridComponent.gridBase.{GamePlay, Grid, WhiteTurn}
import mill.model.{MoveState, Player, PlayerState, SetState, Stone, StoneState}
import mill.util._
import scala.swing.Publisher

class Controller (var player1: Player, var player2: Player, var grid: Grid) extends ControllerInterface with Publisher {
  val undoManager: UndoManager = new UndoManager
  var gamePlayState = GamePlay(new WhiteTurn).state
  var playerState: PlayerState = SetState()

  override def createPlayer1(name: String, tmpColor: String):Unit = {
    if(tmpColor == "b") player1 = Player(name,Stone.black)
    else player1 = Player(name,Stone.white)
    player1.fillStone()
    publish(new PlayerCreated)
  }

  override def createPlayer2(name: String):Unit = {
    if(player1.color == Stone.white) player2 = Player(name,Stone.black)
    else player2 = Player(name,Stone.white)
    player2.fillStone()
    publish(new PlayerCreated)
  }

  override def sayHello(): String = player1.playerToString(player1, player2)

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
    else false
  }

   override def getPlayerState(player: Player): PlayerState = {
     playerState = playerState.getState(player)
     MoveState().getState(player)
  }
}