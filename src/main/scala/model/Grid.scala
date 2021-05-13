package model

import scala.Array.ofDim
import scala.collection.mutable.ListBuffer

case class Grid() {
  var playGround :String = ""
  var gridList :List[Array[Array[Node]]] = List()

  def gridOutSquare() :List[Array[Array[Node]]] = {
    val outSquare = Array.ofDim[Node](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        outSquare(i)(j) = Node(None)
      }
    }
    playGround = "OS: 00--------------------OS: 01--------------------OS: 02\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "OS: 10                                           OS: 12\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "OS: 20--------------------OS: 21--------------------OS: 22"
    val arrayList = List(outSquare)
    arrayList
  }

  def gridOutMidSquare() :List[Array[Array[Node]]] = {
    val outSquare = Array.ofDim[Node](3, 3)
    val midSquare = Array.ofDim[Node](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        outSquare(i)(j) = Node(None)
        midSquare(i)(j) = Node(None)
      }
    }
    playGround = "OS: 00--------------------OS: 01--------------------OS: 02\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|        MS: 00-----------MS: 01-----------MS: 02     |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "OS: 10---MS: 10                          MS:12----OS: 12\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|          |                                |         |\n" +
      "|        MS: 20-----------MS: 21-----------MS: 22     |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "OS: 20--------------------OS: 21--------------------OS: 22"
    val arrayList = List(outSquare,midSquare)
    arrayList
  }

  def createFullGrid() :List[Array[Array[Node]]] = {
    val outSquare = Array.ofDim[Node](3, 3)
    val midSquare = Array.ofDim[Node](3, 3)
    val inSquare = Array.ofDim[Node](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        outSquare(i)(j) = Node(None)
        midSquare(i)(j) = Node(None)
        inSquare(i)(j) = Node(None)
      }
    }
    playGround = "OS: 00--------------------OS: 01--------------------OS: 02\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|        MS: 00-----------MS: 01-----------MS: 02     |\n" +
      "|          |                |               |         |\n" +
      "|          |                |               |         |\n" +
      "|          |     IS: 00---IS: 01---IS: 02   |         |\n" +
      "|          |      |                 |       |         |\n" +
      "|          |      |                 |       |         |\n" +
      "OS: 10---MS: 10--IS: 10            Is: 12--MS:12----OS: 12\n" +
      "|          |      |                 |       |         |\n" +
      "|          |      |                 |       |         |\n" +
      "|          |     IS: 20---IS: 21---IS: 22   |         |\n" +
      "|          |                |               |         |\n" +
      "|          |                |               |         |\n" +
      "|        MS: 20-----------MS: 21-----------MS: 22     |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "OS: 20--------------------OS: 21--------------------OS: 22"
    gridList = List(outSquare,midSquare,inSquare)
    gridList
  }

  def printGrid:String = playGround

  def moveGrid(square :String, pos1 :Char, pos2:Char, color :Stone.Value): List[Array[Array[Node]]] = {
    val intPos1 = pos1.asDigit
    val intPos2 = pos2.asDigit
    square match {
      case "OS:" => gridList(0)(intPos1)(intPos2) = Node (Some(color))
      case "MS:" => gridList(1)(intPos1)(intPos2) = Node (Some(color))
      case "IS:" => gridList(2)(intPos1)(intPos2) = Node (Some(color))
      case _ => throw new UnsupportedOperationException("wrong input! Please try again")
    }
    gridList
  }

}
