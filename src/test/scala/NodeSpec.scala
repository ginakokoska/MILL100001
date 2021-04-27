import org.scalatest._
import wordspec._
import matchers.should._

import model._

class NodeSpec extends AnyWordSpec with Matchers{
  "A Node" when {
    "not set to any value " should {
      val emptyNode = Node(None)
      "should not be set" in {
        emptyNode.isSet should be(false)
      }
    }
    "Node set by white" should {
      val NodeWhite = Node(Some(Stone.white))
      "should be set" in {
        NodeWhite.isSet should be(true)
      }
    }
    "Node set by black" should {
      val NodeBlack = Node(Some(Stone.black))
      "should be set also" in {
        NodeBlack.isSet should be(true)
      }
    }
  }
}
