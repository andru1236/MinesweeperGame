package arquitectura.allgames;

import arquitectura.Setup;
import arquitectura.logicGame.MinesweeperGameA;
import controller.ControllerConsole;
import controller.ControllerUi;

public class GameA extends AbstractGame {

  public GameA() {
    setup = new Setup();
    game = new MinesweeperGameA(setup);
    uiGame = new ControllerUi(game, setup);
    console = new ControllerConsole(game, setup);
  }
}
