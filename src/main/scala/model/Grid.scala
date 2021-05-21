package model

import scala.util.{Failure, Success, Try}

case class Grid() {
  var playGround: String = ""
  var gridList: List[Array[Array[Node]]] = List()

  def gridOutSquare(): List[Array[Array[Node]]] = {
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

  def gridOutMidSquare(): List[Array[Array[Node]]] = {
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
    val arrayList = List(outSquare, midSquare)
    arrayList
  }

  def createFullGrid(): List[Array[Array[Node]]] = {
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
    gridList = List(outSquare, midSquare, inSquare)
    gridList
  }

  def printGrid: String = playGround

  def moveGrid(pos :String, color: Stone.Value): Try[List[Array[Array[Node]]]] = {
    val posArray: Array[String] = pos.split(" ")

      val square = posArray(0)
    var intPos1 = 0
    var intPos2 = 0
    Try(posArray(1).charAt(0).asDigit) match {
      case Success(v) => intPos1 = v
      case Failure(_) => Failure(exception = new Exception)
    }
    Try(posArray(1).charAt(1).asDigit) match {
      case Success(v) => intPos2 = v
      case Failure(_) => Failure(exception = new Exception)
    }

    square match {
        case "OS:" =>
          if(!gridList(0)(intPos1)(intPos2).isSet) {
            gridList(0)(intPos1)(intPos2) = Node(Some(color))
            Success(gridList)
          } else {
            Failure(exception = new Exception)
          }
        case "MS:" =>
          if(!gridList(1)(intPos1)(intPos2).isSet) {
            gridList(1)(intPos1)(intPos2) = Node(Some(color))
            Success(gridList)
          } else {
            Failure(exception = new Exception)
          }
        case "IS:" =>
          if(!gridList(2)(intPos1)(intPos2).isSet) {
            gridList(2)(intPos1)(intPos2) = Node(Some(color))
            Success(gridList)
          } else {
            Failure(exception = new Exception)
          }
        case _ => Failure(exception = new Exception)
      }
  }

  def moveStone(pos: String, color: Stone.Value): List[Array[Array[Node]]] = {
    val posArray: Array[String] = pos.split(" ") //move OS: 00 to OS: 01
    try {
      val intOldPos1 = posArray(2).charAt(0).asDigit
      val intOldPos2 = posArray(2).charAt(1).asDigit
      val intNewPos1 = posArray(5).charAt(0).asDigit
      val intNewPos2 = posArray(5).charAt(1).asDigit
      posArray(1) match {
        case "OS:" => gridList(0)(intOldPos1)(intOldPos2) = Node(None)
        case "MS:" => gridList(1)(intOldPos1)(intOldPos2) = Node(None)
        case "IS:" => gridList(2)(intOldPos1)(intOldPos2) = Node(None)
      }
      posArray(4) match {
        case "OS:" => gridList(0)(intNewPos1)(intNewPos2) = Node(Some(color))
        case "MS:" => gridList(1)(intNewPos1)(intNewPos2) = Node(Some(color))
        case "IS:" => gridList(2)(intNewPos1)(intNewPos2) = Node(Some(color))
      }
    } catch {
      case _: UnsupportedOperationException => println("Wrong Input! Please try again")
    }
    gridList
  }
}

