package mill

import com.google.inject.AbstractModule
import model.gridComponent.gridBase.GamePlay
import model.gridComponent.State
import net.codingwell.scalaguice.ScalaModule
import model.fileIOComponent._
import controller.ControllerInterface
import controller.controllerBase
import model.gridComponent._

/*
  This class binds traits to implementations.
 */

class MillModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[State].to[GamePlay]
    bind[FileIoInterface].to[fileIOJson.FileIoJSON]
//    bind[ControllerInterface].to[controllerBase.Controller]
//    bind[GridInterface].to[gridBase.Grid]
  }
}
