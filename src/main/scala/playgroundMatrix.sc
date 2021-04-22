import scala.io.StdIn._

case class Stone() extends Enumeration {
  //type stone = Value
  val whiteStone, blackStone = Value
}

//isColor methode schreiben, Methode gibt zurück welche Farbe sich auf Node befindet, als Parameter wird Node übergeben
//evtl soll isSet zurückgeben welche Farbe
//if(!isSet) -> gib zurück "ist Frei"
case class Node(stone: Option[Stone#Value]) {
  def isSet: Boolean =  stone match {
    case Some (s: Stone#Value) => true
    case None => false
  } // check if stone is set , s stands for black or white stone // stone must be created as enum in scala 2 4 lines of code

  //was isColor gibt Some(white/black) zurück
  def isColor: Option[Stone#Value] =
    if(isSet) stone
    else None

}

val whiteStone = Stone().whiteStone

val outSquare = Array.ofDim[Node](3,3)
val midSquare = Array.ofDim[Node](3,3)
val inSquare = Array.ofDim[Node](3,3)


//Squares mit "None" (Kein gesetzter Stein) füllen zu beginn des Spiels
for(i <- 0 to 2) {
  for(j <- 0 to 2) {
    outSquare(i)(j) = Node(None)
    midSquare(i)(j) = Node(None)
    inSquare(i)(j) = Node(None)
  }
}

//prüfen ob bei beginn des Spiels isSet = false ist, noch kein Stein wurde gesetzt
for(i <- 0 to 2) {
  for(j <- 0 to 2) {
    println("outSquare " + i + j + ": " + outSquare(i)(j).isSet)
    println("midSquare " + i + j + ": " + midSquare(i)(j).isSet)
    println("inSquare " + i + j + ": " + inSquare(i)(j).isSet + "\n")
  }
}

for(i <- 0 to 2) {
  for(j <- 0 to 2) {
    println("outSquare " + i + j + ": " + outSquare(i)(j))
    println("midSquare " + i + j + ": " + midSquare(i)(j))
    println("inSquare " + i + j + ": " + inSquare(i)(j) + "\n")
  }
}

//Spielfeld mithilfe von Strings printen
val playGround = "OS: 00--------------------OS: 01--------------------OS: 02\n" +
                 "|                           |                         |\n"  +
                 "|                           |                         |\n"  +
                 "|                           |                         |\n"  +
                 "|        MS: 00-----------MS: 01-----------MS: 02     |\n"  +
                 "|          |                |               |         |\n"  +
                 "|          |                |               |         |\n"  +
                 "|          |     IS: 00---IS: 01---IS: 02   |         |\n"  +
                 "|          |      |                 |       |         |\n"  +
                 "|          |      |                 |       |         |\n"  +
                 "OS: 10---MS: 10--IS: 10            Is: 12--MS:12----OS: 12\n" +
                 "|          |      |                 |       |         |\n"  +
                 "|          |      |                 |       |         |\n"  +
                 "|          |     IS: 20---IS: 21---IS: 22   |         |\n"  +
                 "|          |                |               |         |\n"  +
                 "|          |                |               |         |\n"  +
                 "|        MS: 20-----------MS: 21-----------MS: 22     |\n"  +
                 "|                           |                         |\n"  +
                 "|                           |                         |\n"  +
                 "|                           |                         |\n"  +
                 "OS: 20--------------------OS: 21--------------------OS: 22"

println(playGround)


//testweise ein weißen Speilstein auf ein Node setzen
midSquare(0)(0) = Node(Some(Stone().blackStone))

//prüfen ob bei diesem Node isSet = true ist
for(i <- 0 to 2) {
  for(j <- 0 to 2) {
    println("outSquare " + i + j + ": " + outSquare(i)(j).isSet)
    println("midSquare " + i + j + ": " + midSquare(i)(j).isSet)
    println("inSquare " + i + j + ": " + inSquare(i)(j).isSet + "\n")
  }
}
//evtl print anpassen und nochmal printen

println(midSquare(0)(0).isColor.toString)
midSquare(0)(0) = Node(None)
println(midSquare(0)(0).isColor.toString)
midSquare(0)(0) = Node(Some(Stone().whiteStone))
println(midSquare(0)(0).isColor.toString)


//Player class erstellen
//Player muss erstellt werden, in dem beide Spieler name und Farbe eingeben
//Bei color evtl Stone als Datentyp wählen
case class Player(name: String, color: Stone#Value)


//player erstellen
//name über konsolen eingabe eingeben?
val player1 = Player("Luca", Stone().whiteStone)
val player2 = Player("Gina", Stone().blackStone)
println(player1.name.toString + ": " + player1.color.toString)
println(player2.name.toString + ": " + player2.color.toString)

//Player kann name und Farbe zu Beginn wählen
//Konsolen eingabe


println("Name von Player3 eingeben:")
//val playerName = Console.
//val playerName = readLine()
val testStringEingabe = readLine()
println(testStringEingabe)




