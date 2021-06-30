package mill.controller

import mill.model.{Player, PlayerState}
import mill.model.gridComponent.State
import mill.model.gridComponent.gridBase.Grid
import mill.util.UndoManager
import scala.swing.event.Event
import scala.swing.Publisher

/*
  This trait initializes all functions and events implemented in the controller class.
 */

trait ControllerInterface extends Publisher {
  val undoManager: UndoManager
  var gamePlayState: State
  var playerState: PlayerState
  var grid: Grid
  var player1 :Player
  var player2 :Player

  def createPlayer1(name: String, tmpColor: String): Unit
  def createPlayer2(name: String): Unit
  def getPlayerState(player: Player): PlayerState
  def setPlayerState(player: PlayerState): Unit
  def sayHello(): String
  def printGrid(): String
  def moveController(pos: String): Unit
  def undo(): Unit
  def win(player: Player): Boolean
  def load(): Unit
  def save(): Unit

}

class RedrawGrid extends Event
class PlayerCreated extends Event
class GameSaved extends Event
class GameLoaded extends Event
class WonGame extends Event