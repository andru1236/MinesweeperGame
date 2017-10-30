package controller;

import arquitectura.logicGame.LogicGame;
import arquitectura.Setup;
import views.Console;
import java.util.Scanner;

public class MinesweeperViewConsole extends Controller {

  private Console view;

  private static final int CHANGE_DIFFICULTY = 1;
  private boolean stateGame;

  private static final String EASY = "E";
  private static final String MEDIUM = "M";
  private static final String HARD = "H";
  private static final String QUIT = "Q";

  private static final String PUT_FLAG = "F";
  private static final String DISCOVER_CELL = "P";

  public MinesweeperViewConsole(LogicGame game, Setup setup) {
    this.stateGame = true;
    this.game = game;
    this.setup = setup;
    this.view = new Console(this.setup);
    game.addObservertoTable(view);
    init();
  }

  private void init() {
    view.showCommands();
    view.show();
    while (stateGame) {
      Scanner input = new Scanner(System.in);
      String move = input.nextLine().toUpperCase();
      if (CHANGE_DIFFICULTY == move.length()) {
        changeDifficulty(move);
      } else {
        catchMove(move);
      }
    }
  }

  private void changeDifficulty(String jugada) {
    switch (jugada) {
      case EASY:
        this.setup.easy();
        break;
      case MEDIUM:
        this.setup.medium();
        break;
      case HARD:
        this.setup.hard();
        break;
      case QUIT:
        stateGame = false;
        break;
    }
    restart();
  }

  private void catchMove(String move) {
    String[] param = move.split(" ");
    switch (param[0]) {
      case PUT_FLAG:
        play(FLAG, Integer.parseInt(param[1]), Integer.parseInt(param[2]));
        break;
      case DISCOVER_CELL:
        play(UNDER_MINE, Integer.parseInt(param[1]), Integer.parseInt(param[2]));
        break;
    }
  }

  @Override
  public void restart() {
    game.restartGame();
  }

}
