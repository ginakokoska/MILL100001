package mill.model

/*
  This class will set player state to set state until each player has placed 9 stones on the board.
 */

case class SetState() extends PlayerState {
  override def getState(player: Player): PlayerState = {
    if (player.countState(StoneState.notUsed) > 0) new SetState
    else MoveState()
  }
}
