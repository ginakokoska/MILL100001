import org.scalatest._
import wordspec._
import matchers.should._

class NodeSpec extends AnyWordSpec with Matchers{
  "A Node" when {
    "not set to any value " should {
      val emptyNode = Node(0)
      "have value 0" in {
        emptyNode.value should be(0)
      }
      "not be set" in {
        emptyNode.isSet should be(false)
      }
    }
    "set to white(1) value" should {
      val NodeWhite = Node(1)
      "return value 1" in {
        NodeWhite.value should be(1)
      }
      "be set" in {
        NodeWhite.isSet should be(true)
      }
    }
    "set to black(2) value" should {
      val NodeBlack = Node(2)
      "return value 2" in {
        NodeBlack.value should be(2)
      }
      "be set" in {
        NodeBlack.isSet should be(true)
      }
    }
  }
}
