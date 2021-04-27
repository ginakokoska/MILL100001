import scala.io.StdIn._
import model._


val player1 = Player("PlayerOne", Stone.white)
val player2 = Player("PlayerTwo", Stone.black)
val grid = new Grid()

println("Player1: " + player1)
println("Player2: " + player2)

println("Player Test:")
println("Color Player1: " + player1.color)
println("Name player1: " + player1.name)
println("Color Player2: " + player2.color)
println("Name player2: " + player2.name)

//grid.outSquare(0)(0) = Node(Some(Stone.white))
grid.outSquare(0)(0) = Node(Some(player1.color))
val stoneColor = grid.outSquare(0)(0).isColor
val nodeSet = grid.outSquare(0)(0).isSet


