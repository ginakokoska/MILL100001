package mill

import com.google.inject.AbstractModule
import model.gridComponent.gridBase.GamePlay
import model.gridComponent.{GridSizeInterface, State}
import net.codingwell.scalaguice.ScalaModule
import model.fileIOComponent._

class MillModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[State].to[GamePlay]
    bind[FileIoInterface].to[fileIOJson.FileIO]
  }
}
