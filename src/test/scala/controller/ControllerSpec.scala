package controller

import controller.base.Controller
import model.gridComponent.gridBase.grid
import org.scalatest.matchers.should._
import org.scalatest.wordspec._
import model.playerComponent
import model.playerComponent.{Player, Stone}
import util._



class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller Player" when {
    val controller = new Controller(Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
    val observer = new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      def update: Boolean = {
        updated = true;
        updated
      }
    }
    controller.add(observer)
    controller.createPlayer1("player1", "w")
    "player 1 is created, player 1" should {
      "has name 'player1' and white Color and notify its Observer" in {
        controller.player1.name should be("player1")
        controller.player1.color should be(Stone.white)
        observer.update should be(true)
      }
    }

    "player 2 is created, player 2" should {
      controller.add(observer)
      controller.createPlayer2("player2")
      "has name 'player2' and black Color and notify its Observer" in {
        controller.player2.name should be("player2")
        controller.player2.color should be(Stone.black)
        observer.updated should be(true)
      }
    }
  }
  "A Controller Grid 1" when {
    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
    val observer = new Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      def update: Boolean = {
        updated = true;
        updated
      }
    }
    controller.add(observer)
    controller.gridSize("1")

    "created Grid with size 1" should {
      "grid should be 'gridOutSquare' and notify its Observer" in {
        controller.grid.gridList.size should be(1)
        observer.updated should be(true)
      }
    }

//    controller.player1.fillStone()
//    controller.gamePlayState = WhiteTurn()
//    controller.moveController("OS: 01")
//    "should be Node" should {
//
//      "should be override Node with an color" in {
//        controller.grid.gridList(0)(0)(1) should be(Some(Stone.white))
//      }
//    }
  }
  "A Controller Grid 2" when {
    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid = grid())
    val observer = new Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      def update: Boolean = {
        updated = true;
        updated
      }
    }
    "created Grid with size 2" should {
      controller.add(observer)
      controller.gridSize("2")
      "when created grid with size 2 create 'gridOutMidSquare' and notify its Observer" in {
        controller.grid.gridList.size should be(2)
        observer.updated should be(true)
      }
//      controller.moveController("MS: 02")
//      "should be override Node with an color" in {
//
//        controller.grid.gridList(1)(0)(2) shouldNot be(Node(None))
//      }
    }
  }
  "A Controller Grid 3" when {
    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
    val observer = new Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      def update: Boolean = {
        updated = true;
        updated
      }
    }
    "created Grid with size 3" should {
      controller.add(observer)
      controller.gridSize("3")
      "when created grid with size 3 create 'createFullyGrid' and notify its Observer" in {
        controller.grid.gridList.size should be(3)
        observer.updated should be(true)

      }
//      controller.moveController("MS: 10")
//      "should be override Node with an color" in {
//        controller.grid.gridList(1)(1)(0) shouldNot be(Node(None))
//      }
    }
  }
  "A Controller Grid9999" when {
    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
    val observer = new Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      def update: Boolean = {
        updated = true;
        updated
      }
    }
    "created Grid with size 9999" should {
      controller.add(observer)
      controller.gridSize("9999")
      "should be 'createFullyGrid' and notify its Observer" in {
        controller.grid.gridList.size should be(3)
        observer.updated should be(true)

      }
//      controller.moveController("OS: 00")
//      "should be override Node with an color" in {
//        controller.grid.gridList(0)(0)(0) shouldNot be(Node(None))
//      }
    }
  }
}