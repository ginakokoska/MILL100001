package mill.model

import mill.model.StoneState.StoneState

import scala.collection.immutable.ListMap
import scala.util.control.Breaks.{break, breakable}

case class Player(name: String, color: Stone.Value) {
  var mapState: ListMap[String, StoneState.Value] = ListMap()

  def playerToString(player1: Player, player2: Player): String = {
    val helloPlayer = "welcome " + player1.name + "(" + player1.color + ") and " + player2.name + "(" + player2.color + ")"
    helloPlayer
  }

  def fillStone(): Unit = {
    for (i <- 1 to 9) {
      var name = "Stone" + i
      mapState += (name -> StoneState.notUsed)
    }
  }

  def setStone(): Unit = {
    breakable {
      for ((k, v) <- mapState) {
        if (v.equals(StoneState.notUsed)) {
          mapState += (k -> StoneState.inGame)
          break()
        }
      }
    }
  }

  def takeStone(): Unit = {
    breakable {
      for ((k, v) <- mapState) {
        if (v.equals(StoneState.inGame)) {
          mapState += (k -> StoneState.outOfGame)
          break()
        }
      }
    }
  }

  def countState(state: StoneState): Int = {
    var count = 0
    for (i <- mapState.values.toList) {
      if (i.equals(state)) {
        count = count + 1
      }
    }
    count
  }
}
