package mill.model.gridComponent.gridBase

import mill.model.gridComponent.GridInterface
import mill.model._
import scala.util.{Failure, Success, Try}

/*
  This class creates and changes the grid in different sizes for the TUI.
 */

case class Grid() extends GridInterface{
  var playGround: String = ""
  var gridList: List[Array[Array[Node]]] = List()

    override def gridOutSquare(): List[Array[Array[Node]]] = {
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

    override def gridOutMidSquare(): List[Array[Array[Node]]] = {
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

    override def createFullGrid(): List[Array[Array[Node]]] = {
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

    override def printGrid: String = playGround

  override def setStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = {
    val posArray: Array[String] = pos.split(" ")
    var square = ""
    var intPos1 = 0
    var intPos2 = 0
    if (posArray.length == 2) {
      square = posArray(0)
      intPos1 = posArray(1).charAt(0).asDigit
      intPos2 = posArray(1).charAt(1).asDigit
    }

    square match {
      case "OS:" =>
        if (!gridList.head(intPos1)(intPos2).isSet) {
          gridList.head(intPos1)(intPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(0, intPos1, intPos2, color, this) ||
            ValidMill().proofTypeCorner(0, intPos1, intPos2, color, this)) {
            println(color.toString + "have mill!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(exception = new Exception(""))
        }
      case "MS:" =>
        if (!gridList(1)(intPos1)(intPos2).isSet) {
          gridList(1)(intPos1)(intPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(1, intPos1, intPos2, color, this) ||
            ValidMill().proofTypeCorner(1, intPos1, intPos2, color, this)) {
//            player.takeStone()
            println(color.toString + "have mill!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(exception = new Exception(""))
        }
      case "IS:" =>
        if (!gridList(2)(intPos1)(intPos2).isSet) {
          gridList(2)(intPos1)(intPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(2, intPos1, intPos2, color, this) ||
            ValidMill().proofTypeCorner(2, intPos1, intPos2, color, this)) {
            println(color.toString + "have mill!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(exception = new Exception(""))
        }
      case _ => Failure(exception = new Exception(""))
    }
  }

  override def takePos(pos: String, color: Stone.Value): Try[Unit] = {
    val posArray: Array[String] = pos.split(" ")
    var square = 999
    posArray(0) match {
      case "OS:" => square = 0
      case "MS:" => square = 1
      case "IS:" => square = 2
    }
    val intPos1 = posArray(1).charAt(0).asDigit
    val intPos2 = posArray(1).charAt(1).asDigit

    if (gridList(square)(intPos1)(intPos2).isColor.contains(color) &&
      !(ValidMill().proofTypeCorner(square, intPos1, intPos2, color, this) ||
        ValidMill().proofTypeMid(square, intPos1, intPos2, color, this))) {
      gridList(square)(intPos1)(intPos2) = Node(None)
      Success()
    } else
      Failure(new Exception(""))
  }

  override def moveStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = {
    val posArray: Array[String] = pos.split(" ") //move OS: 00 to OS: 01

    val intOldPos1 = posArray(2).charAt(0).asDigit
    val intOldPos2 = posArray(2).charAt(1).asDigit
    val intNewPos1 = posArray(5).charAt(0).asDigit
    val intNewPos2 = posArray(5).charAt(1).asDigit

    var sqOld = 999
    posArray(1) match {
      case "OS:" => sqOld = 0
      case "MS:" => sqOld = 1
      case "IS:" => sqOld = 2
    }
    var sqNew = 999
    posArray(4) match {
      case "OS:" => sqNew = 0
      case "MS:" => sqNew = 1
      case "IS:" => sqNew = 2
    }
    posArray(4) match {
      case "OS:" =>
        if (gridList(sqOld)(intOldPos1)(intOldPos2).isColor.contains(color) &&
          (MoveCondition().moveConditionCorner(intOldPos1, intOldPos2, intNewPos1, intNewPos2,sqOld,sqNew) ||
            MoveCondition().moveConditionMid(posArray(1), intOldPos1, intOldPos2, 0, intNewPos1, intNewPos2)) &&
          !gridList.head(intNewPos1)(intNewPos2).isSet) {
          gridList(sqOld)(intOldPos1)(intOldPos2) = Node(None)
          gridList.head(intNewPos1)(intNewPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(0, intNewPos1, intNewPos2, color, this) ||
            ValidMill().proofTypeCorner(0, intNewPos1, intNewPos2, color, this)) {
            println(color.toString + " have mill1!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(new Exception(""))
        }
      case "MS:" =>
        if (gridList(sqOld)(intOldPos1)(intOldPos2).isColor.contains(color) &&
          (MoveCondition().moveConditionCorner(intOldPos1, intOldPos2, intNewPos1, intNewPos2, sqOld, sqNew) ||
            MoveCondition().moveConditionMid(posArray(1), intOldPos1, intOldPos2, 1, intNewPos1, intNewPos2)) &&
          !gridList(1)(intNewPos1)(intNewPos2).isSet) {
          gridList(sqOld)(intOldPos1)(intOldPos2) = Node(None)
          gridList(1)(intNewPos1)(intNewPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(1, intNewPos1, intNewPos2, color, this) ||
            ValidMill().proofTypeCorner(1, intNewPos1, intNewPos2, color, this)) {
            println(color.toString + " have mill1!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(new Exception(""))
        }
      case "IS:" =>
        if (gridList(sqOld)(intOldPos1)(intOldPos2).isColor.contains(color) &&
          (MoveCondition().moveConditionCorner(intOldPos1, intOldPos2, intNewPos1, intNewPos2, sqOld, sqNew) ||
            MoveCondition().moveConditionMid(posArray(1), intOldPos1, intOldPos2, 2, intNewPos1, intNewPos2)) &&
          !gridList(2)(intNewPos1)(intNewPos2).isSet) {
          gridList(sqOld)(intOldPos1)(intOldPos2) = Node(None)
          gridList(2)(intNewPos1)(intNewPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(2, intNewPos1, intNewPos2, color, this) ||
            ValidMill().proofTypeCorner(2, intNewPos1, intNewPos2, color, this)) {
            println(color.toString + " have mill1!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(new Exception(""))
        }
    }
  }

  override def jumpStone(pos: String, color: Stone.Value, player: Player): Try[List[Array[Array[Node]]]] = {
    val posArray: Array[String] = pos.split(" ") //jump OS: 00 to OS: 01

    val intOldPos1 = posArray(2).charAt(0).asDigit
    val intOldPos2 = posArray(2).charAt(1).asDigit
    val intNewPos1 = posArray(5).charAt(0).asDigit
    val intNewPos2 = posArray(5).charAt(1).asDigit
    var sqOld = 999
    posArray(1) match {
      case "OS:" => sqOld = 0
      case "MS:" => sqOld = 1
      case "IS:" => sqOld = 2
    }

    posArray(4) match {
      case "OS:" =>
        if (gridList(sqOld)(intOldPos1)(intOldPos2).isColor.contains(color) &&
          !gridList.head(intNewPos1)(intNewPos2).isSet) {
          gridList(sqOld)(intOldPos1)(intOldPos2) = Node(None)
          gridList.head(intNewPos1)(intNewPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(0, intNewPos1, intNewPos2, color, this) ||
            ValidMill().proofTypeCorner(0, intNewPos1, intNewPos2, color, this)) {
            player.takeStone()
            println(color.toString + " has a mill!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(new Exception(""))
        }
      case "MS:" =>
        if (gridList(sqOld)(intOldPos1)(intOldPos2).isColor.contains(color) &&
          !gridList(1)(intNewPos1)(intNewPos2).isSet) {
          gridList(sqOld)(intOldPos1)(intOldPos2) = Node(None)
          gridList(1)(intNewPos1)(intNewPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(1, intNewPos1, intNewPos2, color, this) ||
            ValidMill().proofTypeCorner(1, intNewPos1, intNewPos2, color, this)) {
            player.takeStone()
            println(color.toString + " has a mill!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(new Exception(""))
        }
      case "IS:" =>
        if (gridList(sqOld)(intOldPos1)(intOldPos2).isColor.contains(color) &&
          !gridList(2)(intNewPos1)(intNewPos2).isSet) {
          gridList(sqOld)(intOldPos1)(intOldPos2) = Node(None)
          gridList(2)(intNewPos1)(intNewPos2) = Node(Some(color))
          if (ValidMill().proofTypeMid(2, intNewPos1, intNewPos2, color, this) ||
            ValidMill().proofTypeCorner(2, intNewPos1, intNewPos2, color, this)) {
            player.takeStone()
            println(color.toString + " have mill1!")
            Failure(new Exception("message:"))
          } else {
            Success(gridList)
          }
        } else {
          Failure(new Exception(""))
        }
    }
  }
}
