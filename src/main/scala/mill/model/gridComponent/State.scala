package mill.model.gridComponent

import mill.controller.base.Controller
import mill.model.gridComponent.gridBase.Grid

trait State {
  def handle(pos: String, grid: Grid, controller: Controller): State

  def handle2(pos: String, grid: Grid, controller: Controller): State

  def jumpStone(pos: String, grid: Grid, controller: Controller): State

  def handleTakeStone(pos: String, grid: Grid): State
}
