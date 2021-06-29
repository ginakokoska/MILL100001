package mill.model.gridComponent.gridBase

import mill.model.{Player, Stone}
import mill.model.gridComponent.{State, gridBase}

import scala.util.{Failure, Success}

/*
  This class implements the functions of the state pattern.
  In case of a valid play move each function will result in success, changing the state to BlackTurn().
  In case of failure it will remain in the same state.
 */

case class WhiteTurn() extends State {
  override def setStoneState(pos: String, grid: Grid, player: Player): State = {
    val tmp = grid.setStone(pos, Stone.white, player)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        player.setStone()
        GamePlay(new BlackTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a stone!")
          player.setStone()
          GamePlay(gridBase.TakeStone(Stone.white)).state
        } else {
          println("Wrong input or already set! Please try again")
          gridBase.GamePlay(new WhiteTurn).state
        }
    }
  }

  override def moveStoneState(pos: String, grid: Grid, player: Player): State = {
    val tmp = grid.moveStone(pos, Stone.white, player)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        GamePlay(new BlackTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a stone!")
          GamePlay(gridBase.TakeStone(Stone.white)).state
        } else {
          println("Wrong input or already set! Please try again")
          gridBase.GamePlay(new WhiteTurn).state
        }
    }
  }

  override def takeStoneState(pos: String, grid: Grid): State = {
    gridBase.GamePlay(new WhiteTurn).state
  }

  override def jumpStoneState(pos: String, grid: Grid, player: Player): State = {
    val tmp = grid.jumpStone(pos, Stone.white, player: Player)
    tmp match {
      case Success(_) => GamePlay(new BlackTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          GamePlay(gridBase.TakeStone(Stone.white)).state
        } else {
          println("Wrong input or already set! Please try again")
          gridBase.GamePlay(new WhiteTurn).state
        }
    }
  }
}
