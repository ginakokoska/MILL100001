package mill.model.fileIOComponent

import mill.controller.ControllerInterface
import mill.model.gridComponent.gridBase.Node
/*
 This trait initializes the functions to load and store the game state as JSON-file or XML-file.
 */

trait FileIoInterface {
  def load(controller: ControllerInterface): List[Array[Array[Node]]]
  def save(controller: ControllerInterface): Unit
}
