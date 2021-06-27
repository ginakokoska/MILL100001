package mill.model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.Stone
import mill.model.gridComponent.{State, gridBase}
import scala.util.{Failure, Success}

/*
  This class implements the functions of the state pattern.
  In case of a valid play move each function will result in success, changing the state to WhiteTurn().
  In case of failure it will remain in the same state.
 */

case class BlackTurn() extends State {
  override def setStoneState(pos: String, grid: Grid, controller: Controller): State = {
    val tmp = grid.moveGrid(pos, Stone.black, controller.player2)
    tmp match {
      case Success(v) =>
        grid.gridList = v
          controller.player2.setStone()
        GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a stone!")
          gridBase.GamePlay(gridBase.TakeStone(Stone.black)).state
        } else {
          println("Wrong input or already set! Please try again")
          gridBase.GamePlay(new BlackTurn).state
        }
    }
  }

  override def moveStoneState(pos: String, grid: Grid, controller: Controller): State = {
    val tmp = grid.moveStone(pos, Stone.black, controller.player2)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        gridBase.GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a stone!")
          controller.player2.setStone()
          gridBase.GamePlay(gridBase.TakeStone(Stone.black)).state
        } else {
          println("Wrong input or already set! Please try again")
          gridBase.GamePlay(new BlackTurn).state
        }
    }
  }

  override def takeStoneState(pos: String, grid: Grid): State = gridBase.GamePlay(new BlackTurn).state

  override def jumpStoneState(pos: String, grid: Grid, controller: Controller): State = {
    val tmp = grid.jumpStone(pos, Stone.black, controller.player2)
    tmp match {
      case Success(_) => gridBase.GamePlay(new WhiteTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a stone!")
          controller.player2.setStone()
          gridBase.GamePlay(gridBase.TakeStone(Stone.black)).state
        } else {
          println("Wrong input or already set! Please try again")
          gridBase.GamePlay(new BlackTurn).state
        }
    }
  }
}
