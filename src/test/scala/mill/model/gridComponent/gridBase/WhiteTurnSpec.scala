package mill.model.gridComponent.gridBase

import mill.controller.controllerBase.Controller
import mill.model.gridComponent.gridBase
import mill.model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class WhiteTurnSpec() extends AnyWordSpec with Matchers {
  "WhiteTurn" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "called with setStoneState() should return" should {
      val state = WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      val state1 = WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("MS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("MS: 01", tmpGrid, controller)
      val stateMill = WhiteTurn().setStoneState("MS: 02", tmpGrid, controller)
      "BlackTurn() if set to a valid node" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "WhiteTurn() if set to an occupied node" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeStone() if player formed a mill" in {
        stateMill should be(GamePlay(new TakeStone(Stone.white)).state)
      }
    }
    "moveStoneState() should return" should {
      tmpGrid.createFullGrid()
      WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("MS: 01", tmpGrid, controller)
      WhiteTurn().setStoneState("OS: 12", tmpGrid, controller)
      val state = WhiteTurn().moveStoneState("move MS: 01 to OS: 01", tmpGrid, controller)
      val state1 = WhiteTurn().moveStoneState("move OS: 00 to OS: 02", tmpGrid, controller)
      val stateMill = WhiteTurn().moveStoneState("move OS: 12 to OS: 02", tmpGrid, controller)
      "BlackTurn(), when moving to an free, adjacent node " in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "WhiteTurn() again if moved to a non adjacent node or occupied node" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeStone(), when player has formed a mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.white)).state)
      }
    }
    "takeStoneState(), should always return " should {
      val state = WhiteTurn().takeStoneState("MS: 22", tmpGrid)
      "always WhiteTurn" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
    }
    "jumpStone(), should return" should {
      tmpGrid.createFullGrid()
      WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("OS: 01", tmpGrid, controller)
      WhiteTurn().setStoneState("IS: 00", tmpGrid, controller)
      val state = WhiteTurn().jumpStoneState("jump OS: 00 to OS: 02", tmpGrid, controller)
      val state1 = WhiteTurn().jumpStoneState("jump MS: 00 to MS: 02", tmpGrid, controller)
      val stateMill = WhiteTurn().jumpStoneState("jump IS: 00 to OS: 00", tmpGrid, controller)
      "BlackTurn(), if jumped to free node" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "WhiteTurn(), if jumped to invalid node" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeStone(), when player has formed a mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.white)).state)
      }
    }
  }
}
