package model

//State Pattern
trait State {
  def handle(square :String, pos1 :Char, pos2:Char, grid: Grid):State
}

case class GamePlay(state: State) {
//  var state:State = new WhiteTurn

  def handle(square :String, pos1 :Char, pos2:Char, grid: Grid):State = {
    state.handle(square :String, pos1 :Char, pos2:Char, grid:Grid)
    state
  }
}
