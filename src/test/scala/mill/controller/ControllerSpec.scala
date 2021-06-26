package mill.controller

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase.{Grid, Node}
import mill.model._
import mill.model.{Player, Stone}
import mill.util._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import org.scalatest.matchers.should._
//import org.scalatest.wordspec._



class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    //    val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
    //    val observer = new Observer {
    //      var updated: Boolean = false
    //      def isUpdated: Boolean = updated
    //      def update: Boolean = {
    //        updated = true;
    //        updated
    //      }
    //    }
    //    controller.add(observer)
    //    controller.createPlayer1("player1", "w")
    //    "player 1 is created, player 1" should {
    //      "has name 'player1' and white Color and notify its Observer" in {
    //        controller.player1.name should be("player1")
    //        controller.player1.color should be(Stone.white)
    //        observer.update should be(true)
    //      }
    //    }
    //
    //    "player 2 is created, player 2" should {
    //      controller.add(observer)
    //      controller.createPlayer2("player2")
    //      "has name 'player2' and black Color and notify its Observer" in {
    //        controller.player2.name should be("player2")
    //        controller.player2.color should be(Stone.black)
    //        observer.updated should be(true)
    //      }
    //    }
    //  }
    //  "A Controller Grid 1" when {
    //    val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
    //    val observer = new Observer {
    //      var updated: Boolean = false
    //
    //      def isUpdated: Boolean = updated
    //
    //      def update: Boolean = {
    //        updated = true;
    //        updated
    //      }
    //    }
    //    controller.add(observer)
    //    controller.gridSize("1")
    //
    //    "created Grid with size 1" should {
    //      "grid should be 'gridOutSquare' and notify its Observer" in {
    //        controller.grid.gridList.size should be(1)
    //        observer.updated should be(true)
    //      }
    //    }
    //
    ////    controller.player1.fillStone()
    ////    controller.gamePlayState = WhiteTurn()
    ////    controller.moveController("OS: 01")
    ////    "should be Node" should {
    ////
    ////      "should be override Node with an color" in {
    ////        controller.grid.gridList(0)(0)(1) should be(Some(Stone.white))
    ////      }
    ////    }
    //  }
    //  "A Controller Grid 2" when {
    //    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid = grid())
    //    val observer = new Observer {
    //      var updated: Boolean = false
    //
    //      def isUpdated: Boolean = updated
    //
    //      def update: Boolean = {
    //        updated = true;
    //        updated
    //      }
    //    }
    //    "created Grid with size 2" should {
    //      controller.add(observer)
    //      controller.gridSize("2")
    //      "when created grid with size 2 create 'gridOutMidSquare' and notify its Observer" in {
    //        controller.grid.gridList.size should be(2)
    //        observer.updated should be(true)
    //      }
    ////      controller.moveController("MS: 02")
    ////      "should be override Node with an color" in {
    ////
    ////        controller.grid.gridList(1)(0)(2) shouldNot be(Node(None))
    ////      }
    //    }
    //  }
    //  "A Controller Grid 3" when {
    //    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
    //    val observer = new Observer {
    //      var updated: Boolean = false
    //
    //      def isUpdated: Boolean = updated
    //
    //      def update: Boolean = {
    //        updated = true;
    //        updated
    //      }
    //    }
    //    "created Grid with size 3" should {
    //      controller.add(observer)
    //      controller.gridSize("3")
    //      "when created grid with size 3 create 'createFullyGrid' and notify its Observer" in {
    //        controller.grid.gridList.size should be(3)
    //        observer.updated should be(true)
    //
    //      }
    ////      controller.moveController("MS: 10")
    ////      "should be override Node with an color" in {
    ////        controller.grid.gridList(1)(1)(0) shouldNot be(Node(None))
    ////      }
    //    }
    //  }
    //  "A Controller Grid9999" when {
    //    val controller = new Controller(playerComponent.Player("player1", Stone.white), playerComponent.Player("player2", Stone.black), grid())
    //    val observer = new Observer {
    //      var updated: Boolean = false
    //
    //      def isUpdated: Boolean = updated
    //
    //      def update: Boolean = {
    //        updated = true;
    //        updated
    //      }
    //    }
    //    "created Grid with size 9999" should {
    //      controller.add(observer)
    //      controller.gridSize("9999")
    //      "should be 'createFullyGrid' and notify its Observer" in {
    //        controller.grid.gridList.size should be(3)
    //        observer.updated should be(true)
    //
    //      }
    ////      controller.moveController("OS: 00")
    ////      "should be override Node with an color" in {
    ////        controller.grid.gridList(0)(0)(0) shouldNot be(Node(None))
    ////      }
    //    }
    //  }
    "user" should {
      val grid = new Grid()
      grid.gridList = Grid().createFullGrid()
      val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), grid)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        def update: Boolean = {
          updated = true;
          updated
        }
      }
      controller.createPlayer1("namePlayerOne", "w")
      controller.createPlayer2("namePlayerTwo")

      "creates player one " in {
        controller.player1.name should be ("namePlayerOne")
        controller.player1.color should be (Stone.white)
      }
      "creates player two" in {
        controller.player2.name should be ("namePlayerTwo")
        controller.player2.color should be(Stone.black)
      }

      "takes a move" in {
        controller.playerState = SetState()
        controller.moveController("OS: 00")
        val node = controller.grid.gridList.head(0)(0).isSet should be(true)
      }
      "takes undo" in {
        controller.undo()
        for (row <- 0 to 2) {
          for (col <- 0 to 2) {
            controller.grid.gridList.head(row)(col).isSet should be(false)
            controller.grid.gridList(1)(row)(col).isSet should be(false)
            controller.grid.gridList(2)(row)(col).isSet should be(false)
          }
        }
      }

      for (i <- 1 to 9) {
        controller.player1.setStone() //make Stones States from notUsed to inGame
      }
      val winBefore = controller.win()
      for(i <- 1 to 7) {
        controller.player1.takeStone()  //take Stone States from inGame to OutOfGame
      }
      val winAfter = controller.win()
      "wins a Game" in {
        winBefore should be(false)
        winAfter should be(true)
      }

      for(i <- 1 to 9) {
        controller.player1.fillStone() //make Stone States notUsed again
      }
      val playerInSetstate = controller.getPlayerState(controller.player1)
      "is in playerState SetState" in {
        playerInSetstate should be(SetState())
      }

      for(i <- 1 to 9) {
        controller.player1.setStone() //take all Stone States in State inGame
      }
      val playerInMovestate = controller.getPlayerState(controller.player1)
      "is in playerState MoveState" in {
        playerInMovestate should be(MoveState())
      }

      for(i <- 1 to 6) {
        controller.player1.takeStone()  //take 6 Stone States in State OutOfGame
      }
      val playerInJumpstate = controller.getPlayerState(controller.player1)
      "is in playerState JumpState" in {
        playerInJumpstate should be(JumpState())
      }

      "set a playerState" in {
        controller.setPlayerState(SetState())
        controller.playerState should be(SetState())
        controller.setPlayerState(MoveState())
        controller.playerState should be(MoveState())
        controller.setPlayerState(JumpState())
        controller.playerState should be(JumpState())
      }
      "saves the game" in {
        controller.grid.gridList.head(0)(0) = Node(Some(Stone.white))
        controller.grid.gridList.head(0)(1) = Node(Some(Stone.black))
        controller.grid.gridList.head(0)(2) = Node(Some(Stone.white))
        controller.grid.gridList.head(1)(0) = Node(Some(Stone.black))
        controller.save()
      }

//      "load game" in {
//        controller.load()
//        controller.grid.gridList.head(0)(0).isColor should be(Some(Stone.white))
//        controller.grid.gridList.head(0)(1).isColor should be(Some(Stone.black))
//        controller.grid.gridList.head(0)(2).isColor should be(Some(Stone.white))
//        controller.grid.gridList.head(1)(0).isColor should be(Some(Stone.black))
//      }
    }
  }
}