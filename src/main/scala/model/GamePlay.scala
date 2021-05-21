package model

//State Pattern
trait State {
  def handle(pos :String, grid: Grid):State
  def handle2(pos :String , grid: Grid):State
}

case class GamePlay(state: State) {
  def handle(pos :String, grid: Grid):State = {
    state.handle(pos :String, grid:Grid)
    state
  }
  def handle2(pos :String , grid: Grid):State = {
    state.handle2(pos :String, grid:Grid)
    state
  }
}
