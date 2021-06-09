package aView.Gui
import aView.Gui._
import controller.Controller
import model.{BlackTurn, Stone, TakeStone, WhiteTurn}

import scala.swing.{BorderPanel, BoxPanel, Button, Component, FlowPanel, Graphics2D, Label, MainFrame, Orientation, Point, TextField}
import java.awt.{Color, Dimension, Graphics2D, Rectangle}
import javax.imageio.ImageIO
import scala.reflect.internal.util.NoFile.file
import scala.swing.event.{ButtonClicked, EditDone, MouseClicked, MouseDragged, MousePressed, MouseReleased}
import javax.swing.ImageIcon
import scala.swing.Action.NoAction.icon

case class Gui(controller :Controller) extends MainFrame {
  title = "Mill"
//    this.preferredSize = new Dimension(650,600)
//
//    object celsius extends TextField {
//      columns = 5
//    }
//
//    object fahrenheit extends TextField {
//      columns = 5
//    }
//
//    def rechner = new FlowPanel {
//      contents += celsius
//      contents += new Label("Celsius = ")
//      contents += fahrenheit
//      contents += new Label("Fahrenheit")
//    }
//
//    object readPlayer1 extends TextField {
//      columns = 15
//    }
//    object readPlayer2 extends TextField {
//      columns = 15
//    }
//
////    val continue = new Button("continue")
//
//    def startGui() = {
//      contents = new FlowPanel() {
//        contents += new Label("Name Player 1")
//        contents += readPlayer1
//        contents += new Label("Name Player 2")
//        contents += readPlayer2
////        contents += continue
//      }
//    }
//
//    def grid() = {
//      var x = 0
//      var y = 0
//      contents = new FlowPanel {
//        override def paintComponent(g: Graphics2D) {
//          var x = 0
//          var y = 0
//          g.drawRect(100, 0, 400, 400) //Ecke oben links
//          g.drawRect(175, 75, 250, 250)
//          g.drawRect(250, 150, 100, 100)
//          g.drawLine(300, 250, 300, 400)
//          g.drawLine(300, 150, 300, 0)
//          g.drawLine(100, 200, 250, 200)
//          g.drawLine(350, 200, 500, 200)
//          g.drawRect(100, 0, 30, 30)
//          g.fillOval(300, 0, 30, 30)
//          g.setColor(Color.BLUE)
//          g.fillOval(100, 100, 30, 30)
////          p(g)
//          g.drawImage(ImageIO.read(getClass.getResourceAsStream("blackstone.png")), null, x, y)
//
//          listenTo(this.mouse.clicks)
//          var tmpPS = ""
//          reactions += {
//            case MouseReleased(_, point, _, _, _) =>
//              val map = ValidMove().validClick(point.x, point.y)
//              for ((k, v) <- map) {
//                if (v) {
//                  println("Success: move " + tmpPS + " to " + k)
//                  x = point.x
//                  y = point.y
////                  paintComponent(g)
//                } else println("Fail")
//                tmpPS = k
//              }
//            case MousePressed(_, point, _, _, _) =>
//              val map = ValidMove().validClick(point.x, point.y)
//              for ((k, v) <- map) {
//                if (v) {
//                  println("Success:" + k)
//                  p(g/*, point.x, point.y*/)
//                }
//                else println("Fail")
//                tmpPS = k
//              }
//          }
//        }
//      }
//    }
//    def p(g :Graphics2D/*, x :Int, y :Int*/): Unit = {
//      var x = 100
//      var y = 100
//      g.drawImage(ImageIO.read(getClass.getResourceAsStream("blackstone.png")), null, x, y)
//      repaint()
//      val t = 0
//    }
//
//
////    contents = continue
//    listenTo(readPlayer1, readPlayer2 /*continue*/)
//    reactions += {
//      case EditDone(readPlayer1) =>
//        println(readPlayer1)
//      case EditDone(readPlayer2) =>
//        println(readPlayer2)
////      case ButtonClicked(continue) =>
////        readPlayer1.visible = false
////        readPlayer2.visible = false
//
//
//    }
  var board = new Gui2()

  val mainPanel: FlowPanel = new FlowPanel() {
    var pArray :Array[Point] = Array()
    var pCl = new Point()
    var pClStr = ""
    var pRe = new Point()
    var pReStr = ""
    controller.player1.fillStone() //wird dann mit createPlayer aufgerufen!!
    controller.player2.fillStone() //wird dann mit createPlayer aufgerufen!!
    controller.gridSize("3") //wird mit "startGame" Button aufgerufen
    this.visible = true
    this.enabled = false
    contents += new BoxPanel(Orientation.Vertical)  {
      contents += board
      listenTo(this.mouse.clicks, this.mouse.moves)
      reactions += {
        case MouseClicked(_,p,_,_,_) =>
          println(p)
          //map(point,boolean)
          //          val os00 = new Point(30,30)
          //          val os01 = new Point(350,30)
          //          val os02 = new Point(665,30)
          //          val ms00 = new Point(140,140)
          //          val ms01 = new Point(350,140)
          //          val ms02 = new Point(565,140)
          //          val is00 = new Point(250,250)
          //          val is01 = new Point(350,250)
          //          val is02 = new Point(455,250)
          //          val os10 = new Point(30,350)
          //          val ms10 = new Point(140,350)
          //          val is10 = new Point(250,350)
          //          val is12 = new Point(455,350)
          //          val ms12 = new Point(565,350)
          //          val os12 = new Point(665,350)
          //          val is20 = new Point(250,450)
          //          val is21 = new Point(350,450)
          //          val is22 = new Point(455,450)
          //          val ms20 = new Point(140,565)
          //          val ms21 = new Point(350,565)
          //          val ms22 = new Point(565,565)
          //          val os20 = new Point(30,670)
          //          val os21 = new Point(350,670)
          //          val os22 = new Point(670,670)
          //          if((-15 < (p.x - os00.x) && (p.x - os00.x) <= 15 && -15 < (p.y - os00.y) &&  (p.y - os00.y) <= 15)) println("Success: os:00")
          //          else if((-15 < (p.x - os01.x) && (p.x - os01.x) <= 15 && -15 < (p.y - os01.y) &&  (p.y - os01.y) <= 15)) println("Success: os:01")
          //          else if((-15 < (p.x - os02.x) && (p.x - os02.x) <= 15 && -15 < (p.y - os02.y) &&  (p.y - os02.y) <= 15)) println("Success: os:02")
          //          else if((-15 < (p.x - ms00.x) && (p.x - ms00.x) <= 15 && -15 < (p.y - ms00.y) &&  (p.y - ms00.y) <= 15)) println("Success: ms:00")
          //          else if((-15 < (p.x - ms01.x) && (p.x - ms01.x) <= 15 && -15 < (p.y - ms01.y) &&  (p.y - ms01.y) <= 15)) println("Success: ms:01")
          //          else if((-15 < (p.x - ms02.x) && (p.x - ms02.x) <= 15 && -15 < (p.y - ms02.y) &&  (p.y - ms02.y) <= 15)) println("Success: ms:02")
          //          else if((-15 < (p.x - is00.x) && (p.x - is00.x) <= 15 && -15 < (p.y - is00.y) &&  (p.y - is00.y) <= 15)) println("Success: is:00")
          //          else if((-15 < (p.x - is01.x) && (p.x - is01.x) <= 15 && -15 < (p.y - is01.y) &&  (p.y - is01.y) <= 15)) println("Success: is:01")
          //          else if((-15 < (p.x - is02.x) && (p.x - is02.x) <= 15 && -15 < (p.y - is02.y) &&  (p.y - is02.y) <= 15)) println("Success: is:02")
          //          else if((-15 < (p.x - os10.x) && (p.x - os10.x) <= 15 && -15 < (p.y - os10.y) &&  (p.y - os10.y) <= 15)) println("Success: os:10")
          //          else if((-15 < (p.x - ms10.x) && (p.x - ms10.x) <= 15 && -15 < (p.y - ms10.y) &&  (p.y - ms10.y) <= 15)) println("Success: ms:10")
          //          else if((-15 < (p.x - is10.x) && (p.x - is10.x) <= 15 && -15 < (p.y - is10.y) &&  (p.y - is10.y) <= 15)) println("Success: is:10")
          //          else if((-15 < (p.x - is12.x) && (p.x - is12.x) <= 15 && -15 < (p.y - is12.y) &&  (p.y - is12.y) <= 15)) println("Success: is:12")
          //          else if((-15 < (p.x - ms12.x) && (p.x - ms12.x) <= 15 && -15 < (p.y - ms12.y) &&  (p.y - ms12.y) <= 15)) println("Success: ms:12")
          //          else if((-15 < (p.x - os12.x) && (p.x - os12.x) <= 15 && -15 < (p.y - os12.y) &&  (p.y - os12.y) <= 15)) println("Success: os:12")
          //          else if((-15 < (p.x - is20.x) && (p.x - is20.x) <= 15 && -15 < (p.y - is20.y) &&  (p.y - is20.y) <= 15)) println("Success: is:20")
          //          else if((-15 < (p.x - is21.x) && (p.x - is21.x) <= 15 && -15 < (p.y - is21.y) &&  (p.y - is21.y) <= 15)) println("Success: is:21")
          //          else if((-15 < (p.x - is22.x) && (p.x - is22.x) <= 15 && -15 < (p.y - is22.y) &&  (p.y - is22.y) <= 15)) println("Success: is:22")
          //          else if((-15 < (p.x - ms20.x) && (p.x - ms20.x) <= 15 && -15 < (p.y - ms20.y) &&  (p.y - ms20.y) <= 15)) println("Success: ms:20")
          //          else if((-15 < (p.x - ms21.x) && (p.x - ms21.x) <= 15 && -15 < (p.y - ms21.y) &&  (p.y - ms21.y) <= 15)) println("Success: ms:21")
          //          else if((-15 < (p.x - ms22.x) && (p.x - ms22.x) <= 15 && -15 < (p.y - ms22.y) &&  (p.y - ms22.y) <= 15)) println("Success: ms:22")
          //          else if((-15 < (p.x - os20.x) && (p.x - os20.x) <= 15 && -15 < (p.y - os20.y) &&  (p.y - os20.y) <= 15)) println("Success: os:20")
          //          else if((-15 < (p.x - os21.x) && (p.x - os21.x) <= 15 && -15 < (p.y - os21.y) &&  (p.y - os21.y) <= 15)) println("Success: os:21")
          //          else if((-15 < (p.x - os22.x) && (p.x - os22.x) <= 15 && -15 < (p.y - os22.y) &&  (p.y - os22.y) <= 15)) println("Success: os:22")
          //          else println("fail!")
          for((k,v)<-ValidMove().hitbox(p)) {
            pCl = v
            pClStr = k
            val kArray = k.split(" ")
            var sq = 999
            kArray(0) match {
              case "OS:" => sq = 0
              case "MS:" => sq = 1
              case "IS:" => sq = 2
            }
            if(pRe.x != v.x || pRe.y != v.y) {
              controller.gamePlayState match {
                case WhiteTurn() =>
                  board.setCords(v, Color.WHITE)
                  controller.moveController(k)
                case BlackTurn() =>
                  board.setCords(v, Color.BLACK)
                  controller.moveController(k)
                case TakeStone(Stone.white) =>
                  controller.moveController(k)
                  if(!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    board.remCoords(v)
                  } else println("take an other Stone!")
                case TakeStone(Stone.black) =>
                  controller.moveController(k)
                  if(!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    board.remCoords(v)
                  } else println("take an other Stone!")
              }
            }
          }
          board.repaint()
        case MouseDragged(_,p,m) =>
          pArray +:= p
        case MouseReleased(_,p,_,_,_) =>
          if (pArray.nonEmpty) {
          for((k,v)<-ValidMove().hitbox(pArray.head)) {
            for((k1,v1)<-ValidMove().hitbox(pArray(pArray.size-1))) {
              pRe = v
              pReStr = k
              val kArray = k.split(" ")
              var sq = 999
              kArray(0) match {
                case "OS:" => sq = 0
                case "MS:" => sq = 1
                case "IS:" => sq = 2
              }
              controller.gamePlayState match {
                case WhiteTurn() =>
                  controller.moveController("move " + k1 + " to " + k)
                  if(controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet)
                    board.remOldSetNew(v1, (v, Color.WHITE))
                  else println("no print in GUI!!!")
                case BlackTurn() =>
                  controller.moveController("move " + k1 + " to " + k)
                  if(controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet)
                    board.remOldSetNew(v1, (v, Color.BLACK))
                  else println("no print in GUI!!!")
                case TakeStone(Stone.white) =>
                  board.remCoords(v)
                  controller.moveController(k)
                case TakeStone(Stone.black) =>
                  board.remCoords(v)
                  controller.moveController(k)
              }
              board.repaint()
              pArray = Array()
            }
          }
        }else println("it was just a click!")
      }

    }
  } //mainPanel
  contents = new BorderPanel {
    add(mainPanel, BorderPanel.Position.Center)
  }
  def clickHandler(): Unit = {
    board.repaint() //repaint with paintComponent, paintComponent print List with new Coordinates
  }

  centerOnScreen()
  visible = true
}