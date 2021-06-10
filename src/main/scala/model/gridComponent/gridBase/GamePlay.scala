package model.gridComponent.gridBase

import controller.base.Controller

//State Pattern
trait State {
  def handle(pos :String, grid  :Grid, controller :Controller):State
  def handle2(pos :String, grid :Grid, controller :Controller):State
  def jumpStone(pos :String, grid :Grid, controller: Controller):State
  def handleTakeStone(pos :String, grid :Grid):State
}

case class GamePlay(state: State) {
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
