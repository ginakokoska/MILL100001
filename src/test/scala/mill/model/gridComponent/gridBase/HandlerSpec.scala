package mill.model.gridComponent.gridBase

import mill.controller.controllerBase.Controller
import mill.model.gridComponent.gridBase.{BlackTurn, GamePlay, Grid, Handler, Node, TakeStone, WhiteTurn}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._
import mill.model.{JumpState, MoveState, Player, PlayerState, SetState, Stone}
import mill.model.gridComponent.State

import java.awt.Color
import scala.reflect.ClassManifestFactory.Any

class HandlerSpec extends AnyWordSpec with Matchers {
  "A Handler " when {
    "States are" should {
      val grid = Grid()
      grid.createFullGrid()
      val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), grid)
      var gameState = controller.gamePlayState
      var playerState = controller.playerState
      "in WhiteTurn and SetState should set a Stone" in {
        gameState = WhiteTurn()
        playerState = SetState()
        val state = Handler(gameState, playerState).handleState("OS: 00", controller)
        state should be(BlackTurn())
      }
      "in WhiteTurn and MoveState should move a Stone" in {
        gameState = WhiteTurn()
        playerState = MoveState()
        val state = Handler(gameState, playerState).handleState("move OS: 00 to OS: 01", controller)
        state should be(BlackTurn())
      }
      "in WhiteTurn and JumpState should jump a Stone" in {
        gameState = WhiteTurn()
        playerState = JumpState()
        val state = Handler(gameState, playerState).handleState("jump OS: 01 to OS: 22", controller)
        state should be(BlackTurn())
      }
      "in BlackTurn and SetState should set a Stone" in {
        gameState = BlackTurn()
        playerState = SetState()
        val state = Handler(gameState, playerState).handleState("MS: 00",  controller)
        state should be(WhiteTurn())
      }
      "in BlackTurn and MoveState should move a Stone" in {
        gameState = BlackTurn()
        playerState = MoveState()
        val state = Handler(gameState, playerState).handleState("move MS: 00 to MS: 01", controller)
        state should be(WhiteTurn())
      }
      "in BlackTurn and JumpState should jump a Stone" in {
        gameState = BlackTurn()
        playerState = JumpState()
        val state = Handler(gameState, playerState).handleState("jump MS: 01 to MS: 22", controller)
        state should be(WhiteTurn())
      }
      "in TakeStone(Stone.white) should take a Stone" in {
        gameState = TakeStone(Stone.white)
        val state = Handler(gameState, playerState).handleState("MS: 22", controller)
        state should be(BlackTurn())
      }
      "in TakeStone(Stone.black) should take a Stone" in {
        gameState = TakeStone(Stone.black)
        val state = Handler(gameState, playerState).handleState("OS: 22", controller)
        state should be(WhiteTurn())
      }
    }
  }
}
