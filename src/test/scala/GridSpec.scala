import org.scalatest._
import wordspec._
import matchers.should._

class GridSpec extends AnyWordSpec with Matchers {
  "A Grid(outSquare) at the beginning" when {
    "created" should {
      Grid()
      "must have no set Nodes and return false" in {
        for (i <- 0 to 2) {
          for (j <- 0 to 2) {
            Grid().outSquare(i)(j).isSet should be(false)
            Grid().midSquare(i)(j).isSet should be (false)
            Grid().inSquare(i)(j).isSet should be(false)
          }
        }

      }
    }
  }
}
