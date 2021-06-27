package mill.model.gridComponent.gridBase

import mill.model.Stone

/*
  This class defines valid mills.
  A corner mill is formed when moved to corner points f.e. (OS: 00 - OS: 01 - OS: 02)
  A mid mill is formed when moved to mid nodes only f.e (OS: 01 - MS: 01 - IS: 02)
 */

case class ValidMill() {
  def proofTypeCorner(sq: Int, pos1: Int, pos2: Int, color: Stone.Value, grid: Grid): Boolean = {
    var count = 0
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
  }

  def proofTypeMid(sq: Int, pos1: Int, pos2: Int, color: Stone.Value, grid: Grid): Boolean = {
    var count = 0
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
