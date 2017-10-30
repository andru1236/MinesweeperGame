package arquitectura.allgames;

import arquitectura.Setup;
import arquitectura.logicGame.MinesweeperGameB;
import controller.MinesweeperViewConsole;
import controller.MinesweeperViewUi;

public class GameB extends AbstractGame {

  public GameB() {
    setup = new Setup();
    game = new MinesweeperGameB(setup);
    uiGame = new MinesweeperViewUi(game, setup);
    console = new MinesweeperViewConsole(game, setup);
  }
}
