package views;

import model.table.Cell;

public interface View {

  public void restartGame();

  public void updateCellFlag(Cell c);

  public void updateCellDiscover(Cell c);

  public void updateCellBomb(Cell c);

  public void updateLose();

  public void updateWin();

  public void updateReset();

  public void updateScore(int score);
}
