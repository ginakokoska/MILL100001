case class Stone(s: Stone) {

  object Stone extends Enumeration {
    val black, white = Value
  }
}

