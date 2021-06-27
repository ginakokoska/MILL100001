package mill.model.gridComponent

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase.Grid

trait State {
  def setStoneState(pos: String, grid: Grid, controller: Controller): State

  def moveStoneState(pos: String, grid: Grid, controller: Controller): State

  def jumpStoneState(pos: String, grid: Grid, controller: Controller): State

  def takeStoneState(pos: String, grid: Grid): State
}
