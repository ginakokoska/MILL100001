package mill

import com.google.inject.AbstractModule
import model.gridComponent.gridBase.{FullGrid, GamePlay, GridMidSquare, GridOutSquare}
import model.gridComponent.{GridSizeInterface, State}
import net.codingwell.scalaguice.ScalaModule

class MillModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[State].to[GamePlay]

//    bind[ControllerInterface].to[controller.base.Controller]

//    bind[GridInterface].annotatedWithName("MockImpl").toInstance(new model.gridComponent.gridMock.Grid)
//    bind[GridInterface].annotatedWithName("BaseImpl").toInstance(new model.gridComponent.gridBase.Grid)

    bind[GridSizeInterface].annotatedWithName("Out").toInstance(new GridOutSquare)
    bind[GridSizeInterface].annotatedWithName("Mid").toInstance(new GridMidSquare)
    bind[GridSizeInterface].annotatedWithName("Full").toInstance(new FullGrid)




    //    bind[GridInterface].to[model.gridComponent.gridBase.Grid]
  }
}
