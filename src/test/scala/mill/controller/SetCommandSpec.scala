package mill.controller

import mill.controller.base.{Controller, SetCommand}
import mill.model.gridComponent.gridBase.{Grid, Node}
import mill.model.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class SetCommandSpec() extends AnyWordSpec with Matchers {
  "SetCommand" when {
    "called" should {
      val controller = new Controller(Player("player1", Stone.white), Player("player2", Stone.black), grid = Grid())
      val setCommand = new SetCommand(controller, "OS: 00")
      controller.grid.createFullGrid()
      "doStep should execute this command" in {
        setCommand.doStep
        controller.grid.gridList.head(0)(0).isSet should be(true)
      }
      "undoStep  should clear current grid" in {
        setCommand.undoStep
        for (i <- 0 to 2) {
          for (j <- 0 to 2) {
            controller.grid.gridList.head(i)(j).isSet should be(false)
            controller.grid.gridList(1)(i)(j).isSet should be(false)
            controller.grid.gridList(2)(i)(j).isSet should be(false)
          }
        }
      }
      "redoStep should reload executed command" in {
        setCommand.redoStep
        controller.grid.gridList.head(0)(0).isSet should be(true)
      }
    }
  }
}
