package mill.util

/*
  This Trait initializes the methods for the Command Pattern.
 */

trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}
