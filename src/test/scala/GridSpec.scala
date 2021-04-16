//import org.scalatest._
//import wordspec._
//import matchers.should._
//
//class GridSpec extends AnyWordSpec with Matchers {
//  "A Grid is the playingfield of Sudoku. A Grid" when {
//    "to be constructed" should {
//      "be created with 3 squares. All of which have less possibilities for a mill." in {
        val threeSquares = new Node(24)
        val twoSquares = new Node(16)
        val oneSquare = new Node(8)

//
//
//      }
//      "for test purposes only created with a Matrix of Cells" in {
////        val threeSquare = Grid(new Matrix(2, Cell(0)))
//        val twoSquare = Grid(Matrix[Cell](Vector(Vector(Cell(0), Cell(0)), Vector(Cell(0), Cell(0)))))
//      }
//    }
    "created properly but empty" should {
            val threeSquares = new Node(24)
            val twoSquares = new Node(16)
            val oneSquare = new Node(8)
//      "give access to its Cells" in {
//        tinygrid.cell(0, 0) should be(Cell(0))
//        smallGrid.cell(0, 0) should be(Cell(0))
//        smallGrid.cell(0, 1) should be(Cell(0))
//        smallGrid.cell(1, 0) should be(Cell(0))
//        smallGrid.cell(1, 1) should be(Cell(0))
//      }
//      "allow to set individual Cells and remain immutable" in {
//        val changedGrid = smallGrid.set(0, 0, 1)
//        changedGrid.cell(0, 0) should be(Cell(1))
//        smallGrid.cell(0, 0) should be(Cell(0))
//      }
//    }
//    "prefilled with values 1 to n" should {
//      val tinyGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(1)))))
//      val smallGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(1), Cell(2)), Vector(Cell(3), Cell(4)))))
//      "have the right values in the right places" in {
//        smallGrid.cell(0, 0) should be(Cell(1))
//        smallGrid.cell(0, 1) should be(Cell(2))
//        smallGrid.cell(1, 0) should be(Cell(3))
//        smallGrid.cell(1, 1) should be(Cell(4))
//      }
//      "have Houses with the right Cells" in {
//        tinyGrid.row(0).cell(0) should be(Cell(1))
//        tinyGrid.col(0).cell(0) should be(Cell(1))
//        tinyGrid.block(0).cell(0) should be(Cell(1))
//
//        smallGrid.row(0).cell(0) should be(Cell(1))
//        smallGrid.row(0).cell(1) should be(Cell(2))
//        smallGrid.row(1).cell(0) should be(Cell(3))
//        smallGrid.row(1).cell(1) should be(Cell(4))
//        smallGrid.col(0).cell(0) should be(Cell(1))
//        smallGrid.col(0).cell(1) should be(Cell(3))
//        smallGrid.col(1).cell(0) should be(Cell(2))
//        smallGrid.col(1).cell(1) should be(Cell(4))
//      }
//    }
//    "prefilled with 1 to n on the diagonal" should {
//      val normalGrid = new Grid(9)
//      val diagonalGrid = normalGrid.set(0, 0, 1).set(1, 1, 2).set(2, 2, 3).set(3, 3, 4).set(4, 4, 5).set(5, 5, 6).set(6, 6, 7).set(7, 7, 8).set(8, 8, 9)
//      "have blocks with the right cells" in {
//        diagonalGrid.block(0).cell(0) should be(Cell(1))
//        diagonalGrid.block(0).cell(4) should be(Cell(2))
//        diagonalGrid.block(0).cell(8) should be(Cell(3))
//        diagonalGrid.block(4).cell(0) should be(Cell(4))
//        diagonalGrid.block(4).cell(4) should be(Cell(5))
//        diagonalGrid.block(4).cell(8) should be(Cell(6))
//        diagonalGrid.block(8).cell(0) should be(Cell(7))
//        diagonalGrid.block(8).cell(4) should be(Cell(8))
//        diagonalGrid.block(8).cell(8) should be(Cell(9))
//      }
//    }
//  }
//
//}
