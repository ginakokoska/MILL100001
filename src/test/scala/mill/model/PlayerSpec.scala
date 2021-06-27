package mill.model

import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "when created" should {
      val player1 = Player("player1", Stone.white)
      player1.fillStone()
      "has a name" in {
        player1.name should be("player1")
      }
      "has a Color" in {
        player1.color should be(Stone.white)
      }
      "all stoneState should be notUsed" in {
        for ((k, v) <- player1.mapState) {
          v should be(StoneState.notUsed)
        }
      }
      "placing a stone should change its state from notUsed to inGame" in {
        player1.setStone()
        val oneStoneSet = player1.countState(StoneState.inGame)
        oneStoneSet should be(1)
      }
      "taking a stone should change its state from inGame to outOfGame" in {
        player1.takeStone()
        val takeOneStone = player1.countState(StoneState.outOfGame)
        takeOneStone should be(1)
      }
    }
  }
}
