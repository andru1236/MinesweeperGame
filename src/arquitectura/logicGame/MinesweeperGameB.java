package arquitectura.logicGame;

import arquitectura.Setup;
import java.util.ArrayList;
import model.table.TypeBomb;

public class MinesweeperGameB extends MinesweeperGame {

  public static final int VALUE_PER_SCORE = 10;
  public static final int REDUCTION_SCORE_VALUE = 20;
  public static final double PORCENTAGE_BOMB_B = 0.5;
  public static final int GAME_OVER = 0;

  private int numBombB;

  public MinesweeperGameB(Setup setup) {
    super(setup);
    this.nameGame = "MinesweeperGame B";
    this.modality = "Score";
    setScore();
  }

  @Override
  protected void addrandomBombtoBombs() {
    this.bombs = new ArrayList<>();
    setScore();
    insertBombsB();
    insertBombs();
  }

  @Override
  public void discoverCell(int x, int y) {
    if (!table.isFlag(x, y) && !table.isExposed(x, y) && !table.isBlock()) {
      TypeBomb cell = table.discoverCell(x, y);
      switch (cell) {
        case BOMB_B:
          table.youLose();
          valueModality = GAME_OVER;
          table.notifyScore(valueModality);
          break;
        case BOMB:
          valueModality = valueModality - REDUCTION_SCORE_VALUE;
          if (valueModality <= GAME_OVER) {
            table.youLose();
          }
          table.notifyScore(valueModality);
          break;
        case NONE:
          expose(x, y);
      }
      table.checkWin();
    }
  }

  @Override
  public void move2(int x, int y) {
    if (table.isFlag(x, y)) {
      for (int i = x - 1; i <= x + 1; i++) {
        for (int j = y - 1; j <= y + 1; j++) {
          if (table.isValidPosition(i, j) && !table.isFlag(i, j)) {
            TypeBomb cell = table.discoverCell(i, j);
            switch (cell) {
              case BOMB_B:
                table.youLose();
                valueModality = GAME_OVER;
                table.notifyScore(valueModality);
                break;
              case BOMB:
                valueModality = valueModality - REDUCTION_SCORE_VALUE;
                if (valueModality <= GAME_OVER) {
                  table.youLose();
                }
                table.notifyScore(valueModality);
                break;
              case NONE:
                table.putCellValue(i, j, surroundingBombs(i, j));
                break;
            }
          }
        }
      }
      table.checkWin();
    }
  }

  private void setScore() {
    this.valueModality = setup.getNumBombs() * VALUE_PER_SCORE;
  }

  private void insertBombs() {
    int numBomb = setup.getNumBombs() - numBombB;
    bombs = BombsRandom.getRandomBombs(numBomb, TypeBomb.BOMB, bombs,
            setup.getRow(), setup.getCol());
  }

  private void insertBombsB() {
    numBombB = (int) (setup.getNumBombs() * PORCENTAGE_BOMB_B);
    bombs = BombsRandom.getRandomBombs(numBombB, TypeBomb.BOMB_B, bombs,
            setup.getRow(), setup.getCol());
  }

}
