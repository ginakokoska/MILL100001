package controller

import model.{BlackTurn, Stone, WhiteTurn}
import util._

class SetCommand(controller: Controller, pos: String) extends Command {
  override def doStep: Unit = {
    if (controller.player1.mapState.isEmpty && controller.player2.mapState.isEmpty) {
      controller.gamePlayState match {
        case WhiteTurn() => controller.gamePlayState = WhiteTurn().handle2(pos, controller.grid)
        case BlackTurn() => controller.gamePlayState = BlackTurn().handle2(pos, controller.grid)
      }
    } else {
      controller.gamePlayState match {
        case WhiteTurn() =>
          controller.gamePlayState = WhiteTurn().handle(pos, controller.grid)
          controller.player1.numStones()
        case BlackTurn() =>
          controller.gamePlayState = BlackTurn().handle(pos, controller.grid)
          controller.player2.numStones()
      }
    }
  }


  override def undoStep: Unit = controller.grid.createFullGrid()

  override def redoStep: Unit = {
    //redoStep handle like reload, so do doStep again
    doStep
  }
}
