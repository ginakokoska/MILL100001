package mill.model

/*
  This trait initialises a the function that represents the player state.
 */

trait PlayerState {
  def getState(player: Player): PlayerState
}
