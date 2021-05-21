import scala.util.{Try, Success, Failure}

val b = "OS: 12"
//var d :Array[String] = new Array[String](2)
//Try(b.length == 6) match {
//  case Success(v) => d = b.split(" ")
//  case Failure(_) => println("fff")
//}
//d
val d :Array[String] = b.split(" ")

var intPos1 = 0
var intPos2 = 0
Try(d(1).charAt(0).asDigit) match {
  case Success(v) => intPos1 = v
  case Failure(_) => println("ddddd")
}
Try(d(1).charAt(1).asDigit) match {
  case Success(v) => intPos2 = v
  case Failure(_) => println("fail2")
}



//Try(d(1).charAt(0).asDigit) match {
//  case Success(v) =>
//    intPos1 = v
//    Try(d(1).charAt(1).asDigit) match {
//      case Success(v) =>
//        intPos2 = v
//      case Failure(_) => println("fail1")
//    }
//  case Failure(_) => println("fail")
//}
intPos1
intPos2