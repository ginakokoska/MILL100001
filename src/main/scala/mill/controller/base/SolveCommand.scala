//package mill.controller.base
//
//import mill.model.gridComponent.gridBase.Grid
//import mill.util.Command
//
//class SolveCommand(controller:Controller) extends Command {
//  //memento Pattern
//  var memento: Grid = controller.grid
//
//  override def doStep: Unit = {
//    memento = controller.grid
//  }
//
//  //unterschied zwischen undo und redo?
//  override def undoStep: Unit = {
//    val new_memento = controller.grid
//    controller.grid = memento
//    memento = new_memento
//  }
//
//  override def redoStep: Unit = {
//    val new_memento = controller.grid
//    controller.grid = memento
//    memento = new_memento
//  }
//}
