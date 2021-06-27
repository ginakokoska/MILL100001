package mill.aView.Gui

import mill.aView.Tui
import mill.controller.{ControllerInterface, RedrawGrid}
import mill.model.gridComponent.gridBase.{BlackTurn, TakeStone, WhiteTurn}
import java.awt.{Color,Font, Image}
import javax.swing.ImageIcon
import scala.swing.Action.NoAction.icon
import scala.swing.event.{ButtonClicked, MouseClicked, MouseDragged, MouseReleased}
import scala.swing.{ Alignment,  BoxPanel, Button, ComboBox, Dialog, Dimension, FlowPanel, Label, MainFrame, Orientation, Point, TextField}
import mill.model.{SetState, Stone, StoneState}

/*/
  This class creates the GUI.
 */

case class StartGui(controller: ControllerInterface) extends MainFrame {

  /*
  TODO:Please click on the Folder *Resources* and copy the ABSOLUTE Path of each Icon :)
 */
  var continueIcon: Image = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/continue.png").getImage
  continueIcon = continueIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var restartIcon: Image = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/restart.png").getImage
  restartIcon = restartIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var saveIcon: Image = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/save.png").getImage
  saveIcon = saveIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var reloadIcon: Image = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/fromfile.png").getImage
  reloadIcon = reloadIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  var frogIcon: Image = new ImageIcon("/home/gina/IdeaProjects/MILL100001/src/main/resources/aView/Gui/frog.png").getImage
  frogIcon = frogIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)

  val colorPanel: Color = Color.getColor("colorPanel" , 12499113)

  val myFont = new Font("monoSpaceD", Font.BOLD, 40)

  title = "Mill"
  preferredSize = new Dimension(400, 200)
  resizable = false
  centerOnScreen()

  val namePlayer1 = new TextField {
    text = "player1"
    font =  new Font("monoSpaceD", Font.ITALIC, 12)
    listenTo(mouse.clicks)
    reactions += {
      case event: MouseClicked => text = ""
    }
    columns = 22
    visible = true
  }

  val namePlayer2 = new TextField {
    text = "player2"
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

  val choosePlayerButton = new Button {
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

  val undoButton = new Button() {
    background  = colorPanel
    borderPainted = false
    icon = new ImageIcon(restartIcon)
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>
        controller.player1.fillStone()
        controller.player2.fillStone()
        controller.undo()
        boardGui.remAll()
        tui.update
        controller.gamePlayState = WhiteTurn()

    }
  }

  val startgameButton = new Button {
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
  listenTo(startgameButton)
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
        boardGui.restoreCoords()
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
      resizable = false
      contents += namePlayer2
      contents += startgameButton
      background = colorPanel

    }
  }

  def wel(): Unit = {
    contents = new FlowPanel() {
      resizable = false
      background = colorPanel
      contents += welcome
      contents += namePlayer1
      contents += choosePlayerButton
      contents += comboBox
      contents += reloadButton

    }
  }

  var boardGui = new Board()
  var coord :Array[Point] = Array()
  var startPoint = new Point()
  var startStr = ""
  var destinationPoint = new Point()
  var destStr = ""

  val boardPanel = new BoxPanel(Orientation.Vertical) {
    contents += boardGui
    listenTo(this.mouse.clicks, this.mouse.moves)
    reactions += {
      case MouseClicked(_,p,_,_,_) =>
        for((k,v)<-ValidMove().hitbox(p)) {
          if(!k.equals("")) {
            startPoint = v
            startStr = k
            val kArray = k.split(" ")
            var sq = 999
            kArray(0) match {
              case "OS:" => sq = 0
              case "MS:" => sq = 1
              case "IS:" => sq = 2
            }
            if (destinationPoint.x != v.x || destinationPoint.y != v.y) {
              controller.gamePlayState match {
                case WhiteTurn() =>
                  if(controller.player1.countState(StoneState.notUsed) > 0 &&
                    !controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "set to ")
                    boardGui.setCords(v, Color.WHITE)
                    controller.moveController(k)
                  }
                case BlackTurn() =>
                  if(controller.player2.countState(StoneState.notUsed) > 0 &&
                    !controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "set to ")
                    boardGui.setCords(v, Color.BLACK)
                    controller.moveController(k)
                  }
                case TakeStone(Stone.white) =>
                  controller.moveController(k)
                  if (!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "removed from ")
                    boardGui.remCoords(v)
                  }
                  if(controller.win())
                    winMessage("black")
                case TakeStone(Stone.black) =>
                  controller.moveController(k)
                  if (!controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                    tui.stoneSet(k, "removed from ")
                    boardGui.remCoords(v)
                  }
                  if(controller.win()) {
                    winMessage("white")
                  }
              }
            }
          }
          tui.gameState()
        }
      case MouseDragged(_,p,m) =>
        coord +:= p
      case MouseReleased(_,p,_,_,_) =>
        if (coord.nonEmpty) {
          for((k,v)<-ValidMove().hitbox(coord.head)) {
            for((k1,v1)<-ValidMove().hitbox(coord(coord.length-1))) {
              if (!k.equals(k1) && !k.equals("") && !k1.equals("")) {
                destinationPoint = v
                destStr = k
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
                      boardGui.remOldSetNew(v1, (v, Color.WHITE))
                    }
                  case BlackTurn() =>
                    controller.getPlayerState(controller.player2)
                    controller.moveController("move " + k1 + " to " + k)
                    if (controller.grid.gridList(sq)(kArray(1).charAt(0).asDigit)(kArray(1).charAt(1).asDigit).isSet) {
                      tui.stoneSet(k, "moved from " + k1 + " to ")
                      boardGui.remOldSetNew(v1, (v, Color.BLACK))
                    }
                  case _ =>
                    println("Please try to take a stone again")
                }
              }
              tui.gameState()
              coord = Array()
            }
          }
        }
    }

  }


  def winMessage(color: String): Unit = {
    icon = new ImageIcon(frogIcon)
    if (color.equals("white")) {
      Dialog.showMessage(contents.head, namePlayer2.toString() + "Black wins!", title = "Winner!", Dialog.Message.Info, icon )
    } else {
      Dialog.showMessage(contents.head, namePlayer1.toString() + "White wins!", "Winner", Dialog.Message.Info, icon )
      boardGui.remAll()
    }
    preferredSize = new Dimension(400, 200)
    boardGui.remAll()
    controller.setPlayerState(SetState())
    controller.gamePlayState = WhiteTurn()
    controller.player1.fillStone()
    controller.player2.fillStone()
    repaint()
  }

  // TODO: eventuell lösche
  //  def restartQuestion() {
  //    val res = Dialog.showConfirmation(contents.head,
  //      "Do you really want to quit?",
  //      optionType=Dialog.Options.YesNo,
  //      title=title)
  //    if (res == Dialog.Result.Ok) {
  //      sys.exit(0)
  //    }
  //  }

  listenTo(controller)
  reactions += {
    case event: RedrawGrid => boardGui.repaint()
  }


  def board(): Unit = {
    contents = new FlowPanel() {
      resizable = false
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
      contents += undoButton
    }
  }

  visible = true

}