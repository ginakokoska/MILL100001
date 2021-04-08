case class Cell(value:Int) {
  def isSet:Boolean = value != 0
}

val Cell1 = Cell(2)
Cell1.isSet

val Cell2 = Cell(2)
Cell2.isSet

println("test")

1+2
3.toString
List(1,2,6,4,3,2).sorted

case class Person(name:String, age:Int)

val p1 = Person("Luca",23)
p1.age