package mill.controller

import mill.controller.controllerBase.Controller
import mill.model.gridComponent.gridBase.{Grid, Node}
import mill.model._
import mill.model.{Player, Stone}
import mill.util._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec




class ControllerSpec extends AnyWordSpec with Matchers {
  "Controller" when {
    "called" should {
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
      val winBefore = controller.win(controller.player1)
      for(i <- 1 to 7) {
        controller.player1.takeStone()  //take Stone States from inGame to OutOfGame
      }
      val winAfter = controller.win(controller.player1)
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

      "sets a playerState" in {
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