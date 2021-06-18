package mill.model.fileIOComponent

import com.google.inject.Guice
import com.google.inject.name.Names
import mill.MillModule
import mill.model.{FileIoInterface, Stone}
import mill.model.gridComponent.GridInterface
import mill.model.gridComponent.gridBase.{Grid, Node}
import play.api.libs.json._
import play.api.libs.json.Reads._
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import mill.controller._
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites

import java.lang.ModuleLayer.Controller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.Source

class FileIO extends FileIoInterface {

  override def load(controller: ControllerInterface): GridInterface = {
    val source: String = Source.fromFile("gameboard.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val p1 = (json \ "player" \ "player1")
    val p2 = (json \ "player" \ "player2")
    val p1List: List[String] = List("p", p1.get("name").toString(), p1.get("color").toString())
    val p2List: List[String] = List("p", p2.get("name").toString(), p2.get("color").toString())
    controller.createPlayer1(p1List.head, p1List(1))
    controller.createPlayer2(p2List.head)

//    val grid = (json \ "field" \ "grid").as[Array[]]
//    while(true) {
//      var i = 0
//      val stonePos: List[Int] = List(grid.get(i))
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

  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameboard.json"))
    pw.write(Json.prettyPrint(createJsonHUREN(controller)))
    pw.close
  }

  def createJsonHUREN(controller: ControllerInterface): JsValue = {
    val p1 = controller.player1
    val p2 = controller.player2
    var gridList: List[List[Int]] = List() // finale liste
    for (i <- 0 to 2)
      for (j <- 0 to 2)
        for (k <- 0 to 2) {
          if (controller.grid.gridList(i)(j)(k).isSet) {
            if (controller.grid.gridList(i)(j)(k).isColor.contains(Stone.white)) {
              val listWhite = List(i, j, k, 1) // 1 = white
              gridList ++= listWhite
            } else {
              val listBlack = List(i, j, k, 2)
              gridList ++= listBlack
            }
          } else {
            val listNone = List(i, j, k ,0)
            gridList ++= listNone
          }
        }

    Json.obj (
      "date" -> LocalDateTime.now.format(DateTimeFormatter.ofPattern("YYYY-MM-dd_HH-mm-ss")),
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
        "grid" -> Json.toJson(gridList)
      )
    )
  }
}