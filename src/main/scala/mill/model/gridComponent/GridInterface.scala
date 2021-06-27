package mill.model.gridComponent

import mill.model.{Player, Stone}
import mill.model.gridComponent.gridBase.Node
import scala.util.Try

/*
  This trait initialises the functions for the grid class.
 */

trait GridInterface {
  var gridList: List[Array[Array[Node]]]

  def gridOutSquare(): List[Array[Array[Node]]]
  def gridOutMidSquare(): List[Array[Array[Node]]]
  def createFullGrid(): List[Array[Array[Node]]]
  def printGrid: String
  def moveGrid(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
  def takePos(pos: String, color: Stone.Value): Try[Unit]
  def moveStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
  def jumpStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]]
}


