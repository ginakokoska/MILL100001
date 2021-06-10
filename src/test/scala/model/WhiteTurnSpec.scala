package model

import controller.base.Controller
import org.scalatest.matchers.should._
import org.scalatest.wordspec._


case class WhiteTurnSpec() extends AnyWordSpec with Matchers {
  "WhiteTurn" when {
    val tmpGrid = grid()
    tmpGrid.gridList = grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), tmpGrid)
    "handle() should be return " should {
      val state = WhiteTurn().handle("OS: 00", tmpGrid, controller)
      val state1 = WhiteTurn().handle("OS: 00", tmpGrid, controller)
      WhiteTurn().handle("MS: 00", tmpGrid, controller)
      WhiteTurn().handle("MS: 01", tmpGrid,controller)
      val stateMill = WhiteTurn().handle("MS: 02", tmpGrid, controller)
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
      WhiteTurn().handle("OS: 00", tmpGrid, controller)
      WhiteTurn().handle("MS: 01", tmpGrid, controller)
      //      BlackTurn().handle("OS: 01", tmpGrid, controller)
      WhiteTurn().handle("OS: 12", tmpGrid, controller)
      val state = WhiteTurn().handle2("move MS: 01 to OS: 01", tmpGrid, controller)
      val state1 = WhiteTurn().handle2("move OS: 00 to OS: 02", tmpGrid, controller)
      val stateMill = WhiteTurn().handle2("move OS: 12 to OS: 02", tmpGrid, controller)
      "WhiteTurn, by moving OS: 00 to OS: 01" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "BlackTurn again, by moving OS: 00 to OS:02" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeStone, when player have mill" in {
        stateMill should be(GamePlay(TakeStone(Stone.white)).state)
      }
    }
    "handleTakeStone, should be return" should {
      val state = WhiteTurn().handleTakeStone("MS: 22", tmpGrid)
      "always WhiteTurn" in {
        state should be(GamePlay(new WhiteTurn).state)
      }
    }
    "jumpStone, should be return" should {
      tmpGrid.createFullGrid()
      WhiteTurn().handle("OS: 00", tmpGrid, controller)
      WhiteTurn().handle("OS: 01", tmpGrid, controller)
      WhiteTurn().handle("IS: 00", tmpGrid, controller)
      val state = WhiteTurn().jumpStone("jump OS: 00 to OS: 02", tmpGrid, controller)
      val state1 = WhiteTurn().jumpStone("jump MS: 00 to MS: 02", tmpGrid, controller)
      val stateMill = WhiteTurn().jumpStone("jump IS: 00 to OS: 00", tmpGrid, controller)
      "WhiteTurn, by 'jump OS: 00 to OS: 02'" in {
        state should be(GamePlay(new BlackTurn).state)
      }
      "BlackTurn, by 'jump OS: 01 to OS: 10'" in {
        state1 should be(GamePlay(new WhiteTurn).state)
      }
      "TakeTurn, when player have mill" in {
        stateMill should be(GamePlay(TakeStone(Stone.white)).state)
      }
    }
  }
}

