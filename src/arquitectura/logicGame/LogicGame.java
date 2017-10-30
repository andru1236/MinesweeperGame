package arquitectura.logicGame;

import arquitectura.Setup;
import model.Table;
import model.table.Bomb;
import views.View;
import java.util.ArrayList;

public abstract class LogicGame {

  protected Table table;
  protected Setup setup;
  protected ArrayList<Bomb> bombs;

  public String nameGame;
  public String modality;
  public int valueModality;

  public abstract void restartGame();

  protected abstract void addrandomBombtoBombs();

  public abstract void putFlag(int x, int y);

  public abstract void discoverCell(int x, int y);

  public abstract void move2(int x, int y);

  public abstract void addObservertoTable(View view);

}
