package model.gridComponent.gridMock

import model.gridComponent.GridInterface
import model.gridComponent.gridBase.Node
import model.playerComponent.{Player, Stone}
import model.gridComponent.GridInterface

import scala.util.{Failure, Try}

class Grid() extends GridInterface{
  var playGround: String = ""
  var gridList: List[Array[Array[Node]]] = List()
  override def gridOutSquare(): List[Array[Array[Node]]] = this.gridList

  override def gridOutMidSquare(): List[Array[Array[Node]]] = this.gridList

  override def createFullGrid(): List[Array[Array[Node]]] = this.gridList

  override def printGrid: String = this.playGround

  override def moveGrid(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = Failure(new Exception)

  override def takePos(pos: String, color: Stone.Value): Try[Unit] = Failure(new Exception)

  override def moveStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = Failure(new Exception)

  override def jumpStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = Failure(new Exception)
}
