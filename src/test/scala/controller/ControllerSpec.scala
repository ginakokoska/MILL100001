package controller

import org.scalatest.matchers.should._
import org.scalatest.wordspec._
import model.{Grid, Player, Stone}
import util._



class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {

      val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        def update: Boolean = {
          updated = true;
          updated
        }
      }
      controller.add(observer)
      controller.createPlayer1("player1", "w")
      "player 1 is created, player 1" should {
        "has name 'player1' and white Color and notify its Observer" in {
          controller.player1.name should be("player1")
          controller.player1.color should be(Stone.white)
          observer.update should be(true)
        }
      }

      "player 2 is created, player 2" should {
        controller.add(observer)
        controller.createPlayer2("player2")
        "has name 'player2' and black Color and notify its Observer" in {
          controller.player2.name should be("player2")
          controller.player2.color should be(Stone.black)
          observer.updated should be(true)
        }
      }

      "created Grid with size 1" should {
        controller.gridSize("1")
        controller.add(observer)
        "grid should be 'gridOutSquare' and notify its Observer" in {
          controller.grid.gridList.size should be(1)
          observer.updated should be(true)

        }
      }

      "created Grid with size 2" should {
        controller.add(observer)
        val gridList2 = Grid().gridOutMidSquare()
        controller.gridSize("2")
        "when created grid with size 2 create 'gridOutMidSquare' and notify its Observer" in {
          controller.grid.gridList.size should be(2)
          observer.updated should be(true)

        }
      }
//
//      controller.add(observer)
//      val grid3 = controller.gridSize("3")
//      "when created grid with size 3 create 'createFullyGrid' and notify its Observer" in {
//        grid3 should be(Grid().createFullGrid())
//        observer.updated should be(true)
//
//      }
//
//      val grid9999 = controller.gridSize("9999")
//      controller.gridSize("9999")
//      "when created grid with bad value like 9999 create 'createFullyGrid' and notify its Observer" in {
//        grid9999 should be(Grid().createFullGrid())
//        observer.updated should be(true)
//      }


  }
}