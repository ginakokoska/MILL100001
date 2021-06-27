package mill.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class MoveStateSpec() extends AnyWordSpec with Matchers {
  "MoveState" when {
    val player = Player("name", Stone.white)
    player.fillStone()
    "called should return" should {
      val playerStateBefore = MoveState().getState(player)
      for (i <- 1 to 9) {
        player.setStone() //take States from Stone to inGame
      }
      for (i <- 1 to 5) {
        player.takeStone() //take States from Stone to OutOfGame
      }
      val playerState = MoveState().getState(player)
      "MoveState when Player have 0 Stones in State notUsed and less then 6 in State outOfGame" in {
        playerState should be(MoveState())
      }
      "JumpState when Player has 6 Stones in State OutOfGame" in {
        player.takeStone()  //takes one more Stone in State outOfGame
        val playerStateAfterInc = MoveState().getState(player)
        playerStateAfterInc should be(JumpState())
      }
      "SetState when Player hasn't set all nine Stones" in {
        playerStateBefore should be(SetState())
      }
    }
  }
}
