package controller

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def cretePlayer1(name: String, tmpColor: String): Unit
  def createPlayer2(name: String): Unit
  def sayHello(): Unit
  def gridSize(gridSize: String): Unit
  def printGrid(): Unit
  def moveController(pos: String): Unit
  def undo(): Unit

}