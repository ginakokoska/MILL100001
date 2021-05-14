package model

import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class GamePlaySpec extends AnyWordSpec with Matchers{
  "A GamePlay" when {
    val gamePlayWhite = GamePlay(new WhiteTurn)
    val grid = Grid()
    grid.gridList = grid.createFullGrid()
    val state1 = gamePlayWhite.state.handle("OS:", '0', '0', grid)
    "started with White" should {
      "state should return BlackState" in {
        state1 should be(new BlackTurn())
      }
      val state2 = GamePlay(state1).state.handle("MS:", '0', '1', grid)
      "and continues with BlackState and returns WhiteState" in {
        state2 should be(WhiteTurn())
      }
      val state3 = GamePlay(state2).state.handle("IS:", '2', '2', grid)
      "and continues with WhiteState and returns BlackState" in {
        state3 should be(BlackTurn())
      }
    }
  }

}
