package mill.model.gridComponent.gridBase

import mill.controller.base.Controller
import mill.model.{JumpState, MoveState, PlayerState, SetState, Stone}
import mill.model.gridComponent.State

case class Handler(state: State, playerState: PlayerState) {
  def handleState(pos: String, controller: Controller): State = {
    state match {
      case WhiteTurn() =>
        playerState match {
          case SetState() =>
            GamePlay(state).handle(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).handle2(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStone(pos, controller.grid, controller)
//          case _ => GamePlay(state)
        }
      case BlackTurn() =>
        playerState match {
          case SetState() =>
            GamePlay(state).handle(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).handle2(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStone(pos, controller.grid, controller)
//          case _ => GamePlay(state)
        }
      case TakeStone(Stone.white) =>
        GamePlay(state).handleTakeStone(pos, controller.grid)
      case TakeStone(Stone.black) =>
        GamePlay(state).handleTakeStone(pos, controller.grid)
//      case _ => GamePlay(state)
    }
  }
}
