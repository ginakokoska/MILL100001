package aView.Gui
import aView.tui
import controller.base.Controller
import model.gridComponent.gridBase.{BlackTurn, TakeStone, WhiteTurn}
import model.playerComponent.Stone
import org.w3c.dom.html.HTMLFontElement
import scalafx.geometry.Orientation.Vertical

import java.awt.image.BufferedImage
import java.awt.{Color, ComponentOrientation, Font, Point}
import java.io.File
import java.util.Locale
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.text.html.HTML
import scala.swing.Action.NoAction.icon
import scala.swing.event.{ButtonClicked, Key, MouseClicked, MouseDragged, MousePressed, MouseReleased, UIEvent}
import scala.swing.{Alignment, BorderPanel, BoxPanel, Button, CheckBox, ComboBox, Component, Dialog, Dimension, FlowPanel, Frame, GridBagPanel, GridPanel, Label, MainFrame, Menu, Orientation, Panel, Point, PopupMenu, RadioMenuItem, Rectangle, RootPanel, Swing, TextField}


case class StartGui(controller: Controller) extends MainFrame {

  title = "Mill"
  preferredSize = new Dimension(850, 850)

  val namePlayer1 = new TextField {
    text = "Name of Player1"
    listenTo(mouse.clicks)
    reactions += {
      case event: MouseClicked => text = ""
    }
    columns = 20
    visible = true

  }

  val colors = Seq("white", "black")
  val cb = new ComboBox(items = colors) {
  }

  val choosePLbt = new Button {
    text = "create Player 1"
    listenTo(mouse.clicks)
    reactions += {
      case _ : MouseClicked =>
        createPl2()
        controller.createPlayer1(namePlayer1.text, cb.toString())
//        println(namePlayer1.text)
    }
  }





  def createPl2(): Unit = {
    contents = new FlowPanel {
      contents += namePlayer2
      contents += startgame

    }
  }


  val namePlayer2 = new TextField {
    text = "enter Name of Player2"
//    title = "mill"
    listenTo(mouse.clicks)
    reactions += {
      case event: MouseClicked =>
        text = ""
    }
    columns = 20
    visible = true
  }

  val stoneWhite = new Label() {
    val tmpw = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/blackstone.png").getImage
    val resizew = tmpw.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)
    icon = new ImageIcon(resizew)
    enabled = true
    background = Color.yellow

  }


  val welcome = new Label {
    text = "Welcome to Mill"
  }
  val tui = new tui(controller)
  val startgame = new Button {
    text = "start game"
    listenTo(mouse.clicks)
    reactions += {
      case e : MouseClicked =>
        board()
        controller.createPlayer2(namePlayer2.text)
        controller.gridSize("3")
        tui.update
        tui.gameState()
        repaint()
    }
  }
  listenTo(startgame)
  reactions += {
    case clicked: ButtonClicked => controller.createPlayer2(namePlayer2.text)
  }
  def wel(): Unit = {
    contents = new FlowPanel() {
      contents += welcome
      contents += namePlayer1
      contents += choosePLbt
      contents += cb


    }
  }
  var boardGui2 = new Gui2()
  var pArray :Array[Point] = Array()
  var pCl = new Point()
  var pClStr = ""
  var pRe = new Point()
  var pReStr = ""


  val xPanel = new BoxPanel(Orientation.Vertical) {

    contents += boardGui2

    listenTo(this.mouse.clicks, this.mouse.moves)
    reactions += {
      case MouseClicked(_,p,_,_,_) =>
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
                tui.stoneSet(k, "set to ")
                boardGui2.setCords(v, Color.WHITE)
                controller.moveController(k)
              case BlackTurn() =>
                tui.stoneSet(k, "set to ")
                boardGui2.setCords(v, Color.BLACK)
                controller.moveController(k)
              case TakeStone(Stone.white) =>
                controller.moveController(k)
                if(!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                  tui.stoneSet(k, "removed from ")
                  boardGui2.remCoords(v)
                }
              case TakeStone(Stone.black) =>
                controller.moveController(k)
                if(!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                  tui.stoneSet(k, "removed from ")
                  boardGui2.remCoords(v)
                }
            }
          }
          boardGui2.repaint()
          tui.gameState()
        }
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
                    tui.stoneSet(k, "moved from " + k1 + " to ")
                    boardGui2.remOldSetNew(v1, (v, Color.WHITE))
                case BlackTurn() =>
                  controller.moveController("move " + k1 + " to " + k)
                  if(controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet)
                    tui.stoneSet(k, "moved from " + k1 + " to ")
                  boardGui2.remOldSetNew(v1, (v, Color.BLACK))
                case TakeStone(Stone.white) =>
                  tui.stoneSet(k, "removed from ")
                  boardGui2.remCoords(v)
                  controller.moveController(k)
                case TakeStone(Stone.black) =>
                  tui.stoneSet(k, "removed from")
                  boardGui2.remCoords(v)
                  controller.moveController(k)
              }
              boardGui2.repaint()
              tui.gameState()
              pArray = Array()
            }
          }
        }
    }

  }

  val undo = new Button("restart") {
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>
        controller.undo()
        boardGui2.remAll()
        boardGui2.repaint()
    }
  }

  val whiteStone = new WhiteStone()

  def board(): Unit = {
    contents = new FlowPanel() {

      contents += new Label(controller.player1.name, null, Alignment.Leading) {
        font = new Font("monoSpaceD", Font.BOLD, 40)
      }
      contents += new Label("   ", null, Alignment.Trailing) {
        font = new Font("monoSpaceD", Font.BOLD, 60)
      }
      contents += new Label(controller.player2.name, null, Alignment.Trailing) {
        font = new Font("monoSpaceD", Font.BOLD, 40)
      }

      xLayoutAlignment = 750
      yLayoutAlignment = 750
      maximumSize = new Dimension(850,850)
      contents += stoneWhite
      contents += xPanel
      contents += undo
      background = Color.lightGray
    }
  }

  visible = true



}