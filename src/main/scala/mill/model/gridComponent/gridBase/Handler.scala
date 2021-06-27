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
            GamePlay(state).setStoneState(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).moveStoneState(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStoneState(pos, controller.grid, controller)
//          case _ => GamePlay(state)
        }
      case BlackTurn() =>
        playerState match {
          case SetState() =>
            GamePlay(state).setStoneState(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).moveStoneState(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStoneState(pos, controller.grid, controller)
//          case _ => GamePlay(state)
        }
      case TakeStone(Stone.white) =>
        GamePlay(state).takeStoneState(pos, controller.grid)
      case TakeStone(Stone.black) =>
        GamePlay(state).takeStoneState(pos, controller.grid)
//      case _ => GamePlay(state)
    }
  }
}
