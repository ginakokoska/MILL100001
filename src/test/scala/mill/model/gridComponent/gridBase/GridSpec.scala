package mill.model.gridComponent.gridBase

import mill.controller.controllerBase.Controller
import mill.model.{Player, Stone}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

import scala.util.Success

class GridSpec extends AnyWordSpec with Matchers {
  "A Grid" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = tmpGrid.gridOutSquare()
    "created with size 1" should {
      tmpGrid.gridList = tmpGrid.gridOutSquare()
      "creates outSquare with nodes set to none" in {
        for (i <- 0 to 2) {
          for (j <- 0 to 2) {
            tmpGrid.gridList(0)(i)(j) should be(Node(None))
          }
        }
      }
    }
    "created with size 2" should {
      tmpGrid.gridList = tmpGrid.gridOutMidSquare()
      "creates out and midSquare with nodes set to none" in {
        for (i <- 0 to 2) {
          for (j <- 0 to 2) {
            tmpGrid.gridList(0)(i)(j) should be(Node(None))
            tmpGrid.gridList(1)(i)(j) should be(Node(None))
          }
        }
      }
    }
    "created with any other value" should {
      tmpGrid.gridList = tmpGrid.createFullGrid()
      "creates fullSquare with nodes set to none" in {
        for (i <- 0 to 2) {
          for (j <- 0 to 2) {
            tmpGrid.gridList(0)(i)(j) should be(Node(None))
            tmpGrid.gridList(1)(i)(j) should be(Node(None))
            tmpGrid.gridList(2)(i)(j) should be(Node(None))
          }
        }
      }
    }
  }
  "A Grid in Game"  when {
    val tmpGrid = Grid()
    tmpGrid.gridList = tmpGrid.createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "player sets a stone" should {
      tmpGrid.gridList(0)(0)(1) = Node(Some(Stone.white))
      val whiteNode = tmpGrid.moveGrid("OS: 00", Stone.white, controller.player1)
      val alreadySet = tmpGrid.moveGrid("OS: 00", Stone.black, controller.player1)
      val whiteMill = tmpGrid.moveGrid("OS: 02", Stone.white, controller.player1)
      val wrongInput = tmpGrid.moveGrid("F: 00", Stone.white, controller.player1)
      "should set the Node to the players color an return a success " in {
        whiteNode should be(Success(tmpGrid.gridList))
      }
      "should return a failure when node already set" in {
        alreadySet shouldNot be(Success(tmpGrid.gridList))
      }
      "should return failure when a mill has been build because a stone needs to be take first" in {
        whiteMill shouldNot be(Success(tmpGrid.gridList))
      }
      "should return failure when set incorrectly" in {
        wrongInput shouldNot be(Success(tmpGrid.gridList))
      }
    }
    "player take a stone" should {
      tmpGrid.gridList(1)(0)(0) = Node(Some(Stone.white))
      val takeStone = tmpGrid.takePos("MS: 00", Stone.white)
      val wrongInput = tmpGrid.takePos("IS: 00", Stone.white)
      val wrongColor = tmpGrid.takePos("IS: 01", Stone.black)
      "should return success and override Node with None" in {
        takeStone should be(Success())
      }
      "should return failure by wrong input" in {
        wrongInput shouldNot be(Success())
        wrongColor shouldNot be(Success())
      }
    }
    "player move a stone" should {
      val moveStone = tmpGrid.moveStone("move OS: 00 to OS: 10", Stone.white, controller.player1)
      val illegalMove = tmpGrid.moveStone("move OS: 10 to IS: 01", Stone.white,controller.player1)
      val wrongInput = tmpGrid.moveStone("move OS: 22 to OS: 21", Stone.white, controller.player1)
      "should return success by correct Input" in {
        moveStone should be(Success(tmpGrid.gridList))
      }
      "should return Failure by illegal or Wrong Input" in {
        illegalMove shouldNot be(Success(tmpGrid.gridList))
        wrongInput shouldNot be(Success(tmpGrid.gridList))
      }
    }
    "player jump a Stone" should {
      val jumpStone = tmpGrid.jumpStone("jump OS: 01 to IS: 01", Stone.white, controller.player1)
      val wrongInput = tmpGrid.jumpStone("move OS: 22 to OS: OS: IS: 22", Stone.white, controller.player1)
      "should return Success by correct Input" in {
        jumpStone should be(Success(tmpGrid.gridList))
      }
      "should return Failure by illegal Input" in {
        wrongInput shouldNot be(Success(tmpGrid.gridList))
      }
    }
  }
}
