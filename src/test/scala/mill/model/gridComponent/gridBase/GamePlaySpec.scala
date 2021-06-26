package mill.model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase
import mill.model.gridComponent.gridBase._
import mill.model.{Player, Stone}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class GamePlaySpec extends AnyWordSpec with Matchers{
  "A GamePlay" when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "handle" should {
      val stateBlack = GamePlay(WhiteTurn()).handle("OS: 00", tmpGrid, controller)
      val stateWhite = GamePlay(BlackTurn()).handle("OS: 10", tmpGrid, controller)
      "with WhiteTurn should return BlackTurn" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn should return WhiteTurn" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
    "handle2" should {
      val stateBlack = GamePlay(WhiteTurn()).handle2("move OS: 00 to OS: 01", tmpGrid, controller)
      val stateWhite = GamePlay(BlackTurn()).handle2("move OS: 10 to OS: 20", tmpGrid, controller)
      "with WhiteTurn should return BlackTurn" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn should return WhiteTurn" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
    "jumpStone" should {
      val stateBlack = GamePlay(WhiteTurn()).jumpStone("jump OS: 01 to IS: 02", tmpGrid, controller)
      val stateWhite = GamePlay(BlackTurn()).jumpStone("jump OS: 20 to IS: 20", tmpGrid, controller)
      "with WhiteTurn should return BlackTurn" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn should return WhiteTurn" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
    "handleTakeStone" should {
      tmpGrid.createFullGrid()
      WhiteTurn().handle("IS: 02", tmpGrid, controller)
      BlackTurn().handle("IS: 20", tmpGrid, controller)
      val stateBlack = GamePlay(TakeStone(Stone.white)).handleTakeStone("IS: 20", tmpGrid)
      val stateWhite = GamePlay(gridBase.TakeStone(Stone.black)).handleTakeStone("IS: 02", tmpGrid)
      "with WhiteTurn should return BlackTurn" in {
        stateBlack should be(GamePlay(new BlackTurn).state)
      }
      "with BlackTurn should return WhiteTurn" in {
        stateWhite should be(GamePlay(new WhiteTurn).state)
      }
    }
  }
}
