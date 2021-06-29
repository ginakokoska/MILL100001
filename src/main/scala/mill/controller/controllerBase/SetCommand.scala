package mill.controller.controllerBase

import mill.model.gridComponent.gridBase.Handler
import mill.util.Command

/*
  This class implements the functions of the Command Pattern.
  The Command Pattern uses a UndoManager to execute all commands one by one.
 */

class SetCommand(controller: Controller, pos: String) extends Command {
  override def doStep(): Unit = {
    controller.gamePlayState = Handler(controller.gamePlayState, controller.playerState).handleState(pos, controller)
    }

  override def undoStep(): Unit = controller.grid.createFullGrid()

  override def redoStep(): Unit = doStep()
}
