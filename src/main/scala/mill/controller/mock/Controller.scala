
package mill.controller.mock

import com.google.inject.Inject
import mill.controller.ControllerInterface
import mill.model.{Player, PlayerState, SetState}
import mill.model.StoneState.StoneState
import mill.model.gridComponent.{GridInterface, State}
import mill.model.gridComponent.gridBase.{Grid, WhiteTurn}
import mill.util.UndoManager

class Controller @Inject() (var player1: Player, var player2: Player, var grid: Grid) extends ControllerInterface {
  grid = new Grid()
  override def createPlayer1(name: String, tmpColor: String): Unit = {}
  override def createPlayer2(name: String): Unit = {}
  override def sayHello(): String = { "" }
  override def gridSize(gridInterface: GridInterface): Unit = {}
  override def printGrid(): String = { "" }
  override def moveController(pos: String): Unit = {}
  override def undo(): Unit = {}
  override def win(): Boolean = false
  override def getPlayerState(player: Player): PlayerState = SetState()

  override val undoManager: UndoManager = new UndoManager
  override var gamePlayState: State = new WhiteTurn
}