package mill.aView.Gui

import scala.swing.Point

case class ValidMove() {

  def posToPoint(sq: Int, row: Int, col: Int): Point = {
    val p: Point = new Point(0,0)
    row match {
      case 0 =>
        sq match {
          case 0 => p.y = 4
          case 1 => p.y = 106
          case 2 => p.y = 211
        }
      case 1 => p.y = 319
      case 2 =>
        sq match {
          case 0 => p.y = 640
          case 1 => p.y = 531
          case 2 => p.y = 424
        }
    }

    col match {
      case 0 =>
        sq match {
          case 0 => p.x = 4
          case 1 => p.x = 108
          case 2 => p.x = 214
        }
      case 1 => p.x = 320
      case 2 =>
        sq match {
          case 0 => p.x = 638
          case 1 => p.x = 532
          case 2 => p.x = 426
        }
    }
    p
  }

  def hitbox(p: Point): Map[String,Point] = {
    val os00 = new Point(30,30)
    val os01 = new Point(350,30)
    val os02 = new Point(665,30)
    val ms00 = new Point(140,140)
    val ms01 = new Point(350,140)
    val ms02 = new Point(565,140)
    val is00 = new Point(250,250)
    val is01 = new Point(350,250)
    val is02 = new Point(455,250)
    val os10 = new Point(30,350)
    val ms10 = new Point(140,350)
    val is10 = new Point(250,350)
    val is12 = new Point(455,350)
    val ms12 = new Point(565,350)
    val os12 = new Point(665,350)
    val is20 = new Point(250,450)
    val is21 = new Point(350,450)
    val is22 = new Point(455,450)
    val ms20 = new Point(140,565)
    val ms21 = new Point(350,565)
    val ms22 = new Point(565,565)
    val os20 = new Point(30,670)
    val os21 = new Point(350,670)
    val os22 = new Point(670,670)
    if((-15 < (p.x - os00.x) && (p.x - os00.x) <= 15 && -15 < (p.y - os00.y) &&  (p.y - os00.y) <= 15)) Map("OS: 00"-> new Point(4,4))
    else if((-15 < (p.x - os01.x) && (p.x - os01.x) <= 15 && -15 < (p.y - os01.y) &&  (p.y - os01.y) <= 15)) Map("OS: 01"->new Point(320,4))
    else if((-15 < (p.x - os02.x) && (p.x - os02.x) <= 15 && -15 < (p.y - os02.y) &&  (p.y - os02.y) <= 15)) Map("OS: 02"->new Point(638,4))
    else if((-15 < (p.x - ms00.x) && (p.x - ms00.x) <= 15 && -15 < (p.y - ms00.y) &&  (p.y - ms00.y) <= 15)) Map("MS: 00"->new Point(108,106))
    else if((-15 < (p.x - ms01.x) && (p.x - ms01.x) <= 15 && -15 < (p.y - ms01.y) &&  (p.y - ms01.y) <= 15)) Map("MS: 01"->new Point(320,106))
    else if((-15 < (p.x - ms02.x) && (p.x - ms02.x) <= 15 && -15 < (p.y - ms02.y) &&  (p.y - ms02.y) <= 15)) Map("MS: 02"->new Point(532,106))
    else if((-15 < (p.x - is00.x) && (p.x - is00.x) <= 15 && -15 < (p.y - is00.y) &&  (p.y - is00.y) <= 15)) Map("IS: 00"->new Point(214,211))
    else if((-15 < (p.x - is01.x) && (p.x - is01.x) <= 15 && -15 < (p.y - is01.y) &&  (p.y - is01.y) <= 15)) Map("IS: 01"->new Point(320,211))
    else if((-15 < (p.x - is02.x) && (p.x - is02.x) <= 15 && -15 < (p.y - is02.y) &&  (p.y - is02.y) <= 15)) Map("IS: 02"->new Point(426,211))
    else if((-15 < (p.x - os10.x) && (p.x - os10.x) <= 15 && -15 < (p.y - os10.y) &&  (p.y - os10.y) <= 15)) Map("OS: 10"->new Point(4,319))
    else if((-15 < (p.x - ms10.x) && (p.x - ms10.x) <= 15 && -15 < (p.y - ms10.y) &&  (p.y - ms10.y) <= 15)) Map("MS: 10"->new Point(108,319))
    else if((-15 < (p.x - is10.x) && (p.x - is10.x) <= 15 && -15 < (p.y - is10.y) &&  (p.y - is10.y) <= 15)) Map("IS: 10"->new Point(214,319))
    else if((-15 < (p.x - is12.x) && (p.x - is12.x) <= 15 && -15 < (p.y - is12.y) &&  (p.y - is12.y) <= 15)) Map("IS: 12"->new Point(426,319))
    else if((-15 < (p.x - ms12.x) && (p.x - ms12.x) <= 15 && -15 < (p.y - ms12.y) &&  (p.y - ms12.y) <= 15)) Map("MS: 12"->new Point(532,319))
    else if((-15 < (p.x - os12.x) && (p.x - os12.x) <= 15 && -15 < (p.y - os12.y) &&  (p.y - os12.y) <= 15)) Map("OS: 12"->new Point(638,319))
    else if((-15 < (p.x - is20.x) && (p.x - is20.x) <= 15 && -15 < (p.y - is20.y) &&  (p.y - is20.y) <= 15)) Map("IS: 20"->new Point(214,424))
    else if((-15 < (p.x - is21.x) && (p.x - is21.x) <= 15 && -15 < (p.y - is21.y) &&  (p.y - is21.y) <= 15)) Map("IS: 21"->new Point(320,424))
    else if((-15 < (p.x - is22.x) && (p.x - is22.x) <= 15 && -15 < (p.y - is22.y) &&  (p.y - is22.y) <= 15)) Map("IS: 22"->new Point(426,424))
    else if((-15 < (p.x - ms20.x) && (p.x - ms20.x) <= 15 && -15 < (p.y - ms20.y) &&  (p.y - ms20.y) <= 15)) Map("MS: 20"->new Point(108,531))
    else if((-15 < (p.x - ms21.x) && (p.x - ms21.x) <= 15 && -15 < (p.y - ms21.y) &&  (p.y - ms21.y) <= 15)) Map("MS: 21"->new Point(320,531))
    else if((-15 < (p.x - ms22.x) && (p.x - ms22.x) <= 15 && -15 < (p.y - ms22.y) &&  (p.y - ms22.y) <= 15)) Map("MS: 22"->new Point(532,531))
    else if((-15 < (p.x - os20.x) && (p.x - os20.x) <= 15 && -15 < (p.y - os20.y) &&  (p.y - os20.y) <= 15)) Map("OS: 20"->new Point(4,640))
    else if((-15 < (p.x - os21.x) && (p.x - os21.x) <= 15 && -15 < (p.y - os21.y) &&  (p.y - os21.y) <= 15)) Map("OS: 21"->new Point(320,640))
    else if((-15 < (p.x - os22.x) && (p.x - os22.x) <= 15 && -15 < (p.y - os22.y) &&  (p.y - os22.y) <= 15)) Map("OS: 22"->new Point(638,640))
    else Map("" -> new Point(0,0))
  }
}
