package mill.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class incrCommand extends Command {
  var state:Int =0
  override def doStep: Unit = state+=1

  override def undoStep: Unit = state-=1

  override def redoStep: Unit = state+=1
}


class UndoManagerSpec  extends AnyWordSpec with Matchers {

  "An UndoManager" should {
    val undoManager = new UndoManager

    "has a do, undo and redo" in {
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
      command.state should be(1)
      undoManager.undoStep
      command.state should be(0)
      undoManager.redoStep
      command.state should be(1)
    }

    "handles multiple undo steps correctly" in {
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
      command.state should be(1)
      undoManager.doStep(command)
      command.state should be(2)
      undoManager.undoStep
      command.state should be(1)
      undoManager.undoStep
      command.state should be(0)
      undoManager.redoStep
      command.state should be(1)
    }
  }
}