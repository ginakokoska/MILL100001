import scala.swing._
import scala.swing.event.EditDone
import java.awt.geom.Point2D
import scala.collection.immutable.ListMap
import scala.collection.mutable
import scala.swing.Point

case class test() {
  var l: ListMap[Int,Int] = ListMap()
  var testArray: Array[Point] = Array()
  var testList: List[Int] = Nil

  def setC(p: Int): Unit = {
    testList = p::testList
//    l.updated(p.x,p.y)
  }

  def getList(): List[Int] = {
    testList
  }
}
val i1 = 1
val i2 = 2
val i3 = 3
val p1 = new Point(100,1)
val p2 = new Point(200,1)
val p3 = new Point(300,1)
test().setC(i1)
test().setC(i2)
test().setC(i2)
test().getList()
