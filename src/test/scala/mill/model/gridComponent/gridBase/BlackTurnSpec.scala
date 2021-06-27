package mill.model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase
import mill.model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class BlackTurnSpec() extends AnyWordSpec with Matchers {
  "BlackTurn" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "called with setStoneState() should return " should {
      val state = BlackTurn().setStoneState("OS: 00", tmpGrid, controller)
      val state1 = BlackTurn().setStoneState("OS: 00", tmpGrid, controller)
      BlackTurn().setStoneState("MS: 00", tmpGrid, controller)
      BlackTurn().setStoneState("MS: 01", tmpGrid, controller)
      val stateMill = BlackTurn().setStoneState("MS: 02", tmpGrid, controller)
      "WhiteTurn() if set to a valid node" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
      "BlackTurn() if set to an occupied node" in {
        state1 should be(GamePlay(new BlackTurn).state)
      }
      "TakeStone() if player formed a mill" in {
        stateMill should be(GamePlay(new TakeStone(Stone.black)).state)
      }
    }
    "moveStoneState() should return" should {
      tmpGrid.createFullGrid()
      BlackTurn().setStoneState("OS: 00", tmpGrid, controller)
      BlackTurn().setStoneState("MS: 01", tmpGrid, controller)
      BlackTurn().setStoneState("OS: 12", tmpGrid, controller)
      val state = BlackTurn().moveStoneState("move MS: 01 to OS: 01", tmpGrid, controller)
      val state1 = BlackTurn().moveStoneState("move OS: 00 to OS: 02", tmpGrid, controller)
      val stateMill = BlackTurn().moveStoneState("move OS: 12 to OS: 02", tmpGrid, controller)
      "WhiteTurn(), when moving to an free, adjacent node " in {
        state should be(GamePlay(new WhiteTurn).state)
      }
      "BlackTurn() again if moved to a non adjacent node or occupied node" in {
        state1 should be(GamePlay(new BlackTurn).state)
      }
      "TakeStone(), when player has formed a mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.black)).state)
      }
    }
    "takeStoneState(), should always return " should {
      val state = BlackTurn().takeStoneState("MS: 22", tmpGrid)
      "BlackTurn()" in {
        state should be(GamePlay(new BlackTurn).state)
      }
    }
    "jumpStone(), should return" should {
      tmpGrid.createFullGrid()
      BlackTurn().setStoneState("OS: 00", tmpGrid, controller)
      BlackTurn().setStoneState("OS: 01", tmpGrid, controller)
      BlackTurn().setStoneState("IS: 00", tmpGrid, controller)
      val state = BlackTurn().jumpStoneState("jump OS: 00 to OS: 02", tmpGrid, controller)
      val state1 = BlackTurn().jumpStoneState("jump MS: 00 to MS: 02", tmpGrid, controller)
      val stateMill = BlackTurn().jumpStoneState("jump IS: 00 to OS: 00", tmpGrid, controller)
      "WhiteTurn(), if jumped to invalid node" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
      "BlackTurn() if jumped to a free node" in {
        state1 should be(GamePlay(new BlackTurn).state)
      }
      "TakeStone() when player has formed a mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.black)).state)
      }
    }
  }
}
