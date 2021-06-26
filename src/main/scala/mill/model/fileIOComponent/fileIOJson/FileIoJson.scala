package mill.model.fileIOComponent.fileIOJson

import mill.controller._
import mill.model.gridComponent.gridBase.{Grid, Node}
import mill.model.{Stone, StoneState}
import mill.model.fileIOComponent.FileIoInterface
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.json._

import scala.io.Source
import java.io._

class FileIO extends FileIoInterface {

  override def load(controller: ControllerInterface): List[Array[Array[Node]]] = {
    val source: String = Source.fromFile("mill.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val p1 = (json \ "player" \ "player1")
    val p2 = (json \ "player" \ "player2")
    val p1List: List[String] = List(p1.get("name").toString(), p1.get("color").toString(), p1.get("notUsed").toString(), p1.get("inGame").toString(), p1.get("OutOfGame").toString())
    val p2List: List[String] = List(p2.get("name").toString(), p2.get("color").toString(), p1.get("notUsed").toString(), p1.get("inGame").toString(), p1.get("OutOfGame").toString())
    controller.createPlayer1(p1List.head, p1List(1))
    controller.player1.fillStone()
    for(i <- 1 to p1List(3).tail.init.toInt) {
      controller.player1.setStone()
    }
    if(p1List(4).tail.init.toInt > 0) {
      for (i <- 1 to p1List(4).tail.init.toInt) {
        controller.player1.takeStone()
      }
    }

    controller.createPlayer2(p2List.head)
    controller.player2.fillStone()
    for(i <- 1 to p2List(3).tail.init.toInt) {
      controller.player2.setStone()
    }
    if(p2List(4).tail.init.toInt > 0) {
      for (i <- 1 to p2List(4).tail.init.toInt) {
        controller.player2.takeStone()
      }
    }

    val grid = Grid().createFullGrid()
    val tuple = (json \\ "outSquare")
    val test1 = tuple(0)
    for(sq <- 0 to 2) {
      for(node <- 0 to 8) {
        val row = (test1(sq)(node) \\ "row").head.as[Int]
        val col = (test1(sq)(node) \\ "col").head.as[Int]
        val color = (test1(sq)(node) \\ "color").head.as[Int]
        color match {
          case 1 => grid(sq)(row)(col) = Node(Some(Stone.white))
          case 2 => grid(sq)(row)(col) = Node(Some(Stone.black))
          case _ => grid(sq)(row)(col) = Node(None)
        }
      }
    }
    grid
  }



  def createJsonHUREN(controller: ControllerInterface): JsValue = {
    val p1 = controller.player1
    val p2 = controller.player2
    Json.obj (
      "player" -> Json.obj(
        "player1" -> Json.obj(
          "name" -> p1.name,
          "color" -> p1.color.toString,
          "notUsed" -> p1.countState(StoneState.notUsed),
          "inGame" -> p1.countState(StoneState.inGame),
          "OutOfGame" -> p1.countState(StoneState.outOfGame)
        ),
        "player2" -> Json.obj(
          "name" -> p2.name,
          "color" -> p2.color.toString,
          "notUsed" -> p2.countState(StoneState.notUsed),
          "inGame" -> p2.countState(StoneState.inGame),
          "OutOfGame" -> p2.countState(StoneState.outOfGame)
        )
      ),
      "field" -> Json.obj(
        "outSquare" -> Json.toJson(
          for (sq <- 0 to 2) yield {
            for (row <- 0 to 2; col <- 0 to 2) yield {
              Json.obj(
                "square" -> sq,
                "row" -> row,
                "col" -> col,
                if (controller.grid.gridList(sq)(row)(col).isSet) {
                  if (controller.grid.gridList(sq)(row)(col).isColor.contains(Stone.white)) {
                    "color" -> 1
                  } else {
                    "color" -> 2
                  }
                } else {
                  "color" -> 0
                }
              )
            }
          }
        )
      )
    )
  }

  override def save(controller: ControllerInterface): Unit = {
    val pw = new PrintWriter(new File("mill.json"))
    pw.write(Json.prettyPrint(createJsonHUREN(controller)))
    pw.close()
  }
}