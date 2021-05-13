package model

import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    val player1 = Player("player1", Stone.black)
    val player1StartList = player1.mapState.size
    "have the name" in {
      player1.name should be("player1")
      player1StartList should be(9)
      player1.mapState(0) should be(Stone.black)
    }
    "have the Color" in {
      player1.color should be(Stone.black)
    }
    val player2 = Player("player2", Stone.white)
    val player2StartList = player2.mapState.size
    "player 2 should have name" in {
      player2.name should be("player2")
      player2StartList should be(9)
      player2.mapState(3) should be(Stone.white)
    }
    "player 2 should have color" in {
      player2.color should be(Stone.white)
    }
    val welcomeString = player1.playerToString(player1,player2)
    "welcomeString should" in {
      welcomeString should be("welcome player1(black) and player2(white)")
    }
    val oldSize1 = player1.mapState.size
    val removeStone1 = player1.numStones().size
    "should remove one Stone of player 1" in {
      removeStone1 should be(oldSize1 -1)
    }
    val oldSize2 = player2.mapState.size
    val removeStone2 = player2.numStones().size
    "should remove one Stone of player 2" in {
      removeStone2 should be(oldSize2 -1)
    }
  }

}
