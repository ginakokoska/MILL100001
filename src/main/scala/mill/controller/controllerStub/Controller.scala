
package mill.controller.controllerStub

import com.google.inject.Inject
import mill.controller.ControllerInterface
import mill.model.{Player, PlayerState, SetState}
import mill.model.gridComponent.{State}
import mill.model.gridComponent.gridBase.{Grid, WhiteTurn}
import mill.util.UndoManager

/*
  This class initializes functions with default value.
 */

class Controller @Inject() (var player1: Player, var player2: Player, var grid: Grid) extends ControllerInterface {
  grid = new Grid()
  override val undoManager: UndoManager = new UndoManager
  override var gamePlayState: State = new WhiteTurn
  override var playerState: PlayerState = SetState()

  override def createPlayer1(name: String, tmpColor: String): Unit = {}
  override def createPlayer2(name: String): Unit = {}
  override def getPlayerState(player: Player): PlayerState = SetState()
  override def setPlayerState(player: PlayerState): Unit = {}
  override def sayHello(): String = { "" }
  override def printGrid(): String = { "" }
  override def moveController(pos: String): Unit = {}
  override def undo(): Unit = {}
  override def win(player: Player): Boolean = false
  override def load(): Unit = {}
  override def save(): Unit = {}
}