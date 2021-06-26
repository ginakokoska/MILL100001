package mill.model

case class MoveState() extends PlayerState {
  override def getState(player: Player): PlayerState = {
    if (player.countState(StoneState.notUsed) == 0 && player.countState(StoneState.outOfGame) < 6) MoveState()
    else if (player.countState(StoneState.outOfGame) >= 6) JumpState()
    else SetState()
  }
}
