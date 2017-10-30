package model;

import model.table.Cell;
import views.View;

public interface Subject {

  public void addObserver(View o);

  public void notifyCellFlag(Cell c);

  public void notifyCellDiscover(Cell c);

  public void notifyCellBomb(Cell c);

  public void notifyLose();

  public void notifyWin();

  public void notifyReset();

  public void notifyScore(int score);
}
