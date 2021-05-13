//package aView
//
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//import util.Observer
//
//class tuiSpec extends AnyWordSpec with Matchers {
//
//  "A Mill Tui" when {
//    "observed by an Observer" should {
//      val observer = new Observer {
//        var updated: Boolean = false
//
//        def isUpdated: Boolean = updated
//
//        override def update: Boolean = {
//          updated = true;
//          updated
//        }
//      }
//
//      "asks to enter name of player and color" in {
//        observer.updated should be(true)
//
//      }
//
//      "has created player1 with name and color" in {
//        observer.updated should be(true)
//      }
//
//      "has created player2 with name and color " in {
//        observer.updated should be(true)
//      }
//
//      "has welcomed players" in {
//        observer.updated should be(true)
//      }
//
//      "has created grid" in {
//        observer.updated should be(true)
//      }
//      "has printed grid" in {
//        observer.updated should be(true)
//      }
//    }
//  }
//}