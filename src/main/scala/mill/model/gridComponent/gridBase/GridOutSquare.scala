package mill.model.gridComponent.gridBase

import mill.model.gridComponent.GridSizeInterface

case class GridOutSquare() extends GridSizeInterface{
  var playGround: String = ""
  var gridList: List[Array[Array[Node]]] = List()
  def create():Unit = {
    val outSquare = Array.ofDim[Node](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        outSquare(i)(j) = Node(None)
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
  }
  override def printGrid(): String = playGround
}
