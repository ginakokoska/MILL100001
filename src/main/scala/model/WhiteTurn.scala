package model

case class WhiteTurn() extends State {
  override def handle(square :String, pos1 :Char, pos2:Char, grid: Grid):State = {
    println("Black please set your Stone: ")
    grid.gridList = grid.moveGrid(square, pos1, pos2, Stone.white)

    GamePlay(new BlackTurn).state
  }
}
