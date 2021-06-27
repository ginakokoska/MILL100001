package mill.model.gridComponent.gridBase

import com.google.inject.Inject
import mill.controller.base.Controller
import mill.model.gridComponent.State

case class GamePlay @Inject() (state: State) extends State {
  def setStoneState(pos :String, grid: Grid, controller: Controller):State = {
    val newState = state.setStoneState(pos :String, grid:Grid, controller)
    newState
  }
  def moveStoneState(pos :String, grid: Grid, controller: Controller):State = {
    val newState = state.moveStoneState(pos :String, grid:Grid, controller)
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
