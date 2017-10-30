package arquitectura.allgames;

import arquitectura.Setup;
import arquitectura.logicGame.MinesweeperGame;
import controller.MinesweeperViewConsole;
import controller.MinesweeperViewUi;

public class Game extends AbstractGame {

  public Game() {
    setup = new Setup();
    game = new MinesweeperGame(setup);
    uiGame = new MinesweeperViewUi(game, setup);
    console = new MinesweeperViewConsole(game, setup);
  }

}
