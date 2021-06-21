package mill.model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.Stone
import mill.model.gridComponent.{State, gridBase}

import scala.util.{Failure, Success}

case class TakeStone(color: Stone.Value) extends State {
  override def handle(pos: String, grid: Grid, controller: Controller): State = GamePlay(new WhiteTurn).state

  override def handle2(pos: String, grid: Grid, controller: Controller): State = gridBase.GamePlay(new WhiteTurn).state

  override def handleTakeStone(pos: String, grid: Grid): State = {
    if (color == Stone.white) {
      grid.takePos(pos, Stone.black) match {
        case Success(_) => GamePlay(new BlackTurn).state
        case Failure(e) =>
          println(e.getMessage)
          gridBase.GamePlay(TakeStone(Stone.white)).state
      }
    } else {
      grid.takePos(pos, Stone.white) match {
        case Success(_) => gridBase.GamePlay(new WhiteTurn).state
        case Failure(e) =>
          println(e.getMessage)
          gridBase.GamePlay(TakeStone(Stone.black)).state
      }
    }
  }

  override def jumpStone(pos: String, grid: Grid, controller: Controller): State = gridBase.GamePlay(new WhiteTurn).state
}