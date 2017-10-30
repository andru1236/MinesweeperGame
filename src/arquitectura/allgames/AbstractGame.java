package arquitectura.allgames;

import arquitectura.Setup;
import arquitectura.logicGame.LogicGame;
import controller.MinesweeperViewConsole;
import controller.MinesweeperViewUi;

public abstract class AbstractGame {

  protected Setup setup;
  protected LogicGame game;
  protected MinesweeperViewUi uiGame;
  protected MinesweeperViewConsole console;
}
