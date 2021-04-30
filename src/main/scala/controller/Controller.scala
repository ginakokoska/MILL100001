package controller

import model.{Player, Stone, Grid}
import util._

class Controller(var player1: Player, var player2: Player, var grid: Grid) extends Observable {

  def createPlayer1(name: String, tmpColor: String):Unit = {

    if (tmpColor == "b")
      player1 = player1.setPlayer(name, "b")
    else
      player1 = player1.setPlayer(name, "w")
    notifyObservers
  }

  def createPlayer2(name: String):Unit = {
    if(player1.color == Stone.white)
      player2 = player2.setPlayer(name,"b")
    else
      player2 = player2.setPlayer(name, "w")
    notifyObservers
  }

  def sayHello(): Unit = {
    println("\nwelcome " + player1.name + "(" + player1.color + ") and "
      + player2.name + "(" + player2.color + ")\n")
    notifyObservers
  }

  def createGrid():Unit = {
    grid = Grid()
    notifyObservers
  }

  def printGrid(): Unit = {
    grid.printGrid
    notifyObservers
  }
}
