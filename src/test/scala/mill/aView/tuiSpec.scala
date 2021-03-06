package mill.aView

import mill.aView.Tui
import mill.controller.controllerBase.Controller
import mill.model.gridComponent.gridBase.{BlackTurn, Grid, TakeStone, WhiteTurn}
import mill.model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import mill.util.Observer

class tuiSpec extends AnyWordSpec with Matchers {
  "A Mill Tui" when {
    val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), grid = Grid())
    val tui = new Tui(controller)
    "game has started" should {
      "should start with Welcome" in {
        val welString = tui.startGame()
        welString should be("Welcome to our game, Mill!\n" + "Players, please enter your name and select color w(hite) or b(lack):")
      }
      "then tui should create Players" in {
        tui.createPlayers("Luca w", "Gina")
        controller.player1.name should be("Luca")
        controller.player1.color should be(Stone.white)
        controller.player2.name should be("Gina")
        controller.player2.color should be(Stone.black)
        tui.createPlayers("Luca", "Gina")
        controller.player1.name should be("Luca")
        controller.player1.color should be(Stone.white)
        controller.player2.name should be("Gina")
        controller.player2.color should be(Stone.black)
      }

      "then tui should create a Grid, when size 1" in {
        tui.createGrid("1")
        controller.grid.gridList.size should be(1)
      }
      "then tui should create a Grid, when size 2" in {
        tui.createGrid("2")
        controller.grid.gridList.size should be(2)
      }
      "then tui should create a Grid, when size 3 or any other value" in {
        tui.createGrid("3")
        controller.grid.gridList.size should be(3)
        tui.createGrid("999")
        controller.grid.gridList.size should be(3)
      }
      "player should be able to set a Stone" in {
        tui.moveTui("OS: 00")
        controller.grid.gridList.head(0)(0).isSet should be(true)
      }
      "gameState should print the state" in {
        controller.gamePlayState = WhiteTurn()
        tui.gameState() should be(Console.println("White please set your Stone:"))
        controller.gamePlayState = BlackTurn()
        tui.gameState() should be(Console.println("Black please set your Stone:"))
        controller.gamePlayState = TakeStone(Stone.white)
        tui.gameState() should be(Console.println(""))
      }
      "stoneState should print what happens" in {
        tui.stoneSet("set to", "OS: 00") should be(Console.println("Stone set to OS: 00"))
        tui.stoneSet("moved from OS: 00 to", "OS: 01") should be(Console.println("Stone moved from OS: 00 to OS: 01"))
        tui.stoneSet("removed from", "OS: 00") should be(Console.println("Stone removed from OS: 00"))
      }
      "update should say hello and print a grid" in {
        tui.update should be(true)
        Console.println("welcome Luca(white) and Gina(black)")
        Console.println("OS: 00--------------------OS: 01--------------------OS: 02\n" +
          "|                           |                         |\n" +
          "|                           |                         |\n" +
          "|                           |                         |\n" +
          "|        MS: 00-----------MS: 01-----------MS: 02     |\n" +
          "|          |                |               |         |\n" +
          "|          |                |               |         |\n" +
          "|          |     IS: 00---IS: 01---IS: 02   |         |\n" +
          "|          |      |                 |       |         |\n" +
          "|          |      |                 |       |         |\n" +
          "OS: 10---MS: 10--IS: 10            Is: 12--MS:12----OS: 12\n" +
          "|          |      |                 |       |         |\n" +
          "|          |      |                 |       |         |\n" +
          "|          |     IS: 20---IS: 21---IS: 22   |         |\n" +
          "|          |                |               |         |\n" +
          "|          |                |               |         |\n" +
          "|        MS: 20-----------MS: 21-----------MS: 22     |\n" +
          "|                           |                         |\n" +
          "|                           |                         |\n" +
          "|                           |                         |\n" +
          "OS: 20--------------------OS: 21--------------------OS: 22")
      }
    }
  }
}