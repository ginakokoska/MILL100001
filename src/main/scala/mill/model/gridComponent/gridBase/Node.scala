package mill.model.gridComponent.gridBase

import mill.model.Stone

case class Node(stone: Option[Stone.Value]) {
  def isSet: Boolean = stone match {
    case Some(s: Stone.Stone) => true
    case None => false
  }

  def isColor: Option[Stone.Value] =
    if (isSet) stone
    else None

}
