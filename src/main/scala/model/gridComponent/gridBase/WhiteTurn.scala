package model.gridComponent.gridBase

import controller.base.Controller
import model.gridComponent.gridBase
import model.playerComponent.Stone

import scala.util.{Failure, Success}

case class WhiteTurn() extends State {
  override def handle(pos: String, grid: grid, controller: Controller): State = {
    val tmp = grid.moveGrid(pos, Stone.white, controller.player2)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        controller.player1.setStone()
        GamePlay(new BlackTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player1.setStone()
          GamePlay(gridBase.TakeStone(Stone.white)).state
        } else {
          println("Wrong Input or already set! Please try again")
          gridBase.GamePlay(new WhiteTurn).state
        }
    }
  }

  override def handle2(pos: String, grid: grid, controller: Controller): State = {
    val tmp = grid.moveStone(pos, Stone.white, controller.player2)
    tmp match {
      case Success(v) =>
        grid.gridList = v
        GamePlay(new BlackTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          GamePlay(gridBase.TakeStone(Stone.white)).state
        } else {
          println("Wrong Input or already set! Please try again")
          gridBase.GamePlay(new WhiteTurn).state
        }
    }
  }

  override def handleTakeStone(pos: String, grid: grid): State = {
    gridBase.GamePlay(new WhiteTurn).state
  }

  override def jumpStone(pos: String, grid: grid, controller: Controller): State = {
    val tmp = grid.jumpStone(pos, Stone.white, controller.player1)
    tmp match {
      case Success(v) => GamePlay(new BlackTurn).state
      case Failure(f) =>
        if (f.getMessage.equals("message:")) {
          println("take a Stone!")
          controller.player2.setStone()
          GamePlay(gridBase.TakeStone(Stone.white)).state
        } else {
          println("Wrong Input or already set! Please try again")
          gridBase.GamePlay(new WhiteTurn).state
        }
    }
  }
}