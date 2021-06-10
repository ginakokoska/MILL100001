package controller.base

import model.{BlackTurn, _}
import util._

class SetCommand(controller: Controller, pos: String) extends Command {
  override def doStep: Unit = {
    controller.gamePlayState match {
      case WhiteTurn() =>
        if(controller.player1.countState(StoneState.notUsed) > 0)
          controller.gamePlayState = WhiteTurn().handle(pos, controller.grid, controller)
        else if(controller.player1.countState(StoneState.notUsed) == 0 &&
          controller.player1.countState(StoneState.outOfGame) < 6)
          controller.gamePlayState = WhiteTurn().handle2(pos, controller.grid, controller)
        else if(controller.player1.countState(StoneState.outOfGame) >= 6)
          controller.gamePlayState = WhiteTurn().jumpStone(pos, controller.grid, controller)
      case BlackTurn() =>
        if(controller.player2.countState(StoneState.notUsed) > 0)
          controller.gamePlayState = BlackTurn().handle(pos, controller.grid, controller)
        else if(controller.player2.countState(StoneState.notUsed) == 0 &&
          controller.player2.countState(StoneState.outOfGame) < 6)
          controller.gamePlayState = BlackTurn().handle2(pos, controller.grid, controller)
        else if(controller.player2.countState(StoneState.outOfGame) >= 6) {
          controller.gamePlayState = BlackTurn().jumpStone(pos, controller.grid, controller)
        }
      case _ =>
        if(controller.gamePlayState == TakeStone(controller.player1.color))
          controller.gamePlayState = TakeStone(Stone.white).handleTakeStone(pos, controller.grid)
        else if(controller.gamePlayState == TakeStone(controller.player2.color))
          controller.gamePlayState = TakeStone(Stone.black).handleTakeStone(pos, controller.grid)
      //      case TakeStone(controller.player1.color) =>
//        controller.gamePlayState = TakeStone(Stone.white).handleTakeStone(pos, controller.grid)
//      case TakeStone(controller.player2.color) =>
//        controller.gamePlayState = TakeStone(Stone.black).handleTakeStone(pos, controller.grid)
    }
//    if(controller.gamePlayState == TakeStone(controller.player1.color))
//      controller.gamePlayState = TakeStone(Stone.white).handleTakeStone(pos, controller.grid)
//    else if(controller.gamePlayState == TakeStone(controller.player2.color))
//      controller.gamePlayState = TakeStone(Stone.black).handleTakeStone(pos, controller.grid)


//    if(controller.gamePlayState == WhiteTurn()) {
//      if (controller.player1.countState(StoneState.notUsed) == 0 &&
//        controller.player1.countState(StoneState.outOfGame) <= 6)
//        controller.gamePlayState = WhiteTurn().handle2(pos, controller.grid, controller)
//      else controller.gamePlayState = WhiteTurn().handle(pos, controller.grid, controller)
//    } else if(controller.gamePlayState == BlackTurn()) {
//      if (controller.player2.countState(StoneState.notUsed) == 0 &&
//        controller.player2.countState(StoneState.outOfGame) <= 6)
//        controller.gamePlayState = BlackTurn().handle2(pos, controller.grid, controller)
//      else controller.gamePlayState = BlackTurn().handle(pos, controller.grid, controller)
//    } else if(controller.gamePlayState == TakeStone(controller.player1.color))
//      controller.gamePlayState = TakeStone(Stone.white).handleTakeStone(pos, controller.grid)
//    else if(controller.gamePlayState == TakeStone(controller.player2.color))
//      controller.gamePlayState = TakeStone(Stone.black).handleTakeStone(pos, controller.grid)

  }

  override def undoStep: Unit = controller.grid.createFullGrid()

  override def redoStep: Unit = {
    //redoStep handle like reload, so do doStep again
    doStep
  }
}
