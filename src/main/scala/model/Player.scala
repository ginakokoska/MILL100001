package model

case class Player(name: String, color: Stone.Value) {

  def setPlayer(playerName:String, playerColor:String): Player = {
    if(playerColor == "b")  Player(playerName, Stone.black)
    else Player(playerName, Stone.white)
  }
}

