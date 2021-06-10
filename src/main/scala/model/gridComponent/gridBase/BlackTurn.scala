package model.gridComponent.gridBase

import controller.base.Controller
import model.gridComponent.gridBase
import model.playerComponent.Stone

import scala.util.{Failure, Success}

case class BlackTurn() extends State {
  override def handle(pos: String, grid: Grid, controller: Controller): State = {
    val tmp = grid.moveGrid(pos, Stone.black, controller.player1)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        controller.player2.setStone()
        GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          gridBase.GamePlay(gridBase.TakeStone(Stone.black)).state
        } else {
          println("Wrong Input or already set! Please try again")
          gridBase.GamePlay(new BlackTurn).state
        }
    }
  }

  override def handle2(pos: String, grid: Grid, controller: Controller): State = {
    val tmp = grid.moveStone(pos, Stone.black, controller.player1)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        gridBase.GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player2.setStone()
          gridBase.GamePlay(gridBase.TakeStone(Stone.black)).state
        } else {
          println("Wrong Input or already set! Please try again")
          gridBase.GamePlay(new BlackTurn).state
        }
    }
  }

  override def handleTakeStone(pos: String, grid: Grid): State = gridBase.GamePlay(new BlackTurn).state

  override def jumpStone(pos: String, grid: Grid, controller: Controller): State = {
    val tmp = grid.jumpStone(pos, Stone.black, controller.player1)
    tmp match {
      case Success(v) => gridBase.GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player2.setStone()
          gridBase.GamePlay(gridBase.TakeStone(Stone.black)).state
        } else {
          println("Wrong Input or already set! Please try again")
          gridBase.GamePlay(new BlackTurn).state
        }
    }
  }
}
