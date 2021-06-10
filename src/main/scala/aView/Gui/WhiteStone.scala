package aView.Gui

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.swing.{Component, Graphics2D}

case class WhiteStone() extends Component {
  var whiteStone:  BufferedImage = ImageIO.read(new File("src/main/resources/aView/Gui/weiss.png"))
  var scaledImage: Image = whiteStone.getScaledInstance(60,60, BufferedImage.TYPE_INT_ARGB)

  override def paintComponent(g :Graphics2D) {
    g.drawImage(scaledImage, 0, 0, null)
  }
}
