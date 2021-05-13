package model

case class BlackTurn() extends State{
  override def handle(square :String, pos1 :Char, pos2:Char, grid: Grid):State = {
    println("White please set your Stone: ")
    grid.gridList = grid.moveGrid(square, pos1, pos2, Stone.black)

    GamePlay(new WhiteTurn).state

  }
}
