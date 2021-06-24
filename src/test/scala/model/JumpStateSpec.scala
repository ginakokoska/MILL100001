package model

import mill.model.{JumpState, MoveState, Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class JumpStateSpec() extends AnyWordSpec with Matchers {
  "A Jumpstate" when {
    val player = Player("name", Stone.white)
    player.fillStone()
    "should return" should {
      val playerStateBefore = JumpState().getState(player)
      for (i <- 1 to 9) {
        player.setStone() //take States from Stone to inGame
      }
      for (i <- 1 to 6) {
        player.takeStone() //take States from Stone to OutOfGame
      }
      val playerState = JumpState().getState(player)
      "JumpState when Player have 6 Stones in State OutOfGame" in {
        playerState should be(JumpState())
      }
      "MoveState when Player have less then 6 Stones in State OutOfGame" in {
        playerStateBefore should be(MoveState())
      }
    }
  }
}
