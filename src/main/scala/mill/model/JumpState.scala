package mill.model

case class JumpState() extends PlayerState {
  override def getState(player: Player): PlayerState = {
    if (player.countState(StoneState.outOfGame) == 6) JumpState()
    else MoveState()
  }
}