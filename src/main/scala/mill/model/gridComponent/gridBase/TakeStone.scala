package mill.model.gridComponent.gridBase

import mill.controller.controllerBase.Controller
import mill.mill.controller
import mill.model.Stone
import mill.model.gridComponent.{State, gridBase}
import scala.util.{Failure, Success}

/*
  This class ensures that stones of the opposite color are taken, that are not inside a mill.
  In case of success it will change state to the other player.
  In case of failure it will remain in the same state.
 */

case class TakeStone(color: Stone.Value) extends State {
  override def setStoneState(pos: String, grid: Grid, controller: Controller): State = GamePlay(new WhiteTurn).state

  override def moveStoneState(pos: String, grid: Grid, controller: Controller): State = gridBase.GamePlay(new WhiteTurn).state

  override def takeStoneState(pos: String, grid: Grid): State = {
    if (color == Stone.white) {
      grid.takePos(pos, Stone.black) match {
        case Success(_) =>
          controller.player2.takeStone()
          GamePlay(new BlackTurn).state
        case Failure(e) =>
          println(e.getMessage)
          gridBase.GamePlay(TakeStone(Stone.white)).state
      }
    } else {
      grid.takePos(pos, Stone.white) match {
        case Success(_) =>
          controller.player1.takeStone()
          gridBase.GamePlay(new WhiteTurn).state
        case Failure(e) =>
          println(e.getMessage)
          gridBase.GamePlay(TakeStone(Stone.black)).state
      }
    }
  }

  override def jumpStoneState(pos: String, grid: Grid, controller: Controller): State = gridBase.GamePlay(new WhiteTurn).state
}
