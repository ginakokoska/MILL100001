package mill.model.gridComponent.gridBase

import com.google.inject.Inject
import mill.controller.base.Controller
import mill.model.{JumpState, MoveState, Player, PlayerState, SetState, Stone}
import mill.model.gridComponent.{State, gridBase}

case class Handler(state: State, playerState: PlayerState) {
  def handleState(player1: Player, player2: Player, pos: String, controller: Controller): State = {
    state match {
      case WhiteTurn() =>
        playerState match {
          case SetState() =>
            GamePlay(state).handle(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).handle2(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStone(pos, controller.grid, controller)
          case _ => GamePlay(state)
        }
      case BlackTurn() =>
        playerState match {
          case SetState() =>
            GamePlay(state).handle(pos, controller.grid, controller)
          case MoveState() =>
            GamePlay(state).handle2(pos, controller.grid, controller)
          case JumpState() =>
            GamePlay(state).jumpStone(pos, controller.grid, controller)
          case _ => GamePlay(state)
        }
      case TakeStone(Stone.white) =>
        GamePlay(state).handleTakeStone(pos, controller.grid)
      case TakeStone(Stone.black) =>
        GamePlay(state).handleTakeStone(pos, controller.grid)
      case _ => GamePlay(state)
    }
  }
}
