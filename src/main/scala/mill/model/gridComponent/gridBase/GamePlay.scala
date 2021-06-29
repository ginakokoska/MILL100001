package mill.model.gridComponent.gridBase

import com.google.inject.Inject
import mill.model.Player
import mill.model.gridComponent.State

/*
  This class is needed for the state pattern. Creating different game play states.
 */

case class GamePlay @Inject() (state: State) extends State {
  def setStoneState(pos :String, grid: Grid, player: Player):State = {
    val newState = state.setStoneState(pos, grid, player)
    newState
  }
  def moveStoneState(pos :String, grid: Grid, player: Player):State = {
    val newState = state.moveStoneState(pos, grid, player)
    newState
  }
  def jumpStoneState(pos :String, grid :Grid, player: Player):State = {
    val newState = state.jumpStoneState(pos, grid, player)
    newState
  }
  def takeStoneState(pos :String, grid :Grid) :State = {
    val newState = state.takeStoneState(pos, grid)
    newState
  }
}
