package mill.aView.Gui

import mill.aView.Tui
import mill.controller.{ControllerInterface, RedrawGrid}
import mill.controller.base._
import mill.model.gridComponent.gridBase.{BlackTurn, TakeStone, WhiteTurn}

import java.awt.JobAttributes.DialogType
import java.awt.{Color, ComponentOrientation, Font, Point}
import javax.swing.ImageIcon
import scala.swing.Action.NoAction.icon
import scala.swing.event.{ButtonClicked, Key, KeyPressed, KeyReleased, MouseClicked, MouseDragged, MousePressed, MouseReleased, UIEvent}
import scala.swing.{AbstractButton, Alignment, BorderPanel, BoxPanel, Button, CheckBox, ComboBox, Component, Dialog, Dimension, FlowPanel, Frame, GridBagPanel, GridPanel, Label, MainFrame, Menu, MenuBar, Orientation, Panel, Point, PopupMenu, ProgressBar, RadioMenuItem, Rectangle, RootPanel, Swing, TextField, Window}
import mill.controller.base.Controller
import mill.model.{Stone, StoneState}

case class StartGui(controller: ControllerInterface) extends MainFrame {

  title = "Mill"
  preferredSize = new Dimension(400, 200)
  centerOnScreen()

  var continueIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\continue.png").getImage
  continueIcon = continueIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var restartIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\restart.png").getImage
  restartIcon = restartIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var saveIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\trophy.png").getImage
  saveIcon = saveIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var reloadIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\wow.png").getImage
  reloadIcon = reloadIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var frogIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\MILL100001\\src\\main\\resources\\aView\\Gui\\frog.png").getImage
  frogIcon = frogIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  val colorPanel = Color.getColor("colorPanel" , 12499113)

  val myFont = new Font("monoSpaceD", Font.BOLD, 40)



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

  val colors = Seq("white", "black")
  val comboBox = new ComboBox(items = colors) { }


  val choosePLbt = new Button {
    borderPainted = false
    background = colorPanel
    icon = new ImageIcon(continueIcon)
    listenTo(mouse.clicks)
    reactions += {
      case clicked: MouseClicked =>
        createPl2()
        controller.createPlayer1(namePlayer1.text, comboBox.toString())
    }
  }

  val undo = new Button() {
    background  = colorPanel
    borderPainted = false
    icon = new ImageIcon(restartIcon)
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

  val startgame = new Button {
    background  = colorPanel
    borderPainted = false
    icon = new ImageIcon(continueIcon)
    listenTo(mouse.clicks)
    reactions += {
      case buttonClicked : MouseClicked =>
        board()
        controller.createPlayer2(namePlayer2.text)
        tui.createGrid("3")
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

  val saveButton = new Button {
    borderPainted = false
    background = colorPanel
    icon = new ImageIcon(saveIcon)
    listenTo(mouse.clicks)
    reactions += {
      case clicked: MouseClicked =>
        controller.save()
    }
  }

  val reloadButton = new Button {
    borderPainted = false
    background = colorPanel
    icon = new ImageIcon(reloadIcon)
    listenTo(mouse.clicks)
    reactions += {
      case clicked: MouseClicked =>
        controller.load()
        boardGui2.restoreCoords()
        board()
    }
  }
  listenTo(reloadButton)
  reactions += {
    case clicked: ButtonClicked =>
//      controller.createPlayer2(namePlayer2.text)
      preferredSize = new Dimension(750, 850)
  }


  val welcome = new Label {
    text = "Welcome to Mill"
  }

  val tui = new Tui(controller)

  def createPl2(): Unit = {
    contents = new FlowPanel {
      contents += namePlayer2
      contents += startgame
      background = colorPanel

    }
  }

  def wel(): Unit = {
    contents = new FlowPanel() {
      background = colorPanel
      contents += welcome
      contents += namePlayer1
      contents += choosePLbt
      contents += comboBox
      contents += reloadButton

    }
  }

  var boardGui2 = new Gui()
  var pArray :Array[Point] = Array()
  var pCl = new Point()
  var pClStr = ""
  var pRe = new Point()
  var pReStr = ""



  val boardPanel = new BoxPanel(Orientation.Vertical) {
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
                    winMessageBlack()

                case TakeStone(Stone.black) =>
                  controller.moveController(k)
                  if (!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "removed from ")
                    boardGui2.remCoords(v)
                  }
                  if(controller.win())
                    winMessageWhite()
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
                }
              }
              tui.gameState()
              pArray = Array()

            }
          }
        }
    }

  }

  def winMessageWhite() = {
    //    Dialog.showMessage(contents.head, "White wins!", title = "Winner!")
    Dialog.showMessage(contents.head, "White wins!", "Winner", Dialog.Message.Info, icon)
    icon = new ImageIcon(frogIcon)
  }

  def winMessageBlack() = {
    //    Dialog.showMessage(contents.head, "White wins!", title = "Winner!")
    Dialog.showMessage(contents.head, "Black wins!", "Winner", Dialog.Message.Info, icon)
    icon = new ImageIcon(frogIcon)
  }

  listenTo(controller)
  reactions += {
    case event: RedrawGrid => boardGui2.repaint()
  }


  def board(): Unit = {
    contents = new FlowPanel() {
      contents += new Label(controller.player1.name, null, Alignment.Leading) {
        font = myFont
      }
      contents += new Label("  vs  ", null, Alignment.Trailing) {
        font = myFont
      }
      contents += new Label(controller.player2.name, null, Alignment.Trailing) {
        font = myFont
      }
      xLayoutAlignment = 750
      yLayoutAlignment = 750
      background = colorPanel
      maximumSize = new Dimension(850,850)
      contents += boardPanel
      contents += saveButton
      contents += undo
    }
  }



  visible = true



}