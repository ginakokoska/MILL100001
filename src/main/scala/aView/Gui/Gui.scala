package aView.Gui
import aView.Gui._
import scalafx.scene.image.ImageView

import scala.swing.Component
import scalafx.scene.paint._
import scala.swing.{MainFrame,TextField, FlowPanel,Button,Label}
import java.awt.{Color, Dimension, Graphics2D}
import javax.imageio.ImageIO
import scala.reflect.internal.util.NoFile.file
import scala.swing.event.{ButtonClicked, EditDone, MouseClicked, MouseDragged, MousePressed, MouseReleased}
import scala.swing.Graphics2D
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{BoundingBox, Insets}
import scalafx.scene.{Scene, SubScene}
import scalafx.scene.effect.DropShadow
import scalafx.scene.image._
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.{DarkGray, DarkRed, Red, White}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text

case class Gui() extends MainFrame {
//  def main(args: Array[String]): Unit = {
    title = "Mill"
    this.preferredSize = new Dimension(650,600)

    object celsius extends TextField {
      columns = 5
    }

    object fahrenheit extends TextField {
      columns = 5
    }

    def rechner = new FlowPanel {
      contents += celsius
      contents += new Label("Celsius = ")
      contents += fahrenheit
      contents += new Label("Fahrenheit")

//      border = Swing.EmptyBorder(15, 10, 10, 10)
    }

    object readPlayer1 extends TextField {
      columns = 15
    }
    object readPlayer2 extends TextField {
      columns = 15
    }

    val continue = new Button("continue")

    def startGui() = {
      contents = new FlowPanel() {
        contents += new Label("Name Player 1")
        contents += readPlayer1
        contents += new Label("Name Player 2")
        contents += readPlayer2
        contents += continue
      }
    }

    def grid() = {
      contents = new FlowPanel {
        override def paintComponent(g: Graphics2D){
          g.drawRect(100,0,400,400) //Ecke oben links
          g.drawRect(175,75,250,250)
          g.drawRect(250, 150, 100, 100)
          g.drawLine(300,250,300,400)
          g.drawLine(300,150,300,0)
          g.drawLine(100,200,250,200)
          g.drawLine(350,200,500,200)
          p(g)
        }

        listenTo(this.mouse.clicks)
        var tmpPS = ""
        reactions += {
          //        case MousePressed(_,point,_,_,_) => p1 = point.toString
          case MouseReleased(_,point,_,_,_) =>
            val map = ValidMove().validClick(point.x, point.y)
            for((k,v)<-map){
              if(v) println("Success: move " + tmpPS + " to " + k)
              else println("Fail")
              tmpPS = k
            }
          //          val os00 = new Point(100,0)
          //          val os01 = new Point(300,0)
          //          val os02 = new Point(500,0)
          //          val ms00 = new Point(175,75)
          //          val ms01 = new Point(300,75)
          //          val ms02 = new Point(425,75)
          //          val is00 = new Point(250,150)
          //          val is01 = new Point(300,150)
          //          val is02 = new Point(350,150)
          //          val os10 = new Point(100,200)
          //          val ms10 = new Point(175,200)
          //          val is10 = new Point(250,200)
          //          val is12 = new Point(350,200)
          //          val ms12 = new Point(425,200)
          //          val os12 = new Point(500,200)
          //          val is20 = new Point(250,250)
          //          val is21 = new Point(300,250)
          //          val is22 = new Point(350,250)
          //          val ms20 = new Point(175,325)
          //          val ms21 = new Point(300,325)
          //          val ms22 = new Point(425,325)
          //          val os20 = new Point(100,400)
          //          val os21 = new Point(300,400)
          //          val os22 = new Point(500,400)
          //
          //          if((-15 < (point.x - os00.x) && (point.x - 100) <= 15 && -15 < (point.y - os00.y) &&  (point.y - 0) <= 15)) println("Success: move " + tmpPS + " to OS:00")
          //          else if(-15 < (point.x - os01.x) && (point.x - 300) <= 15 && -15 < (point.y - os01.y) && (point.y - 0) <= 15) println("Success: OS:01")
          //          else if(-15 < (point.x - os02.x) && (point.x - 500) <= 15 && -15 < (point.y - os02.y) && (point.y - 0) <= 15) println("Success: OS:02")
          //          else if(-15 < (point.x - ms00.x) && (point.x - 175) <= 15 && -15 < (point.y - ms00.y) && (point.y - 75) <= 15) println("Success: MS:00")
          //          else if(-15 < (point.x - ms01.x) && (point.x - 300) <= 15 && -15 < (point.y - ms01.y) && (point.y - 75) <= 15) println("Success: MS:01")
          //          else if(-15 < (point.x - ms02.x) && (point.x - 425) <= 15 && -15 < (point.y - ms02.y) && (point.y - 75) <= 15) println("Success: MS:02")
          //          else if(-15 < (point.x - is00.x) && (point.x - 250) <= 15 && -15 < (point.y - is00.y) && (point.y - 150) <= 15) println("Success: IS:00")
          //          else if(-15 < (point.x - is01.x) && (point.x - 300) <= 15 && -15 < (point.y - is01.y) && (point.y - 150) <= 15) println("Success: IS:01")
          //          else if(-15 < (point.x - is02.x) && (point.x - 350) <= 15 && -15 < (point.y - is02.y) && (point.y - 150) <= 15) println("Success: IS:02")
          //          else if(-15 < (point.x - os10.x) && (point.x - 100) <= 15 && -15 < (point.y - os10.y) && (point.y - 200) <= 15) println("Success: OS:10")
          //          else if(-15 < (point.x - ms10.x) && (point.x - 175) <= 15 && -15 < (point.y - ms10.y) && (point.y - 200) <= 15) println("Success: MS:10")
          //          else if(-15 < (point.x - is10.x) && (point.x - 250) <= 15 && -15 < (point.y - is10.y) && (point.y - 200) <= 15) println("Success: IS:10")
          //          else if(-15 < (point.x - is12.x) && (point.x - 350) <= 15 && -15 < (point.y - is12.y) && (point.y - 200) <= 15) println("Success: IS:12")
          //          else if(-15 < (point.x - ms12.x) && (point.x - 425) <= 15 && -15 < (point.y - ms12.y) && (point.y - 200) <= 15) println("Success: MS:12")
          //          else if(-15 < (point.x - os12.x) && (point.x - 500) <= 15 && -15 < (point.y - os12.y) && (point.y - 200) <= 15) println("Success: OS:12")
          //          else if(-15 < (point.x - is20.x) && (point.x - 250) <= 15 && -15 < (point.y - is20.y) && (point.y - 250) <= 15) println("Success: IS:20")
          //          else if(-15 < (point.x - is21.x) && (point.x - 300) <= 15 && -15 < (point.y - is21.y) && (point.y - 250) <= 15) println("Success: IS:21")
          //          else if(-15 < (point.x - is22.x) && (point.x - 350) <= 15 && -15 < (point.y - is22.y) && (point.y - 250) <= 15) println("Success: IS:22")
          //          else if(-15 < (point.x - ms20.x) && (point.x - 175) <= 15 && -15 < (point.y - ms20.y) && (point.y - 325) <= 15) println("Success: MS:20")
          //          else if(-15 < (point.x - ms21.x) && (point.x - 300) <= 15 && -15 < (point.y - ms21.y) && (point.y - 325) <= 15) println("Success: MS:21")
          //          else if(-15 < (point.x - ms22.x) && (point.x - 425) <= 15 && -15 < (point.y - ms22.y) && (point.y - 325) <= 15) println("Success: MS:22")
          //          else if(-15 < (point.x - os20.x) && (point.x - 100) <= 15 && -15 < (point.y - os20.y) && (point.y - 400) <= 15) println("Success: OS:20")
          //          else if(-15 < (point.x - os21.x) && (point.x - 300) <= 15 && -15 < (point.y - os21.y) && (point.y - 400) <= 15) println("Success: OS:21")
          //          else if(-15 < (point.x - os22.x) && (point.x - 500) <= 15 && -15 < (point.y - os22.y) && (point.y - 400) <= 15) println("Success: OS:22")
          //          else println("Fail")
          case MousePressed(_,point,_,_,_) =>

            val map = ValidMove().validClick(point.x, point.y)
            for((k,v)<-map){
              if(v) println("Success:" + k)
              else println("Fail")
              tmpPS = k
            }
          //          val os00 = new Point(100,0)
          //          val os01 = new Point(300,0)
          //          val os02 = new Point(500,0)
          //          val ms00 = new Point(175,75)
          //          val ms01 = new Point(300,75)
          //          val ms02 = new Point(425,75)
          //          val is00 = new Point(250,150)
          //          val is01 = new Point(300,150)
          //          val is02 = new Point(350,150)
          //          val os10 = new Point(100,200)
          //          val ms10 = new Point(175,200)
          //          val is10 = new Point(250,200)
          //          val is12 = new Point(350,200)
          //          val ms12 = new Point(425,200)
          //          val os12 = new Point(500,200)
          //          val is20 = new Point(250,250)
          //          val is21 = new Point(300,250)
          //          val is22 = new Point(350,250)
          //          val ms20 = new Point(175,325)
          //          val ms21 = new Point(300,325)
          //          val ms22 = new Point(425,325)
          //          val os20 = new Point(100,400)
          //          val os21 = new Point(300,400)
          //          val os22 = new Point(500,400)
          //
          //          if((-15 < (point.x - os00.x) && (point.x - 100) <= 15 && -15 < (point.y - os00.y) &&  (point.y - 0) <= 15)) println("Success: OS:00")
          //          else if(-15 < (point.x - os01.x) && (point.x - 300) <= 15 && -15 < (point.y - os01.y) && (point.y - 0) <= 15) println("Success: OS:01")
          //          else if(-15 < (point.x - os02.x) && (point.x - 500) <= 15 && -15 < (point.y - os02.y) && (point.y - 0) <= 15) println("Success: OS:02")
          //          else if(-15 < (point.x - ms00.x) && (point.x - 175) <= 15 && -15 < (point.y - ms00.y) && (point.y - 75) <= 15) println("Success: MS:00")
          //          else if(-15 < (point.x - ms01.x) && (point.x - 300) <= 15 && -15 < (point.y - ms01.y) && (point.y - 75) <= 15) println("Success: MS:01")
          //          else if(-15 < (point.x - ms02.x) && (point.x - 425) <= 15 && -15 < (point.y - ms02.y) && (point.y - 75) <= 15) println("Success: MS:02")
          //          else if(-15 < (point.x - is00.x) && (point.x - 250) <= 15 && -15 < (point.y - is00.y) && (point.y - 150) <= 15) println("Success: IS:00")
          //          else if(-15 < (point.x - is01.x) && (point.x - 300) <= 15 && -15 < (point.y - is01.y) && (point.y - 150) <= 15) println("Success: IS:01")
          //          else if(-15 < (point.x - is02.x) && (point.x - 350) <= 15 && -15 < (point.y - is02.y) && (point.y - 150) <= 15) println("Success: IS:02")
          //          else if(-15 < (point.x - os10.x) && (point.x - 100) <= 15 && -15 < (point.y - os10.y) && (point.y - 200) <= 15) {
          //            println("Success: OS:10 ")
          //            tmpPS = "OS: 10"
          //
          //          }
          //          else if(-15 < (point.x - ms10.x) && (point.x - 175) <= 15 && -15 < (point.y - ms10.y) && (point.y - 200) <= 15) println("Success: MS:10")
          //          else if(-15 < (point.x - is10.x) && (point.x - 250) <= 15 && -15 < (point.y - is10.y) && (point.y - 200) <= 15) println("Success: IS:10")
          //          else if(-15 < (point.x - is12.x) && (point.x - 350) <= 15 && -15 < (point.y - is12.y) && (point.y - 200) <= 15) println("Success: IS:12")
          //          else if(-15 < (point.x - ms12.x) && (point.x - 425) <= 15 && -15 < (point.y - ms12.y) && (point.y - 200) <= 15) println("Success: MS:12")
          //          else if(-15 < (point.x - os12.x) && (point.x - 500) <= 15 && -15 < (point.y - os12.y) && (point.y - 200) <= 15) println("Success: OS:12")
          //          else if(-15 < (point.x - is20.x) && (point.x - 250) <= 15 && -15 < (point.y - is20.y) && (point.y - 250) <= 15) println("Success: IS:20")
          //          else if(-15 < (point.x - is21.x) && (point.x - 300) <= 15 && -15 < (point.y - is21.y) && (point.y - 250) <= 15) println("Success: IS:21")
          //          else if(-15 < (point.x - is22.x) && (point.x - 350) <= 15 && -15 < (point.y - is22.y) && (point.y - 250) <= 15) println("Success: IS:22")
          //          else if(-15 < (point.x - ms20.x) && (point.x - 175) <= 15 && -15 < (point.y - ms20.y) && (point.y - 325) <= 15) println("Success: MS:20")
          //          else if(-15 < (point.x - ms21.x) && (point.x - 300) <= 15 && -15 < (point.y - ms21.y) && (point.y - 325) <= 15) println("Success: MS:21")
          //          else if(-15 < (point.x - ms22.x) && (point.x - 425) <= 15 && -15 < (point.y - ms22.y) && (point.y - 325) <= 15) println("Success: MS:22")
          //          else if(-15 < (point.x - os20.x) && (point.x - 100) <= 15 && -15 < (point.y - os20.y) && (point.y - 400) <= 15) println("Success: OS:20")
          //          else if(-15 < (point.x - os21.x) && (point.x - 300) <= 15 && -15 < (point.y - os21.y) && (point.y - 400) <= 15) println("Success: OS:21")
          //          else if(-15 < (point.x - os22.x) && (point.x - 500) <= 15 && -15 < (point.y - os22.y) && (point.y - 400) <= 15) println("Success: OS:22")
          //          else println("Fail")
        }
      }
    }
    def p(g :Graphics2D): Unit = {
      g.drawImage(ImageIO.read(getClass.getResource("/blackMillStone.JPG")), null, 300, 200)
    }



    contents = continue
    listenTo(readPlayer1, readPlayer2, continue)
    reactions += {
      case EditDone(readPlayer1) =>
        println(readPlayer1)
      case EditDone(readPlayer2) =>
        println(readPlayer2)
      case ButtonClicked(continue) =>
//        readPlayer1.visible = false
//        readPlayer2.visible = false


    }

    visible = true

//  }
}