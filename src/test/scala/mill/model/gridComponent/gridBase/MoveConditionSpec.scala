package mill.model.gridComponent.gridBase

import mill.model.Stone
import mill.model.gridComponent.gridBase.{Grid, MoveCondition, Node}
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class MoveConditionSpec extends AnyWordSpec with Matchers {
  "A MoveConditionSpec" when {
    val tmpGrid = Grid()
    "player moves Stone from a corner" should {
      tmpGrid.gridList = tmpGrid.createFullGrid()
      tmpGrid.gridList.head(0)(0) = Node(Some(Stone.white))
      val legalMove = MoveCondition().moveConditionCorner(0, 0, 0, 1,0,0)
      val illegalMove = MoveCondition().moveConditionCorner(0, 0, 0, 2,0,0)
      "should return true when move is legal" in {
        legalMove should be(true)
      }
      "should return false when move is illegal" in {
        illegalMove should be(false)
      }
    }
    "player moves Stone from a MidNode" should {
      tmpGrid.gridList = tmpGrid.createFullGrid()
      tmpGrid.gridList.head(0)(1) = Node(Some(Stone.white))
      val legalMove = MoveCondition().moveConditionMid("OS:", 0, 1, 1,  0, 1)
      val illegalMove = MoveCondition().moveConditionMid("OS:", 0, 1, 2, 0, 1)
      "should return true when move is legal" in {
        legalMove should be(true)
      }
      "should return false when move is illegal" in {
        illegalMove should be(false)
      }
    }
  }
}
