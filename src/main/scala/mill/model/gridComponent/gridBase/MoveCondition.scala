package mill.model.gridComponent.gridBase

case class MoveCondition() {

  def moveConditionCorner(pos1: Int, pos2: Int, pos1New: Int, pos2New: Int): Boolean = {
    var rVar = false
    var tmp = 0

    if (pos1 != 1 && pos2 != 1) {
      if (pos1 < pos1New) tmp = pos1New - pos1
      else if (pos1 > pos1New) tmp = pos1 - pos1New
      else {
        if (pos2 < pos2New) tmp = pos2New - pos2
        else if (pos2 > pos2New) tmp = pos2 - pos2New
      }
    }

    if (tmp == 1) rVar = true
    else rVar = false

    rVar
  }

  def moveConditionMid(sqStr: String, pos1: Int, pos2: Int, sqNew: Int, pos1New: Int, pos2New: Int): Boolean = {
    var sq = 0
    var tmp = 0
    sqStr match {
      case "OS:" => sq = 0
      case "MS:" => sq = 1
      case "IS:" => sq = 2
    }

    if (sq != sqNew) {
      if (pos1 == pos1New && pos2 == pos2New) {
        if (sq < sqNew) tmp = sqNew - sq
        else if (sqNew < sq) tmp = sq - sqNew
      }
      else tmp = 0
    } else {
      if (pos1 == pos1New) {
        if (pos2 < pos2New) tmp = pos2New - pos2
        else if (pos2 > pos2New) tmp = pos2 - pos2New
      } else if (pos2 == pos2New) {
        if (pos1 < pos1New) tmp = pos1New - pos1
        else if (pos1 > pos1New) tmp = pos1 - pos1New
      }
    }

    if (tmp == 1) true
    else false
  }
}
