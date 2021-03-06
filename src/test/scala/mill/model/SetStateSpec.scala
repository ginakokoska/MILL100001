package mill.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class SetStateSpec() extends AnyWordSpec with Matchers {
  "SetState" when {
    val player = Player("name", Stone.white)
    player.fillStone()
    "called should return" should {
      val playerStateBefore = SetState().getState(player)
      for (i <- 1 to 9) {
        player.setStone() //take States from Stone to inGame
      }
      val playerState = SetState().getState(player)
      "SetState when Player has more than 0 Stones in State notUsed" in {
        playerStateBefore should be(SetState())
      }
      "MoveState when Player has 0 Stones in State notUsed" in {
        playerState should be(MoveState())
      }
    }
  }
}
