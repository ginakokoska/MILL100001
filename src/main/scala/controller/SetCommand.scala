package controller

import model.{BlackTurn, Stone, WhiteTurn}
import util._

class SetCommand(gridSize :String, controller: Controller, square :String, pos1 :Char, pos2:Char) extends Command {
  override def doStep: Unit = {
//    controller.grid = controller.gridSize(gridSize) //controller method returns unit
    if(controller.grid.gridList.isEmpty) {
      gridSize match {
        case "1" => controller.grid.gridList = controller.grid.gridOutSquare()
        case "2" => controller.grid.gridList = controller.grid.gridOutMidSquare()
        case _ => controller.grid.gridList = controller.grid.createFullGrid()
      }
    } else {
      controller.gamePlayState match {
        case WhiteTurn() => controller.gamePlayState = WhiteTurn().handle(square, pos1, pos2, controller.grid)
        case BlackTurn() => controller.gamePlayState = BlackTurn().handle(square, pos1, pos2, controller.grid)
        case _ => throw new UnsupportedOperationException("")
      }
    }
  }

  override def undoStep: Unit = controller.grid.createFullGrid()

  override def redoStep: Unit = {
    if(controller.grid.gridList.isEmpty) {
      gridSize match {
        case "1" => controller.grid.gridOutSquare
        case "2" => controller.grid.gridOutMidSquare()
        case _ => controller.grid.createFullGrid()
      }
    } else {
      controller.gamePlayState match {
        case WhiteTurn() => WhiteTurn().handle(square, pos1, pos2, controller.grid)
        case BlackTurn() => BlackTurn().handle(square, pos1, pos2, controller.grid)
        case _ => throw new UnsupportedOperationException("")
      }
    }
  }
}
