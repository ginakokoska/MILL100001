package model.playerComponent

object StoneState extends Enumeration {
  type StoneState = Value
  val notUsed, inGame, outOfGame = Value
}
