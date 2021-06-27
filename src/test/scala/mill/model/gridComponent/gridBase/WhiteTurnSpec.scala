package mill.model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase
import mill.model.gridComponent.gridBase._
import mill.model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class WhiteTurnSpec() extends AnyWordSpec with Matchers {
  "WhiteTurn" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "handle() should be return " should {
      val state = WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      val state1 = WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("MS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("MS: 01", tmpGrid, controller)
      val stateMill = WhiteTurn().setStoneState("MS: 02", tmpGrid, controller)
      "WhiteTurn, by OS: 00" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "BlackTurn again, by OS: 00 again cause already set" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeStone, when player have mill" in {
        stateMill should be(GamePlay(new TakeStone(Stone.white)).state)
      }
    }
    "handle2() should be return" should {
      tmpGrid.createFullGrid()
      WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("MS: 01", tmpGrid, controller)
      //      BlackTurn().handle("OS: 01", tmpGrid, controller)
      WhiteTurn().setStoneState("OS: 12", tmpGrid, controller)
      val state = WhiteTurn().moveStoneState("move MS: 01 to OS: 01", tmpGrid, controller)
      val state1 = WhiteTurn().moveStoneState("move OS: 00 to OS: 02", tmpGrid, controller)
      val stateMill = WhiteTurn().moveStoneState("move OS: 12 to OS: 02", tmpGrid, controller)
      "WhiteTurn, by moving OS: 00 to OS: 01" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "BlackTurn again, by moving OS: 00 to OS:02" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeStone, when player have mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.white)).state)
      }
    }
    "handleTakeStone, should be return" should {
      val state = WhiteTurn().takeStoneState("MS: 22", tmpGrid)
      "always WhiteTurn" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
    }
    "jumpStone, should be return" should {
      tmpGrid.createFullGrid()
      WhiteTurn().setStoneState("OS: 00", tmpGrid, controller)
      WhiteTurn().setStoneState("OS: 01", tmpGrid, controller)
      WhiteTurn().setStoneState("IS: 00", tmpGrid, controller)
      val state = WhiteTurn().jumpStoneState("jump OS: 00 to OS: 02", tmpGrid, controller)
      val state1 = WhiteTurn().jumpStoneState("jump MS: 00 to MS: 02", tmpGrid, controller)
      val stateMill = WhiteTurn().jumpStoneState("jump IS: 00 to OS: 00", tmpGrid, controller)
      "WhiteTurn, by 'jump OS: 00 to OS: 02'" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "BlackTurn, by 'jump OS: 01 to OS: 10'" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeTurn, when player have mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.white)).state)
      }
    }
  }
}
