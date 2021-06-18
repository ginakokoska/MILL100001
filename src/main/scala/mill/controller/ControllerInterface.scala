package mill.controller

import mill.model.{Player, PlayerState}
import mill.model.gridComponent.State
import mill.model.gridComponent.gridBase.Grid
import mill.util.UndoManager

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
  def printGrid(): String
  def moveController(pos: String): Unit
  def undo(): Unit
  def win(): Boolean
  def getPlayerState(player: Player): PlayerState

}

import scala.swing.event.Event

class RedrawGrid extends Event
class PlayerCreated extends Event