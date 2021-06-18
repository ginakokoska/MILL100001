import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.ControllerInterface
import model.Player
import model.gridComponent.gridBase.{BlackTurn, GamePlay, Handler, TakeStone, WhiteTurn}
import model.gridComponent.{GridInterface, State}
import model.gridComponent.gridMock.Grid
import net.codingwell.scalaguice.ScalaModule

import java.lang.ModuleLayer.Controller

class MillModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[State].to[GamePlay]
  }
}
