package model.gridComponent.gridBase

import controller.base.Controller

//State Pattern
trait State {
  def handle(pos :String, grid  :grid, controller :Controller):State
  def handle2(pos :String, grid :grid, controller :Controller):State
  def jumpStone(pos :String, grid :grid, controller: Controller):State
  def handleTakeStone(pos :String, grid :grid):State
}

case class GamePlay(state: State) {
  def handle(pos :String, grid: grid, controller: Controller):State = {
    val newState = state.handle(pos :String, grid:grid, controller)
    newState
  }
  def handle2(pos :String, grid: grid, controller: Controller):State = {
    val newState = state.handle2(pos :String, grid:grid, controller)
    newState
  }
  def jumpStone(pos :String, grid :grid, controller: Controller):State = {
    val newState = state.jumpStone(pos, grid, controller)
    newState
  }
  def handleTakeStone(pos :String, grid :grid) :State = {
    val newState = state.handleTakeStone(pos, grid)
    newState
  }
}
