package mill.model

/*
  This object creates different states for the stones.
 */

object StoneState extends Enumeration {
  type StoneState = Value
  val notUsed, inGame, outOfGame = Value
}
