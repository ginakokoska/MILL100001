package model

import scala.collection.mutable._

case class Player(name: String, color: Stone.Value) {
  var mapState :ListBuffer[Stone.Value] = ListBuffer (this.color, this.color, this.color, this.color, this.color, this.color, this.color, this.color, this.color)
  def playerToString(player1: Player, player2: Player) :String = {
    val helloPlayer = "welcome " + player1.name + "(" + player1.color + ") and " + player2.name + "(" + player2.color + ")"
    helloPlayer
  }

  def numStones() :ListBuffer[Stone.Value] = {
    //kontrolliert Anzahl an Steinen
    mapState -= mapState(0)
    mapState
  }
}