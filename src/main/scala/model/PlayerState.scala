package model

import model.StoneState.StoneState

trait PlayerState {
  def getState(player: Player): PlayerState
}
