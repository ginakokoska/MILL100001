package model.gridComponent.gridBase

import com.google.inject.Inject
import controller.base.Controller
import model.gridComponent.State

case class GamePlay @Inject() (state: State) extends State {
  def handle(pos :String, grid: Grid, controller: Controller):State = {
    val newState = state.handle(pos :String, grid:Grid, controller)
    newState
  }
  def handle2(pos :String, grid: Grid, controller: Controller):State = {
    val newState = state.handle2(pos :String, grid:Grid, controller)
    newState
  }
  def jumpStone(pos :String, grid :Grid, controller: Controller):State = {
    val newState = state.jumpStone(pos, grid, controller)
    newState
  }
  def handleTakeStone(pos :String, grid :Grid) :State = {
    val newState = state.handleTakeStone(pos, grid)
    newState
  }
}
