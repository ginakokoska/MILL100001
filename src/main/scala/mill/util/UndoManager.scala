package mill.util

/*
  This class implements the Undo Manager that stores all commands in a stack.
 */

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil
  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep()
  }
  def undoStep(): Unit = {
    undoStack match {
      case Nil =>
      case head::stack =>
        head.undoStep()
        undoStack=stack
        redoStack=head::redoStack
    }
  }
  def redoStep(): Unit = {
    redoStack match {
      case Nil =>
      case head::stack =>
        head.redoStep()
        redoStack=stack
        undoStack=head::undoStack
    }
  }
}
