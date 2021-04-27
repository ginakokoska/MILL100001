import controller.Controller
import aView._
import model.{Grid, Player, Stone}


object main {
  val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), Grid())
  val tui = new tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    tui.startGame
  }
}
