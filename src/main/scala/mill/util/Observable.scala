package mill.util

/*
  This trait was used to update the TUI when notify observer was called.
  (Observer Pattern)
 */

trait Observer {
  def update: Boolean
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(): Unit = subscribers.foreach(o => o.update)
}