package mill.model

/*
  This class will set player state to jump state if only 3 stones are left in game.
 */

case class JumpState() extends PlayerState {
  override def getState(player: Player): PlayerState = {
    if (player.countState(StoneState.outOfGame) == 6) JumpState()
    else MoveState()
  }
}
