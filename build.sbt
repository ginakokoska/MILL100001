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

coverageExcludedPackages := "mill.aView.Gui.*;" +
  "mill.controller.controllerStub.*;" +
  "mill.model.gridComponent.gridStub.*;" +
  "mill.model.gridComponent.GridInterface;" +
  "mill.model.gridComponent.State;" +
  "mill.model.fileIOComponent.*;" +
  "mill.model.PlayerState;" +
  "mill.util.Command;" +
  "mill.mill;"