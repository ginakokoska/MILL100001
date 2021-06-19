package mill.model.fileIOComponent.fileIOxml

import mill.controller.ControllerInterface
import mill.controller.base.Controller
import mill.mill.controller
import mill.model.Stone
import mill.model.fileIOComponent.FileIoInterface
import mill.model.gridComponent.GridInterface
import mill.model.gridComponent.gridBase.{Grid, Node}

import scala.xml.{Elem, NodeSeq, PrettyPrinter, XML}

class FileIOXML extends FileIoInterface{

  override def load(controller: ControllerInterface): List[Array[Array[Node]]] = {
    val file = scala.xml.XML.loadFile("mill.xml")
    val p1 = file \\ "Player1"
    val p2 = file \\ "Player2"
    val p1n = (p1 \ "name").text
    val p1c = (p1 \ "color").text
    val p2n = (p2 \ "name").text
    controller.createPlayer1(p1n, p1c)
    controller.createPlayer2(p2n)

    val field = file \\ "grid"
    var grid: GridInterface = new Grid()
    val outSquare = field \ "out"
    val midSquare = field \ "mid"
    val inSquare = field \ "in"

    for (st <- outSquare) {
      val row: Int = (st \ "@row").text.toInt
      val col: Int = (st \ "@col").text.toInt
      val color = (st \\ "node")

      if(color.contains(Stone.white)) controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
      else if (color.contains(Stone.black)) controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
      else controller.grid.gridList.head(row)(col) = Node(None)
//      color(row)(col) match {
//        case Node(Some(Stone.white)) => controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
//        case Node(Some(Stone.black)) => controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
//        case _ => controller.grid.gridList.head(row)(col) = Node(None)
//      }
    }

    for (st <- midSquare) {
      val row: Int = (st \ "@row").text.toInt
      val col: Int = (st \ "@col").text.toInt
      val color = (st \\ "node")

      if(color.contains(Stone.white)) controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
      else if (color.contains(Stone.black)) controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
      else controller.grid.gridList.head(row)(col) = Node(None)
      //      color(row)(col) match {
      //        case Node(Some(Stone.white)) => controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
      //        case Node(Some(Stone.black)) => controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
      //        case _ => controller.grid.gridList.head(row)(col) = Node(None)
      //      }
    }

    for (st <- inSquare) {
      val row: Int = (st \ "@row").text.toInt
      val col: Int = (st \ "@col").text.toInt
      val color = (st \\ "node")

      if(color.contains(Stone.white)) controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
      else if (color.contains(Stone.black)) controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
      else controller.grid.gridList.head(row)(col) = Node(None)

      //      color(row)(col) match {
      //        case Node(Some(Stone.white)) => controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
      //        case Node(Some(Stone.black)) => controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
      //        case _ => controller.grid.gridList.head(row)(col) = Node(None)
      //      }
    }

//    for (sqElem <- allSquares) {
//      val sq = (sqElem \ "@square").text.toInt
//      val col = (sqElem \ "@col").text.toInt
//      val row = (sqElem \ "@row").text.toInt
//      val color = (sqElem \ "color").text.toInt
//      controller.grid.gridList(sq)(col)(row) = Node(None)
//
//      color match {
//        case 1 => controller.grid.gridList(sq)(col)(row) = Node(Some(Stone.white))
//        case 2 => controller.grid.gridList(sq)(col)(row) = Node(Some(Stone.black))
//        case _ => controller.grid.gridList(sq)(col)(row) = Node(None)
//      }
//    }
    grid.gridList
  }

  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("mill.xml"))
    val prettyPrinter = new PrettyPrinter(120, 6)

    val xml = prettyPrinter.format(saveGame(controller))
    pw.write(xml)
    pw.close
  }

  def saveGame(controller: ControllerInterface): Elem = {
    <game>
      {playerst_toXML()}
      {grid_toXML()}
    </game>
  }

  def playerst_toXML(): Elem = {
    val player1 = controller.player1
    val player2 = controller.player2
    <players>
      <p1>
        <name>{player1.name}</name>
        <color>{player1.color}</color>
      </p1>
      <p2>
        <name>{player2.name}</name>
        <color>{player2.color}</color>
      </p2>
    </players>
  }

  def grid_toXML(): Elem = {
    <grid >
      <out>
        {
        for(row <- 0 to 2; col <- 0 to 2)
          yield nodeToXml(0 ,row, col, controller)
        }
      </out>
      <mid>
        {
        for(row <- 0 to 2; col <- 0 to 2)
          yield nodeToXml(1 ,row, col, controller)
        }
      </mid>
      <in>
        {
        for(row <- 0 to 2; col <- 0 to 2)
          yield nodeToXml(2 ,row, col, controller)
        }
      </in>
    </grid>
  }

  def nodeToXml(sq: Int, row: Int, col: Int, controller: Controller): Elem = {
    <node>
      { controller.grid.gridList(sq)(row)(col) }
    </node>
  }
}