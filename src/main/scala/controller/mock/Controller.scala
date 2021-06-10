
package controller.mock

import controller.ControllerInterface
import model.{Player, grid}

class Controller(var player1: Player, var player2: Player, var grid: grid) extends ControllerInterface {
  grid = new grid()
  override def cretePlayer1(name: String, tmpColor: String): Unit = {}
  override def createPlayer2(name: String): Unit = {}
  override def sayHello(): Unit = {}
  override def gridSize(gridSize: String): Unit = {}
  override def printGrid(): Unit = {}
  override def moveController(pos: String): Unit = {}
  override def undo(): Unit = {}

}