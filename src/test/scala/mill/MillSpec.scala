package mill

import aView.Tui
import controller.base.Controller
import model.gridComponent.gridBase.Grid
import model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class mill extends AnyWordSpec with Matchers {
  "The Mill main class" should {
    val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), grid = Grid())
    val tui = new Tui(controller)
//    "run normally until anyone wins" in {
//      val in = new ByteArrayInputStream("Q".getBytes)
//      Console.withIn(in) {
//        mill.mill.main(Array())
//      }
//    }
    "create the controller " in {
//      controller should be(new Controller(Player("player1", Stone.white), Player("player2", Stone.black), grid = Grid()))
      controller should be(controller)
    }
    "create the tui" in {
      tui should be(tui)
    }
  }
}
