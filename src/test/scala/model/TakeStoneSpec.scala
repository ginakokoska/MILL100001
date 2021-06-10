package model

import controller.base.Controller
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class TakeStoneSpec extends AnyWordSpec with Matchers{
  "A TakeStone" when {
    val tmpGrid = grid()
    tmpGrid.gridList = grid().createFullGrid()
    val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), tmpGrid)
    "Stone" should {
      tmpGrid.gridList(1)(0)(0) = Node(Some(Stone.black))
      val stateHandle = TakeStone(Stone.white).handle("OS: 00", tmpGrid, controller)
      val stateHandle2 = TakeStone(Stone.white).handle("move OS: 00 to OS: 01", tmpGrid, controller)
      val stateJumpStone = TakeStone(Stone.white).handle("jump OS: 01 to OS: 10", tmpGrid, controller)
      val stateTakeStoneCorrectly = TakeStone(Stone.white).handleTakeStone("MS: 00", tmpGrid)
      val stateTakeStoneInCorrectly = TakeStone(Stone.white).handleTakeStone("MS: 01", tmpGrid)
      "is moving should always return WhiteTurn" in {
        stateHandle should be(GamePlay(WhiteTurn()).state)
        stateHandle2 should be(GamePlay(WhiteTurn()).state)
        stateJumpStone should be(GamePlay(WhiteTurn()).state)
      }
      "is take by players correctly" in {
        stateTakeStoneCorrectly should be(GamePlay(BlackTurn()).state)
      }
      "is take by players incorrectly" in {
        stateTakeStoneInCorrectly should be(GamePlay(TakeStone(Stone.white)).state)
      }
      tmpGrid.gridList(2)(0)(0) = Node(Some(Stone.white))
      val stateTakeStoneCorrectlyBlack = TakeStone(Stone.black).handleTakeStone("IS: 00", tmpGrid)
      val stateTakeStoneInCorrectlyBlack = TakeStone(Stone.black).handleTakeStone("IS: 01", tmpGrid)
      "is take by players correctly with Black Color" in {
        stateTakeStoneCorrectlyBlack should be(GamePlay(WhiteTurn()).state)
      }
      "is take by players incorrectly with Black Color" in {
        stateTakeStoneInCorrectlyBlack should be(GamePlay(TakeStone(Stone.black)).state)
      }
    }
  }
}
