import org.scalatest.matchers
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    val player = Player("Name", Stone.black)
    "have the name" in {
      player.name should be("Name")
    }
    "have the Color" in {
      player.color should be(Stone.black)
    }
  }


  "with other Color" when {
    val otherPlayer = Player("Name", Stone.white)
    "have the name" in {
      otherPlayer.name should be("Name")
    }
    "have the Color" in {
      otherPlayer.color should be(Stone.white)
    }
  }


}
