package model.gridComponent.gridBase

import mill.model.Stone
import mill.model.gridComponent.gridBase.Node
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class NodeSpec extends AnyWordSpec with Matchers{
  "A Node" when {
    "not set to any value " should {
      val emptyNode = Node(None)
      "should not be set" in {
        emptyNode.isSet should be(false)
      }
      "and its color should be None" in {
        emptyNode.isColor should be(None)
      }
    }
    "Node set by white" should {
      val NodeWhite = Node(Some(Stone.white))
      "should be set" in {
        NodeWhite.isSet should be(true)
      }
      "and its color should be white" in {
        NodeWhite.isColor should be(Some(Stone.white))
      }
    }
    "Node set by black" should {
      val NodeBlack = Node(Some(Stone.black))
      "should be set also" in {
        NodeBlack.isSet should be(true)
      }
      "and its color should be black" in {
        NodeBlack.isColor should be(Some(Stone.black))
      }
    }
  }
}
