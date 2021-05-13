import scala.collection.mutable.ListBuffer
import model._
import org.w3c.dom.events.{Event, EventException}

import scala.collection.convert.ImplicitConversions.`collection asJava`

object StoneWC extends Enumeration {
  type Stone = Value
  val white, black = Value
}
val whiteStone1 = StoneWC.white
case class PlayerWC(name: String, color: StoneWC.Value) {
  var stoneCount :ListBuffer[StoneWC.Value] = ListBuffer()

  def PlayerStones :ListBuffer[StoneWC.Value] = {

    val whiteStone2 = StoneWC.white
    val whiteStone3 = StoneWC.white
    val whiteStone4 = StoneWC.white
    val whiteStone5 = StoneWC.white
    val whiteStone6 = StoneWC.white
    val whiteStone7 = StoneWC.white
    val whiteStone8 = StoneWC.white
    val whiteStone9 = StoneWC.white
    stoneCount = ListBuffer(whiteStone1,whiteStone2,whiteStone3,
      whiteStone4, whiteStone5, whiteStone6, whiteStone7, whiteStone8, whiteStone9)
    stoneCount
  }

}

val player1 = PlayerWC("Luca", StoneWC.white)
val player2 = PlayerWC("Gina", StoneWC.black)

player1.PlayerStones
player1.PlayerStones -= whiteStone1




player2.PlayerStones

case class NodeWC(stone: Option[StoneWC.Value]) {
  def isSet: Boolean =  stone match {
    case Some (s: StoneWC.Stone) => true
    case None => false
  }

  def isColor: Option[StoneWC.Value] =
    if(isSet) stone
    else None

}

case class GridWC() {
  var playGround: String = ""
  var TestArrayList: List[Array[Array[NodeWC]]] = List()

  def gridOutSquare(): List[Array[Array[NodeWC]]] = {
    val outSquare = Array.ofDim[NodeWC](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        outSquare(i)(j) = NodeWC(None)
      }
    }
    playGround = "OS: 00--------------------OS: 01--------------------OS: 02\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "OS: 10                                           OS: 12\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "|                                                     |\n" +
      "OS: 20--------------------OS: 21--------------------OS: 22"
    val TestArrayList = List(outSquare)
    TestArrayList
  }
}



//State Pattern(VL-Folie): ...
//object movingTest {
//  var state = whiteTurn
//  def handle(e: Event) = {
//    e match {
//      case on: whiteIsMoving => state = whiteTurn
//      case off: blackIsMoving => state = blackTurn
//    }
//    state
//  }
//  def whiteTurn = println("white is on Turn")
//  def blackTurn = println("black is on Turn")
//}
//movingTest.handle(Event)
//case class whiteIsMoving() {
//  movingTest.handle(Event)
//
//}
//case class blackIsMoving() {
//  movingTest.handle(Event)
//}
//Wie kann ich datentyp Event verwenden? evtl Platzhalter?

//maybe factory-pattern ...
trait moveStone {
  def setStone()
}


//State Patter: ...
trait State {
  def handle()
}

case class gamePlay() {
  var state:State = _
  state = new whiteTurn()

  def handle(): Unit = {
    state.handle
  }
}

class whiteTurn extends State with moveStone {
  override def handle(): Unit = {
    println("White is on Turn")
    println("White set Stone on ...")
    setStone()
    gamePlay().state = new blackTurn
  }

  override def setStone(): Unit = {

  }
}

class blackTurn extends State {
  override def handle(): Unit = {
    println("Black is on Turn")
    println("Black set Stone on ...")
    gamePlay().state = new whiteTurn
  }
}
