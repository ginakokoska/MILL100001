package aView.Gui

import scala.swing.event.{ButtonClicked, EditDone}
import scala.swing.{Button, FlowPanel, Frame, Label, MainFrame, TextField}

case class StartGui() extends MainFrame {


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

  listenTo(readPlayer1, readPlayer2, continue)
  reactions += {
    case EditDone(readPlayer1) =>
      println(readPlayer1)
    case EditDone(readPlayer2) =>
      println(readPlayer2)
    case ButtonClicked(continue) =>
      //        readPlayer1.visible = false
      //        readPlayer2.visible = false
      continue
      Gui().grid()
  }
}
