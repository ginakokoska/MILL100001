package mill.model.gridComponent.gridMock

import mill.model.{Player, Stone}
import mill.model.gridComponent.GridInterface
import mill.model.gridComponent.GridInterface
import mill.model.gridComponent.gridBase.Node

import scala.util.{Failure, Try}

class Grid() extends GridInterface{
  var playGround: String = ""
  var gridList: List[Array[Array[Node]]] = List()
    override def gridOutSquare(gridInterface: GridInterface): List[Array[Array[Node]]] = this.gridList

    override def gridOutMidSquare(gridInterface: GridInterface): List[Array[Array[Node]]] = this.gridList

    override def createFullGrid(gridInterface: GridInterface): List[Array[Array[Node]]] = this.gridList

    override def printGrid: String = this.playGround

  override def moveGrid(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = Failure(new Exception)

  override def takePos(pos: String, color: Stone.Value): Try[Unit] = Failure(new Exception)

  override def moveStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = Failure(new Exception)

  override def jumpStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = Failure(new Exception)
}
