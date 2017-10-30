package arquitectura.logicGame;

import arquitectura.Setup;
import java.util.ArrayList;
import model.table.TypeBomb;

public class MinesweeperGameA extends MinesweeperGame {

  public static final double PORCENTAGE_LIFE_PER_BOMB = 0.4;
  public static final double PORCENTAGE_BOMB_A = 0.5;
  public static final int GAME_OVER = 0;

  private int numBombA;

  public MinesweeperGameA(Setup setup) {
    super(setup);
    this.nameGame = "MinesweeperGame A";
    this.modality = "Lifes ";
    setLifes();

  }

  @Override
  protected void addrandomBombtoBombs() {
    this.bombs = new ArrayList<>();
    setLifes();
    insertBombsA();
    insertBombs();
  }

  @Override
  public void discoverCell(int x, int y) {
    if (!table.isFlag(x, y) && !table.isExposed(x, y) && !table.isBlock()) {
      TypeBomb cell = table.discoverCell(x, y);
      switch (cell) {
        case BOMB_A:
          table.youLose();
          valueModality = GAME_OVER;
          table.notifyScore(valueModality);
          break;
        case BOMB:
          valueModality = valueModality - 1;
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
              case BOMB_A:
                table.youLose();
                valueModality = GAME_OVER;
                table.notifyScore(valueModality);
                break;
              case BOMB:
                valueModality = valueModality - 1;
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

  private void setLifes() {
    this.valueModality = (int) (setup.getNumBombs() * PORCENTAGE_LIFE_PER_BOMB);
  }

  private void insertBombs() {
    int numBomb = setup.getNumBombs() - numBombA;
    bombs = BombsRandom.getRandomBombs(numBomb, TypeBomb.BOMB, bombs,
            setup.getRow(), setup.getCol());
  }

  private void insertBombsA() {
    numBombA = (int) (setup.getNumBombs() * PORCENTAGE_BOMB_A);
    bombs = BombsRandom.getRandomBombs(numBombA, TypeBomb.BOMB_A, bombs,
            setup.getRow(), setup.getCol());
  }

}
