
package controller.mock

import controller.ControllerInterface
import model.gridComponent.gridBase.grid
import model.playerComponent.Player

class Controller(var player1: Player, var player2: Player, var grid: grid) extends ControllerInterface {
  grid = new grid()
  override def createPlayer1(name: String, tmpColor: String): Unit = {}
  override def createPlayer2(name: String): Unit = {}
  override def sayHello(): String = { "" }
  override def gridSize(gridSize: String): Unit = {}
  override def printGrid(): String = { "" }
  override def moveController(pos: String): Unit = {}
  override def undo(): Unit = {}

}