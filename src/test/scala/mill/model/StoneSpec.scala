package mill.model

import mill.model.Stone
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec with Matchers {
  "A Stone" when {
    val whiteStone = Stone.white
    "when it set to white" in {
      whiteStone should be(Stone.white)
    }
    val blackStone = Stone.black
    "when it set to black" in {
      blackStone should be(Stone.black)
    }
  }
}
