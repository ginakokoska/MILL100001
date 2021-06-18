package mill.model.gridComponent

import mill.model.{Player, Stone}
import mill.model.gridComponent.gridBase.Node

import scala.util.Try

trait GridInterface {
  var gridList: List[Array[Array[Node]]]

  def gridOutSquare(gridInterface: GridInterface): List[Array[Array[Node]]]
  def gridOutMidSquare(gridInterface: GridInterface): List[Array[Array[Node]]]
  def createFullGrid(gridInterface: GridInterface): List[Array[Array[Node]]]
  def printGrid: String
  def moveGrid(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
  def takePos(pos: String, color: Stone.Value): Try[Unit]
  def moveStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
  def jumpStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
}
