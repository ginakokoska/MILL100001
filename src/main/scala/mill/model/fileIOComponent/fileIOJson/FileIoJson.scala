package mill.model.fileIOComponent.fileIOJson

import mill.controller._
import mill.model.gridComponent.gridBase.Node
import mill.model.Stone
import mill.model.fileIOComponent.FileIoInterface
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.json._

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.Source
import java.io._

class FileIO extends FileIoInterface {

//  implicit val grid = new Writes[Grid] {
//    override def writes(grid: Grid): JsValue = {
//      "gridList" -> grid.gridList.toString()
//    }
//  }

  override def load(controller: ControllerInterface): List[Array[Array[Node]]] = {
    val source: String = Source.fromFile("mill.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val p1 = (json \ "player" \ "player1")
    val p2 = (json \ "player" \ "player2")
    val p1List: List[String] = List("p", p1.get("name").toString(), p1.get("color").toString())
    val p2List: List[String] = List("p", p2.get("name").toString(), p2.get("color").toString())
    controller.createPlayer1(p1List.head, p1List(1))
    controller.createPlayer2(p2List.head)


//    val nameReads: Reads[Int] = (JsPath \ "row").read[Int]

    for(k <- 0 to 2) {
      for (i <- 0 to 2; j <- 0 to 2) {
       s
        val row = (json \\ "row") (i).as[Int]
        val col = (json \\ "col") (j).as[Int]
        val color = (json \\ "color")(i).as[Int]

        color match {
          case 1 => controller.grid.gridList(sq)(row)(col) = Node(Some(Stone.white))
          case 2 => controller.grid.gridList(sq)(row)(col) = Node(Some(Stone.black))
          case _ => controller.grid.gridList(sq)(row)(col) = Node(None)
        }
      }
    }

//    for(i <- 0 to 2; j <- 0 to 2) {
//      val row = (json \ "field" \ "outSquare" \ "row")(i).as[Int]
//      val col = (json \ "field" \ "outSquare" \ "col")(j).as[Int]
//      val color = (json \ "field" \ "outSquare" \ "color")(i)(j).as[Int]
//
//      color match {
//        case 1 => controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
//        case 2 => controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
//        case _ => controller.grid.gridList.head(row)(col) = Node(None)
//      }
//    }
//
//    for(i <- 0 to 2; j <- 0 to 2) {
//      val row = (json \ "field" \ "midSquare" \ "row")(i).as[Int]
//      val col = (json \ "field" \ "midSquare" \ "col")(j).as[Int]
//      val color = (json \ "field" \ "midSquare" \ "color")(i)(j).as[Int]
//
//      color match {
//        case 1 => controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
//        case 2 => controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
//        case _ => controller.grid.gridList.head(row)(col) = Node(None)
//      }
//    }
//
//    for(i <- 0 to 2; j <- 0 to 2) {
//      val row = (json \ "field" \ "inSquare" \ "row")(i).as[Int]
//      val col = (json \ "field" \ "inSquare" \ "col")(j).as[Int]
//      val color = (json \ "field" \ "inSquare" \ "color")(i)(j).as[Int]
//
//      color match {
//        case 1 => controller.grid.gridList.head(row)(col) = Node(Some(Stone.white))
//        case 2 => controller.grid.gridList.head(row)(col) = Node(Some(Stone.black))
//        case _ => controller.grid.gridList.head(row)(col) = Node(None)
//      }
//    }


//    val grid = (json \ "field" \  "grid")
//    val list: List[String] = List(grid.get("grid").toString())

//    var i = 0
//    var whileLoop: Boolean = true
//    while(whileLoop) {
//      if(list == null) {
//        whileLoop = false
//      } else {
//        val sq = list(i).charAt(0).asDigit
//        val row = list(i).charAt(1).asDigit
//        val col = list(i).charAt(3).asDigit
//
//        list(i).charAt(4).asDigit match {
//          case 1 => controller.grid.gridList(sq)(row)(col) = Node(Some(Stone.white))
//          case 2 => controller.grid.gridList(sq)(row)(col) = Node(Some(Stone.black))
//          case _ => controller.grid.gridList(sq)(row)(col) = Node(None)
//        }
//      }
//      i += 1
//    }
    controller.grid.gridList
    //    grid.as[List[List[Int]]]
//    while(true) {
//      var i = 0
//      val stonePos: List[Int] = List(grid(i))
//
//
//
//    }
//
//
//    val injector = Guice.createInjector(new MillModule)
//    size match {
//      case 1 => grid = injector.instance[GridInterface](Names.named("Out"))
//      case 2 => grid = injector.instance[GridInterface](Names.named("Mid"))
//      case 3 => grid = injector.instance[GridInterface](Names.named("Full"))
//      case _ =>
//    }

//    for (index <- 0 until size * size) {
//      val row = (json \\ "row")(index).as[Int]
//      val col = (json \\ "col")(index).as[Int]
//      val cell = (json \\ "cell")(index)
//      val value = (cell \ "Type").as[String]
//      val endzelle = grid.getCell(value)
//      grid = gameboard.movePlayer((row, col), endzelle)
//      if (endzelle.isInstanceOf[PlayerCell]) {
//      }
//    }
//    grid
  }



  def createJsonHUREN(controller: ControllerInterface): JsValue = {
    val p1 = controller.player1
    val p2 = controller.player2
//    var gridList: List[String] = List() // finale liste
//    for (i <- 0 to 2)
//      for (j <- 0 to 2)
//        for (k <- 0 to 2) {
//          if (controller.grid.gridList(i)(j)(k).isSet) {
//            if (controller.grid.gridList(i)(j)(k).isColor.contains(Stone.white)) {
////              val listWhite = List(i, j, k, 1) // 1 = white
////              gridList ++= listWhite
//              val stPos: String = i.toString + j.toString + k.toString + "1"
//              gridList += stPos
//            } else {
////              val listBlack = List(i, j, k, 2)
////              gridList ++= listBlack
//              val stPos: String = i.toString + j.toString + k.toString + "2"
//              gridList += stPos
//            }
//          } else {
////            val listNone = List(i, j, k ,0)
////            gridList ++= listNone
//            val stPos: String = i.toString + j.toString + k.toString + "0"
//            gridList += stPos
//          }
//        }

    Json.obj (
   //   "date" -> LocalDateTime.now.format(DateTimeFormatter.ofPattern("YYYY-MM-dd_HH-mm-ss")),
      "player" -> Json.obj(
        "player1" -> Json.obj(
          "name" -> p1.name,
          "color" -> p1.color.toString
        ),
        "player2" -> Json.obj(
          "name" -> p2.name,
          "color" -> p2.color.toString
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
                    "color" -> "white"
                  } else {
                    "color" -> "black"
                  }
                } else {
                  "color" -> "hure"
                }
              )
            }
          }
        )

