package mill

import com.google.inject.AbstractModule
import model.gridComponent.gridBase.GamePlay
import model.gridComponent.{GridSizeInterface, State}
import net.codingwell.scalaguice.ScalaModule

class MillModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[State].to[GamePlay]
  }
}
