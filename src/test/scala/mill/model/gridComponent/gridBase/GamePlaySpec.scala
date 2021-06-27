package mill.model.gridComponent.gridBase

import mill.controller.controllerBase.Controller
import mill.model.gridComponent.gridBase
import mill.model.gridComponent.gridBase._
import mill.model.{Player, Stone}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class GamePlaySpec extends AnyWordSpec with Matchers{
  "GamePlay" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "called by setStoneState()" should {
      val stateBlack = GamePlay(WhiteTurn()).setStoneState("OS: 00", tmpGrid, controller)
      val stateWhite = GamePlay(BlackTurn()).setStoneState("OS: 10", tmpGrid, controller)
      "with WhiteTurn() should return BlackTurn()" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn() should return WhiteTurn()" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
    "called by moveStoneState()" should {
      val stateBlack = GamePlay(WhiteTurn()).moveStoneState("move OS: 00 to OS: 01", tmpGrid, controller)
      val stateWhite = GamePlay(BlackTurn()).moveStoneState("move OS: 10 to OS: 20", tmpGrid, controller)
      "with WhiteTurn() should return BlackTurn()" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn() should return WhiteTurn()" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
    "called by jumpStoneState" should {
      val stateBlack = GamePlay(WhiteTurn()).jumpStoneState("jump OS: 01 to IS: 02", tmpGrid, controller)
      val stateWhite = GamePlay(BlackTurn()).jumpStoneState("jump OS: 20 to IS: 20", tmpGrid, controller)
      "with WhiteTurn() should return BlackTurn()" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn() should return WhiteTurn()" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
    "called by takeStoneState" should {
      tmpGrid.createFullGrid()
      WhiteTurn().setStoneState("IS: 02", tmpGrid, controller)
      BlackTurn().setStoneState("IS: 20", tmpGrid, controller)
      val stateBlack = GamePlay(TakeStone(Stone.white)).takeStoneState("IS: 20", tmpGrid)
      val stateWhite = GamePlay(gridBase.TakeStone(Stone.black)).takeStoneState("IS: 02", tmpGrid)
      "with WhiteTurn() should return BlackTurn()" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn() should return WhiteTurn()" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
  }
}
