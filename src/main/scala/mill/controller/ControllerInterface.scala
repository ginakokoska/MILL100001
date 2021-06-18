package mill.controller

import mill.model.{Player, PlayerState}
import mill.model.gridComponent.{GridInterface, State}
import mill.model.StoneState.StoneState
import mill.model.gridComponent.gridBase.{GamePlay, Grid, WhiteTurn}
import mill.util.{Observer, UndoManager}

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  val undoManager: UndoManager
  var gamePlayState: State
  var grid: Grid
  var player1 :Player
  var player2 :Player

  def createPlayer1(name: String, tmpColor: String): Unit
  def createPlayer2(name: String): Unit
  def sayHello(): String
  def gridSize(gridInterface: GridInterface): Unit
  def printGrid(): String
  def moveController(pos: String): Unit
  def undo(): Unit
  def win(): Boolean
  def getPlayerState(player: Player): PlayerState

}

import scala.swing.event.Event

class RedrawGrid extends Event
class PlayerCreated extends Event