package model.gridComponent

import model.gridComponent.gridBase.Node
import model.playerComponent.{Player, Stone}

import scala.util.Try

trait GridInterface {
  def gridOutSquare(): List[Array[Array[Node]]]
  def gridOutMidSquare(): List[Array[Array[Node]]]
  def createFullGrid(): List[Array[Array[Node]]]
  def printGrid: String
  def moveGrid(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
  def takePos(pos: String, color: Stone.Value): Try[Unit]
  def moveStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
  def jumpStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
}
