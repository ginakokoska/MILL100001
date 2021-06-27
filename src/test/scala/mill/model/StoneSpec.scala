package mill.model

import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec with Matchers {
  "Stone" when {
    val whiteStone = Stone.white
    "when set to white should be white" in {
      whiteStone should be(Stone.white)
    }
    val blackStone = Stone.black
    "when set to black should be black" in {
      blackStone should be(Stone.black)
    }
  }
}
