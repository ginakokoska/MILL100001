package mill.model

import mill.controller.ControllerInterface
import mill.model.gridComponent.GridInterface
import mill.model.gridComponent.gridBase.{Grid, Node}

trait FileIoInterface {
  def load(game: Grid): (GridInterface, Any)
  def save(controller: ControllerInterface): Unit
}
