package arquitectura.allgames;

import arquitectura.Setup;
import arquitectura.logicGame.MinesweeperGameA;
import controller.MinesweeperViewConsole;
import controller.MinesweeperViewUi;

public class GameA extends AbstractGame {

  public GameA() {
    setup = new Setup();
    game = new MinesweeperGameA(setup);
    uiGame = new MinesweeperViewUi(game, setup);
    console = new MinesweeperViewConsole(game, setup);
  }
}