//    for(row <- 0 to 2; col <- 0 to 2) yield {
//            Json.obj (
//
//              "row" -> row,
//              "col" -> col,
//              if (controller.grid.gridList.head(row)(col).isSet) {
//                if (controller.grid.gridList.head(row)(col).isColor.contains(Stone.white)) {
//                  "color" -> 1
//                } else {
//                  "color" -> 2
//                }
//              } else {
//                "color" -> 0
//              }
//            )
//          }
//        ),
//        "midSquare" -> Json.toJson(
//          for(row <- 0 to 2; col <- 0 to 2) yield {
//            Json.obj (
//              "row" -> row,
//              "col" -> col,
//              if (controller.grid.gridList(1)(row)(col).isSet) {
//                if (controller.grid.gridList(1)(row)(col).isColor.contains(Stone.white)) {
//                  "color" -> 1
//                } else {
//                  "color" -> 2
//                }
//              } else {
//                "color" -> 0
//              }
//            )
//          }
//        ),
//        "inSquare" -> Json.toJson(
//          for(row <- 0 to 2; col <- 0 to 2) yield {
//            Json.obj (
//              "row" -> row,
//              "col" -> col,
//              if (controller.grid.gridList(2)(row)(col).isSet) {
//                if (controller.grid.gridList(2)(row)(col).isColor.contains(Stone.white)) {
//                  "color" -> 1
//                } else {
//                  "color" -> 2
//                }
//              } else {
//                "color" -> 0
//              }
//            )
//          }
  //      )
      )
    )
  }

  override def save(controller: ControllerInterface): Unit = {
    val pw = new PrintWriter(new File("mill.json"))
    pw.write(Json.prettyPrint(createJsonHUREN(controller)))
    pw.close()
  }
}