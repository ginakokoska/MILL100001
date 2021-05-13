package model

import org.scalatest._
import org.scalatest.matchers.should._
import org.scalatest.wordspec._

class GridSpec extends AnyWordSpec with Matchers {
  "A Grid" when {
    "created with size 1" should {
      val grid = Grid().gridOutSquare()
      "outSquare must have no set Nodes and return false" in {
        for (i <- 0 to 2) {
          for (j <- 0 to 2) {
              grid(0)(i)(j) should be(Node(None))
          }
        }
      }
      "created with size 2" should {
        val grid = Grid().gridOutMidSquare()
        "out and midSquare must have no set Nodes and return false" in {
          for (i <- 0 to 2) {
            for (j <- 0 to 2) {
              grid(0)(i)(j).isSet should be(false)
              grid(1)(i)(j).isSet should be(false)
            }
          }
        }
      }
      "created with any other value" should {
        val grid = Grid().createFullGrid()
        "out, mid and inSquare must have no set Nodes and return false" in {
          for (i <- 0 to 2) {
            for (j <- 0 to 2) {
              grid(0)(i)(j).isSet should be(false)
              grid(1)(i)(j).isSet should be(false)
              grid(2)(i)(j).isSet should be(false)
            }
          }
        }
      }
      "White Players input OS: 00 " should {
        val grid = Grid()
        grid.gridList = grid.createFullGrid()
        grid.gridList = grid.moveGrid("OS:", '0', '0', Stone.white)
        "overriding Node OS: 00 " in {
          grid.gridList(0)(0)(0).isColor should be(Some(Stone.white))
        }
      }
      "White Players input MS: 01 " should {
        val grid = Grid()
        grid.gridList = grid.createFullGrid()
        grid.gridList = grid.moveGrid("MS:", '0', '1', Stone.white)
        "overriding Node MS: 01 " in {
          grid.gridList(1)(0)(1).isColor should be(Some(Stone.white))
        }
      }
      "White Players input IS: 10 " should {
        val grid = Grid()
        grid.gridList = grid.createFullGrid()
        grid.gridList = grid.moveGrid("IS:", '1', '0', Stone.white)
        "overriding Node IS: 10 " in {
          grid.gridList(2)(1)(0).isColor should be(Some(Stone.white))
        }
      }
      "Black Players input OS: 02 " should {
        val grid = Grid()
        grid.gridList = grid.createFullGrid()
        grid.gridList = grid.moveGrid("OS:", '0', '2', Stone.black)
        "overriding Node OS: 02 " in {
          grid.gridList(0)(0)(2).isColor should be(Some(Stone.black))
        }
      }
      "Black Players input MS: 20 " should {
        val grid = Grid()
        grid.gridList = grid.createFullGrid()
        grid.gridList = grid.moveGrid("MS:", '2', '0', Stone.black)
        "overriding Node MS: 20 " in {
          grid.gridList(1)(2)(0).isColor should be(Some(Stone.black))
        }
      }
      "Black Players input IS: 21 " should {
        val grid = Grid()
        grid.gridList = grid.createFullGrid()
        grid.gridList = grid.moveGrid("IS:", '2', '1', Stone.black)
        "overriding Node IS: 21 " in {
          grid.gridList(2)(2)(1).isColor should be(Some(Stone.black))
        }
      }
    }
  }
}
