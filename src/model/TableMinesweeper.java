package model;

import model.table.Bomb;
import model.table.Cell;
import model.table.TypeBomb;
import arquitectura.Setup;
import java.util.ArrayList;
import views.View;

public class TableMinesweeper extends Table {

  public TableMinesweeper(Setup setup, ArrayList<Bomb> bombs) {
    this.setup = setup;
    this.col = setup.getCol();
    this.row = setup.getRow();
    this.bombs = bombs;
    this.lose = false;
    this.win = false;
    this.block = false;
    this.views = new ArrayList<>();
    fillCel();
    insertBombs(bombs);
  }

  @Override
  public void restartTable(ArrayList<Bomb> bombs) {
    this.col = setup.getCol();
    this.row = setup.getRow();
    this.bombs = bombs;
    this.lose = false;
    this.win = false;
    this.block = false;
    fillCel();
    insertBombs(bombs);
    notifyReset();
  }

  private void fillCel() {
    bombs = new ArrayList<>();
    cells = new Cell[row][col];
    for (int x = 0; x < row; x++) {
      for (int y = 0; y < col; y++) {
        cells[x][y] = new Cell(x, y);
      }
    }
  }

  @Override
  protected void insertBombs(ArrayList<Bomb> bombs) {
    int x;
    int y;
    this.bombs = bombs;
    for (Bomb bomb : bombs) {
      x = bomb.getX();
      y = bomb.getY();
      cells[x][y].setBomb(bomb);
    }
  }

  @Override
  public void putFlag(int x, int y) {
    cells[x][y].setFlag(true);
    notifyCellFlag(cells[x][y]);
  }

  @Override
  public void removeFlag(int x, int y) {
    cells[x][y].setFlag(false);
    notifyCellFlag(cells[x][y]);
  }

  @Override
  public TypeBomb discoverCell(int x, int y) {
    cells[x][y].setExposed(true);
    if (cells[x][y].isBomb() == true) {
      notifyCellBomb(cells[x][y]);
      return cells[x][y].getTypeBomb();
    }
    return TypeBomb.NONE;
  }

  @Override
  public void checkWin() {
    if (checkAllState()) {
      this.win = true;
      gameover();
      notifyWin();
    }
  }

  private boolean checkAllState() {
    boolean res = true;
    int numCel = row * col;
    int aux = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (cells[i][j].getExposed() || cells[i][j].getFlag()) {
          aux++;
        }
      }
    }
    if (numCel != aux) {
      res = false;
    }
    return res;
  }

  @Override
  public void youLose() {
    lose = true;
    gameover();
    notifyLose();
  }

  private void gameover() {
    int x;
    int y;
    for (Bomb bomb : bombs) {
      x = bomb.getX();
      y = bomb.getY();
      notifyCellBomb(cells[x][y]);
    }
    block = true;
  }

  @Override
  public void addObserver(View view) {
    this.views.add(view);
  }

  @Override
  public void notifyCellFlag(Cell cell) {
    views.forEach((view) -> {
      view.updateCellFlag(cell);
    });
  }

  @Override
  public void notifyCellDiscover(Cell cell) {
    views.forEach((view) -> {
      view.updateCellDiscover(cell);
    });
  }

  @Override
  public void notifyCellBomb(Cell cell) {
    views.forEach((view) -> {
      view.updateCellBomb(cell);
    });
  }

  @Override
  public void notifyLose() {
    views.forEach((view) -> {
      view.updateLose();
    });
  }

  @Override
  public void notifyWin() {
    views.forEach((view) -> {
      view.updateWin();
    });
  }

  @Override
  public void notifyReset() {
    views.forEach((view) -> {
      view.updateReset();
    });
  }

  @Override
  public void notifyScore(int score) {
    views.forEach((view) -> {
      view.updateScore(score);
    });
  }
}
