import scala.io.StdIn._
import model.{Stone, playerComponent, _}
import model.gridComponent.gridBase.{Grid, Node}
import model.playerComponent.playerBase


val player1 = model.Player("PlayerOne", Stone.white)
val player2 = model.Player("PlayerTwo", Stone.black)
var grid = new Grid()

println("Player1: " + player1)
println("Player2: " + player2)

println("Player Test:")
println("Color Player1: " + player1.color)
println("Name player1: " + player1.name)
println("Color Player2: " + player2.color)
println("Name player2: " + player2.name)

//grid.outSquare(0)(0) = Node(Some(Stone.white))
def test(): Grid = {
  var grid1 = grid()
  grid1.gridList(0)(0)(0) = Node(Some(player1.color))
  grid1
}
grid = test()

//val stoneColor = grid.outSquare(0)(0).isColor
//val nodeSet = grid.outSquare(0)(0).isSet


