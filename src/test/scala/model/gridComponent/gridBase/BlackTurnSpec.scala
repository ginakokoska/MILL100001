package model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase
import mill.model.gridComponent.gridBase._
import mill.model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class BlackTurnSpec() extends AnyWordSpec with Matchers {
  "BlackTurn" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "handle() should be return " should {
      val state = BlackTurn().handle("OS: 00", tmpGrid, controller)
      val state1 = BlackTurn().handle("OS: 00", tmpGrid, controller)
      BlackTurn().handle("MS: 00", tmpGrid, controller)
      BlackTurn().handle("MS: 01", tmpGrid, controller)
      val stateMill = BlackTurn().handle("MS: 02", tmpGrid, controller)
      "WhiteTurn, by OS: 00" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
      "BlackTurn again, by OS: 00 again cause already set" in {
        state1 should be(GamePlay(new BlackTurn).state)
      }
      "TakeStone, when player have mill" in {
        stateMill should be(GamePlay(new TakeStone(Stone.black)).state)
      }
    }
    "handle2() should be return" should {
      tmpGrid.createFullGrid()
      BlackTurn().handle("OS: 00", tmpGrid, controller)
      BlackTurn().handle("MS: 01", tmpGrid, controller)
      //      BlackTurn().handle("OS: 01", tmpGrid, controller)
      BlackTurn().handle("OS: 12", tmpGrid, controller)
      val state = BlackTurn().handle2("move MS: 01 to OS: 01", tmpGrid, controller)
      val state1 = BlackTurn().handle2("move OS: 00 to OS: 02", tmpGrid, controller)
      val stateMill = BlackTurn().handle2("move OS: 12 to OS: 02", tmpGrid, controller)
      "WhiteTurn, by moving OS: 00 to OS: 01" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
      "BlackTurn again, by moving OS: 00 to OS:02" in {
        state1 should be(GamePlay(new BlackTurn).state)
      }
      "TakeStone, when player have mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.black)).state)
      }
    }
    "handleTakeStone, should be return" should {
      val state = BlackTurn().handleTakeStone("MS: 22", tmpGrid)
      "always BlackTurn" in {
        state should be(GamePlay(new BlackTurn).state)
      }
    }
    "jumpStone, should be return" should {
      tmpGrid.createFullGrid()
      BlackTurn().handle("OS: 00", tmpGrid, controller)
      BlackTurn().handle("OS: 01", tmpGrid, controller)
      BlackTurn().handle("IS: 00", tmpGrid, controller)
      val state = BlackTurn().jumpStone("jump OS: 00 to OS: 02", tmpGrid, controller)
      val state1 = BlackTurn().jumpStone("jump MS: 00 to MS: 02", tmpGrid, controller)
      val stateMill = BlackTurn().jumpStone("jump IS: 00 to OS: 00", tmpGrid, controller)
      //      "WhiteTurn, by 'jump OS: 00 to OS: 02'" in {
      //        state should be(GamePlay(new WhiteTurn).state)
      //      }
      "BlackTurn, by 'jump OS: 01 to OS: 10'" in {
        state1 should be(GamePlay(new BlackTurn).state)
      }
      "TakeTurn, when player have mill" in {
        stateMill should be(GamePlay(gridBase.TakeStone(Stone.black)).state)
      }
    }
  }
}
