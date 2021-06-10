package model

import controller.base.Controller

import scala.util.{Failure, Success, Try}

case class WhiteTurn() extends State {
  override def handle(pos :String, grid: grid, controller: Controller):State = {
      val tmp = grid.moveGrid(pos, Stone.white, controller.player2)
      tmp match {
        case Success(v) =>
          grid.gridList = v
          controller.player1.setStone()
          GamePlay(new BlackTurn).state
        case Failure(f) =>
          if(f.getMessage.equals("message:")) {
            println("take a Stone!")
            controller.player1.setStone()
            GamePlay(TakeStone(Stone.white)).state
          } else {
            println("Wrong Input or already set! Please try again")
            GamePlay(new WhiteTurn).state
          }
      }
  }

  override def handle2(pos: String, grid: grid, controller :Controller): State = {
    val tmp = grid.moveStone(pos, Stone.white, controller.player2)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        GamePlay(new BlackTurn).state
      case Failure(f) =>
        if(f.getMessage.equals("message:")) {
          println("take a Stone!")
          GamePlay(TakeStone(Stone.white)).state
        } else {
          println("Wrong Input or already set! Please try again")
          GamePlay(new WhiteTurn).state
        }
    }
  }

  override def handleTakeStone(pos: String, grid: grid): State = {
    GamePlay(new WhiteTurn).state
  }

  override def jumpStone(pos: String, grid: grid, controller: Controller): State = {
    val tmp = grid.jumpStone(pos, Stone.white, controller.player1)
    tmp match {
      case Success(v) => GamePlay(new BlackTurn).state
      case Failure(f) =>
        if(f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player2.setStone()
          GamePlay(TakeStone(Stone.white)).state
        } else {
          println("Wrong Input or already set! Please try again")
          GamePlay(new WhiteTurn).state
        }
    }
  }
}
