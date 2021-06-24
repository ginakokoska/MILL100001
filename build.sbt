name := "mill100001"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.13" % "3.0.0"

libraryDependencies += "org.openjfx" % "javafx" % "12.0.2" pomOnly()

libraryDependencies += "com.google.inject" % "guice" % "4.2.3"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.13" % "2.0.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC2"

coverageExcludedPackages := "mill.aView.Gui.Gui;" +
  "mill.aView.Gui.StartGui;" +
  "mill.aView.Gui.ValidMove;" +
  "mill.aView.Gui.Tui;" +
  "mill.model.fileIOComponent.fileIOJson.FileIO;" +
  "mill.model.fileIOComponent.fileIOxml.FileIOXML;" +
  "mill.model.gridComponent.gridMock.Grid;"
//  "mill.model.gridComponent.gridBase.;" +
//  "mill.model.gridComponent.GridMock.;" +
//  "mill.model.gridComponent.GridInterface;" +
//  "mill.model.gridComponent.GridSizeInterface;" +
//  "mill.model.gridComponent.State;" +

//  "mill.model.PlayerState;" +
//  "mill.util.Command;"