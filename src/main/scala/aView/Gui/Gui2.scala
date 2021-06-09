package aView.Gui


import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import scala.swing.Action.NoAction.icon
import scala.swing.{Button, Component, Dimension, FlowPanel, Frame, Graphics2D, MainFrame, Panel}
import java.io.File
import java.awt.{Color, Image, Point}
import scala.collection.immutable.ListMap
import scala.swing.event.MouseClicked

case class Gui2() extends Component {
  val panelDimension = new Dimension(700,700)
  minimumSize = panelDimension
  preferredSize = panelDimension
  maximumSize = panelDimension
  var currentImage: BufferedImage = _
  var backgroundImage:  BufferedImage = ImageIO.read(new File("src/main/resources/aView/Gui/millboard.png"))
  var scaledImage: Image = backgroundImage.getScaledInstance(500,500, BufferedImage.TYPE_INT_ARGB)
  var cordList: ListMap[Point,Color] = ListMap()
  var testList: List[Point] = Nil

  def setCords(p: (Point,Color)): Unit = {
    cordList += (p._1->p._2)
    val debugg = 0
  }

  def remCoords(p: Point): Unit = {
    cordList -= p
    val debugg = 0
  }

  def remOldSetNew(old :Point, nw :(Point,Color)): Unit = {
    cordList -= old
    cordList += (nw._1->nw._2)
  }

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(backgroundImage,0,0,null)
    if(cordList.nonEmpty) {
      for ((k,v)<-cordList) {
        if(v == Color.WHITE) g.setColor(Color.WHITE)
        else g.setColor(Color.BLACK)
        g.fillOval(k.x, k.y, 55, 55)
      }
    }
//    g.setColor(Color.BLACK)
//    g.fillOval(4,4,60,60) // os00
//    g.fillOval(320,4,60,60) //os01
//    g.fillOval(638,4,60,60) // os02
//    g.fillOval(108, 106,60,60) //ms00
//    g.fillOval(320, 106,60,60) //ms01
//    g.fillOval(532, 106,60,60) //ms02
//    g.fillOval(214, 211,60,60) //is00
//    g.fillOval(320, 211,60,60) //is01
//    g.fillOval(426, 211,60,60) //is02
//    g.fillOval(4, 319,60,60) //os10
//    g.fillOval(108, 319,60,60) //ms10
//    g.fillOval(214, 319,60,60) //is10
//    g.fillOval(426, 319,60,60) //is12
//    g.fillOval(532, 319,60,60) //ms12
//    g.fillOval(638, 319,60,60) //os12
//    g.fillOval(214, 424,60,60) //is20
//    g.fillOval(320, 424,60,60) //is21
//    g.fillOval(426, 424,60,60) //is20
//    g.fillOval(108, 531,60,60) //ms20
//    g.fillOval(320, 531,60,60) //ms21
//    g.fillOval(532, 531,60,60) //ms22
//    g.fillOval(4, 640,60,60) //os20
//    g.fillOval(320, 640,60,60) //os21
//    g.fillOval(638, 640,60,60) //os22

    //    g.fillOval(320, 106, 60, 60) //Mitte Mitte
//    g.fillOval(320,211,60,60)
    //upper Row1 = x=4


//    g.setColor(Color.WHITE)
//    g.fillOval(135,0,30,30)
//    g.setColor(Color.BLACK)
//    g.fillOval(270, 0, 30, 30) // oben rechts
//
//    g.fillOval(270,270,30,30) // unten rechts
  }
}