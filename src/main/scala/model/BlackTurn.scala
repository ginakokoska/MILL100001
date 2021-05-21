package model

import scala.util.{Failure, Success}

case class BlackTurn() extends State{
  override def handle(pos :String, grid: Grid):State = {
    val tmp = grid.moveGrid(pos, Stone.white)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        GamePlay(new WhiteTurn).state
      case Failure(_) =>
        println("Wrong Input or already set! Please try again")
        GamePlay(new BlackTurn).state
    }
  }

  override def handle2(pos :String, grid: Grid): State = {
    println("White please set your Stone: ")
    grid.gridList = grid.moveStone(pos , Stone.black)

    GamePlay(new WhiteTurn).state
  }
}
