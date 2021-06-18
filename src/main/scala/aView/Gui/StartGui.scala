package aView.Gui
import aView.Tui
import controller.{ControllerInterface, RedrawGrid}
import controller.base._
import model.gridComponent.gridBase.{BlackTurn, TakeStone, WhiteTurn}

import java.awt.JobAttributes.DialogType
import java.awt.{Color, ComponentOrientation, Font, Point}
import javax.swing.ImageIcon
import scala.swing.Action.NoAction.icon
import scala.swing.event.{ButtonClicked, Key, KeyPressed, KeyReleased, MouseClicked, MouseDragged, MousePressed, MouseReleased, UIEvent}
import scala.swing.{AbstractButton, Alignment, BorderPanel, BoxPanel, Button, CheckBox, ComboBox, Component, Dialog, Dimension, FlowPanel, Frame, GridBagPanel, GridPanel, Label, MainFrame, Menu, MenuBar, Orientation, Panel, Point, PopupMenu, ProgressBar, RadioMenuItem, Rectangle, RootPanel, Swing, TextField, Window}
import controller.base.Controller
import model.{Stone, StoneState}

case class StartGui(controller: ControllerInterface) extends MainFrame {

  title = "Mill"
  preferredSize = new Dimension(400, 200)
  centerOnScreen()

//  val menu = new MenuBar() {
//    val tempmenu = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/millboard.png").getImage
//    val resizemenu = tempmenu.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)
//    icon = new ImageIcon(resizemenu)
//    background = Color.green
//
//  }

  val namePlayer1 = new TextField {
    text = "enter Name of Player1"
    font =  new Font("monoSpaceD", Font.ITALIC, 12)
    listenTo(mouse.clicks)
    reactions += {
      case event: MouseClicked => text = ""
    }
    columns = 22
    visible = true
  }

  val colors = Seq("white", "black")
  val cb = new ComboBox(items = colors) {
  }

  val choosePLbt = new Button {
    borderPainted = false
    background  = Color.getColor("boardcolor", 12499113)
    val tmpch = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\continue.png").getImage
    val resizech = tmpch.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)
    icon = new ImageIcon(resizech)
    listenTo(mouse.clicks)
    reactions += {
      case e : MouseClicked =>
        createPl2()
        controller.createPlayer1(namePlayer1.text, cb.toString())
    }
    listenTo(keys)
    reactions += {
      case e: KeyPressed =>
        createPl2()
        controller.createPlayer1(namePlayer1.text, cb.toString())
    }
  }


  def createPl2(): Unit = {
    contents = new FlowPanel {
      contents += namePlayer2
      contents += startgame
      background = Color.getColor("panelcolor", 12499113)

    }
  }


  val namePlayer2 = new TextField {
    text = "enter Name of Player2"
    font =  new Font("monoSpaceD", Font.ITALIC, 12)
    listenTo(mouse.clicks)
    reactions += {
      case event: MouseClicked =>
        text = ""
    }
    columns = 22
    visible = true
  }

  val stoneWhite = new Label() {
    val tmpw = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/blackstone.png").getImage
    val resizew = tmpw.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)
    icon = new ImageIcon(resizew)
    enabled = true
  }

  val welcome = new Label {
    text = "Welcome to Mill"
  }

  val tui = new Tui(controller)

  val startgame = new Button {
    background  = Color.getColor("boardcolor", 12499113)
    borderPainted = false
    val tmpst = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\continue.png").getImage
    val resizest = tmpst.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)
    icon = new ImageIcon(resizest)
    listenTo(mouse.clicks, keys)
    reactions += {
      case e : MouseClicked =>
        board()
        controller.createPlayer2(namePlayer2.text)
        controller.gridSize("3")
        tui.update
        tui.gameState()
        repaint()
      case KeyPressed(_, Key.Enter, _, _) =>
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
    case clicked: ButtonClicked =>
      controller.createPlayer2(namePlayer2.text)
      preferredSize = new Dimension(750, 850)
  }
  listenTo(startgame)
  reactions += {
    case KeyPressed(_, Key.Enter, _, _) =>
      controller.createPlayer2(namePlayer2.text)
      preferredSize = new Dimension(750, 850)

  }



  def wel(): Unit = {
    contents = new FlowPanel() {
      contents += welcome
      contents += namePlayer1
      contents += choosePLbt
      contents += cb
//      contents += menu
      background = Color.getColor("panelcolor", 12499113)
    }
  }

  var boardGui2 = new Gui()
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
          if(!k.equals("")) {
            pCl = v
            pClStr = k
            val kArray = k.split(" ")
            var sq = 999
            kArray(0) match {
              case "OS:" => sq = 0
              case "MS:" => sq = 1
              case "IS:" => sq = 2
            }
            if (pRe.x != v.x || pRe.y != v.y) {
              controller.gamePlayState match {
                case WhiteTurn() =>
                  if(controller.player1.countState(StoneState.notUsed) > 0) {
                    tui.stoneSet(k, "set to ")
                    boardGui2.setCords(v, Color.WHITE)
                    controller.moveController(k)
                  }
                case BlackTurn() =>
                  if(controller.player2.countState(StoneState.notUsed) > 0) {
                    tui.stoneSet(k, "set to ")
                    boardGui2.setCords(v, Color.BLACK)
                    controller.moveController(k)
                  }
                case TakeStone(Stone.white) =>
                  controller.moveController(k)
                  if (!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "removed from ")
                    boardGui2.remCoords(v)
                  }
                  if(controller.win())
                    winMessage
                case TakeStone(Stone.black) =>
                  controller.moveController(k)
                  if (!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "removed from ")
                    boardGui2.remCoords(v)
                  }
                  if(controller.win())
                    winMessage
              }
            }
          }
          tui.gameState()
        }
      case MouseDragged(_,p,m) =>
        pArray +:= p
      case MouseReleased(_,p,_,_,_) =>
        if (pArray.nonEmpty) {
          for((k,v)<-ValidMove().hitbox(pArray.head)) {
            for((k1,v1)<-ValidMove().hitbox(pArray(pArray.size-1))) {
              if (!k.equals(k1) && !k.equals("") && !k1.equals("")) {
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
                    controller.getPlayerState(controller.player1)
                    controller.moveController("move " + k1 + " to " + k)
                    if (controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                      tui.stoneSet(k, "moved from " + k1 + " to ")
                      boardGui2.remOldSetNew(v1, (v, Color.WHITE))
                    }
                  case BlackTurn() =>
                    controller.getPlayerState(controller.player2)
                    controller.moveController("move " + k1 + " to " + k)
                    if (controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                      tui.stoneSet(k, "moved from " + k1 + " to ")
                      boardGui2.remOldSetNew(v1, (v, Color.BLACK))
                    }
//                  case TakeStone(Stone.white) =>
//                    tui.stoneSet(k, "removed from ")
//                    boardGui2.remCoords(v)
//                    controller.getPlayerState(controller.player1)
//                    controller.moveController(k)
//                  case TakeStone(Stone.black) =>
//                    tui.stoneSet(k, "removed from")
//                    boardGui2.remCoords(v)
//                    controller.getPlayerState(controller.player2)
//                    controller.moveController(k)
                }
              }
                tui.gameState()
                pArray = Array()

            }
          }
        }
    }

  }

  def winMessage() = {
//    Dialog.showMessage(contents.head, "White wins!", title = "Winner!")
    Dialog.showMessage(contents.head, "White wins!", "Winner", Dialog.Message.Info, icon)
    val tmpwin = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\trophy.png").getImage
    val resizeu = tmpwin.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)
    icon = new ImageIcon(resizeu)

  }

  listenTo(controller)
  reactions += {
    case event: RedrawGrid => boardGui2.repaint()
  }

  val undo = new Button() {
    background  = Color.getColor("boardcolor", 12499113)
    borderPainted = false
    val tmpu = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\restart.png").getImage
    val resizeu = tmpu.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)
    icon = new ImageIcon(resizeu)
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>
        controller.player1.fillStone()
        controller.player2.fillStone()
        controller.undo()
        boardGui2.remAll()
        tui.update
        controller.gamePlayState = WhiteTurn()

    }
  }

  val whiteStone = new WhiteStone()

  def board(): Unit = {
    contents = new FlowPanel() {

      contents += new Label(controller.player1.name, null, Alignment.Leading) {
        font = new Font("monoSpaceD", Font.BOLD, 40)
      }
      contents += new Label("  vs  ", null, Alignment.Trailing) {
        font = new Font("monoSpaceD", Font.BOLD, 40)
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
//      contents += menu
      background = Color.getColor("panelcolor", 12499113)

    }
  }



  visible = true



}