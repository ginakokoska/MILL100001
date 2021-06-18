
package controller.mock

import com.google.inject.Inject
import controller.ControllerInterface
import model.{Player, PlayerState, SetState}
import model.StoneState.StoneState
import model.gridComponent.State
import model.gridComponent.gridBase.{Grid, WhiteTurn}
import util.UndoManager

class Controller @Inject() (var player1: Player, var player2: Player, var grid: Grid) extends ControllerInterface {
  grid = new Grid()
  override def createPlayer1(name: String, tmpColor: String): Unit = {}
  override def createPlayer2(name: String): Unit = {}
  override def sayHello(): String = { "" }
  override def gridSize(gridSize: String): Unit = {}
  override def printGrid(): String = { "" }
  override def moveController(pos: String): Unit = {}
  override def undo(): Unit = {}
  override def win(): Boolean = false
  override def getPlayerState(player: Player): PlayerState = SetState()

  override val undoManager: UndoManager = new UndoManager
  override var gamePlayState: State = new WhiteTurn
}