package mill.model.gridComponent

import mill.controller.controllerBase.Controller
import mill.model.gridComponent.gridBase.Grid

/*
  This trait is initialises the state pattern for the different stone states.
 */

trait State {
  def setStoneState(pos: String, grid: Grid, controller: Controller): State

  def moveStoneState(pos: String, grid: Grid, controller: Controller): State

  def jumpStoneState(pos: String, grid: Grid, controller: Controller): State

  def takeStoneState(pos: String, grid: Grid): State
}
