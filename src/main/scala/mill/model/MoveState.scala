package mill.model

/*
  This class will set player state to move state if 9 to 7 stones are in game.
 */

case class MoveState() extends PlayerState {
  override def getState(player: Player): PlayerState = {
    if (player.countState(StoneState.notUsed) == 0 && player.countState(StoneState.outOfGame) < 6) MoveState()
    else if (player.countState(StoneState.outOfGame) >= 6) JumpState()
    else SetState()
  }
}
