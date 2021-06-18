//package model
//
//import model.gridComponent.gridBase.{Grid, MoveCondition, Node}
//import org.scalatest.matchers.should._
//import org.scalatest.wordspec._
//
//class MoveConditionSpec extends AnyWordSpec with Matchers {
//  "A MoveConditionSpec" when {
//    val tmpGrid = Grid()
//    "player move Stone from an Corner" should {
//      tmpGrid.gridList = tmpGrid.createFullGrid()
//      tmpGrid.gridList(0)(0)(0) = Node(Some(Stone.white))
//      val legalMove = MoveCondition().moveConditionCorner(0, 0, 0, 1)
//      val illegalMove = MoveCondition().moveConditionCorner(0, 0, 0, 2)
//      "should return true when move is legal" in {
//        legalMove should be(true)
//      }
//      "should return false when move is illegal" in {
//        illegalMove should be(false)
//      }
//    }
//    "player move Stone from a MidNode" should {
//      tmpGrid.gridList = tmpGrid.createFullGrid()
//      tmpGrid.gridList(0)(0)(1) = Node(Some(Stone.white))
//      val legalMove = MoveCondition().moveConditionMid("OS:", 0, 1, 1,  0, 1)
//      val illegalMove = MoveCondition().moveConditionMid("OS:", 0, 1, 2, 0, 1)
//      "should return true when move is legal" in {
//        legalMove should be(true)
//      }
//      "should return false when move is illegal" in {
//        illegalMove should be(false)
//      }
//    }
//  }
//}
