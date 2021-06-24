package model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase
import mill.model.gridComponent.gridBase._
import mill.model.{Player, Stone}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class TakeStoneSpec extends AnyWordSpec with Matchers{
  "A TakeStone state initialized by a Color  " when {
    val tmpGrid = Grid()
    tmpGrid.gridList = Grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
    "by building a mill through either of the following actions:" +
      "-> build mill whilst setting a stone" +
      "-> build mill whilst moving a stone" +
      "-> build mill whilst jumping with a stone " should {
      tmpGrid.gridList(1)(0)(0) = Node(Some(Stone.black))
      val stateHandle = TakeStone(Stone.white).handle("OS: 00", tmpGrid, controller)
      val stateHandle2 = gridBase.TakeStone(Stone.white).handle("move OS: 00 to OS: 01", tmpGrid, controller)
      val stateJumpStone = gridBase.TakeStone(Stone.white).handle("jump OS: 01 to OS: 10", tmpGrid, controller)
      val stateTakeStoneCorrectly = gridBase.TakeStone(Stone.white).handleTakeStone("MS: 00", tmpGrid)
      val stateTakeStoneInCorrectly = gridBase.TakeStone(Stone.white).handleTakeStone("MS: 01", tmpGrid)
      "should result in either: " +
        "->  WhiteTurn() " +
        "->  BlackTurn() " in {
        stateHandle should be(GamePlay(WhiteTurn()).state)
        stateHandle2 should be(GamePlay(WhiteTurn()).state)
        stateJumpStone should be(GamePlay(WhiteTurn()).state)
      }
      "if White took a stone correctly" in {
        stateTakeStoneCorrectly should be(GamePlay(BlackTurn()).state)
      }
      "if White took a stone incorrectly, the state remains in TakeStone(blackstone)" in {
        stateTakeStoneInCorrectly should be(GamePlay(gridBase.TakeStone(Stone.white)).state)
      }
      tmpGrid.gridList(2)(0)(0) = Node(Some(Stone.white))
      val stateTakeStoneCorrectlyBlack = gridBase.TakeStone(Stone.black).handleTakeStone("IS: 00", tmpGrid)
      val stateTakeStoneInCorrectlyBlack = gridBase.TakeStone(Stone.black).handleTakeStone("IS: 01", tmpGrid)
      "if Black player took a stone correctly" in {
        stateTakeStoneCorrectlyBlack should be(GamePlay(WhiteTurn()).state)
      }
      "if Black player took a stone incorrectly, the state remains in TakeStone(whitestone)" in {
        stateTakeStoneInCorrectlyBlack should be(GamePlay(gridBase.TakeStone(Stone.black)).state)
      }
    }
  }
}
