//package model
//
//import controller.Controller
//import org.scalatest.matchers.should._
//import org.scalatest.wordspec._
//
//
//case class BlackTurnSpec() extends AnyWordSpec with Matchers {
//  "WhiteTurn" when {
//    val grid = Grid()
//    grid.createFullGrid()
//    val state = GamePlay(BlackTurn()).state.handle("OS:", '0', '0', grid)
//    "should" should {
//      "return BlackState" in {
//        state should be(WhiteTurn())
//      }
//    }
//  }
//}
