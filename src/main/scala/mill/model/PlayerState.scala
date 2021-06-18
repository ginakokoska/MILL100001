package mill.model

trait PlayerState {
  def getState(player: Player): PlayerState
}
