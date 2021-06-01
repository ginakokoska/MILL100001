package model

import controller.Controller

import scala.util.{Failure, Success}

case class BlackTurn() extends State{
  override def handle(pos :String, grid: grid, controller: Controller):State = {
    val tmp = grid.moveGrid(pos, Stone.black, controller.player1)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        controller.player2.setStone()
        GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if(f.getMessage.equals("message:")) {
          println("take a Stone!")
          GamePlay(TakeStone(Stone.black)).state
        } else {
          println("Wrong Input or already set! Please try again")
          GamePlay(new BlackTurn).state
        }
    }
  }

  override def handle2(pos :String, grid: grid, controller: Controller): State = {
    val tmp = grid.moveStone(pos, Stone.black, controller.player1)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if(f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player2.setStone()
          GamePlay(TakeStone(Stone.black)).state
        } else {
          println("Wrong Input or already set! Please try again")
          GamePlay(new BlackTurn).state
        }
    }
  }

  override def handleTakeStone(pos: String, grid: grid): State = GamePlay(new BlackTurn).state

  override def jumpStone(pos: String, grid: grid, controller: Controller): State = {
    val tmp = grid.jumpStone(pos, Stone.black, controller.player1)
    tmp match {
      case Success(v) => GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if(f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player2.setStone()
          GamePlay(TakeStone(Stone.black)).state
        } else {
          println("Wrong Input or already set! Please try again")
          GamePlay(new BlackTurn).state
        }
    }
  }
}
