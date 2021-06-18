//package model
//
//import mill.controller.base.Controller
//import mill.model.{Player, gridComponent}
//import model.gridComponent.gridBase
//import model.gridComponent.gridBase.{BlackTurn, GamePlay, Grid, Node, TakeStone, WhiteTurn}
//import org.scalatest.matchers.should._
//import org.scalatest.wordspec._
//
//class TakeStoneSpec extends AnyWordSpec with Matchers{
//  "A TakeStone" when {
//    val tmpGrid = Grid()
//    tmpGrid.gridList = Grid().createFullGrid()
//    val controller = new Controller(Player("player1", Stone.white), mill.model.Player("player2", Stone.black), tmpGrid)
//    "Stone" should {
//      tmpGrid.gridList(1)(0)(0) = Node(Some(Stone.black))
//      val stateHandle = TakeStone(Stone.white).handle("OS: 00", tmpGrid, controller)
//      val stateHandle2 = gridBase.TakeStone(Stone.white).handle("move OS: 00 to OS: 01", tmpGrid, controller)
//      val stateJumpStone = gridBase.TakeStone(Stone.white).handle("jump OS: 01 to OS: 10", tmpGrid, controller)
//      val stateTakeStoneCorrectly = gridBase.TakeStone(Stone.white).handleTakeStone("MS: 00", tmpGrid)
//      val stateTakeStoneInCorrectly = gridBase.TakeStone(Stone.white).handleTakeStone("MS: 01", tmpGrid)
//      "is moving should always return WhiteTurn" in {
//        stateHandle should be(GamePlay(WhiteTurn()).state)
//        stateHandle2 should be(GamePlay(WhiteTurn()).state)
//        stateJumpStone should be(GamePlay(WhiteTurn()).state)
//      }
//      "is take by players correctly" in {
//        stateTakeStoneCorrectly should be(GamePlay(BlackTurn()).state)
//      }
//      "is take by players incorrectly" in {
//        stateTakeStoneInCorrectly should be(GamePlay(gridBase.TakeStone(Stone.white)).state)
//      }
//      tmpGrid.gridList(2)(0)(0) = Node(Some(Stone.white))
//      val stateTakeStoneCorrectlyBlack = gridBase.TakeStone(Stone.black).handleTakeStone("IS: 00", tmpGrid)
//      val stateTakeStoneInCorrectlyBlack = gridBase.TakeStone(Stone.black).handleTakeStone("IS: 01", tmpGrid)
//      "is take by players correctly with Black Color" in {
//        stateTakeStoneCorrectlyBlack should be(GamePlay(WhiteTurn()).state)
//      }
//      "is take by players incorrectly with Black Color" in {
//        stateTakeStoneInCorrectlyBlack should be(GamePlay(gridBase.TakeStone(Stone.black)).state)
//      }
//    }
//  }
//}
