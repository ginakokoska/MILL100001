package mill.model.gridComponent.gridBase
import mill.controller.controllerBase.Controller
import mill.model.{JumpState, MoveState, PlayerState, SetState, Stone}
import mill.model.gridComponent.State

/*
  This class contains the main logic. Ensuring the players take alternating turns and move according to their stone number.
 */

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
        }
      case BlackTurn() =>
        playerState match {
          case SetState() =>
            GamePlay(state).setStoneState(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).moveStoneState(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStoneState(pos, controller.grid, controller)
        }
      case TakeStone(Stone.white) =>
        GamePlay(state).takeStoneState(pos, controller.grid)
      case TakeStone(Stone.black) =>
        GamePlay(state).takeStoneState(pos, controller.grid)
    }
  }
}
