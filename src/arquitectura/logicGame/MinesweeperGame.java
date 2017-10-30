package arquitectura.logicGame;

import arquitectura.Setup;
import model.Table;
import model.TableMinesweeper;
import views.View;
import java.util.ArrayList;
import model.table.TypeBomb;

public class MinesweeperGame extends LogicGame {

  public MinesweeperGame(Setup setup) {
    this.setup = setup;
    this.nameGame = "Minesweeper Game";
    this.modality = "Traditional";
    this.valueModality = 2017;
    addrandomBombtoBombs();
    table = new TableMinesweeper(setup, bombs);
  }

  @Override
  public void addObservertoTable(View view) {
    table.addObserver(view);
  }

  public MinesweeperGame(Table table) {
    this.table = table;
  }

  @Override
  public void restartGame() {
    addrandomBombtoBombs();
    table.restartTable(bombs);
  }

  @Override
  protected void addrandomBombtoBombs() {
    this.bombs = new ArrayList<>();
    this.bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB,
            bombs, setup.getRow(), setup.getCol());
  }

  @Override
  public void putFlag(int x, int y) {
    if (!table.isExposed(x, y) && !table.isBlock()) {
      if (!table.isFlag(x, y)) {
        table.putFlag(x, y);
      } else {
        table.removeFlag(x, y);
      }
      table.checkWin();
    }
  }

  @Override
  public void discoverCell(int x, int y) {
    if (!table.isFlag(x, y) && !table.isExposed(x, y) && !table.isBlock()) {
      if (table.discoverCell(x, y) == TypeBomb.BOMB) {
        table.youLose();
      } else {
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
            if (table.discoverCell(i, j) == TypeBomb.BOMB) {
              table.youLose();
            } else {
              table.putCellValue(i, j, surroundingBombs(i, j));
            }
          }
        }
      }
      table.checkWin();
    }
  }

  protected void expose(int x, int y) {
    int value = surroundingBombs(x, y);
    if (value == 0) {
      table.putCellValue(x, y, value);
      for (int i = x - 1; i <= x + 1; i++) {
        for (int j = y - 1; j <= y + 1; j++) {
          if (table.isValidPosition(i, j) && !table.isFlag(i, j)
                  && !table.isExposed(i, j)) {
            expose(i, j);
          }
        }
      }
    } else {
      table.putCellValue(x, y, value);
    }
  }

  protected int surroundingBombs(int x, int y) {
    int valor = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (table.isValidPosition(i, j) && table.isBomb(i, j)) {
          valor++;
        }
      }
    }
    return valor;
  }
}
