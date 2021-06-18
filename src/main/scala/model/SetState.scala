package model
import model.StoneState.StoneState

case class SetState() extends PlayerState {
  override def getState(player: Player): PlayerState = {
    if(player.countState(StoneState.notUsed) > 0) new SetState
    else MoveState()
  }
}
