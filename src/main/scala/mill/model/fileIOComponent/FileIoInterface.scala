package mill.model.fileIOComponent

import mill.controller.ControllerInterface
import mill.model.gridComponent.gridBase.Node

trait FileIoInterface {
  def load(controller: ControllerInterface): List[Array[Array[Node]]]
  def save(controller: ControllerInterface): Unit
}
