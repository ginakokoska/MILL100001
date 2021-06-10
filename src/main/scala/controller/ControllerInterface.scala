package controller

import util.Observer

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def createPlayer1(name: String, tmpColor: String): Unit
  def createPlayer2(name: String): Unit
  def sayHello(): String
  def gridSize(gridSize: String): Unit
  def printGrid(): String
  def moveController(pos: String): Unit
  def undo(): Unit
  def win(): Boolean

}

import scala.swing.event.Event

class RedrawGrid extends Event
class PlayerCreated extends Event