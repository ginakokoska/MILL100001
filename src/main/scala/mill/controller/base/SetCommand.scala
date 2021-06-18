package mill.controller.base

import mill.model.gridComponent.gridBase.Handler
import mill.util.Command
import mill.util._

class SetCommand(controller: Controller, pos: String) extends Command {
  override def doStep: Unit = {
    controller.gamePlayState = Handler(controller.gamePlayState, controller.playerState).handleState(pos, controller)
    }

  override def undoStep: Unit = controller.grid.createFullGrid()

  override def redoStep: Unit = doStep

}
