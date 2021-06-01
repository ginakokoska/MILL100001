package model

import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class MillProofSpec extends AnyWordSpec with Matchers {
  "A MillProofSpec" when {
    val tmpGrid = grid()
    "player takes a move to a corner" should {
      tmpGrid.gridList = grid().createFullGrid()
      tmpGrid.gridList(0)(0)(2) = Node(Some(Stone.white))
      tmpGrid.gridList(0)(1)(2) = Node(Some(Stone.white))
      tmpGrid.gridList(0)(2)(2) = Node(Some(Stone.white))
      val millInCorner = millProof().proofTypeCorner(0, 0, 2, Stone.white, tmpGrid)
      val noMillInCorner = millProof().proofTypeCorner(1, 2, 2, Stone.white, tmpGrid)
      "should return true when player have mill" in {
        millInCorner should be(true)
      }
      "should return false when player dont have mill" in {
        noMillInCorner should be(false)
      }
    }
    "player takes a move to a MidNode" should {
      tmpGrid.gridList = grid().createFullGrid()
      tmpGrid.gridList(0)(0)(2) = Node(Some(Stone.white))
      tmpGrid.gridList(0)(1)(2) = Node(Some(Stone.white))
      tmpGrid.gridList(0)(2)(2) = Node(Some(Stone.white))
      val millInMid = millProof().proofTypeMid(0, 1, 2, Stone.white, tmpGrid)
      val noMillInMid = millProof().proofTypeMid(1, 1, 2, Stone.white, tmpGrid)
      "should return true when player have mill" in {
        millInMid should be(true)
      }
      "should return false when player dont have mill" in {
        noMillInMid should be(false)
      }
    }
  }
}
