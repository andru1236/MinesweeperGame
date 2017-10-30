package controller;

import arquitectura.logicGame.LogicGame;
import arquitectura.Setup;

public abstract class Controller {

  protected static final int UNDER_MINE = 1;
  protected static final int FLAG = 3;
  protected LogicGame game;
  public Setup setup;

  public abstract void restart();

  public void play(int move, int x, int y) {
    switch (move) {
      case UNDER_MINE:
        game.discoverCell(x, y);
        break;
      case FLAG:
        game.putFlag(x, y);
        break;

      case 2:
        game.move2(x, y);
        break;
    }
  }
}
