package mill.controller.controllerBase

import mill.model.{JumpState, MoveState, SetState, Stone}
import mill.model.gridComponent.gridBase.{BlackTurn, GamePlay,/* Handler,*/ TakeStone, WhiteTurn}
import mill.util.Command

/*
  This class implements the functions of the Command Pattern.
  The Command Pattern uses a UndoManager to execute all commands one by one.
 */

class SetCommand(controller: Controller, pos: String) extends Command {
  override def doStep(): Unit = {
//    controller.gamePlayState = Handler(controller.gamePlayState, controller.playerState).handleState(pos, controller)
    controller.gamePlayState match {
      case WhiteTurn() =>
        controller.playerState match {
          case SetState() =>
            controller.gamePlayState = GamePlay(WhiteTurn()).setStoneState(pos, controller.grid, controller.player1)
          case MoveState() =>
            controller.gamePlayState = GamePlay(WhiteTurn()).moveStoneState(pos, controller.grid, controller.player1)
          case JumpState() =>
            controller.gamePlayState = GamePlay(WhiteTurn()).jumpStoneState(pos, controller.grid, controller.player1)
        }
      case BlackTurn() =>
        controller.playerState match {
          case SetState() =>
            controller.gamePlayState = GamePlay(BlackTurn()).setStoneState(pos, controller.grid, controller.player2)
          case MoveState() =>
            controller.gamePlayState = GamePlay(BlackTurn()).moveStoneState(pos, controller.grid, controller.player2)
          case JumpState() =>
            controller.gamePlayState = GamePlay(BlackTurn()).jumpStoneState(pos, controller.grid, controller.player2)
        }
      case TakeStone(Stone.white) =>
        controller.gamePlayState = GamePlay(TakeStone(Stone.white)).takeStoneState(pos, controller.grid)
      case TakeStone(Stone.black) =>
        controller.gamePlayState = GamePlay(TakeStone(Stone.black)).takeStoneState(pos, controller.grid)
    }
    }

  override def undoStep(): Unit = controller.grid.createFullGrid()

  override def redoStep(): Unit = doStep()
}
