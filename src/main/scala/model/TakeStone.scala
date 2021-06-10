package model

import controller.base.Controller

import scala.util.{Failure, Success}

case class TakeStone(color :Stone.Value) extends State {
  override def handle(pos: String, grid: grid, controller: Controller): State = GamePlay(new WhiteTurn).state

  override def handle2(pos: String, grid: grid, controller: Controller): State = GamePlay(new WhiteTurn).state

  override def handleTakeStone(pos :String, grid :grid): State = {
    if(color == Stone.white) {
      grid.takePos(pos, Stone.black) match {
        case Success(v) => GamePlay(new BlackTurn).state
        case Failure(e) =>
          println(e.getMessage)
          GamePlay(new TakeStone(Stone.white)).state
      }
    } else {
      grid.takePos(pos, Stone.white) match {
        case Success(v) => GamePlay(new WhiteTurn).state
        case Failure(e) =>
          println(e.getMessage)
          GamePlay(new TakeStone(Stone.black)).state
      }
    }
  }

  override def jumpStone(pos: String, grid: grid, controller: Controller): State = GamePlay(new WhiteTurn).state
}
