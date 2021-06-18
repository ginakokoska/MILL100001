package mill.aView.Gui

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import scala.swing.Action.NoAction.icon
import scala.swing.{Button, Component, Dimension, FlowPanel, Frame, Graphics2D, MainFrame, Panel}
import java.io.File
import java.awt.{Color, Image, Point}
import scala.collection.immutable.ListMap
import scala.swing.event.MouseClicked

case class Gui() extends Component {
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
  }

  def remCoords(p: Point): Unit = {
    cordList -= p
  }

  def remOldSetNew(old :Point, nw :(Point,Color)): Unit = {
    cordList -= old
    cordList += (nw._1->nw._2)
  }

  def remAll(): Unit = {
    for((k,v)<-cordList){
      cordList -= k
    }
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

  }
}