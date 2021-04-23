case class Node(stone: Option[Stone.Value]) {
  def isSet: Boolean =  stone match {
    case Some (s: Stone.Stone) => true
    case None => false
  } // check if stone is set , s stands for black or white stone // stone must be created as enum in scala 2 4 lines of code

  def isColor: Option[Stone.Value] =
    if(isSet) stone
    else None
}
