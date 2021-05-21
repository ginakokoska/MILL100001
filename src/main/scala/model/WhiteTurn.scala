package model

import scala.util.{Failure, Success, Try}

case class WhiteTurn() extends State {
  override def handle(pos :String, grid: Grid):State = {
      val tmp = grid.moveGrid(pos, Stone.white)
      tmp match {
        case Success(v) =>
          grid.gridList = v
          GamePlay(new BlackTurn).state
        case Failure(f) =>
          println("Wrong Input or already set! Please try again")
          GamePlay(new WhiteTurn).state
      }
  }

  override def handle2(pos: String, grid: Grid): State = {
    println("Black please set your Stone: ")
    grid.gridList = grid.moveStone(pos, Stone.white)

    GamePlay(new BlackTurn).state
  }
}
