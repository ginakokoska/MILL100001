package mill.model.gridComponent.gridBase

import com.google.inject.Inject
import mill.controller.controllerBase.Controller
import mill.model.gridComponent.State

/*
  This class is needed for the state pattern. Creating different game play states.
 */

case class GamePlay @Inject() (state: State) extends State {
  def setStoneState(pos :String, grid: Grid, controller: Controller):State = {
    val newState = state.setStoneState(pos, grid, controller)
    newState
  }
  def moveStoneState(pos :String, grid: Grid, controller: Controller):State = {
    val newState = state.moveStoneState(pos, grid, controller)
    newState
  }
  def jumpStoneState(pos :String, grid :Grid, controller: Controller):State = {
    val newState = state.jumpStoneState(pos, grid, controller)
    newState
  }
  def takeStoneState(pos :String, grid :Grid) :State = {
    val newState = state.takeStoneState(pos, grid)
    newState
  }
}
