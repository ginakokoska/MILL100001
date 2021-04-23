import scala.io.StdIn._

object main {
  def main(args: Array[String]): Unit = {
    println("Hello World, and Welcome to our Game Mill!")

    println("Bitte name von Player 1 eingeben:")
    val player1Name = readLine()

    println("Bitte wählen Sie eine Farbe w(eiß)/s(chwarz)")


    val playerColor = readLine()
    if (playerColor == "w") {
      val player1 = Player(player1Name, Stone.white)
      println(player1)
    } else if (playerColor == "s") {
      val player1 = Player(player1Name, Stone.black)
      println(player1)
    } else {
      println("eingabe fehler!")
    }

    println("Bitte name von Player 2 eingeben:")
    val player2Name = readLine()
    if (playerColor == "w") {
      val player2 = Player(player2Name, Stone.black)
      println(player2)
    } else {
      val player2 = Player(player2Name, Stone.white)
      println(player2)
    }





    println("Playground:")
    Grid()
  }
}
