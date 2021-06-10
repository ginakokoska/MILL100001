import model.playerComponent.StoneState.StoneState
import model.playerComponent.StoneState

import scala.collection.immutable.ListMap
import scala.util.control.Breaks.{break, breakable}
//import scala.collection.mutable.Map

object keyColor extends Enumeration {
  type keyColoer = Value
  val black, white = Value
}

object valState extends Enumeration {
  type valState = Value
  val notUsed, inGame, outOfGame = Value
}

case class PlayerWC() {
  var map :ListMap[String, valState.Value] = ListMap()

  def fillStone() : Unit = {
    for(i <- 1 to 9) {
      var name = "Stone" + i
      map += (name -> valState.notUsed)
    }
  }

  def setStone() :Unit = {
    breakable {
      for ((k,v) <- map) {
        if(v.equals(valState.notUsed)) {
          map += (k -> valState.inGame)
          break()
        }
      }
    }
  }

  def takeStone() :Unit = {
    breakable {
      for ((k,v) <- map) {
        if(v.equals(valState.inGame)) {
          map += ("Stone1" -> valState.outOfGame)
          break()
        }
      }
    }
  }

  def countState(state :valState.Value) : Int = {
    var counter = 0
    for(i <- map.values.toList) {
      if(i.equals(state)) {
        counter = counter + 1
      }
    }
    counter
  }
}

val player1 = PlayerWC()
player1.map
player1.fillStone()
player1.setStone()
player1.setStone()
player1.setStone()
player1.takeStone()
player1.setStone()


player1.countState(valState.notUsed)
//var t = player1.map.values.toList.count(x: Int => {})
var counter = 0
val l = player1.map.values.toArray
for(i <- 0 to 8) {
  println(l(i))
  if(l(i) == valState.inGame) {
    counter = counter + 1
  }
}
counter
//
//player1.map