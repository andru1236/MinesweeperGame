package model;

import model.table.Bomb;
import model.table.Cell;
import model.table.TypeBomb;
import arquitectura.Setup;
import java.util.ArrayList;
import views.View;

public abstract class Table implements Subject {

  protected ArrayList<Bomb> bombs;
  protected ArrayList<View> views;
  protected Cell[][] cells;
  protected boolean block;
  protected boolean lose;
  protected boolean win;
  protected Setup setup;
  protected int col;
  protected int row;

  protected abstract void insertBombs(ArrayList<Bomb> bombs);

  public abstract void restartTable(ArrayList<Bomb> bombs);

  public abstract void putFlag(int x, int y);

  public abstract void removeFlag(int x, int y);

  public abstract TypeBomb discoverCell(int x, int y);

  public abstract void checkWin();

  public abstract void youLose();

  public boolean isBomb(int x, int y) {
    return cells[x][y].isBomb();
  }

  public boolean isFlag(int x, int y) {
    return cells[x][y].getFlag();
  }

  public boolean isExposed(int x, int y) {
    return cells[x][y].getExposed();
  }

  public void putCellValue(int x, int y, int valor) {
    cells[x][y].setValue(valor);
    cells[x][y].setExposed(true);
    notifyCellDiscover(cells[x][y]);
  }

  public boolean isBlock() {
    return block;
  }

  public boolean isValidPosition(int x, int y) {
    return x >= 0 && x < row && y >= 0 && y < col;
  }

  public boolean isWin() {
    return win;
  }

  public boolean isLose() {
    return lose;
  }

  public ArrayList<Bomb> getBombs() {
    return bombs;
  }
}
