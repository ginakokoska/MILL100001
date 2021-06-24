package model.gridComponent.gridBase

import mill.model.Stone
import mill.model.gridComponent.gridBase.{Grid, Node, millProof}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class MillProofSpec extends AnyWordSpec with Matchers {
  "A MillProofSpec" when {
    val tmpGrid = Grid()
    val tmpMill = millProof()
    "player moves to a corner" should {
      tmpGrid.gridList = Grid().createFullGrid()
      tmpGrid.gridList.head(0)(2) = Node(Some(Stone.white))
      tmpGrid.gridList.head(1)(2) = Node(Some(Stone.white))
      tmpGrid.gridList.head(2)(2) = Node(Some(Stone.white))
      val millInCorner = tmpMill.proofTypeCorner(0, 0, 2, Stone.white, tmpGrid)
      val noMillInCorner = tmpMill.proofTypeCorner(1, 2, 2, Stone.white, tmpGrid)
      "should return true when player has a mill" in {
        millInCorner should be(true)
      }
      "should return false when player doesn't have a mill" in {
        noMillInCorner should be(false)
      }
    }
    "player moves to a MidNode" should {
      tmpGrid.gridList = Grid().createFullGrid()
      tmpGrid.gridList.head(0)(2) = Node(Some(Stone.white))
      tmpGrid.gridList.head(1)(2) = Node(Some(Stone.white))
      tmpGrid.gridList.head(2)(2) = Node(Some(Stone.white))
      val millInMid = tmpMill.proofTypeMid(0, 1, 2, Stone.white, tmpGrid)
      val noMillInMid = tmpMill.proofTypeMid(1, 1, 2, Stone.white, tmpGrid)
      "should return true when player has a mill" in {
        millInMid should be(true)
      }
      "should return false when player doesn't have a mill" in {
        noMillInMid should be(false)
      }
    }
  }
}
