package mill.model.gridComponent.gridBase

import mill.model.gridComponent.GridSizeInterface

case class FullGrid() extends GridSizeInterface {
  var playGround: String = ""
  var gridList: List[Array[Array[Node]]] = List()

  override def create(): Unit = {
    val outSquare = Array.ofDim[Node](3, 3)
    val midSquare = Array.ofDim[Node](3, 3)
    val inSquare = Array.ofDim[Node](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        outSquare(i)(j) = Node(None)
        midSquare(i)(j) = Node(None)
        inSquare(i)(j) = Node(None)
      }
    }
    playGround = "OS: 00--------------------OS: 01--------------------OS: 02\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|        MS: 00-----------MS: 01-----------MS: 02     |\n" +
      "|          |                |               |         |\n" +
      "|          |                |               |         |\n" +
      "|          |     IS: 00---IS: 01---IS: 02   |         |\n" +
      "|          |      |                 |       |         |\n" +
      "|          |      |                 |       |         |\n" +
      "OS: 10---MS: 10--IS: 10            Is: 12--MS:12----OS: 12\n" +
      "|          |      |                 |       |         |\n" +
      "|          |      |                 |       |         |\n" +
      "|          |     IS: 20---IS: 21---IS: 22   |         |\n" +
      "|          |                |               |         |\n" +
      "|          |                |               |         |\n" +
      "|        MS: 20-----------MS: 21-----------MS: 22     |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "|                           |                         |\n" +
      "OS: 20--------------------OS: 21--------------------OS: 22"
  }

  override def printGrid(): String = playGround
}
