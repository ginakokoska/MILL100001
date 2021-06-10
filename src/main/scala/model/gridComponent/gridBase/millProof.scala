package model.gridComponent.gridBase

import model.playerComponent.Stone

case class millProof() {
  def proofTypeCorner(sq: Int, pos1: Int, pos2: Int, color: Stone.Value, grid: Grid): Boolean = {
    var count = 0
    //    var rVar = false
    val tmp = pos1.toString + pos2.toString
    tmp match {
      case "00" =>
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(0)(i).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(i)(0).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case "02" =>
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(0)(i).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(i)(2).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case "22" =>
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(i)(2).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(2)(i).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case "20" =>
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(2)(i).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(i)(0).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case _ => count = 0
    }
    if (count == 3) true
    else false
    //    var tmp = 0
    //    if(pos1 != 1 && pos2 != 1) {
    //      if (pos1 == pos2) {
    //        for (i <- 0 to 2) {
    //          if (grid.gridList(sq)(pos1)(i).isColor.contains(color) ||
    //            grid.gridList(sq)(i)(pos1).isColor.contains(color)) tmp = tmp + 1
    //          else {
    //            tmp = 0
    //          }
    //        }
    //      } else {
    //        for (i <- 0 to 2) {
    //          if (grid.gridList(sq)(pos1)(i).isColor.contains(color) ||
    //            (grid.gridList(sq)(i)(pos2).isColor.contains(color) &&
    //            grid.gridList(sq)(pos1)(i).isColor.contains(color))) tmp = tmp + 1
    //          else {
    //            tmp = 0
    //          }
    //        }
    //      }
    //    } else tmp = 0
    //
    //    if(tmp == 3) true
    //    else false
    //    for(i<-0 to 2) {
    //      if (grid.gridList(i)(0)(0).isColor.contains(color) && grid.gridList(i)(0)(1).isColor.contains(color) &&
    //        grid.gridList(i)(0)(2).isColor.contains(color)) return true
    //      else if (grid.gridList(i)(0)(0).isColor.contains(color) && grid.gridList(i)(1)(0).isColor.contains(color) &&
    //        grid.gridList(i)(2)(0).isColor.contains(color)) return true
    //      else if (grid.gridList(i)(0)(0).isColor.contains(color) && grid.gridList(i)(0)(1).isColor.contains(color) &&
    //        grid.gridList(i)(0)(2).isColor.contains(color)) return true
    //      else if (grid.gridList(i)(0)(2).isColor.contains(color) && grid.gridList(i)(1)(2).isColor.contains(color) &&
    //        grid.gridList(i)(2)(2).isColor.contains(color)) return true
    //      else if (grid.gridList(i)(2)(0).isColor.contains(color) && grid.gridList(i)(2)(1).isColor.contains(color) &&
    //        grid.gridList(i)(2)(2).isColor.contains(color)) return true
    //    }
    //
    //    if(grid.gridList(0)(0)(1).isColor.contains(color) && grid.gridList(1)(0)(1).isColor.contains(color) &&
    //      grid.gridList(2)(0)(1).isColor.contains(color)) true
    //    else if (grid.gridList(0)(1)(2).isColor.contains(color) && grid.gridList(1)(1)(2).isColor.contains(color) &&
    //      grid.gridList(2)(1)(2).isColor.contains(color)) true
    //    else if (grid.gridList(0)(2)(1).isColor.contains(color) && grid.gridList(1)(2)(1).isColor.contains(color) &&
    //      grid.gridList(2)(2)(1).isColor.contains(color)) true
    //    else if (grid.gridList(0)(1)(0).isColor.contains(color) && grid.gridList(1)(1)(0).isColor.contains(color) &&
    //      grid.gridList(2)(1)(0).isColor.contains(color)) true
    //    else false
  }

  def proofTypeMid(sq: Int, pos1: Int, pos2: Int, color: Stone.Value, grid: Grid): Boolean = {
    var count = 0
    //    var rVar = false
    val tmp = pos1.toString + pos2.toString
    tmp match {
      case "01" =>
        for (i <- 0 to 2) {
          if (grid.gridList(i)(pos1)(pos2).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(0)(i).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case "10" =>
        for (i <- 0 to 2) {
          if (grid.gridList(i)(pos1)(pos2).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(i)(0).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case "12" =>
        for (i <- 0 to 2) {
          if (grid.gridList(i)(pos1)(pos2).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(i)(2).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case "21" =>
        for (i <- 0 to 2) {
          if (grid.gridList(i)(pos1)(pos2).isColor.contains(color)) count = count + 1
          else count = 0
        }
        if (count == 3) return true
        else count = 0
        for (i <- 0 to 2) {
          if (grid.gridList(sq)(2)(i).isColor.contains(color)) count = count + 1
          else count = 0
        }
      case _ => count = 0
    }
    if (count == 3) true
    else false
  }


}
